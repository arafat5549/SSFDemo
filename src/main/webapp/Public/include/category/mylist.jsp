<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-sm-12">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>分类管理-自己实现分页JspForEach循环</h5>
                <div class="ibox-tools">
                    <a class="collapse-link">
                        <i class="fa fa-chevron-up"></i>
                    </a>
                    <a class="close-link">
                        <i class="fa fa-times"></i>
                    </a>
                </div>
            </div>
            <div class="ibox-content">
                <div class="table-responsive">
                    <table class="table table-striped table-bordered table-hover" id="mytable">
                        <thead>
                            <tr>
                                  <th></th>
                                  <th>编号</th>
                                  <th>名称</th>
                                  <th>操作</th>
                            </tr>
                        </thead>
                        <tbody>
                        	<c:forEach items="${pagenation.data }" var="c">
                        	    <tr>
                                  <th></th>
                                  <th>${c.id }</th>
                                  <th>${c.name }</th>
                                  <th><a href="#">删除</a></th>
                           		 </tr>
                        	</c:forEach>
                        </tbody>
                    </table>
                </div>	
                <!-- 自定义标签库 比include方法相比 可以传递参数进去 -->
                <tags:pagenation pagenation="${pagenation}" pageSize="10"></tags:pagenation>
                
            </div>
        </div>
	</div>
</div>