<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/taglib.jsp" %> 


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表-分页</title>

</head>
<body>

    <table>
        <thead>
          <th>用户id</th>
          <th>用户名称</th>
          <th>用户密码</th>
          <th>操作</th>
        </thead>
        
        <tbody>
           <c:forEach items="${pagenation.lists }" var="user">
      		<tr>
               <td>${user.id }</td>
               <td>${user.username }</td>
               <td>${user.password }</td>
               <td> 
                 <a href="${context }/user?method=upd&pid=${user.id }">修改</a> /
                 <a href="${context }/user?method=del&pid=${user.id }">删除</a> 
               </td>
               
            </tr>
  		  </c:forEach>
        </tbody>
    </table>
    
    <tags:pagenation pagenation="${pagenation}" pageSize="10"/>
    
</body>
</html>