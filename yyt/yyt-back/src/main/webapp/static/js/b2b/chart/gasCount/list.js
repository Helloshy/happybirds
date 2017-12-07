var K = window.KindEditor;
var laybox;
var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
$(function(){
	
	//加载日期控件
	 $('.date-picker').datetimepicker({  
	        language:  'zh-CN',  
	        format:'yyyy-mm-dd',
	        weekStart: 1,  
	        todayBtn:  1,  
	        autoclose: 1,  
	        todayHighlight: 1,  
	        startView: 2,  
	        forceParse: 0,  
	        minView: "month"
	    })
	
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
	    url : webRoot + '/chart/gasCount/list.htm',
	    mtype : "GET",
	    datatype : "json",
	    colModel : [{
	                label : '所属公司',
	                name : 'company_name',
	                width : 150,
	                sortable : false
	            },{
	                label : '营业网点',
	                name : 'hall_name',
	                width : 100,
	                sortable : false,
	            },{
	                label : '用户类型',
	                name : 'record_tag',
	                width : 100,
	                sortable : false,
	            },{
	                label : '用户数',
	                name : 'totalUser',
	                width : 100,
	                sortable : false,
	            }, {
	                label : '总消费金额',
	                name : 'totalPrice',
	                width : 150,
	                sortable : false
	            },{
	                label : '总用气量',
	                name : 'totalgas',
	                width : 150,
	                sortable : false
	            },{
	                label : '时间',
	                name : 'queryTime',
	                width : 150,
	                sortable : false/*,
	                formatter: function(cellvalue, options, rowObject){

	                	return (new Date(cellvalue)).format('yyyy-MM-dd hh:mm:ss')+'-'+(new Date(rowObject.endDate))
	                }*/
	            }],
	            postData:{
	            	'beginDate':$.trim($("#beginDate").val()),
	            	'endDate':$.trim($("#endDate").val())
	            }, //发送查询数据 
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
		
        /**
    	 * 列表查询函数
    	 */
    	$("#queryFun").click(function(){
    		var beginDate =$.trim($("#beginDate").val());
    		var endDate =$.trim($("#endDate").val());
            $("#grid-table").jqGrid('setGridParam',{ 
                url:webRoot + '/chart/gasCount/list.htm',
                postData:{'beginDate':beginDate,'endDate':endDate}, //发送查询数据
                page:1 
            }).trigger("reloadGrid"); //重新载入 
    	});
});

var getParam = function () {
	var beginDate =$.trim($("#beginDate").val());
	var endDate =$.trim($("#endDate").val());
	//var page = $('#grid-table').getGridParam('page'); // current page
	//var rows = $('#grid-table').getGridParam('rows'); // rows
	return {beginDate:beginDate,endDate:endDate}
}

var exportData = function (beginDate,endDate) {
	var param = getParam();
	window.location.href = webRoot+'/chart/gasCount/export.htm?'+'beginDate='+param.beginDate+'&endDate='+param.endDate
}