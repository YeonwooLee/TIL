package com.example.stock_helper.python.cybos5;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class GetStockDetailDTO {
    // @SerializedName("stock_name") //파이썬 리턴(dict)에서는 key가 stock_name임
    private String stockName;

    // @SerializedName("stock_code")//파이썬 리턴(dict)에서는 key가 stock_code임
    private String stockCode;

    private float stockRise; //상승률
    private int riseRank;//상승 순위

    private long stockTransactionAmount; //거래대금
    private int amountRank;//거래대금 순위

    @Override
    public String toString(){
        //국일제지 [ -0.95%(00위) / 거래대금 10억(00위) ]
        return String.format("◎ %s [%.2f%%(%d위) / 거래대금 %d억(%d위)]",stockName,stockRise,riseRank,stockTransactionAmount/100000000,amountRank);
    }
}
