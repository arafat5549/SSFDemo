<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/taglib.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/static/head.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加评论</title>
</head>
<body>
     <%@ include file="/static/include/top.jsp" %>
    <form action="${context }/review" method="post">
        <input type="hidden" name="method" value="add">
        <input type="hidden" name="pid" value="${param.pid }"><!-- 我的参数 -->
        <textarea rows="10" cols="10" name="text"></textarea><br>
        <button>发表评论</button>
    </form>
</body>
</html>