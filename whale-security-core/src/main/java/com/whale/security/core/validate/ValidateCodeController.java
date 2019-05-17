package com.whale.security.core.validate;

import com.whale.security.core.properties.SecurityProperties;
import com.whale.security.core.validate.sms.SmsCodeSender;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

/**
 * @author ljy
 * @version V1.0
 * @Package com.whale.security.core.validate
 * @Description: TODO
 * @date 2019/5/6 0006 23:01
 */
@RestController
public class ValidateCodeController implements Serializable {

    public static  final  String SESSION_KEY ="SESSION_KEY_IMAGE_CODE";//key

    //spring 操作session的工具类
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ValidateCodeGenerator imageCodeGenerator;

    @Autowired
    private ValidateCodeGenerator smsCodeGenerator;

    @Autowired
    private SmsCodeSender smsCodeSender;//注入手机验证码发送器

    @RequestMapping("/code/image")
    private void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1根据请求中的随机数生成图片
//        ImageCode imageCode = createImageCode(request);
//        ImageCode imageCode = createImageCode(new ServletWebRequest(request));
        ImageCode imageCode = (ImageCode) imageCodeGenerator.generate(new ServletWebRequest(request));
        //2将随机数放到session中
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY,imageCode);
        //3将生成的图片写到接口的响应中
        ImageIO.write(imageCode.getImage(),"jpeg",response.getOutputStream());
    }

    @RequestMapping("/code/sms")
    private void createSms(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {
        //1根据请求中的随机数生成图片
        ValidateCode smsCode = smsCodeGenerator.generate(new ServletWebRequest(request));
        //2将随机数放到session中
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY,smsCode);
        //3、这块应该由短信服务商将我们的短信发送出去，我们需要封装一个短信验证码发送的接口
        String mobile = ServletRequestUtils.getRequiredStringParameter(request,"mobile");
        smsCodeSender.send(mobile,smsCode.getCode());

    }

    /**
     *
     * @param  request(HttpServletRequest)
     * @return
     */
   /* private ImageCode createImageCode(ServletWebRequest request) {
        //生成一个图片对象
//        int width = 67;
        int width = ServletRequestUtils.getIntParameter(request.getRequest(),"width",securityProperties.getCode().getImage().getWidth());
//        int height =23;
        int height = ServletRequestUtils.getIntParameter(request.getRequest(),"height",securityProperties.getCode().getImage().getHeight());

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();

        //生成干扰条纹
        Random random = new Random();
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        //生成四位随机数 写入图片
        String sRand = "";
//        for (int i = 0; i <4; i++) {
//        验证码的长度不应该在请求中配置
        for (int i = 0; i <securityProperties.getCode().getImage().getLength(); i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 16);
        }

        g.dispose();

//        return new ImageCode(image, sRand, 60);
        return new ImageCode(image, sRand, securityProperties.getCode().getImage().getExpireIn());



    }

    *//**
     * 生成随机背景条纹
     *
     * @param fc
     * @param bc
     * @return
     *//*
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }*/
}
