/**
 * 
 */
package com.whale.security.core;

import com.whale.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhailiang
 *
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)//使SecurityProperties中的配置生效
public class SecurityCoreConfig {

}
