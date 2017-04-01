package com.ssf.service;

import java.util.Date;
import java.util.List;

import com.ssf.dao.CartItemDao;
import com.ssf.dao.OrderDao;
import com.ssf.dao.OrderItemDao;
import com.ssf.dao.ProductDao;
import com.ssf.model.CartItem;
import com.ssf.model.Order;
import com.ssf.model.Order.OrderStatus;
import com.ssf.model.OrderItem;
import com.ssf.model.Product;

public class OrderService {
	OrderDao orderDao = new OrderDao();
	OrderItemDao orderItemDao = new OrderItemDao();
	CartItemDao cartItemDao = new CartItemDao();
	ProductDao productDao = new ProductDao();
	/**
	 * 根据用户id获取所有的订单
	 * @param userId
	 */
	public List<Order> findOrdersByUserId(int userId){
		//1.根据用户id获取所有订单
		List<Order> orders = orderDao.findOrdersByUserId(userId);
		for (Order order : orders) {
			//2.根据订单id获取所有的订单项目
			List<OrderItem> items = orderItemDao.findOrderItemsByOrderId(order.getId());		
			order.setItems(items);
			//3.把订单项管理的商品信息读取进来
			for (OrderItem orderItem : items) {
				Product p = productDao.findById(orderItem.getProductId());
				orderItem.setProduct(p);
			}
		}   
		return orders;
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
	/**
	 * 付款
	 */
	public String pay(Order order)
	{
		//1.判断是不是处于等待付款状态
		if(order.getStatus().equals(OrderStatus.WAIT_PAY.getValue())){
			order.setStatus(OrderStatus.WAIT_DELIVER.getValue());//改变状态
			order.setPayDate(new Date());//设置付款日期
			orderDao.update(order);
		}
		return "";
	}
	public Integer findMaxId() {
		return orderDao.findMaxId() +1;
	}
}
