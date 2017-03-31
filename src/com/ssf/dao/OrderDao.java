package com.ssf.dao;

import java.util.List;

import com.ssf.model.Order;

/**
 * 订单Dao层
 * 
 * @author wyy
 * 2017年3月31日
 *
 */
public class OrderDao implements BaseDao<Order>{

	@Override
	public List<Order> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(Order t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(Order t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer findMaxId() {
		// TODO Auto-generated method stub
		return null;
	}

}
