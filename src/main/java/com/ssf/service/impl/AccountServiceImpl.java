package com.ssf.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssf.dao.IAccountDao;
import com.ssf.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	IAccountDao accountDao; 
	
	public void transferAccount(int from ,int to ,BigDecimal money) throws Exception
	{
		accountDao.minusMoney(from, money);
		int i= 1/0;
		accountDao.addMoney(to, money);
	}

	@Override
	public void test() {
		System.out.println("支付宝");
	}
}
