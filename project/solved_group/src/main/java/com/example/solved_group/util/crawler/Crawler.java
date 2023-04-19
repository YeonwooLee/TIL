package com.example.stock_helper.util.crawling;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;



public abstract class Crawler<T> {
    protected WebDriver driver;// 드라이버
    protected String url;
    protected boolean isTest;

    public Crawler(String url,boolean isTest){
        this.isTest = isTest;
        this.url = url;
    }

    public T crawling(){
        //옵션세팅
        ChromeOptions options = getOption(isTest);
        //드라이버 세팅
        setDriver(options);
        //페이지이동
        movePage();
        //파싱데이터 생성
        T result = parsePage();
        //드라이버 종료
        quitDriver();
        //파싱데이터 리턴
        return result;
    }
    //옵션 세팅
    public ChromeOptions getOption(boolean isTest){
        ChromeOptions options = new ChromeOptions();
        if(!isTest) options.addArguments("headless");
        options.addArguments("--remote-allow-origins=*");
        return options;
    }

    //드라이버 연결
    public void setDriver(ChromeOptions options){
        WebDriverManager.chromedriver().setup();//드라이버 버전 맞추는거
        driver = new ChromeDriver(options);
        this.driver = driver;
    }

    //페이지 이동
    public void movePage(){
        driver.get(url);
    }

    //드라이버 종료
    public boolean quitDriver(){
        driver.quit();
        return true;
    }

    public abstract T parsePage();



}
