package com.example.stock_helper.python;

import com.example.stock_helper.stock.Stock;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static com.example.stock_helper.telegram.strings.MyErrorMsg.NO_STOCK_NAME_ERROR;


@Component
@RequiredArgsConstructor
@Setter
public class StockFinder {
    private final ReadPython readPython;

    //향상된 getStockDetail
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

    public List<Stock> getStocks(){
        //TODO 이걸 매번 파이썬 켜서 api 연결해서 가져올 생각하지말고 ScheduledTasks 이용해서 db에 stock리스트 저장해둘 것
        return Arrays.asList(readPython.readPythonFile(Stock[].class,"cybos5\\allStockInfo",new String[]{""}));
    }
}
