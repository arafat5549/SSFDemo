package foo.temp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

import org.apache.commons.io.IOUtils;

/**
 * 课程Lesson20161025
 * System.getProperty("user.dir")
 * 3.统计工程路径下的所有文件  :知识点(文件夹遍历，多线程，非阻塞IO)
 * @author wyy
 * 2016年10月25日
 *
 */
public  class Lesson20161025 
{
	public native String User();
	
	public static String readFromFile(String fileName) throws IOException
	{
//		StringBuffer sb = new StringBuffer();
//		try {
//			FileReader reader = new FileReader(fileName);
//			int index =0;
//			char[] buf = new char[1024];
//			while ((index=reader.read(buf))!=-1) {
//				sb.append(buf);
//			}
//			System.out.println(sb);
//			reader.close();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		FileInputStream is = new FileInputStream(new File(fileName));
		return IOUtils.toString(is);
	}
	
	public static void main(String[] args) {
	    //\u4e00-\u9fa5
		String ssString = null;
		try {
			ssString = readFromFile(System.getProperty("user.dir")+"\\src\\empty.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		long en_count   =0;//BigInteger
		long cn_count   =0;
		long num_count  =0;
		long other_count=0;
		//1.解析字符串：知识点(文字编码，字符Unicode)
		//2.从文本读取字符串：知识点(IO流)
		//3.统计工程路径下的所有文件  :知识点(文件夹遍历，多线程，非阻塞IO)
		
		//1.根据后缀名来过滤文件(.java ,.txt,.xml,.avi)
		//2.文件夹遍历
		//3.文件特别特别大，怎么读取 (多线程，非阻塞IO)
		//.
		
		String test = ssString;//"abbc1擦擦擦擦111擦擦擦擦_2###";
		for (int i = 0; i < test.length(); i++) {
			
			char c = test.charAt(i);
			if((c>= 'a' && c<='z')||(c>= 'A' && c<='Z'))
			{
				//System.out.print(test.charAt(i));
				en_count+=1;
			}
			else if(c>= '0' && c<='9'){
				//System.out.print(test.charAt(i));
				num_count+=1;
			}
			else if(c>= '\u4e00' && c<='\u9fa5'){
				//\u4e00-\u9fa5
				//System.out.print(test.charAt(i));
				cn_count+=1;
			}
			else{
				other_count+=1;
			}
		}
		System.out.println("英文字符:"+en_count+"，中文字符："+cn_count
				+"，数字字符："+num_count+"，其他字符："+other_count);
//		TestJNI hJni = new TestJNI();
//		System.out.println(hJni.getClass().getClassLoader());
//		System.out.println(hJni.getClass().getClassLoader().getParent());
	}
}

