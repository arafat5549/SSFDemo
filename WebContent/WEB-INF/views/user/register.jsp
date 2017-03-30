<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/static/taglib.jsp" %> 

<%
   //response
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
</head>
<body>
   <div>
       ${msg }
   </div> 
      
   <form action="${context}/user" method="post">
      <input type="hidden" name="method" value="register">
	     用户：<input type="text"     name="username" value=""><br>
	     密码：<input type="password" name="password" value=""><br>
	   <button name="btn" value="123">注册</button>  
   </form>
   
</body>
</html>