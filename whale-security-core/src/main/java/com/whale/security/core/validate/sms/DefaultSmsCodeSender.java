package com.whale.security.core.validate.sms;

import java.io.Serializable;

/**
 * @author ljy
 * @version V1.0
 * @Package com.whale.security.core.validate.sms
 * @Description: 短信验证码的默认实现 , 使用者可以覆盖掉这个
 * @date 2019/5/17 0017 10:36
 */
public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {

        System.out.println("向手机发送短信验证码"+mobile+"---"+code);

    }
}
