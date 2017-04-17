package com.ssf.model;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.ssf.validator.Not999;

//1.从你的需求中抽取你需要的实体对象(判断他有哪些属性)

/**
 * 用户实体
 * @author wyy
 * 2017年3月23日
 *
 */
public class User extends DateEntity{
	private Integer id;
	
	//JDk的验证注解
	@Pattern(regexp="^[a-zA-Z0-9_]{3,8}")//用户名为3-8位的数字字母下划线
	private String username;
	
	
	//Hibernate的验证注解
	@Length(min=6,max=8)
	//@Not999
	private String password;
	
	private String avartarUrl;
	
	public String getAvartarUrl() {
		return avartarUrl;
	}
	public void setAvartarUrl(String avartarUrl) {
		this.avartarUrl = avartarUrl;
	}
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
				+ password + "],avartarUrl="+avartarUrl;
	}
	
	//打印对象 需要重写他的toString
	
}
