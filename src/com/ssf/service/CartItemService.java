package com.ssf.service;

import java.util.List;

import com.ssf.dao.CartItemDao;
import com.ssf.dao.ProductDao;
import com.ssf.model.CartItem;
import com.ssf.model.Product;

public class CartItemService {

	CartItemDao cartItemDao = new CartItemDao();
	ProductDao productDao = new ProductDao();
	/**
	 * 根据购物车id获取所有的购物车项目
	 * @param cartId
	 * @return
	 */
	public List<CartItem> findItemsByCartId(Integer cartId){
		List<CartItem> items = cartItemDao.findItemsByCartId(cartId);
		//在Service层做外键关联的对象
		for (CartItem cartItem : items) {
			Product p =productDao.findById(cartItem.getProductId());
			cartItem.setProduct(p);
		} 
		return items;
	}
	
	/**
	 * 根据购物车id和商品id获取购物车项目
	 * @param cartId
	 * @param pid
	 * @return
	 */
	public CartItem findByCartIdAndProductId(Integer cartId,Integer pid){
		return cartItemDao.findByCartIdAndProductId(cartId,pid);
	}
	/**
	 * 获取可用的ID
	 * @return
	 */
	public Integer findMaxId(){
		return cartItemDao.findMaxId() + 1;
	}
	/**
	 * 保存对象
	 * @param t
	 * @return
	 */
	public boolean save(CartItem t){
		return cartItemDao.save(t);
	}
	/**
	 * 更新对象
	 * @param t
	 */
	public void update(CartItem t){
		cartItemDao.update(t);
	}
	
	public CartItem findById(Integer id){
		return cartItemDao.findById(id);
	}
}
