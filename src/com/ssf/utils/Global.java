package com.ssf.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Strings;

/**
 * 全局常量和静态方法
 * @author wyy
 * 2017年3月2日
 *
 */
public class Global {
	public static final int MAX_PRODUCT_COUNT = 9999;
	public static final String SESSION_CART = "session_cart";
	public static final String SESSION_USER = "session_user";
	public static final String UPLOAD_AVATAR_URL = "/static/avatar/";
	
	public static int PAGE_LIMIT = 20;
	
	public static String getAdminPath(){
		return "admin";
	}
	
	
	
	//参数验证
	public static boolean validate(String msg,String regex){
		if(Strings.isNullOrEmpty(msg) || Strings.isNullOrEmpty(regex)) return false;
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(msg);
		return m.matches();
	}
	
	public static void main(String[] args) {
		String username = "";
		String regex = "[\u4e00-\u9fa5a-zA-Z0-9_]{3,6}"; //[\u4e00-\u9fa5]
		boolean flag = validate(username,regex);
		System.out.println(flag);
		
		//不能带中文 字母数字开头， 长度为6-12
		String password  = "$asv";
		String password2 = "asvcc中sss";
		String password3 = "asvcc11123123%%%%%sss";
		
		String p = "9sdfsd#sdf";
		String regex2 = "[a-zA-Z0-9][^\u4e00-\u9fa5]{5,11}";
		flag = validate(password,regex2);
		System.out.println(flag);
		flag = validate(password2,regex2);
		System.out.println(flag);
		flag = validate(password3,regex2);
		System.out.println(flag);
		flag = validate(p,regex2);
		System.out.println(flag);
//		Pattern p = Pattern.compile(regex);
//		Matcher m = p.matcher(username);
//		System.out.println(m.matches());
//		//username!=null && !"".equals(username)
//		//StringUtils.isNotEmpty(username);
//		System.out.println(username.matches(regex));
		
		//username.split(regex)
		
	}
	
}
