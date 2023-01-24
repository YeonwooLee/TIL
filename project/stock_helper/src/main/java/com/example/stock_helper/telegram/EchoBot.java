package com.example.stock_helper.telegram;
import com.example.stock_helper.python.ReadPython;
import com.example.stock_helper.python.StockFinder;
import com.example.stock_helper.python.cybos5.GetStockDetailDTO;
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
            System.out.println("받은 메시지 정보 = " + mensagem);
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

        var msg =  userText;//답장 기본적으로 유저 인풋 그대로

        if(userText.startsWith("이거로시작하면") && chatId.equals(Chat.STOCK_SEARCH.getChatId())){
            msg = "이 메세지를 출력한다";
        }else if(userText.startsWith(".") && chatId.equals(Chat.STOCK_SEARCH.getChatId())){//!!로 시작하는 메세지 && STOCK_SEARCH방
            String order = userText.replace(".", "");
            msg = getStockMainInfo(order);
        }

        if(chatId.equals(Chat.STOCK_SEARCH.getChatId())){//채팅아이디가 이거면
            //이짓을 한다
            // msg="★ROOM [STOCK_SEARCH]★\n"+msg;
            msg = msg;
        }



        return SendMessage.builder()
                .text(msg)
                .chatId(chatId)
                .build();
    }

    //주식 메인 정보
    private String getStockMainInfo(String stockName){
        try {
            return StockFinder.getStockDetail(stockName).toString();
        }catch(RuntimeException e){
            return "잘못된 주식명: ["+stockName+"]";
        }
    }


}