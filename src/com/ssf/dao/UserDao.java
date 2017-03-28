package com.ssf.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.ssf.model.User;
import com.ssf.utils.DBUtils;

public class UserDao implements BaseDao<User>{
    //不用通配符来做
	private static  String columns = 
			" a.id, "
			+ "a.username,"
			+ "a.password,"
			+ "a.create_time AS 'createTime'," //数据库命名规范和JavaBean命名规范
			+ "a.avatar_url AS 'avatarUrl' ";
	
	public int getListCount(){
		String sql = "SELECT "+columns+" FROM sys_user a";
		try {
			List<User> lists =DBUtils.getInstance().listBean(sql, User.class);
			return lists.size();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	//2.根据username获取某个对象
		public User findByUserName(String username)
		{
			if(Strings.isNullOrEmpty(username)){
				return null;
			}
			String sql ="SELECT "+columns+" FROM sys_user a WHERE username=?";
			try {
				return DBUtils.getInstance().queryBean(sql, User.class,username);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
	//--------------------------------------------------------
	//1.获取所有对象
	public List<User> findAll(){
		String sql ="SELECT "+columns+" FROM sys_user a";
		try {
			return DBUtils.getInstance().listBean(sql, User.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<User>();
	}
	
	//2.根据id获取某个对象
	public User findById(int id){
		Preconditions.checkState(id>0,"UserDao-findById-id必须大于0");
		
		
		String sql =
				"SELECT "
				+ columns
				+ " FROM sys_user a WHERE id=?";
		try {
			return DBUtils.getInstance().queryBean(sql, User.class,id);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	//3.获取分页对象 limit,offset
	public List<User> listPage(int limit,int offset){
		Preconditions.checkState(limit>0 ,"UserDao-limit必须大于0");
		Preconditions.checkState(offset>0,"UserDao-offset必须大于0");
		
		String sql = "SELECT "+columns+" FROM sys_user a limit "+offset+","+limit+"";
		try {
			return DBUtils.getInstance().listBean(sql, User.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<User>();
	}
	
	//4.save存储对象
	public boolean save(User user){
		//Preconditions.checkNotNull(user,"UserDao插入,user不能为null");
		
		String idStr = "";
		String idHolder ="";
		boolean flag_id = user.getId() > 0;
		if(flag_id){
			idStr = "id,";
			idHolder = "?,";
		}
		
		String sql = 
				"INSERT INTO sys_user"
				+ "("+idStr+"username,password,create_time,avatar_url"
				+ ") VALUES("+idHolder+"?,?,?,?)";
		
		
		user.preInsert();
		
		List<Object> params = new ArrayList<Object>();
		if(flag_id)
			params.add(user.getId());
		params.add(user.getUsername());
		params.add(user.getPassword());
		params.add(user.getCreateTime());
		params.add(user.getAvatarUrl());
		
		try {
			DBUtils.getInstance().executeUpdate2(sql, 
					params
			);
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	//5.修改对象-User
	public void update(User user){
		String sql = "UPDATE sys_user SET username=?,password=?,create_time=?,avatar_url=? WHERE id=?";
		try {
			DBUtils.getInstance().executeUpdate(sql, 
					user.getUsername(),user.getPassword(),
					user.getCreateTime(),user.getAvatarUrl()
					,user.getId());
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//6.删除对象
	public void delete(int id){
		String sql = "DELETE FROM sys_user WHERE id=?";
		try {
			DBUtils.getInstance().executeUpdate(sql, id);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
