package com.ssf.model;

import java.text.SimpleDateFormat;

/**
 * 商品评论实体类
 * @author wyy
 * 2017年4月7日
 *
 */
public class Review extends DateEntity{

	private Integer id;
	private String  text;
	private Integer productId;
	private Integer userId;
	
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		//year Month day  hour minute second
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		
		return "Review [id=" + id + ", text=" + text + ", productId="
				+ productId + ", userId=" + userId + ", getCreateTime()="
				+ format.format(getCreateTime()) + ", getUpdateTime()=" + format.format(getUpdateTime())
				+ "]";
	}
	
	
}
