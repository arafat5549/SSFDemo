package com.ssf.dao;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Update;

import com.ssf.base.BaseDao;
import com.ssf.model.Account;

public interface IAccountDao extends BaseDao<Account>{
	String sql_minus = "UPDATE test_account SET balance=balance-#{1} WHERE id=#{0}";
	String sql_add   = "UPDATE test_account SET balance=balance+#{1} WHERE id=#{0}";
	
	@Update(sql_add)
	public void addMoney(int aid,BigDecimal money);
	
	@Update(sql_minus)
	public void minusMoney(int aid, BigDecimal money);
}
