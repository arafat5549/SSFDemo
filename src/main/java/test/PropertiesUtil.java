package test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 键值对- 配置文件
 * @author wyy
 * 2017年4月5日
 *
 */
public class PropertiesUtil {

	public static void main(String[] args) {
		PropertiesUtil p = new PropertiesUtil();
		InputStream is  = p.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		//System.out.println(url);
		
		Properties props = new Properties();
		try {
			props.load(is);
			
			for(Object key:props.keySet()){
				System.out.println(key+","+props.getProperty((String)key));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
