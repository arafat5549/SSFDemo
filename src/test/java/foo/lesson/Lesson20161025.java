package foo.lesson;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

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
	//3.统计工程路径下的所有文件
	//2#文档爬虫
	/*
	static class FileCrawler implements Runnable {
        private final BlockingQueue<File> fileQueue;
        private final FileFilter fileFilter;
        private final File root;

        public FileCrawler(BlockingQueue<File> fileQueue,
                           final FileFilter fileFilter,
                           File root) {
            this.fileQueue = fileQueue;
            this.root = root;
            this.fileFilter = new FileFilter() {
                public boolean accept(File f) {
                    return f.isDirectory() || fileFilter.accept(f);
                }
            };
        }

        private boolean alreadyIndexed(File f) {
            return false;
        }
        @Override
        public void run() {
            try {
                crawl(root);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        private void crawl(File root) throws InterruptedException {
        	
            File[] entries = root.listFiles(fileFilter);
            System.out.println(entries.length+",crawl---"+root);
            if (entries != null) {
                for (File entry : entries)
                    if (entry.isDirectory())
                        crawl(entry);
                    else if (!alreadyIndexed(entry))
                    {
                    	fileQueue.put(entry);
                    	System.out.println(entry);
                    }
            }
        }
    }
	
	public static void startCrawling(File[] roots) {
        BlockingQueue<File> queue = new LinkedBlockingQueue<File>(10);
        FileFilter filter = new FileFilter() {
            public boolean accept(File file) {
                return true;
            }
        };
        for (File root : roots)
            new Thread(new FileCrawler(queue, filter, root)).start();
        
    }
	*/
	
	//阻塞型IO模式-遍历文件夹
	public static void listAllFile(File root,FileFilter filter){
		  
		File[] entries = root.listFiles(filter);
		 if (entries != null) {
             for (File entry : entries)
                 if (entry.isDirectory())
                	 listAllFile(entry,filter);
                 else
                 {
                	if(entry.getName().endsWith(".java") || entry.getName().endsWith(".xml"))
                	{
                		//System.out.println(entry);
                		String content = readFromFile(entry.getAbsolutePath());
                		CountFile(content,entry.getName());
                	}
                 }
         }
	}
	
	//2.从文件读取
	public static String readFromFile(String fileName)
	{
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
		System.out.printf("%30s - 英文字符:%8d,中文字符:%8d,数字字符:%8d,其他字符:%8d\n"
				,prefix,en_count,cn_count,num_count,other_count);
//		System.out.println(prefix+"英文字符:"+en_count+"，中文字符："+cn_count
//				+"，数字字符："+num_count+"，其他字符："+other_count);
	}
	
	@Test
	public void test(){
		FileFilter filter = new FileFilter() {
            public boolean accept(File file) {
                return true;//file.getName().endsWith(".java");//.matches(".+\\.java$");
            }
        };
		String dir = "F:\\";//System.getProperty("user.dir");
		//startCrawling(new File[]{new File(dir)});
		listAllFile(new File(dir),filter);
	}
	
	public static void main(String[] args) {
		//1.解析字符串：知识点(文字编码，字符Unicode)
		//2.从文本读取字符串：知识点(IO流)
		//3.统计工程路径下的所有文件  :知识点(文件夹遍历，多线程，非阻塞IO)
		//#1.根据后缀名来过滤文件(.java ,.txt,.xml,.avi)
		//#2.文件夹遍历
		//#3.文件特别特别大，怎么读取 (多线程，非阻塞IO)
		String ssString = readFromFile(System.getProperty("user.dir")+"\\src\\empty.xml");
		CountFile(ssString,"");
	}
}

