<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="row">
    <div class="col-sm-12">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>添加分类</h5>
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
            
                <form enctype="multipart/form-data" method="post" class="form-horizontal" action="${context }/admin/user/userInfo" id="add_userInfo">
                     
                    <div class="form-group">
                        <label class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" name="username" value="${session_user.username }">
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-2 control-label">用户头像</label>
                        <div class="col-sm-3">
                            <img src="${context }/${session_user.avartarUrl }" class="img-responsive img-rounded" width="300px">
                            <input type="file" name="avatarUrl">
                        </div>
                    </div>
					

                    <div class="form-group">
                        <div class="col-sm-4 col-sm-offset-2">
                            <button class="btn btn-primary" type="submit">修改</button>
                        </div>
                    </div>
                   
                </form>
            </div>
        </div>
    </div>
</div>