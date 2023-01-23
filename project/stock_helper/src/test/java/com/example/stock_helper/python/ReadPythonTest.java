package com.example.stock_helper.python;

import com.example.stock_helper.python.cybos5.GetStockDetailDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReadPythonTest {
    @Test
    public void 파이썬_받아오기_테스트(){
        //given
        String findStockName = "temp";

        //when
        GetStockDetailDTO getStockDetailDTO = ReadPython.readPythonFile(GetStockDetailDTO.class, "cybos5\\getStockDetail", new String[]{findStockName});
        // then
        System.out.println("getStockDetailDTO = " + getStockDetailDTO);
        assertEquals(findStockName,getStockDetailDTO.getStockName());
    }

}