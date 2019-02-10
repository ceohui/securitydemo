package com.whale.validator;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.Serializable;

/**
 * @author ljy
 * @version V1.0
 * @Package com.whale.validator
 * @Description: 约束逻辑
 * @date 2019/2/10 19:41
 */
// 不用写 @Component  ，当它实现ConstraintValidator接口时会自动被spring装配为bean
// 在这个校验器里面可以注入spring 管理的任意bean
public class MyConstraintValidator implements ConstraintValidator<MyConstraint,Object>{

    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        System.out.println("MyConstraintValidator init");

    }

    /**
     * 校验逻辑
     * @param value
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        /*验证逻辑*/
        System.out.println(value);
        return false; //true 验证 通过
    }
}
