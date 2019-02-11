package com.whale.web.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ljy
 * @version V1.0
 * @Package com.whale.web.filter
 * @Description: 时间拦截器
 * @date 2019/2/11 13:18
 */
//@Component
public class TimeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("TimeFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("TimeFilter start");
        long start = new Date().getTime();
        filterChain.doFilter(servletRequest,servletResponse);
        long end = new Date().getTime();
        System.out.println("TimeFilter耗时(毫秒) : " +(end-start));
        System.out.println("TimeFilter finish");
    }

    @Override
    public void destroy() {
        System.out.println("TimeFilter destroy");
    }
}
