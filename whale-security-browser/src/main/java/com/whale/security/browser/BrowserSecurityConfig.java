package com.whale.security.browser;

import com.sun.org.apache.xpath.internal.operations.And;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.Serializable;

/**
 * @author ljy
 * @version V1.0
 * @Package com.whale.security.browser
 * @Description: TODO
 * @date 2019/4/6 0006 19:22
 */

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();//也可以自定义 只要实现PasswordEncoder接口
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.formLogin()   //指定身份认证的方式为表单登录
        //http.httpBasic()
        http.formLogin()
//                .loginPage("/login") //指定登录页面的url
//                .loginProcessingUrl("/authentication/form.do")
                .and()
                .authorizeRequests() //对请求授权
                .antMatchers("/signIn.html").permitAll() //加一个匹配器 对匹配的路径不进行身份认证
                .anyRequest()        //任何请求
                .authenticated()    //安全认证
                .and()
                .cors().disable();  // .csrf().disable();// 禁用跨站攻击
                // 默认都会产生一个hiden标签 里面有安全相关的验证 防止请求伪造 这边我们暂时不需要 可禁用掉
                //任何请求都必须经过表单验证才能进行访问

    }
}