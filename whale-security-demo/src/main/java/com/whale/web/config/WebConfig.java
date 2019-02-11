package com.whale.web.config;

import com.whale.web.filter.TimeFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.FilterRegistration;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ljy
 * @version V1.0
 * @Package com.whale.web.config
 * @Description: TODO
 * @date 2019/2/11 16:53
 */
@Configuration
public class WebConfig implements Serializable {

    @Bean
    public FilterRegistrationBean timeFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();

        TimeFilter timeFilter = new TimeFilter();
        registrationBean.setFilter(timeFilter);

        //设置过滤路径
        List<String> urls = new ArrayList<>();
        urls.add("/*");
        registrationBean.setUrlPatterns(urls);

        return registrationBean;

    }
}
