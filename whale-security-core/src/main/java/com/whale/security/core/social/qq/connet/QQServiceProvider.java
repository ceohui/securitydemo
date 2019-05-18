/**
 * 
 */
package com.whale.security.core.social.qq.connet;

import com.whale.security.core.social.qq.api.QQ;
import com.whale.security.core.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;


/**
 * @author zhailiang
 *
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

	private String appId;

	//http://wiki.connect.qq.com/%E4%BD%BF%E7%94%A8authorization_code%E8%8E%B7%E5%8F%96access_token

	private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
	
	private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";
	
	public QQServiceProvider(String appId, String appSecret) {
		super(new OAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
		//用OAuth2Template的默认实现
		//public OAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl)
		//在qq互联上注册应用的时候，qq互联会给每一个应用分一个clientId和clientSecret 相当于a应用的用户名和密码
		//authorizeUrl 流程第1步 将用户导向认证服务器的时候导向的那个url
		//accessTokenUrl 流程第4步 拿着授权码去申请令牌的时候的url

		this.appId = appId;
	}
	
	@Override
	public QQ getApi(String accessToken) {
		/**
		 * 注意 因为 QQImpl 的 属性是多实例的对象，所以不能在QQImpl的类上加 @component注解来实例化，这样它会是一个单例的
		 *
		 * 所以每次直接用new创建
		 */
		return new QQImpl(accessToken, appId);
	}

}
