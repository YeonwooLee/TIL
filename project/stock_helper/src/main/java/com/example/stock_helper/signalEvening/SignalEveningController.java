package com.example.stock_helper.signalEvening;

import com.example.stock_helper.stock.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class SignalEveningController {
    private final StockService stockService;

    @Value("${file.dir}")
    private String fileDir;

    @GetMapping("/signaleye")
    public String newItem() {
        log.info("signalEvening 메인 폼 접속");
        return "item-form";
    }
    @PostMapping("/signaleye")
    @ResponseBody
    public String saveItem(@ModelAttribute ItemForm form, RedirectAttributes
            redirectAttributes) throws IOException {
        log.info("signalEvening 폼 전송");
        MultipartFile file = form.getAttachFile();
        int stockRise = form.getStockRise();
        int stockTransactionAmount = form.getStockTransactionAmount();
        log.info("stockRise={}, 거래대금={}",stockRise,stockTransactionAmount);
        List<String> stockList = stockService.makeTodayHotStockOnlyName(stockRise,stockTransactionAmount*100000000);
        log.info("stockList={}",stockList);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd_HH_mm_ss");
        String formattedDateTime = now.format(formatter);
        String fullPath = fileDir + formattedDateTime;

        file.transferTo(new File(fullPath));

        String result = ReadingSignalEvening.parsing(fullPath,stockList);

        return result;
    }
}
