/**
 * 
 */
package com.whale.security.core.social.qq.connet;

import com.whale.security.core.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;



/**
 * @author zhailiang
 *
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

	/**
	 *
	 * @param providerId  提供商的唯一标识，我们通过配置文件进行配置
	 * @param appId
	 * @param appSecret
	 */
	public QQConnectionFactory(String providerId, String appId, String appSecret) {
		super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
	}

}
