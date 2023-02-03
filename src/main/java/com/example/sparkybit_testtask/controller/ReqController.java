package com.example.sparkybit_testtask.controller;



import com.example.sparkybit_testtask.InputData;
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
    public InputData first(@RequestBody InputData data){
        logger.info("request to fibonacci");
        return service.fibonacci(data);
    }
}
