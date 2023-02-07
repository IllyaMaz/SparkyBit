package com.example.sparkybitTesttask.inputData;

import lombok.Data;

import java.util.List;

@Data
public class InputData {
    private List<String> row;

    @Override
    public String toString() {
        return "InputData{" +
                "row=" + row +
                '}';
    }
}
