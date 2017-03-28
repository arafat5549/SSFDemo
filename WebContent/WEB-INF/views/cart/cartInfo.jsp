<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/static/taglib.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style type="text/css">
* {
		font-size: 11pt;
	}
	div {
		margin:20px;
		border: solid 2px gray;
		width: 150px;
		height: 150px;
		text-align: center;
	}
	li {
		margin: 10px;
	}
	
	#buy {
		background: url('${context}/static/images/ui_sprites.png') no-repeat;
		display: inline-block;
		
		background-position: 0 -902px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
	#buy:HOVER {
		background: url('${context}/static/images/ui_sprites.png') no-repeat;
		display: inline-block;
		
		background-position: 0 -938px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
</style>

<title>旧版本-购物车</title>
</head>
<body>

<h1>购物车</h1>
<c:choose>
	<%-- 如果没有车，或车的内容集合为0长 --%>
	<c:when test="${empty sessionScope.session_cart or fn:length(sessionScope.session_cart.items) eq 0}">
		<img src="${context }/static/images/cart.png" width="300"/>
	</c:when>
<c:otherwise>
<table border="1" width="100%" cellspacing="0" background="black">
	<tr>
		<td colspan="7" align="right" style="font-size: 15pt; font-weight: 900">
			<a href="${context }/cart?method=clear">清空购物车</a>
		</td>
	</tr>
	<tr>
		<th>书名</th>
		<th>单价</th>
		<th>数量</th>
		<th>小计</th>
		<th>操作</th>
	</tr>

<c:forEach items="${sessionScope.session_cart.items }" var="item">
	<tr>
		<td>${item.product.name }</td>
		<td>${item.product.price }元</td>
		<td>${item.count }</td>
		<td>${item.product.price * item.count}元</td>
		<td><a href="${context }/cart?method=delete&itemid=${item.id }">删除</a></td>
	</tr>
</c:forEach>

	<tr>
		<td colspan="7" align="right" style="font-size: 15pt; font-weight: 900">
			合计：0 元
		</td>
	</tr>
	
	<tr>
		<td colspan="7" align="right" style="font-size: 15pt; font-weight: 900">
			<a id="buy" href="${context}/order?method=add"/>
		</td>
	</tr>
</table>
	</c:otherwise>
</c:choose>
	
</body>
</html>