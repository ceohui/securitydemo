package com.whale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.JsonPath;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest implements Serializable {

    /**
     * 注入web环境的ApplicationContext容器；
     */
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        /**
         * MockMvcBuilder是用来构造MockMvc的构造器，其主要有两个实现：
         * StandaloneMockMvcBuilder和DefaultMockMvcBuilder，分别对应两种测试方式，
         * 即独立安装和集成Web环境测试（此种方式并不会集成真正的web环境，而是通过相应的Mock API进行模拟测试，无须启动服务器）。
         */
        //创建一个MockMvc进行测试；
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void whenQuerySuccess() throws Exception {
        //andExpect：添加ResultMatcher验证规则，验证控制器执行完成后结果是否正确；
        mockMvc.perform(
                 MockMvcRequestBuilders.get("/user1").
                         param("username","hello")      //带参数
                         .param("password","123456")
                         .contentType(MediaType.APPLICATION_JSON_UTF8)) //用contentType表示具体请求中的媒体类型信息，MediaType.APPLICATION_JSON表示互联网媒体类型的json数据格式
                .andExpect(MockMvcResultMatchers.status().isOk())     //期望的状态码 200
                .andExpect(jsonPath("$.length()").value(3));////验证length是否为3，jsonPath的使用
    }

    @Test
    public void whenGetInfoSuccess() throws Exception {
        //andExpect：添加ResultMatcher验证规则，验证控制器执行完成后结果是否正确；
       String  result = mockMvc.perform(
                MockMvcRequestBuilders.get("/user4/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)) //用contentType表示具体请求中的媒体类型信息，MediaType.APPLICATION_JSON表示互联网媒体类型的json数据格式
                .andExpect(MockMvcResultMatchers.status().isOk())     //期望的状态码 200
                .andExpect(jsonPath("$.username").value("tom"))
                .andReturn().getResponse().getContentAsString();       //将返回结果转换为字符串 并 定义 一个变量result接收
        System.out.println(result);
    }

    @Test
    public void whenCreateSuccess() throws Exception {
        Date date = new Date();
        System.out.println(date.getTime());
        //{"username":"tom"}  ，在java 双引号需要转义
        String content ="{\"username\":\"tom\",\"birthday\":\""+date.getTime() +"\"}";
        System.out.println(content);
        String result = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    @Test
    public void whenUpdateSuccess() throws Exception {
        //Date date = new Date();

        //jdk 8 时间操作  当前时间加一年 默认时区  转换为毫秒
        Date date = new Date(LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        System.out.println(date.getTime());
        //{"username":"tom"}  ，在java 双引号需要转义
        String content ="{\"id\":\"1\",\"username\":\"tom\",\"birthday\":\""+date.getTime() +"\"}";
        System.out.println(content);
        String result = mockMvc.perform(MockMvcRequestBuilders.put("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }
}
