<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/static/taglib.jsp" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/static/head.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
</head>
<body>
<div class="navitagorDiv">
	<nav class="navbar navbar-default navbar-fixed-top navbar-inverse">
		<img style="margin-left:10px;margin-right:0px" class="pull-left" src="${contextStatic }/img/site/tmallbuy.png" height="45px">
		<a class="navbar-brand" href="#nowhere">天猫后台</a>
		
		<a class="navbar-brand" href="admin_listCategory.html">分类管理</a>
		<a class="navbar-brand" href="admin_listUser.html">用户管理</a>
		<a class="navbar-brand" href="admin_listOrder.html">订单管理</a>
	</nav>
</div>


<div class="workingArea">
	<h1 class="label label-info" >用户管理</h1>

	<br>
	<br>
	
	<div class="listDataTableDiv">
		<table class="table table-striped table-bordered table-hover  table-condensed">
			<thead>
				<tr class="success">
					<th>ID</th>
					<th>用户名称</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${userlist}" var="u">
				<tr>
					<td>${u.id}</td>
					<td>${u.username}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	
	
	
</div>
</body>
</html>