package com.example.stock_helper.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter

public class Stock {
    // @SerializedName("stock_name") //파이썬 리턴(dict)에서는 key가 stock_name임
    private String stockName;

    // @SerializedName("stock_code")//파이썬 리턴(dict)에서는 key가 stock_code임
    private String stockCode;

    private float stockRise; //상승률
    private int riseRank;//상승 순위

    private long stockTransactionAmount; //거래대금
    private int amountRank;//거래대금 순위

    private String searchTime;//검색 시간(추정)

    @Override
    public String toString(){
        return String.format("$Hot$ %s [%.2f%%(%d위) / 거래대금 %d억(%d위)]",stockName,stockRise,riseRank,stockTransactionAmount/100000000,amountRank);
    }
}
