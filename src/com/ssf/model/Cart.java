package com.ssf.model;

import java.util.Set;

import com.google.common.collect.Sets;

/**
 * 购物车
 * 
 * @author wyy
 * 2017年2月24日
 *
 */
public class Cart extends DateEntity{

//	private User user;
//	private Integer cookieId;
//	private String  ipUrl;
	
	private Integer id;
	private Integer userId;
	
	private Set<CartItem> items = Sets.newHashSet();
	
	private Integer cartItemCount;

	public Integer getCartItemCount() {
		return items.size();
	}
	public void setItemCount(Integer cartItemCount) {
		this.cartItemCount = cartItemCount;
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
	
	public Set<CartItem> getItems() {
		return items;
	}
	public void setItems(Set<CartItem> items) {
		this.items = items;
	}
	@Override
	public String toString() {
		return "Cart [id=" + id + ", userId=" + userId + ", items=" + items
				+ ", toString()=" + super.toString() + "]";
	}
	
	//------------------------------
	public CartItem findByItemId(int itemId){
		for (CartItem item : items) {
			if(itemId >0 && item.getId() == itemId){
				return item;
			}
		}
		return null;
	}
	
	public CartItem findByProductId(int pid){
		for (CartItem item : items) {
			if(pid >0 && item.getProductId() == pid){
				return item;
			}
		}
		return null;
	}
	public void clear(){
		items.clear();
	}
	
	public void removeByItemId(int itemid){
		for (CartItem item : items) {
			if(itemid>0 && item.getId() == itemid){
				items.remove(item);
				break;
			}
		}
	}
	
	
}
