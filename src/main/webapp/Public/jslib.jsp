<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <!-- Jquery和BootStrap 
    <script src="${contextStatic }/js/jquery.min.js-v=2.1.4"></script>-->
    <script src="${contextStatic }/js/jquery-2.1.1.js"></script>
    <script src="${contextStatic }/js/jquery-ui-1.10.4.min.js"></script>
    <script src="${contextStatic }/js/bootstrap.min.js-v=3.3.5"></script>
	<!--  -->
    <script src="${contextStatic }/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="${contextStatic }/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="${contextStatic }/js/inspinia.js"></script>
    <script src="${contextStatic }/js/plugins/pace/pace.min.js"></script>

    <!-- iCheck 自定义checkbox -->
    <script src="${contextStatic }/js/plugins/iCheck/icheck.min.js"></script>

    <!-- Jvectormap中国地图 -->
    <script src="${contextStatic }/js/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
    <script src="${contextStatic }/js/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>

    <!-- Flot图表绘制 -->
    <script src="${contextStatic }/js/plugins/flot/jquery.flot.js"></script>
    <script src="${contextStatic }/js/plugins/flot/jquery.flot.tooltip.min.js"></script>
    <script src="${contextStatic }/js/plugins/flot/jquery.flot.resize.js"></script>

    <!-- laydate日期选择 -->
    <script src="${contextStatic }/js/plugins/layer/laydate/laydate.js"></script>
    
    <!-- validate参数验证 -->
    <script src="${contextStatic }/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${contextStatic }/js/plugins/validate/messages_zh.min.js"></script>

    <!-- morris折线图 -->
    <script src="${contextStatic }/js/plugins/morris/morris.js"></script>
    <script src="${contextStatic }/js/plugins/morris/raphael-2.1.0.min.js"></script>
    
    <!-- morris -->
    <script src="${contextStatic }/js/plugins/tableexport/Blob.js"></script>
    <script src="${contextStatic }/js/plugins/tableexport/FileSaver.js"></script>
    <script src="${contextStatic }/js/plugins/tableexport/tableExport.js"></script>
    
    <!-- Data Tables 基于jQuery表格插件 -->
    <script src="${contextStatic }/js/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="${contextStatic }/js/plugins/dataTables/dataTables.bootstrap.js"></script>

    <!-- fullcalendar 日程管理 -->
    <script src="${contextStatic }/js/jquery-ui.custom.min.js"></script>
    <script src="${contextStatic }/js/plugins/fullcalendar/fullcalendar.min.js"></script>	

    <!-- 树形菜单 -->
    <script src="${contextStatic }/js/plugins/ztree/jquery.ztree.core.min.js"></script>	

    <!-- 菜单 -->
    <script>
            var s_url=window.location.pathname;
            var now_url = '';
            for(var i = 0;i<$("#side-menu li").length;i++){
                now_url=$("#side-menu li a").eq(i).attr("href");
                if(now_url == s_url){
                    $("#side-menu a").eq(i).parent().addClass("active");
                    $("#side-menu a").eq(i).parent().parent().parent().addClass("active");
                    $("#side-menu a").eq(i).parent().parent().addClass("in");
                }else{
                    $("#side-menu a").eq(i).parent().removeClass("active");
                }
            }        
    </script>



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