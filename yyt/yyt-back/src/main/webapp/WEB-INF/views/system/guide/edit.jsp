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
    <script type="text/javascript">
    	var contextPath ='${pageContext.request.contextPath}';
    </script>
</head>

<body>
	<form id="dataFrom" action="${pageContext.request.contextPath}/system/guide/save.htm">
		<input type="hidden" id="recordType" name="recordType" value="${guide.recordType}"  />
		<input type="hidden" id="id" name="id" value="${guide.id}"  />
	    <div class="dialog-1">
	    	<div class="layout-1">
	    		<span style="color:red">推荐图片分辨率为1920*460</span>
	    	</div>
	         <div class="layout-1">
	            <span class="layout-left">图片：</span>
	            <div class="layout-right ">
		        	<div class="form-group col-md-12 has-feedback" >
					    <div class="col-md-4 input-group">
						    <input type="file" name="file" class="layout-right" onchange="javascript:PreviewImage(this)" id="upload" style="display:none"/>
						    <input type="hidden" name="logoPath" id="logoPath" value="${guide.logoPath}"/>
						    <input id="fileText" class="form-control" type="text" style="width:300px;" value="${fileServer }${guide.logoPath}">
						    <span class="input-group-addon" onclick="$('input[id=upload]').click();" style="cursor: pointer; background-color: #e7e7e7">
						    	<i class="fa fa-folder-open"></i>选择文件
						    </span>
					    </div>
	   				</div>
	   			</div>
	        </div>
	         <div class="layout-1">
	         	<img alt="" src="${pageContext.request.contextPath}/static/images/loadding.gif" id="loadding" style="display:none">
	        	<img alt="" height="75" width="88" id="localImag" src="${fileServer }${guide.logoPath}"/>
	        	<span id="errorImag" style="color:red"></span>
	        </div>
	         
	         <div class="layout-1">
	        
	            <span class="layout-left">是否可用:</span>
	            <select name="status" style="width:100px;">
	            	<option value="1">是</option>	
	            	<option value="0" <c:if test="${guide.status == 0 }">selected="selected"</c:if>>否</option>	
	            </select>
	        </div>
	         <div class="layout-1">
	            <span class="layout-left">内容:</span>
	            <textarea style="height:190px;width: 97%" name="content" rows="80" cols="8" id="editor_id">${guide.content }</textarea>
	        </div>
	        <div class="btn-box">
	            <button type="submit" class="btn btn-primary save" id="saveData">保存</button>
	            <button type="button" class="btn btn-danger" onclick="cancel()">取消</button>
	        </div>
	    </div>
	</form>

    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/ajaxfileupload.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/system/guide/edit.js"></script>
</body>

<script type="text/javascript">
K.create("#editor_id",{items:['bold','italic','underline','fontname','fontsize','forecolor','hilitecolor','plug-align','justifyleft','justifycenter','justifyright','justifyfull','link','plug-order','plug-indent'],
	//uploadJson : '${pageContext.request.contextPath}/kindeditor/upload.htm',
    allowFileManager : false,
   // allowImageUpload : true, 
	autoHeightMode : false,
	afterBlur : function(){this.sync();}
});
</script>


</html>
