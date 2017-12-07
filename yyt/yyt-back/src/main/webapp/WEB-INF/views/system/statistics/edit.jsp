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
    </style>
    
</head>

<body>
	<form id="dataFrom" action="${pageContext.request.contextPath}/system/statistics/update.htm">
		<input type="hidden" id="id" name="id" value="${statistics.id}"  />
	    <div class="dialog-1" >
	        <div class="layout-1">
	            <span class="layout-left" style="width:13%">统计内容：</span>
	            <span class="layout-left">${statistics.title }</span>
	        </div>
	        <div class="layout-1">
	            <span class="layout-left" style="width:13%">数量</span>
	            <input type="text" name="amount" value="${statistics.amount }"> 家
	        </div>
	        <div class="btn-box">
	            <button type="submit" class="btn btn-primary save">保存</button>
	            <button type="button" class="btn btn-danger" onclick="cancel()">取消</button>
	        </div>
	    </div>
	</form>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/system/statistics/edit.js"></script>
 </body>
</html>
