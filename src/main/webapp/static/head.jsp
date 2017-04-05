<%-- 所有头部相关的配置一般是css和js文件 注意要放在head标签对里面 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 把页面默认缓存去掉，每次都会生成全新的页面 -->    
<meta http-equiv="Expires" content="0">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Cache-Control" content="no-store">

<!-- Jquery和BootStrap代码库 -->
<script src="${context }/static/js/jquery/2.0.0/jquery.min.js"></script>
<link href="${context }/static/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="${context }/static/js/bootstrap/3.3.6/bootstrap.min.js"></script>	
<link href="${context }/static/css/fore/style.css" rel="stylesheet">

<script>
function formatMoney(num){
	num = num.toString().replace(/\$|\,/g,'');  
	if(isNaN(num))  
	    num = "0";  
	sign = (num == (num = Math.abs(num)));  
	num = Math.floor(num*100+0.50000000001);  
	cents = num%100;  
	num = Math.floor(num/100).toString();  
	if(cents<10)  
	cents = "0" + cents;  
	for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)  
	num = num.substring(0,num.length-(4*i+3))+','+  
	num.substring(num.length-(4*i+3));  
	return (((sign)?'':'-') + num + '.' + cents);  
}

function checkEmpty(id, name){
	var value = $("#"+id).val();
	if(value.length==0){
		
		$("#"+id)[0].focus();
		return false;
	}
	return true;
}
</script>
