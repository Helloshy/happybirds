var K = window.KindEditor;
var laybox;
var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var requestURL='';
$(function(){
	
	$(window).on('resize.jqGrid', function() {
	    $(grid_selector).jqGrid('setGridWidth', $(".page-content").width());
	})
	var parent_column = $(grid_selector).closest('[class*="col-"]');
	$(document).on('settings.ace.jqGrid', function(ev, event_name, collapsed) {
	    if (event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed') {
	        setTimeout(function() {
	            $(grid_selector).jqGrid('setGridWidth', parent_column.width());
	        }, 0);
	    }
	})
	$(grid_selector).jqGrid({
			url : requestURL,
			mtype : "GET",
			datatype : "json",
			colModel : [{
				label : '所属公司',
				name : 'company_name',
				width : 160,
				align :'center',
				sortable : false
			},{
				label : '用户编号',
				name : 'unid',
				width : 150,
				align :'center',
				sortable : false,
			},{
				label : '',
				name : 'id',
				width : 0,
				align :'center',
				sortable : false,
				hidden:true
			}, {
				label : '用户类型',
				name : 'record_tag',
				width : 130,
				align :'center',
				sortable : false
			},  {
				label : '用户姓名',
				name : 'real_name',
				width : 150,
				align :'center',
				sortable : false,
			}, {
				label : '手机号码',
				name : 'tel',
				width : 150,
				align :'center',
				sortable : false,
			}, {
				label : '用气地址',
				name : 'address',
				width : 150,
				align :'center',
				sortable : false
			},{
				label : '气表类型',
				name : 'meter_type',
				width : 130,
				align :'center',
				sortable : false,
				formatter: function(cellvalue, options, rowObject){
					if(rowObject.status==1){
						if(cellvalue==1){
							return '机械表';
						}
						if(cellvalue==2){
							return 'IC卡表';
						}
					}else {
						return '';
					}
				}
			},{
				label : '气表编号',
				name : 'meter_no',
				width : 150,
				sortable : false
			},{
				label : '上期表字',
				name : 'last_count',
				width : 150,
				sortable : false
			},{
				label : '当期表字',
				name : 'current_count',
				width : 150,
				sortable : false
			},{
				label : '用气量',
				name : 'gasConsum',
				width : 150,
				sortable : false
			},{
				label : '费用',
				name : 'amount',
				width : 150,
				sortable : false
			},{
				label : '当前余额',
				name : 'balance_amount',
				width : 150,
				sortable : false
			},{
				label : '抄表员',
				name : 'recordName',
				width : 150,
				sortable : false
			},{
				label : '抄表时间',
				name : 'record_time',
				width : 150,
				sortable : false
			},{
				label : '审核状态',
				name : 'state',
				width : 150,
				sortable : false,
				formatter: function(cellvalue, options, rowObject){
					return cellvalue == 0?'未审核':'审核';
				}
			},{
				label : '更新时间',
				name : 'update_time',
				width : 160,
				sortable : false,
				formatter: function(cellvalue, options, rowObject){
					return (new Date(cellvalue)).format('yyyy-MM-dd hh:mm:ss')
				}
			},{
				label : '操作账号',
				name : 'user_name',
				width : 160,
				sortable : false
			}],
			caption: "数据列表",
			hidegrid:false,
			multiselect: true,
			rownumbers : true,
			viewrecords : true,
			height : 500,
			rowNum : 20,
			rowList : [10, 20, 30],
			pager : pager_selector,
			altRows : true,
			postData:{'queryParam':$.trim($("#queryParam").val())},//发送查询参数
			loadComplete : function() {
				var table = this;
				setTimeout(function() {
					updatePagerIcons(table);
				}, 0);
			}
		}
	).navGrid(pager_selector, {
		edit : false,
		add : false,
		del : false,
		search : false,
		refresh : true,
		refreshicon : 'ace-icon fa fa-refresh green'
	});
	$(window).triggerHandler('resize.jqGrid');
		
	/**
	 * 列表查询函数
	 */
	$("#queryFun").click(function(){
		   var queryParam = $('#queryParam').val();
		$("#grid-table").jqGrid('setGridParam',{
			url:webRoot + '/meter/batchImport/list.htm',
			postData:{'queryParam':queryParam}, //发送查询数据
			page:1
		}).trigger("reloadGrid"); //重新载入
	});

	initFileInput('uploadFile',webRoot+'/meter/batchImport/saveImport.htm');

	/*$('#uploadFile').fileinput({
		language: 'zh', //设置语言
		uploadUrl:'', //上传的地址
		allowedFileExtensions : ['xls','xlsx'],//接收的文件后缀
		showUpload: false, //是否显示上传按钮
		showCaption: false,//是否显示标题
		browseClass: "btn btn-primary", //按钮样式
		showPreview:false
	});*/

	//上传
	$('#downLoadFun').click(function(){
		window.location.href=webRoot+'/meter/batchImport/download.htm'
	});




});

var imports=function () {
	var titles =new Array();
	var notNullTitles = new Array();

	$.importExcel.open({
		'importPageUrl':webRoot+'',
		'submitUrl':'',
		'templateName':'',
		'titles':titles,
		'notNullTitles':notNullTitles

	})
}

//初始化fileinput控件（第一次初始化）
function initFileInput(ctrlName, uploadUrl) {
	var control = $('#' + ctrlName);

	control.fileinput({
		language: 'zh', //设置语言
		uploadUrl: uploadUrl, //上传的地址
		allowedFileExtensions : ['xls','xlsx'],//接收的文件后缀
		showUpload: true, //是否显示上传按钮
		showRemove:true,
		showCaption: false,//是否显示标题
		browseClass: "btn btn-primary", //按钮样式
		previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
		showPreview:false
	});
}
			