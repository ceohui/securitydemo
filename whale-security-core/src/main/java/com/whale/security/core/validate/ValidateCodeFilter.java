package com.whale.security.core.validate;

import com.whale.security.core.properties.SecurityProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ljy
 * @version V1.0
 * @Package com.whale.security.core.validate
 * @Description: TODO
 * @date 2019/5/16 0016 10:40
 */

/**
 * 继承spring中的OncePerRequestFilter，确保每次请求调用一次过滤器
 */
//InitializingBean 实现此接口中的 afterPropertiesSet 初始化方法在其中初始化图片验证码拦截路径
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    private AuthenticationFailureHandler authenticationFailureHandler;

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    //验证码路径，需要初始化
    private Set<String> urls = new HashSet<>();

    //配置
    private SecurityProperties securityProperties;

    //路径正则匹配工具
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();

        String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getImage().getUrl(),",");
        if(configUrls!=null){
            for (String url : configUrls) {
                urls.add(url);
            }
        }
        //这个路径是默认的
        urls.add("/authentication/form");

    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        System.out.println(httpServletRequest.getRequestURI());
        System.out.println(httpServletRequest.getRequestURL());

        //如果请求路径满足匹配模式 则需要验证码
        boolean action = false;
        for (String url : urls) {
            if(pathMatcher.match(url,httpServletRequest.getRequestURI())){
                action = true;
            }

        }

//        if(StringUtils.equals("/authentication/form",httpServletRequest.getRequestURI())
//                && StringUtils.equalsAnyIgnoreCase(httpServletRequest.getMethod(),"post")){
        if(action){

            try {
                validate(new ServletWebRequest(httpServletRequest));

            }catch (ValidateCodeException e){
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);

                return;//失败后直接返回，不再走下面的过滤器
            }

        }

        //如果不是登录请求，直接放行
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        ImageCode codeInSession  = (ImageCode) sessionStrategy.getAttribute(request, ValidateCodeProcessor.SESSION_KEY_PREFIX+"IMAGE");
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");
        if(StringUtils.isBlank(codeInRequest)){
            throw new ValidateCodeException("验证码的值不能为空");
        }
        if(codeInSession == null){
            throw new ValidateCodeException("验证码不存在");
        }
        if(codeInSession.isExpried()){
            sessionStrategy.removeAttribute(request,ValidateCodeProcessor.SESSION_KEY_PREFIX+"IMAGE");
            throw new ValidateCodeException("验证码已过期");
        }
        if(! StringUtils.equals(codeInSession.getCode(),codeInRequest)){
            throw new ValidateCodeException("验证码不匹配");
        }

        sessionStrategy.removeAttribute(request,ValidateCodeProcessor.SESSION_KEY_PREFIX+"IMAGE");

    }


    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }


}
