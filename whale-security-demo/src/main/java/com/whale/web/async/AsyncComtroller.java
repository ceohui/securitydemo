package com.whale.web.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsyncComtroller{

    private Logger logger = LoggerFactory.getLogger(getClass());


    @RequestMapping("/order")
    public String order() throws InterruptedException {
        //同步处理的方式
        logger.info("主线程开始");
        Thread.sleep(1000);
        logger.info("主线程返回");
        return "success";
    }
}
