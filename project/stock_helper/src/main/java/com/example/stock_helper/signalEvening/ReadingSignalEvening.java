package com.example.stock_helper.signalEvening;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadingSignalEvening {

    public static String parsing(String filePath,List<String> stockList) throws IOException {
        //make path that platform-independent
        // Path filePath = Paths.get("C:","PdfBox_Examples","my_doc.pdf");

        //Loading an existing document
        File file = new File(filePath);
        PDDocument document = PDDocument.load(file);

        //Instantiate PDFTextStripper class
        PDFTextStripper pdfStripper = new PDFTextStripper();

        //Retrieving text from PDF document
        String text = pdfStripper.getText(document);
        // System.out.println(text);

        //Closing the document
        document.close();


        //파싱용 재료 생성
        //1. 기존 문자열
        String[] before = text.split("\n");
        //2. 오늘의 주식 리스트

        //파싱
        String after = extractCore(before, stockList);
        System.out.println("시작");
        // System.out.println(after);

        return after;
    }

    static String extractCore(String[] before, List<String> stockList){
        //결과
        String res = "";
        //key: 테마명
        //value: List [종목문단1, 종목문단2]
        Map<String,List<String>> map = new HashMap<>();

        //문단 저장용 전역 String
        String temp = "";
        //테마 저장용
        String curTheme = "";

        //모드
        boolean isAppendMode = false; //문단 추가 모드

        for(int i=0;i<before.length;i++){
            String cur = before[i];
            if(isAppendMode){
                //문단 추가 모드

                temp+=cur+"\n</br>";
                //빈 줄 만나면 문단 추가 모드 종료
                if(cur.length()==2) {
                    isAppendMode=false;
                    map.get(curTheme).add(temp);
                }
            }else{
                //문단 추가 모드 X (==탐색모드)

                if(isTheme(cur)){
                    //cur이 테마명이면
                    //map에 없는 테마면 넣는다
                    map.putIfAbsent(cur,new ArrayList<>());
                    curTheme = cur;//테마설정
                }else if(isHotStock(cur,stockList)){
                    //stockList의 원소 중에 cur의 내용에 포함되는 원소가 있다면
                    temp=cur+"\n</br>";//temp를 현재행으로 초기화
                    isAppendMode=true;//appendMode 실행
                    

                }
            }
        }

        //map의 내용을 String화
        for(String key: map.keySet()){
            List<String> list = map.get(key);

            // res+=key+"\n";
            res+="</br>"+key+"</br>";
            for(String s:list){
                res+=s;
            }
        }
        return res;
    }

    static boolean isTheme(String s){
        return s.contains("<") && s.contains(">");
    }

    static boolean isHotStock(String s, List<String> stockList){
        for(String stock:stockList){
            if(s.contains(stock)) return true;
        }
        return false;
    }
}