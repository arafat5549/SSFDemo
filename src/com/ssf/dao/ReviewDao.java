package com.ssf.dao;

import java.util.List;

import com.ssf.model.Review;
import com.ssf.utils.DBUtils;

/**
 * 
 * @author wyy
 * 2017年4月7日
 *
 */
public class ReviewDao implements BaseDao<Review>{

	private static final String COLUMNS =
			" a.id,"
		   + "a.text,"
		   + " a.product_id AS 'productId',"
	       + " a.user_id AS 'userId',"
	       + " a.create_time AS 'createTime', "
	       + " a.update_time AS 'updateTime' ";
	
	 /**
	  *  根据商品id获取所有评论列表（按时间排列）
	  */
	public List<Review> findOrderReviewListByProductId(int pid,int orderType){
		 String sql = "SELECT " +  COLUMNS +" FROM sys_review a "
		 		+ "WHERE a.product_id=? "
		 		+ "ORDER BY a.create_time desc"; 
		return DBUtils.getInstance().listBean(sql, Review.class, pid);
	 }
	
	
	@Override
	public List<Review> findAll() {
		return null;
	}

	@Override
	public Review findById(Integer id) {
		return null;
	}

	@Override
	public boolean save(Review t) {
		String sql = "INSERT INTO sys_review(text,product_id,user_id,create_time,update_time) "
				+ "VALUES(?,?,?,?,?)";
		t.preInsert();
		return DBUtils.getInstance().execute(sql, 
				t.getText(),
				t.getProductId(),
				t.getUserId(),
				t.getCreateTime(),
				t.getUpdateTime());
	}

	@Override
	public void update(Review t) {
		
	}

	@Override
	public void delete(Integer id) {
		
	}

	@Override
	public Integer findMaxId() {
		return null;
	}

}
