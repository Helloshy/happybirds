<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>新增</title>
</head>

<body>
	<form id="dataFrom" action="${pageContext.request.contextPath}/message/save.htm">
		<input type="hidden" name="id" value="${message.id}"  />
	    <div class="dialog-1">
	        <div class="layout-1">
	            <span class="layout-left">标题</span>
	            <input name="title" value="${message.title}" type="text" class="layout-right"/>
	        </div>
	        <div class="layout-1">
	            <span class="layout-left">内容</span>
	            <textarea id="editor_id" name="content" style="height: 250px;width: 97%" cols="100" rows="8">${message.content }</textarea>
	        </div>
	        <div class="btn-box">
	            <button type="submit" class="btn btn-primary save">保存</button>
	            <button type="button" class="btn btn-danger" onclick="cancel()">取消</button>
	        </div>
	    </div>
	</form>

    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/system/messagePush/edit.js"></script>
    <script type="text/javascript">
    K.create("#editor_id",{items:['bold','italic','underline','fontname','fontsize','forecolor','hilitecolor','plug-align','justifyleft','justifycenter','justifyright','justifyfull',"image",'link','plug-order','plug-indent'],
    	uploadJson : '${pageContext.request.contextPath}/kindeditor/upload.htm', allowFileManager : false,
        allowImageUpload : true, 
    	autoHeightMode : false,
    	afterBlur : function(){this.sync();}
    });
    
    </script>
</body>



</html>
