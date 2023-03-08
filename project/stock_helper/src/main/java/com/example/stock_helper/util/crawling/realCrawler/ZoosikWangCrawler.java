package com.example.stock_helper.util.crawling.realCrawler;

import com.example.stock_helper.util.crawling.Crawler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class ZoosikWangCrawler extends Crawler<List<ZoosikWangZoosikSajun>> {


    public ZoosikWangCrawler(ChromeOptions chromeOption, WebDriver driver) {
        super(chromeOption, driver,
                ZoosikWangEnum.SITE_URL.getValue(),
                ZoosikWangEnum.XPATH_OF_ELEMENT_GROUP.getValue());
    }

    @Override
    public void movePage() {
        //주식왕 홈페이지로 이동
        driver.get(url);

        //iframe 이동
        driver.switchTo().frame("mainFrame");

        //주식사전 클릭
        WebElement element = driver.findElement(By.xpath("//*[@id=\"category282\"]"));
        element.click();

    }

    @Override
    public List<ZoosikWangZoosikSajun> crawling() {
        //원본주식 tag
        WebElement zoosikSajunElement = driver.findElement(By.xpath(xpathOfElementGroup));
        //문자열추출
        String[] zoosikArr = zoosikSajunElement.getText().split("\n");


        // Map<String,String> map = new HashMap<>();
        List<ZoosikWangZoosikSajun> zoosikSajun = new ArrayList<>();

        for(int i=0;i< zoosikArr.length;i++){
            String cur = zoosikArr[i];
            if(!cur.contains(":")) continue;//:없으면 continue;
            String[] keyAndVal = cur.split(":",2);

            //주식명
            String stockName = keyAndVal[0];
            //테마정리
            List<String> theme= Arrays.stream(keyAndVal[1].replace("/ h",",h").replace(" ","").split(",")).toList();
            //현재주식
            ZoosikWangZoosikSajun curStock = new ZoosikWangZoosikSajun(stockName,theme);
            //사전에 add
            zoosikSajun.add(curStock);
        }

        return zoosikSajun;
    }
}
