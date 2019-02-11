package com.whale.exception;

import java.io.Serializable;

/**
 * @author ljy
 * @version V1.0
 * @Package com.whale.exception
 * @Description: 自定义异常
 * @date 2019/2/11 11:57
 */
public class UserNotExistException extends RuntimeException{

    private static final long serialVersionUID = -3807424756736653437L;

    private String id;// 存放用户不存在的id

    public UserNotExistException(String id){
        super("user not exists ! ");
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
