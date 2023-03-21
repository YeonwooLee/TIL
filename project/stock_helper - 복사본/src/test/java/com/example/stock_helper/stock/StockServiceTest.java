package com.example.stock_helper.stock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StockServiceTest {
    @Autowired
    StockService stockService;
    @Test
    public void 전체종목파이썬검색후db에넣기(){
        //given

        //when
        stockService.reportCurrentTime();
        // then
    }
    @Test
    public void 주식상세정보가져오기(){
        //given
        String stockName = "솔트룩스";

        //when
        Stock stockDetail = stockService.getStockDetail(stockName);
        // then
        System.out.println("stockDetail = " + stockDetail);
        Assertions.assertEquals(stockName,stockDetail.getStockName());
    }

    @Test
    public void 최신주식목록(){
        //given

        //when
        List<Stock> latestStock = stockService.getLatestStock();
        // then
        for(Stock stock:latestStock){
            System.out.println("stock = " + stock);
        }

    }
}