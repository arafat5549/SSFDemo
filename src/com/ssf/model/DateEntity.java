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

	private Date createTime; //创建时间
	private Date updateTime; //更新时间
	
	
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 插入save操作前需要调用这方法，生成创建时间
	 */
	public void preInsert(){
		if(createTime == null){
			createTime = new Date();
		}
		if(updateTime == null){
			updateTime = new Date();
		}
	}
	/**
	 * 更新Update操作前需要调用这方法，生成创建时间
	 */
	public void preUpdate(){
//		if(createTime == null){
//			createTime = new Date();
//		}
		if(updateTime == null){
			updateTime = new Date();
		}
	}
}
