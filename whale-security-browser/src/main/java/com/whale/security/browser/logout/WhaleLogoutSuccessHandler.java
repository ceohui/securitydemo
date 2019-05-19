package com.whale.security.browser.logout;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ljy
 * @version V1.0
 * @Package com.whale.security.browser.logout
 * @Description: TODO
 * @date 2019/5/19 0019 21:07
 */
public class WhaleLogoutSuccessHandler implements LogoutSuccessHandler {


//    private SecurityProperties securityProperties;

    private String signOutUrl;

    private ObjectMapper objectMapper = new ObjectMapper();

    public WhaleLogoutSuccessHandler(String signOutUrl){
        this.signOutUrl = signOutUrl;

    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("退出成功");

//        String signOutUrl = securityProperties.getBrowser().getSignOutUrl();
        //如果没有配置退出页面
        if(StringUtils.isBlank(signOutUrl)){

            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(objectMapper.writeValueAsString("LogoutSuccessHandler退出成功"));

        }else {
            response.sendRedirect(signOutUrl);
        }

    }

//    public SecurityProperties getSecurityProperties() {
//        return securityProperties;
//    }
//
//    public void setSecurityProperties(SecurityProperties securityProperties) {
//        this.securityProperties = securityProperties;
//    }
}
