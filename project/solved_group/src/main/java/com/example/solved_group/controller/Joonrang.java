package com.example.solved_group.controller;

import com.example.solved_group.util.crawler.notice.Notice;
import com.example.solved_group.util.crawler.notice.NoticeCrawler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Joonrang extends NoticeCrawler {
    public Joonrang() {
        super("http://council.jungnang.go.kr/kr/announceBBS.do",
                "//*[@id=\"sub_board\"]/table/tbody/tr",
                true);
    }

    @Override
    public void movePage() {
        driver.get(url);

    }

    @Override
    public List<Notice> crawling() {
        List<Notice> noticeList = new ArrayList<>();
        printOrder();//순서확인
        setOrder(0,1,3);//순서배정

        List<WebElement> trList = driver.findElements(By.xpath(xpathOfElementGroup));
        for(WebElement tr: trList){
            noticeList.add(trToNotice(tr));
        }
        return noticeList;
    }

    @Override
    public Date toDate(String date) {
        return null;
    }
}
