package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.ssf.dao.ProductDao;
import com.ssf.dao.UserDao;
import com.ssf.model.Category;
import com.ssf.model.Product;
import com.ssf.model.User;
import com.ssf.service.AccountService;
import com.ssf.service.CategoryService;
import com.ssf.service.ProductService;
import com.ssf.utils.DBUtils;

public class JunitTest {
	
	ProductService productService = new ProductService();
	CategoryService categoryService = new CategoryService();
	AccountService accountService = new AccountService();
	
	ProductDao productDao = new ProductDao();
	UserDao userDao = new UserDao();
	/**
	 * 测试用户
	 */
	@Test
	public void userTest() throws Exception{
		User user = userDao.findById(1);
		System.out.println(user);
	} 
	/**
	 * 测试 账户(事务相关)
	 * @throws Exception 
	 */
	@Test
	public void accountTest() throws Exception{
	}
	
	/**
	 * 测试分类
	 */
	@Test
	public void categoryTest(){
		List<Category> lists = categoryService.findSubCate(9999999);
		System.out.println(lists);
	}
	/**
	 * 测试商品
	 */

	/**
	 * SQL语句测试
	 */
	@Test
	public void sqlTest(){
		DBUtils dbUtils = DBUtils.getInstance();
		
		try {
//			String sql = "SELECT * FROM sys_user";
//			ArrayList<User> lists= dbUtils.listBean(sql, User.class);
//			System.out.println(lists);
			
//			String sql2 = "SELECT * FROM sys_product WHERE id<? AND pname=?";
//			ArrayList<Product> lists2= dbUtils.listBean(sql2, Product.class,100,"PNAME:0");
//			System.out.println(lists2);
			
			
			//时间测试
//			String sql= "INSERT INTO test_date(s_date,s_time,s_timestamp,s_datetime)" +
//					"VALUES (?,?,?,?)";
//			java.util.Date d1=new java.util.Date();//处理天数 不能处理时分秒
//			
//			//java.sql.Date d2 = new java.sql.Date(d1.getTime());
//			//long time = d1.getTime();
//			java.sql.Time time = new java.sql.Time(d1.getTime());//只能时分秒
//			//long timestamp = d1.getTime();
//			java.sql.Timestamp timestamp = new java.sql.Timestamp(d1.getTime());
//			
//			java.util.Date datetime = new java.util.Date();
//			dbUtils.executeUpdate(sql, d1,time,timestamp,datetime);
			
			//date 和 datetime最常用
//			sql = "SELECT * FROM test_date";
//			TestDate testdate = dbUtils.queryBean(sql, TestDate.class);
//			
//			long t = testdate.getS_timestamp();
//			
//			
//			//yyyy-MM-dd:hh-mm-ss
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy#MM#dd:hh-mm-ss");
//			String str = sdf.format(new java.util.Date(t));
//			System.out.println("sdf="+str);
//			
//			System.out.println(testdate);
			
//			dbUtils.getDataSource();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * SQL注入
	 */
	@Test
	public void sqlSave(){
		//SQL注入
		DBUtils dbUtils = DBUtils.getInstance();
		String username = " 1' or'1=1 ";
		String sql = 
		"SELECT * FROM sys_user WHERE username=?";
		//"SELECT * FROM sys_user WHERE username='"+username+"'";
		//"DELETE FROM sys_user WHERE username='"+username+"'";
		System.out.println(sql);
		try {
			ArrayList<Object> params = new ArrayList<Object>();
			params.add(username);
			
			ArrayList<User> lists = dbUtils.listBean(sql,User.class ,params);
			System.out.println(lists);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	/**
	 * 批量处理 Batch
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void batchTest() throws ClassNotFoundException, SQLException{
		DBUtils dbUtils = DBUtils.getInstance();
		
		int times = 10000;
		long begin = System.currentTimeMillis();
		//1.普通的批量插入，关闭了自动事务提交
		String sql = "INSERT INTO demo(username,password) VALUES(?,?)";
		Connection conn = dbUtils.openConnection();
		conn.setAutoCommit(false);//关闭自动事务
		
//		for (int i = 0; i < times; i++) {
//			String username= "uname:"+i;
//			String password = "p:"+i;
//			dbUtils.executeUpdate(conn,sql, username,password);
//		}
		
		//2.批处理
//		Object[][] params =new Object[times][2];
//		for (int i = 0; i < times; i++) {
//			String username= "uname:"+i;
//			String password = "p:"+i;
//			params[i][0]= username;
//			params[i][1]= password;
//		}
//		dbUtils.batchExecute(conn, sql, params);
		
		//INSERT INTO demo(username,password) VALUES ('',''),('',''),('','')
		//3.改变SQL语法来进行批处理,插入100万条数据
		PreparedStatement ptmt= conn.prepareStatement("");
		for(int k=0;k<100;k++)
		{
			String prefix = "INSERT INTO demo(username,password) VALUES ";
			StringBuffer suffix = new StringBuffer();
			for (int i = 0; i < times; i++) {
				String username= "uname:"+i;
				String password = "p:"+i;
				suffix.append("('"+username+"','"+password+"'),");
			}
			String newsql = prefix + suffix.substring(0, suffix.length() - 1);
			ptmt.addBatch(newsql);
		}
		ptmt.executeBatch();
		//
		conn.commit();//关闭自动事务，需要手动提交事务
		dbUtils.closeConnection(conn);
		System.out.println("经过时间:"+(System.currentTimeMillis()-begin)); 
	}
		
	/**
	 * String测试
	 */
	@Test
	public void stringTest(){
		
		//堆内存   栈内存   静态方法区（常量区）
		//String s =new String("string");
		String s0 = "string";//常量池
		String s1 = "string";
		String s2 = "str" +"ing";
		//==比较的是引用地址  equals内容
		System.out.println("1."+(s0 == s1));
		System.out.println("2."+(s0 == s2));
		
		String s3 = new String("string");//new 对象
		String s4 = "str" + new String("ing");
		//--------------------------------
		String s5 = new String("str") + new String("ing") + new String("");
		System.out.println("3."+(s0 == s3)); 
		System.out.println("4."+(s0.equals(s3)));
		System.out.println("5."+(s0 == s4));
		System.out.println("6."+(s0 == s5));
		System.out.println("7."+(s3 == s4));
		System.out.println("8."+(s3 == s5));
		System.out.println("----------------------------");	
		
		int      i = 10;
		Integer i2 = 10;
		Integer i3 = new Integer(10);
		System.out.println(i == i2);
		System.out.println(i == i3);
		
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("111111").append("11111");
		
		StringBuilder sBuilder = new StringBuilder();
	}
	
	@Test
	public  void stringbuffertest()
	{
		int sum= 10000000;
		long cur = System.currentTimeMillis();
		String string = "";
		for (int i = 0; i < sum; i++) {
			string+=new String(""+i);
		}
		System.out.println("字符串拼接Times:"+(System.currentTimeMillis() - cur));
//		String aString = "www";
//		String s = "a"+"n"+aString+"edn";
//		//1.StringBuffer线程安全
//		//2.StringBuilder非线程安全
//		int sum= 10000000;
//		long cur = System.currentTimeMillis();
//		StringBuffer sb = new StringBuffer();
//		for (int i = 0; i < sum; i++) {
//			sb.append(new String("i"));
//		}
//		System.out.println("sb.append(new String('i')),Times:"+(System.currentTimeMillis() - cur));
//		//
//		cur = System.currentTimeMillis();
//		StringBuffer sb2 = new StringBuffer();
//		for (int i = 0; i < sum; i++) {
//			sb2.append("i");
//		}
//		System.out.println("sb.append('i'),Times:"+(System.currentTimeMillis() - cur));
//		//
//		
//		cur = System.currentTimeMillis();
//		StringBuilder sbu = new StringBuilder();
//		for (int i = 0; i < sum; i++) {
//			sbu.append(new String("i"));
//		}
//		System.out.println("Times:"+(System.currentTimeMillis() - cur));
//		//
//		cur = System.currentTimeMillis();
//		StringBuilder sbu2 = new StringBuilder();
//		for (int i = 0; i < sum; i++) {
//			sbu2.append("i");
//		}
//		System.out.println("Times:"+(System.currentTimeMillis() - cur));
	}
}
