package com.example.stock_helper.signalEvening;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ItemForm {
    private int stockRise;//상승률
    private int stockTransactionAmount;//거래대금
    private MultipartFile attachFile;//이브닝리포트
    private boolean writeDate;//날짜표기할껀지
}
