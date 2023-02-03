package com.example.stock_helper.telegram.strings;


import lombok.Getter;

@Getter
public enum Message {
    ALERT_SOMETHING("입니다\n"),
    TODAY_HOT_STOCK_MSG_HEADER_AUTO("<오늘의 HOT 자동알림 %d%%, %d억>\n"),
    TODAY_HOT_STOCK_MSG_HEADER("<오늘의 핫한 주식 목록>\n"),
    NOT_EXIST_STOCK_NAME("존재하지 않는 주식명: [%s]"),
    ONE_STOCK_INF("◎ %s [%.2f%%(%d위) / 거래대금 %d억(%d위)]----- 시총: %d억 PER: %.2f"),
    HOT_STOCK_INF("◎ %s [%.2f%%(%d위) / 거래대금 %d억(%d위)]----- 시총: %d억 PER: %.2f \n");

    Message(String msgFormat){
        this.msgFormat = msgFormat;
    }
    private String msgFormat;
}