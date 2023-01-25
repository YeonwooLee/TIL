package com.example.stock_helper.telegram;

import lombok.Getter;

@Getter
public enum Order {
    TODAY_HOT_STOCK("!");

    Order(String orderCode){
        this.orderCode = orderCode;
    }
    private String orderCode;
}