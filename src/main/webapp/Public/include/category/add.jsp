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
                <form method="post" class="form-horizontal" action="${context }/admin/category/add" id="add_category">
                    
                    <div class="form-group">
                         <label class="col-sm-2 control-label">分类名</label>
                         <div class="col-md-3">
                            <input type="text" class="form-control" name="name">
                        </div>
                    </div>
                    
                    
                    <div class="form-group">
                         <label class="col-sm-2 control-label">父分类</label>
                         <div class="col-md-3">
								<ul id="treeDemo" class="ztree"></ul>
                        </div>
                        <div class="col-md-3">
                        	<input type="text" class="form-control"  disabled="disabled" id="parentName">
                        </div>
                        <div class="col-md-3">
                            <input type="hidden" class="form-control"  name="parentId" id="parentId">
                        </div>
                        
                        <div class="col-md-3">
                            <input type="hidden" class="form-control"  name="parentIds" id="parentIds">
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <div class="col-sm-4 col-sm-offset-2">
                            <button class="btn btn-primary" type="submit">添加</button>
                        </div>
                    </div>
                    <!--  
                    <div class="form-group">
                        <label class="col-sm-2 control-label">借款人</label>
                        <div class="col-sm-3">
                            <select class="form-control m-b" name="borrow_uid">
                                <foreach name="user_list" item="list" key="k">
                                <option value="{$list.id}">{$list.name}</option>
                                </foreach>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">合同号</label>
                        <div class="col-md-3">
                            <input type="text" class="form-control" name="contract_number">
                        </div>
                    </div>
					
				       <div class="form-group">
					<label class="col-sm-2 control-label">借款期限</label>
					<div class="col-md-3">
				                   <select class="form-control m-b" name="borrow_duration">
				                       <option value="0.5">0.5</option>
			                                  <option value="1" selected="">1</option>
				                       <option value="2">2</option>
				                       <option value="3">3</option>
				                       <option value="4">4</option>
				                       <option value="5">5</option>
				                       <option value="6">6</option>
				                       <option value="7">7</option>
				                       <option value="8">8</option>
				                       <option value="9">9</option>
				                       <option value="10">10</option>
				                       <option value="11">11</option>
				                       <option value="12">12</option>
				                   </select>
			                            <span class="help-block m-b-none">单位:月</span>
					 </div>
					</div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">借款金额</label>
                        <div class="col-md-3">
                            <input type="text" class="form-control" name="borrow_money" aria-required="true" aria-invalid="true">
                            <span class="help-block m-b-none">金额:<span id="input_money_format">零元整</span></span> 
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">利率</label>
                        <div class="col-md-3">
                            <input type="text" class="form-control" name="borrow_interest_rate" aria-required="true" aria-invalid="true">
                            <span class="help-block m-b-none">%</span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">手续费率</label>
                        <div class="col-md-3">
                            <input type="text" class="form-control" name="borrow_procedures_rate" aria-required="true" aria-invalid="true">
                            <span class="help-block m-b-none">%</span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">借款时间</label>
                        <div class="col-md-3">
                            <input id="hello" class="laydate-icon form-control layer-date" name="borrow_time" aria-required="true" aria-invalid="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">还款方式</label>
                        <div class="col-md-3">
                            <select class="form-control m-b" name="repayment_type">
                                <option value="付息还本">付息还本</option>
                                <option value="到期本息">到期本息</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-4 col-sm-offset-2">
                            <button class="btn btn-primary" type="submit">添加</button>
                        </div>
                    </div>
                    -->
                </form>
            </div>
        </div>
    </div>
</div>