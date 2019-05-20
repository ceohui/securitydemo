/**
 * 
 */
package com.whale.security.app.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.whale.security.core.properties.LoginType;
import com.whale.security.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//implements AuthenticationSuccessHandler
@Component("whaleAuthenticationSuccessHandler")
public class WhaleAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());

	// ObjectMapper spring mvc 提供的
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private SecurityProperties securityProperties;


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.
	 * AuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * org.springframework.security.core.Authentication)
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
										Authentication authentication) throws IOException, ServletException {

		logger.info("登录成功");
		if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())){
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(authentication));
		}else {
			//如果配置的返回类型不是json，则调用父类方法，进行跳转
			super.onAuthenticationSuccess(request,response,authentication);
		}


	}
}
