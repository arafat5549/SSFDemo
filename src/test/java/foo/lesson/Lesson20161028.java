package foo.lesson;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据库<p>
 * 
 * 0.什么是数据库?<br>
 * 1.主流数据库有哪些?数据库有哪些分类?<br>
 * 2.JDBC操作?<br>
 * @author wyy
 * 2016年10月28日
 *
 */
public class Lesson20161028 
{
	private static final Logger logger = LoggerFactory.getLogger(Lesson20161028.class); 
	private static final Properties pros = new Properties();
	
	static{
		//存储的系统变量
//		Properties properties = System.getProperties();
//		for(Object s:properties.keySet()){
//			System.out.println(s +" = " + properties.get(s));
//		}
		//
		
		try {
			InputStream is =new FileInputStream(
					System.getProperty("user.dir")+"\\src\\main\\resources"
							+ "\\jdbc.properties");
			pros.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		System.out.println(pros.getProperty("jdbc.driverClassName"));
		
	}
	
	/***
	 * 0.什么是数据库Database？
	 * #有组织 有结构的数据的容器。
	 * 1.主流数据库有哪些？有哪些分类?
	 * 
	 * 关系型数据库:Oracle,Mysql,SqlServer,Sqlite,ProsteSql
	 * NoSql非关系型数据库:MongoDB,Redis/MemCache(键值对缓存)
	 * 
	 * 关系型数据库
	 * -文件存储方式：行列式表(Scheme),(表级锁)
	 * 优点：
	 *  -支持事务,ACID特性
	 *  -支持复杂查询
	 * 缺点：
	 *  -文件结构复杂,可用性灵活性较差
	 *  
	 * 非关系型数据库(文件+索引)
	 * 优点：
	 *  -文件结构简单
	 *  -效率高，高性能
	 *  -可扩展性强，灵活性
	 *  -高可用性
	 * 缺点：
	 *  -数据安全问题，不支持事务
	 * 
	 * 什么时候用到关系型数据库？
	 * - 用户信息 和 金钱
	 * 什么时候用到非关系型数据库？
	 * - 大数据查询
	 * - 对数据安全不敏感的信息
	 * 
	 * - Google05发布的这三篇论文：
	 * - 1.(HFDS)/文档系统
	 * - 2.(map/reduce):
	 * - 3.(多个机子执行的数据整合起来)
	 * 	 
	 *   #
	 *   #Hadoop(Java实现Google三篇论文的大数据框架)
	 *   #Lucene / ElaticSearch
	 *   
	 *   #
	 *  
	 */
	
	/**
	 * JDBC操作 mysql
	 * JavaDataBaseConnectiv/ API用来编程语言和数据库
	 * @throws Exception 
	 */
	private static Connection conn = null;
	static Connection openConnection() throws SQLException
	{ 
		if (null == conn || conn.isClosed()) {
			String driverClass = pros.getProperty("jdbc.driverClassName");
			String url = pros.getProperty("jdbc.url");
			String username = pros.getProperty("jdbc.username");
			String password = pros.getProperty("jdbc.password");
			//1.导入相关数据库的驱动
			try {
				Class.forName(driverClass);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			//2.获得数据库连接
			conn = DriverManager.getConnection(url, username, password);
		}
		return conn;
	}
	//获取结果集数据
	static List<Map<String, Object>> getResultSet(ResultSet rs) throws SQLException{
		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		ResultSetMetaData rsmd = rs.getMetaData();//元数据描述数据本身的结构
		int columnCount = rsmd.getColumnCount();
		while (null != rs && rs.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i = 0; i < columnCount; i++) {
				String name = rsmd.getColumnName(i + 1);
				Object value = rs.getObject(name);
				map.put(name, value);
			}
			lists.add(map);
		}
		//System.out.println(lists);
		return lists;
	}
	//参数1：数据库连接 ，参数2：SQL语句，参数：可变参数
	static List<Map<String, Object>> queryMapList(Connection conn,String sql, Object... params)
	throws SQLException, InstantiationException, IllegalAccessException
	{
		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		try {
			preStmt = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++)
				preStmt.setObject(i + 1, params[i]);// 下标从1开始
			rs = preStmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while (null != rs && rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 0; i < columnCount; i++) {
					String name = rsmd.getColumnName(i + 1);
					Object value = rs.getObject(name);
					map.put(name, value);
				}
				lists.add(map);
			}
		} finally {
			if (null != rs)
				rs.close();
			if (null != preStmt)
				preStmt.close();
		}
		return lists;
	}
	
	/**
	 * PreparedStatement和Statement区别
	 * 
	 * 预处理语句
	 * 1.可以被缓存 (数据库连接池可以支持PSCache缓存)
	 * 2.参数化查询  (占位符参数只能是一个数值)
	 * 3.提高性能 尽量使用参数化查询
	 * 4.防止Sql注入(严重的安全性问题)
	 * 
	 * 一句话建议：使用PreparedStatement，并且使用参数化查询
	 * @throws SQLException 
	 */
	@Test
	public void PreparedStatementTest() throws SQLException
	{
		//数据库拼接SQL
		String username = "1' OR '1'='1";//"Beck";
//		String sql_stmt = "SELECT * FROM tb_user "
//				+ "WHERE username = '"+username+"';";
		String sql_ps = "SELECT * FROM tb_user WHERE username = ?;";
		//sql_ps = "SELECT * FROM tb_user WHERE username IN(?);";
		
		//System.out.println(sql_stmt);
		Connection conn = openConnection();
		PreparedStatement ps = conn.prepareStatement(sql_ps);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		List<Map<String, Object>> lists = getResultSet(rs);
		System.out.println(lists);
	}
	/**
	 * 要求：能自己写出来
	 * 1.读取数据库配置
	 * 2.导入数据库驱动 以mysql为例子(mysql-java-connector.jar)
	 * 3.获取数据库连接
	 * 4.执行SQL语句
	 * 5.获取ResultSet集合数据
	 */
	@Test
	public void JTest() throws ClassNotFoundException, SQLException
	{
		String driverClass = pros.getProperty("jdbc.driverClassName");
		String url = pros.getProperty("jdbc.url");
		String username = pros.getProperty("jdbc.username");
		String password = pros.getProperty("jdbc.password");
		//1.获取数据库连接
		Class.forName(driverClass);
		Connection connection = DriverManager.getConnection(url, username, password);
		System.out.println(connection);
		
		String sql = "SELECT * FROM tb_user WHERE username=?;";
		//2.执行SQL语句
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, "Beck");

		ResultSet rs= ps.executeQuery();
		//3.获取ResultSet集合数据
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		ResultSetMetaData rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
		while (null!=rs && rs.next()) {//0
			Map<String, Object> map = new HashMap<String, Object>();
			for(int i=0;i<count;i++){
				String name  = rsmd.getColumnName(i+1);
				Object value = rs.getObject(name);
				map.put(name, value);
				System.out.print(name+"="+value+",");
			} 
			lists.add(map);
			System.out.println();
		}
		//
		System.out.println(lists.size()+","+lists);
		
		rs.close();
		ps.close();
		connection.close();
	}
	/**
	 * 基本Sql操作
	 */
	//1.插入测试数据，生成数据库结构
	static void insetTestData() throws SQLException, IOException{
		Connection conn = openConnection();
		Reader reader = null;
		ScriptRunner scriptRunner = new ScriptRunner(conn);
		reader = Resources.getResourceAsReader("sql/SqlTest.sql");
		scriptRunner.runScript(reader);
		logger.info("SqlTest.sql executed successfully");
		reader = Resources.getResourceAsReader("sql/SqlTestData.sql");
		scriptRunner.runScript(reader );
		logger.info("SqlTestData.sql executed successfully");
	}
	//1.
	@Test
	public void SqlTest() throws ClassNotFoundException, SQLException, IOException, InstantiationException, IllegalAccessException{
		Connection connection = openConnection();
		//索引所有列
		String sql  = "SELECT * FROM tb_user "
				+ "WHERE username=?;";
		//索引指定列
		String sql2 = "SELECT uid,username FROM tb_user "
				+ "WHERE username=?;";
		//排序
		String sql3 = "SELECT uid,username,code FROM tb_user "
				+ "WHERE username=? ORDER BY code DESC;";//AEC
		//
		String sql4 = "SELECT * FROM tb_user WHERE username=? or username=? ";
		String sql5 = "SELECT * FROM tb_user WHERE username IN(?,?);";
		
		
		//String test = "'Beck','Kennedy'"; 
		//String sql6 = "SELECT * FROM tb_user WHERE username IN("+test+")"; 

//		sql注入的方式：
//		String inject = "1' OR '1' ='1";//
//		String sql7 = "SELECT * FROM tb_user WHERE username='"+inject+"'";
//		System.out.println(sql7);
		//SELECT * FROM tb_user WHERE username IN('Beck','Kennedy');
		
		//多表查询
		//表结构
		//学生表#Student(SNO,Sname,Sage,Ssex)  
		//课程表 #Course(CNO,Cname,TNO)  
		//成绩表#SC(SNO,CNO,score)  
		//教师表#Teacher(TNO,Tname) 
		
        //--（1）查询“001”课程比“002”课程成绩高的所有学生的学号；
		String sql8 =
		 "select a.SNO,a.Score "+
		 "from SC a,SC b "+
		 "where a.SNO=b.SNO "+
		 "and a.CNO=1 and b.CNO=2 and a.score > b.score;";
		//--（2） 查询平均成绩大于60分的同学的学号和平均成绩；
		
		List<Map<String, Object>> lists 
		= queryMapList(connection,sql8);
		System.out.println(lists.size()+","+lists);
	}
}
