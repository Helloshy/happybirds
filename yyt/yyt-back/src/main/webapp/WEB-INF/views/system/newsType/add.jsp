<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>新增资讯分类</title>
    <style type="text/css">
    .ke-dialog-default.ke-dialog {
	  z-index: 99999999!important;
	}
    </style>
</head>
<body>
	<form id="dataFrom" action="${pageContext.request.contextPath}/system/newsType/save.htm">
		<input type="hidden" id="state" name="state" value="1"/>
	    <div class="dialog-1">
	         <div class="layout-1">
	            <span class="layout-left">分类名称：</span>
	            <input type="text" class="layout-right" name="id" >
	        </div>
	        <div class="btn-box">
	            <button type="submit" class="btn btn-primary save">保存</button>
	            <button type="button" class="btn btn-danger" onclick="cancel()">取消</button>
	        </div>
	    </div>
	</form>
	
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/system/newsType/add.js"></script>
 </body>
</html>
