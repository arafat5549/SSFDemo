package com.ssf.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ssf.utils.DBUtils;

public class AccountDao {

	/**
	 * 
	 * @param from  转账方#用户ID
	 * @param to    收账方用户ID
	 * @param money 转账的金额
	 * @throws Exception 
	 */
	public void transfer(int from ,int to ,BigDecimal money) throws Exception{
		
		String sql  = "UPDATE test_account SET balance=balance-? WHERE id=?";
		String sql2 = "UPDATE test_account SET balance=balance+? WHERE id=?";
		//使用同一个连接
		Connection conn = DBUtils.getInstance().openConnection();
		conn.setAutoCommit(false); //关闭自动提交
		
		DBUtils.getInstance().execute(conn,sql, money,from);
//		if(false)
//		{
//			conn.rollback();
//			throw new Exception();
//		}
		
		DBUtils.getInstance().execute(conn,sql2, money,to);
		
		conn.commit();//关闭了自动提交 需要你手动提交
		
		//
	}
	
	//大批量插入--方法1
	public void batchInsert(){
		String sql  = "INSERT INTO test_account(user_id,balance) VALUES(?,10000)";
		
		long begin = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			DBUtils.getInstance().execute(sql, i+5);
		}
		System.out.println("batchInserts:"+((System.currentTimeMillis())-begin));
	}
	//大批量插入--方法2
	public void batchInsert2(){
		String sql  = "INSERT INTO test_account(user_id,balance) VALUES(?,10000)";
		Connection conn = DBUtils.getInstance().openConnection();
		long begin = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			DBUtils.getInstance().execute(conn,sql, i+5);
		}
		System.out.println("batchInsert2:"+((System.currentTimeMillis())-begin));
	}
	//大批量插入-自动提交
	public void batchInsert3(){
		String sql  = "INSERT INTO test_account(user_id,balance) VALUES(?,10000)";
		Connection conn = DBUtils.getInstance().openConnection();
		
		try {
			conn.setAutoCommit(false);
			long begin = System.currentTimeMillis();
			for (int i = 0; i < 10000; i++) {
				DBUtils.getInstance().execute(conn,sql, i+5);
			}
			conn.commit();
			System.out.println("batchInsert3:"+((System.currentTimeMillis())-begin));
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	
	//批处理 - 把所有的sql整合起来一起执行
	public void batchInsert4(){
		String sql  = "INSERT INTO test_account(user_id,balance) VALUES(?,10000)";
		Connection conn = DBUtils.getInstance().openConnection();
		PreparedStatement ptmt = null;
		try {
			conn.setAutoCommit(false);
			ptmt = conn.prepareStatement(sql);
			long begin = System.currentTimeMillis();
			for (int i = 0; i < 10000; i++) {
				ptmt.setInt(1, i+5);
				ptmt.addBatch();
			}
			ptmt.executeBatch();
			conn.commit();
			System.out.println("batchInsert4:"+((System.currentTimeMillis())-begin));
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		AccountDao a =new AccountDao();
		
		a.batchInsert4();
	}
}
