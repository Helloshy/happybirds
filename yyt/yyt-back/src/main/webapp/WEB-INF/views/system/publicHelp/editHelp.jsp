<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>新增</title>
    <style type="text/css">
    .ke-dialog-default.ke-dialog {
	  z-index: 99999999!important;
	}
    </style>
    
</head>

<body>
	<form id="dataFrom" action="${pageContext.request.contextPath}/system/publicHelp/save.htm">
		<input type="hidden"  name="id" value="${publicHelp.id }"  />
	    <div class="dialog-1">
	        <div class="layout-1">
	            <span class="layout-left">标题：</span>
	             <input type="text" name="title" value="${publicHelp.title }"/>
	        </div>
	        <div class="layout-1">
	            <span class="layout-left">显示位置：</span>
	            <select class="layout-right" name="msgType">
	            	<option value="1"  <c:if test="${publicHelp.msgType ==1 }">selected="selected"</c:if>>常见问题</option>
	            	<option value="2"  <c:if test="${publicHelp.msgType ==2 }">selected="selected"</c:if>>我要买气</option>
	            	<option value="3"  <c:if test="${publicHelp.msgType ==3 }">selected="selected"</c:if>>我要卖气</option>
	            	<option value="4"  <c:if test="${publicHelp.msgType ==4 }">selected="selected"</c:if>>交易指南</option>
	            	<option value="5"  <c:if test="${publicHelp.msgType ==5 }">selected="selected"</c:if>>交易辅助</option>
	            </select>
	        </div>
	         <div class="layout-1">
	            <span class="layout-left">内容</span>
	            <textarea name="memo" style="height:200px;width: 97%"  rows="100" cols="8" id="editor_id">${content}</textarea>
	        </div>
	        <div class="btn-box">
	            <button type="submit" class="btn btn-primary save">保存</button>
	            <button type="button" class="btn btn-danger" onclick="cancel()">取消</button>
	        </div>
	    </div>
	</form>

    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/system/publicHelp/edit.js"></script>
</body>

<script type="text/javascript">
	K.create("#editor_id",{items:['bold','italic','underline','fontname','fontsize','forecolor','hilitecolor','plug-align','justifyleft','justifycenter','justifyright','justifyfull',"image",'link','plug-order','plug-indent'],
		uploadJson : '${pageContext.request.contextPath}/kindeditor/upload.htm',
	    allowFileManager : false,
	    allowImageUpload : true, 
		autoHeightMode : false,
		afterBlur : function(){this.sync();}
	});
</script>


</html>