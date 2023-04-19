package com.example.solved_group.util.crawler.notice;

import java.util.*;

import com.example.stock_helper.util.crawling.Crawler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class NoticeCrawler extends Crawler<List<Notice>> {
    protected Map<String,Integer> order;
    protected String xpathOfElementGroup;
    protected String defaultNo = "번호없음";
    protected String defaultTitle= "제목없음";
    protected Date defaultWriteDate=null;

    //크롤러생성
    //driver, url, tr xpath 세팅
    public NoticeCrawler(String url, String xpathOfElementGroup, boolean isTest,int noIdx,int titleIdx,int writeDateIdx) {
        super(url, isTest);
        this.xpathOfElementGroup = xpathOfElementGroup;
        //td인덱스 저장용 HashMap
        order = new HashMap<>();
        //임시 td인덱스 설정
        order.put("noIdx",noIdx);
        order.put("titleIdx",titleIdx);
        order.put("writeDateIdx",writeDateIdx);
    }

    @Override
    public List<Notice> parsePage(){
        List<Notice> noticeList = new ArrayList<>();//결과
        List<WebElement> trList = driver.findElements(By.xpath(xpathOfElementGroup));//tr집합
        if(isTest){//테스트면 인덱스출력
            printOrder();
        }
        //tr순회하면서 Notice화
        for(WebElement tr:trList){
            Notice curNotice = trToNotice(tr);//tr을 Notice로
            noticeList.add(curNotice);
        }
        return noticeList;
    }


    //인덱스 결정을 위한 출력
    public void printOrder(){
        WebElement tr = driver.findElement(By.xpath(xpathOfElementGroup));
        List<WebElement> tds = tr.findElements(By.tagName("td"));
        for(int i=0;i<tds.size();i++){
            System.out.printf("%d번 인덱스 = %s\n",i,tds.get(i).getText().toString());
        }
    }

    //
    public Notice trToNotice(WebElement tr){
        List<WebElement> tds = tr.findElements(By.tagName("td"));
        //인덱스 가져옴
        int noIdx = order.get("noIdx");
        int titleIdx = order.get("titleIdx");
        int writeDateIdx = order.get("writeDateIdx");

        //인덱스로 값 추출
        String no = noIdx!=-1?tds.get(noIdx).getText().toString():defaultNo;
        String title = titleIdx!=-1?tds.get(titleIdx).getText().toString():defaultTitle;
        Date writeDate = writeDateIdx!=-1?toDate(tds.get(writeDateIdx).getText().toString()):defaultWriteDate;//date는 따로 파싱

        //객체 만든다
        Notice n = new Notice(no,title,writeDate);
        return n;
    }

    public abstract Date toDate(String date);





}

