package com.ssf.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Sets;
import com.ssf.model.Cart;
import com.ssf.model.CartItem;
import com.ssf.model.Product;
import com.ssf.model.User;
import com.ssf.utils.DBUtils;

public class CartDao implements BaseDao<Cart>{

	//CartItemDao cartItemDao = new CartItemDao();
	//ProductDao productDao = new ProductDao();
	//不用通配符来做
	private static  String columns = 
			" a.id, "
			+ "a.user_id AS 'userId',"
			+ "a.create_time AS 'createTime' ";
	//
	public Integer findMaxCount(){
		String sql ="SELECT max(a.id) FROM sys_cart_item a";
		try {
			return DBUtils.getInstance().execute_max(sql) +1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}
	
	
	//根据userid获取所有的购物车数据
	public Cart findByUserId(int userid){
		String sql ="SELECT "+ columns+" FROM sys_cart a WHERE a.user_id=?";
		Cart cart = null;
		try {
			cart = DBUtils.getInstance().queryBean(sql, Cart.class,userid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cart;
	}
		
		
    //----------------------------------------------------		
	@Override
	public List<Cart> findAll() {
		String sql ="SELECT "+ columns+" FROM sys_cart a";
		try {
			return DBUtils.getInstance().listBean(sql, Cart.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<Cart>();
	}

	@Override
	public Cart findById(int id) {
		String sql =
				"SELECT "
				+ columns
				+ " FROM sys_cart a WHERE id=?";
		try {
			return DBUtils.getInstance().queryBean(sql, Cart.class,id);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Cart> listPage(int limit, int offset) {
		String sql = "SELECT "+columns+" FROM sys_cart a limit "+offset+","+limit+"";
		try {
			return DBUtils.getInstance().listBean(sql, Cart.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<Cart>();
	}

	@Override
	public boolean save(Cart t) {
		String idStr = "";
		String idHolder ="";
		boolean flag_id = t.getId() > 0;
		if(flag_id){
			idStr = "id,";
			idHolder = "?,";
		}
		
		String sql = 
				"INSERT INTO sys_cart"
				+ "("+idStr+"user_id,create_time"
				+ ") VALUES("+idHolder+"?,?)";
		
		
		t.preInsert();
		
		List<Object> params = new ArrayList<Object>();
		if(flag_id)
			params.add(t.getId());
		params.add(t.getUserId());
		params.add(t.getCreateTime());
		
		try {
			DBUtils.getInstance().executeUpdate2(sql, 
					params
			);
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public void update(Cart t) {
		String sql = "UPDATE sys_cart SET user_id=?,create_time=? WHERE id=?";
		try {
			DBUtils.getInstance().executeUpdate(sql, 
					t.getUserId(),t.getCreateTime(),
					t.getId());
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM sys_cart WHERE id=?";
		try {
			DBUtils.getInstance().executeUpdate(sql, id);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


	public Integer getCartItemCount(User user) {
		if(user==null){
			return 0;
		}
		Cart cart = findByUserId(user.getId());
		return cart!=null ? cart.getItems().size() : 0;
	}

}
