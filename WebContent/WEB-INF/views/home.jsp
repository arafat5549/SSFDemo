<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

  
<%@ include file="/static/taglib.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/static/head.jsp" %> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

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
			$("img#catear").css("top",top-20);
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

<title>天猫商城</title>
</head>
<body>

<%@ include file="/static/include/top.jsp" %> 
<%--@ include file="/static/include/search.jsp" --%> 

<div class="homepageDiv">


<img src="${contextStatic }/img/site/catear.png" id="catear" class="catear"/>
<!-- BEGIN分类区域 -->	
<div class="categoryWithCarousel">
<div class="headbar show1">
	<div class="head ">
	
		<span style="margin-left:10px" class="glyphicon glyphicon-th-list"></span>
		<span style="margin-left:10px" >商品分类</span>
		
	</div>
	
	<div class="rightMenu">
		<span><a href=""><img src="${contextStatic }/img/site/chaoshi.png"/></a></span>
		<span><a href=""><img src="${contextStatic }/img/site/guoji.png"/></a></span>
        
		<span>
			<a href="forecategory?cid=1">分类</a>
		</span>	
	</div>
	
</div>

<!-- categoryMenu分类菜单 -->
<div style="position: relative">
	<div class="categoryMenu">
	 
	<c:forEach items="${firstCategoryList }" var="item">
	    <div cid="${item.id }" class="eachCategory">
			<span class="glyphicon glyphicon-link"></span>
			<a href="forecategory?cid=${item.id }">
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
	<c:forEach items="${firstCategoryList }" var="item">
		<div cid="${item.id }" class="productsAsideCategorys">
			<c:forEach items="${item.childs }" var="child">
			   <div class="row show1">
			      <a href="product?pid=${child.id }"><strong>${child.name } >></strong></a>
			      <c:forEach items="${child.childs }" var="grandchild">
			      	<a href="product?pid=${grandchild.id }">${grandchild.name } |</a>
			      </c:forEach>
			   </div>
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

<!-- carousel 商品轮播 -->
<div id="carousel-of-product"  class="carousel-of-product carousel slide1" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#carousel-of-product" data-slide-to="0" class="active"></li>
    <li data-target="#carousel-of-product" data-slide-to="1"></li>
    <li data-target="#carousel-of-product" data-slide-to="2"></li>
    <li data-target="#carousel-of-product" data-slide-to="3"></li>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner" role="listbox">
    <div class="item active">
      <img class="carousel carouselImage" src="${context }/static/img/lunbo/1.jpg" >
    </div>
    <div class="item">
      <img  class="carouselImage" src="${context }/static/img/lunbo/2.jpg" >
    </div>
    <div class="item">
		<img  class="carouselImage" src="${context }/static/img/lunbo/3.jpg" >
    </div>

    <div class="item">
        <img  class="carouselImage" src="${context }/static/img/lunbo/4.jpg" >
    </div>

  </div>
</div>	

<div class="carouselBackgroundDiv"></div>

</div>

<!-- BEGIN商品展示区域 -->
<div class="homepageCategoryProducts">
	<c:forEach items="${firstCategoryList }" var="item">
		<div class="eachHomepageCategoryProducts">
			<div class="left-mark"></div>
			<span class="categoryTitle">${item.name }</span>
			<br>
			<c:forEach items="${item.products}" var="p" varStatus="st">
					<c:if test="${st.count<=5}">
						<div class="productItem" >
							<a href="product?method=detail&pid=${p.id}">
							<img width="100px" src="${contextStatic }/img/productSingle_middle/19.jpg">
							</a>
							<a class="productItemDescLink" href="product?method=detail&pid=${p.id}">
								<span class="productItemDesc">[热销]
								${fn:substring(p.name, 0, 20)}
								</span>
						    </a>
							<span class="productPrice">
								¥<fmt:formatNumber type="number" value="${p.promotePrice}" minFractionDigits="2"/>
							</span>
							
							<!--<a class="productAddCart" href="">评论</a>-->
							<a class="productAddCart" href="${context}/cart?method=add&pid=${p.id}">
        						<span style="color:#C40000;margin:0px" class="glyphicon glyphicon-shopping-cart redColor"></span>
        						<strong>加入购物车</strong>
							</a>
						</div>
					</c:if>				
			</c:forEach>
			<div style="clear:both"></div>
		</div>
	</c:forEach>
	<!-- 
	<div class="eachHomepageCategoryProducts">
		<div class="left-mark"></div>
		<span class="categoryTitle">商品分类</span>
		<br>
		
		
		 
		<div class="productItem" >
			<a href="foreproduct?pid=1">
			<img width="100px" src="${context }/static/img/productSingle_middle/19.jpg"></a>
			<a class="productItemDescLink" href="foreproduct?pid1">
				<span class="productItemDesc">
				[热销]夏普
				</span>
			</a>
			<span class="productPrice">
				¥12.0
			</span>

			<a class="productAddCart" target="_blank" href="">
        		<span style="color:#C40000;margin:0px" class="glyphicon glyphicon-shopping-cart redColor"></span>
        		<strong>加入购物车</strong>
			</a>
		</div>

		<div class="productItem" >
			<a href="foreproduct?pid=1">
			<img width="100px" src="${context }/static/img/productSingle_middle/19.jpg"></a>
			<a class="productItemDescLink" href="foreproduct?pid1">
				<span class="productItemDesc">
				[热销]夏普
				</span>
			</a>
			<span class="productPrice">
				12.0
			</span>
		</div>
		
		<div style="clear:both"></div>
	</div>

	<div class="eachHomepageCategoryProducts">
		<div class="left-mark"></div>
		<span class="categoryTitle">美食分类</span>
		<br>
		<div class="productItem" >
			<a href="foreproduct?pid=2">
			<img width="100px" src="${context }/static/img/productSingle_middle/19.jpg"></a>
			<a class="productItemDescLink" href="foreproduct?pid1">
				<span class="productItemDesc">
				[热销]夏普
				</span>
			</a>
			<span class="productPrice">
				12.0
			</span>
		</div>

		<div style="clear:both"></div>
	</div>
	-->
</div>
</div>

<%-- 
<%@ page import="" %>    
<%
  User user = (User)request.getAttribute("user");
%>  

<%
   if(){
	   
%>
  <a>sssss</a>
<%
   }
%>

${user } / <%=request.getAttribute("user") %> ${user.image }
--%>
</body>
</html>