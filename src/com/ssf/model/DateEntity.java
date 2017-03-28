package com.ssf.model;

import java.util.Date;

/**
 * 时间实体类 任何时间都需要创建时间属性
 * 任何数据表一定要需要创建 创建时间和修改时间 字段
 * 这样有利于数据维护，比如以后可以清楚旧的数据
 * @author wyy
 * 2017年2月22日
 *
 */
public class DateEntity {
	private Date createTime;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	public void preInsert(){
		if(createTime == null)
		  setCreateTime(new Date());
	}

	@Override
	public String toString() {
		return "DateEntity [createTime=" + createTime + "]";
	}
	
	
}
