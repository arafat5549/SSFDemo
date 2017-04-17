<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<!-- 侧边菜单-开始 -->
	<nav class="navbar-default navbar-static-side" role="navigation">
		 <div class="sidebar-collapse">
		 	<ul class="nav" id="side-menu">
		 	   <li class="nav-header" style="text-align:center;">
		 	   		<div class="dropdown profile-element"> <span>
                        <a href="#">
                            <img alt="image" class="img-circle" src="${contextStatic }/img/borrow_log_600x600.png" width="80px;"  height="80px;" />
                        </a>
                        </span>
                        <span class="clear">
                        <span class="block m-t-xs" style = "color:#fff;">
                        <strong class="font-bold">后台管理系统</strong>
                        </span> 
                        </span>
                   </div>
		 	   </li>
		 	   
		 	   <li>
                    <a href="#">
                        <i class="fa fa-home"></i>
                        <span class="nav-label">分类管理</span>
                        <span class="fa arrow"></span>
                    </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="${context }/admin/demo/1" data-index="0">分类列表</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="${context }/admin/add" data-index="1">添加分类</a>
                            </li>
                        </ul>
                </li>

				<li>
                    <a href="#">
                        <i class="fa fa-home"></i>
                        <span class="nav-label">借款管理</span>
                        <span class="fa arrow"></span>
                    </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="#" data-index="0">添加借款</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="#" data-index="1">借款列表</a>
                            </li>
                        </ul>
                </li>                
		 	</ul>
		 </div>
	</nav>
	<!-- 侧边菜单-结束 -->

    <!-- 头部导航-开始 -->
    <div id="page-wrapper" class="gray-bg">
		<div class="row border-bottom">
			<nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">

				<div class="navbar-header">
                    <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
				</div>	
					<ul class="nav navbar-top-links navbar-left">
                       <li>
                           <a class="m-r-lg text-muted welcome-message">{$title}</a>
                      </li>
                   </ul>

                    <ul class="nav navbar-top-links navbar-right">
                        <span class="m-r-sm text-muted welcome-message">欢迎你,</span>
                        <li class="dropdown">
                                <a class="dropdown-toggle count-info" data-toggle="dropdown" href="javascript:void(0)">
                                       <strong><span class="text-success">{$Think.session.name}</span></strong>
                                </a>
                                <ul class="dropdown-menu dropdown-messages" style="width: 120px;">
                                    <li>
                                        <strong><a href="#">个人信息</a></strong>
                                    </li>
                                     <li>
                                        <strong><a href="#">退出登陆</a></strong>
                                    </li>
                                </ul>
                          </li>
                    </ul>
			</nav>
		</div>
    <!-- 头部导航-结束 -->
    <div style="padding:10px;"></div>
    
    <!-- 正文部分 -->
