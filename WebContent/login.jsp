<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   String contextPath = request.getContextPath();
   pageContext.setAttribute("contextPath", contextPath);
%>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页</title>
</head>
<body>
     
    <p><%=request.getAttribute("msg") %></p>
    <p>${msg }</p>
     
    <form action="${contextPath}/user" method="post">
        <div>
                       用户名：<input type="text"  id="username" name="username" value="${session_user.username }"/>
        </div>
        <div>
                       密码：<input type="password" id="password" name="password" value="${session_user.password }"/>
        </div>
        <button>提交</button>
    </form>
</body>
</html>