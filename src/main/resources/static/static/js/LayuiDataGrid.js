/**
 * layui数据表格
 */
//设置默认表格属性
var options = {
	height: 'full-230',	//表格高度
	path: '',
	url: '',
	id: 'id',	//用于刷新表格
	where: {}, //查询条件
	addurl: 'add',
	editurl: 'edit',
	delurl: 'delete',
	searchurl: 'search',
	saveurl: 'save',
	winWidth:'500px',
	toolbar: 'default',
	page: true,
	//数据渲染完成后回调
	done: function(res, curr, count){
		//如果是异步请求数据方式，res即为你接口返回的信息。
	    //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
	    //console.log(res);
		//console.info(res.data[1].LAY_TABLE_INDEX);
	    //得到当前页码
	    //console.log(curr); 
	    //得到数据总量
	    //console.log(count);
	}
}

var table = null;
var tableData=null;
var layuiTbale = function(data, rowId){
	var that = this;
	var tableIns;		//渲染表格变量
	this.crId = null;	//当前行id
	this.currRow = null;	//行索引，从0开始
	this.a = 0;
	//表格属性参数
	this.ops = $.extend({}, options, data);
	//加载数据地址
	if(!this.ops.url){
		this.ops.url = this.ops.path+'/page1';
	}
	//根据id取出对应行的值
	this.getRowData = function(id){
		for(var i = 0; i < tableData.length; i++){
			var x = tableData[i];
			if(x.id == id){
				return x;
			}
		}
		return null;
	}
	//获取当前行数据
	this.getCurrRowData = function(){
		if(that.crId){
			for(var i = 0; i < tableData.length; i++){
				var x = tableData[i];
				if(x.id == that.crId){
					return x;
				}
			}
		}
		return null;
	}
	
	//页面加载执行
	this.init = function(){
		layui.use("table", function(){
			table = layui.table;
			
			//数据渲染完成后回调
			that.ops.done = function(res){
				//将表格的所有数据装载到tableData中
				tableData = res.data;
				if(rowId){
					//根据id查找一行数据
					var crow = that.getRowData(rowId);
					if(!crow){
						that.currRow = null;
						that.crId = null;
						return ;
					}
					var crId=crow.LAY_TABLE_INDEX;
					that.currRow=crId;
					that.crId=rowId;
				}else {
					that.currRow = null;
					that.crId = null;
				}
			}
			
			//渲染表格
			tableIns = table.render(that.ops);
			//表格的选中行事件处理
			table.on('row', function(obj){
					if(that.a == 1){
						that.a = 0;
						return ;
					}
					that.currRow=obj.tr.attr('data-index'); 	//当前行索引
					that.crId=obj.data.id;	//当前行id
					obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
					$(".layui-form-radio").removeClass('layui-form-radioed');
					$(".layui-anim").removeClass('layui-anim-scaleSpring');
					obj.tr.find(".layui-form-radio").addClass('layui-form-radioed');
					obj.tr.find(".layui-anim").addClass('layui-anim-scaleSpring');
					
					//parent查找父类元素,找到layui-table-box存放表格的div
					var tableBox = obj.tr.parent().parent().parent().parent('.layui-table-box');
					var tableDiv;
					//获取点击的行或单选按钮
					if (tableBox.find(".layui-table-fixed.layui-table-fixed-l").length>0) {
						tableDiv = tableBox.find(".layui-table-fixed.layui-table-fixed-l");
					} else {
						tableDiv = tableBox.find(".layui-table-body.layui-table-main");
					}
					//获取当前行的单选框
					var checkCell = tableDiv.find("tr[data-index=" + that.currRow + "]").find("td div.layui-table-cell div.layui-form-radio I");
					if (checkCell.length>0) {
						that.a = 1;
						checkCell.click();
					}
					//unbind() 方法移除被选元素的事件处理程序。 
//					$(tableDiv).unbind('click').click(function(){
//						console.log(4+"===");
//					});
					//对td的单击事件进行拦截停止，防止事件冒泡再次触发上述的单击事件  将此代码在页面初始化后执行一次即可以。
					//stopPropagation停止事件的传播
//					$(tableDiv).on("click", function (e) {
//						console.log(3+"==");
//						e.stopPropagation();
//					});
			})
			
			//表格工具栏事件处理
			table.on('toolbar', function(obj){
				switch(obj.event){
				case 'add':
					that.add();
					break;
				case 'updata':
					that.updata();
					break;
				case 'delete':
					that.delete();
					break;
				case 'find':
					that.find();
					break;
				case 'refresh':
					that.refresh();
					break;
				case 'reset':
					that.reset();
					break;
				}
			})
		})
	}
	
	//弹窗，新增
	this.add = function(){
		//新增页面地址
		var addurl = that.ops.path+(that.ops.path.length>0?'/': '')+that.ops.addurl;
		//保存地址
		var saveurl = that.ops.path+(that.ops.path.length>0?'/': '')+that.ops.saveurl;
		$.post(addurl, function(data){
			layer.open({
				type: 1,
				title: '新增',
				area: that.ops.winWidth,
				content: data,
				maxmin: true,
				btn: ['提交', '退出'],
				success: function(layero, index){
					layui.use('form', function(){
						var form = layui.form;
						layero.addClass('layui-form');
						var submitBtn=layero.find('.layui-layer-btn0');
		    			 submitBtn.attr('lay-filter','formVerify').attr('lay-submit','');
		    			 layero.keydown(function(e){
		    				 if(e.keyCode==13){
		    					 submitBtn.click();
		    				 }
		    			 });
		    			 
		    			 form.on('submit(formVerify)', function(data){
		    				$.post(saveurl, data.field, function(result){
		    					if(result.success){
		    						layer.close(index);
		    						table.reload('id',{
		    							
		    						});
		    					}
		    					layer.msg(result.msg,{offset:'rb'});
		    				}) 
		    			 });
					});
				}
			})
		})
	}
	
	//弹窗，修改
	this.updata = function(){
		//修改页面地址
		var editurl = that.ops.path+(that.ops.path.length>0?'/': '')+that.ops.editurl;
		//保存地址
		var saveurl = that.ops.path+(that.ops.path.length>0?'/': '')+that.ops.saveurl;
		if(that.crId){
			$.post(editurl, {id: that.crId}, function(data){
				layer.open({
					type: 1,
					title: '修改',
					area: that.ops.winWidth,
					content: data,
					maxmin: true,
					btn: ['提交', '退出'],
					success: function(layero, index){
						layui.use('form', function(){
							var form = layui.form;
							layero.addClass('layui-form');
							var submitBtn=layero.find('.layui-layer-btn0');
			    			 submitBtn.attr('lay-filter','formVerify').attr('lay-submit','');
			    			 layero.keydown(function(e){
			    				 if(e.keyCode==13){
			    					 submitBtn.click();
			    				 }
			    			 });
			    			 
			    			 form.on('submit(formVerify)', function(data){
			    				$.post(saveurl, data.field, function(result){
			    					if(result.success){
			    						layer.close(index);
			    						table.reload('id',{
			    							
			    						});
			    					}
			    					layer.msg(result.msg,{offset:'rb'});
			    				}) 
			    			 });
						});
					}
				})
			})
		}else{
			layer.msg('请选择一行进行修改', {offset: 'rb'});
		}
	}
	
	this.delete = function(){
		//删除请求地址
		var delurl = that.ops.path+(that.ops.path.length>0?'/': '')+that.ops.delurl;
		if(that.crId){
			layer.confirm('确定要删除吗？', function(index) {
	        	$.post(delurl, {id: that.crId}, function(result){
	        		table.reload('id',{
					});
	        		layer.close(index);
	        		layer.msg(result.msg,{offset:'rb'});
	        	});
        	});
		}else{
			layer.msg('请选择一行进行删除', {offset: 'rb'});
		}
	}
	
	this.reset = function(){
		if(that.crId){
			layer.confirm('确定要重置密码吗？', function(index) {
	        	$.post("system/user/reset", {id: that.crId}, function(result){
	        		table.reload('id',{
					});
	        		layer.close(index);
	        		layer.msg(result.msg,{offset:'rb'});
	        	});
        	});
		}else{
			layer.msg('请选择一行进行删除', {offset: 'rb'});
		}
	}
	
	this.find = function(){
		var searchurl = that.ops.path + (that.ops.path.length > 0? '/': '')+that.ops.searchurl;
		$.post(searchurl, that.ops.where, function(data){
			layer.open({
				title: '查询',
				shade: 0.3,
				maxmin: true,
				content: data,
				area: ['width:' + that.ops.winWidth],
				btn:['查询','清除查询条件','退出'],
				btn2: function(index, layero){
					that.ops.where = {};
					table.reload('id',{
						where: ''
					});
					layer.close(index);
				},
				success: function(layero, index){
					layui.use('form', function(){
  						var form=layui.form;
		    			layero.addClass('layui-form');
			    		var submitBtn=layero.find('.layui-layer-btn0');
			    		submitBtn.attr('lay-filter','formVerify').attr('lay-submit','');
			    		layero.keydown(function(e){
				    		if(e.keyCode==13){
				    			submitBtn.click();
				    		}
				    	});
			    		form.on('submit(formVerify)',function(data){
			    			that.ops.where = data.field;
			    			table.reload('id',{
    							where: that.ops.where
    						});
			    			layer.close(index);
			    		});
  					})
				}
			})
		})
	}
	
	this.refresh = function(){
		table.reload('id',{
			where: that.ops.where
		});
	}
}
