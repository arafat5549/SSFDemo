package com.ssf.dao;

import java.util.List;

import com.ssf.model.User;
import com.ssf.utils.DBUtils;
/**
 * DataAccessObject(DAO层) # Resposity层
 * 用户DAO接口层 
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
		//
		String sql ="SELECT "+COLUMN+" FROM sys_user a WHERE username=?";		
		System.out.println(sql);
		User exist = DBUtils.getInstance().queryBean(sql,User.class,name);
		return exist;
	}
	/**
	 * 测试-废弃类
	 */
	public User findByName_stmt(String name){
		String sql ="SELECT "+COLUMN+" FROM sys_user a WHERE username='"+name+"'";	
		User exist = DBUtils.getInstance().queryBean_stmt(sql,User.class);
		return exist;
	}
	
	//is 返回的如果是boolean类型就用is开头
	public boolean isInDeptByEmpAndDept(){
		return false;
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
		// TODO Auto-generated method stub
		return null;
	}
}
