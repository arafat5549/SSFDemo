<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%--引入JSTL标签库的前缀 --%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page  import="com.ssf.model.User"%>

<%
   String contextPath = request.getContextPath();
   pageContext.setAttribute("contextPath", contextPath);
%>    

<%
   User user = (User)request.getSession().getAttribute("session_user");
%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
</head>
<body>
   <div>
   <%if(user!=null){%>
               欢迎,<%=user.getUsername() %>.<a href="${contextPath }/user?method=logout">退出</a>
   <%}else{%>
   		<a href="login.jsp">登录</a>
  	    <a href="${contextPath }/user?method=login">登录</a>
   <%}%>
	</div>
 
	<div>
        <h1>使用JSTL标签库和EL表达式</h1>
        
        <c:if test="${not empty session_user }">
                           欢迎,${session_user.username } . <a href="${contextPath }/user?method=logout">退出</a>
        </c:if>
        
        <c:if test="${empty session_user }">
        	<a href="login.jsp">登录</a>
  	    	<a href="${contextPath }/user?method=login">登录</a>
        </c:if>
        
        <a href="${contextPath }/user?method=listUser">测试用户列表</a>
        <br>
        <c:forEach items="${userlist }" var="user">
             ${user.username } / ${user.password } <br>
        </c:forEach>
        
        <c:forEach var="ddd" begin="1" end="10" step ="2">
			<c:set var="sum" value="${sum +dddi}" />
		</c:forEach>
        
    </div>
    
    <%-- 
    	<jsp:attribute name=""></jsp:attribute>
    --%>
</body>
</html>