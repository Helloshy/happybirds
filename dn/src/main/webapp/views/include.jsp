<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/";%>
<%String contextPath = request.getContextPath();%>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<script type="text/javascript">
var base = base || {};
base.basePath = '<%=basePath%>';
base.tSpace = '';
window.onerror=function(sMessage, sUrl, sLine){
	//$.messager.progress('close');
	//$.messager.alert('错误!',sMessage +"\n"+sUrl+"\n请刷新再试!",'error');
	//return true;
}
</script>
<style>
	behavior: url(<%=basePath%>js/pie/PIE.htc);
</style>
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">   
<link rel="stylesheet" href="<%=basePath%>js/jquery-easyui-1.4/themes/default/easyui.css" type="text/css"></link><!-- easyui样式文件 -->
<link rel="stylesheet" href="<%=basePath%>js/jquery-easyui-1.4/themes/icon.css" type="text/css"></link><!-- easyui图标样式 -->
<link rel="stylesheet" href="<%=basePath%>css/custom.css" type="text/css"></link>
<script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.11.1.min.js"></script><!-- jquery主js-->
<script type="text/javascript" src="<%=basePath%>js/jquery/jquery-migrate-1.2.1.min.js"></script><!-- jquery向下兼容-->
<script type="text/javascript" src="<%=basePath%>js/jquery-easyui-1.4/jquery.easyui.min.js"></script><!-- easyui主js-->
<script type="text/javascript" src="<%=basePath%>js/jquery-easyui-1.4/locale/easyui-lang-zh_CN.js"></script><!-- easyui中文js-->
<script type="text/javascript" src="<%=basePath%>js/kindeditor-4.1.10/kindeditor-min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/kindeditor-4.1.10/lang/zh_CN.js"></script>

<script type="text/javascript" src="<%=basePath%>js/public/main.js"></script><!-- 不分项目定义的工具类 -->
<script type="text/javascript" src="<%=basePath%>js/util/customTools.js"></script><!-- 自定义的jq模式数据处理工具类 -->
<script type="text/javascript" src="<%=basePath%>js/util/easyuiExpand.js"></script><!-- easyui扩展js-->

<script type="text/javascript" src="<%=basePath%>js/datapicker/WdatePicker.js"></script> <!-- 时间插件 -->

<script type="text/javascript" src="<%=basePath %>/js/layer/layer.js"></script><!-- 错误提示 -->


<script type="text/javascript">
	var webRoot = '<%=basePath%>' ;
	var gridAddVideo = function(){
		$('#videoLink').val('') ;
		$( "#video-dialog" ).dialog("open") ;
	} ;

	var saveInsure = function(){
		var link = $('#videoLink').val() ;
		editor.insertHtml(link) ;
		$( "#video-dialog" ).dialog("close") ;
	} ;
</script>

<div style="display: none;">
<div id="video-dialog" style="width: 550px;height: 300px;" class="easyui-dialog" class="easyui-dialog"  data-options="iconCls:'icon-save',resizable:true,modal:true,
	closed:true,title:'修改',buttons:[{text:'确定', iconCls:'icon-ok', handler:saveInsure},{text:'取消',iconCls:'icon-remove',handler:function(){$('#video-dialog').dialog('close');}}]">
	<form id="form" enctype="multipart/form-data" target="uploadImgFrame" method="post" style="margin: 0px;padding: 0px;">
		<table class="mf-table">
			<tr class="mf-line">
				<td class="mf-left">视频链接：</td>
				<td class="mf-right">
					<textarea rows="5" cols="60" id="videoLink" ></textarea>
				</td>
			</tr>
		</table>
	</form>
</div>
</div>