package com.ssf.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 购物车实体类
 * @author wyy
 * 2017年3月30日
 *
 */
public class Cart extends DateEntity{
	private Integer id;
	private Integer userId;//一个用户只能有一份购物车
	
	private List<CartItem> items =new ArrayList<CartItem>();
	
	/**
	 * 根据商品id获取购物车项
	 * @param pid
	 * @return
	 */
	public CartItem findByProductId(int pid){
		//CartItem item = null;
		for (CartItem i : items) {
			if(pid > 0 && i.getProductId() == pid){
				return i;
			}
		}
		return null;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<CartItem> getItems() {
		return items;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}
	
	
}
