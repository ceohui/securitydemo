/**
 * 
 */
package com.whale.security.core.validate.impl;

import com.whale.security.core.validate.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * @author zhailiang
 *
 */
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {

	/**
	 * 操作session的工具类
	 */
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	/**
	 * 收集系统中所有的 {@link ValidateCodeGenerator} 接口的实现。
	 *
	 * 这个map的注入
	 * spring启动的时候，会查找map的value接口ValidateCodeGenerator的所有实现bean，
	 * 并把这个bean为value，bean的名称为key存入map中
	 *
	 * 这种行为叫依赖搜索
	 */
	@Autowired
	private Map<String, ValidateCodeGenerator> validateCodeGenerators;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whale.security.core.validate.code.ValidateCodeProcessor#create(org.
	 * springframework.web.context.request.ServletWebRequest)
	 */
	@Override
	public void create(ServletWebRequest request) throws Exception {
		C validateCode = generate(request);//生成
		save(request, validateCode);//保存
		send(request, validateCode);//发送 这是一个抽象方法 需要子类去实现
	}

	/**
	 * 生成校验码
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private C generate(ServletWebRequest request) {
		String type = getProcessorType(request);
		String generatorName = type + "CodeGenerator";
		ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(generatorName);
		if (validateCodeGenerator == null) {
			throw new ValidateCodeException("验证码生成器" + generatorName + "不存在");
		}
		return (C) validateCodeGenerator.generate(request);
	}

	/**
	 * 保存校验码
	 *
	 * @param request
	 * @param validateCode
	 */
	private void save(ServletWebRequest request, C validateCode) {
		sessionStrategy.setAttribute(request, getSessionKey(request).toUpperCase(), validateCode);
	}

	/**
	 * 构建验证码放入session时的key
	 *
	 * @param request
	 * @return
	 */
	private String getSessionKey(ServletWebRequest request) {
		return SESSION_KEY_PREFIX + getProcessorType(request);
	}

	/**
	 * 发送校验码，由子类实现
     * 它的抽象方法 send由具体的子类实现
	 * 
	 * @param request
	 * @param validateCode
	 * @throws Exception
	 */
	protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;

    /**
     * 根据请求的url获取校验码的类型
     * @param request
     * @return
     */
	private String getProcessorType(ServletWebRequest request){
        String type = StringUtils.substringAfter(request.getRequest().getRequestURI(), "/code/");
        return type;

    }
}
