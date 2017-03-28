package com.ssf.model;

import java.util.ArrayList;
import java.util.List;

import com.ssf.utils.Global;

//分页对象
public class Pagenation<T> {
	
	public Pagenation(){
		limit = Global.PAGE_LIMIT;
		offset = 0;
	}
	
	//#从前台传过来的
	//1.每页显示多少条记录
	private int limit;   //100
	//2.你的记录索引
	private int offset;  //101
	//#从数据库取出来
	//3.根据你的limit和offset取出来的一部分记录
	private List<T> lists = new ArrayList<T>();
	//4.总记录数
	private int total;    //10000
	//#根据上面的元素计算出来
	//5.总页数        total%limit==0 ? total/limit : total/limit+1;
	private int totalPage; 
	//6.当前页数    offset%limit==0 ? offset/limit :offset/limit+1 ;
	private int pageIndex;
	
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public List<T> getLists() {
		return lists;
	}
	public void setLists(List<T> lists) {
		this.lists = lists;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	
}
