<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/Public/taglib.jsp" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/Public/csslib.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>模板页面</title>
</head>
<body>

<div id="wrapper"> 
	<%@ include file="/Public/include/layout.jsp" %>
	<%-- Body主面板内容 --%>
	<%@ include file="/Public/include/category/list.jsp" %>
	<%-- Body主面板内容2 --%>
	<%@ include file="/Public/include/category/mylist.jsp" %>
	

</div>

<%@ include file="/Public/jslib.jsp" %>

<!-- 测试数据 -->
<script src="${context }/products.js"></script>

<!-- 表格管理  表格id为category_table -->
<script>
function category_table_info ( d ) {
    // `d` is the original data object for the row
    return '<table class="table table-striped">'+
    '<tr>'+
        '<td>备注:</td>'+
        '<td>'+d.repayment_remarks+'</td>'+
    '</tr>'+
    '</table>';
};

$(document).ready(function() {
	//alert('${context}'+'/123');
    //DataTable表格插件
    var category_table =  $("#category_table").DataTable({
          "language": {//I18n国际化
                "decimal":        "",
                "emptyTable":     "没有数据",
                "info":           "从 第_START_ 到 _END_条 /共 _TOTAL_ 条数据",
                "infoEmpty":      "从 0到 0 /共 0条数据",
                "infoFiltered":   "(从 _MAX_ 条数据中检索)",
                "infoPostFix":    "",
                "thousands":      ",",
                "lengthMenu":     "每页显示 _MENU_ 条记录",
                "loadingRecords": "加载中...",
                "processing":     "搜索中...",
                "search":         "搜索:",
                "zeroRecords":    "没有检索到数据",
                "paginate": {
                    "first":      "首页",
                    "last":       "尾页",
                    "next":       "后一页",
                    "previous":   "前一页"
                },
          },
          // 设置相关排列 - col-sm bootstrap来做响应式编程
          "dom": 't<".col-sm-4"l><".col-sm-4"i><".col-sm-4"p>',
          "orderMulti": true, //可以多行排序
          "processing": true, //进度条
          //"serverSide": true,  //服务端#分页 
           ajax: {
                 url: '${context}/admin/category/ajaxquery',
                 dataSrc: '' // data:{分类列表}，page：1
           },
           /**/
          //data: productConfig,
          //"columnDefs": [ {
          //"targets": 8,
          //"orderable": false
          //} ],
          "columns": [
					{
					    "class":          'details-control',
					    "orderable":      false,
					    "data":           null,
					    "defaultContent": ''
					},
                    { data: "id" },
                    { data: "name"},
                    {
                    	"orderable":      false,
                    	"sWidth": "50px", 
                    	"data": "id", 
                    	"mRender": 
                    	function(data, type, full) {  
                        	return '<a href ="__URL__/id/'+data+'"><span class="fa fa-plus-circle" style="font-size:20px;"></span></a>'+  
                        	'<a href ="__URL__/id/'+data+'"><span class="fa fa-minus-circle" style="font-size:20px;"></span></a>';  
                    }  
                }
                    
          ],
          "order": [ 1, 'asc' ],                         //按第几列排序
          "lengthMenu": [ [ 5, 20, 100], [ 5, 20, 100] ] //每页显示多少条
    });
   //------------------------ 
    $('#id').on('change', function () { //根据输入数据查找
    	category_table
        .columns(1)
        .search(this.value)
        .draw();
    });
   
    $('#name').on('change', function () {
    	category_table
        .columns(2)
        .search(this.value )
        .draw();
	});
   
    //显示备注信息
    $('#category_table tbody').on('click', 'td.details-control', function () {
        var tr = $(this).closest('tr');
        var row = category_table.row( tr );
        if ( row.child.isShown() ) {
            // This row is already open - close it
            row.child.hide();
            tr.removeClass('shown');
        }
        else {
            // Open this row
            row.child( category_table_info (row.data()) ).show();
            tr.addClass('shown');
        }
} );
});
</script>
</body>
</html>