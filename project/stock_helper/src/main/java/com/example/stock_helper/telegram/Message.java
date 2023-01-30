package com.example.stock_helper.telegram;


import lombok.Getter;

@Getter
public enum Message {
    TODAY_HOT_STOCK_MSG_HEADER("<오늘의 핫한 주식 목록>\n");

    Message(String msgFormat){
        this.msgFormat = msgFormat;
    }
    private String msgFormat;
}