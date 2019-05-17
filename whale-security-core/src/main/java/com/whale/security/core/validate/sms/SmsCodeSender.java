package com.whale.security.core.validate.sms;

/**
 * @author ljy
 * @version V1.0
 * @Package com.whale.security.core.validate.sms
 * @Description: 短信验证码发送接口
 * @date 2019/5/17 0017 10:34
 */
public interface SmsCodeSender {

    void send(String mobile,String code);

}
