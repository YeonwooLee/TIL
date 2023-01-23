# 코드

```java
package com.yanoos.bottomOfLegends.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yanoos.bottomOfLegends.conf.ErrorMsg;
import com.yanoos.bottomOfLegends.conf.riot.ApiKey;
import com.yanoos.bottomOfLegends.conf.riot.ApiUrlHeader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

import static com.yanoos.bottomOfLegends.conf.riot.ApiKey.API_KEY;
import static com.yanoos.bottomOfLegends.conf.riot.ApiKey.API_KEY_QUERY_PARAMETER;

@Slf4j
@Component
public class ApiCall {

    //Map<String,Object>로 받아옴
    public Map<String, Object> getMap(String baseUrl, String urlPath, Object[] paramForm) {
        String url =makeRiotRequestUrl(baseUrl, urlPath, paramForm);
        return WebClient.builder().build().get().uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(status -> !status.is2xxSuccessful(), clientResponse -> {
                    // Handle non-2xx status codes
                    throw new RuntimeException(String.format(ErrorMsg.RESPONSE_STATUS_NOT_2XX.getValue(),clientResponse.statusCode()));
                })
                .bodyToMono(Map.class)
                .block();
    }



    //url로 요청 보내고 T타입 객체 받아옴
    //Mono객체가 멀티스레딩에 유리함
    public <T> T getCustom(String baseUrl, String path, Object paramForm, Class<T> responseType) {
        String url =makeRiotRequestUrl(baseUrl, path, paramForm);
        log.info("riot api request = {}",url);
        return WebClient.builder().build().get().uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(status -> !status.is2xxSuccessful(), clientResponse -> {
                    // Handle non-2xx status codes
                    throw new RuntimeException(String.format(ErrorMsg.RESPONSE_STATUS_NOT_2XX.getValue(),clientResponse.statusCode()));
                })
                .bodyToMono(responseType)
                .block();
    }

    //url조합 후 마지막에 riot_api 쿼리 파라미터 추가
    public String makeRiotRequestUrl(String baseUrl, String urlPath, Object requestForm) {
        String urlFrom;
        try{
            log.info("쿼리 파라미터 type check(별말 없으면 map type임)");
            Map<String,String> params = objectToMap(requestForm);
            urlFrom = applyMapToUrlFormat(baseUrl+urlPath,params);
        }catch (IllegalArgumentException e){
            log.info("쿼리 파라미터 type = String[]");
            String[] params = (String[])requestForm;
            urlFrom = String.format(changeFormatToPercentType(baseUrl+urlPath),params);
        }

        UriComponents riotRequestUrl = UriComponentsBuilder.fromUriString(urlFrom).queryParam(API_KEY_QUERY_PARAMETER.getValue(),API_KEY.getValue())
                .build();
        return riotRequestUrl.toString();
    }

    //주소를 www.{name}.com 에서 www.%s.com 방식으로 바꿔서 formating 가능하도록 변경
    private String changeFormatToPercentType(String formatString){
        log.info("String[] typ 쿼리 파라미터로 인한 changeFormatToPercentType 실행" +
                "-- 파라미터가 %s에 대입될 순서대로 입력되어있어야함");
        StringBuilder sb = new StringBuilder();
        boolean flag = true;
        for(int i=0;i<formatString.length();i++){
            String s = formatString.substring(i,i+1);
            if(s.equals("{")){
                sb.append("%");
                flag=false;
            }else if(s.equals("}")){
                s="s";
                flag=true;
            }
            if(flag) sb.append(s);
        }
        return sb.toString();
    }

    private String applyMapToUrlFormat(String urlFormat, Map<String,String> map){
        log.debug("urlFormat = {}",urlFormat);
        for(String key:map.keySet()){
            String val = map.get(key);
            key = String.format("{%s}",key);

            urlFormat=urlFormat.replace(key,val);
            log.debug("key is {}",key);
            log.debug("urlFormat = {}",urlFormat);
        }
        log.debug("final urlFormat = {}",urlFormat);
        return urlFormat;

    }

    //객체를 맵으로
    private Map<String,String> objectToMap(Object object){
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,String> map = objectMapper.convertValue(object,Map.class);
        return map;
    }


}

```

# 사용예시

```java
package com.yanoos.bottomOfLegends.riotAPI;

import com.yanoos.bottomOfLegends.riotAPI.dto.*;
import com.yanoos.bottomOfLegends.util.ApiCall;
import com.yanoos.bottomOfLegends.summoner.Summoner;
import com.yanoos.bottomOfLegends.riotAPI.requestForm.GetMatchIdsByPuuidForm;
import com.yanoos.bottomOfLegends.util.BasicUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static com.yanoos.bottomOfLegends.conf.riot.ApiUrl.*;
import static com.yanoos.bottomOfLegends.conf.riot.ApiUrlHeader.*;
import static com.yanoos.bottomOfLegends.conf.riot.NoFormData.*;
import static com.yanoos.bottomOfLegends.conf.riot.QueueType.*;
import static org.junit.jupiter.api.Assertions.*;


class APICallTest {
    @Autowired
    RiotApiImpl riotApiService;


    private Summoner summoner;
    private Summoner summoner1;

    @Test
    public void url_만들기(){//소환사명으로 소환사 DTO 호출하는 주소 만들기
        //given
        ApiCall apiCall = new ApiCall();
        String baseURl = KR_HEADER.getValue();
        String path = GET_SUMMONER_BY_SUMMONER_NAME.getValue();
        String[] params = {"현대 그랜저"};
        //when
        String res = apiCall.makeRiotRequestUrl(baseURl, path, params);

        // then
        String expect = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/현대 그랜저?api_key=RGAPI-5f54c32b-21e1-4d3a-8c28-9c8c3b68d0fd";
        assertEquals(expect,res);
    }
    @Test
    public void API_요청_맵타입반환_성공_테스트(){
        //given
        ApiCall apiCall = new ApiCall();
        String baseURl = KR_HEADER.getValue();
        String path = GET_SUMMONER_BY_SUMMONER_NAME.getValue();
        String[] params = {"현실은"};
        //when
        Map<String,Object> map = apiCall.getMap(baseURl, path, params);
        
        // then
        String expectPuuid = "atJeikrF4pdO-13WYXNWb0fsi6nNPPpn5vGtosfod7sx4KHUdfiS8K4xAdcLjxI6JyiH8kTnxLctmw";
        String puuid = (String)map.get("puuid");
        assertEquals(expectPuuid,puuid);
    }
    @Test
    public void API_요청_맵타입반환_실패_테스트(){
        //given
        ApiCall apiCall = new ApiCall();
        String baseURl = KR_HEADER.getValue();
        String path = GET_SUMMONER_BY_SUMMONER_NAME.getValue();
        String[] params = {"현실*은"};
        //when


        // then
        assertThrows(RuntimeException.class,()->{
            Map<String,Object> map = apiCall.getMap(baseURl, path, params);
        });
    }
    @Test
    public void API_요청_지정타입반환_성공_테스트(){//소환사명으로 소환사 DTO 호출
        //given
        ApiCall apiCall = new ApiCall();
        String baseURl = KR_HEADER.getValue();
        String path = GET_SUMMONER_BY_SUMMONER_NAME.getValue();
        String[] params = {"현실은"};


        //when
        summoner = apiCall.getCustom(baseURl, path, params, Summoner.class);



        // then
        System.out.println("summoner = " + summoner);
        String expectPuuid = "atJeikrF4pdO-13WYXNWb0fsi6nNPPpn5vGtosfod7sx4KHUdfiS8K4xAdcLjxI6JyiH8kTnxLctmw";
        assertEquals(expectPuuid, summoner.getPuuid());
    }
    @Test
    public void API_요청_지정타입반환_실패_테스트(){//소환사명으로 소환사 DTO 호출
        //given
        ApiCall apiCall = new ApiCall();
        String baseURl = KR_HEADER.getValue();
        String path = GET_SUMMONER_BY_SUMMONER_NAME.getValue();
        String[] params = {"현실-은"};


        //when

        // then
        assertThrows(RuntimeException.class,()->{
            summoner = apiCall.getCustom(baseURl, path, params, Summoner.class);
        });
    }
    @Test
    public void API_요청_지정타입인_리스트로_반환(){//puuid로 게임 리스트 호출
        //given
        ApiCall apiCall = new ApiCall();
        String baseURl = ASIA_HEADER.getValue();
        String path = GET_MATCH_IDS_BY_PUUID.getValue();
        GetMatchIdsByPuuidForm params = new GetMatchIdsByPuuidForm("atJeikrF4pdO-13WYXNWb0fsi6nNPPpn5vGtosfod7sx4KHUdfiS8K4xAdcLjxI6JyiH8kTnxLctmw",
                "","",RANK_SOLO.getValue(),"","0","20");

        //when
        List<String> game = apiCall.getCustom(baseURl, path, params, List.class);


        // then
        System.out.println("game = " + game);
        String expectPuuid = "eQcvg3_JdOBD-kQFSI8tBwouugqXoq2l1tyMhBTUdSpxzC487eTn_N-9M293p6XmyuK9WlqYwsF_mA";

    }
    @Test
    public void API_요청_지정타입인_리스트로_반환_실패테스트(){//puuid로 게임 리스트 호출
        //given
        ApiCall apiCall = new ApiCall();
        String baseURl = ASIA_HEADER.getValue();
        String path = GET_MATCH_IDS_BY_PUUID.getValue();
        GetMatchIdsByPuuidForm params = new GetMatchIdsByPuuidForm("{실패용텍스트}atJeikrF4pdO-13WYXNWb0fsi6nNPPpn5vGtosfod7sx4KHUdfiS8K4xAdcLjxI6JyiH8kTnxLctmw",
                "","",RANK_SOLO.getValue(),"","0","20");

        //when
        //then
        assertThrows(RuntimeException.class,()->{
            List<String> game = apiCall.getCustom(baseURl, path, params, List.class);
        });

    }
    @Test
    public void 다중객체구조API요청을_사용하는_방법_예제(){//matchId로 매치 정보 호출
        //given
        ApiCall apiCall = new ApiCall();
        String baseURl = ASIA_HEADER.getValue();
        String path = GET_MATCH_BY_MATCH_ID.getValue();
        String[] params = {"KR_6277224710"};
        //when
        Map<String, Object> match = apiCall.getCustom(baseURl, path, params,Map.class);
        Map<String,Object> matchInfo = (Map<String,Object>) match.get(INFO.getValue());
        List<Object> participants = (List<Object>) matchInfo.get(PARTICIPANTS.getValue());


        // then
        ParticipantDTO participantDTO = BasicUtil.objectToObject(participants.get(0), ParticipantDTO.class,true);

        assertEquals("Darius",participantDTO.getChampionName());

    }
    
    

}
```

