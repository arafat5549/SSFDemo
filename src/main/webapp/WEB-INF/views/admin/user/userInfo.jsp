<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/Public/taglib.jsp" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/Public/csslib.jsp" %>
<link rel="stylesheet" href="${contextStatic }/css/plugins/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息</title>
</head>
<body>

<div id="wrapper"> 
	<%@ include file="/Public/include/layout.jsp" %>
	<%-- Body主面板内容 --%>
	<%@ include file="/Public/include/user/userInfo.jsp" %>
</div>

<%@ include file="/Public/jslib.jsp" %>


</body>
</html>