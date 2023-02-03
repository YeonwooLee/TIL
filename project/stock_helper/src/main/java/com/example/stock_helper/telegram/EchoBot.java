package com.example.stock_helper.telegram;
import com.example.stock_helper.stock.Stock;
import com.example.stock_helper.python.StockFinder;
import com.example.stock_helper.telegram.strings.Chat;
import com.example.stock_helper.telegram.strings.Message;
import com.example.stock_helper.telegram.strings.MyErrorMsg;
import com.example.stock_helper.telegram.strings.Order;
import com.example.stock_helper.util.MyConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.util.List;


import static com.example.stock_helper.telegram.strings.Message.TODAY_HOT_STOCK_MSG_HEADER;

@RequiredArgsConstructor
@Component
public class EchoBot extends TelegramLongPollingBot {
    private final StockFinder stockFinder;
    private final MyConverter myConverter;

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
            // System.out.println("받은 메시지 정보 = " + mensagem);
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
        }//주식상세정보
        else if(userText.startsWith(".") && chatId.equals(Chat.STOCK_SEARCH.getChatId())){//.로 시작하는 메세지 && STOCK_SEARCH방
            order = userText.replace(".", "");
            msg = stockDetailToString(order);
        }// !!float 상승률,int 억
        else if(userText.startsWith(Order.TODAY_HOT_STOCK.getOrderCode())){
            order = userText.replace(Order.TODAY_HOT_STOCK.getOrderCode(),"");//명령에서 명령코드("!") 제거 -> !float 상승률,int 억
            msg = TODAY_HOT_STOCK_MSG_HEADER.getMsgFormat() + myConverter.listToMsg(getTodayHotStock(order));


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
    private List<String> getTodayHotStock(String order){//order = "상승률,몇억이상"
        String[] orders = order.split(",");
        int riseRate = Integer.parseInt(orders[0]);//상승률
        long hundredMillion = Long.parseLong(orders[1])*100000000;//몇 억 이상인지 찾는용
        List<String> todayHotStocks = stockFinder.makeTodayHotStock(riseRate,hundredMillion);
        return todayHotStocks;
    }

    //주식 메인 정보
    private String stockDetailToString(String stockName){
        String result = "임시";
        try {
            Stock stock = stockFinder.getStockDetail(stockName);

            float stockRise = stock.getStockRise();
            int riseRank = stock.getRiseRank();
            long stockTransactionAmount = stock.getStockTransactionAmount() / 100000000;
            int amountRank = stock.getAmountRank();
            float per = stock.getPer();
            long marketCapitalization = stock.getMarketCapitalization()/100000000;

            result = String.format(Message.ONE_STOCK_INF.getMsgFormat(),
                    stockName,
                    stockRise,
                    riseRank,
                    stockTransactionAmount,
                    amountRank,
                    marketCapitalization,
                    per);
            return result;
        }catch(RuntimeException e){
            if (e.getMessage().equals(String.format(MyErrorMsg.NO_STOCK_NAME_ERROR.getMsgFormat(),stockName))){
                return String.format(Message.NOT_EXIST_STOCK_NAME.getMsgFormat(),stockName);
            }
            return MyErrorMsg.DISCONNECT_MAYBE.getMsgFormat();
        }
    }



}