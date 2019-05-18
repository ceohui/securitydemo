/**
 * 
 */
package com.whale.security.core.social.qq.config;

import com.whale.security.core.properties.QQProperties;
import com.whale.security.core.properties.SecurityProperties;
import com.whale.security.core.social.qq.connet.QQConnectionFactory;
import com.whale.security.core.social.qq.connet.SocialAutoConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;


/**
 * qq连接工厂的配置
 *
 * @ConditionalOnProperty(prefix = "whale.security.social.qq", name = "app-id")
 *
 * whale.security.social.qq配置后，这个类的配置才会生效
 *
 */
@Configuration
@ConditionalOnProperty(prefix = "whale.security.social.qq", name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

	@Autowired
	private SecurityProperties securityProperties;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter
	 * #createConnectionFactory()
	 */
	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		QQProperties qqConfig = securityProperties.getSocial().getQq();
		QQConnectionFactory qqConnectionFactory =
				new QQConnectionFactory(
						qqConfig.getProviderId(),
						qqConfig.getAppId(),
						qqConfig.getAppSecret());
		return qqConnectionFactory;
	}

}
