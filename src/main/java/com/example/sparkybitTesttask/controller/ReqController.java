package com.example.sparkybitTesttask.controller;



import com.example.sparkybitTesttask.inputData.InputData;
import com.example.sparkybitTesttask.service.ReqService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class ReqController {
    private final ReqService service;
    private Logger logger = Logger.getLogger(ReqController.class);

    /**
     input example :
        {
            "row":[
            "8,13",
            "13,21",
            "1,2,3,5,8,14"
            ]
        }
     */
    @PostMapping("fibonacci")
    public InputData postToFibonacci(@RequestBody InputData data){
        logger.info("request to fibonacci data: " + data.toString());
        return service.parseInputData(data);
    }
}
