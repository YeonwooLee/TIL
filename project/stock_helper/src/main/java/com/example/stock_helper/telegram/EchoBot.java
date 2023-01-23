package com.example.stock_helper.telegram;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class EchoBot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return StockEye2Bot.BOT_USER_NAME;
    }

    @Override
    public String getBotToken() {
        return StockEye2Bot.BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            var mensagem = responder(update);
            System.out.println("기모디 = " + mensagem);
            try {
                execute(mensagem);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private SendMessage responder(Update update) {
        var userText = update.getMessage().getText(); //들어온 채팅
        var chatId = update.getMessage().getChatId().toString();

        var resposta =  userText;

        if(userText.startsWith("이거로시작하면")){
            resposta = "이 메세지를 출력한다";
        }


        return SendMessage.builder()
                .text(resposta)
                .chatId(chatId)
                .build();
    }




}