package com.example.stock_helper.python;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Slf4j
public class ReadPython {
    public static <T> T readPythonFile(Class<T> responseType,String pythonProgramName, String[] args) {
        //Group = com.mysite
        //Artifact = sbb
        //내부 패키지 = python
        String workingDir = System.getProperty("user.dir");
        System.out.println("workingDir = " + workingDir);
        String path = workingDir + "\\src\\main\\java\\com\\example\\stock_helper\\python";
        String command = String.format("python %s\\%s.py", path, pythonProgramName);
        for (String arg : args) {
            command += " " + arg;
        }
        log.debug(String.format("파이썬 파일 실행, command = %s", command));
        String result = "";
        T mo = null;
        try {
            Process p = Runtime.getRuntime().exec(command);
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String output = in.readLine(); //파이썬 dict 형식으로 출력(print)

            mo = new Gson().fromJson(output, responseType);//파이썬 dict형식 출력을 MyObject형식으로 변경
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(mo==null) throw new RuntimeException("python 반환값 null");
        return mo;
    }
}