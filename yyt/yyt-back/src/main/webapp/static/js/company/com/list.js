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
	    url : webRoot + '/company/list.htm',
	    mtype : "GET",
	    datatype : "json",
	    colModel : [{
		            label : '公司账号',
		            name : 'user_name',
		            width : 150,
		            sortable : false
		        },{
	                label : '公司名称',
	                name : 'company_name',
	                width : 150,
	                sortable : false
	            },{
	                label : '所在省市',
	                name : 'province',
	                width : 100,
	                sortable : false,
	                formatter: function(cellvalue, options, rowObject){
	                	return rowObject.province + rowObject.city + rowObject.district + rowObject.address;
	                }
	            }, {
	                label : '微信公众号APPID',
	                name : 'appid',
	                width : 100,
	                sortable : false
	            }, {
	                label : '微信公众号秘钥',
	                name : 'apikey',
	                width : 100,
	                sortable : false
	            }, {
	                label : '微信商户号',
	                name : 'mchid',
	                width : 100,
	                sortable : false
	            } ,{
	                label : '微信商户号秘钥',
	                name : 'appSecret',
	                width : 100,
	                sortable : false
	            },{
	                label : '证书',
	                name : 'file_path',
	                width : 100,
	                sortable : false,
	                formatter: function(cellvalue, options, rowObject){
	                	if($.trim(cellvalue) == ''){
	                		return '----';
	                	}
	                	var url = (cellvalue.indexOf("http") == -1)? fileServer + cellvalue:cellvalue;
	                	return "<a href='" + url+ "'target='_blank' title=''>证书文件</a>"
	               
	                }
	            },{
	                label : '更新时间',
	                name : 'create_time',
	                width : 100,
	                sortable : false,
	                formatter: function(cellvalue, options, rowObject){
	                	return (new Date(cellvalue)).format('yyyy-MM-dd hh:mm:ss')
	                }
	            },{
	                label : '操作账号',
	                name : 'user_name',
	                width : 100,
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
		    postData:{'hallName':$.trim($("#hallName").val())},//发送查询参数
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
			var url = webRoot + "/company/editPage.htm?id=" + cbox;
			laybox = layer.open({
				title: "编辑",
				type: 1,
				area:["700px","80%"],
				content:CommonUtil.ajax(url,"get")
			});
		});
});
			