package com.example.stock_helper.util.crawling;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MyCrawlerTest {
    @Test
    public void 최초테스트() throws InterruptedException {
        //given
        Map<String, String> map = MyCrawler.temp();
        //when


        // then
        Assertions.assertEquals("[전선, 광케이블, 윤석열, https://blog.naver.com/nazoosikwang/222992093919]",map.get("가온전선"));
    }

}