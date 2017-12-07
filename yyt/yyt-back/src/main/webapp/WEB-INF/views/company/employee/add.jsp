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
	<form id="dataFrom" action="${pageContext.request.contextPath}/employee/save.htm">
	    <div class="dialog-1">
			<div class="layout-1">
				<span class="layout-left">燃气公司：</span>
				<span class="layout-right">${company.companyName }</span>
				<input type="hidden" name="companyId" value="${company.id }">
			</div>
			<div class="layout-1">
				<span class="layout-left">员工账号：</span>
				<input type="text" name="userName" id="user"  required="required" >
				<span class="layout-left">员工姓名：</span>
				<input type="text" name="realName"  required="required" >
			</div>
			<div class="layout-1">
				<span class="layout-left">所在部门：</span>
				<select name="departmentTag" id="companyHallId" style="width:160px;">
					<option value="">--请选择--</option>
					<option value="客服部">客服部</option>
					<option value="工程部">工程部</option>
					<option value="市场部">市场部</option>
					<option value="管理层">管理层</option>
				</select>
				<span class="layout-left">所属营业厅：</span>
				<select name="companyHallId" id="companyHallId" style="width:160px;">
					<option value="">--请选择--</option>
					<c:forEach items="${halls}" var="hall">
						<option value="${hall.id }" >${hall.hallName }</option>
					</c:forEach>
				</select>
			</div>
			<div class="layout-1">
				<span class="layout-left">职位：</span>
				<input type="text" name="position"  required="required" >
				<span class="layout-left">联系方式：</span>
				<input type="text" name="tel"  required="required" >
			</div>
			<div class="layout-1">
				<span class="layout-left">权限组别：</span>
				<select name="roleId" id="roleId" style="width:160px;">
				<option value="">--请选择--</option>
				<c:forEach items="${roles}" var="role">
					<option value="${role.id }" >${role.roleName }</option>
				</c:forEach>
				</select>
			</div>
	        <div class="btn-box">
	            <button type="submit" class="btn btn-primary save">保存</button>
	            <button type="button" class="btn btn-danger" onclick="cancel()">取消</button>
	        </div>
	    </div>
	</form>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/company/employee/add.js"></script>
</body>



</html>
