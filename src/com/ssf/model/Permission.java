package com.ssf.model;

/**
 * 权限表
 * @author wyy
 * 2017年4月10日
 *
 */
public class Permission extends DateEntity{

	private Integer id;
	private Integer resourceId;
	private String action;//权限的行为是什么，权限名称
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Integer getResourceId() {
		return resourceId;
	}
	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}
	
}
