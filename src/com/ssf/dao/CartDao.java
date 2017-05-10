package com.ssf.dao;

import java.util.List;

import com.ssf.model.Cart;
import com.ssf.utils.DBUtils;

/**
 * 购物车Dao层
 * 
 * @author wyy
 * 2017年3月30日
 *
 */
public class CartDao implements BaseDao<Cart>{
	
	private static final String COLUMNS =
			" a.id,"
	       + " a.user_id AS 'userId',"
	       + " a.create_time AS 'createTime', "
	       + " a.update_time AS 'updateTime' ";
	
	/**
	 * 取用户关联的购物车
	 */
	public Cart findCartByUserId(Integer userId){
		String sql ="SELECT "+COLUMNS +" FROM sys_cart a WHERE a.user_id=?";
		return DBUtils.getInstance().queryBean(sql, Cart.class, userId);
	}
	
	@Override
	public List<Cart> findAll() {
		String sql ="SELECT " + COLUMNS +" FROM sys_cart a";
		return DBUtils.getInstance().listBean(sql, Cart.class);
	}

	@Override
	public Cart findById(Integer id) {
		String sql ="SELECT " + COLUMNS +" FROM sys_cart a WHERE a.id=?";
		return DBUtils.getInstance().queryBean(sql, Cart.class,id);
	}

	@Override
	public boolean save(Cart t) {
		String idStr = "";
		String idSuffix ="";
		boolean hasId = t!=null && t.getId()!=null && t.getId() >0;
		if(hasId)
		{
			idStr = "id,";
			idSuffix="?,";
		}
		String sql ="INSERT INTO sys_cart("
				+ idStr+"user_id,create_time,update_time) "
				+ " VALUES("+idSuffix+"?,?,?)";
		t.preInsert();
		
		if(hasId){
			return DBUtils.getInstance().execute(sql,
					t.getId(),
					t.getUserId(),
					t.getCreateTime(),
					t.getUpdateTime());
		}
		else{
			return DBUtils.getInstance().execute(sql, 
					t.getUserId(),
					t.getCreateTime(),
					t.getUpdateTime());
		}
		
	}

	@Override
	public void update(Cart t) {
		String sql = "UPDATE sys_cart SET "
				+ "user_id=?,create_time=?,update_time=? "
				+ " WHERE id=?";
		 t.preUpdate();
		 DBUtils.getInstance().execute(sql, 
					t.getUserId(),
					t.getCreateTime(),
					t.getUpdateTime(),
					t.getId());
	}

	@Override
	public void delete(Integer id) {
		String sql = "DELETE FROM sys_cart WHERE id=?";
		DBUtils.getInstance().execute(sql, id);
	}

	@Override
	public Integer findMaxId() {
		String sql = "SELECT MAX(id) FROM sys_cart";
		return DBUtils.getInstance().execute_max(sql);
	}

}
