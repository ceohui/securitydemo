package com.whale.security.core.properties;

/**
 * @author ljy
 * @version V1.0
 * @Package com.whale.security.core.properties
 * @Description: TODO
 * @date 2019/5/18 0018 19:56
 */
public abstract class SocialProperties {
    private String appId;
    private String appSecret;
    public SocialProperties() {
    }
    public String getAppId() {
        return this.appId;
    }
    public void setAppId(String appId) {
        this.appId = appId;
    }
    public String getAppSecret() {
        return this.appSecret;
    }
    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
