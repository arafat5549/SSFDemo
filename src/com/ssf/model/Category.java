package com.ssf.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 分类模块
 * @author wyy
 * 2017年3月29日
 *
 */
public class Category extends DateEntity{
	
	private Integer id;
	private String name;
	private Integer parentId;
	private String  parentIds;
	
	//只包含下级分类
	private List<Category> childs = new ArrayList<Category>();
	
	public List<Category> getChilds() {
		return childs;
	}
	public void setChilds(List<Category> childs) {
		this.childs = childs;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getParentIds() {
		return parentIds;
	}
	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", parentId="
				+ parentId + ", parentIds=" + parentIds + ", getCreateTime()="
				+ getCreateTime() + ", getUpdateTime()=" + getUpdateTime()
				+ "]";
	}
	
	
}
