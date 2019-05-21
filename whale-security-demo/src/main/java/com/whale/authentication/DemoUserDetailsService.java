package com.whale.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author ljy
 * @version V1.0
 * @Package com.whale.security.browser
 * @Description: TODO
 * @date 2019/4/6 0006 20:05
 */
@Component
public class DemoUserDetailsService implements UserDetailsService{
    // 这里注入dao 层 用于查询数据库

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //根据用户名从数据库查找用户信息
        logger.info("UserDetails 登录用户名"+ userName);
        String password = passwordEncoder.encode("123456");

        return new User(userName, password,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER"));//第三个参数是做授权的
    }

    /**
     * @param userId the user ID used to lookup the user details
     * @return the SocialUserDetails requested
     * @see UserDetailsService#loadUserByUsername(String)
     */

   /* @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {

        logger.info("登录用户id"+ userId);

        return buildUser(userId);
    }


    private SocialUser buildUser(String userName) {
        String password = passwordEncoder.encode("123456");
        logger.info("数据库密码是："+password);
        SocialUser admin = new SocialUser(userName, password,
                true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER"));//第三个参数是做授权的

        return admin;

    }*/
}
