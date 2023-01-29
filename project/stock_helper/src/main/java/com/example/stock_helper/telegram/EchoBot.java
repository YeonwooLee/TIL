package com.example.stock_helper.telegram;
import com.example.stock_helper.stock.Stock;
import com.example.stock_helper.python.StockFinder;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        String order = "";
        var msg =  userText;//답장 기본적으로 유저 인풋 그대로

        if(userText.startsWith("이거로시작하면") && chatId.equals(Chat.STOCK_SEARCH.getChatId())){
            msg = "이 메세지를 출력한다";
        }else if(userText.startsWith(".") && chatId.equals(Chat.STOCK_SEARCH.getChatId())){//.로 시작하는 메세지 && STOCK_SEARCH방
            order = userText.replace(".", "");
            msg = getStockMainInfo(order);
        }else if(userText.startsWith(Order.TODAY_HOT_STOCK.getOrderCode())){
            order = userText.replace(Order.TODAY_HOT_STOCK.getOrderCode(),"");//명령에서 명령코드("!!") 제거 -> !!float 상승률,int 억
            msg = listToMsg(getTodayHotStock(order));

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
    //오늘의 핫한주식
    private List<Stock> getTodayHotStock(String order){//order = "상승률,몇억이상"
        String[] orders = order.split(",");
        int riseRate = Integer.parseInt(orders[0]);//상승률
        long hundredMillion = Long.parseLong(orders[1])*100000000;//몇 억 이상인지 찾는용
        List<Stock> stocks = StockFinder.getStocks();//
        List<Stock> result = new ArrayList<>();

        for(Stock st : stocks){
            if(st.getStockTransactionAmount()>=hundredMillion && st.getStockRise()>=riseRate){
                result.add(st);
            }
        }
        Collections.sort(result, new Comparator<Stock>(){
            @Override
            public int compare(Stock s1, Stock s2){
                return s1.getRiseRank()-s2.getRiseRank();
            }
        });
        return result;
    }

    //주식 메인 정보
    private String getStockMainInfo(String stockName){
        try {
            return StockFinder.getStockDetail(stockName).toString();
        }catch(RuntimeException e){
            return "잘못된 주식명: ["+stockName+"]";
        }
    }
    private <T> String listToMsg(List<T> list) {
        String result = list.stream()
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
        return result;
    }


}