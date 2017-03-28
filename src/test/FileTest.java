package test;

import java.io.File;

public class FileTest {
	
	//文件夹遍历
	public static void listFiles(File startPath){
		//File file = new File(startPath);
		
		File[] lists= startPath.listFiles();
		for (File dir : lists) {
			if(dir.isDirectory()){
				listFiles(dir);//递归
			}
			else{
				if(dir.getName().endsWith(".ppt") || dir.getName().endsWith(".PPT"))
					System.out.println(dir);
			}
		}
	}
	
	
	public static void main(String[] args) {
		listFiles(new File("F:\\testtest"));
	}
}
