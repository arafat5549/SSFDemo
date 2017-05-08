package com.ssf.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.ssf.service.AccountService;

//@Service(value="r2")//银行
public class AccountServiceImpl2 implements AccountService{

	@Override
	public void transferAccount(int from, int to, BigDecimal money)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void test() {
		System.out.println("银行");
	}

}
