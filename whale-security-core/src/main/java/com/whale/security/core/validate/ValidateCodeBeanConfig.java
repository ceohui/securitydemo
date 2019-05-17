/**
 * 
 */
package com.whale.security.core.validate;

import com.whale.security.core.properties.SecurityProperties;
import com.whale.security.core.validate.image.ImageCodeGenerator;
import com.whale.security.core.validate.sms.DefaultSmsCodeSender;
import com.whale.security.core.validate.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author zhailiang
 *
 */
@Configuration
public class ValidateCodeBeanConfig {
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Bean
	@ConditionalOnMissingBean(name = "imageCodeGenerator")
	public ValidateCodeGenerator imageCodeGenerator() { //方法的名字就是spring容器中bean的名字
		ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
		codeGenerator.setSecurityProperties(securityProperties);
		return codeGenerator;
	}

	@Bean
//	@ConditionalOnMissingBean(name = "smsCodeSender")
	@ConditionalOnMissingBean(SmsCodeSender.class)//当容器中找到了SmsCodeSender的实现就不会再用此实现bean
	public SmsCodeSender smsCodeSender() { //方法的名字就是spring容器中bean的名字
		return new DefaultSmsCodeSender();
	}

}
