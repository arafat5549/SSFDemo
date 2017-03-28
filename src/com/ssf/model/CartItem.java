package com.ssf.model;

/**
 * 购物车项
 * @author wyy
 * 2017年2月24日
 *
 */
public class CartItem extends DateEntity{

	private Product product;
	
	private Integer id;
	private Integer cartId;
	private Integer productId;
	private Integer count;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCartId() {
		return cartId;
	}
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Override
	public String toString() {
		return "CartItem [product=" + product + ", id=" + id + ", cartId="
				+ cartId + ", productId=" + productId + ", count=" + count
				+ ", toString()=" + super.toString() + "]";
	}
	
	
}
