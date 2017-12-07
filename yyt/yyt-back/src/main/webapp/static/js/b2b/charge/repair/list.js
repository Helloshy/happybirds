var K = window.KindEditor;
var laybox;
var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
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
			url :'',
			mtype : "GET",
			datatype : "json",
			colModel : [{
				label : '缴费时间',
				name : 'createTime',
				width : 160,
				sortable : false
			},{
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
				sortable :false
			},{
				label : '气表编号',
				name : 'meter_no',
				width : 150,
				sortable : false
			},{
				label : '缴费金额',
				name : 'amount',
				width : 150,
				sortable : false
			},{
				label : '支付方式',
				name : 'pay_method',
				width : 150,
				sortable : false,
				formatter: function(cellvalue, options, rowObject){
					if(cellvalue==1){
						return '现金支付';
					}
					if(cellvalue==2){
						return '微信支付';
					}
				}
			},{
				label : '当前余额',
				name : 'balance_amount',
				width : 150,
				sortable : false
			},{
				label : '收费人员',
				name : 'operatorName',
				width : 160,
				sortable : false
			},{
				label : ' 更新时间',
				name : '',
				width : 160,
				sortable : false
			},{
				label : '操作账号',
				name : '',
				width : 160,
				sortable : false
			},{
				label : '操作',
				width : 90,
				align : "center",
				fixed : true,
				sortable : false,
				resize : false,
				formatter : function(cellvalue, options, rowObject) {
					var array = [];
					array.push(' <a href="javascript:void(0);" onclick="detailFun(\'' + rowObject.id
						+ '\');" title="缴费">票据补打</a> ');
					return array.join('');
				}
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
                url:webRoot + '/charge/repair/list.htm',
                postData:{'queryParam':queryParam}, //发送查询数据
                page:1 
            }).trigger("reloadGrid"); //重新载入 
    	});
});

function detailFun(id){
	var url = webRoot + "/charge/repair/printBill.htm?id="+id;
	laybox = layer.open({
		title: "现金缴费",
		type: 1,
		area:["80%","90%"],
		content:CommonUtil.ajax(url,"get")
	});
}