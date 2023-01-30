package com.example.stock_helper;

import com.example.stock_helper.stock.Stock;
import com.example.stock_helper.python.StockFinder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class StockFinderHelperApplicationTests {
	@Autowired
	StockFinder stockFinder;

	@Test
	public void 향상된_주식상세정보가져오기(){
		//given
		String stockName = "동화약품";

		//when
		Stock stockDetail = stockFinder.getStockDetail(stockName);
		// then
		System.out.println("stockDetail = " + stockDetail);
		Assertions.assertEquals(stockName,stockDetail.getStockName());
	}

	@Test
	public void 전체주식리스트생성(){
	    //given

	    //when
		List<Stock> stocks = stockFinder.getStocks();
	    // then
		System.out.println("stocks = " + stocks.get(0).toString());
		System.out.println("거래대금="+stocks.get(0).getStockTransactionAmount());

		System.out.println(stocks.get(0).testString());
		Assertions.assertEquals("동화약품",stocks.get(0).getStockName());
	}
}
