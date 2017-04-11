package com.ssf.model;

import java.math.BigDecimal;

/**
 * 商品实体类
 * 
 * @author wyy
 * 2017年3月28日
 *
 */
public class Product extends DateEntity{

	private Integer id;
	private String name;
	private String subTitle;
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	private BigDecimal originPrice; //原始价格
	private BigDecimal promotoPrice;//优惠价格
	private Integer stock;          //库存
	private Integer categoryId;    //分类id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getOriginPrice() {
		return originPrice;
	}
	public void setOriginPrice(BigDecimal originPrice) {
		this.originPrice = originPrice;
	}
	public BigDecimal getPromotoPrice() {
		return promotoPrice;
	}
	public void setPromotoPrice(BigDecimal promotoPrice) {
		this.promotoPrice = promotoPrice;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", originPrice="
				+ originPrice + ", promotoPrice=" + promotoPrice + ", stock="
				+ stock + ", categoryId=" + categoryId + ", getCreateTime()="
				+ getCreateTime() + ", getUpdateTime()=" + getUpdateTime()
				+ "]";
	}
	
	
	//其他属性
	//商品描述
	//商品图片
	
	
}
