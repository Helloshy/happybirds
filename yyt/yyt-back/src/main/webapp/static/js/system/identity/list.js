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
	    url : webRoot + '/system/identity/list.htm',
	    mtype : "GET",
	    datatype : "json",
	    colModel : [{
	                label : '用户名',
	                name : 'user_name',
	                width : 150,
	                sortable : false
	            },{
	                label : '账户（手机）',
	                name : 'register_phone',
	                width : 100,
	                sortable : false,
	            }, {
	                label : '企业名称',
	                name : 'company_name',
	                width : 150,
	                sortable : false
	            }, {
	                label : '企业性质',
	                name : 'type',
	                width : 150,
	                sortable : false
	            },  {
	                label : '资质身份',
	                name : 'is_buyer',
	                width : 150,
	                sortable : false,
	                formatter: function(cellvalue, options, rowObject){
	                	if(cellvalue==1 && rowObject.is_seller ==1){
	                		return '买家身份&卖家身份';
	                	}else if(cellvalue == 1){
	                		return '买家身份'
	                	}else if( rowObject.is_seller == 1){
	                		return '卖家身份';
	                	}else{
	                		return '---';
	                	}
	                }
	            }, {
	                label : '审核状态',
	                name : 'state',
	                width : 150,
	                sortable : false,
	                formatter: function(cellvalue, options, rowObject){
	                	if(cellvalue==0 ){
	                		return '未审核';
	                	}else {
	                		return '已审核'
	                	}
	                }
	            },{
	                label : '更新时间',
	                name : 'create_time',
	                width : 150,
	                sortable : false,
	                formatter: function(cellvalue, options, rowObject){
	                	return (new Date(cellvalue)).format('yyyy-MM-dd hh:mm:ss')
	                }
	            },{
	                label : '操作',
	                name : 'id',
	                width : 100,
	                sortable : false,
	                formatter: function(cellvalue, options, rowObject){
	                	return'<a href="details.htm?id='+cellvalue+'">详情</a>'
	                }
	            }],
	            postData:{
	            	state:$("#state").val()=='all'?null:$("#state").val(),
	            	companyName:$.trim($("#userName").val()),
	            	beginDate:$.trim($("#beginDate").val()),
	            	endDate:$.trim($("#endDate").val())
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
    		var state = $("#state").val();
    		if(state == 'all'){
    			state = null ;
    		}
    		var companyName = $.trim($("#userName").val());
    		var beginDate =$.trim($("#beginDate").val());
    		var endDate =$.trim($("#endDate").val());
            $("#grid-table").jqGrid('setGridParam',{ 
                url:webRoot + '/system/identity/list.htm', 
                postData:{state:state,companyName:companyName,beginDate:beginDate,endDate:endDate}, //发送查询数据 
                page:1 
            }).trigger("reloadGrid"); //重新载入 
    	});
});
			