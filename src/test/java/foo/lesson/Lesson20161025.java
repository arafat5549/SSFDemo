package foo.lesson;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.io.IOUtils;

/**
 * <b>文件读取与字符串操作</b><p>
 * 
 * 1.解析字符串：知识点(文字编码，字符Unicode)<br>
 * 2.从文本读取字符串：知识点(IO流)<br>
 * 3.统计指定路径下的所有文件  :知识点(文件夹遍历，文件过滤，递归)<br>
 * 4.非阻塞IO读取文件夹:知识点(多线程，非阻塞IO，线程池Executors-Java.util.concurretn并发包)<br>
 * 
 * @author wyy
 * 2016年10月25日
 *
 */
public class Lesson20161025 
{
	//3.统计工程路径下的所有文件
	//4.Non-blockingIO 伪非阻塞型IO-
	//线程1.遍历文件夹
	static class Crawler implements Runnable{
		private BlockingQueue<String> queue;
		private File root;
		private static int cpus = Runtime.getRuntime().availableProcessors();
		private static ExecutorService es = Executors.newFixedThreadPool(cpus);
		
		public Crawler(File root,BlockingQueue<String> list){
			this.queue = list;
			this.root = root;
		}
		public boolean IsFileRead(File file){
			//TODO
			return false;
		}
		
		private void crawl(File sroot)
		{
			File[] entries = sroot.listFiles();
			if(entries!=null){
				for(File file:entries){
					if(file.isDirectory()){//如果是文件夹的话
						crawl(file);
					}
					else{
//						try {
//							queue.put(file.getAbsolutePath());
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
						//wwww#xxx.com
						if(!IsFileRead(file))//没有读取过得文件才读取
						{
							String fname = file.getName();
							if(fname.endsWith(".java")|| fname.endsWith(".xml"))
							{
								Runnable pp =  new ReadRuuner(file);
							    es.execute(pp);
							}	
						}
					}
				}
			}
		}
		
		@Override
		public void run() 
		{
			while(true) 
			{
				crawl(root);
			}
		}
		
	}
	
	//开线程读取文件
	static class ReadRuuner implements Runnable
	{
		private File file;
		public ReadRuuner(File file){
			this.file = file;
		}
		@Override
		public void run() {
			String content = readFromFile(file.getAbsolutePath());
			CountFile(content,"#"+file.getName());
		}
	};
	
	//3.阻塞型IO模式-遍历文件夹
	public static void ListFiles(File root){
		//1.获取当前文件夹下的目录
		File[] entries = root.listFiles();
		if(entries!=null){
			for(File file:entries){
				if(file.isDirectory()){//如果是文件夹的话
					ListFiles(file);
				}
				else{
					String fname = file.getName();
					if(fname.endsWith(".java")|| fname.endsWith(".xml")){
						
						String str= readFromFile(file.getAbsolutePath());
						CountFile(str, fname);
					}
				}
			}
		}
	}
	
	//2.从文件读取
	public static String readFromFile(String fileName)
	{
		if(fileName==null || "".equals(fileName)){
			return null;
		}
		//StringUtils.endsWithAny(arg0, "xml","java")
		//1.传统文件读取方式
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
		//2.利用commons-io第三方IO工具包
		String ret =null;
		FileInputStream is = null;
		try {
			is = new FileInputStream(new File(fileName));
			ret = IOUtils.toString(is);
		}
		catch (IOException e) {
			e.printStackTrace();
		}finally{ 
			IOUtils.closeQuietly(is);
		}
		return ret;
	}
	
	//1.解析字符串
	private static void CountFile(String str,String prefix){
		long en_count   =0;
		long cn_count   =0;
		long num_count  =0;
		long other_count=0;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if((c>= 'a' && c<='z')||(c>= 'A' && c<='Z'))
			{
				en_count+=1;
			}
			else if(c>= '0' && c<='9'){
				num_count+=1;
			}
			else if(c>= '\u4e00' && c<='\u9fa5'){
				//\u4e00-\u9fa5
				cn_count+=1;
			}
			else{
				other_count+=1;
			}
		}
		//.文本对齐
		System.out.printf("%30s - 英文字符:%8d,中文字符:%8d,数字字符:%8d,其他字符:%8d\n"
				,prefix,en_count,cn_count,num_count,other_count);
//		System.out.println(prefix+"英文字符:"+en_count+"，中文字符："+cn_count
//				+"，数字字符："+num_count+"，其他字符："+other_count);
	}
	
	
	public static void main(String[] args) {
		//1.解析字符串：知识点(文字编码，字符Unicode)
		//2.从文本读取字符串：知识点(IO流)
		//3.统计工程路径下的所有文件  :知识点(文件夹遍历，多线程，非阻塞IO)
		//4.非阻塞IO读取（内存溢出）
		//#1.根据后缀名来过滤文件(.java ,.txt,.xml,.avi)
		//#2.文件夹遍历
		//#3.文件特别特别大，怎么读取 (多线程，非阻塞IO)
		//String ssString = readFromFile(System.getProperty("user.dir")+"\\src\\empty.xml");
		//CountFile(ssString,"");
		
		String dir = "F:\\";//System.getProperty("user.dir");
//		listAllFile(new File(dir),null);
		
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>(10);
		new Thread(new Crawler(new File(dir), queue)).start();
		//new Thread(new ReadCrawler(queue)).start();
//		for(String s:set){
//			System.out.println(s);
//		}
	}
}

