<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/static/taglib.jsp" %> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息表</title>
</head>
<body>
     <!-- 文件上传 enctype="multipart/form-data"   enctype="multipart/form-data" --> 
    <form action="${context }/user?method=userInfo" method="post" enctype="multipart/form-data">
	    
	    
	    <ul>
	       <li>
	       <img src="${context }/static/avatar/${empty session_user.avatarUrl ? 'avatar.jpg' : session_user.avatarUrl}"
	       width="40" height="40">
	       <input type="file" name="avatarUrl" value="">
	       </li>
	       <li>${session_user.username }</li>
	    </ul>
	    
	    <button>提交</button>
    </form>
    
</body>
</html>