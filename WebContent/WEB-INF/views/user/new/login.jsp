<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/static/taglib.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/static/head.jsp" %>
<script>

$(function(){
	
	<c:if test="${not empty msg }">
		$("span.errorMessage").html("${msg}");
		$("div.loginErrorMessageDiv").show();		
	</c:if>
	
	$("form.loginForm").submit(function(){
		if(0==$("#name").val().length||0==$("#password").val().length){
			$("span.errorMessage").html("请输入账号密码");
			$("div.loginErrorMessageDiv").show();			
			return false;
		}
		return true;
	});
	
	$("form.loginForm input").keyup(function(){
		$("div.loginErrorMessageDiv").hide();	
	});
	
	var left = window.innerWidth/2+162;
	$("div.loginSmallDiv").css("left",left);
})
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>天猫商城-注册</title>
</head>
<body>

<div id="loginDiv" style="position: relative">
	
	<div class="simpleLogo">
		<a href="index.html"><img src="${contextStatic }/img/site/simpleLogo.png"></a>
	</div>

	
	<img id="loginBackgroundImg" class="loginBackgroundImg" src="${contextStatic }/img/site/loginBackground.png">
	
	<form class="loginForm" action="${context }/user" method="post">
	
	    <input type="hidden" name="method" value="login"> 
		<div id="loginSmallDiv" class="loginSmallDiv">
			<div class="loginErrorMessageDiv">
				<div class="alert alert-danger" >
				  <button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
				  	<span class="errorMessage"></span>
				</div>
			</div>
				
			<div class="login_acount_text">账户登录</div>
			<div class="loginInput " >
				<span class="loginInputIcon ">
					<span class=" glyphicon glyphicon-user"></span>
				</span>
				<input id="name" name="username" placeholder="手机/会员名/邮箱" type="text">			
			</div>
			
			<div class="loginInput " >
				<span class="loginInputIcon ">
					<span class=" glyphicon glyphicon-lock"></span>
				</span>
				<input id="password" name="password" type="password" placeholder="密码" type="text">
			</div>
			<span class="text-danger">不要输入真实的天猫账号密码</span><br><br>
			
			
			<div>
				<a class="notImplementLink" href="#nowhere">忘记登录密码</a> 
				<a href="register.html" class="pull-right">免费注册</a> 
			</div>
			<div style="margin-top:20px">
				<button class="btn btn-block redButton" type="submit">登录</button>
			</div>
		</div>	
	</form>


</div>

</body>
</html>