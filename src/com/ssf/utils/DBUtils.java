package com.ssf.utils;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 数据库连接工具类
 * 写成一个单例(1.私有化构造器)
 * 
 * #jdbc API
 * @author wyy
 * 2017年2月13日
 *
 */
public class DBUtils 
{
	private DBUtils(){}
	private static DBUtils instance = new DBUtils();
	public static DBUtils getInstance(){
		return instance;
	}
	
	//
//	private static ItcastConnectionPool mypool = new ItcastConnectionPool();
//	private static Connection conn = null;
	
	//C3P0数据库连接池
	private static DataSource dataSource = new ComboPooledDataSource();
	public static DataSource getDataSource() {
		return dataSource;
	}
	
	//驱动类 ： mysql-java-
	//URL:
	//用户名:
	//密码:
	public static String driverName = "com.mysql.jdbc.Driver";
	public static String url = "jdbc:mysql://127.0.0.1:3306/demo";
	public static String username ="root";
	public static String password = "123456";
	
	public Connection openConnection() {
        //1.同时使用一个数据库连接
//		if(conn==null || conn.isClosed()){//如果数据库连接不为空，就不要重新获取
//			Class.forName(driverName);//加载到Java虚拟机里面
//			conn = DriverManager.getConnection(url, username, password);
//		}
//		return conn;
		//2.每次请求创建一个数据库连接 效率低
//		Class.forName(driverName);//加载到Java虚拟机里面
//		Connection conn = DriverManager.getConnection(url, username, password);
//		return conn;
		
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void closeConnection(Connection conn) throws SQLException{
		try {
			//conn.close();
		}finally{
			if(conn!=null && !conn.isClosed()) conn.close(); //设为null，下次调用openConnection会重新获取
		}
	}
	
	

	public <T> T queryBean(String sql,Class<T> cls,Object ...params) throws SQLException
	{
		ArrayList<T> lists = listBean(sql, cls, params);
		return lists.size() >0 ? lists.get(0) : null;
	}
	/**
	 * 通用的list方法  泛型方法
	 * Object ...params 可变参数，一定要放在最后
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * 
	 */
	
//	public <T> List<T> listBean2(String sql,Class<T> cls,Object ...params) 
//	{
//		ArrayList<T> lists = new ArrayList<T>();
//		return lists;
//	}
	
	public <T> ArrayList<T> listBean(String sql,Class<T> cls,Object ...params) throws SQLException{
		
		ArrayList<T> lists = new ArrayList<T>();
		Connection conn = openConnection();
		PreparedStatement ptmt= null;
		ResultSet rs = null;		
		try {	
			ptmt = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ptmt.setObject(i+1, params[i]);
			}
			rs = ptmt.executeQuery();
			ResultSetMetaData rsm = ptmt.getMetaData();//获取元数据
			int count = rsm.getColumnCount();
			while (null != rs && rs.next()) {
				HashMap<String, Object> maps = new HashMap<String, Object>();
				T t = cls.newInstance();
				for (int i = 0; i < count; i++) {
					//String columnName = rsm.getColumnName(i+1);//每一列的名称
					String label = rsm.getColumnLabel(i+1);
					Object object = rs.getObject(label);
					//System.out.print(label+","+object+"\t\n");
					if(object!=null)
						maps.put(label, object);
				}
				BeanUtils.populate(t, maps);//反射
				lists.add(t);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		finally{
			if(rs!=null)
				rs.close();
			if(ptmt!=null)
				ptmt.close();
		}
		closeConnection(conn);
		return lists;
	}
	
	public int executeUpdate2(String sql,List<Object> paramList) throws SQLException{
		Connection conn = openConnection();
		int ret = executeUpdate2(conn,sql,paramList);
		closeConnection(conn);
		return ret;
	}
	
	//用List集合来做
	private int executeUpdate2(Connection conn,String sql,List<Object> paramList) throws SQLException{
		int ret = 0;
		PreparedStatement ptmt = null;
		try {
			ptmt = conn.prepareStatement(sql);
			for (int i = 0; i < paramList.size(); i++) {
				ptmt.setObject(i+1, paramList.get(i));
			}
			ret = ptmt.executeUpdate();//所影响的几率
		}
		finally{
			if(ptmt!=null) ptmt.close();
		}
		//closeConnection();
		return ret;
	}
	
	public int executeUpdate(String sql,Object ...params)throws SQLException
	{
		Connection conn = openConnection();
		int ret = executeUpdate(conn,sql,params);
		closeConnection(conn);
		return ret;
	}
	
	public int executeUpdate(Connection conn,String sql,Object ...params) throws  SQLException
	{
		int ret = 0;
		PreparedStatement ptmt = null;
		try {
			ptmt = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ptmt.setObject(i+1, params[i]);
			}
			ret = ptmt.executeUpdate();//所影响的几率
		}
		finally{
			if(ptmt!=null) ptmt.close();
		}
		//closeConnection();
		return ret;
	}
	
	//批量处理
	public void batchExecute(Connection conn,String sql,Object[][] params) throws SQLException{
		PreparedStatement ptmt = null; 
		try {
			
			ptmt = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				Object[] rowParams = params[i];
				for (int k = 0; k < rowParams.length; k++) {
					Object obj = rowParams[k];
					ptmt.setObject(k + 1, obj);
				}
				ptmt.addBatch();
			}
			ptmt.executeBatch();
		}
		finally{
			if(ptmt!=null) ptmt.close();
		}
	}
	public int execute_max(String sql) throws SQLException
	{
		Connection conn = openConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs!=null){
				while(rs.next()){
					int max = rs.getInt(1);
					return max;
				}
			}
			
		}
		finally{
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
		}
		closeConnection(conn);
		return 0;
	}
	
//	//执行sql语句
//	public void execute(String sql) throws SQLException, ClassNotFoundException{
//		Connection conn = openConnection();
//		Statement stmt = null;
//		conn.createStatement();
//		stmt.executeUpdate(sql);
//		closeConnection();
//	}
	
	//-----------------------------------------------------------------------
//	/**
//	 * PreparedStatement相关的方法区域
//	 */
//	//用PreparedStatement的list方法
//	public ArrayList<User> list_ptmt(String sql,ArrayList<Object> params) throws ClassNotFoundException, SQLException{
//		Connection conn = openConnection();
//		PreparedStatement ptmt= conn.prepareStatement(sql);
//		
//		for (int i = 0; i < params.size(); i++) {
//			ptmt.setObject(i+1, params.get(i));
//		}
//		
//		ResultSet rSet= ptmt.executeQuery();
//		ArrayList<User> lists = new ArrayList<User>();
//		while (rSet.next()) {
//			String username = rSet.getString("username");
//			String password = rSet.getString("password");
//			System.out.println(username+","+password);
//			User user =new User();
//			user.setUsername(username);
//			user.setPassword(password);
//			lists.add(user);
//		}
//		closeConnection();
//		return lists;
//	}
//	
//	 /**
//	  * Statement相关的方法区域
//	  */
//	 //查询表用Statement的list方法
//	 public ArrayList<User> list(String sql) throws SQLException, ClassNotFoundException{
//		 //.
//		 Connection conn = openConnection();
//		 Statement stmt = conn.createStatement();
//		 //boolean f = stmt.execute(sql);
//		 ResultSet rSet= stmt.executeQuery(sql);
//		 ArrayList<User> lists = new ArrayList<User>();
//		 while (rSet.next()) {
//			String username = rSet.getString("username");
//			String password = rSet.getString("password");
//			System.out.println(username+","+password);
//			User user =new User();
//			user.setUsername(username);
//			user.setPassword(password);
//			lists.add(user);
//		}
//		closeConnection();
//		return lists;
//	 }
//	 //
//	 public User query(String sql) throws ClassNotFoundException, SQLException{
//		 ArrayList<User> lists = list(sql);
//		 return lists.size()>0 ? lists.get(0) : null;
//	 }
//	 
//	//执行sql语句  增加 删除和修改
//	public void update(String sql) throws SQLException, ClassNotFoundException{
//		Connection conn = openConnection();
//		Statement stmt = conn.createStatement();
//		stmt.executeUpdate(sql);
//		closeConnection();
//	}
	
	
	
	public static void main(String[] args) {//     syntax语法错误
		String sql ="SELECT max(a.id) FROM sys_cart a";
		try {
			int max = DBUtils.getInstance().execute_max(sql);
			System.out.println(max);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//String
		//UUID.randomUUID();
	}
}
