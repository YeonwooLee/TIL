package com.example.stock_helper;

import com.example.stock_helper.stock.Stock;
import com.example.stock_helper.telegram.EchoBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.List;

@Configuration
public class CommonConfig {
    @Bean//텔레그램 api 객체는 하나만 만들어서 사용
    TelegramBotsApi makeBots() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new EchoBot());
        return telegramBotsApi;
    }


}
