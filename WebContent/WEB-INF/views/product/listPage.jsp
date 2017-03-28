<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/taglib.jsp" %> 



<%@ page import="com.ssf.model.Pagenation"%> 


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品页面-分页</title>

</head>
<body>
    <div class="categoryArea">
    	<table>
	        <thead>
	          <th>分类名称</th>
	        </thead>
	        
	        
	        <tbody>
	           <c:forEach items="${firstCategoryList }" var="category">
	      		<tr>
	               <td>${category.name }</td>
	               <td>
		               <ul>
		               	<c:forEach items="${category.childs }" var="child">
		                    <li><a href="${context }/product?method=list&cid=${child.id }">${child.name }</a></li>
		              	 </c:forEach>
		               </ul>
	               </td>
	            </tr>
	            <!--  
	              <a href="${context }/product?method=list&cid=${category.id }"></a>
	            -->
	  		  </c:forEach>
	        </tbody>
	    </table>
    </div>
    
    <div class="productArea">
	    <table>
	        <thead>
	          <th>商品id</th>
	          <th>商品名称</th>
	          <th>商品价格</th>
	          <th>商品分类</th>
	          <th>操作</th>
	        </thead>
	        
	        <tbody>
	           <c:forEach items="${pagenation.lists }" var="product">
	      		<tr>
	               <td>${product.id }</td>
	               <td>${product.name }</td>
	               <td>${product.price }</td>
	               <td>${product.categoryId } / ${product.category.name }</td>
	               <td> 
	                 <a href="${context }/product?method=upd&pid=${product.id }">修改</a> /
	                 <a href="${context }/product?method=del&pid=${product.id }">删除</a> /
	                 <a href="${context }/cart?method=add&pid=${product.id }">加入购物车</a>
	               </td>
	               
	            </tr>
	  		  </c:forEach>
	        </tbody>
	    </table>
	    
	    <tags:pagenation pagenation="${pagenation}" pageSize="10"/>
    </div>
</body>
</html>