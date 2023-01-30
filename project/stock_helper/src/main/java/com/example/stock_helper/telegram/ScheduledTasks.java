package com.example.stock_helper.telegram;


import com.example.stock_helper.python.StockFinder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.stock_helper.telegram.strings.Chat.STOCK_SEARCH;

@Component
@RequiredArgsConstructor
@Slf4j
public class ScheduledTasks {
    private final StockFinder stockFinder;
    private final TelegramBotsApi telegramBotsApi;
    private final EchoBot echoBot;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "*/10 55 1,19 * * *") //1시 17시의 55분에 10초마다 발생
    public void reportCurrentTime() {
        // stockFinder.setTempStock(stockFinder.getStocks());
        log.info("주식리스트 갱신 {}", dateFormat.format(new Date()));
        //TODO db에 주식 리스트 갱신 현재시간+주식코드 = pk, 주식명, 현재가, 등수 등
    }
    @Scheduled(cron = "*/3 * * * * *") //1시 17시의 55분에 10초마다 발생
    public void test() throws TelegramApiException {
        // stockFinder.setTempStock(stockFinder.getStocks());
        log.info("temp");
        echoBot.execute(SendMessage.builder()
                .text("sdfsd")
                .chatId(STOCK_SEARCH.getChatId())
                .build());

    }
}