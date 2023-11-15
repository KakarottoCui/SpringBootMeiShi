/**
 * bootstraptable增删改
 */

var options2 = {
	toolbar: '#toolbar',
	striped: true,	//隔行换色
	sortName: 'id',   //定义那一列可以排序
	sortOrder: 'desc',	//排序方式
	height: tableHeight(),		//设置高度
	pagination: true,	//显示表格的底部工具栏
	sidePagination: 'server',   //client客户端分页，server服务器分页
	pageNumber: 1,		//初始的页数
	pageSize: 10,		//默认每页数据
	pageList: [10, 15, 50, 100],	//设置分页选择每页显示的条数
	search: true,		//定义右上方的搜索框，输入即可以开始搜索
	showColumns: true,	//选列的下拉菜单
	showRefresh: true,	//刷新按钮
	showToggle: true,	//视图切换
	toolbarAlign: 'left',	//自定义按钮位置
	clickToSelect: true,	//点击行选中
	singleSelect: true,		//单选
	urls: '',
	addurl: 'add',
	editurl: 'edit',
	delurl: 'delete',
	saveurl: 'save',
	queryParams: function (param){	//传递参数
		var params = {};
        params['offset'] = param.offset; // 页码
        params['limit'] = param.limit; // 条数
        params['search'] = param.search; // 搜索内容
        params['sort'] = param.sort; // 排序字段
        params['order'] = param.order; // 排序方式
        return params;
	}
}

var ops2=null;
var obj = null;
var bootstrapTable = function(data, dom){
	obj = dom;
	//表格参数
	ops2 = $.extend({}, options2, data);
	this.init = function(){
		dom.bootstrapTable(ops2);
	}

	//新增
	this.btn_add = function btn_add(){
		var addurl = ops2.urls+"/"+ops2.addurl;
		var dialog = $('<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="myModalLabel"></div>');
		dialog.load(addurl);
		$("body").append(dialog);
		/*弹出模态框,绑定关闭后的事件*/
		dialog.modal().on('hidden.bs.modal', function () {
			//删除
	    	dialog.remove();
	    });
	}

	//修改
	this.btn_edit = function btn_edit(){
		var str = obj.bootstrapTable('getSelections');
		if(str.length != 1){
			bootbox.alert("请选中一行进行编辑");
			return ;
		}
		var editurl = ops2.urls+"/"+ops2.editurl;
		var dialog = $('<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="myModalLabel"></div>');
		var id = str[0].id;
		dialog.load(editurl+"?id="+id);
		/*添加到body中*/
		$("body").append(dialog);
		/*弹出模态框,绑定关闭后的事件*/
		dialog.modal().on('hidden.bs.modal', function () {
			//删除模态框
	    	dialog.remove();
	    });
	}

	/*删除*/
	this.btn_delete = function btn_delete(){
		var delurl = ops2.urls+"/"+ops2.delurl;
		var str = obj.bootstrapTable('getSelections');
		if(str.length != 1){
			bootbox.alert("请选中一行进行删除");
		}else{
			bootbox.confirm("确定删除选中这一行吗?", function(s){
				if(s){
					var id = str[0].id;
					$.post(delurl,{id:id},function(){
						/* refresh刷新 */
						obj.bootstrapTable('refresh');
						bootbox.alert('<h4>'+"删除成功！"+'</h4>');
					});
				}
			});
		}
	}

}
