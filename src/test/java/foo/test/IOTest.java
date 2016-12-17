package foo.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <b>文件读取与字符串操作</b><p>
 * 
 * 1.解析字符串：知识点(文字编码，字符Unicode)<br>
 * 2.从文本读取字符串：知识点(IO流)<br>
 * 3.统计指定路径下的所有文件  :知识点(文件夹遍历，文件过滤，递归)<br>
 * 4.非阻塞IO读取文件夹:知识点(多线程，非阻塞IO，线程池Executors-Java.util.concurretn并发包)<br>
 * 
 * @author wyy
 * 2016年12月16日
 *
 */
public class IOTest 
{
	//1.读取文件
	public  static String readFromFile(String path){
		StringBuffer sb = new StringBuffer();
		if(path!=null && !"".equals(path)){//路径不为空
			FileInputStream is = null;
			try {
				is = new FileInputStream(new File(path));
				//注意: 字节流与字符流的区别？   FileReader字符流
				//1.
				byte buf[] = new byte[1024];
				int index =0;
				while ((index=is.read(buf))!=-1) {
					sb.append(new String(buf));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		return sb.toString();
	}
	//2.解析字符串
	public static void parseContent(String content,String prefix){
		
		//注意: ''与"" 的区别？
		int enCount  = 0;
		int numCount = 0;
		int cnCount  = 0;
		int otherCount = 0;
		for (char c : content.toCharArray()) {
			if((c>='a' && c<= 'z') || (c>='A' && c<='Z')){
				enCount++;
			}
			else if(c>='0' && c<='9'){
				numCount++;
			}
			else if(c>='\u4e00' && c<='\u9fa5'){
				cnCount++;
			}
			else{
				otherCount++;
			}
		}
		//字符串格式化
		System.out.printf("%30s - 英文字符:%8d,中文字符:%8d,数字字符:%8d,其他字符:%8d\n"
				,prefix,enCount,cnCount,numCount,otherCount);
//		System.out.println("英文字符="+enCount+",数字字符="+numCount
//				+",中文字符="+cnCount+",其他字符="+otherCount);
	}
	private static AtomicLong count = new AtomicLong(0);
	private static int cpus = Runtime.getRuntime().availableProcessors();
	private static ExecutorService es = Executors.newFixedThreadPool(cpus);
	//3.文件夹遍历
	public static void ListFiles(File root){
		//1.获取所有的目录
		File[] files = root.listFiles();
		for (File file : files) {
			if(file.isDirectory()){
				ListFiles(file);
			}
			else{
				String filename = file.getName();
				if(filename.endsWith(".java")||filename.endsWith(".sql")){//正则表达式
					String filepath = file.getAbsolutePath();
					count.incrementAndGet();
					es.execute(new ReadFile(filepath));
					
					System.out.println(count.get());
					//new Thread(new ReadFile(filepath)).start();
					//String content = readFromFile(file.getAbsolutePath());
					//parseContent(content,filename); 
				}
			}
		}
	}
	//1.
	static class ReadFile implements Runnable{
		private String path;
		public ReadFile(String path){
			this.path = path;
		}
		@Override
		public void run() {
			String content = readFromFile(path);
			parseContent(content,""); 
		}
		
	}
	
	public static void main(String[] args) {
		//1.读取文件
//		String content = readFromFile("F:\\abc.sql");
//		System.out.println(content);
//		parseContent(content); 
		//2.遍历文件夹
		ListFiles(new File("F:\\"));
		//3.非阻塞IO-读取 / 多线程  首选线程池
		 
		
	}
}
