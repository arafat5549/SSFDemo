package com.ssf.utils.test;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.ssf.utils.DBUtils;

//自定义连接池-测试没有用
public abstract class ItcastConnectionPool implements DataSource{

	public static int maxPoolSize = 10;
	public static int initPoolSize = 5;
	//连接池的数据机构其实就是一个List集合
	private List<Connection> pool  = new ArrayList<Connection>();
	
	public ItcastConnectionPool ()
	{
		try {
			Class.forName(DBUtils.driverName);
			for (int i = 0; i < initPoolSize; i++) {
				Connection con = DriverManager.getConnection
						(
						DBUtils.url,
						DBUtils.username,
						DBUtils.password
						); 
				//ItcastConnection conWapper = new ItcastConnection(con, this); 
				pool.add(con); 
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return 0;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}

	public void add(Connection con) {
		pool.add(con);
	}
	
	@Override
	public Connection getConnection() throws SQLException {
		if(pool.size() > 0) {
			return pool.remove(0);
		}
		throw new SQLException("没连接了");

	}

	@Override
	public Connection getConnection(String username, String password)
			throws SQLException {
		return null;
	}

}
