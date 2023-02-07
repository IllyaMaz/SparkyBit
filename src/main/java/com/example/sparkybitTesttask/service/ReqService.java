package com.example.sparkybitTesttask.service;

import com.example.sparkybitTesttask.inputData.InputData;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReqService {

    private static Logger logger = Logger.getLogger(ReqService.class);

    public InputData ParseInputData(InputData data){
        InputData inputFile = new InputData();
        List<String> row = data.getRow();
        List<String> result = new ArrayList<>();

        for (String string :row) {
            boolean fibonacciLine = isFibonacciLine(string);
            if (fibonacciLine) {
                String reverse = reverse(string);
                logger.info("write down: " + reverse);
                result.add(reverse);
            }
        }
        writeToFile(result);
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

            return  input3 <= input1 &&
                    input2 > input1 &&
                    fibonacciNumb1 == fibonacciNumb2 &&
                    fibonacciNumb2 == fibonacciNumb3 &&
                    fibonacciNumb1 == true;
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

                result = input3 <= input1 &&
                        input2 > input1 &&
                        fibonacciNumb1 == fibonacciNumb2 &&
                        fibonacciNumb2 == fibonacciNumb3 &&
                        fibonacciNumb1 == true;

                if (result == false){
                    return result;
                }
            }
            return result;
        }
        return false;
    }

    private static boolean isFibonacciNumb(int numb){
        return Math.sqrt(5*(Math.pow(numb,2))-4)%1==0 || Math.sqrt(5*(Math.pow(numb,2))+4)%1==0;
    }

    private static String reverse(String line){
        int stringLength = line.length();
        String result = "";
        for (int i = 0; i < stringLength; i++) {
            result = line.charAt(i) + result;
        }
        return result;
    }

    private static void writeToFile(List<String> list){
        try (FileWriter writer = new FileWriter("response.txt", true)){
            for (String line : list) {
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            logger.info(e);
        }
    }
}
