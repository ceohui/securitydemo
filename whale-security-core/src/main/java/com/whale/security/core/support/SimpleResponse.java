/**
 * 
 */
package com.whale.security.core.support;

/**
 * @author zhailiang
 *
 */
public class SimpleResponse {
	
	public SimpleResponse(Object content){
		this.content = content;
	}
	
	private Object content;//Object可以返回任何对象

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
	
}
