/**
 * 
 */
package com.whale.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.whale.security.browser.support.SimpleResponse;
import com.whale.security.core.properties.LoginType;
import com.whale.security.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//implements AuthenticationFailureHandler
@Component("whaleAuthenctiationFailureHandler")
public class WhaleAuthenctiationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private SecurityProperties securityProperties;

	@Override
	public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException authenticationException ) throws IOException, ServletException {
		logger.info("登录失败");
		if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
			httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			httpServletResponse.setContentType("application/json;charset=UTF-8");
//			httpServletResponse.getWriter().write(objectMapper.writeValueAsString(authenticationException));//打印的信息太多 简化如下
			httpServletResponse.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(authenticationException.getMessage())));
		}else {
			super.onAuthenticationFailure(httpServletRequest,httpServletResponse,authenticationException);
		}

	}

}
