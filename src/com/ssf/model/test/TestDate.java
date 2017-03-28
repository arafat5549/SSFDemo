package com.ssf.model.test;

/**
 * 测试时间model
 * 
 * ORM框架
 * 
 * @author wyy
 * 2017年2月21日
 *
 */
public class TestDate {
     private int id;
     private java.util.Date s_date;
     private long s_time;
     private long s_timestamp;
     private java.util.Date s_datetime;
     
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public java.util.Date getS_date() {
		return s_date;
	}
	public void setS_date(java.util.Date s_date) {
		this.s_date = s_date;
	}
	public long getS_time() {
		return s_time;
	}
	public void setS_time(long s_time) {
		this.s_time = s_time;
	}
	public long getS_timestamp() {
		return s_timestamp;
	}
	public void setS_timestamp(long s_timestamp) {
		this.s_timestamp = s_timestamp;
	}
	public java.util.Date getS_datetime() {
		return s_datetime;
	}
	public void setS_datetime(java.util.Date s_datetime) {
		this.s_datetime = s_datetime;
	}
	@Override
	public String toString() {
		return "TestDate [id=" + id + ", s_date=" + s_date + ", s_time="
				+ s_time + ", s_timestamp=" + s_timestamp + ", s_datetime="
				+ s_datetime + "]";
	}
     
     
}
