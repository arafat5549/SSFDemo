package com.ssf.model;

/**
 * 资源实体类
 * @author wyy
 * 2017年4月10日
 *
 */
public class Resource extends DateEntity{

	private Integer id;
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
	
	
}
