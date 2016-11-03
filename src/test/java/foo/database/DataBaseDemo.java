package foo.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.bson.Document;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;



/**
 * TODO 数据库操作相关的Demo<p>
 * 
 * 
 * #1.JDBC操作
 * #2.Sql语句【Sql必学必会】
 * #3.NoSql(MongoDb - Redis)
 * #4.常用数据库配置
 * #5.Mysql调优
 * #6.Sql注入
 * 
 * #JDBC事务  propagation事务传播机制
 * #ORM框架(Mybaits - Hibernate4)
 * @author wyy
 * 2016年10月26日
 *
 */
public class DataBaseDemo 
{
	private Logger logger = LoggerFactory.getLogger(DataBaseDemo.class); 
	
	private static final Properties PROPERTIES = new Properties();
	
	static
	{
		try {
			InputStream is = new FileInputStream(System.getProperty("user.dir")
					+"\\src\\main\\resources\\jdbc.properties");
			PROPERTIES.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * JDBC操作
	 * JavaDatabaseConnectivity
	 * 为编程语言和数据库连接的一套API
	 * 
	 * 基本数据库操作
	 * @throws Exception 
	 * 
	 */
	static Connection getConnection() throws Exception{
		final String driverClass = PROPERTIES.getProperty("jdbc.driverClassName");
		final String url = PROPERTIES.getProperty("jdbc.url");
		final String username = PROPERTIES.getProperty("jdbc.username");
		final String password   = PROPERTIES.getProperty("jdbc.password");
		System.out.println("Connecting to database...");
		//STEP 1.加载数据库驱动
		Class.forName(driverClass);
		//STEP 2.创建数据库连接		
		Connection conn = DriverManager.getConnection(url,username,password); 
		System.out.println(conn);
		return conn;
	}
	static List<Map<String, Object>> getResultSet(ResultSet rs) throws Exception{
		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
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
		System.out.println(lists.size()+","+lists);
		return lists;
	}
	@Test
	public void JDBCTest() throws Exception
	{
		
		Connection conn = getConnection();
		//STEP 3:执行查询
		String sql = "SELECT * FROM tb_user";
		System.out.println("Creating statement...");
		ResultSet rs = null;
		//#1、执行静态SQL语句。通常通过Statement实例实现。   
		//Statement stmt = conn.createStatement();
		//rs = stmt.executeQuery(sql);
	    //#2、执行动态SQL语句。通常通过PreparedStatement实例实现。  
		PreparedStatement ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
	    //#3、执行数据库存储过程。通常通过CallableStatement实例实现
		
		//STEP 4:从结果集遍历查询结果
		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
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
		System.out.println(lists);
		
		rs.close();
		ps.close();
		conn.close();
	}

	/**
	 * Statement和PreparedStatement的区别
	 * 预处理语句的优势
	 * 1.PreparedStatement可以写动态参数化的查询
	 * -SELECT * FROM tb_user WHERE username=?
	 * 2.PreparedStatement比 Statement 更快
	 * -为了获得性能上的优势，应该使用参数化sql查询而不是字符串追加的方式。
	 * 3.PreparedStatement可以防止SQL注入式攻击
	 * -PreparedStatement不允许一个占位符（？）有多个值
	 * 
	 * 预处理语句的缺点
	 * 1. 为了防止SQL注入攻击，PreparedStatement不允许一个占位符（？）有多个值，在执行有**IN**子句查询的时候这个
	 * 问题变得棘手起来。下面这个SQL查询使用PreparedStatement就不会返回任何结果
	 * -SELECT * FROM tb_user WHERE username IN (?)
	*  -preparedSatement.setString(1, "'personal loan', 'home loan', 'gold loan'");
	 */
	@Test
	public void PreparedStatementTest() throws Exception
	{
		Connection conn = getConnection();//(uid,username,password,email,code,state)
		PreparedStatement ps = null;
		//1.典型的Sql注入
		String userName = "1' OR '1'='1";
		String passWord = "1' OR '1'='1";
		String sqlInject = "SELECT * FROM tb_user WHERE username = '" + userName + "' and password = '"+ passWord +"';";
		ps = conn.prepareStatement(sqlInject);
		Statement stmt = conn.createStatement();
		System.out.println(sqlInject);
		ResultSet rs = ps.executeQuery(sqlInject);//stmt.executeQuery(sqlInject);//
		getResultSet(rs);
		//1.插入测试数据
		String sql = "INSERT INTO tb_user values(?,?,?,?,?,?)";
		ps = conn.prepareStatement(sql);
		List<String> lists = IOUtils.readLines(new FileInputStream("F:\\data.csv"));
		for (String s : lists) {
			//1.字符串拼接方式
//			StringBuffer sqlsb = new StringBuffer();
//			sqlsb.append("INSERT INTO tb_user values(");
//			String array[] = s.split(",");
//			sqlsb.append("'"+UUID.randomUUID().toString()+"',");
//			for (String xs : array) {
//				sqlsb.append("'"+xs+"',");
//			}
//			sqlsb.append(1+")");
//			System.out.println(sqlsb);
//			ps.execute(sqlsb.toString());
			//2.占位符方式
//			int c = 1;
//			ps.setString(c++, UUID.randomUUID().toString());
//			for (String xs : array) {
//				ps.setString(c++, xs);
//			}
//			ps.setInt(c++, 0);
//			ps.execute();
		}
	}  
	/**
	 * 
	 * @param args
	 */
	@Test
	public void MongoDBTest()
	{
//        //连接到MongoDB服务 如果是远程连接可以替换“localhost”为服务器所在IP地址  
//        //ServerAddress()两个参数分别为 服务器地址 和 端口  
//        ServerAddress serverAddress = new ServerAddress("localhost",27017);  
//        List<ServerAddress> addrs = new ArrayList<ServerAddress>();  
//        addrs.add(serverAddress);  
//          
//        //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码  
//        MongoCredential credential = MongoCredential.createScramSha1Credential("root", "sff", "root".toCharArray());  
//        List<MongoCredential> credentials = new ArrayList<MongoCredential>();  
//        credentials.add(credential); 
		
        // 连接到 mongodb 服务
       MongoClient mongoClient = new MongoClient("localhost" , 27017 );
       // 连接到数据库
       MongoDatabase mongoDatabase = mongoClient.getDatabase("sff");  
       System.out.println("Connect to database successfully"+mongoDatabase);
       //mongoDatabase.createCollection("test");
       
       MongoCollection<Document> collection = mongoDatabase.getCollection("test");
       System.out.println("集合 test 选择成功"+collection);
       
       //插入文档  
       /** 
       * 1. 创建文档 org.bson.Document 参数为key-value的格式 
       * 2. 创建文档集合List<Document> 
       * 3. 将文档集合插入数据库集合中 mongoCollection.insertMany(List<Document>) 插入单个文档可以用 mongoCollection.insertOne(Document) 
       * */
       Document document = new Document("title", "MongoDB").  
       append("description", "database").  
       append("likes", 100).  
       append("by", "Fly");  
       List<Document> documents = new ArrayList<Document>();  
       documents.add(document);  
       collection.insertMany(documents);  
       System.out.println("文档插入成功"); 
	}
	//db.createUser({user:"root",pwd:"root",roles:[]}) 
	/**
	 * SQL练习
	 * 	//表结构
	 * 	//#Student(SNO,Sname,Sage,Ssex)  学生表
		//#Course(CNO,Cname,TNO)  课程表 
		//#SC(SNO,CNO,score)  成绩表
		//#Teacher(TNO,Tname) 教师表
		
	 * 
	 * @param args
	 * @throws Exception 
	 */
	
	//1.准备测试数据
	@Test
	public void RunScriptDemo() throws Exception{
		Connection conn = getConnection();
		Reader reader = null;
		ScriptRunner scriptRunner = new ScriptRunner(conn);
		reader = Resources.getResourceAsReader("sql/SqlTest.sql");
		scriptRunner.runScript(reader);
		logger.info("SqlTest.sql executed successfully");
		reader = Resources.getResourceAsReader("sql/SqlTestData.sql");
		scriptRunner.runScript(reader );
		logger.info("SqlTestData.sql executed successfully");
	}
	//2.SQL试题练习
	@Test
	public void SQLTestDemo()
	{
		//（1）查询“001”课程比“002”课程成绩高的所有学生的学号；
		//（2） 查询平均成绩大于60分的同学的学号和平均成绩；
		//
		//
	}
	
	
	public static void main(String[] args) 
	{
		
	}
}
