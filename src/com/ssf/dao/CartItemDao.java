package com.ssf.dao;

import java.util.List;

import com.ssf.model.CartItem;
import com.ssf.utils.DBUtils;

/**
 * 购物车项目Dao层
 * 
 * @author wyy
 * 2017年3月30日
 *
 */
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
		String sql ="SELECT " + COLUMNS +" FROM sys_cart_item a";
		return DBUtils.getInstance().listBean(sql, CartItem.class);
	}

	@Override
	public CartItem findById(Integer id) {
		String sql ="SELECT " + COLUMNS +" FROM sys_cart_item a WHERE a.id=?";
		return DBUtils.getInstance().queryBean(sql, CartItem.class,id);
	}

	@Override
	public boolean save(CartItem t) {
		String idStr = "";
		String idSuffix ="";
		boolean hasId = t!=null && t.getId()!=null && t.getId() >0;
		if(hasId)
		{
			idStr = "id,";
			idSuffix="?,";
		}
		String sql ="INSERT INTO sys_cart_item("
				+ idStr+"cart_id,product_id,count,create_time,update_time) "
				+ " VALUES("+idSuffix+"?,?,?,?,?)";
		//System.out.println(sql);
		t.preInsert();
		
		if(hasId){
			return DBUtils.getInstance().execute(sql,
					t.getId(),
					t.getCartId(),
					t.getProductId(),
					t.getCount(),
					t.getCreateTime(),
					t.getUpdateTime());
		}
		else{
			return DBUtils.getInstance().execute(sql, 
					t.getCartId(),
					t.getProductId(),
					t.getCount(),
					t.getCreateTime(),
					t.getUpdateTime());
		}
	}
	
	@Override
	public void update(CartItem t) {
		String sql = "UPDATE sys_cart_item SET "
				+ "cart_id=?,product_id=?,count=?,create_time=?,update_time=? "
				+ " WHERE id=?";
		 t.preUpdate();
		 DBUtils.getInstance().execute(sql, 
					t.getCartId(),
					t.getProductId(),
					t.getCount(),
					t.getCreateTime(),
					t.getUpdateTime(),
					t.getId());
	}

	@Override
	public void delete(Integer id) {
		String sql = "DELETE FROM sys_cart_item WHERE id=?";
		DBUtils.getInstance().execute(sql, id);
	}

	@Override
	public Integer findMaxId() {
		String sql = "SELECT MAX(id) FROM sys_cart_item";
		return DBUtils.getInstance().execute_max(sql);
	}

}
