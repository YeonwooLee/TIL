package com.example.solved_group.util.crawler.notice;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.stock_helper.util.crawling.Crawler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class NoticeCrawler extends Crawler<List<Notice>> {
    protected Map<String,Integer> order;

    //크롤러생성
    //driver, url, tr xpath 세팅
    public NoticeCrawler(String url, String xpathOfElementGroup, boolean isTest) {
        super(url, xpathOfElementGroup, isTest);
        order = new HashMap<>();
    }
    //인덱스 결정을 위한 출력
    public void printOrder(){
        movePage();
        WebElement tr = driver.findElement(By.xpath(xpathOfElementGroup));
        List<WebElement> tds = tr.findElements(By.tagName("td"));
        for(int i=0;i<tds.size();i++){
            System.out.printf("%d번 인덱스 = %s\n",i,tds.get(i).getText().toString());
        }
    }
    //인덱스 결정
    public void setOrder(int noIdx, int titleIdx, int writeDateIdx){
        order.put("noIdx",noIdx);
        order.put("titleIdx",titleIdx);
        order.put("writeDateIdx",writeDateIdx);
    };

    //
    public Notice trToNotice(WebElement tr){
        List<WebElement> tds = tr.findElements(By.tagName("td"));
        //인덱스 가져옴
        int noIdx = order.get("noIdx");
        int titleIdx = order.get("titleIdx");
        int writeDateIdx = order.get("writeDateIdx");

        //인덱스로 값 추출
        String no = tds.get(noIdx).getText().toString();
        String title = tds.get(titleIdx).getText().toString();
        Date writeDate = toDate(tds.get(writeDateIdx).getText().toString());//date는 따로 파싱

        //객체 만든다
        Notice n = new Notice(no,title,writeDate);
        return n;
    }

    public abstract Date toDate(String date);





}
