<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
	<form id="dataFrom" action="${pageContext.request.contextPath}/community/save.htm">
	    <div class="dialog-1">
			<div class="layout-1">
				<span class="layout-left">所属燃气公司：</span>
				<span class="layout-right">${company.companyName }</span>
				<input type="hidden" name="companyId" value="${company.id }">
			</div>
			<div class="layout-1">
				<span class="layout-left">小区名称：</span>
				<input type="text" name="itemName" class="layout-right" required="required">
			</div>
			<div class="layout-1">
				<span class="layout-left">小区地址</span>
				<span class="layout-right">
				<select id="province" name="province" style="width:100px;">
				</select> 省
				<select id="city" name="city" style="width:100px;">
					<option value="">--请选择--</option>
				</select> 市
				<select id="district" name="district" style="width:100px;">
					<option value="">--请选择--</option>
				</select>区
				<input type="text" name="address"  required="required">
				</span>
			</div>
			
			<div class="layout-1">
				<span class="layout-left">所属营业厅：</span>
				<select name="companyHallId" id="companyHallId" class="layout-right">
				<option value="">--请选择--</option>
				<c:forEach items="${companyHalls}" var="companyHall">
					<option value="${companyHall.id }">${companyHall.hallName }</option>
				</c:forEach>
				</select>
			</div>
			<div class="layout-1">
				<span class="layout-left">抄表人：</span>
				<select name="sid" id="sid" class="layout-right">
				<option value="">--请选择--</option>
				</select>
			</div>
	        <div class="btn-box">
	            <button type="submit" class="btn btn-primary save">保存</button>
	            <button type="button" class="btn btn-danger" onclick="cancel()">取消</button>
	        </div>
	    </div>
	</form>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/loadProvince.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/company/community/add.js"></script>
</body>



</html>
