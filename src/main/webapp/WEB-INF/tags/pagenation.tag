<%@ tag language="java" pageEncoding="UTF-8"%>
<!-- 分页模块 自定义标签库 -->
<%@ attribute name="pagenation" type="com.ssf.model.Pagenation" required="true"%>
<!-- 显示多少条页签 -->
<%@ attribute name="pageSize" type="java.lang.Integer" required="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
    int pageIndex = pagenation.getPage();
    int totalPage = pagenation.getTotalPage();
    int begin =  Math.max(pageIndex - pageSize/2 + 1, 1);//第几个页签开始
    int end = Math.min(begin + pageSize , totalPage);
    request.setAttribute("begin", begin);
    request.setAttribute("end", end);
%>
<div class="pagenation"> 
    <a href="${context }/admin/demo/1">首页</a>
    
    <c:if test="${pagenation.page >=1}">
      <a href="${context }/admin/demo/${pagenation.page - 1}">前一页</a>
    </c:if>
    
    <c:forEach begin="${begin }" end="${end}" step="1" var="page">
        <c:if test="${pagenation.page == page -1 }">
            ${page }
        </c:if>
        <c:if test="${pagenation.page != page -1 }">
          <a href="${context }/admin/demo/${page}">${page }</a>
        </c:if>
    </c:forEach>
    
   <c:if test="${pagenation.page < (pagenation.totalPage-1)}">
      <a href="${context }/admin/demo/${pagenation.page + 1}">下一页</a>
    </c:if>
    
    <a href="${context }/admin/demo/${pagenation.totalPage}">尾页</a>
</div>
