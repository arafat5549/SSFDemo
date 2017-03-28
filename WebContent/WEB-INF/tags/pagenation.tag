<%@ tag language="java" pageEncoding="UTF-8"%>
<!-- 分页模块 自定义标签库 -->
<%@ attribute name="pagenation" type="com.ssf.model.Pagenation" required="true"%>
<%@ attribute name="pageSize" type="java.lang.Integer" required="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
    int pageIndex = pagenation.getPageIndex();
    int totalPage = pagenation.getTotalPage();
    int begin =  Math.max(pageIndex - pageSize/2 + 1, 1);
    int end = Math.min(begin + pageSize , totalPage);
    request.setAttribute("begin", begin);
    request.setAttribute("end", end);
%>
<div class="pagenation"> 
    <a href="?method=list&limit=${pagenation.limit }&offset=0">首页</a>
    
    <c:if test="${pagenation.pageIndex >=1}">
      <a href="?method=list&limit=${pagenation.limit }&offset=${pagenation.limit * (pagenation.pageIndex-1)}">前一页</a>
    </c:if>
    
    <c:forEach begin="${begin }" end="${end}" step="1" var="page">
        <c:if test="${pagenation.pageIndex == page -1 }">
            ${page }
        </c:if>
        <c:if test="${pagenation.pageIndex != page -1 }">
          <a href="?method=list&limit=${pagenation.limit }&offset=${pagenation.limit * (page-1)}">${page }</a>
        </c:if>
    </c:forEach>
    
   <c:if test="${pagenation.pageIndex < (pagenation.totalPage-1)}">
      <a href="?method=list&limit=${pagenation.limit }&offset=${pagenation.limit * (pagenation.pageIndex+1)}">下一页</a>
    </c:if>
    
    <a href="?method=list&limit=${pagenation.limit }&offset=${pagenation.limit * (pagenation.totalPage-1)}">尾页</a>
</div>
