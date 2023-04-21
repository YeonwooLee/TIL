package com.example.solved_group;

import com.example.solved_group.util.crawler.notice.NoticeCrawler;

import java.util.Date;

public class Joongrang extends NoticeCrawler {
    public Joongrang(String url, String xpathOfElementGroup, boolean isTest, int noIdx, int titleIdx, int writeDateIdx) {
        super(url, xpathOfElementGroup, isTest, noIdx, titleIdx, writeDateIdx);
    }

    @Override
    public Date toDate(String date) {
        return null;
    }
}
