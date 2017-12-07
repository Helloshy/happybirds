var laybox;

$(function(){
	buyProduct();
});

/**
 * 已买到的商品
 */
function buyProduct(){
	$(".buyerPageContent").hide();
	$("#buyProduct").show();
	var grid_selector = "#buy-product-grid-table";
	var pager_selector = "#buy-product-grid-pager";
	
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
	    url : webRoot + '/system/account/orderList.htm',
	    mtype : "GET",
	    datatype : "json",
	    colModel : [{
	                label : '<center>商品</center>',
	                name : 'districtId',
	                width : 150,
	                sortable : false, 
                	formatter: function(cellvalue, options, rowObject){
                		return rowObject.districtId +rowObject.tagValue+rowObject.gasRate+"Nm³/T";
	                }
	            },{
	                label : '<center>订单号</center>',
	                name : 'id',
	                width : 100,
	                sortable : false, 
	            }, {
	                label : '<center>配送方式</center>',
	                name : 'delivery',
	                width : 100,
	                sortable : false
	            },  {
	                label : '<center>出厂价/挂牌价 <br/>（元/吨）</center>',
	                name : 'unitPrice',
	                width : 100,
	                sortable : false, 
	                formatter: function(cellvalue, options, rowObject){
	                	return toDecimal(cellvalue);
	                }
	            },  {
	                label : '<center>采购数量（吨）</center>',
	                name : 'qty',
	                width : 100,
	                sortable : false
	            },  {
	                label : '<center>采购金额（元）</center>',
	                name : 'unitPrice',
	                width : 100,
	               
	                sortable : false, 
	                formatter: function(cellvalue, options, rowObject){
	                	return toDecimal(rowObject.unitPrice * rowObject.qty);
	                }
	            },  {
	                label : '<center>运费（元）</center>',
	                name : 'deliveryFee',
	                width : 100,
	                sortable : false, 
	                formatter: function(cellvalue, options, rowObject){
	                	return toDecimal(cellvalue);
	                }
	            }, {
	                label : '<center>总计（元）</center>',
	                name : 'deliveryFee',
	                width : 100,
	                sortable : false, 
	                formatter: function(cellvalue, options, rowObject){
	                	return toDecimal(rowObject.unitPrice * rowObject.qty +rowObject.deliveryFee);
	                }
	            }, {
	                label : '<center>订单状态</center>',
	                name : 'state',
	                width : 100,
	                sortable : false, 
	                formatter: function(cellvalue, options, rowObject){
	                	switch(cellvalue){
	                	case 1:
	                	  return '待确认';
	                	case 2:
	                	  return '待配送'
	                	case 3:
	                	  return '待收货'
	                	case 4:
	                	  return '待完成'
	                	case 5:
	                	  return '已完成'
	                	}
	                }
	            },  {
	                label : '<center>卖方商家</center>',
	                name : 'sellerName',
	                width : 150,
	                sortable : false, 
	                formatter: function(cellvalue, options, rowObject){
	                	return "<a href='company.htm?userId="+rowObject.sellerId+"'>"+cellvalue+"</a>"
	                }
	            },  {
	                label : '<center>订单时间</center>',
	                name : 'createTime',
	                width : 100,
	                sortable : false
	            },  {
	                label : '<center>操作</center>',
	                name : 'id',
	                width : 100,
	                sortable : false, 
	                formatter: function(cellvalue, options, rowObject){
	                	return "<a href='javascript:void(0);'  onclick='orderDetails(\""+cellvalue+"\")'>详情</a>"
	                }
	            }],
	            postData:{
	            	buyerId:$.trim($("#uid").val()),
	            	sellerId:null,
	            	state:$("#sellProductState").val()=='all'?null:$("#sellProductState").val(),
	            	searchTitle:$.trim($("#sellProductSearchTitle").val())}, //发送查询数据 
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
}

/**
 * 已买到的商品搜索
 */
function buyProductSearch(){
	var buyProductState = $("#buyProductState").val();
	if(buyProductState == 'all'){
		buyProductState = null ;
	}
	var buyProductSearchTitle =$.trim($("#buyProductSearchTitle").val());
    $("#buy-product-grid-table").jqGrid('setGridParam',{ 
        url:webRoot + '/system/account/orderList.htm',
        postData:{
        	 buyerId:$.trim($("#uid").val()),
        	 sellerId:null,
        	state:buyProductState,
        	searchTitle:buyProductSearchTitle}, //发送查询数据 
        page:1 
    }).trigger("reloadGrid"); //重新载入 
}
/**
 * 订单详情页面
 * @param id
 */
function orderDetails(id){
	var url = webRoot + "/order/detailPage.htm?orderId=" + id;
	detailFun("订单详情",url);
}


/**
 * 我的账单
 */
function bill(){
	$(".buyerPageContent").hide();
	$("#bill").show();
	var grid_selector = "#bill-grid-table";
	var pager_selector = "#bill-grid-pager";
	
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
	    url : webRoot + '/system/account/billList.htm',
	    mtype : "GET",
	    datatype : "json",
	    colModel : [{
	                label : '<center>商品</center>',
	                name : 'districtId',
	                width : 150,
	                sortable : false,
	                formatter: function(cellvalue, options, rowObject){
	                	return cellvalue + rowObject.tagValue + rowObject.gasRate+"Nm³/T";
	                }
	            },{
	                label : '<center>订单号</center>',
	                name : 'id',
	                width : 150,
	                sortable : false, 
	            },   {
	                label : '<center>配送方式</center>',
	                name : 'delivery',
	                width : 100,
	                sortable : false
	            },  {
	                label : '<center>出厂价、挂牌价<br/>（元/吨）</center>',
	                name : 'unitPrice',
	                width : 100,
	                sortable : false,
	                formatter: function(cellvalue, options, rowObject){
	                	return toDecimal(cellvalue);
	                }
	            },  {
	                label : '<center>采购数量<br/>（吨）</center>',
	                name : 'qty',
	                width : 100,
	                sortable : false, 
	                formatter: function(cellvalue, options, rowObject){
	                	return toDecimal(cellvalue);
	                }
	            },  {
	                label : '<center>采购金额<br/>（元）</center>',
	                name : 'unitPrice',
	                width : 100,
	                sortable : false, 
	                formatter: function(cellvalue, options, rowObject){
	                	return toDecimal(rowObject.unitPrice * rowObject.qty);
	                }
	            },  {
	                label : '<center>运费<br/>（元）</center>',
	                name : 'deliveryFee',
	                width : 100,
	                sortable : false, 
	                formatter: function(cellvalue, options, rowObject){
	                	return toDecimal(cellvalue);
	                }
	            },  {
	                label : '<center>气差费用<br/>（元）</center>',
	                name : 'poorGasFee',
	                width : 100,
	                sortable : false,
	                formatter: function(cellvalue, options, rowObject){
	                	return toDecimal(cellvalue);
	                }
	            },  {
	                label : '<center>压车费用<br/>（元）</center>',
	                name : 'pressFee',
	                width : 100,
	                sortable : false,
	                formatter: function(cellvalue, options, rowObject){
	                	return toDecimal(cellvalue);
	                }
	            },  {
	                label : '<center>结算金额</center>',
	                name : 'totalFee',
	                width : 100,
	                sortable : false,
	                formatter: function(cellvalue, options, rowObject){
	                	return toDecimal(cellvalue)
	                }
	            },  {
	                label : '<center>卖方商家</center>',
	                name : 'sellerName',
	                width : 150,
	                sortable : false,
	                formatter: function(cellvalue, options, rowObject){
	                	return "<a href='company.htm?userId="+rowObject.sellerId+"'>"+cellvalue+"</a>";
	                }
	            },  {
	                label : '<center>订单时间</center>',
	                name : 'createTime',
	                width : 150,
	                sortable : false,
	                formatter: function(cellvalue, options, rowObject){
	                	return new Date(cellvalue).format('yyyy-MM-dd: hh:mm:ss');
	                }
	            },  {
	                label : '<center>操作</center>',
	                name : 'id',
	                width : 70,
	                sortable : false,
	                formatter: function(cellvalue, options, rowObject){
	                	return "<a href='javascript:void(0);' onclick='billDetail(\""+cellvalue+"\")'>详情</a>";
	                }
	            }],
	            postData:{
	            	buyerId:$.trim($("#uid").val()),
	            	sellerId:null,
	            	searchTitle:$.trim($("#bill-searchTitle").val())
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
}

function billSearch(){
    $("#bill-grid-table").jqGrid('setGridParam',{ 
        url:webRoot + '/system/account/billList.htm',
        postData:{
        	buyerId:$.trim($("#uid").val()),
        	sellerId:null,
        	searchTitle:$.trim($("#bill-searchTitle").val())
    	}, //发送查询数据 
        page:1 
    }).trigger("reloadGrid"); //重新载入 
}

/**
 * 我的账单详情
 * @param id 订单id
 */
function billDetail(id){
	
	var url = webRoot + "/system/account/billDetail.htm?type=2&orderId="+id;
	laybox = layer.open({
		title: "账单详情",
		type: 1,
		area:["600px","80%"],
		content:CommonUtil.ajax(url,"get")
	});
}


/**
 * 我的供应商
 */
function supplier(){
	$(".buyerPageContent").hide();
	$("#supplier").show();
	var grid_selector = "#supplier-grid-table";
	var pager_selector = "#supplier-grid-pager";
	
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
	    url : webRoot + '/system/account/supplierList.htm',
	    mtype : "GET",
	    datatype : "json",
	    colModel : [{
	                label : '<center>用户名</center>',
	                name : 'user_name',
	                width : 100,
	                sortable : false
	            },{
	                label : '<center>账号（手机号）</center>',
	                name : 'register_phone',
	                width : 100,
	                sortable : false, 
	            },   {
	                label : '<center>公司名称</center>',
	                name : 'company_name',
	                width : 150,
	                sortable : false
	            },  {
	                label : '<center>联系人</center>',
	                name : 'contact_person',
	                width : 100,
	                sortable : false
	            },  {
	                label : '<center>联系电话</center>',
	                name : 'phone',
	                width : 100,
	                sortable : false
	            },  {
	                label : '<center>QQ号</center>',
	                name : 'qq',
	                width : 100,
	                sortable : false
	            },  {
	                label : '<center>地址</center>',
	                name : 'address',
	                width : 150,
	                sortable : false
	            },  {
	                label : '<center>添加时间</center>',
	                name : 'settlement_date',
	                width : 100,
	                sortable : false
	            }],
	            postData:{
	            	buyerId:$.trim($("#uid").val()),
	            	userName:$.trim($("#supplier-userName").val())
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
}



function supplierSearch(){
	var userName =$.trim($("#supplier-userName").val());
    $("#supplier-grid-table").jqGrid('setGridParam',{ 
        url:webRoot + '/system/account/supplierList.htm',
        postData:{buyerId:usid,userName:userName}, //发送查询数据 
        page:1 
    }).trigger("reloadGrid"); //重新载入 
	
	
	
}


/**
 * 我的供应商
 */
function userAddress(){
	$(".buyerPageContent").hide();
	$("#userAddress").show();
	var grid_selector = "#userAddress-grid-table";
	var pager_selector = "#userAddress-grid-pager";
	
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
	    url : webRoot + '/system/account/addressList.htm',
	    mtype : "GET",
	    datatype : "json",
	    colModel : [{
	                label : '<center>地点名称</center>',
	                name : 'item_name',
	                width : 100,
	                sortable : false
	            },{
	                label : '<center>详细地址</center>',
	                name : 'address',
	                width : 100,
	                sortable : false, 
	            },   {
	                label : '<center>存储能力（吨）</center>',
	                name : 'store',
	                width : 150,
	                sortable : false
	            },  {
	                label : '<center>工作时间</center>',
	                name : 'work_type',
	                width : 100,
	                sortable : false,
	                formatter: function(cellvalue, options, rowObject){
	                	return cellvalue=='1'?'全天':'';
	                }
	            },  {
	                label : '<center>联系人</center>',
	                name : 'contact_person',
	                width : 100,
	                sortable : false
	            },  {
	                label : '<center>联系电话</center>',
	                name : 'contact_phone',
	                width : 100,
	                sortable : false
	            },  {
	                label : '<center>更新时间</center>',
	                name : 'create_time',
	                width : 100,
	                sortable : false
	            }],
	            postData:{
	            	userId:$.trim($("#uid").val()),
	            	itemName:$.trim($("#supplier-itemName").val())
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
}



function userAddressSearch(){
	var itemName =$.trim($("#supplier-itemName").val());
    $("#userAddress-grid-table").jqGrid('setGridParam',{ 
        url:webRoot + '/system/account/addressList.htm',
        postData:{userId:usid,itemName:itemName}, //发送查询数据 
        page:1 
    }).trigger("reloadGrid"); //重新载入 
}

/**
 * 删除卸气点
 */
function delUserAddress(){
	var grid_selector = '#userAddress-grid-table';
	var cbox = $(grid_selector).jqGrid('getGridParam','selarrrow');
	if(cbox == ""){
		layer.alert("请选择删除项！", {
			icon : 0
		});
		return;
	}
	layer.confirm("是否删除？",function(index){
		var url = webRoot + "/system/account/delAddress.htm";
		var s = CommonUtil.ajax(url, 
								"post",
								{ids: cbox.join(",")},
								"json");
		if(s.status == 10001){
			layer.msg("删除成功！");
			var reccount = $(grid_selector).getGridParam("reccount");//当前页行数
			if(reccount == cbox.length){
				var prevPage = $(grid_selector).getGridParam("page") - 1;
				$(grid_selector).jqGrid("setGridParam",{
					page:prevPage
				}).trigger("reloadGrid");
			}else{
				$(grid_selector).trigger("reloadGrid");
			}
		}else{
			layer.alert("删除失败！", {
				icon : 2
			});
		}
	});
}
/**
 * 新建一个弹出窗口
 * @param id
 * @returns
 */
function detailFun(title,url){
	laybox = layer.open({
		title: title,
		type: 1,
		area:["100%","100%"],
		content:CommonUtil.ajax(url,"get")
	});
}