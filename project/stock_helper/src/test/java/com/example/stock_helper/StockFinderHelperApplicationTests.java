package com.example.stock_helper;

import com.example.stock_helper.stock.Stock;
import com.example.stock_helper.python.StockFinder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


class StockFinderHelperApplicationTests {

	@Test
	public void 향상된_주식상세정보가져오기(){
		//given
		String stockName = "동화약품";

		//when
		Stock stockDetail = StockFinder.getStockDetail(stockName);
		// then
		System.out.println("stockDetail = " + stockDetail);
		Assertions.assertEquals(stockName,stockDetail.getStockName());
	}

	@Test
	public void 전체주식리스트생성(){
	    //given

	    //when
		List<Stock> stocks = StockFinder.getStocks();
	    // then
		System.out.println("stocks = " + stocks.get(0).toString());
		System.out.println("거래대금="+stocks.get(0).getStockTransactionAmount());

		System.out.println(stocks.get(0).testString());
		Assertions.assertEquals("동화약품",stocks.get(0).getStockName());
	}
}
