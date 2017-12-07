<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	.new-contentarea {
    width: 100%;
    overflow:hidden;
    margin: 0 auto;
    position:relative;
}
.new-contentarea label {
    width:100%;
    height:100%;
    display:block;
}
.new-contentarea input[type=file] {
    width:188px;
    height:60px;
    background:#333;
    margin: 0 auto;
    position:absolute;
    right:50%;
    margin-right:-94px;
    top:0;
    right/*\**/:0px\9;
    margin-right/*\**/:0px\9;
    width/*\**/:10px\9;
    opacity:0;
    filter:alpha(opacity=0);
    z-index:2;
}
a.upload-img{
    width:165px;
    display: inline-block;
    margin-bottom: 10px;
    height:57px;
    line-height: 57px;
    font-size: 20px;
    color: #FFFFFF;
    background-color: #f38e81;
    border-radius: 3px;
    text-decoration:none;
    cursor:pointer;
}
a.upload-img:hover{
    background-color: #ec7e70;
}
 
.tc{text-align:center;}
	
    </style>
    <script type="text/javascript">
    	var contextPath = '${pageContext.request.contextPath}';
    </script>
</head>

<body>
	<form id="dataFrom" action="${pageContext.request.contextPath}/system/entrust/save.htm">
		<input type="hidden"  name="state" value="1"  />
		<input type="hidden" id="id" name="id" value="${tag.id}"  />
	    <div class="dialog-1">
	         
	         <div class="layout-1">
	            <span class="layout-left">申请书模板：</span>
	            <div class="layout-right ">
		        	<div class="form-group col-md-12 has-feedback">
					    <div class="col-md-4 input-group">
						   <input type="file" name="file" id="file" onchange="afterSelectFile(this)" style="display:none">
	             			<input type="hidden" name="logoPath" id="logoPath" value="${tag.logoPath }" >
						    <input id="fileText" class="form-control" type="text" style="width:300px;" onclick="$('input[id=file]').click();" value="${tag.logoPath }">
						    <span class="input-group-addon" onclick="$('input[id=file]').click();" style="cursor: pointer; background-color: #e7e7e7"><i class="fa fa-folder-open"></i>选择文件</span>
					    </div>
	   				</div>
	   			</div>
	        </div>
	         <div class="layout-1" id="loadding" style="display:none">
	           <img alt="" src="${pageContext.request.contextPath}/static/images/loadding.gif" >
	        	<span>正在上传文件</span>
	        </div>
	        <div class="btn-box">
	            <button type="submit" class="btn btn-primary save">保存</button>
	            <button type="button" class="btn btn-danger" onclick="cancel()">取消</button>
	        </div>
	    </div>
	</form>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/ajaxfileupload.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/system/entrust/edit.js"></script>
 </body>
</html>
