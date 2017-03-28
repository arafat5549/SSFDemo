<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/taglib.jsp" %> 

<c:forEach items="${productList }" var="product">
		<div class="productItem" >
			<a href="foreproduct?pid=1">
			<img width="100px" src="${context }/static/img/productSingle_middle/19.jpg"></a>
			<a class="productItemDescLink" href="foreproduct?pid1">
				<span class="productItemDesc">
				[热销]夏普
				</span>
			</a>
			<span class="productPrice">
				¥12.0
			</span>

			<a class="productAddCart" target="_blank" href="">
        		<span style="color:#C40000;margin:0px" class="glyphicon glyphicon-shopping-cart redColor"></span>
        		<strong>加入购物车</strong>
			</a>
		</div>
</c:forEach>