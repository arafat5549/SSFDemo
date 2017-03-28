<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@ include file="/static/taglib.jsp" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>主页</title>
</head>
<body>
       
      <div>
           ${msg }
      </div> 
   <a href="${context}/user?method=register">注册</a>
     
   <c:if test="${empty sessionScope.session_user }">
       <a href="${context}/user?method=login">登录</a>
   </c:if>
   
   <c:if test="${not empty sessionScope.session_user }">
               欢迎回来<a href="${context}/user?method=userInfo">${sessionScope.session_user.username }！</a>
       <a href="${context}/user?method=logout">退出</a>
       <br>
       <a href="${context}/product?method=list&limit=20&offset=0">商品列表</a>
       
       <a href="${context}/cart?method=list">查看购物车</a>
   </c:if>
   
   
   
   <%-- 
   	 <a href="${context}/product?method=list&limit=20&offset=0">商品列表</a>
   	 <a href="${context}/user?method=list&limit=20&offset=0">用户列表</a>
   	 ${myfns:getAdminPath()}
   
   
   <table class="table" align="center">
	<tr style="background: #4682B4; height: 120px; ">
		<td colspan="2" align="center">
			<iframe frameborder="0" src="" name="top"></iframe>
		</td>
	</tr>
	<tr>
		<td width="120" style="padding:5px;" align="center" valign="top">
			<iframe frameborder="0" width="120" src="${context}/category?method=findAll'/>" name="left"></iframe>
		</td>
	</tr>
</table>
   --%>
   
   
</body>
</html>