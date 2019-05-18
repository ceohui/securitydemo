/**
 * 
 */
package com.whale.security.core.properties;

/**
 * SocialProperties 官方没找到 需要我们重新
 * https://www.jianshu.com/p/e6de152a0b4e
 */
public class QQProperties extends SocialProperties {

	private String providerId = "qq";

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	
}
