package com.whale.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ljy
 * @version V1.0
 * @Package com.whale.web.aspect
 * @Description: TODO
 * @date 2019/2/11 22:38
 */
@Aspect
@Component
public class TimeAspect{

    //@Before() 相当于 拦截器的 preHandle
    //@After()
    //@AfterThrowing 抛出异常时调用

    //@Around()      切入点 覆盖了上面三种 我们一般用这个

    //第一个*  可以是任何返回值
    //第二个*  类中的任何一个方法
    //(..)    方法中的任意参数

    //参考aop表达式语法
    // https://docs.spring.io/spring/docs/4.3.22.RELEASE/spring-framework-reference/htmlsingle/#aop-pointcuts-examples
    @Around("execution(* com.whale.web.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("time aspect start ");

        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            System.out.println("arg is " + arg);
        }
        long start = new Date().getTime();

        Object object = pjp.proceed(); //切入点方法的返回值

        System.out.println("time aspect 耗时： "+ (new Date().getTime()-start));

        System.out.println("time aspect end ");


        return object;
    }
}
