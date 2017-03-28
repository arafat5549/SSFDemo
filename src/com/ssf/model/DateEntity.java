package com.ssf.model;

import java.util.Date;

/**
 * 时间实体
 * 
 * 每个实体类都要有的创建时间和修改时间
 * 
 * @author wyy
 * 2017年3月28日
 *
 */
public class DateEntity {

	private Date create_time; //datetime
	private Date update_time;
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
	
}
