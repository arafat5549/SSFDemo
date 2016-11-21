package foo.algorithm;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import scala.collection.parallel.ParIterableLike.Partition;

import com.google.common.collect.Lists;

/**
 * 排序算法
 * @author wyy
 * 2016年11月11日
 *
 */
public class SortDemo 
{
	private final static Random random = new Random();
	@Test
	public void test(){
		List<Integer> list = Lists.newArrayList();
		Random random= new Random();
		  for(int i =0;i<10;i++){
			  list.add(random.nextInt(200));
		  }
		int key = random.nextInt(200);
		Integer[] values = (Integer[])list.toArray(new Integer[list.size()]);
		
		System.out.println("BaseArray:"+Arrays.toString(values));
		//insertSort(values);
		//shellSort(values);
		//bubbleSort(values);
		//quickSort(values,0,values.length-1);
		mergeSort(values,0,values.length-1);
		System.out.println(Arrays.toString(values));
	}
	/*
	 * 插入排序：
	 * 直接插入排序（增量）
	 * 小数组,已经基本排序的数组/原址排序
	 */
	public void insertSort(Integer[] keys)
	{
		for(int j=1;j<keys.length;j++){
			int key = keys[j];
			int i = j-1;
			while(i>=0 && keys[i]>key){
				keys[i+1]=keys[i];
				i = i-1;
			}
			keys[i+1] = key;
		}
	}
	/*
	 * 插入排序：
	 * 希尔排序（增量减半）
	 */
	public void shellSort(Integer[] keys){
		
		for(int delta=keys.length/2; delta>0; delta/=2) //若干趟，控制增量每趟减半
		{
			for(int j=delta;j<keys.length;j++){
				int key = keys[j];
				int i = j-delta;
				while(i>=0 && keys[i]>key){
					keys[i+delta]=keys[i];
					i = i-delta;       //每组元素相距delta远
				}
				keys[i+delta] = key;   //插入元素
			}
		}
	}
	private void swap(Integer[]keys,int i,int j){
		int temp = keys[j];
		keys[j] = keys[i];
		keys[i] = temp;
	}
	/*
	 * 交换排序:
	 * 冒泡排序（升序）
	 * O(n2)
	 */
	public void bubbleSort(Integer[] keys){
		bubbleSort(keys,true);
	}
	public void bubbleSort(Integer[] keys,boolean asc){
		boolean exchange=true;
		for (int i = 1; i < keys.length && exchange; i++) {
			for (int j = 0; j < keys.length-i; j++)
			{
				exchange = false;
				if(asc ? keys[j]>keys[j+1] : keys[j]<keys[j+1]){
					swap(keys,j,j+1);
					
					exchange = true;
				}
			}
		}
	}
	/*
	 * 快速排序
	 * 大数组/原址排序
	 */
	public void quickSort(Integer[] keys,int begin,int end){
		quickSort(keys,begin,end,true);
	}
	public void quickSort(Integer[] keys,int begin,int end,boolean asc){
		if(keys!=null && begin>=0 && begin<keys.length && end>=0 && end<keys.length &&
				begin < end){
			int idx = randomized_partition(keys,begin,end,asc);
			quickSort(keys,begin,idx-1);
			quickSort(keys,idx+1,end);
		}
	}
	//划分数组
	private int partition(Integer[] keys,int begin,int end,boolean asc)
	{
		int vot = keys[end];
		int i = begin - 1;
		for(int j=begin;j<end;j++){
			if(asc ? keys[j]<=vot : keys[j]>=vot){   //1.begin<=k<=i, keys[k] <= key
				i = i+1;		//2.i+1<=k<=j-1, keys[k] > key
				swap(keys,i,j); //3.k=end,		 keys[k]=key
			}
		}
		swap(keys,i+1,end);
		return i+1;
	}
	
	private int randomized_partition(Integer[] keys,int begin,int end,boolean asc){
		int i = begin + random.nextInt(end - begin);
		swap(keys, end, i);
		return partition(keys, begin, end, asc);
	}
	/*
	 * 归并排序
	 */
	public void mergeSort(Integer[] keys,int begin,int end)
	{
		if(keys!=null && begin>=0 && begin<keys.length && end>=0 && end<keys.length &&
				begin < end)
		{
			int idx = (begin+end)/2;
			mergeSort(keys,begin,idx);
			mergeSort(keys,idx+1,end);
			
			new_merge(keys, begin, idx, end);
		}
	}
	
	//带“哨兵”的原生态merge
	private void merge(Integer[] keys,int begin,int idx,int end){
		if(keys.length == 0 || keys.length == 1)	
			return;
		
		int n1 = idx - begin+1;
		int n2 = end - idx;
		
		Integer[] left = new Integer[n1+1];
		Integer[] right = new Integer[n2+1];
		
		for (int i = 0; i < n1; i++) {
			left[i]= keys[begin+i];
		}
		left[left.length-1] = Integer.MAX_VALUE;
		for (int i = 0; i < n2; i++) {
			right[i]= keys[idx+i+1];
		}
		right[right.length-1] = Integer.MAX_VALUE;
		
		//System.out.print(Arrays.toString(left)+" ,");
		//System.out.println(Arrays.toString(right));
		int temp1 = 0;
		int temp2 = 0; 
		for (int i = begin; i <= end; i++) {
			if(left[temp1] <= right[temp2]){
				keys[i]= left[temp1++];
			} 
			else{
				keys[i]= right[temp2++];
			}
		}
	}
	//新merge --- 不使用哨兵， 而是当一个数组走完了，直接把另一个数组中剩下的写到原始数组。
	private void new_merge(Integer[] src, int begin, int mid, int end){
		
		if(src.length == 0 || src.length == 1)	//in case src is empty
			return;
		
		//初始化左右两个子数组
		Integer[] left = new Integer[mid - begin + 1];
		Integer[] right = new Integer[end - mid];
		for(int i = 0; i < left.length; i ++)
			left[i] = src[begin + i];
		for(int i = 0; i < right.length; i ++)
			right[i] = src[mid + i + 1];
		
		//开始merge
		int temp1 = 0;
		int temp2 = 0;
out:	for(int i = begin; i <= end; i ++){
			if(left[temp1] <= right[temp2]){
				src[i] = left[temp1++];
				if(temp1 == left.length){	//left结束了，把剩下的right填满src
					for(int j = temp2; j < right.length; j ++)
						src[++i] = right[j];
					break out;
				}
			}else{
				src[i] = right[temp2++];
				if(temp2 == right.length){	//right结束了，把剩下的left填满src
					for(int j = temp1; j < left.length; j ++)
						src[++i] = left[j];
					break out;
				}
			}
		}
	}
	/*
	 * 堆排序
	 *  
	 */
	//.1建立堆结构
	
}
