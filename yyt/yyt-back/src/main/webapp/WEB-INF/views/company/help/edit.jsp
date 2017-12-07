<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>修改</title>
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
	<form id="dataFrom" action="${pageContext.request.contextPath}/help/save.htm">
	    <input type="hidden" name="id" value="${companyService.id}" >
	    <div class="dialog-1">
	    	<!-- <div class="layout-1">
	    		<span style="color:red">推荐图片分辨率为1920*460</span>
	    	</div> -->
	    	<c:choose>
	    		<c:when test="${companyService.recordType == 4 }">
	    			<div class="layout-1">
			            <span class="layout-left">标题：</span>
			           	<input type="text" name="itemName" class="layout-right" required="required" value="${companyService.itemName}">
			        </div>
	    		</c:when>
	    		<c:otherwise>
			        <div class="layout-1">
			        	<span class="layout-left">内容用途:</span>
			        	<span class="layout-right">
			        		<c:choose>
			        		<c:when test="${companyService.recordType == 1}">报装</c:when>
			        		<c:when test="${companyService.recordType == 1}">报修</c:when>
			        		<c:when test="${companyService.recordType == 1}">安检</c:when>
			        		</c:choose>
			        	</span>
			        </div>
			        <div class="layout-1">
			         	<span class="layout-left">联络电话:</span>
						<input type="text" required="required" name="tel" value="${companyService.tel}">	        
			        </div>
	    		</c:otherwise>
	    	</c:choose>
	         <div class="layout-1">
	         	<img alt="" src="${pageContext.request.contextPath}/static/images/loadding.gif" id="loadding" style="display:none">
	         	<a href="javascript:void(0);" id="uploadImg" onclick="$('input[id=upload]').click();">
	        		<img alt="" height="75" width="88" src="${pageContext.request.contextPath}/static/images/image.png"/>
	        	</a>
	        	<img alt="" height="75" width="88" id="localImag" style="display:none"/>
	        	<input type="file" name="file"  onchange="javascript:PreviewImage(this)" id="upload" style="display:none"/>
	        	<input type="text" name="logoPath" id="logoPath" style="display:none"/>
	        	<a href="javascript:void(0)"  onclick="$('input[id=upload]').click();" >选择文件</a>
				&nbsp;&nbsp;&nbsp;
	        	<span id="errorImag" style="color:red"></span>
	        </div>
	         
	         <div class="layout-1">
	            <span class="layout-left">内容:</span>
	            <textarea required="required" style="height: 190px;width: 97%" name="content" rows="80" cols="8" id="editor_id">${companyService.content}</textarea>
	        </div>
	        <div class="btn-box">
	            <button type="submit" class="btn btn-primary save">保存</button>
	            <button type="button" class="btn btn-danger" onclick="cancel()">取消</button>
	        </div>
	    </div>
	</form>

    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/ajaxfileupload.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/company/help/add.js"></script>
</body>

<script type="text/javascript">
K.create("#editor_id",{items:['bold','italic','underline','fontname','fontsize','forecolor','hilitecolor','plug-align','justifyleft','justifycenter','justifyright','justifyfull','link','plug-order','plug-indent'],
	//uploadJson : '${pageContext.request.contextPath}/kindeditor/upload.htm',
    allowFileManager : false,
	autoHeightMode : false,
	afterBlur : function(){this.sync();}
});
</script>


</html>
