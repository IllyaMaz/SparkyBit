package com.example.sparkybitTesttask.service;

import com.example.sparkybitTesttask.inputData.InputData;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j
public class ReqService {

    public InputData parseInputData(InputData data) {

        int point = 0;
        InputData inputFile = new InputData();
        List<String> row = data.getRow();
        List<String> result = new ArrayList<>();

        for (String line : row) {
            boolean fibonacciNumb = isFibonacciNumb(point);
            if (fibonacciNumb){
                String reverse = reverse(line);
                result.add(reverse);
            }
            point++;
        }

        writeToFile(result);
        inputFile.setRow(result);
        return inputFile;

    }

    private static boolean isFibonacciNumb(int numb) {
        double pow = 5 * Math.pow(numb, 2);
        return Math.sqrt(pow-4)%1==0 || Math.sqrt(pow+4)%1==0;
    }

    private static String reverse(String line) {
        char[] chars = line.toCharArray();
        String result = "";
        for (int i = line.length()-1; i > -1;i--){
            result += chars[i];
        }
        return result;
    }

    private static void writeToFile(List<String> list) {
        try (FileWriter writer = new FileWriter("response.txt", true)){
            for (String line : list) {
                writer.write(line + "\n");
                log.info("write down: " + line);
            }
        } catch (IOException e) {
            log.info(e);
        }
    }
}
