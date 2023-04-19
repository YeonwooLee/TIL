package com.example.solved_group.controller;


import com.example.solved_group.Jongno;
import com.example.solved_group.Joongrang;
import com.example.solved_group.util.crawler.notice.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TempController {
    @Autowired
    Jongno jn;
    @Autowired
    Joongrang jr;

    @GetMapping("jongno")
    @ResponseBody
    public String temp(){
        List<Notice> notices = jn.crawling();
        StringBuilder sb = new StringBuilder();
        for(Notice nt:notices){
            sb.append(nt.toString()+"<br/>");
        }

        return sb.toString();
    }
    @GetMapping("joongrang")
    @ResponseBody
    public String temp2(){

        List<Notice> notices = jr.crawling();
        StringBuilder sb = new StringBuilder();
        for(Notice nt:notices){
            sb.append(nt.toString()+"<br/>");
        }

        return sb.toString();
    }
}
