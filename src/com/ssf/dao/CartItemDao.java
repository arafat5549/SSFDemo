package com.ssf.dao;

import java.util.List;

import com.ssf.model.CartItem;
import com.ssf.utils.DBUtils;

public class CartItemDao implements BaseDao<CartItem>{

	private static final String COLUMNS =
			" a.id,"
	       + " a.cart_id AS 'cartId',"
	       + " a.product_id AS 'productId',"
	       + " a.count,"
	       + " a.create_time AS 'createTime', "
	       + " a.update_time AS 'updateTime' ";
	
	public List<CartItem> findItemsByCartId(Integer cartId){
		String sql ="SELECT " + COLUMNS +" FROM sys_cart_item a WHERE a.cart_id=?";
		return DBUtils.getInstance().listBean(sql, CartItem.class, cartId);
	}
	
	/**
	 * 根据购物车id和商品id获取购物车项目
	 * @param cartId
	 * @param pid
	 * @return
	 */
	public CartItem findByCartIdAndProductId(Integer cartId,Integer pid){
		String sql ="SELECT " + COLUMNS +" FROM sys_cart_item a WHERE a.cart_id=? AND a.product_id=?";
		return DBUtils.getInstance().queryBean(sql, CartItem.class, cartId,pid);
		//return null;
	}
	
	
	@Override
	public List<CartItem> findAll() {
		
		return null;
	}

	@Override
	public CartItem findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(CartItem t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(CartItem t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer findMaxId() {
		// TODO Auto-generated method stub
		return null;
	}

}
