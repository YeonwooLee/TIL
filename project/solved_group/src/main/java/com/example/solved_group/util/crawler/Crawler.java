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
//    private ChromeOptions chromeOption;//크롬옵션
    protected WebDriver driver;// 드라이버

    protected String url;
    protected String xpathOfElementGroup;
    public Crawler(String url, String xpathOfElementGroup,boolean isTest){
        conn(isTest);
        this.url = url;
        this.xpathOfElementGroup = xpathOfElementGroup;

    }
    //드라이버 연결
    public boolean conn(boolean isTest){
        //옵션 세팅
        ChromeOptions options = new ChromeOptions();
        if(!isTest) options.addArguments("headless");
        options.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().setup();//이건뭐지
        driver = new ChromeDriver(options);
        this.driver = driver;
        return true;
    }
    //드라이버 종료
    public boolean quit(){
        driver.quit();
        return true;
    }


    public abstract void movePage();

    public abstract T crawling();


}
