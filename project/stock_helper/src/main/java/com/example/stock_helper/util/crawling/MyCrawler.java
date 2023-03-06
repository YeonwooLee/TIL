package com.example.stock_helper.util.crawling;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyCrawler {
    public static Map<String,String> temp() throws InterruptedException {
        // Set up the WebDriver using WebDriverManager

        // Create a new instance of the ChromeDriver
        WebDriver driver = WebDriverManager.chromedriver().create();

        // driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        // driver.findElement(By.partialLinkText("form")).click();

        // Navigate to the desired webpage
        driver.get("https://blog.naver.com/nazoosikwang");
        driver.switchTo().frame("mainFrame");//frame id

        // Find the element by ID and click on it
        // WebElement element = driver.findElement(By.partialLinkText("산업"));//이런 것도 있다

        //주식사전 클릭
        WebElement element = driver.findElement(By.xpath("//*[@id=\"category282\"]"));
        element.click();

        List<WebElement> elements = driver.findElements(By.className("se-text-paragraph-align-"));

        // Get the page source
        String pageSource = driver.getPageSource();

        // int idx=0;
        // for(WebElement e : elements){
        //
        //     System.out.println("e.getText() = " + e.getText());
        //     idx++;
        //     if(idx==5) break;
        // }


        Map<String,String> map = new HashMap<>();
        for(int i=0;i< elements.size();i++){
            String cur = elements.get(i).getText();
            if(!cur.contains(":")) continue;//:없으면 continue;
            String[] keyAndVal = cur.split(":");

            String key = keyAndVal[0];
            String val = keyAndVal[1];

            map.put(key,val);

        }
        // Close the WebDriver
        driver.quit();

        return map;


    }
}
