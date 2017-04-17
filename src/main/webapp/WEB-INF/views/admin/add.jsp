<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/Public/taglib.jsp" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/Public/csslib.jsp" %>
<link rel="stylesheet" href="${contextStatic }/css/plugins/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分类添加页面</title>
</head>
<body>

<div id="wrapper"> 
	<%@ include file="/Public/include/layout.jsp" %>
	<%-- Body主面板内容 --%>
	<%@ include file="/Public/include/category/add.jsp" %>
</div>

<%@ include file="/Public/jslib.jsp" %>


 <SCRIPT type="text/javascript" >

 function onClick(event, treeId, treeNode, clickFlag) {
	//showLog("[ "+getTime()+" onClick ]&nbsp;&nbsp;clickFlag = " + clickFlag + " (" + (clickFlag===1 ? "普通选中": (clickFlag===0 ? "<b>取消选中</b>" : "<b>追加选中</b>")) + ")");
    //alert(treeNode.id);
    $("#parentName").val(treeNode.name);
    $("#parentId").val(treeNode.id);
    $("#parentIds").val(treeNode.parentIds); 
    
 }	
 
 //var zNodes =[
 //       	  {"id":1,"pId":0,"name":"个人用品/服饰"},{"id":2,"pId":0,"name":"家用电器"},{"id":11,"pId":1,"name":"男装"},{"id":12,"pId":1,"name":"女装"},{"id":13,"pId":1,"name":"配饰"},{"id":21,"pId":2,"name":"家具"},{"id":22,"pId":2,"name":"生活家电"},{"id":23,"pId":2,"name":"大电器"},{"id":60,"pId":21,"name":"安全座椅"},{"id":64,"pId":13,"name":"太阳镜"},{"id":68,"pId":12,"name":"品牌女装"},{"id":69,"pId":11,"name":"时尚男鞋"},{"id":71,"pId":11,"name":"男士西服"},{"id":72,"pId":11,"name":"男士手拿包"},{"id":73,"pId":11,"name":"男表"},{"id":74,"pId":12,"name":"女表"},{"id":75,"pId":23,"name":"空调"},{"id":76,"pId":23,"name":"冰箱"},{"id":77,"pId":22,"name":"原汁机"},{"id":78,"pId":23,"name":"扫地机器人"},{"id":79,"pId":23,"name":"平衡车"},{"id":80,"pId":22,"name":"电热水器"},{"id":81,"pId":21,"name":"沙发"},{"id":82,"pId":22,"name":"马桶"},{"id":83,"pId":22,"name":"平板电视"}]; 
 
 var setting = {
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				onClick: onClick
			}
};
 
 $(document).ready(function(){
		//$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		
		$.ajax(
			{ 
				url: "/MySSMShop/admin/demo/getJson", 
				//context: document.body,
			 	success: function(data){
 					$.fn.zTree.init($("#treeDemo"), setting,  data);
				}
			}
		);
		/**/
	});
	
	
</script>

</body>
</html>