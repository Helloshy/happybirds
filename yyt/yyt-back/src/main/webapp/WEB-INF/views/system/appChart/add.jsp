<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	.error{
		color:red;
	}
    </style>
    <script type="text/javascript">
    	var contextPath = '${pageContext.request.contextPath}';
    	var provinceStr = '${provinces}';
    </script>
</head>

<body>
	<form id="dataFrom" action="${pageContext.request.contextPath}/system/appChart/saveOrUpdate.htm">
		<input type="hidden" id="status" name="status" value="1"  />
	    <div class="dialog-1">
	        <div class="layout-1">
	            <span class="layout-left">地区</span>
	            <select class="layout-right" name="districtId" id="districtId" onchange="changeDistrict(this)" >
	            	<option value="s">--请选择--</option>
	            	<c:forEach items="${region}" var="re">
	            		<option value="${re}">${re}</option>
	            	</c:forEach>
	            </select>
	        </div>
	        <div class="layout-1">
	            <span class="layout-left">省份</span>
	            <select class="layout-right" name="province" id="province">
	            	<option value="s">--请选择--</option>
	            </select>
	        </div>
	        <div class="layout-1">
	            <span class="layout-left">时间</span>
	            <input type="text" class="layout-right date-picker" name="issueDate" id="issueDate">
	        	<label id="issueDate-errorMsg" style="color:red"></label>
	        </div>
	         <div class="layout-1">
	            <span class="layout-left">资讯价</span>
	            <input type="text" name="price" id="price">&nbsp;&nbsp;元/吨
	        </div>
	         <div class="layout-1">
	            <span class="layout-left">成交价</span>
	            <input type="text" name="transactionPrice" id="transactionPrice" >&nbsp;&nbsp;元/吨
	        </div>
	       <!--  <div class="layout-1">
	            <span class="layout-left">涨幅</span>
	            <span id="rise">0</span>&nbsp;&nbsp;元/吨
	        </div> -->
	        <div class="btn-box">
	            <button type="submit" class="btn btn-primary save">保存</button>
	            <button type="button" class="btn btn-danger" onclick="cancel()">取消</button>
	        </div>
	    </div>
	</form>
	
   
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/system/appChart/add.js"></script>
 </body>
</html>
