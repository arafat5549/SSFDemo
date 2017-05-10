package com.ssf.service;

import test.proxy.IOCBeanFactory;

import com.ssf.dao.CartDao;
import com.ssf.model.Cart;

public class CartService {
	
	CartDao cartDao = new CartDao();
	
	public boolean save(Cart t){
		return cartDao.save(t);
	}
	/**
	 * 获取可用的Id
	 * @return
	 */
	public Integer findMaxId(){
		return cartDao.findMaxId() + 1;
	}
	
	/**
	 * 取用户关联的购物车
	 */
	public Cart findCartByUserId(Integer userId){
		return cartDao.findCartByUserId(userId);
	}
	
	/**
	 * 添加入购物车
	 */
	
	
}
