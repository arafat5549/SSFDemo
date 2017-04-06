package com.ssf.exception;

/**
 * 自定义异常-只处理运行时异常
 * 
 * @author wyy
 * 2017年4月6日
 *
 */
public class BizException extends RuntimeException{
	
	private String message;
	public BizException( String message){
		this.setMessage(message);
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
