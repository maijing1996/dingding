/**

 @Name：order 管理员管理模块控制
 @Author：偶木
 @Date：2018-08-28
    
 */

 
layui.define(['table', 'form'], function(exports){
	var $ = layui.$,
	admin = layui.admin,
	view = layui.view,
	table = layui.table,
	form = layui.form;
	
	//管理员角色
	table.render({
		elem: '#OM-system-role',
		url: '/manager/role/information/info',
		cols: [[
			{type: 'checkbox', fixed: 'left'},
			{field: 'id', title: '编号', width: 200},
			{field: 'title', title: '角色名称', width: 200},
			{field: 'info', title: '角色描述'},
			{title: '操作', width: 200, align: 'center', fixed: 'right', toolbar: '#table-toolbar-role'}
		]],
		page: false,
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
  
	//管理员角色_监听工具条
	table.on('tool(OM-system-role)', function(obj){
		var data = obj.data;
		if(obj.event === 'del'){
			layer.confirm('确定删除此管理员角色？', function(index){
				layer.close(index);
				
				//数据封装
				var req = {"id": data.id};
				//执行 Ajax 后重载
	          	$.ajax({
					type: "POST",
					url: "/manager/role/delete",
					dataType:'json',//预期服务器返回的数据类型
					contentType: "application/json; charset=utf-8",
					async: false,
					data: JSON.stringify(req),
					success: function(data){
						layer.open({
	                        content: data.desc,
	                        btn: ['确认'],
	                        yes: function(index, layero) {
	                        	layer.close(index);
	                        	table.reload('OM-system-role', null);
	                        }
	                    });
					}, error: function(){
						location.hash='/system/404';
					}
		    	});
			});
		} else if(obj.event === 'edit'){
			//执行 Ajax 后重载
      		$.ajax({
				type: "POST",
				url: "/manager/role/menu",
				dataType:'json',//预期服务器返回的数据类型
				contentType: "application/json; charset=utf-8",
				async: false,
				data: JSON.stringify({}),
				success: function(res){
					var ver = data.power.split(',');
					for(var i=0; i < res.result.length; i++) {
						var list = res.result[i].list;
						for(var j=0; j < list.length; j++) {
							var obj = list[j];
							var val2 = false;
							for(var z=0; z < ver.length; z++) {
								if(obj.id == parseInt(ver[z])) {
									val2 = true;
									break;
								}
							}
							obj.ioc = val2;
						}
					}
					data.result = res.result;
				}, error: function(){
					location.hash='/system/404';
				}
    		});
      		//
			view('OM-module-temp').render('power/component/role-edit', data).done(function() {
				//动态加载
				form.render();
				//提交监听
				form.on('submit(OM-form-submit)', function(data2){
					var field = data2.field;
					
					//获取checkbox[name='like']的值
		            var arr = new Array();
		            $("input:checkbox[name='m_power']:checked").each(function(i){
		                arr[i] = $(this).val();
		            });
		           	field.power = arr.join(",");//将数组合并成字符串
					
					//提交
		    		$.ajax({
		    			type: "POST",
						url: "/manager/role/update",
						dataType:'json',//预期服务器返回的数据类型
						contentType: "application/json; charset=utf-8",
						async: false,
						data: JSON.stringify(field),
						success: function(data){
							//直接跳转不做任何处理且提示
							layer.open({
	                  	      	content: data.desc,
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
	
	//个人薪酬记录
	table.render({
		elem: '#OM-personal-remuneration',
		url: '/manager/personal/remuneration/info',
		cols: [[
			{type: 'checkbox', fixed: 'left'},
			{field: 'monthy', title: '月份'},
			{field: 'work_date', title: '出勤天数'},
			{field: 'all_date', title: '满勤天数'},
			{field: 'basics_money', title: '基本薪酬'},
			{field: 'money', title: '基本奖金'},
			{field: 'total', title: '薪酬小计'},
			{field: 'leave_money', title: '请假扣除'},
			{field: 'late_money', title: '处罚金'},
			{field: 'back_pay', title: '补发工资'},
			{field: 'bonus', title: '奖金'},
			{field: 'log_state', title: '状态'},
			{field: 'per_reserved_fund', title: '公积金'},
			{field: 'per_social_insurance', title: '社保'},
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
	
	exports('center', {})
});