package com.ssf.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单实体类
 * @author wyy
 * 2017年3月31日
 *
 */
public class Order extends DateEntity{

	private Integer id;
	private String ordercode;//唯一的订单号(为什么我们不用id作为唯一订单号)
	private Integer userId; //可以有多个订单
	
	//所有的商品集合
	private List<OrderItem> items = new ArrayList<OrderItem>();
	
	private String address;//收货地址    
	private String post;   //邮政编号
	private String receiver;//收件人
	private String mobile; //手机
	private String message; //备注
	//待付款-待发货-待收货-待评价
	//枚举类型(常量的集合)
	private String status;//订单状态
	//运维记录时间
	private Date payDate;    //付款时间
	private Date deliverDate;//发货时间
	private Date confirmDate;//确认收货时间
	
	//
	private Integer totalCount;//所有商品的数量
	private BigDecimal total;  //所有商品的价格
	
	
	public Integer getTotalCount() {
		int totalCount = 0;
		for (OrderItem item : items) {
			totalCount+=item.getCount();
		}
		return totalCount;
	}
//	public void setTotalCount(Integer totalCount) {
//		this.totalCount = totalCount;
//	}

	public BigDecimal getTotal() {
		BigDecimal total = new BigDecimal(0);
		for (OrderItem item : items) {
			total = total.add(item.getProduct().getPromotoPrice());
		}
		//System.out.println(total);
		return total;
	}

//	public void setTotal(BigDecimal total) {
//		this.total = total;
//	}

	public enum OrderStatus{
		WAIT_PAY("waitPay"),
		WAIT_DELIVER("waitDeliver"),
		WAIT_CONFIRM("waitConfirm"),
		WAIT_REVIEW("waitReview");
		
		private String status;
		public String getValue(){
			return status;
		}
		OrderStatus(String status){
			this.status = status;
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrdercode() {
		return ordercode;
	}

	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public Date getDeliverDate() {
		return deliverDate;
	}

	public void setDeliverDate(Date deliverDate) {
		this.deliverDate = deliverDate;
	}

	public Date getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	};
	
	
	
	
}
