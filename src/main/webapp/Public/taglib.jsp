<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- JSP的通用定义文件 -->    
    
<!-- 标签库的定义 -->    
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags/" %>
<%--@ taglib prefix="myfns" uri="/WEB-INF/tlds/myfns.tld" --%>

<!-- 常量的定义 -->
<%
    String context = request.getContextPath();
    request.setAttribute("context", context);    //请求域
    
    request.setAttribute("contextStatic", context+"/Public");    //请求域
    //request.setAttribute("__Public__", "//Public");
%>