package com.example.stock_helper.python;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.UnsupportedEncodingException;

class ReadPythonTest {
    @Test
    public void 파이썬_임시_테스트(){
        //given

        //when
        Integer getStockDetailDTO = ReadPython.readPythonFile(Integer.class, "cybos5\\temp", new String[]{"111123123"});
        // then
        System.out.println("getStockDetailDTO = " + getStockDetailDTO);

    }

    @Test
    public void 파이썬_받아오기_테스트() throws UnsupportedEncodingException {
        //given
        String findStockName = "삼성전자";

        //when
        GetStockDetailDTO getStockDetailDTO = ReadPython.readPythonFile(GetStockDetailDTO.class, "cybos5\\getStockDetail", new String[]{findStockName});
        // then
        System.out.println("getStockDetailDTO = " + getStockDetailDTO);
        String temp = getStockDetailDTO.getStockName();
        String result = temp;
        assertEquals(findStockName,result);
    }

    @Test
    public void temp(){
        //given
        String temp = "asdf";
        //when
        String a = temp.replace("a", "!!");
        // then
        System.out.println("a = " + a);
        System.out.println("temp = " + temp);
    }
}