package com.ssf.model;

import java.util.List;

/**
 * 分页对象
 * @author wyy
 * 2017年4月17日
 *
 */
public class Pagenation<T> {
	//从前台获取
	private Integer offset; //偏移量 从第几条记录开始
	private Integer limit;  //每页显示多少条
	//从数据库获取
	private Integer total;  //总记录数
	List<T> data;           //只返回你需要的记录数
	//计算出来
	private Integer totalPage;//总页数
	private Integer page;     //当前页数
	
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	
	
	
}
