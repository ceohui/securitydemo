/**
 * 
 */
package com.whale.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhailiang
 *
 */
@ConfigurationProperties(prefix = "whale.security") //这个类会读取以whale.security开头的配置项
public class SecurityProperties {
	//浏览器配置
	private BrowserProperties browser = new BrowserProperties();

	//验证码配置
	private ValidateCodeProperties code = new ValidateCodeProperties();

	private MySocialProperties social = new MySocialProperties();


	public BrowserProperties getBrowser() {
		return browser;
	}

	public void setBrowser(BrowserProperties browser) {
		this.browser = browser;
	}

	public ValidateCodeProperties getCode() {
		return code;
	}

	public void setCode(ValidateCodeProperties code) {
		this.code = code;
	}

	public MySocialProperties getSocial() {
		return social;
	}

	public void setSocial(MySocialProperties social) {
		this.social = social;
	}
}