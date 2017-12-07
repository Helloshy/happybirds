<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<input type="hidden" id="status" name="status" value="1"  />
	    <div class="dialog-1">
	        <div class="layout-1">
	            <span class="layout-left">标题：</span>
	            <input class="layout-right" type="text" name="title" />
	        </div>
	        <div class="layout-1">
	            <span class="layout-left">显示位置：</span>
	            <select class="layout-right" name="msgType">
	            	<option value="1">常见问题</option>
	            	<option value="2">我要买气</option>
	            	<option value="3">我要卖气</option>
	            	<option value="4">交易指南</option>
	            	<option value="5">交易辅助</option>
	            </select>
	        </div>
	         <div class="layout-1">
	            <span class="layout-left">内容</span>
	            <textarea name="memo" style="height: 200px;width: 97%"  rows="100" cols="8" id="editor_id"></textarea>
	        </div>
	        <div class="btn-box">
	            <button type="submit" class="btn btn-primary save">保存</button>
	            <button type="button" class="btn btn-danger" onclick="cancel()">取消</button>
	        </div>
	    </div>
	</form>

    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/system/publicHelp/add.js"></script>
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
