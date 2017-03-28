package com.ssf.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 分类实体类
 * 
 * @author wyy
 * 2017年2月22日
 *
 */
public class Category extends DateEntity{

	private Integer id;
	private String name;
	private Integer parentId;
	private String parentIds;

	private List<Category> childs = new ArrayList<Category>();//子分类
	private List<Product> products = new ArrayList<Product>();//子分类
	
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
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
				+ parentId +",parentIds="+parentIds;
	}
	
	
	
}
