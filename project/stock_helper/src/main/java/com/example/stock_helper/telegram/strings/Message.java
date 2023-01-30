package com.example.stock_helper.telegram.strings;


import lombok.Getter;

@Getter
public enum Message {
    TODAY_HOT_STOCK_MSG_HEADER("<오늘의 핫한 주식 목록>\n"),
    NOT_EXIST_STOCK_NAME("존재하지 않는 주식명: [%s]");

    Message(String msgFormat){
        this.msgFormat = msgFormat;
    }
    private String msgFormat;
}