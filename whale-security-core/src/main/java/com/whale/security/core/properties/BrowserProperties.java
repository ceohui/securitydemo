/**
 * 
 */
package com.whale.security.core.properties;

/**
 * @author zhailiang
 *
 */
public class  BrowserProperties {

	private String loginPage = "/signIn.html";

	private String signUpUrl = "/signUp.html";

	private String signOutUrl;

	private LoginType loginType = LoginType.JSON;

	private int rememberMeSecodes = 3600;//秒 1小时

	private SessionProperties session = new SessionProperties();


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

	public SessionProperties getSession() {
		return session;
	}

	public void setSession(SessionProperties session) {
		this.session = session;
	}

	public String getSignOutUrl() {
		return signOutUrl;
	}

	public void setSignOutUrl(String signOutUrl) {
		this.signOutUrl = signOutUrl;
	}

	public String getSignUpUrl() {
		return signUpUrl;
	}

	public void setSignUpUrl(String signUpUrl) {
		this.signUpUrl = signUpUrl;
	}
}
