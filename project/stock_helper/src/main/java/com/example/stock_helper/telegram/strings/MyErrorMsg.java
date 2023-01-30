package com.example.stock_helper.telegram.strings;

import lombok.Getter;

@Getter
public enum MyErrorMsg {
    NO_STOCK_NAME_ERROR("존재하지 않는 주식명: [%s]"),
    DISCONNECT_MAYBE("연결 끊김 의심");

    MyErrorMsg(String msgFormat){
        this.msgFormat = msgFormat;
    }
    private String msgFormat;
}