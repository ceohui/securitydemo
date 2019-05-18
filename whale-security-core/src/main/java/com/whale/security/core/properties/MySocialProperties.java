package com.whale.security.core.properties;

import java.io.Serializable;

/**
 * @author ljy
 * @version V1.0
 * @Package com.whale.security.core.properties
 * @Description: TODO
 * @date 2019/5/18 0018 19:59
 */
public class MySocialProperties implements Serializable {


    private QQProperties qq = new QQProperties();


    public QQProperties getQq() {
        return qq;
    }

    public void setQq(QQProperties qq) {
        this.qq = qq;
    }
}
