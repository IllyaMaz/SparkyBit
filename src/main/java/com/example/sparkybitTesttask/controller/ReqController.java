package com.example.sparkybitTesttask.controller;

import com.example.sparkybitTesttask.inputData.InputData;
import com.example.sparkybitTesttask.service.ReqService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Log4j
public class ReqController {
    private final ReqService service;

    @PostMapping("fibonacci")
    public InputData postToFibonacci(@RequestBody InputData data) {
        log.info("request to fibonacci data: " + data);
        return service.parseInputData(data);
    }
}
