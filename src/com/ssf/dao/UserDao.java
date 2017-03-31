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
	private static final String COLUMNS = 
			  " a.id,"
			+ " a.username,"
			+ " a.password ";
	       //+ a.dept_id AS 'deptId'
	
	
	/**
	 * 根据名称查找用户
	 */
	public User findByName(String name){
		String sql ="SELECT "+COLUMNS+" FROM sys_user a WHERE username=?";		
		User exist = DBUtils.getInstance().queryBean(sql,User.class,name);
		return exist;
	}
	/**
	 * 测试-废弃类
	 * 根据名称查找用户
	 */
	public User test_findByName_stmt(String name){
		String sql ="SELECT "+COLUMNS+" FROM sys_user a WHERE username='"+name+"'";	
		User exist = DBUtils.getInstance().queryBean_stmt(sql,User.class);
		return exist;
	}
	
	
	@Override
	public List<User> findAll() {
		String sql ="SELECT "+COLUMNS+" FROM sys_user a";		
		return DBUtils.getInstance().listBean(sql,User.class);
	}

	@Override
	public boolean save(User t) {
		String sql = "INSERT INTO sys_user(username,password,create_time,update_time) VALUES (?,?,?,?)";
		t.preInsert();
		return DBUtils.getInstance().execute(
				sql, 
				t.getUsername(),
				t.getPassword(),
				t.getCreateTime(),
				t.getUpdateTime());
		
	}

	@Override
	public void update(User t) {
		String sql = "UPDATE sys_user SET "
				+ "username=?,password=?create_time=?,update_time=? "
				+ " WHERE id=?";
		 t.preUpdate();
		 DBUtils.getInstance().execute(sql, 
					t.getUsername(),
					t.getPassword(),
					t.getCreateTime(),
					t.getUpdateTime(),
					t.getId());
	}

	@Override
	public void delete(Integer id) {
		String sql = "DELETE FROM sys_user WHERE id=?";
		DBUtils.getInstance().execute(sql, id);
	}
	@Override
	public User findById(Integer id) {
		String sql ="SELECT " + COLUMNS +" FROM sys_user a WHERE a.id=?";
		return DBUtils.getInstance().queryBean(sql, User.class,id);
	}
	@Override
	public Integer findMaxId() {
		return null;
	}
}
