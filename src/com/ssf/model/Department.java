package com.ssf.model;

/**
 * 实体部门类
 * @author wyy
 * 2017年3月25日
 *
 */
public class Department {
    //{1,"公司",0} 父类id代表没有父类 一级分类
	//{2,"研发部",1}
	//{3,"手机研发部",2}
	
	
	//{12,"市场部",1}
   private Integer id;////private int id;1.为什么要封装类型而不用基础类型
   private String name;
   //无限级分类，树结构
   private Integer parentId;//我的父类是谁,有且只有一个父类
   private String parentIds;//记录你所有的父类
   
	public Integer getParentId() {
		return parentId;
	}
	public String getParentIds() {
		return parentIds;
	}
	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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
	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + "]";
	}
	
	
   
}
