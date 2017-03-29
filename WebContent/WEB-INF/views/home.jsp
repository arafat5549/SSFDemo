<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/static/taglib.jsp" %>

<%--@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@ include file="/static/head.jsp" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>主页</title>

   <script type="text/javascript">

    function showProductsAsideCategorys(cid){
		$("div.eachCategory[cid="+cid+"]").css("background-color","white");
		$("div.eachCategory[cid="+cid+"] a").css("color","#87CEFA");
		$("div.productsAsideCategorys[cid="+cid+"]").show();
	}


	function hideProductsAsideCategorys(cid){
		$("div.eachCategory[cid="+cid+"]").css("background-color","#e2e2e3");
		$("div.eachCategory[cid="+cid+"] a").css("color","#000");
		$("div.productsAsideCategorys[cid="+cid+"]").hide();
	}

    $(function(){	
	    $("div.eachCategory").mouseenter(function(){
	        var cid = $(this).attr("cid");
	        showProductsAsideCategorys(cid);
	    });
	    $("div.eachCategory").mouseleave(function(){
	        var cid = $(this).attr("cid");
	        hideProductsAsideCategorys(cid);
	    });
	    $("div.productsAsideCategorys").mouseenter(function(){
    		var cid = $(this).attr("cid");
    		showProductsAsideCategorys(cid);
   		 });
	    $("div.productsAsideCategorys").mouseleave(function(){
	    	var cid = $(this).attr("cid");
	    	hideProductsAsideCategorys(cid);
	    });

    	$("div.rightMenu span").mouseenter(function(){
			var left = $(this).position().left;
			var top = $(this).position().top;
			var width = $(this).css("width");
			var destLeft = parseInt(left) + parseInt(width)/2;
			$("img#catear").css("left",destLeft);
			$("img#catear").css("top",top-2);
			$("img#catear").fadeIn(500);

		});

		$("div.rightMenu span").mouseleave(function(){
			$("img#catear").hide();
		});

		var left = $("div#carousel-of-product").offset().left;
		$("div.categoryMenu").css("left",left-20);
		$("div.categoryWithCarousel div.head").css("margin-left",left);
		$("div.productsAsideCategorys").css("left",left-20);

	});
    </script>

    <script type="text/javascript">
		$(function(){
			$("div.productsAsideCategorys div.row a").each(function(){
				var v = Math.round(Math.random() *6);
				if(v == 1)
					$(this).css("color","#87CEFA");
			});
		});
	</script>
</head>
<body>
   
   <%@ include file="/static/include/top.jsp" %>
   
<div class="homepageDiv">


<img src="static/img/site/catear.png" id="catear" class="catear"/>
<!-- BEGIN分类区域 -->	
<div class="categoryWithCarousel">
<div class="headbar show1">
	<div class="head ">
	
		<span style="margin-left:10px" class="glyphicon glyphicon-th-list"></span>
		<span style="margin-left:10px" >商品分类</span>
		
	</div>
	
	<div class="rightMenu">
		<span><a href=""><img src="static/img/site/chaoshi.png"/></a></span>
		<span><a href=""><img src="static/img/site/guoji.png"/></a></span>
	</div>
	
</div>

<!-- categoryMenu分类菜单 -->
<div style="position: relative">
	<div class="categoryMenu">
	     <c:forEach items="${firstCategorys }" var="item">
	         <div cid="${item.id }" class="eachCategory">
					<span class="glyphicon glyphicon-link"></span>
					<a href="${context }/product?cid=${item.id}">
						${item.name }
					</a>
			</div>
	     </c:forEach>
	        
	        <!--  
			<div cid="1" class="eachCategory">
					<span class="glyphicon glyphicon-link"></span>
					<a href="forecategory?cid=1">
						商品分类
					</a>
			</div>

			<div cid="2" class="eachCategory">
					<span class="glyphicon glyphicon-link"></span>
					<a href="forecategory?cid=2">
						美食分类
					</a>
			</div>
			-->
	</div>  
</div>

<!-- productsAsideCategorys隐藏分类区域 -->
<div style="position: relative;left: 0;top: 0;">

    <c:forEach items="${firstCategorys }" var="item">
        <div cid="${item.id }" class="productsAsideCategorys">
           <c:forEach items="${item.childs }" var="child">
              <a href="${context }/product?cid=${child.id}">${child.name } ></a>
              <c:forEach items="${child.childs }" var="grand">
                  <a href="${context }/product?cid=${grand.id}">${grand.name }</a> |
              </c:forEach>
              <div class="seperator"></div>
           </c:forEach>
        </div>
    </c:forEach>
     
    <!--  
	<div cid="1" class="productsAsideCategorys">
		<div class="row show1">
			<a href="foreproduct?pid=1">商品子类1 ></a>

			<a href="foreproduct?pid=1">商品孙子类</a> |
			<a href="foreproduct?pid=1">商品孙子类</a> |
		</div>
		<div class="seperator"></div>

		<div class="row show1">
			<a href="foreproduct?pid=1">商品子类2 ></a>

			<a href="foreproduct?pid=1">商品孙子类</a> |
			<a href="foreproduct?pid=1">商品孙子类</a> |
			<a href="foreproduct?pid=1">商品孙子类</a> |
			<a href="foreproduct?pid=1">商品孙子类</a> |
		</div>
		<div class="seperator"></div>
	</div>

	<div cid="2" class="productsAsideCategorys">
		<div class="row show1">
			<a href="foreproduct?pid=1">美食子类 ></a>

			<a href="foreproduct?pid=1">美食孙子类</a> |
			<a href="foreproduct?pid=1">美食孙子类</a> |
			<a href="foreproduct?pid=1">美食孙子类</a> |
			<a href="foreproduct?pid=1">美食孙子类</a> |
		</div>
		<div class="seperator"></div>
	</div>
	-->
</div>
   
</div> 

 <%--
    <c:forEach items="${firstCategorys }" var="item">
        ${item.name }
    </c:forEach>
    
    @ include file="/static/include/footer.jsp" --%>
</body>
</html>