package com.ssf.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.ssf.dao.AccountDao;
import com.ssf.utils.BeanFactory;
import com.ssf.utils.DBUtils;

//事务一般在Service层来进行处理
public class AccountService 
{
	private AccountDao accountDao;// = new  AccountDao();
	public AccountDao getAccountDao(){
		if(accountDao==null){
			accountDao = (AccountDao)BeanFactory.getInstance().getBean("accountDao");
		}
		return accountDao;
	}
	
	//DBUtils dbUtils = DBUtils.getInstance();
	//从from到to 转了多少钱
	public void transfer(int from,int to,int money,boolean b){
		
		Connection conn = null;
		
		try{
			conn =  DBUtils.getInstance().openConnection();
			conn.setAutoCommit(false);//
			accountDao.minusMoney(conn,from, money);
//			if(b){
//			   throw new Exception();//假设这个时候出问题
//			}
			accountDao.addMoney(conn,to, money);
			
			conn.commit();
		}catch(Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		finally{
			try {
				 DBUtils.getInstance().closeConnection(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
