package com.example.stock_helper;

import com.example.stock_helper.python.CybosConnection;
import com.example.stock_helper.stock.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class MyApplicationRunner implements ApplicationRunner {
    private final CybosConnection cybosConnection;
    private final StockService stockService;

    @Override
    public void run(ApplicationArguments args) throws IOException {
        // return;
        cybosConnection.runCybos();
        stockService.reportCurrentTime();

    }
}
