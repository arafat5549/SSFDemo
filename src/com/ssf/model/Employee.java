package com.ssf.model;

/**
 * 员工实体类
 * @author wyy
 * 2017年3月28日
 *
 */
public class Employee {
	private Integer id;
	private String name;
	//驼峰命名方式#数据库命令规范
	private Integer deptId;//部门ID,跟其他表之间的关联
	
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
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", deptId=" + deptId
				+ "]";
	}
	
	
}
