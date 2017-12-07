<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	.layout-left{
		width:18%!important;
	}
	.layout-right{
		width:50%!important;
	}
    </style>
    <script type="text/javascript">
	  	var province = '${companyHall.province}'
		var city = '${companyHall.city}'
		var district ='${companyHall.district}'
    </script>
</head>

<body>
	<form id="dataFrom" action="${pageContext.request.contextPath}/companyHall/save.htm">
	   	<input type="hidden" name="id" value="${companyHall.id }">
		<div class="dialog-1">
	        <div class="layout-1">
	            <span class="layout-left">所属燃气公司：</span>
	            <span class="layout-right">${company.companyName }</span>
	            <input type="hidden" name="companyId" value="${company.id}">
	        </div>
	        <div class="layout-1">
	            <span class="layout-left">营业网点：</span>
	            <input type="text" name="hallName" class="layout-right" value="${companyHall.hallName}" required="required">
	           
	        </div>
	        <div class="layout-1">
	            <span class="layout-left">负责人：</span>
	            <input name="manager" class="layout-right" required="required" value="${companyHall.manager}"/>
	        </div>
	        <div class="layout-1">
	            <span class="layout-left">联络电话：</span>
	             <input name="tel" class="layout-right" required="required" value="${companyHall.tel}"/>
	        </div>
				<div class="layout-1">
				<span class="layout-left">所在地址</span>
				<span>
				<select id="province" name="province" style="width:100px;">
				</select> 省
				<select id="city" name="city" style="width:100px;">
					<option value="">--请选择--</option>
				</select> 市
				<select id="district" name="district" style="width:100px;">
					<option value="" >--请选择--</option>
				</select>区
				<input type="text" name="address"  required="required" value="${companyHall.address}">
				</span>
			</div>
	        <div class="btn-box">
	            <button type="submit" class="btn btn-primary save">保存</button>
	            <button type="button" class="btn btn-danger" onclick="cancel()">取消</button>
	        </div>
	    </div>
	</form>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/loadProvince.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/company/hall/edit.js"></script>
</body>
</html>
