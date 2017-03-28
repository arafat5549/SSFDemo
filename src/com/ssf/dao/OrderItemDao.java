package com.ssf.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssf.model.Order;
import com.ssf.model.OrderItem;
import com.ssf.model.Product;
import com.ssf.utils.DBUtils;

public class OrderItemDao implements BaseDao<OrderItem>{
	private ProductDao productDao = new ProductDao();
	private static  String columns = 
			" a.id, "
			+ "a.count,"
			+ "a.order_id AS 'orderId',"
			+ "a.product_id AS 'productId',"
			+ "a.user_id AS 'userId',"
			+ "a.create_time AS 'createTime' ";
	
	public List<OrderItem> findAllByOrderId(int orderId){
		String sql ="SELECT "+ columns+" FROM sys_order_item a WHERE order_id=?";
		try {
			List<OrderItem> orderItems = DBUtils.getInstance().listBean(sql, OrderItem.class,orderId);
		    for (OrderItem orderItem : orderItems) {
				Product p = productDao.findById(orderItem.getProductId());
				orderItem.setProduct(p);
			}
		    return orderItems;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<OrderItem>();
	}
	//---------------------------------------------------------------
	@Override
	public List<OrderItem> findAll() {
		return null;
	}

	@Override
	public OrderItem findById(int id) {
		return null;
	}

	@Override
	public List<OrderItem> listPage(int limit, int offset) {
		return null;
	}

	@Override
	public boolean save(OrderItem t) {
		String idStr = "";
		String idHolder ="";
		boolean flag_id = t.getId()!=null &&  t.getId()> 0;
		if(flag_id){
			idStr = "id,";
			idHolder = "?,";
		}
		
		String sql = 
				"INSERT INTO sys_order_item"
				+ "("+idStr+"order_id,product_id,user_id,count,create_time"
				+ ") VALUES("+idHolder+"?,?,?,?,?)";
		
		
		t.preInsert();
		
		List<Object> params = new ArrayList<Object>();
		if(flag_id)
			params.add(t.getId());
		params.add(t.getOrderId());
		params.add(t.getProductId());
		params.add(t.getUserId());
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
	public void update(OrderItem t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		
	}
}
