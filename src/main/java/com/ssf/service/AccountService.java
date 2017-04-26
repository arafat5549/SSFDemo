package com.ssf.service;

import java.math.BigDecimal;


public interface AccountService {
	public void test();
	public void transferAccount(int from ,int to ,BigDecimal money) throws Exception;
}
