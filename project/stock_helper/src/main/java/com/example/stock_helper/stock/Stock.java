package com.example.stock_helper.stock;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Stock {
    // @SerializedName("stock_name") //파이썬 리턴(dict)에서는 key가 stock_name임
    private String stockName;//종목명

    // @SerializedName("stock_code")//파이썬 리턴(dict)에서는 key가 stock_code임
    private String stockCode;//종목코드

    private float stockRise; //상승률
    private int riseRank;//상승 순위

    private long stockTransactionAmount; //거래대금
    private int amountRank;//거래대금 순위

    
    //가격
    private int openingPrice;//시가
    private int currentPrice;//현재가
    
    private String searchTime;//검색 시간(추정)


    public String testString(){
        StringBuilder sb = new StringBuilder();
        sb.append("종목명:"+stockName+"\n");
        sb.append("종목코드:"+stockCode+"\n");
        sb.append("상승률:"+stockRise+"\n");
        sb.append("상승 순위:"+riseRank+"\n");
        sb.append("거래대금:"+stockTransactionAmount+"\n");
        sb.append("거래대금 순위:"+amountRank+"\n");
        sb.append("시가:"+openingPrice+"\n");
        sb.append("현재가:"+currentPrice+"\n");
        sb.append("검색 시간(추정):"+searchTime+"\n");

        return sb.toString();

    }
}
