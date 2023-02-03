package com.example.sparkybit_testtask.controller;


import com.example.sparkybit_testtask.InputData;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;

@Service
public class ReqService {

    private Logger logger = Logger.getLogger(ReqService.class);

    public InputData fibonacci(InputData data){
        InputData inputFile = new InputData();
        List<String> row = data.getRow();
        List<String> result = new ArrayList<>();
        for (int i = 0 ; i < row.size(); i++){
            boolean fibonacciLine = isFibonacciLine(row.get(i));
            if (fibonacciLine){
                String reverse = reverse(row.get(i));
                logger.info("write down: " + reverse);
                saveToMemory(reverse);
                result.add(reverse);
            }
        }
        inputFile.setRow(result);
        return inputFile;
    }

    private static boolean isFibonacciLine(String line){

        String[] split = line.split(",");
        int length = split.length;

        if (length == 1){
            int input = Integer.parseInt(split[0]);
            return isFibonacciNumb(input);
        }

        if (length == 2) {
            int input1 = Integer.parseInt(split[0]);
            int input2 = Integer.parseInt(split[1]);
            boolean fibonacciNumb1 = isFibonacciNumb(input1);
            boolean fibonacciNumb2 = isFibonacciNumb(input2);

            int input3 = input2 - input1;
            boolean fibonacciNumb3 = isFibonacciNumb(input3);

            if ((input3 <= input1) && (input2 > input1) && fibonacciNumb1 == fibonacciNumb2 && fibonacciNumb2 == fibonacciNumb3){
                return true;
            } else {
                return false;
            }
        }

        if (length > 2){
            boolean result = true;
            for (int i = 0; i < length -1;i++){
                int input1 = Integer.parseInt(split[i]);
                int input2 = Integer.parseInt(split[i+1]);
                boolean fibonacciNumb1 = isFibonacciNumb(input1);
                boolean fibonacciNumb2 = isFibonacciNumb(input2);

                int input3 = input2 - input1;
                boolean fibonacciNumb3 = isFibonacciNumb(input3);

                if ((input3 <= input1) && (input2 > input1) && fibonacciNumb1 == fibonacciNumb2 && fibonacciNumb2 == fibonacciNumb3){
                    result = result == true;
                } else {
                    result = false;
                }

            }
            return result;
        }
        return false;
    }

    private static boolean isFibonacciNumb(int numb){
        if (Math.sqrt(5*(pow(numb,2))-4)%1==0 || Math.sqrt(5*(pow(numb,2))+4)%1==0){
            return true;
        }else {
            return false;
        }
    }

    private static String reverse(String line){
        int stringLength = line.length();
        String result = "";
        for (int i = 0; i < stringLength; i++) {
            result = line.charAt(i) + result;
        }
        return result;

    }

    private static void saveToMemory(String line){
        try {
            FileWriter writer = new FileWriter("response.txt",true);
            writer.write(line + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
