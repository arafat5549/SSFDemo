<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%--@ taglib prefix="tags" tagdir="/WEB-INF/tags/" --%>
<%--@ taglib prefix="myfns" uri="/WEB-INF/tlds/myfns.tld" --%>

<%
    String context = request.getContextPath();
    request.setAttribute("context", context);    //请求域
    
    request.setAttribute("contextStatic", context+"/static");    //请求域
%>