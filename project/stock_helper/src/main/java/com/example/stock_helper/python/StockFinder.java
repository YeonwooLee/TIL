package com.example.stock_helper.python;

import com.example.stock_helper.dto.Stock;
import com.example.stock_helper.python.cybos5.GetStockDetailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
public class StockFinder {
    public static GetStockDetailDTO getStockDetail(String stockName){
        return ReadPython.readPythonFile(GetStockDetailDTO.class,"cybos5\\getStockDetail",new String[]{stockName});
    }

    public static Stock[] getStocks(){
        return ReadPython.readPythonFile(Stock[].class,"cybos5\\allStockInfo",new String[]{""});
    }
}
