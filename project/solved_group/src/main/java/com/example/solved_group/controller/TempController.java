package com.example.solved_group.controller;


import com.example.solved_group.util.crawler.notice.Notice;
import com.example.solved_group.util.crawler.notice.NoticeCrawler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TempController {
    @GetMapping("test")
    @ResponseBody
    public String temp(){
        Joonrang sv = new Joonrang();
        List<Notice> crawling = sv.crawling();
        StringBuilder sb = new StringBuilder();
        for(Notice n : crawling){
            sb.append(n.toString());
        }
        return sb.toString();
    }
}
