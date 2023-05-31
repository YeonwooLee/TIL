package com.example.stock_helper.stock;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("stock")
@Log4j
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;

    

    /*
        TODO pdf 관련 작업 (signalEvening 패키지) 외부 프로젝트로 뺴기
        TODO 여기서 stockService의 stockService.makeTodayHotStockOnlyName() 사용하여 리스트만 전달  
     */
    
}
