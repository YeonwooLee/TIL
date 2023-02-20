package com.example.stock_helper.stock;

import com.example.stock_helper.python.StockFinder;
import com.example.stock_helper.telegram.strings.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.example.stock_helper.telegram.strings.MyErrorMsg.DIFFERENCE_STOCK_COUNT;
import static com.example.stock_helper.telegram.strings.MyErrorMsg.NO_STOCK_NAME_ERROR;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockService {
    private final StockFinder stockFinder;
    private final StockRepository stockRepository;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //30분마다 업뎃침
    @Scheduled(cron = "30 */30 * * * *") //
    public void scheduledReportCurrentTime(){
        reportCurrentTime();
    }

    //주식 최신화 with python
    // @Scheduled(cron = "30 */30 * * * *") //
    @Transactional
    public void reportCurrentTime() {
        // stockFinder.setTempStock(stockFinder.getStocks());
        log.info("주식리스트 갱신 {}", dateFormat.format(new Date()));
        String firstCheckTime = getFirstTime();//최초 리스트 작성 시점


        //TODO db에 주식 리스트 갱신 현재시간+주식코드 = pk, 주식명, 현재가, 등수 등
        List<Stock> stocks = stockFinder.getStocks();
        boolean isOk = validateStockList(stocks,firstCheckTime);
        for (Stock stock : stocks) {
            stockRepository.save(stock);
        }
        if(isOk){
            for (Stock stock : stocks) {
                stockRepository.save(stock);
            }

        }else{
            throw new RuntimeException(String.format(DIFFERENCE_STOCK_COUNT.getMsgFormat(),stocks.size()));
        }



    }

    //최신스탁리스트
    public List<Stock> getLatestStock(){
        return stockRepository.findLatestStock();
    }

    //단일 주식
    public Stock getStockDetail(String stockName){
        // List<Stock> stocks = getStocks(); //지금 따끈하게 구어오는 전체 주식 리스트
        List<Stock> stocks = stockRepository.findLatestStockByStockName(stockName);
        //주식명이 주식 리스트에 없음 에러
        if(stocks.size()==0) throw new RuntimeException(String.format(NO_STOCK_NAME_ERROR.getMsgFormat(),stockName));
        return stocks.get(0);
    }
    //오늘의 핫한 주식
    public List<String> makeTodayHotStock(int riseRate, long hundredMillion){
        log.info("todayHot >>> riseRate = {}, hundredMillion = {}",riseRate, hundredMillion);
        List<Stock> stocks = getLatestStock();//
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
            long dayForeignNetPurchase = stock.getDayForeignNetPurchase();
            long dayInstitutionalNetPurchase = stock.getDayInstitutionalNetPurchase();

            //문자열 포멧팅
            String strStock = String.format(Message.HOT_STOCK_INF.getMsgFormat(),
                    stockName,
                    stockRise,
                    riseRank,
                    stockTransactionAmount,
                    amountRank,
                    marketCapitalization,
                    per,
                    dayForeignNetPurchase,
                    dayInstitutionalNetPurchase);

            //최종결과리스트에 삽입
            finalResult.add(strStock);
        }
        return finalResult;
    }

    //마지막 검색 시간
    public String getLastTime(){
        return stockRepository.findMaxSearchTime().get(0);
    }
    
    //처음 검색 시간
    public String getFirstTime(){
        return stockRepository.findMinSearchTime().get(0);
    }

    //새로받아온 주식 리스트 검증
    boolean validateStockList(List<Stock> newStocks, String firstCheckTime){//이번에 만든 주식 리스트, 이전 검색 시점

        long lastStockListSize = stockRepository.countBySearchTime(firstCheckTime);
        if(lastStockListSize==0) return true;
        return newStocks.size()>=lastStockListSize?true:false;
    }

    public boolean passMinuteLastCheck(String time,int diff) throws ParseException {
        //TODO 마지막 검색 시간보다 3분 이상 지났으면 reportCurrentTime()
        //TODO String input = "2023-02-08 00:00:00.000"을 시간화 하는 것부터 시작
        return true;
    }
}