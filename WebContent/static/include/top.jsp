<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<nav class="top ">
		<a href="${context}">
			<span style="color:#C40000;margin:0px" class=" glyphicon glyphicon-home redColor"></span>
			天猫首页
		</a>	
		
		<span>喵，欢迎来天猫</span>
		
		<c:if test="${!empty session_user}">
			<a href="${context }/user?method=userInfo">${session_user.username}</a>
			<a href="${context }/user?method=logout">退出</a>		
		</c:if>
		
		<c:if test="${empty session_user}">
			<a href="${context }/user?method=login">请登录</a>
			<a href="${context }/user?method=register">免费注册</a>		
		</c:if>


		<span class="pull-right">
			<a href="${context }/order?method=list">我的订单</a>
			<a href="${context }/cart?method=list">
			<span style="color:#C40000;margin:0px" class="glyphicon glyphicon-shopping-cart redColor"></span>
			购物车<strong>${session_cart.cartItemCount}</strong>件</a>		
		</span>
</nav>

<div>
	${msg}
</div>