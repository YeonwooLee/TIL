package com.example.stock_helper.python;

import com.example.stock_helper.stock.Stock;
import com.example.stock_helper.telegram.strings.Message;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.example.stock_helper.telegram.strings.MyErrorMsg.NO_STOCK_NAME_ERROR;

@Slf4j
@Component
@RequiredArgsConstructor
@Setter
public class StockFinder {
    private final ReadPython readPython;

    //전체 주식 리스트
    public List<Stock> getStocks(){
        //TODO 이걸 매번 파이썬 켜서 api 연결해서 가져올 생각하지말고 ScheduledTasks 이용해서 db에 stock리스트 저장해둘 것
        return Arrays.asList(readPython.readPythonFile(Stock[].class,"cybos5\\allStockInfo",new String[]{""}));
    }
    //단일 주식
    public Stock getStockDetail(String stockName){
        List<Stock> stocks = getStocks(); //지금 따끈하게 구어오는 전체 주식 리스트

        Stock result = new Stock();
        boolean findFlag = false;//없는 주식 체크용
        for(int i=0;i<stocks.size();i++){
            Stock curStock = stocks.get(i);
            if(curStock.getStockName().equals(stockName)){
                findFlag = true; //찾았당
                result = curStock;
                break;
            }
        }
        //주식명이 주식 리스트에 없음 에러
        if(!findFlag) throw new RuntimeException(String.format(NO_STOCK_NAME_ERROR.getMsgFormat(),stockName));
        return result;
    }

    //오늘의 핫한 주식
    public List<String> makeTodayHotStock(int riseRate, long hundredMillion){
        log.info("todayHot >>> riseRate = {}, hundredMillion = {}",riseRate, hundredMillion);
        List<Stock> stocks = getStocks();//
        List<Stock> result = new ArrayList<>();

        for(Stock st : stocks){
            if(st.getStockTransactionAmount()>=hundredMillion && st.getStockRise()>=riseRate){
                result.add(st);
            }
        }

        //이름순정렬
        Collections.sort(result, new Comparator<Stock>(){
            @Override
            public int compare(Stock s1, Stock s2){
                return s1.getStockName().compareTo(s2.getStockName());
            }
        });

        List<String> finalResult = new ArrayList<>(); //스트링 포멧 맞추기
        for(int i=0;i<result.size();i++){//Stock 리스트 순회
            Stock stock = result.get(i);//현재 주식

            //문자열화 하기 위한 데이터 필드
            String stockName = stock.getStockName();
            float stockRise = stock.getStockRise();
            int riseRank = stock.getRiseRank();
            long stockTransactionAmount = stock.getStockTransactionAmount() / 100000000;
            int amountRank = stock.getAmountRank();
            float per = stock.getPer();
            long marketCapitalization = stock.getMarketCapitalization()/100000000;
            //문자열 포멧팅
            String strStock = String.format(Message.HOT_STOCK_INF.getMsgFormat(),
                    stockName,
                    stockRise,
                    riseRank,
                    stockTransactionAmount,
                    amountRank,
                    marketCapitalization,
                    per);

            //최종결과리스트에 삽입
            finalResult.add(strStock);
        }
        return finalResult;
    }
}
