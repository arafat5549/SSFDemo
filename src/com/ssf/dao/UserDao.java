package com.ssf.dao;

import java.util.List;

import com.ssf.model.User;
import com.ssf.utils.DBUtils;
/**
 * 用户DAO接口层  <br>
 * (DAO即 DataAccessObject 用户访问对象)又叫 Resposity层，是用来处理 和数据库交互的
 * 一般在该行编写SQL语句
 * @author wyy
 * 2017年3月23日
 *
 */
public class UserDao implements BaseDao<User>{
	//注意事项：数据库不要使用*
	//1.节省了数据库解析的过程
	//2.只会获取我需要的字段
	private static final String COLUMN = 
			  " a.id,"
			+ " a.username,"
			+ " a.password ";
	       //+ a.dept_id AS 'deptId'
	
	
	/**
	 * 根据名称查找用户
	 */
	public User findByName(String name){
		String sql ="SELECT "+COLUMN+" FROM sys_user a WHERE username=?";		
		System.out.println(sql);
		User exist = DBUtils.getInstance().queryBean(sql,User.class,name);
		return exist;
	}
	/**
	 * 测试-废弃类
	 * 根据名称查找用户
	 */
	public User test_findByName_stmt(String name){
		String sql ="SELECT "+COLUMN+" FROM sys_user a WHERE username='"+name+"'";	
		User exist = DBUtils.getInstance().queryBean_stmt(sql,User.class);
		return exist;
	}
	
	
	@Override
	public List<User> findAll() {
		return null;
	}

	@Override
	public boolean save(User t) {
		String sql = "INSERT INTO sys_user(username,password) VALUES (?,?)";
		return DBUtils.getInstance().execute(sql, 
				t.getUsername(),
				t.getPassword());
		
	}

	@Override
	public void update(User t) {
		
	}

	@Override
	public void delete(Integer id) {
		
	}
	@Override
	public User findById(Integer id) {
		return null;
	}
}
