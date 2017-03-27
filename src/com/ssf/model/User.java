package com.ssf.model;

//1.从你的需求中抽取你需要的实体对象(判断他有哪些属性)

/**
 * 用户实体
 * @author wyy
 * 2017年3月23日
 *
 */
public class User {
	private Integer id;
	private String username;
	private String password;
	//private Integer deptId; //dept_id
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + "]";
	}
	
	//打印对象 需要重写他的toString
	
}
