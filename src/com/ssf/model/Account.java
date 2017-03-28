package com.ssf.model;

/**
 * 账户信息[未完成]
 * 
 * @author wyy
 * 2017年2月23日
 *
 */
public class Account extends DateEntity{

	private Integer id;
	private String aname;
	private Double balance;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
}
