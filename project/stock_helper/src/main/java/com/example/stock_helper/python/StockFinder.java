package com.example.stock_helper.python;

import com.example.stock_helper.stock.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static com.example.stock_helper.telegram.strings.MyErrorMsg.NO_STOCK_NAME_ERROR;


@Component
@RequiredArgsConstructor
public class StockFinder {
    //향상된 getStockDetail
    public static Stock getStockDetail(String stockName){
        List<Stock> stocks = getStocks(); //전체 주식 리스트

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

    public static List<Stock> getStocks(){
        return Arrays.asList(ReadPython.readPythonFile(Stock[].class,"cybos5\\allStockInfo",new String[]{""}));
    }
}
