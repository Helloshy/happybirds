<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>修改资讯内容</title>
    <style type="text/css">
    .ke-dialog-default.ke-dialog {
	  z-index: 99999999!important;
	}
	.layout-left {
	 	width: 15%;
	}
	.layout-1 {
    	margin-top:5px !important;
    	margin-bottom:5px !important;
	}
    </style>
    <script type="text/javascript">
    	var contextPath = '${pageContext.request.contextPath}';
    </script>
</head>

<body>
	<form id="dataFrom" action="${pageContext.request.contextPath}/system/news/save.htm" >
		<input type="hidden" id="status" name="status" value="1"  />
		<input type="hidden" name="id" value="${news.id }">
	    <div class="dialog-1">
	         <div class="layout-1">
	            <span class="layout-left">分类名称：</span>
	            <select name="newsTag" class="layout-right">
	            	<c:forEach items="${tags}" var="tag">
           				<option value="${tag.id}" <c:if test="${tag.id == news.newsTag}">selected="selected"</c:if>>${tag.id}</option>
           			</c:forEach>
	            </select>
	        </div>
	         <div class="layout-1">
	            <span class="layout-left">资讯来源：</span>
	            <input type="text" name="link" class="layout-right" placeholder="请输入资讯来源" value="${news.newsTag }">
	        </div>
	        <div class="layout-1">
	            <span class="layout-left">列表图片：</span>
	            <div class="layout-right ">
		        	<div class="form-group col-md-12 has-feedback" >
					    <div class="col-md-4 input-group">
						    <input type="file" name="file" id="file" class="layout-right file" onchange="afterSelectFile(this)" style="display:none">
						    <input type="hidden" name="logoPath" id="logoPath" value="${news.logoPath}"/>
						    <input id="fileText" class="form-control" type="text" value="${fileServer }${news.logoPath}" style="width:300px;">
						    <span class="input-group-addon" onclick="$('input[id=file]').click();" style="cursor: pointer; background-color: #e7e7e7"><i class="fa fa-folder-open"></i>选择文件</span>
					    	</div>
	   				</div>
	   			</div>
	        </div>
	        <div class="layout-1">
	        	<a href="javascript:void(0);" id="uploadImg" onclick="$('input[id=file]').click();" <c:if test="${news.logoPath != '' }">style="display:none"</c:if>>
	        		<img alt="" height="75" width="88" src="${pageContext.request.contextPath}/static/images/image.png"/>
	        	</a>
	         	<img alt="" src="${pageContext.request.contextPath}/static/images/loadding.gif" id="loadding" style="display:none" >
	        	<img alt="" height="75" width="88" id="localImag" src="${fileServer }${news.logoPath}" <c:if test="${news.logoPath == '' }">style="display:none"</c:if>/>
	        	<span id="imgError" style="color:red;"></span>
	        	<span style="color:red;">推荐图片分辨率为：330*288</span>
	        </div>
	        <div class="layout-1">
	            <span class="layout-left">标题：</span>
	            <input type="text" name="title" class="layout-right" placeholder="请输入标题" value="${news.title}"/>
	        </div>
	         <div class="layout-1">
	            <span class="layout-left">详情：</span>
	            <textarea style="height: 150px;width: 97%" name="content" rows="80" cols="8" id="editor_id">${news.content }</textarea>
	        </div>
	        <div class="layout-1">
	        </div>
	        <div class="btn-box">
	            <button type="submit" class="btn btn-primary save">保存</button>
	            <button type="button" class="btn btn-danger" onclick="cancel()">取消</button>
	        </div>
	    </div>
	</form>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/ajaxfileupload.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/system/news/edit.js"></script>
	<script type="text/javascript">
	K.create("#editor_id",{items:['bold','italic','underline','fontname','fontsize','forecolor','hilitecolor','plug-align','justifyleft','justifycenter','justifyright','justifyfull',"image",'link','plug-order','plug-indent'],
		uploadJson : '${pageContext.request.contextPath}/kindeditor/upload.htm',
	    allowFileManager : false,
	    allowImageUpload : true, 
		autoHeightMode : false,
		afterBlur : function(){this.sync();}
	});
	</script>
 </body>
</html>
