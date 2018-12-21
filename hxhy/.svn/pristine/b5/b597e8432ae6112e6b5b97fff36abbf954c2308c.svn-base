/**

 @Name：order 管理员管理模块控制
 @Author：偶木
 @Date：2018-08-28
    
 */

 
layui.define(['table', 'form', 'laydate'], function(exports){
	var $ = layui.$,
	admin = layui.admin,
	view = layui.view,
	table = layui.table,
	laydate = layui.laydate,
	form = layui.form;
	
	//税收列表
	table.render({
		elem: '#OM-finance-revenue',
		url: '/manager/finance/revenue/info', //模拟接口
		cols: [[
			{type: 'checkbox', fixed: 'left'},
			{field: 'id', title: '编号'},
			{field: 'type_name', title: '类型'},
			{field: 'threshold', title: '起征点'},
			{field: 'ceiling', title: '起征上限'},
			{field: 'tax_rate', title: '税率（%）'},
			{field: 'add_time', title: '添加时间'},
			{title: '操作', width: 150, align:'center', fixed: 'right', toolbar: '#table-tookbar-revenue'}
		]],
		page: true,
		limit: 30,
		height: 'full-320',
		
		//ajax
		contentType: 'application/json; charset=UTF-8',
		method: 'post',
		request: {
			limitName: 'size'
		},
		response: {
			statusCode: 200
		},
		text: '对不起，加载出现异常！'
	});
  
	//税收列表_监听工具条
	table.on('tool(OM-finance-revenue)', function(obj){
		var data = obj.data;
		if(obj.event === 'del'){
			layer.confirm('确定删除此信息吗？', function(index){
				layer.close(index);
				
				//数据封装
				var req = {"id": data.id};
				//执行 Ajax 后重载
	          	$.ajax({
					type: "POST",
					url: "/manager/finance/revenue/delete",
					dataType:'json',//预期服务器返回的数据类型
					contentType: "application/json; charset=utf-8",
					async: false,
					data: JSON.stringify(req),
					success: function(data){
						layer.open({
	                        content: data.msg,
	                        btn: ['确认'],
	                        yes: function(index, layero) {
	                        	layer.close(index);
	                        	table.reload('OM-finance-revenue', null);
	                        }
	                    });
					}, error: function(){
						location.hash='/system/404';
					}
		    	});
			});
		} else if(obj.event === 'edit'){
			view('OM-module-temp').render('finance/component/taxes-edit', data).done(function() {
          		console.log(data);
				if(data.type == 1) {
					$('#h_type').append("<option value=''>请选择类型</option>"
							+ "<option value='1' selected>个人所得税</option>"
							+ "<option value='2'>公司税收</option>");
          		} else if(data.type == 2) {
          			$('#h_type').append("<option value=''>请选择类型</option>"
							+ "<option value='1'>个人所得税</option>"
							+ "<option value='2' selected>公司税收</option>");
          		} else {
          			$('#h_type').append("<option value='' selected>请选择类型</option>"
							+ "<option value='1'>个人所得税</option>"
							+ "<option value='2'>公司税收</option>");
          		}
				
				//动态加载
				form.render();
				
				//提交监听
				form.on('submit(OM-form-submit)', function(data){
		    		var field = data.field;

		    		//校验
		    		if(field.threshold < 0) {
	      				layer.msg('请填写正确的税收起征点');
	      				return false;
	      			}
		    		if(field.ceiling < 0) {
	      				layer.msg('请填写正确的起征上限');
	      				return false;
	      			}
		    		if(field.tax_rate < 0) {
	      				layer.msg('请填写正确的税率');
	      				return false;
	      			}

		    		//提交
		    		$.ajax({
		    			type: "POST",
						url: "/manager/finance/revenue/update",
						dataType:'json',//预期服务器返回的数据类型
						contentType: "application/json; charset=utf-8",
						async: true,
						data: JSON.stringify(field),
						success: function(data){
							//直接跳转不做任何处理且提示
							layer.open({
	                  	      	content: data.msg,
	                   	     	btn: ['确认'],
	                   	     	yes: function(index, layero) {
	                        		layer.close(index);
	                        		//刷新当前页面
	        			    		window.location.reload();
	                        	}
	                   	 	});
						}, error: function(){
							location.hash='/system/404';
						}
		    		});
		    		return false;
		  		});
			});
		}
	});
	
	//员工信息
	table.render({
		elem: '#OM-system-employee',
		url: '/manager/finance/employee/info',
		cols: [[
			{type: 'checkbox', fixed: 'left'},
			{field: 'id', title: '编号'},
			{field: 'position', title: '职位'},
			{field: 'name', title: '姓名',},
			{field: 'work_name', title: '是否在职'},
			{field: 'basics_money', title: '基本工资'},
			{field: 'money', title: '月薪总薪酬'},
			{field: 'department', title: '部门'},
			{field: 'title', title: '方案标题'},
			{field: 'description', title: '描述'},
			{title: '操作', width: 200, align: 'center', fixed: 'right', toolbar: '#table-toolbar-employee'}
		]],
		page: true,
		limit: 30,
		height: 'full-320',
		
		//ajax
		contentType: 'application/json; charset=UTF-8',
		method: 'post',
		request: {
			limitName: 'size'
		},
		response: {
			statusCode: 200
		},
		text: '对不起，加载出现异常！'
	});
  
	//员工信息_监听工具条
	table.on('tool(OM-system-employee)', function(obj){
		var data = obj.data;
		if(obj.event === 'edit'){
			view('OM-module-temp').render('finance/component/employee-edit', data).done(function() {
				
				//执行 Ajax 后重载
	      		$.ajax({
					type: "POST",
					url: "/manager/finance/employee/scheme",
					dataType:'json',//预期服务器返回的数据类型
					contentType: "application/json; charset=utf-8",
					async: false,
					data: JSON.stringify({"user_id":data.user_id, "department_id":data.department_id}),
					success: function(res){
						for(var i=0; i < res.data.length; i++) {
							var obj = res.data[i];
							if(obj.id == data.work_id) {
								$("#h_work_id").append("<option value=" + obj.id + " selected>"+obj.title+"</option>");
							} else {
								$("#h_work_id").append("<option value=" + obj.id + ">"+obj.title+"</option>");
							}
						}
					}, error: function(){
						location.hash='/system/404';
					}
	    		});
				//动态加载
				form.render();
				//提交监听
				form.on('submit(OM-form-submit)', function(data){
					var field = data.field;
					
					if(field.is_work == 'on') {
		    			field.is_work = 1;
		    		} else {
		    			field.is_work = 0;
		    		}
					if(field.is_attendance == 'on') {
		    			field.is_attendance = 1;
		    		} else {
		    			field.is_attendance = 0;
		    		}
					if(field.is_settlement == 'on') {
		    			field.is_settlement = 1;
		    		} else {
		    			field.is_settlement = 0;
		    		}
					if(field.basics_money < 0) {
	      				layer.msg('请填写正确的基本薪酬');
	      				return false;
	      			}
					if(field.money < 0) {
	      				layer.msg('请填写正确的奖金');
	      				return false;
	      			}
					
					//提交
		    		$.ajax({
		    			type: "POST",
						url: "/manager/finance/employee/update",
						dataType:'json',//预期服务器返回的数据类型
						contentType: "application/json; charset=utf-8",
						async: false,
						data: JSON.stringify(field),
						success: function(data){
							//直接跳转不做任何处理且提示
							layer.open({
	                  	      	content: data.msg,
	                   	     	btn: ['确认'],
	                   	     	yes: function(index, layero) {
	                        		layer.close(index);
	                        		//刷新当前页面
	        			    		window.location.reload();
	                        	}
	                   	 	});
						}, error: function(){
							location.hash='/system/404';
						}
		    		});
		    		return false;
				});
			});
		}
	});
	
	//额外费用接口
	table.render({
		elem: '#OM-finance-extra',
		url: '/manager/finance/extra/info',
		cols: [[
			{type: 'checkbox', fixed: 'left'},
			{field: 'id', title: '编号'},
			{field: 'monthy', title: '月份'},
			{field: 'name', title: '员工名称'},
			{field: 'title', title: '标题'},
			{field: 'description', title: '描述'},
			{field: 'money', title: '金额'},
			{field: 'extra_type', title: '费用类型'},
			{field: 'type', title: '资金流向'},
			{field: 'state', title: '作用对象'},
			{field: 'use_date', title: '使用日期'},
			{field: 'add_date', title: '添加时间'},
			{title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#table-toolbar-extra'}
		]],
		page: true,
		limit: 30,
		height: 'full-320',
		
		//ajax
		contentType: 'application/json; charset=UTF-8',
		method: 'post',
		request: {
			limitName: 'size'
		},
		response: {
			statusCode: 200
		},
		
		text: '对不起，加载出现异常！'
	});
  
	//额外费用_监听工具条
	table.on('tool(OM-finance-extra)', function(obj){
		var data = obj.data;
		
		if(obj.event === 'del'){
			layer.confirm('确定删除此信息吗？', function(index){
				layer.close(index);
				
				//数据封装
				var req = {"id": data.id};
				//执行 Ajax 后重载
				$.ajax({
					type: "POST",
					url: "/manager/finance/extra/delete",
					dataType:'json',//预期服务器返回的数据类型
					contentType: "application/json; charset=utf-8",
					async: false,
					data: JSON.stringify(req),
					success: function(data){
						layer.open({
							content: data.msg,
							btn: ['确认'],
							yes: function(index, layero) {
								layer.close(index);
								table.reload('OM-finance-extra', null);
							}
						});
					}, error: function(){
						location.hash='/system/404';
					}
				});
			});
		} else if(obj.event === 'edit') {
			view('OM-module-temp').render('finance/component/premium-edit', data).done(function() {
				
				//初始化
				$(".personage").css("display","none");
				$("#h_employee").append("<option value='' selected>请选择员工</option>");
	    		$.ajax({
					type: "POST",
					url: "/manager/confine/user/employee",
					dataType:'json',//预期服务器返回的数据类型
					contentType: "application/json; charset=utf-8",
					async: false,
					success: function(data2) {
						var list = data2.data;
						for(var i=0; list.length > i; i++) {
							var obj = list[i];
							if(obj.name == data.name) {
								$(".personage").css("display","block");
								$("#h_employee").append("<option value="+obj.user_id+" selected>"+obj.name+"</option>");
							} else {
								$("#h_employee").append("<option value="+obj.user_id+">"+obj.name+"</option>");
							}
						}
					}, error: function(){
						location.hash='/system/404';
					}
	    		});
	    		
	    		//
				if(data.type == 1) {
					$("#h_type").append("<option value=''>请选择资金流向</option>"
							+ "<option value='1' selected>支出</option>"
							+ "<option value='2'>收入</option>");
				} else if(data.type == 2) {
					$("#h_type").append("<option value=''>请选择资金流向</option>"
							+ "<option value='1'>支出</option>"
							+ "<option value='2' selected>收入</option>");
				} else {
					$("#h_type").append("<option value='' selected>请选择资金流向</option>"
							+ "<option value='1'>支出</option>"
							+ "<option value='2'>收入</option>");
				}
				
				//
				if(data.state == 1) {
					$("#h_state").append("<option value=''>请选择作用对象</option>"
							+ "<option value='1' selected>公司</option>"
							+ "<option value='2'>个人</option>");
				} else if(data.state == 2) {
					$("#h_state").append("<option value=''>请选择作用对象</option>"
							+ "<option value='1'>公司</option>"
							+ "<option value='2' selected>个人</option>");
				} else {
					$("#h_state").append("<option value='' selected>请选择作用对象</option>"
							+ "<option value='1'>公司</option>"
							+ "<option value='2'>个人</option>");
				}
				
				//
				if(data.extra_type == 1) {
					$("#h_extra_type").append("<option value=''>请选择费用类型</option>"
							+ "<option value='1' selected>补发工资</option>"
							+ "<option value='2'>餐费</option>"
							+ "<option value='3'>奖金</option>"
							+ "<option value='4'>加班费</option>"
							+ "<option value='5'>费用报销</option>"
							+ "<option value='6'>公司支付</option>"
							+ "<option value='7'>公司收入</option>");
				} else if(data.state == 2) {
					$("#h_extra_type").append("<option value=''>请选择费用类型</option>"
							+ "<option value='1'>补发工资</option>"
							+ "<option value='2' selected>餐费</option>"
							+ "<option value='3'>奖金</option>"
							+ "<option value='4'>加班费</option>"
							+ "<option value='5'>费用报销</option>"
							+ "<option value='6'>公司支付</option>"
							+ "<option value='7'>公司收入</option>");
				} else if(data.state == 3) {
					$("#h_extra_type").append("<option value=''>请选择费用类型</option>"
							+ "<option value='1'>补发工资</option>"
							+ "<option value='2'>餐费</option>"
							+ "<option value='3' selected>奖金</option>"
							+ "<option value='4'>加班费</option>"
							+ "<option value='5'>费用报销</option>"
							+ "<option value='6'>公司支付</option>"
							+ "<option value='7'>公司收入</option>");
				} else if(data.state == 4) {
					$("#h_extra_type").append("<option value=''>请选择费用类型</option>"
							+ "<option value='1'>补发工资</option>"
							+ "<option value='2'>餐费</option>"
							+ "<option value='3'>奖金</option>"
							+ "<option value='4' selected>加班费</option>"
							+ "<option value='5'>费用报销</option>"
							+ "<option value='6'>公司支付</option>"
							+ "<option value='7'>公司收入</option>");
				} else if(data.state == 5) {
					$("#h_extra_type").append("<option value=''>请选择费用类型</option>"
							+ "<option value='1'>补发工资</option>"
							+ "<option value='2'>餐费</option>"
							+ "<option value='3'>奖金</option>"
							+ "<option value='4'>加班费</option>"
							+ "<option value='5' selected>费用报销</option>"
							+ "<option value='6'>公司支付</option>"
							+ "<option value='7'>公司收入</option>");
				} else if(data.state == 6) {
					$("#h_extra_type").append("<option value=''>请选择费用类型</option>"
							+ "<option value='1'>补发工资</option>"
							+ "<option value='2'>餐费</option>"
							+ "<option value='3'>奖金</option>"
							+ "<option value='4'>加班费</option>"
							+ "<option value='5'>费用报销</option>"
							+ "<option value='6' selected>公司支付</option>"
							+ "<option value='7'>公司收入</option>");
				} else if(data.state == 7) {
					$("#h_extra_type").append("<option value=''>请选择费用类型</option>"
							+ "<option value='1'>补发工资</option>"
							+ "<option value='2'>餐费</option>"
							+ "<option value='3'>奖金</option>"
							+ "<option value='4'>加班费</option>"
							+ "<option value='5'>费用报销</option>"
							+ "<option value='6'>公司支付</option>"
							+ "<option value='7' selected>公司收入</option>");
				} else {
					$("#h_extra_type").append("<option value='' selected>请选择费用类型</option>"
							+ "<option value='1'>补发工资</option>"
							+ "<option value='2'>餐费</option>"
							+ "<option value='3'>奖金</option>"
							+ "<option value='4'>加班费</option>"
							+ "<option value='5'>费用报销</option>"
							+ "<option value='6'>公司支付</option>"
							+ "<option value='7'>公司收入</option>");
				}
				
				laydate.render({
		  	      	elem: '#test-laydate-normal-cn',
		  	      	type: 'month',
		  	      	value: data.monthy,
		  	      	format: 'yyyyMM'
		  	    });
		  		
				if(data.use_date != '') {
					laydate.render({
						elem: '#test-laydate-normal-cn2',
						value: data.use_date,
						type: 'datetime'
					});
				}
				
				//动态加载
				form.render();
          		
          		//作用对象监听
				form.on('select(h_state)', function(data){
					if(data.value == '2') {
						$(".personage").css("display","block");
					} else {
						$(".personage").css("display","none");
					}
					//动态加载
					form.render();
				});
          		
				//提交监听
				form.on('submit(OM-form-submit)', function(data){
		    		var field = data.field;
		    		
		    		//校验
		    		if(field.state === '2' && field.user_id == '') {
	      				layer.msg('请填写员工名称');
	      				return false;
	      			} else if(field.user_id == '') {
	      				field.user_id = '0';
	      			}
		    		if(field.money < 0) {
	      				layer.msg('请填写正确的金额');
	      				return false;
	      			}
		    		if(field.use_date != '') {
		    			field.use_date = new Date(field.use_date).getTime()
		    			console.log(field.use_date)
		    		}

		    		//提交
		    		$.ajax({
		    			type: "POST",
						url: "/manager/finance/extra/update",
						dataType:'json',//预期服务器返回的数据类型
						contentType: "application/json; charset=utf-8",
						async: false,
						data: JSON.stringify(field),
						success: function(data){
							//直接跳转不做任何处理且提示
							layer.open({
	                  	      	content: data.msg,
	                   	     	btn: ['确认'],
	                   	     	yes: function(index, layero) {
	                        		layer.close(index);
	                        		//刷新当前页面
	        			    		window.location.reload();
	                        	}
	                   	 	});
						}, error: function(){
							location.hash='/system/404';
						}
		    		});
		    		return false;
		  		});
			});
		}
	});
	
	//资金结算
	table.render({
		elem: '#OM-finance-payment-employee',
		url: '/manager/finance/payment/employee',
		cols: [[
			{type: 'checkbox', fixed: 'left'},
			{field: 'id', title: '编号'},
			{field: 'monthy', title: '月份'},
			{field: 'name', title: '员工名称'},
			{field: 'basics_money', title: '基础薪酬'},
			{field: 'money', title: '基础奖金'},
			{field: 'work_date', title: '出勤天数'},
			{field: 'all_date', title: '满勤天数'},
			{field: 'overtime_pay', title: '加班费'},
			{field: 'leave_money', title: '请假扣除'},
			{field: 'late_money', title: '处罚金'},
			{field: 'bonus', title: '奖金'},
			{field: 'meal_fee', title: '餐费'},
			{field: 'back_pay', title: '补发工资'},
			{field: 'per_social_insurance', title: '公积金'},
			{field: 'per_reserved_fund', title: '社保'},
			{field: 'individual_income_tax', title: '税收'},
			{field: 'real_money', title: '实收金额'},
			{field: 'remark', title: '备注'},
		]],
		page: true,
		limit: 30,
		height: 'full-320',
		
		//ajax
		contentType: 'application/json; charset=UTF-8',
		method: 'post',
		request: {
			limitName: 'size'
		},
		response: {
			statusCode: 200
		},
		
		text: '对不起，加载出现异常！'
	});
	
	exports('finance', {})
});