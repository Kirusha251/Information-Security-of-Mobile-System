package com.example.apache.shell_linux;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Apache on 26.02.2017.
 */

public class ShellExecuter {

    public ShellExecuter(){

    }

    public String Executer(String[] command){
        StringBuilder cmdReturn = new StringBuilder();

        ProcessBuilder processBuilder;
        Process process;
        try{
            processBuilder = new ProcessBuilder(command);
            process = processBuilder.start();

            InputStream inputStream = process.getInputStream();
            int c;
            while ((c = inputStream.read()) != -1) {
                cmdReturn.append((char) c);
            }

            } catch (IOException e1) {
            e1.printStackTrace();
        }

        String response = cmdReturn.toString();
        return response;
    }
}
