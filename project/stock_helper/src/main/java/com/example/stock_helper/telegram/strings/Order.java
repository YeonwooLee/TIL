package com.example.stock_helper.telegram.strings;

import lombok.Getter;

@Getter
public enum Order {
    TODAY_HOT_STOCK("!"),
    EXCUTE_CYBOS_PLUS("연결!");

    Order(String orderCode){
        this.orderCode = orderCode;
    }
    private String orderCode;
}