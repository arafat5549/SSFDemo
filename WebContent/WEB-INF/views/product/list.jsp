<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/taglib.jsp" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/static/head.jsp" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品列表</title>
</head>
<body>

   <%@ include file="/static/include/top.jsp" %>
   
   <table>
   	<c:forEach items="${products}" var="p">
   	   <tr>
           <td>${p.name }</td>
       </tr>
  	 </c:forEach>
	</table>
</body>
</html>