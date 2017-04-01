package com.ssf.dao;

import java.util.List;

import com.ssf.model.Order;
import com.ssf.utils.DBUtils;

/**
 * 订单Dao层
 * 
 * @author wyy
 * 2017年3月31日
 *
 */
public class OrderDao implements BaseDao<Order>{
	
	private static final String COLUMNS = 
			" a.id,"
			+ "a.ordercode,"
			+ "a.address,"
			+ "a.receiver,"
			+ "a.mobile,"
			+ "a.post,"
			+ "a.message,"
			+ "a.status,"
			+ "a.pay_date AS 'payDate'," //"a.pay_date AS 'payDate',"
			+ "a.delivery_date AS 'deliverDate',"
			+ "a.confirm_date AS 'confirmDate',"
			+ "a.user_id AS 'userId',"
			+ "a.create_time AS 'createTime', "
			+ "a.update_time AS 'updateTime' ";
	
	/**
	 * 根据用户id获取所有的订单
	 * @param userId
	 */
	public List<Order> findOrdersByUserId(int userId){
		String sql ="SELECT "+ COLUMNS+" FROM sys_order a WHERE a.user_id=?";
		return DBUtils.getInstance().listBean(sql, Order.class, userId);
	}
	
	
	
	@Override
	public List<Order> findAll() {
		String sql ="SELECT "+ COLUMNS+" FROM sys_order a";
		return DBUtils.getInstance().listBean(sql, Order.class);
	}

	@Override
	public Order findById(Integer id) {
		String sql ="SELECT "+ COLUMNS+" FROM sys_order a WHERE id=?";
		return DBUtils.getInstance().queryBean(sql, Order.class,id);
	}

	@Override
	public boolean save(Order t) {
		String idStr = "";
		String idHolder ="";
		boolean hasId = t.getId()!=null && t.getId() > 0;
		if(hasId){
			idStr = "id,";
			idHolder = "?,";
		}
		
		String sql = 
				"INSERT INTO sys_order"
				+ "("+idStr+"ordercode,address,receiver,mobile,post,message,"
				+ "status,pay_date,delivery_date,confirm_date, user_id,create_time,update_time) "
				+ "VALUES("+idHolder+"?,?,?,?,?,?,"
						+ "?,?,?,?,?,?,?)";
		
		
		t.preInsert();
		
		return DBUtils.getInstance().execute(sql, 
				t.getId(),
				t.getOrdercode(),
				t.getAddress(),
				t.getReceiver(),
				t.getMobile(),
				t.getPost(),
				t.getMessage(),
				t.getStatus(),
				t.getPayDate(),
				t.getDeliverDate(),
				t.getConfirmDate(),
				t.getUserId(),
				t.getCreateTime(),
				t.getUpdateTime());
	}

	@Override
	public void update(Order t) {
		String sql = "UPDATE sys_order SET status=?,pay_date=?,delivery_date=?,confirm_date=? WHERE id=?";
		
		DBUtils.getInstance().execute(
					sql, 
					t.getStatus(),
					t.getPayDate(),
					t.getDeliverDate(),
					t.getConfirmDate(),
					t.getId());
	}

	@Override
	public void delete(Integer id) {
		String sql = "DELETE FROM sys_order WHERE id=?";
		DBUtils.getInstance().execute(sql, id);
	}

	@Override
	public Integer findMaxId() {
		String sql = "SELECT MAX(id) FROM sys_order";
		return DBUtils.getInstance().execute_max(sql);
	}

}
