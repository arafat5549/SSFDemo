<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/static/head.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>天猫商城-付款</title>
</head>
<body>

<%@ include file="/static/include/top.jsp" %> 


<div class="aliPayPageDiv">
	<div class="aliPayPageLogo">
		<img class="pull-left" src="${contextStatic }/img/site/simpleLogo.png">
		<div style="clear:both"></div>
	</div>
	
	<div>
		<span class="confirmMoneyText">扫一扫付款（元）</span>
		<span class="confirmMoney">
		￥<fmt:formatNumber type="number" value="${param.total}" minFractionDigits="2"/></span>
		
	</div>
	<div>
		<img class="aliPayImg" src="${contextStatic }/img/site/alipay2wei.png">
	</div>

	
	<div>
	    <!--  
		<a href="order?method=alipay&oid=${param.oid}&total=${param.total}"><button class="confirmPay">确认支付</button></a>
		-->
		<form action="${context }/order" method="post">
			<input type="hidden" name="method" value="alipay">
			<input type="hidden" name="oid" value="${param.oid}">
			<input type="hidden" name="total" value="${param.total}">
		    <button class="confirmPay">确认支付</button>
		</form>
	</div>

</div>

</body>
</html>