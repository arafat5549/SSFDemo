package com.ssf.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * 加密工具
 * @author wyy
 * 2017年5月4日
 *
 */
public class EncryptUtils {

	
	public static void main(String[] args) {
		//不可逆算法
		
		//1.生成MD5编码
		String data = "abc";
		String ret = DigestUtils.md5Hex(data);
		System.out.println(ret);
		
		
		//2.SHA1
		data = "abc";
		ret = DigestUtils.sha1Hex(data);
		System.out.println(ret);
		
		
		//可逆算法
		//常规加密解密算法：BASE64
		//加密
		data= "abc"; // abc为要加密的字符串
		byte[] b = Base64.encodeBase64(data.getBytes(), true);
		System.out.println("BASE64:"+new String(b));
		//解密
		data = "YWJj"; // YWJj为要解密的字符串
		byte[] b2 = Base64.decodeBase64(data.getBytes());
		System.out.println("BASE64:"+new String(b2));
		
		
	}
}
