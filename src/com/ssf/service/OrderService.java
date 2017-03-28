package com.ssf.service;

import java.util.Date;
import java.util.List;

import com.google.common.base.Strings;
import com.ssf.dao.OrderDao;
import com.ssf.dao.OrderItemDao;
import com.ssf.model.CartItem;
import com.ssf.model.Order;
import com.ssf.model.Order.orderStatus;
import com.ssf.model.OrderItem;

public class OrderService {
	
	private OrderDao orderDao = new OrderDao();
	private OrderItemDao orderItemDao = new OrderItemDao();
	
	public Integer findMaxCount(){
		return orderDao.findMaxCount();
	}
	public List<Order> findAllByUserId(int userId){
		List<Order> orders = orderDao.findAllByUserId(userId);
		for (Order order : orders) {
			List<OrderItem> orderItems = orderItemDao.findAllByOrderId(order.getId());
			order.setOrderItems(orderItems);
		}
		return orders;
	}
	//根据ID获取订单对象
	public Order findById(int id){
		Order order = orderDao.findById(id);
		if(order!=null){
			List<OrderItem> orderItems = orderItemDao.findAllByOrderId(order.getId());
			order.setOrderItems(orderItems);
		}
		return order;
	}
	
	/**
	 * 下订单
	 */
	public String order(List<CartItem> items,Order order){
		if(items.size()==0){
			return "订单生成错误-你至少需要选择一个商品";
		}
		
		if(Strings.isNullOrEmpty(order.getAddress())){
			return "地址必须填写";
		}
		if(!order.getAddress().matches(".{0,50}")){
			return "地址长度为50个字符";
		}
		
		if(Strings.isNullOrEmpty(order.getReceiver())){
			return "收件人必须填写";
		}
		if(!order.getReceiver().matches(".{0,25}")){
			return "收件人为25个字符";
		}
		//正则判断手机号
		if(Strings.isNullOrEmpty(order.getMobile())){
			return "手机号必须填写";
		}
		if(!order.getMobile().matches("[0-9]{11}")){
			return "手机号必须为11个数字";
		}
		
		Date createTime = new Date();
		order.setCreateTime(createTime);
		for (CartItem cartItem : items) {
			OrderItem oi = new OrderItem();
			oi.setOrderId(order.getId());
			oi.setProductId(cartItem.getProductId());
			oi.setCount(cartItem.getCount());
			oi.setUserId(order.getUserId());
			oi.setCreateTime(createTime);
			orderItemDao.save(oi);
		}
		boolean f = orderDao.save(order);
		if(!f){
			return "订单生成错误";
		}
		return "";
	}
	/**
	 * 付款(测试 一定成功)
	 */
	public String pay(Order order){
		if(order==null){
			return "付款错误-没有该订单";
		}
		//为等待付款状态
		if(orderStatus.STATUS_WAITPAY.getValue().equals(order.getStatus())){
			order.setStatus(orderStatus.STATUS_WAITDELIVERY.getValue());//付款成功-设为等待发货状态
			order.setPayDate(new Date());//付款成功-设置付款日期
			orderDao.update(order);
		}
		else{
			return "付款错误-该订单已付款";
		}
		return "";
	}
	/**
	 * 发货(测试 一定成功)
	 */
	public String delivery(Order order){
		if(order==null){
			return "发货错误-没有该订单";
		}
		//为等待发货状态
		if(orderStatus.STATUS_WAITDELIVERY.getValue().equals(order.getStatus())){
			order.setStatus(orderStatus.STATUS_WAITCONFIRM.getValue());//发货成功-设为确认收货状态
			order.setDeliveryDate(new Date());//发货成功-设置发货日期
			orderDao.update(order);
		}
		else{
			return "发货错误-该订单已发货";
		}		
		return "";
	}
	/**
	 * 确认收货
	 */
	public String confirm(Order order){
		if(order==null){
			return "确认收货错误-没有该订单";
		}
		//为等待发货状态
		if(orderStatus.STATUS_WAITCONFIRM.getValue().equals(order.getStatus())){
			order.setStatus(orderStatus.STATUS_WAITREVIEW.getValue());//确认收货成功-设为等待评价状态
			order.setConfirmDate(new Date());//确认收货成功-设置确认收货日期
			orderDao.update(order);
		}
		else{
			return "确认收货错误-该订单已确认收货";
		}		
		return "";
	}
}
