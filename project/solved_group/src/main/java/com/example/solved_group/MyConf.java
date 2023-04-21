package com.example.solved_group;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConf {
    @Bean
    Jongno jongno(){
      Jongno jn = new Jongno(
              "https://www.jongno.go.kr/portal/bbs/selectBoardList.do?bbsId=BBSMSTR_000000000201&menuId=1752&menuNo=1752",
              "//*[@id=\"subContent\"]/table/tbody/tr",
              false,
              -1,1,5
      );
      return jn;
    }


    @Bean
    Joongrang joongrang(){
        Joongrang jr = new Joongrang(
                "https://www.jungnang.go.kr/portal/bbs/list/B0000117.do?menuNo=200475",
                "//*[@id=\"content\"]/div/div[3]/div/table/tbody/tr",
                false,
                0,1,4
        );
        return jr;
    }
}
