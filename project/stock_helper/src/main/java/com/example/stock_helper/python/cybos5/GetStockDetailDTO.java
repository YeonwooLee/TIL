package com.example.stock_helper.python.cybos5;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetStockDetailDTO {
    @SerializedName("stock_name") //파이썬 리턴(dict)에서는 key가 stock_name임
    private String stockName;

    @SerializedName("stock_code")//파이썬 리턴(dict)에서는 key가 stock_code임
    private String stockCode;
}
