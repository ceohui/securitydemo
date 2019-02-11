package com.whale.web.controller;

import com.whale.exception.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ljy
 * @version V1.0
 * @Package com.whale.web
 * @Description: 这个类只会处理其他controller抛出来的异常，不会处理请求
 * @date 2019/2/11 12:12
 */

@ControllerAdvice
public class ControllerExceptionHandle{

    @ExceptionHandler(UserNotExistException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,Object> handleUserNotExeistException(UserNotExistException ex){
        Map<String,Object> result = new HashMap<>();
        result.put("id",ex.getId());
        result.put("messsage",ex.getMessage());
        return result;
    }

}
