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

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}
}
