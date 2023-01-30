package com.example.stock_helper.python;

import com.example.stock_helper.stock.Stock;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;



@RequiredArgsConstructor
public class StockFinder {
    //향상된 getStockDetail
    public static Stock getStockDetail(String stockName){
        List<Stock> stocks = getStocks(); //전체 주식 리스트

        Stock result = new Stock();
        for(int i=0;i<stocks.size();i++){
            Stock curStock = stocks.get(i);
            if(curStock.getStockName().equals(stockName)){
                result = curStock;
                break;
            }
        }
        return result;
    }

    public static List<Stock> getStocks(){
        return Arrays.asList(ReadPython.readPythonFile(Stock[].class,"cybos5\\allStockInfo",new String[]{""}));
    }
}
