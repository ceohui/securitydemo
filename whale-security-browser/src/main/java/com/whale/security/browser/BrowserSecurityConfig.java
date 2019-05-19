package com.whale.security.browser;

import com.whale.security.browser.session.MyExpiredSessionStrategy;
import com.whale.security.core.authentication.AbstractChannelSecurityConfig;
import com.whale.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.whale.security.core.properties.SecurityConstants;
import com.whale.security.core.properties.SecurityProperties;
import com.whale.security.core.validate.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @author ljy
 * @version V1.0
 * @Package com.whale.security.browser
 * @Description: TODO
 * @date 2019/4/6 0006 19:22
 */

@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {

    @Autowired
    private AuthenticationSuccessHandler whaleAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler whaleAuthenctiationFailureHandler;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private DataSource dataSource;

    @Qualifier("myUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();//也可以自定义 只要实现PasswordEncoder接口
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        //tokenRepository.setCreateTableOnStartup(true);
        //启动时建立这张表persistent_logins，也可以吧创建表语句拿去手动执行
        //只能执行一次，第二次需要关掉，否则报错MySQLSyntaxErrorException: Table 'persistent_logins' already exists
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {





        applyPasswordAuthenticationConfig(http);

        http.apply(validateCodeSecurityConfig)
                .and()
             .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSecodes())
                .userDetailsService(userDetailsService)
                .and()
            .sessionManagement()
                .invalidSessionUrl("/session/invalid")
                .maximumSessions(1)//为1 后面登录的session会把前面的登录的session失效掉
                .expiredSessionStrategy(new MyExpiredSessionStrategy())//并发登录导致超时的处理策略
                .and()
                .and()
                .authorizeRequests()
                .antMatchers(
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        securityProperties.getBrowser().getLoginPage(),
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
                        "/session/invalid")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .cors().disable().csrf().disable();// 禁用跨站攻击








        //http.formLogin()   //指定身份认证的方式为表单登录
        //http.httpBasic()

     /*   ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(whaleAuthenctiationFailureHandler);//设置错误过滤器

        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();


        SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
        smsCodeFilter.setAuthenticationFailureHandler(whaleAuthenctiationFailureHandler);//设置错误过滤器

        smsCodeFilter.setSecurityProperties(securityProperties);
        smsCodeFilter.afterPropertiesSet();


        http.addFilterBefore(smsCodeFilter,UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(validateCodeFilter,UsernamePasswordAuthenticationFilter.class)
                .formLogin()
//                .loginPage("/signIn.html") //指定登录页面的url
//                .loginPage("/anthentication/require") //指定登录页面的url
                .loginPage(securityProperties.getBrowser().getLoginPage()) //指定登录页面的url
                .loginProcessingUrl("/authentication/form")
                .successHandler(whaleAuthenticationSuccessHandler)
                .failureHandler(whaleAuthenctiationFailureHandler)
                .permitAll()
                .and()

            .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSecodes())
                .userDetailsService(userDetailsService)


                .and()
                .authorizeRequests() //对请求授权
//                .antMatchers("/signIn.html","/code/image").permitAll() //加一个匹配器 对匹配的路径不进行身份认证
                .antMatchers(securityProperties.getBrowser().getLoginPage(),"/code/*").permitAll() //加一个匹配器 对匹配的路径不进行身份认证
                .anyRequest()        //任何请求
                .authenticated()    //安全认证
                .and()
                .cors().disable().csrf().disable()// 禁用跨站攻击

        .apply(smsCodeAuthenticationSecurityConfig);*/


                // 默认都会产生一个hiden标签 里面有安全相关的验证 防止请求伪造 这边我们暂时不需要 可禁用掉
                //任何请求都必须经过表单验证才能进行访问

       /* http.csrf().disable().cors().disable().headers().disable()
                .authorizeRequests()
                .antMatchers("/signIn.html").permitAll() // 配置不需要身份认证的请求地址
                .anyRequest().authenticated() // 其他所有访问路径需要身份认证
                .and()
                .formLogin()
                .loginPage("/signIn.html") // 指定登录请求地址
                .loginProcessingUrl("/authentication/form")
                .permitAll();
        */


    }
}
