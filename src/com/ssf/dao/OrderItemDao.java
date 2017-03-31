package com.ssf.dao;

import java.util.List;

import com.ssf.model.OrderItem;
import com.ssf.utils.DBUtils;

public class OrderItemDao implements BaseDao<OrderItem>{
	
	private static  final String COLUMNS = 
			" a.id, "
			+ "a.count,"
			+ "a.order_id AS 'orderId',"
			+ "a.product_id AS 'productId',"
			+ "a.user_id AS 'userId',"
			+ "a.create_time AS 'createTime', "
			+ "a.update_time AS 'updateTime' ";
	
	@Override
	public List<OrderItem> findAll() {
		return null;
	}

	@Override
	public OrderItem findById(Integer id) {
		return null;
	}

	@Override
	public boolean save(OrderItem t) {
		String idStr = "";
		String idHolder ="";
		boolean hasId = t.getId()!=null &&  t.getId()> 0;
		if(hasId){
			idStr = "id,";
			idHolder = "?,";
		}
		
		String sql = 
				"INSERT INTO sys_order_item"
				+ "("+idStr+"order_id,product_id,user_id,count,create_time,update_time"
				+ ") VALUES("+idHolder+"?,?,?,?,?,?)";
		
		
		t.preInsert();
		
		if(hasId)
		{
			return DBUtils.getInstance().execute(sql, 
					t.getId(),
					t.getOrderId(),
					t.getProductId(),
					t.getUserId(),
					t.getCount(),
					t.getCreateTime(),
					t.getUpdateTime());
		}
		return DBUtils.getInstance().execute(sql, 
				t.getOrderId(),
				t.getProductId(),
				t.getUserId(),
				t.getCount(),
				t.getCreateTime(),
				t.getUpdateTime());
	}

	@Override
	public void update(OrderItem t) {
		
	}

	@Override
	public void delete(Integer id) {
		
	}

	@Override
	public Integer findMaxId() {
		String sql = "SELECT MAX(id) FROM sys_order_item";
		return DBUtils.getInstance().execute_max(sql);
	}

}
