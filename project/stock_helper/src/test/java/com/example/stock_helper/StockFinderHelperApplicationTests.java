package com.example.stock_helper;

import com.example.stock_helper.python.ReadPython;
import com.example.stock_helper.python.StockFinder;
import com.example.stock_helper.python.cybos5.GetStockDetailDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


class StockFinderHelperApplicationTests {
	@Test
	public void 주식상세정보가져오기(){
	    //given
		String stockName = "국일제지";

	    //when
		GetStockDetailDTO stockDetail = StockFinder.getStockDetail(stockName);
		// then
		System.out.println("stockDetail = " + stockDetail);
		Assertions.assertEquals(stockName,stockDetail.getStockName());
	}

}
