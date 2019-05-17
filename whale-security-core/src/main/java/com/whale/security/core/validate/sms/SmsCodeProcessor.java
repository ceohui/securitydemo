/**
 * 
 */
package com.whale.security.core.validate.sms;

import com.whale.security.core.validate.ValidateCode;
import com.whale.security.core.validate.impl.AbstractValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.ServletRequest;


/**
 * 短信验证码处理器
 * 
 * @author zhailiang
 *
 * ValidateCodeProcessor.class.getSimpleName();  ValidateCodeProcessor
 */
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

	/**
	 * 短信验证码发送器
	 */
	@Autowired
	private SmsCodeSender smsCodeSender;
	
	@Override
	protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
//		String mobile= ServletRequestUtils.getStringParameter((ServletRequest) request, "mobile");
//		String mobile= ServletRequestUtils.getRequiredStringParameter((ServletRequest)request,"mobile");
		String mobile= ServletRequestUtils.getRequiredStringParameter(request.getRequest(),"mobile");
		smsCodeSender.send(mobile, validateCode.getCode());
	}

}
