/**
 * 
 */
package com.whale.security.core.properties;

import sun.security.util.SecurityConstants;

/**
 * @author zhailiang
 *
 */
public class  BrowserProperties {

	private String loginPage = "/signIn.html";

	private LoginType loginType = LoginType.JSON;

	private int rememberMeSecodes = 3600;//秒 1小时

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	public LoginType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}

	public int getRememberMeSecodes() {
		return rememberMeSecodes;
	}

	public void setRememberMeSecodes(int rememberMeSecodes) {
		this.rememberMeSecodes = rememberMeSecodes;
	}
}
