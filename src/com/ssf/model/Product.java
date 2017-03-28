package com.ssf.model;

import java.math.BigDecimal;




class pp{
	 //一旦你重写了你的构造器 ，你的无参构造器就没有了如果手动创建一个
//	 //无参构造器
//	 public Product(){
//		 System.out.println("调用Product的构造器");
//	 }
//	 //有参的构造器,
//	 public Product(int id){
//		  this.id =id;
//	 }
//	 
//	 //成员方法，成员方法有返回值
//	 public final void Product(){}
//	 //静态变量
//	 protected static int s_var;
//	 //成员变量
//	 protected int var;	
}

//1.带public的才是入口类,入口类只能有一份
//2.类怎么保证初始化 ，默认会调用无参构造器construct

/**
 * 商品实体类
 * 
 * @author wyy
 * 2017年2月15日
 *
 */
public class Product extends DateEntity{
     private Integer id;
     private String name;
     private String subTitle;
     private BigDecimal price;
     private BigDecimal promotePrice;
     private Integer stock;
     private Integer categoryId;//分类id catgoryId
    
     private Integer saleCount;
     private Integer reviewCount;
     private Category category;
     
     public Integer getSaleCount() {
		return saleCount;
	}
	public void setSaleCount(Integer saleCount) {
		this.saleCount = saleCount;
	}
	public Integer getReviewCount() {
		return reviewCount;
	}
	public void setReviewCount(Integer reviewCount) {
		this.reviewCount = reviewCount;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public BigDecimal getPromotePrice() {
		return promotePrice;
	}
	public void setPromotePrice(BigDecimal promotePrice) {
		this.promotePrice = promotePrice;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
     
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
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
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price
				+ ", catgoryId=" + categoryId + ", category=" + category
				+ ", toString()=" + super.toString() + "]";
	}
	
     
     
}
