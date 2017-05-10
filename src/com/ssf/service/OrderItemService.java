package com.ssf.service;

import com.ssf.dao.OrderItemDao;

public class OrderItemService {
	private OrderItemDao oderItemDao= new OrderItemDao();
	
	public Integer findMaxId() {
		return oderItemDao.findMaxId() +1;
	}
}
