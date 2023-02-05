package com.example.stock_helper.python;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class CybosConnection {
    private final ReadPython readPython;

    public boolean runCybos() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String workingDir = System.getProperty("user.dir");

        CybosUser cybosUser = mapper.readValue(new File(workingDir+"\\src\\main\\java\\com\\example\\stock_helper\\python\\userInfo.json"), CybosUser.class);

        String id = cybosUser.getId();
        String pwd = cybosUser.getPwd();
        String pwdcert = cybosUser.getPwdcert();


        String[] command = new String[] {"C:\\DAISHIN\\STARTER\\ncStarter.exe","/prj:cp","/id:"+id,"/pwd:"+pwd,"/pwdcert:"+pwdcert, "/autostart"};

        Process process = new ProcessBuilder(command).start();
        InputStream is = process.getInputStream();//Get an inputstream from the process which is being executed
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        String s = readPython.readPythonFile(String.class, "util\\gui\\python\\guiController", new String[]{"20"});
        System.out.println(s);
        return true;
    }
}
