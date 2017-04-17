<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="row"> <!-- 每一个class的row是一个大框 -->
    <div class="col-sm-12">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>分类管理-DataTable前台框架利用JSON来读取</h5>
                <div class="ibox-tools">
                    <a class="collapse-link">
                        <i class="fa fa-chevron-up"></i>
                    </a>
                </div>
            </div>
            <div class="ibox-content">
                <div class="row">
                    <div class="col-sm-12">
                            <p>编号后加 <i class="fa fa-asterisk text-danger"></i> 时此借款有<span class='text-danger'>备注</span></p>
                    </div>
                    <div class="col-sm-3">
                            <div class="input-group m-b"><span class="input-group-btn">
                                    <button type="button" class="btn btn-primary" >编号</button> </span>
                                    <input type="text" class="form-control" id="id" placeholder="精确">
                            </div>
                    </div>
                    <div class="col-sm-3">
                            <div class="input-group m-b"><span class="input-group-btn">
                                    <button type="button" class="btn btn-primary" >名称</button> </span>
                                    <input type="text" class="form-control" id="name" placeholder="模糊">
                            </div>
                    </div>
                    <div class="col-sm-6" id="export-btn">
                            <a data-type="xls" data-table="category_table" href="javascript:;" type="button" class="btn btn-danger btn-sm">导出xls</a>
                    </div>
                </div>
                <div class="table-responsive">
                    <table class="table table-striped table-bordered table-hover dataTables-example" id="category_table">
                        <thead>
                            <tr>
                                  <th></th>
                                  <th>编号</th>
                                  <th>名称</th>
                                  <th>操作</th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
	</div>
</div>