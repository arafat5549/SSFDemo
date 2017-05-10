<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/static/taglib.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/static/head.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评论列表</title>
</head>
<body>
<%@ include file="/static/include/top.jsp" %>
<table>
    <thead>
      <th>文本</th>
      <th>时间</th>
      <th>评论人</th>
    </thead>
    
    <tbody>
        
	    <c:forEach items="${reviewList }" var="review">
	       <tr>
		   	<td>${review.text }</td> 
		   	<td><fmt:formatDate value="${review.createTime }" type="both"/></td><!-- 格式化时间的处理 -->
		   	<td>${review.user.username }</td>
		   </tr>
		</c:forEach>
    </tbody>
</table>


</body>
</html>