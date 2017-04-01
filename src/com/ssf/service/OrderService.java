package com.ssf.service;

import java.util.List;

import com.ssf.dao.CartItemDao;
import com.ssf.dao.OrderDao;
import com.ssf.dao.OrderItemDao;
import com.ssf.model.CartItem;
import com.ssf.model.Order;
import com.ssf.model.OrderItem;

public class OrderService {
	OrderDao orderDao = new OrderDao();
	OrderItemDao orderItemDao = new OrderItemDao();
	CartItemDao cartItemDao = new CartItemDao();
	
	/**
	 * 根据用户id获取所有的订单
	 * @param userId
	 */
	public void findOrdersByUserId(int userId){
		
	}
	
	/**
	 * 生成订单
	 */
	public String order(Order order,List<CartItem> items){
		
		//需要添加事务
		
		boolean flag = orderDao.save(order);//不一定两张表能同时成功
		for (OrderItem oi: order.getItems()) {
			flag = flag | orderItemDao.save(oi);
		}
		//
		if(flag)
		{
			for (CartItem s : items) {
				cartItemDao.delete(s.getId());
			}
		}
		
		
		if(!flag){
			return "插入数据库错误-订单";
		}
		return "";
	}
	
	public Integer findMaxId() {
		return orderDao.findMaxId() +1;
	}
}
