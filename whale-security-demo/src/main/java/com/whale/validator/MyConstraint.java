package com.whale.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ljy
 * @version V1.0
 * @Package com.whale.validator
 * @Description: 自定义约束注解
 * @date 2019/2/10 19:37
 */
@Target({ElementType.METHOD,ElementType.FIELD})  //这个注解可以标注在方法和字段上面
@Retention(RetentionPolicy.RUNTIME)              //运行时注解
@Constraint(validatedBy = MyConstraintValidator.class)  //约束的执行逻辑 validatedBy 具体执行约束逻辑的类
public @interface MyConstraint{

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
