package com.ssf.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssf.model.CartItem;
import com.ssf.utils.DBUtils;

public class CartItemDao implements BaseDao<CartItem>{

	private static  String columns = 
			" a.id,"
			+ "a.cart_id AS 'cartId',"
			+ "a.product_id AS 'productId',"
			+ "a.count,"
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
	//根据购物车Id获取
	public List<CartItem> findByCartId(int cartid) {
		String sql = "SELECT "+ columns + " FROM sys_cart_item a WHERE cart_id=?";
		
		try {
			return DBUtils.getInstance().listBean(sql, CartItem.class,cartid);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<CartItem>();
	}
	//根据商品id获取
	public CartItem findByProductId(int cartid,int pid) {
		String sql = "SELECT "+ columns + " FROM sys_cart_item a WHERE cart_id=? AND product_id=?";
		try {
			return DBUtils.getInstance().queryBean(sql, CartItem.class,cartid,pid);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	//根据商品id获取
	public CartItem findByItemId(int cartid,int itemid) {
		String sql = "SELECT "+ columns + " FROM sys_cart_item a WHERE cart_id=? AND id=?";
		try {
			return DBUtils.getInstance().queryBean(sql, CartItem.class,cartid,itemid);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	//
	public void clearByCardId(int cartid){
		String sql = "DELETE FROM sys_cart_item WHERE cart_id=?";
		try {
			DBUtils.getInstance().executeUpdate(sql, cartid);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//-------------------------------------------
	@Override
	public List<CartItem> findAll() {
		String sql ="SELECT "+columns+" FROM sys_cart_item a";
		try {
			return DBUtils.getInstance().listBean(sql, CartItem.class);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<CartItem>();
	}

	@Override
	public CartItem findById(int id) {
		String sql ="SELECT "+columns+" FROM sys_cart_item a WHERE id=?";
		try {
			return DBUtils.getInstance().queryBean(sql, CartItem.class,id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CartItem> listPage(int limit, int offset) {
		String sql = "SELECT "+columns+" FROM sys_cart_item a "
				+ "limit "+offset+","+limit+"";
		try {
			return DBUtils.getInstance().listBean(sql, CartItem.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<CartItem>();
	}

	@Override
	public boolean save(CartItem t) {
		String idStr = "";
		String idHolder ="";
		boolean flag_id = t.getId()!=null &&  t.getId()> 0;
		if(flag_id){
			idStr = "id,";
			idHolder = "?,";
		}
		
		String sql = 
				"INSERT INTO sys_cart_item"
				+ "("+idStr+"cart_id,product_id,count,create_time"
				+ ") VALUES("+idHolder+"?,?,?,?)";
		
		
		t.preInsert();
		
		List<Object> params = new ArrayList<Object>();
		if(flag_id)
			params.add(t.getId());
		params.add(t.getCartId());
		params.add(t.getProductId());
		params.add(t.getCount());
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
	public void update(CartItem t) {
		String sql = "UPDATE sys_cart_item SET cart_id=?,product_id=?,count=?,create_time=? WHERE id=?";
		try {
			DBUtils.getInstance().executeUpdate(sql, 
					t.getCartId(),
					t.getProductId(),
					t.getCount(),
					t.getCreateTime()
					,t.getId());
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM sys_cart_item WHERE id=?";
		try {
			DBUtils.getInstance().executeUpdate(sql, id);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
