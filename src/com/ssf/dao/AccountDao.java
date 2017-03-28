package com.ssf.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ssf.model.Account;
import com.ssf.utils.DBUtils;

public class AccountDao implements BaseDao<Account>{

	public void addMoney(int id,int money) throws ClassNotFoundException, SQLException{
		DBUtils dbutils = DBUtils.getInstance();
		addMoney(dbutils.openConnection(), id, money);
	}
	
	public void addMoney(Connection conn,int id,int money) throws ClassNotFoundException, SQLException{
		DBUtils dbutils = DBUtils.getInstance();
		String sql ="UPDATE sys_account SET balance=balance+? WHERE id=?";
		dbutils.executeUpdate(conn,sql, money,id);
	}
	//
	public void minusMoney(int id,int money) throws ClassNotFoundException, SQLException
	{
		DBUtils dbutils = DBUtils.getInstance();
		minusMoney(dbutils.openConnection(), id, money);
	}
    public void minusMoney(Connection conn,int id,int money) throws ClassNotFoundException, SQLException{
    	DBUtils dbutils = DBUtils.getInstance();
		String sql ="UPDATE sys_account SET balance=balance-? WHERE id=?";
		dbutils.executeUpdate(conn,sql, money,id);
	}
	//----------------------------------------
	@Override
	public List<Account> findAll() {
		return null;
	}

	@Override
	public Account findById(int id) {
		return null;
	}

	@Override
	public List<Account> listPage(int limit, int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(Account t) {
		return false;
	}

	@Override
	public void update(Account t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
