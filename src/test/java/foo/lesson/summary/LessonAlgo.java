package foo.lesson.summary;

import java.util.Arrays;
import java.util.Random;


public class LessonAlgo {
	private static Random random = new Random();
	public static void main(String[] args) {
		int randoms[] = new int[10];
		for (int i = 0; i < randoms.length; i++) {
			randoms[i] = random.nextInt(100);
		}
		for (int i : randoms) {
			System.out.print(i+",");
		}
		System.out.println();
		int key = randoms[5];
		
		bubbleSort(randoms);

		for (int i : randoms) {
			System.out.print(i+",");
		}
		System.out.println();
		
		 
		int idx = binarySearch(randoms,key);
		System.out.println("idx="+idx+","+randoms[idx]+"-key="+key);
		//Arrays.binarySearch(a, key)
	}
	
	private static void swap(int[]keys,int i,int j){
		int temp = keys[j];
		keys[j] = keys[i];
		keys[i] = temp;
	}
	
   /**
    * 冒泡排序
    */
	public static void bubbleSort(int randoms[]){
		int size = randoms.length;
		//boolean exchange=true;
		for (int i = 1; i < size; i++)
		{
			for(int j=0;j < size-i ; j++){
				//exchange = false;
				if(randoms[j] > randoms[j+1]){
					swap(randoms,j,j+1);
					//exchange = true;
				}
			}
		}
		
//		for (int i = 0; i < size - 1; i++)
//		{
//			for(int j=0;j < size-i-1; j++){
//				int a1 = randoms[j];
//				int a2 = randoms[j+1];
//				if(a1 > a2){
//					swap(randoms,j,j+1);
//				}
//			}
//		}
		
	}
	/**
	 * 二分查找 - 已经排序过得数列
	 */
	 public static int binarySearch(int randoms[],int key){
		 //
		 int low = 0;
		 int high = randoms.length - 1;
		 while (low <= high) {
		      int mid = (low+high) /2 ;
		      int midVal = randoms[mid];
		      if(midVal > key){
		    	  high = mid - 1;
		      }
		      else if(midVal < key){
		    	  low = mid +1;
		      }
		      else 
		    	  return mid;
		 }
		 return -1;
	 }
}
