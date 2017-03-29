package com.ssf.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.ssf.model.User;

/**
 * DBUtils工具类(只有一份实例,单例模式)
 * 用来处理数据ORM映射(ObjectRelativeMapping即数据库类型到Java对象的映射)
 * 
 * @author wyy
 * 2017年3月24日
 *
 */
public class DBUtils {
	private DBUtils(){}
	private static DBUtils instance = null;
	public  static DBUtils getInstance(){
		if(instance == null){
			instance = new  DBUtils();
		}
		return instance;
	}
	//-----------------------------------------------
	private static String driver = "com.mysql.jdbc.Driver";//Mysql驱动类
	private static String url ="jdbc:mysql://localhost:3306/myshop";
	private static String username = "root";
	private static String password = "123456";
	
	/**
	 * 1.获取数据库连接
	 * @return
	 */
	public Connection openConnection(){
		
		try {
			//1.加载驱动 # 把java的class加载到JVM里面
			Class.forName(driver);
			//反射：Class.forName(driver).newInstance() , new Driver();
			return DriverManager.getConnection(url, username, password);			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 2.操作数据库 (让Java执行SQL语句) - executeQuery查询获取返回数据
	 * @param sql
	 * @return
	 */
	public List<User> query(String sql){
		List<User> lists = new ArrayList<User>();
		Connection conn = openConnection();
		if(conn!=null){
			try {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql); 
				while(rs.next()){//1.读完了一行
					User user =new User();
					int id = rs.getInt("id");
					String username = rs.getString("username");
					String password = rs.getString("password");
					//String describe = rs.getString("describe");
					user.setId(id);
					user.setUsername(username);
					user.setPassword(password);
					lists.add(user);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lists;
	}
	/**
	 * 2-1.规范化的代码(数据库也是IO连接记得关闭流)
	 */
	public List<User> query2(String sql){
		List<User> lists = new ArrayList<User>();
		Connection conn = null;
		Statement  stmt = null;
		ResultSet  rs   = null;
		try {
			conn = openConnection();
			stmt = conn.createStatement();//避免一个连接对硬一个SQL
			rs = stmt.executeQuery(sql);
			//mapping映射 
			//ORM(ObjectRelativeMapping)对象关系映射,
			//将数据库类型转化为JAVA的属性,封装为JAVA的对象
			while(rs.next()){ //数据库一般从1#(0代表连接或者读取数据成功)
				User user =new User();
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				//String describe = rs.getString("describe");
				user.setId(id);
				user.setUsername(username);
				user.setPassword(password);
				lists.add(user);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{//从后往前关,先进后出
			try {
				if(rs!=null )   rs.close();
				if(stmt!=null ) stmt.close();
				if(conn!=null ) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lists;
	}
	//Statement的方式
	/**
	 * queryBean 只返回一个结果
	 * 
	 * @param sql
	 * @param cls
	 * @return
	 */
	public <T> T queryBean_stmt(String sql,Class<T> cls){
		List<T> lists = listBean_stmt(sql, cls);
		return lists.size()>0 ? lists.get(0)  : null;
	}
	
	/**
	 * 2-3.通用的查询模块(泛型方法) 泛型和反射
	 * @param sql 数据库sql语句
	 * @param cls 你需要指定的类的模板
	 */
	public <T> List<T> listBean_stmt(String sql,Class<T> cls){
		List<T> lists = new ArrayList<T>();
		Connection conn = null;
		Statement  stmt = null;
		ResultSet  rs   = null;
		
		//难点1.不同对象的属性和属性的类型都不一样
		try {
			conn = openConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			//1.获取结果集的元数据
			ResultSetMetaData rsmd = rs.getMetaData();//元数据(描绘数据的数据)
			//2.获取你有多少列
			int columns = rsmd.getColumnCount();
			while(rs.next()){
				T obj = cls.newInstance();
				Map<String,Object> maps =new HashMap<String,Object>();
				for(int i=0;i<columns;i++){
					//每一列叫什么名字
					String key = rsmd.getColumnName(i+1);
					Object value = rs.getObject(key);
					maps.put(key, value);
				}
				//3.反射类，将值赋给对象
				BeanUtils.populate(obj, maps);
				
				lists.add(obj);
			}
		} catch (SQLException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null )   rs.close();
				if(stmt!=null ) stmt.close();
				if(conn!=null ) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return lists;
	}
	//PreparedStatement的做法（推荐方式）
	/**
	 * 从数据库获取(SELECT)一个对象
	 * @param sql    sql语句
	 * @param cls    实体的Class,比如User.class
	 * @param params 参数是可变长度 (一个参数列表只能有一个可变参数，一定要放在最后)
	 */
	public <T> T queryBean(String sql,Class<T> cls,Object... params)
	{
		List<T> lists = listBean(sql, cls, params);
		return lists.size()>0 ? lists.get(0)  : null;
	}
	
	/**
	 * 从数据库获取(SELECT)一个对象
	 * @param sql    sql语句
	 * @param cls    实体的Class,比如User.class
	 * @param params 参数是可变长度 (一个参数列表只能有一个可变参数，一定要放在最后)
	 * @return
	 */
	public <T> List<T> listBean(String sql,Class<T> cls,Object... params)
	{
		List<T> lists = new ArrayList<T>();
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		try {
			conn = openConnection();
			ptmt = conn.prepareStatement(sql);
			//参数绑定
			for (int i = 0; i < params.length; i++) {
				ptmt.setObject(i+1, params[i]);
			}
			
			rs = ptmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while(rs.next()){
				Map<String,Object> maps =new HashMap<String, Object>();
				for (int i = 0; i < columnCount; i++) {
					//String key = rsmd.getColumnName(i+1);
					String key =rsmd.getColumnLabel(i+1);
					Object obj = rs.getObject(key);
					maps.put(key, obj);
				}
				T t = cls.newInstance();
				BeanUtils.populate(t, maps);
				lists.add(t);
			}
			
		} catch (SQLException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null )   rs.close();
				if(ptmt!=null ) ptmt.close();
				if(conn!=null ) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lists;
	}
	
	/**
	 * 执行SQL
	 * @param sql     sql语句
	 * @param params  参数是可变长度 (一个参数列表只能有一个可变参数，一定要放在最后)
	 * @return 是否执行成功
	 */
	public boolean execute(String sql,Object... params){
		Connection conn = null;
		PreparedStatement ptmt = null;
		try {
			conn = openConnection();
			ptmt = conn.prepareStatement(sql);
			//参数绑定
			for (int i = 0; i < params.length; i++) {
				ptmt.setObject(i+1, params[i]);
			}
			
			int ret = ptmt.executeUpdate();
			return ret >=1;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(ptmt!=null ) ptmt.close();
				if(conn!=null ) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	
	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		//DBUtils db = DBUtils.getInstance();
		//Connection conn = db.openConnection();
		//System.out.println(conn);
//		String sql = "SELECT * FROM sys_user";
//		List<User> lists = db.listBean(sql,User.class);
//		
//		System.out.println(lists);
//		
//		String sql2 = "SELECT * FROM test_department";
//		List<Department> lists2 = db.listBean(sql2,Department.class);
//		System.out.println(lists2);
		
		//1.泛型 （类，方法，属性，参数）
		/**
		 * 基于继承的泛型实现会带来两个问题：
		 * 1.第一个问题是有关get方法的，我们每次调用get方法都会返回一个Object对象，
		 * 每一次都要强制类型转换为我们需要的类型，这样会显得很麻烦；
		 * 
		 * 2.第二个问题是有关add方法的，假如我们往聚合了String对象的ArrayList中加入一个File对象
		 * ，编译器不会产生任何错误提示，而这不是我们想要的。
		 */
//		List<Object> mylist = new ArrayList<Object>();
//		mylist.add(new User());
//		mylist.add(new Department());
//		for (Object object : mylist) {
//			User user =(User)object;//运行时错误 #每一次都需要强转
//		}
//		List<User> mylist2 = new ArrayList<User>();
//		mylist2.add(new User());
//		mylist2.add(new Department());//源码时期错误
		
		//2.反射reflect
		//反射的作用概括地说是运行时获取类的各种定义信息，比如定义了哪些属性与方法。
		//原理是通过类的class对象来获取它的各种信息。

		User user =new User();//我就知道了用户的所有信息
		Class cls3 = user.getClass();
		Class cls = User.class;
		Class cls2 = Class.forName("com.ssf.model.User");
		
		Object obj = cls.newInstance();
		Method ms[]=cls.getMethods();
		for (Method method : ms) { 
			
			if(method.getName().equals("setUsername")){
				System.out.println(method);
				method.invoke(obj, new Object[]{"测试用户名"});
			}
			//
		}
		
		System.out.println(obj);
		//我还没创建对象 我能不能调用对象的方法
		
	}
	
}
