package foo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.io.FileSystemUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import foo.entity.User;

/**
 * Apache Commons工具包集合提供了一系列实用的通用工具类<p>
 * 1.Commons-io包 推荐度5星<br>
 * 2.commons-lang包 推荐度5星<br>
 * 3.commons-codec密码包 推荐度4星<br>
 * 4.commons-dbutils原生JDBC操作 推荐度4星<br>
 * @author wyy
 *
 */
public class ApacheCommonsDemo 
{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * Commons-io包 推荐度5星
	 * 提供了很多非常实用的IO操作
	 */
	@Test
	public void IOTest() throws MalformedURLException, IOException
	{
		String url = "http://jakarta.apache.org";
		File file = new File("F:\\dev\\servlet.txt");
		InputStream in= null;
		//标准代码：  
//		//in = new URL(url).openStream(); 
//		in = new FileInputStream(file);
//		try {  
//		       InputStreamReader inR = new InputStreamReader( in );  
//		       BufferedReader buf = new BufferedReader( inR );  
//		       String line;  
//		       while ( ( line = buf.readLine() ) != null ) {  
//		          logger.info( line );  
//		       }  
//		  } finally {  
//		    in.close();  
//		  }  
		
		//logger.info(FileUtils.readFileToString(file, "UTF-8"));
		//in = new URL(url).openStream(); 
		in = FileUtils.openInputStream(file);
		try {  
			logger.info(IOUtils.toString( in ) );  
		} finally {  
		    IOUtils.closeQuietly(in);  
		}  
		
		//3．察看剩余空间  
		long freeSpace = FileSystemUtils.freeSpaceKb("C:/"); 
		logger.info((freeSpace/1024)+"MB");
		
	}
	
	/**
	 * commons-lang包 推荐度5星
	 * 主要是一些公共的工具集合，比如对字符、数组的操作等等
	 */
	@Test
	public void LangTest()
	{
		//1.数组合并
        String[] s1 = new String[] { "1", "2", "3" };  
        String[] s2 = new String[] { "a", "b", "c" };  
        String[] s = (String[]) ArrayUtils.addAll(s1, s2);  
//        for (int i = 0; i < s.length; i++) {  
//        	logger.info(s[i]);  
//        }  
        String str = ArrayUtils.toString(s);  
        //str = str.substring(1, str.length() - 1);  
        logger.info(str + ">>" + str.length()+">>"+s.length);
        
        //2 截取从from开始字符串  
        logger.info(StringUtils.substringAfter("SELECT * FROM PERSON ", "FROM"));  
        //3 判断该字符串是不是为数字(0~9)组成，如果是，返回true 但该方法不识别有小数点和 请注意  
        logger.info(StringUtils.isNumeric("454534")+""); //返回true 
        //4.取得类名  
        logger.info(ClassUtils.getShortClassName(Test.class));  
        //取得其包名  
        logger.info(ClassUtils.getPackageName(Test.class)); 
        //5.NumberUtils  
        logger.info(NumberUtils.toInt("6")+""); 
        //6.五位的随机字母和数字  
        logger.info(RandomStringUtils.randomAlphanumeric(5));  
        //7.StringEscapeUtils   输出结果为&lt;html&gt;
        logger.info(StringEscapeUtils.escapeHtml("<html>"));  
        //  
        logger.info(StringEscapeUtils.escapeJava("\\String")); 
      //8.StringUtils,判断是否是空格字符  
        logger.info(StringUtils.isBlank("   ")+"");  
        //将数组中的内容以,分隔  
        logger.info(StringUtils.join(new Integer[]{1,2},","));  
        //在右边加下字符,使之总长度为6  
        logger.info(StringUtils.rightPad("abc", 6, 'T'));  
        //首字母大写  
        logger.info(StringUtils.capitalize("abc"));  
        //Deletes all whitespaces from a String 删除所有空格  
        logger.info(StringUtils.deleteWhitespace("   ab  c  "));  
        //判断是否包含这个字符  
        logger.info(StringUtils.contains("abc", "ba")+"");  
        //表示左边两个字符  
        logger.info(StringUtils.left("abc", 2));  
	}
	/**
	 * commons-codec密码包 推荐度4星<p>
	 * Base64双向反编码回来<br>
	 * MD5单向字符编码           <br>
	 * 包含了一系列常用的加密解密
	 */
	@Test
	public void CodecTest()
	{
		//传过来的就是username编码后的MD5
		//后台我是不是知道原来的username叫什么,再把usernameMD5编码一下
		String code ="abcdefg";
		//1.
		String out = Md5Crypt.md5Crypt(code.getBytes());
		logger.info(out);
		
		String org = Md5Crypt.apr1Crypt(out.getBytes());
		logger.info(org);
		//2.Base64
		byte b[] = Base64.encodeBase64(code.getBytes());
		logger.info(new String(b));
		byte c[] = Base64.decodeBase64(b);
		logger.info(new String(c));
	}
	
	/**
	 * Commons-BeanUtils包 推荐度3星
	 */
	@Test
	public void BeanUtilsTest() throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException{
		//1.复制对象
		User user = new User("1234","1","1234",1);
		logger.info(user.toString());
		User user2 =  (User)BeanUtils.cloneBean(user);  
		logger.info(user2.toString());  
		//2.将map转化为一个Person对象 
		Map<String,String> map = new HashMap<String,String>();  
	    map.put("name","tom");  
	    map.put("password","tom@");  
	    map.put("id","21");  
	    User person = new User();  
	    BeanUtils.populate(person,map);  
	    logger.info(person.toString());
		Map<String,String> map2 = BeanUtils.describe(person);  
		logger.info(map2.toString());		
		   
	}
}
