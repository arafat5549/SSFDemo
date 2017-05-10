package com.ssf.service;

import java.util.List;

import com.ssf.dao.ReviewDao;
import com.ssf.dao.UserDao;
import com.ssf.model.Review;
import com.ssf.model.User;

/**
 * 
 * @author wyy
 * 2017年4月7日
 *
 */
public class ReviewService {
	
	 ReviewDao reviewDao = new ReviewDao();
	 UserDao userDao =new UserDao();
	 /**
	  *  根据商品id获取所有评论列表（按时间排列）
	  *  
	  *  @param pid 前台传过来
	  */
	public List<Review> findOrderReviewListByProductId(int pid,int orderType){
		List<Review> lists = reviewDao.findOrderReviewListByProductId(pid,orderType);
		for (Review review : lists) {
			User user = userDao.findById(review.getUserId());
			review.setUser(user);
		}
		return lists;
	 }
	 /**
	  * 发表评论
	  */
	public String publish(Review review){
		String text = review.getText();
		if(text!=null && (text.length()<10 ||text.length()>255)){
			return "评论长度必须为10-255之间";
		}
		int pid = review.getProductId();
		if(pid <=0){
			return "无相关商品，无法评论";
		}
		boolean flag = reviewDao.save(review);
		if(!flag){
			return "插入数据库错误";
		}
		return "";
	 }
}
