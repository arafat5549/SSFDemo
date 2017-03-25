package com.ssf.model;

/**
 * 实体部门类
 * @author wyy
 * 2017年3月25日
 *
 */
public class Department {
   //private int id;
   private Integer id;//1.为什么要封装类型而不用基础类型
   private String name;
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
