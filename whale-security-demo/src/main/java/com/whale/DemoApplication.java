package com.whale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author ljy
 * @version V1.0
 * @Package com.whale
 * @Description:
 * @date 2019/1/12 23:39
 */


@EnableSwagger2
@RestController
@SpringBootApplication  //这是一个SpringBoot项目
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);//SpringBoot标准启动方式
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello spring secruity";
    }
}
