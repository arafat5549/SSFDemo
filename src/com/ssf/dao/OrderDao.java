package com.ssf.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssf.model.Order;
import com.ssf.model.OrderItem;
import com.ssf.utils.DBUtils;

public class OrderDao implements BaseDao<Order>{
	//private OrderItemDao orderItemDao = new OrderItemDao();
	
	private static String columns = 
			" a.id,"
			+ "a.ordercode,"
			+ "a.address,"
			+ "a.receiver,"
			+ "a.mobile,"
			+ "a.post,"
			+ "a.message,"
			+ "a.status,"
			+ "a.pay_date AS 'payDate',"
			+ "a.delivery_date AS 'deliveryDate',"
			+ "a.confirm_date AS 'confirmDate',"
			+ "a.user_id AS 'userId',"
			+ "a.create_time AS 'createTime' ";
	
	public Integer findMaxCount(){
		String sql ="SELECT max(a.id) FROM sys_order a";
		try {
			return DBUtils.getInstance().execute_max(sql) +1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}
	public List<Order> findAllByUserId(int userId) {
		String sql ="SELECT "+ columns+" FROM sys_order a WHERE user_id=?";
		try {
			List<Order> orders = DBUtils.getInstance().listBean(sql, Order.class,userId);
		    			
		    return orders;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<Order>();
	}
	//----------------------------------------------------------------
	@Override
	public List<Order> findAll() {
		String sql ="SELECT "+ columns+" FROM sys_order a";
		try {
			return DBUtils.getInstance().listBean(sql, Order.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<Order>();
	}

	@Override
	public Order findById(int id) {
		if(id <=0){
			return null;
		}
		String sql ="SELECT "+ columns+" FROM sys_order a WHERE id=?";
		try {
			return DBUtils.getInstance().queryBean(sql, Order.class,id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Order> listPage(int limit, int offset) {
		return null;
	}

	@Override
	public boolean save(Order t) {
		String idStr = "";
		String idHolder ="";
		boolean flag_id = t.getId()!=null && t.getId() > 0;
		if(flag_id){
			idStr = "id,";
			idHolder = "?,";
		}
		
		String sql = 
				"INSERT INTO sys_order"
				+ "("+idStr+"ordercode,address,receiver,mobile,post,message,"
				+ "status,pay_date,delivery_date,confirm_date, user_id,create_time) "
				+ "VALUES("+idHolder+"?,?,?,?,?,?"
						+ ",?,?,?,?,?,?)";
		
		
		t.preInsert();
		
		List<Object> params = new ArrayList<Object>();
		if(flag_id)
			params.add(t.getId());
		params.add(t.getOrdercode());
		params.add(t.getAddress());
		params.add(t.getReceiver());
		params.add(t.getMobile());
		params.add(t.getPost());
		params.add(t.getMessage());
		
		params.add(t.getStatus());
		params.add(t.getPayDate());
		params.add(t.getDeliveryDate());
		params.add(t.getConfirmDate());
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
	public void update(Order t) {
		String sql = "UPDATE sys_order SET status=?,pay_date=?,delivery_date=?,confirm_date=? WHERE id=?";
		try {
			DBUtils.getInstance().executeUpdate(
					sql, 
					t.getStatus(),
					t.getPayDate(),
					t.getDeliveryDate(),
					t.getConfirmDate(),
					t.getId());
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM sys_order WHERE id=?";
		try {
			DBUtils.getInstance().executeUpdate(sql, id);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
