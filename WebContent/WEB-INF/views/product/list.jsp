<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/static/taglib.jsp" %>     
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品列表</title>
</head>
<body>

    <table>
        <thead>
          <th>商品id</th>
          <th>商品名称</th>
          <th>商品价格</th>
          <th>商品分类</th>
          <th>操作</th>
        </thead>
        
        <tbody>
           <c:forEach items="${productList }" var="product">
      		<tr>
               <td>${product.id }</td>
               <td>${product.pname }</td>
               <td>${product.price }</td>
               <td>${product.cid } / ${product.category.cname }</td>
               <td> 
                 <a href="${context }/product?method=upd&pid=${product.id }">修改</a> /
                 <a href="${context }/product?method=del&pid=${product.id }">删除</a> 
               </td>
               
            </tr>
  		  </c:forEach>
           <!--  
           <tr>
               <td>1</td>
               <td>名称1</td>
               <td>价格高1</td>
               <td>分类1</td>
           </tr>
           
           <tr>
               <td>2</td>
               <td>名称2</td>
               <td>价格高2</td>
               <td>分类2</td>
           </tr>
           -->
        </tbody>
    </table>

     

</body>
</html>