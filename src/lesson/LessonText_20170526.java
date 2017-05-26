package lesson;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.Validate;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * 练手小项目
 * 
 * 1.回文字符串
 * 2.密码加密
 * 
 * @author wyy
 *
 */
public class LessonText_20170526 {

	/**
	 * 1.判断回文字符串
	 */
	
	//1.进阶-提取一段文本中最长的回文字符串
	
	/**
	 * 2.密码加密-可以将数据加密解密，并能将其发送给朋友
	 * 
	 * 推荐工具类:Apache commons-codec.jar
	 * 
	 * 说明加密算法:
	 * （可逆加密算法）Base64:用于传输8Bit字节代码,在HTTP环境下传递较长的标识信息(一种简单的加密并不安全)
	 * MD5
	 * DES/SHA1
	 */
	//base64加密
	private static String base64Encode(String plainText){
		byte[] b=plainText.getBytes();  
        Base64 base64=new Base64();  
        b=base64.encode(b);  
        String s=new String(b);  
        return s;  
	}
	//base64解密
	public static String base64Decode(String encodeStr){  
        byte[] b=encodeStr.getBytes();  
        Base64 base64=new Base64();  
        b=base64.decode(b);  
        String s=new String(b);  
        return s;  
    }  
    /**
	 * Hex编码.
	 */
	public static String encodeHex(byte[] input) {
		return new String(Hex.encodeHex(input));
	}

	/**
	 * Hex解码.
	 */
	public static byte[] decodeHex(String input) {
		try {
			return Hex.decodeHex(input.toCharArray());
		} catch (DecoderException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//MD5编码
	public static String md5(String data) {  
	      return DigestUtils.md5Hex(data);  
	}     
	//SHA编码
	public static String sha1(String data) {  
	    return DigestUtils.sha1Hex(data);  
	 }
    
	//2.进阶-MD5加盐(不加盐混淆，可能会有安全问题)
    private static final String SHA1 = "SHA-1";
	private static final String MD5 = "MD5";
	private static SecureRandom random = new SecureRandom();
    /**
	 * 生成随机的Byte[]作为salt.
	 * @param numBytes byte数组的大小
	 */
	public static byte[] generateSalt(int numBytes) {
		Validate.isTrue(numBytes > 0, "numBytes argument must be a positive integer (1 or larger)", numBytes);
		byte[] bytes = new byte[numBytes];
		random.nextBytes(bytes);
		return bytes;
	}
	/**
	 * 对字符串进行散列, 支持md5与sha1算法.  JAVA8才支持 :hmacmd5
	 * MessageDigest为JDK的密码加密类
	 */
	private static byte[] digest(byte[] input, String algorithm, byte[] salt, int iterations) {
		try {
			MessageDigest digest = MessageDigest.getInstance(algorithm);
			if (salt != null) {
				digest.update(salt);
			}

			byte[] result = digest.digest(input);

			for (int i = 1; i < iterations; i++) {
				digest.reset();
				result = digest.digest(result);
			}
			return result;
		} catch (GeneralSecurityException e) {
			//throw Exceptions.unchecked(e);
			e.printStackTrace();
		}
		return salt;
	}
	
	//public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 2;
	public static final int SALT_SIZE = 8;
	/**
	 * 生成密码
	 * @param plainText
	 * @return
	 */
	public static String entryptPassword(String plainPassword) {
		byte[] salt = generateSalt(SALT_SIZE);
		//System.out.println(encodeHex(salt));
		byte[] hashPassword = digest(plainPassword.getBytes(), SHA1, salt, HASH_INTERATIONS);//sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
		return encodeHex(salt)+encodeHex(hashPassword); //encodeHex(salt)+ encodeHex(hashPassword)
	}
	
	/**
	 * 验证密码
	 * @param plainPassword 明文密码
	 * @param password 密文密码
	 * @return 验证成功返回true
	 */
	public static boolean validatePassword(String plainPassword, String password) {
		byte[] salt = decodeHex(password.substring(0,16));//hex编码为16位
		byte[] hashPassword = digest(plainPassword.getBytes(), SHA1, salt, HASH_INTERATIONS);
		return password.equals(encodeHex(salt)+encodeHex(hashPassword)); // encodeHex(salt)+ encodeHex(hashPassword)
	}
	
	public static void main(String[] args) {
		String plainPassword ="123456";
		String encode = base64Encode(plainPassword);
		System.out.println("base64加密:"+encode);
		
		String decode = base64Decode(encode);
		System.out.println("base64加解密密:"+decode);
		
		//
		String md5 = md5(plainPassword);
		System.out.println("MD5加密:"+md5);
		String sha1 = sha1(plainPassword);
		System.out.println("sha1加密:"+sha1);
		//MD5加盐
		
		
//		for (int i = 0; i < 10; i++) {
//			String md5salut= new String(digest(plainText.getBytes(), MD5, generateSalt(6), 2));
//			System.out.println("MD5加盐："+md5salut);
//			String md5Origin = md5(plainText);
//			System.out.println("MD5加密:"+md5Origin);
//		}
		System.out.println("MD5复杂加密算法（每次生成的加密字符串都不一样）:");
		String encodePWD = entryptPassword(plainPassword);
		System.out.println(encodePWD);
		boolean flag = validatePassword(plainPassword,encodePWD);
		System.out.println(flag);
	}
}
