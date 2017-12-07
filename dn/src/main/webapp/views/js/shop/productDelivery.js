
var dataOptions = {
	baseUrl:"",
	basePath:"",
	addInit:function(){},// 添加弹窗初始化
	editInit:function(data){},// 修改数据加载完成初始化
	saveInit:function(data){}// 保存前执行操作
};


$(function(){
	$('#id').combotree({
		checkbox:true,
		required: true,
		multiple:true,
		state:closed,
		onLoadSuccess:function () {
			$("#id").combotree('tree').tree("collapseAll");
		},
		onlyLeafCheck:true,
		panelWidth:200,
		panelHeight:300,
		url:webRoot+'/web/product/delivery/searchCitysList.htm'
	}) ;
}) ;

/**添加*/
function gridAdd(){
	clearTextBox();
	//异步请求加载数据
	/*$.ajax({
		type:"GET",
		dataType:"json",
		url:dataOptions.baseUrl+'getProvinceAndCityList.htm',
		success:function (data) {
			console.log(data)//[{provinceId:value,cityIds:[value1,value2,value3]}
			var html="";
			for(var i=0;i<data.length;i++){
				//生成tr
				var str ="<tr class='mf-line' id='deliveryAdressTr"+i+"'>dsd</tr>";
				console.log(str)
				html+=str;
				//将tr追加到table
				/!*var provinceId = data[i].provinceId;
				 //生成省份td拼接到tr
				 var str2 = "<td><input type='radio'  name='deliveryAdress' value='"+provinceId+"'></td>";
				 console.log(str2)
				 //$(str2).appendTo('deliveryAdressTr'+i);
				 var cityIds = [];
				 cityIds = (data[i].cityIds).split(",");

				 for (var y=0;y<cityIds.length;y++){
				 //生成省份下的城市td拼接到tr
				 var cityId =cityIds[y];
				 var str3 = "<td><input type='radio'  name='deliveryAdress' value='"+cityId+"'></td>";
				 console.log(str3)
				 $(str3).appendTo('deliveryAdressTr'+i);
				 }*!/

			}
			$('#add-data-table').append($(html))
		}
	})*/
	$('#add-data-form').form('clear');// 清除form表单数据
	$( "#add-dialog" ).dialog("open").dialog('setTitle','添加');
};

//保存
var save = function(){
	if(!$("#add-data-form").form('validate'))return;
	var url = dataOptions.baseUrl+'save.htm';/* 配置保存数据地址 */
	var check = function(){};
		//TODO js 获取父节点
	/* 请求成功后回调函数success  data为请求服务器返回的参数*/
	var success = function(ret){
		if(ret && ret.status == 10001){
			$('#add-dialog').dialog('close');
			mygrid.datagrid('reload');
			$('#add-data-form').form('clear');
			$.messager.show({
				title:'提示',
				msg:'保存成功',
				timeout:5000,
				showType:'slide'
			});
		}else {
			parent.$.messager.alert("错误",ret.msg,"error");
		}
	};
	var error = null; // 请求服务器失败回调函数error 传递null使用默认error处理
	formSubmit($('#add-data-form'),url,check,success);
};

//修改
var gridEdit = function(rowIndex){
	var rowData = getData(rowIndex);// 取到选择的数据
	var url = dataOptions.baseUrl + 'data.htm';/* 配置查询要修改的数据查询地址 */
	var data = {ids:rowData.id}; /* 请求的参数内容 */
	/* 请求成功后回调函数success  data为请求服务器返回的参数*/
	var success = function(ret){
		if(ret && ret.status == 10001){
			$('#add-data-form').form('clear');// 清除form表单数据
			var _data = ret.data.data[0]
			// 初始化
			$('input[name="oldId"]').val(_data.id);
			$('#price1').textbox('setValue',_data.price);
			$('#id').combotree('setValues',_data.citys.split(',')) ;
			var tree = $('#id').combotree('tree')
			var nodes = tree.tree('getChecked')
			if(nodes){
				for(var i=0;i<nodes.length;i++){
					console.log(nodes[i])
					tree.tree('expandTo',nodes[i].target)
				}
			}
			console.log(nodes)
			/*myLoad("#add-data-form",ret.data.data[0]);*/
			$('#add-dialog').dialog('setTitle','修改');
			$('#add-dialog').dialog('open');
		}else parent.$.messager.alert("错误",ret.msg,"error");
	};
	var error = null; // 请求服务器失败回调函数error 传递null使用默认error处理
	// ajav异步查询数据
	asynPAjaxMin(url,data,success,error);
};

var gridDeliveryDel = function () {
	$('#delivery-data-form').form('clear');// 清除form表单数据
	var sele = mygrid.datagrid("getSelections");
	if(sele.length!=1){
		$.messager.alert("确认","只能选择一条数据")
		return
	}
	console.log(sele)
}
