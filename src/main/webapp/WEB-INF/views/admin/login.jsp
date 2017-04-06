<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/static/taglib.jsp" %>   
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/static/head.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台登录页面</title>
</head>
<body>
   <div>
       ${msg }
   </div> 
      
   <form action="${context}/admin/login" method="post" >
      <!--  <input type="hidden" name="method" value="login">-->
	     用户：<input type="text"     name="username" value="${session_user.username }"><br>
	     密码：<input type="password" name="password" value="${session_user.password }"><br>
	  <button>登录</button> 
   </form>
</body>
</html>