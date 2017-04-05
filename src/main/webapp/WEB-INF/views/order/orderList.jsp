<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/taglib.jsp" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/static/head.jsp" %>

<script>
var deleteOrder = false;
var deleteOrderid = 0;
$(function(){
	$("a[orderStatus]").click(function(){
		var orderStatus = $(this).attr("orderStatus");
		if('all'==orderStatus){
			$("table[orderStatus]").show();	
		}
		else{
			$("table[orderStatus]").hide();
			$("table[orderStatus="+orderStatus+"]").show();			
		}
		
		$("div.orderType div").removeClass("selectedOrderType");
		$(this).parent("div").addClass("selectedOrderType");
	});
	
	$("a.deleteOrderLink").click(function(){
		deleteOrderid = $(this).attr("oid");
		deleteOrder = false;
		$("#deleteConfirmModal").modal("show");
	});
	
	$("button.deleteConfirmButton").click(function(){
		deleteOrder = true;
		$("#deleteConfirmModal").modal('hide');
	});	
	
	$('#deleteConfirmModal').on('hidden.bs.modal', function (e) {
		if(deleteOrder){
			var page="foredeleteOrder";
			$.post(
				    page,
				    {"oid":deleteOrderid},
				    function(result){
						if("success"==result){
							$("table.orderListItemTable[oid="+deleteOrderid+"]").hide();
						}
						else{
							location.href="login.jsp";
						}
				    }
				);
			
		}
	})		
	
	$(".ask2delivery").click(function(){
		var link = $(this).attr("link");
		$(this).hide();
		page = link;
		$.ajax({
			   url: page,
			   success: function(result){
				alert("卖家已秒发，刷新当前页面，即可进行确认收货")
			   }
			});
		
	});
});

</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>天猫商城-订单列表</title>
</head>
<body>

<%@ include file="/static/include/top.jsp" %> 

<div class="boughtDiv">
	<div class="orderType">
		<div class="selectedOrderType"><a orderStatus="all" href="#nowhere">所有订单</a></div>
		<div><a  orderStatus="waitPay" href="#nowhere">待付款</a></div>
		<div><a  orderStatus="waitDelivery" href="#nowhere">待发货</a></div>
		<div><a  orderStatus="waitConfirm" href="#nowhere">待收货</a></div>
		<div><a  orderStatus="waitReview" href="#nowhere" class="noRightborder">待评价</a></div>
		<div class="orderTypeLastOne"><a class="noRightborder">&nbsp;</a></div>
	</div>
	<div style="clear:both"></div>
	<div class="orderListTitle">
		<table class="orderListTitleTable">
			<tr>
				<td>宝贝</td>
				<td width="100px">单价</td>
				<td width="100px">数量</td>
				<td width="120px">实付款</td>
				<td width="100px">交易操作</td>
			</tr>
		</table>
	
	</div>
	
	<div class="orderListItem">
		<c:forEach items="${orders}" var="o">
			<table class="orderListItemTable" orderStatus="${o.status}" oid="${o.id}">
				<tr class="orderListItemFirstTR">
					<td colspan="2">
					<b><fmt:formatDate value="${o.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></b> 
					<span>订单号: ${o.ordercode} 
					</span>
					</td>
					<td  colspan="2"><img width="13px" src="static/img/site/orderItemTmall.png">天猫商场</td>
					<td colspan="1">
						<a class="wangwanglink" href="#nowhere">
							<div class="orderItemWangWangGif"></div>
						</a>
						
					</td>
					<td class="orderItemDeleteTD">
						<a class="deleteOrderLink" oid="${o.id}" href="#nowhere">
							<span  class="orderListItemDelete glyphicon glyphicon-trash"></span>
						</a>
						
					</td>
				</tr>
				<c:forEach items="${o.items}" var="oi" varStatus="st">
					<tr class="orderItemProductInfoPartTR" >
						<td class="orderItemProductInfoPartTD"><img width="80" height="80" src="static/img/productSingle_middle/19.jpg"></td>
						<td class="orderItemProductInfoPartTD">
							<div class="orderListItemProductLinkOutDiv">
								<a href="product?method=detail&pid=${oi.product.id}">${oi.product.name}</a>
								<div class="orderListItemProductLinkInnerDiv">
											<img src="static/img/site/creditcard.png" title="支持信用卡支付">
											<img src="static/img/site/7day.png" title="消费者保障服务,承诺7天退货">
											<img src="static/img/site/promise.png" title="消费者保障服务,承诺如实描述">						
								</div>
							</div>
						</td>
						<td  class="orderItemProductInfoPartTD" width="100px">
						
							<div class="orderListItemProductOriginalPrice">￥<fmt:formatNumber type="number" value="${oi.product.originPrice}" minFractionDigits="2"/></div>
							<div class="orderListItemProductPrice">￥<fmt:formatNumber type="number" value="${oi.product.promotoPrice}" minFractionDigits="2"/></div>
		
		
						</td>
						<c:if test="${st.count==1}">
						 
							<td valign="center" rowspan="${fn:length(o.items)}" class="orderListItemNumberTD orderItemOrderInfoPartTD" width="100px">
								<span class="orderListItemNumber">${o.totalCount}</span>
							</td>
							<td valign="center" rowspan="${fn:length(o.items)}" width="120px" class="orderListItemProductRealPriceTD orderItemOrderInfoPartTD">
								<div class="orderListItemProductRealPrice">￥<fmt:formatNumber  minFractionDigits="2"  maxFractionDigits="2" type="number" value="${o.total}"/></div>
								<div class="orderListItemPriceWithTransport">(含运费：￥0.00)</div>
							</td>
							<td valign="center" rowspan="${fn:length(o.items)}" class="orderListItemButtonTD orderItemOrderInfoPartTD" width="100px">
								<c:if test="${o.status=='waitConfirm' }">
										<form action="${context}/order" method="post">
									   		<input type="hidden" name="method" value="confirm">
											<input type="hidden" name="oid" value="${o.id}">
									   		<button class="orderListItemConfirm">确认收货</button> 
									   </form>
								    <!--  
										<a href="foreconfirmPay?oid=${o.id}">
											<button class="orderListItemConfirm">确认收货</button>
										</a>
									-->
								</c:if>
								<c:if test="${o.status=='waitPay' }">
									<a href="order?method=alipay&oid=${o.id}&total=${o.total}">
										<button class="orderListItemConfirm">付款</button>
									</a>								
								</c:if>
								
								<c:if test="${o.status=='waitDelivery' }">
								    <!-- 测试 如果用户名字为admin则可以发货 -->
									<c:if test="${session_user.username=='admin' }">
									   <!--<button class="btn btn-info btn-sm ask2delivery" link="admin_order_delivery?id=${o.id}">Admin-发货</button>-->
									   <form action="${context}/order" method="post">
									   		<input type="hidden" name="method" value="delivery">
											<input type="hidden" name="oid" value="${o.id}">
									   		<button class="btn btn-info btn-sm">Admin-发货</button> 
									   </form>
									   
									</c:if>
									
									<c:if test="${session_user.username!='admin' }">
										<span>待发货</span>
									</c:if>
								</c:if>

								<c:if test="${o.status=='waitReview' }">
									<a href="forereview?oid=${o.id}">
										<button  class="orderListItemReview">评价</button>
									</a>
								</c:if>
							</td>						
						</c:if>
					</tr>
				</c:forEach>		
				
			</table>
		</c:forEach>
		
	</div>
	
</div>


</body>
</html>