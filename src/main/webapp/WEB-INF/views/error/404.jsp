<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="/static/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>404 - 页面不存在</title>
	<%@include file="/static/head.jsp" %>
</head>
<body>
	<div class="container-fluid">
		<div class="page-header"><h1>页面不存在.</h1></div>
		<div><a href="javascript:" onclick="history.go(-1);" class="btn">返回上一页</a></div>
		<!--<script>try{top.$.jBox.closeTip();}catch(e){}</script>  -->
	</div>
</body>
</html>
<%
out.print("<!--"+request.getAttribute("javax.servlet.forward.request_uri")+"-->");

out = pageContext.pushBody();
%>