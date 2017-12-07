<%@page import="com.kapphk.web.utils.PropertiesUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
	//获取文件服务器地址
	request.setAttribute("fileServer", PropertiesUtil.getFileServer());
%>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">   

<link rel="stylesheet" href="${ctx}/assets/css/bootstrap.css" />

<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ace-fonts.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
<link rel="stylesheet" href="${ctx}/assets/css/ace-skins.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ace-rtl.css" />
<link rel="stylesheet" href="${ctx}/assets/font-awesome/css/font-awesome.css" />
<link rel="stylesheet" href="${ctx}/static/plugins/bootstrap-datepicker/css/bootstrap-datetimepicker.css" />
<link rel="stylesheet" href="${ctx}/static/plugins/kindeditor-4.1.10/themes/default/default.css" />
<link rel="stylesheet" href="${ctx}/static/plugins/JBox/jBox/Skins2/Metro/jbox.css" />
<link rel="stylesheet" href="${ctx}/static/plugins/bootstrap-fileinput/css/fileinput.min.css" />

<script src="${ctx}/static/js/jquery-1.9.1.min.js"></script>
<script src="${ctx}/static/js/jquery-migrate-1.2.1.js"></script>
<script src="${ctx}/static/js/jquery-form.js"></script>
<script src="${ctx}/static/js/LodopFuncs.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.validate.messages_zh.js"></script>
<script src="${ctx}/static/js/common.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/bootstrap.js"></script>
<!--<script src="${ctx}/static/plugins/bootstrap-datepicker/js/moment.js"></script>-->
<script src="${ctx}/static/plugins/bootstrap-datepicker/js/bootstrap-datetimepicker.js"></script>
<script src="${ctx}/static/plugins/bootstrap-datepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${ctx}/assets/js/jquery.jqGrid.js"></script>
<script src="${ctx}/assets/js/grid.locale-cn.js"></script>
<script type="text/javascript" src="${ctx}/static/plugins/JBox/jBox/jquery.jBox-2.3.min.js"></script>
<script src="${ctx}/static/plugins/layer/layer.js"></script>
<script src="${ctx}/static/plugins/kindeditor-4.1.10/kindeditor-all.js"></script>
<script src="${ctx}/static/plugins/bootstrap-fileinput/js/fileinput.min.js"></script>
<script src="${ctx}/static/plugins/bootstrap-fileinput/js/locales/zh.js"></script>

<script type="text/javascript">
	var webRoot = "${ctx}" ;
	var fileServer = '${fileServer}';
	function updatePagerIcons(table) {
		var replacement = 
		{
			'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
			'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
			'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
			'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
		};
		$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
			var icon = $(this);
			var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
			
			if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
		})
	}
	function cancel(){
		layer.close(laybox);
	}
	
    function toDecimal(x) {    
        var f = parseFloat(x);    
        if (isNaN(f)) {    
            return 0;    
        }    
        var f = Math.round(x*100)/100;    
        var s = f.toString();   
        
         var rs = s.indexOf('.');    
        if (rs < 0) {    
            rs = s.length;    
            s += '.';    
        }    
        while (s.length <= rs + 2) {    
            s += '0';    
        }   
        return s;
        //return (Math.round(parseFloat(s)*100)/100);    */ 
    }   
    
    
    Date.prototype.format = function(format) {
        var date = {
               "M+": this.getMonth() + 1,
               "d+": this.getDate(),
               "h+": this.getHours(),
               "m+": this.getMinutes(),
               "s+": this.getSeconds(),
               "q+": Math.floor((this.getMonth() + 3) / 3),
               "S+": this.getMilliseconds()
        };
        if (/(y+)/i.test(format)) {
               format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
        }
        for (var k in date) {
               if (new RegExp("(" + k + ")").test(format)) {
                      format = format.replace(RegExp.$1, RegExp.$1.length == 1
                             ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
               }
        }
        return format;
 }
</script>


<style>
.doc-buttons {
  padding-left: 12px!important;
}
 .layout-1 {
	margin: 5px auto;!important;
}
.ui-jqgrid tr.jqgrow td { 
	text-overflow : ellipsis;
	white-space: normal;
 }
.ui-jqgrid-view > .ui-jqgrid-titlebar {

  background: #6FB3E0;

}
.ke-menu-default{
	z-index: 99999999!important;
}
.ke-colorpicker-default{
 z-index: 99999999!important;
}

.dialog-1 .modal-header {
  background-color: #307ECC;
  color: #fff; }
  .dialog-1 .modal-header .close {
    color: #fff; }
.dialog-1 .layout-1 {
  margin: 10px auto;
  font-size: 14px;
  margin-left: 20px; }
  .dialog-1 .layout-1 .layout-left {
    height: 36px;
    width: 15%;
    display: inline-block; }
  .dialog-1 .layout-1 .layout-right {
    height: 36px;
    width: 80%;
    display: inline-block; }
.dialog-1 .btn-box {
  text-align: center; }

/*# sourceMappingURL=admin-style.css.map */

/*定义滚动条高宽及背景 高宽分别对应横竖滚动条的尺寸*/  
::-webkit-scrollbar  
{  
    width: 1px;  
    height: 1px;  
    background-color: #F5F5F5;  
}  
  
/*定义滚动条轨道 内阴影+圆角*/  
::-webkit-scrollbar-track  
{  
    -webkit-box-shadow: inset 0 0 1px rgba(0,0,0,0.3);  
    border-radius: 10px;  
    background-color: #F5F5F5;  
}  
  
/*定义滑块 内阴影+圆角*/  
::-webkit-scrollbar-thumb  
{  
    border-radius: 10px;  
    -webkit-box-shadow: inset 0 0 1px rgba(0,0,0,.3);  
    background-color: #555;  
}  

</style>