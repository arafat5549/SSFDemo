package foo.lesson;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 数据库连接池的简单实现<br>
 * 缓存的简单实现<br>
 * 
 * 数据库的缓存一般key值为sql语句，value值为你的执行完sql的返回结果集
 * 1.
 * 
 * 参数验证
 * 
 * 后台参数验证框架 HibernateValidation
 * 前端验证框架Jquery-Validation
 * 
 * @author wyy
 * 2016年11月29日
 *
 */
public class Lesson20161129 
{
	/**
	 *  HibernateValidation验证框架
	 *  
	 *  1.参数验证是一个很常用的功能（防止你往数据库写入错误信息）
	 *  2.往前台推送错误信息
	 *  
	 *  如何使用验证框架：
	 *  #1.导入依赖包
	 *  #2.与Spring整合
	 *  
	 *  Bean Validation 中内置的 constraint       
		@Null   被注释的元素必须为 null       
		@NotNull    被注释的元素必须不为 null       
		@AssertTrue     被注释的元素必须为 true       
		@AssertFalse    被注释的元素必须为 false       
		@Min(value)     被注释的元素必须是一个数字，其值必须大于等于指定的最小值       
		@Max(value)     被注释的元素必须是一个数字，其值必须小于等于指定的最大值       
		@DecimalMin(value)  被注释的元素必须是一个数字，其值必须大于等于指定的最小值       
		@DecimalMax(value)  被注释的元素必须是一个数字，其值必须小于等于指定的最大值       
		@Size(max=, min=)   被注释的元素的大小必须在指定的范围内       
		@Digits (integer, fraction)     被注释的元素必须是一个数字，其值必须在可接受的范围内       
		@Past   被注释的元素必须是一个过去的日期       
		@Future     被注释的元素必须是一个将来的日期       
		@Pattern(regex=,flag=)  被注释的元素必须符合指定的正则表达式 
		      
		Hibernate Validator 附加的 constraint       
		@NotBlank(message =)   验证字符串非null，且长度必须大于0       
		@Email  被注释的元素必须是电子邮箱地址       
		@Length(min=,max=)  被注释的字符串的大小必须在指定的范围内       
		@NotEmpty   被注释的字符串的必须非空       
		@Range(min=,max=,message=)  被注释的元素必须在合适的范围内
	 *  
	 */
	
	//1.缓存的过程（本质上是HashMap<String,Object>）
	//#1.数据库的缓存一般key值为sql语句，value值为你的执行完sql的返回结果集
	//#2.你要执行sql语句之前，你先去缓存里面寻找
	//#2-1.如果缓存没有的话，你要执行这条sql语句，并且缓存你的结果集 key值就是这条SQL语句
	//#2-2.如果缓存有的话，直接返回结果集
	
	//2.数据库连接池（本质上List<Connection>）
	//#1.创建指定大小的List<Connection>对象
	//#2.每个申请我就分配给他一个Connection对象
	//#3.执行完再把连接对象返回给连接池
	//#4.如果连接池为空，（对象都被占用)返回的连接就为null，你需要等待别人释放连接
	
	
	//连接池和缓存的交互
	public static void main(String[] args) {
		Connection con = null;
		ResultSet rs =  null;//需要缓存的结果集
		String sql = "select * from user";
		//1.先去缓存里面找连接对象
		CacheImpl cacheImpl = CacheImpl.getInstance();
		Object object = cacheImpl.getObject(sql);
		
		ConnectionPoolImpl poolImpl = ConnectionPoolImpl.getInstance();
		//2-1.如果没有的话，执行sql语句 ，缓存结果集
		if(object == null){
			con = poolImpl.getConnection();
			try {
				Statement state = con.createStatement();
				rs = state.executeQuery(sql);
				cacheImpl.addToCache(sql, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//2-2缓存不为空 说明缓存里面已经有了，直接获取结果集
		else{
			rs = (ResultSet)object;
		}
		
		
			try {
				while(rs.next()){
				System.out.println(rs.getString("username"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		poolImpl.release(con);
		
		
		
//		URL url = Lesson20161129.class.getClassLoader().getResource("jdbc.properties");
//		System.out.println(url)
//		ResourceBundle bundle = ResourceBundle.getBundle("jdbc.properties");
//	    System.out.println(bundle);
	} 
}

//缓存的简单实现
//
class CacheImpl{
	private Map<String, Object> cache= null;
	private static CacheImpl cacheImpl = new CacheImpl();
	private CacheImpl(){
		cache = new HashMap<String, Object>();
		
	}
	public static CacheImpl getInstance(){
		return cacheImpl;
	}
	public void addToCache(String key,Object obj){
		cache.put(key, obj);
	}
	
	public Object getObject(String key){
		Object object = cache.get(key);
		if(object!=null){
			return object;
		}
		return null;
	}

}


//.数据库连接池实现类  - 怎么读取properties文件
//数据库连接的List集合
class ConnectionPoolImpl{
	
	private static Properties p = new Properties();
	static{
		InputStream is = Lesson20161129.class.getClassLoader().getResourceAsStream("jdbc.properties");
		try {
			p.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	private List<Connection> poolList = null;
	//int initSize = 1;//初始连接1
	private int maxSize = 10;//最大连接数
	private static ConnectionPoolImpl poolImpl = new ConnectionPoolImpl();
	private Connection conn;
	
	private ConnectionPoolImpl(){
		maxSize = 10;
		poolList = new ArrayList<Connection>();
		
	}
	public static ConnectionPoolImpl getInstance(){
		return poolImpl;
	}
	//创建数据库连接
	public void createConnection(){
		int size = maxSize;
		String url = p.getProperty("jdbc.url");
		String user = p.getProperty("jdbc.username");
		String password = p.getProperty("jdbc.password");
		
		for (int i = 0; i < size; i++) {
			try {
				Class.forName(p.getProperty("jdbc.driverClassName"));
				Connection conn = DriverManager.getConnection(url, user, password);
				poolList.add(conn);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		
	}
	//
	public synchronized Connection getConnection(){
		if(poolList.size()>0){
				Connection con = poolList.get(0);
				poolList.remove(con);
				return con;
		}
		return null;
	}
	public synchronized void release(Connection con){
		poolList.add(con);
	}
	//
	public synchronized void closePool(){
		int k = poolList.size();
		for(int i=0;i<k;i++){
			try {
				
				conn = (Connection) poolList.get(0);
				conn.close();
				poolList.remove(0);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
