package com.whale.web.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

@RestController
public class AsyncComtroller{

    private Logger logger = LoggerFactory.getLogger(getClass());

    /*@RequestMapping("/order")
    public String order() throws InterruptedException {
        //同步处理的方式
        logger.info("主线程开始");
        Thread.sleep(1000);
        logger.info("主线程返回");
        return "success";
    }*/

    @RequestMapping("/order")
    public Callable<String>  order() throws InterruptedException {
        logger.info("主线程开始");

        Callable<String> result = new Callable<String>() {
            @Override
            public String call() throws Exception {
                logger.info("副线程开始");
                Thread.sleep(1000);
                logger.info("副线程返回");
                return "success";
            }
        };
        Thread.sleep(1000);
        logger.info("主线程返回");
        return result;
    }
}
