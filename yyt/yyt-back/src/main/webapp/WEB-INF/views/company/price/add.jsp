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
	.layout-left{
		width:18%!important;
	}
	.layout-right{
		width:50%!important;
	}
    </style>
    
</head>

<body>
	<form id="dataFrom" action="${pageContext.request.contextPath}/companyPrice/save.htm">
	    <div class="dialog-1">
	        <div class="layout-1">
	            <span class="layout-left">所属燃气公司：</span>
	            <span class="layout-right">${company.companyName }</span>
	            <input type="hidden" name="companyId" value="${company.id}">
	        </div>
	        <div class="layout-1">
	            <span class="layout-left">用户类型：</span>
	            <input type="radio" name="recordTag" value="公福用户" checked="checked">公福用户
	            &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
	            <input type="radio" name="recordTag" value="居民用户">居民用户
	        </div>
	        <div class="layout-1">
	            <span class="layout-left">售气价格：</span>
	            <input name="price" class="layout-right" required="required" /> 元/立方米
	        </div>
	        <div class="layout-1">
	            <span class="layout-left">生效日期：</span>
	             <input name="priceDate" class="layout-right date-picker" required="required"/>
	        </div>
	        <div class="btn-box">
	            <button type="submit" class="btn btn-primary save">保存</button>
	            <button type="button" class="btn btn-danger" onclick="cancel()">取消</button>
	        </div>
	    </div>
	</form>
	
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/company/price/add.js"></script>
</body>
</html>
