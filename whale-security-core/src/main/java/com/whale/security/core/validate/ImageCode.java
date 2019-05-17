package com.whale.security.core.validate;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author ljy
 * @version V1.0
 * @Package com.whale.security.core.validate
 * @Description: TODO
 * @date 2019/5/6 0006 22:53
 */
public class ImageCode extends ValidateCode  {
    /**
     * 一个图片验证码包含三个信息
     */

    private BufferedImage image; //图片

  /*  private String code;//code是一个随机数，图片根据这个随机数生成，这个随机数是要存入到session中的

    private LocalDateTime expireTime;//验证码图片过期时间*/

    /**
     *
     * @param image
     * @param code
     * @param expireIn 多少秒过期
     */
    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code,expireIn);
        this.image = image;
     /*   this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);*/
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code,expireTime);
        this.image = image;
     /*   this.code = code;
        this.expireTime = expireTime;*/
    }

   /* public boolean isExpried() {
        //判断当前时间是否在过期时间之后
        return LocalDateTime.now().isAfter(expireTime);
    }
*/
    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

   /* public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }*/
}
