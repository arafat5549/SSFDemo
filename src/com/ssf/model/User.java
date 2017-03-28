package com.ssf.model;


/**
 * 用户实体类
 * 
 * JavaBean对象封装一般用包装类即Integer类型
 * 
 * 我的选择是包装类好！原因如下： 
1。所有的sql使用的默认类型都是null，如果你把POJO中的映射属性类型写为基本类型，
当查找不到记录的时候，返回null赋给基本类型就会出错 
2。包装类型都可以相应的转化为基本类型，如果你设置为基本类型比如int的话，
它默认初始化为0，但0本身就代表着一种含义，如果为null的话，既好理解，
也可以方便开发人员转化！而且很多xml配置中默认都是null。
 */
public class User extends DateEntity{
	private Integer id;
	private String username;
    private String password;
    private String avatarUrl; //avatarUrlName = `avatar_url_name
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", avatarUrl=" + avatarUrl + ", toString()="
				+ super.toString() + "]";
	}
	
	
    
}
