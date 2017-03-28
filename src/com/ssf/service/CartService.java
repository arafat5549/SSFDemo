package com.ssf.service;

import java.util.List;

import com.google.common.collect.Sets;
import com.ssf.dao.CartDao;
import com.ssf.dao.CartItemDao;
import com.ssf.dao.ProductDao;
import com.ssf.model.Cart;
import com.ssf.model.CartItem;
import com.ssf.model.Product;
import com.ssf.model.User;
import com.ssf.utils.Global;

public class CartService {
  
	private CartDao cartDao = new CartDao();
	private ProductDao productDao = new ProductDao();
	private CartItemDao cartItemDao = new CartItemDao(); 
	
	//获取UUID-id最大值+1
	public Integer findMaxCount(){
		return cartDao.findMaxCount();
	}
	public Integer getCartItemCount(User user){
		return cartDao.getCartItemCount(user);
	}
	//读取购物车
	public Cart findByUserId(int userid){
		Cart cart = cartDao.findByUserId(userid);
		if(cart!=null && cart.getId()!=null && cart.getId() > 0){
			List<CartItem> items = cartItemDao.findByCartId(cart.getId());
			for (CartItem cartItem : items) {
				Product p = productDao.findById(cartItem.getProductId());
				cartItem.setProduct(p);
			}
			cart.setItems(Sets.newHashSet(items));				
		}
		return cart;
	}
	
	public void clearCartItems(List<CartItem> items){
		for (CartItem cartItem : items) {
			cartItemDao.delete(cartItem.getId());
		}
	}
	
	//加入购物车,修改购物车   count可以为复数
	public String addToCart(Cart cart,int pid,int count)
	{
		String ret = "";
		if(cart == null){
			return "购物车获取错误";
		}
		Product p = productDao.findById(pid);
		if(p==null){
			return "商品获取错误";
		}
		CartItem item = cart.findByProductId(pid);   //cartItemDao.findByProductId(cart.getId(),pid);
		if(item == null){//没有这个商品就创建一个新的商品项
			item = new CartItem();
			item.setId(cartItemDao.findMaxCount());//在缓存中直接获取ID
			item.setProduct(p);
			item.setProductId(p.getId());
			count = Math.max(0, count);
			count = Math.min(Global.MAX_PRODUCT_COUNT, count);
			item.setCount(count);
			item.setCartId(cart.getId());
			
			cart.getItems().add(item);
			cartItemDao.save(item);
		}   
		else{//有这个商品就增加他的购买数量
			int amount = item.getCount()+count;
			amount = Math.max(0, amount);
			amount = Math.min(Global.MAX_PRODUCT_COUNT, amount);
			item.setCount(amount);
			cartItemDao.update(item);
		}
		return ret;
	}
	//删除某一项
	public String deleteCartItem(Cart cart,int itemid){
		String ret = "";
		if(cart == null){
			return "购物车获取错误";
		}
		if(itemid <= 0){
			return "商品获取错误";
		}
		CartItem item = cartItemDao.findByItemId(cart.getId(),itemid);
		if(item==null){
			return "商品获取错误";
		}
		cart.removeByItemId(itemid);
		cartItemDao.delete(itemid);
		return ret;
	}
	//清空购物车
	public void clearCart(Cart cart){
		cart.clear();
		cartItemDao.clearByCardId(cart.getId());
	}
	//保存购物车
	public boolean save(Cart cart) {
		return cartDao.save(cart);
	}
}
