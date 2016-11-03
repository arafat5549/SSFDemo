package foo;

import java.util.Properties;

/**
 * 各自临时代码<p>
 * 
 * @author wyy
 * 2016年10月26日
 *
 */
public class TempDemo 
{
     public static void main(String[] args) {
    	 Properties p= System.getProperties();
    	 //System.out.println(p);
    	 for(Object key:p.keySet()){
    		 System.out.printf("%30s=%s\n",key,p.get(key).toString());
    	 }
    	 
	}
}
