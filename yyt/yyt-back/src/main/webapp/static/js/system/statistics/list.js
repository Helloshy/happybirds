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
	    url : webRoot + '/system/statistics/list.htm',
	    mtype : "GET",
	    datatype : "json",
	    colModel : [{
	                label : '统计内容',
	                name : 'title',
	                width : 150,
	                sortable : false
	            },{
	                label : '数量',
	                name : 'amount',
	                width : 100,
	                sortable : false,
	                formatter: function(cellvalue, options, rowObject){
	                	return cellvalue + getUnit(rowObject.id,rowObject.title);
	                }
	            },{
	                label : '修改时间',
	                name : 'createTime',
	                width : 150,
	                sortable : false,
	                formatter: function(cellvalue, options, rowObject){
	                	return (new Date(cellvalue)).format('yyyy-MM-dd hh:mm:ss')
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
		
		//编辑
		$("#editFun").click(function(){
			var cbox = $(grid_selector).jqGrid('getGridParam','selarrrow');
        	if(cbox == ""){
        		layer.alert("请选择编辑项！", {
					icon : 0
				});
        		return;
        	}else if(cbox.length > 1){
        		layer.alert("只能选择一项！", {
					icon : 0
				});
        		return;
        	}
			var url = webRoot + "/system/statistics/editPage.htm?id=" + cbox;
			laybox = layer.open({
        		title: "编辑",
        		type: 1,
        		area:["600px","40%"],
        		content:CommonUtil.ajax(url,"get")
        	});
		});
});

/**
 * 获取单位
 */
function getUnit(id,value){
	value = value;
	if(value == '今日成交'||(value=='累计成交'&& id !=4)){
		return "吨";
	}else if(value == '入驻商家'){
		return "家";
	}else if(value == '累计成交'&& id ==4){
		return "单";
	}else {
		return '';
	}
	
	
}