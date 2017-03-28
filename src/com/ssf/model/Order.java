package com.ssf.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单对象
 * @author wyy
 * 2017年2月28日
 *
 */
public class Order extends DateEntity{
	private Integer id;
	private String ordercode;
	private String address;
	private String receiver;
	private String mobile;
	private String post;
	private String message;
	
	private String status;
	private Date payDate;
	private Date deliveryDate;
	private Date confirmDate;
	private Integer userId;
	
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();
	private Integer totalCount;
	private BigDecimal total;
	
	public BigDecimal getTotal() {
		Float total =0.0f;
		for (OrderItem i : orderItems) {
			total += i.getProduct().getPromotePrice().floatValue();
		}
		return new BigDecimal(total);
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public Integer getTotalCount() {
		int total = 0;
		for (OrderItem i : orderItems) {
			total += i.getCount();
		}
		return total;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	//"waitPay","waitDelivery","waitConfirm","waitReview"
	//枚举 订单状态
	public enum orderStatus{
		//STATUS_NONE("",0),
		STATUS_WAITPAY("waitPay",1),//等待付款
		STATUS_WAITDELIVERY("waitDelivery",2),//等待发货
		STATUS_WAITCONFIRM("waitConfirm",3),//等待确认收货
		STATUS_WAITREVIEW("waitReview",4),//等待评价
		STATUS_SUCCESS("success",5), //订单成功
		STATUS_CANCLE("cancle",6)    //取消订单
		;
		String status ="";
		int flag = 0;
		private orderStatus(String status,int flag){
			this.status = status;
			this.flag = flag;
		}
		public String getValue(){
			return status;
		}
		public int getFlag(){
			return flag;
		}
	}
	
	public Integer getId() {
		//String s = orderStatus.STATUS_WAITCONFIRM.status;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
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
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public Date getConfirmDate() {
		return confirmDate;
	}
	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
}
