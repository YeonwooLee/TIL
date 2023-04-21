package com.example.solved_group;

import com.example.solved_group.util.crawler.notice.NoticeCrawler;

import java.util.Date;

public class Jongno extends NoticeCrawler {

    public Jongno(String url, String xpathOfElementGroup, boolean isTest, int noIdx, int titleIdx, int writeDateIdx) {
        super(url, xpathOfElementGroup, isTest, noIdx, titleIdx, writeDateIdx);
    }

    @Override
    public Date toDate(String date) {
        return null;
    }
}
