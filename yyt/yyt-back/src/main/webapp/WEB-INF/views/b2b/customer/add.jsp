<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<%@ include file="/common/include.jsp" %>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>修改</title>
</head>

<body>
	<form id="dataFrom" action="${pageContext.request.contextPath}/customer/list/update.htm">
		<input  name="companyId" type="hidden" value="${company.id}"/>
	    <div class="dialog-1">
			<div class="layout-1">
				<span class="layout-left">燃气公司</span>
				<span>${company.companyName}</span>
			</div>
			<div class="layout-1">
				<span class="layout-left">用户编号</span>
				<input name="id" type="text"/>
			</div>
			<div class="layout-1">
				<span class="layout-left">用户类型</span>
				<input name="recordTag" type="radio"  value="居名用户" /><span>居名用户</span>
				<input name="recordTag" type="radio"  value="公福用户"/><span>公福用户</span>
			</div>
			<div class="layout-1">
				<span class="layout-left">用户姓名</span>
				<input type="text" name="realName">
				<span style="margin-left: 75px;padding: 5px;">手机号码</span>
				<input name="tel"  type="text">
			</div>
			<div class="layout-1">
				<span class="layout-left">用气地址</span>
				<span>小区</span>
				<select name="communityId"  style="width: 200px">
					<c:forEach items="${communities}" var="community">
						<option value="${community.itemName}">${community.itemName}</option>
					</c:forEach>
				</select>
				<input type="text" name="unit" placeholder="详细地址" style="margin-left:15px;width: 300px"/>
			</div>
	        <div class="btn-box">
	            <button type="submit" class="btn btn-primary save">保存</button>
	            <button type="button" class="btn btn-danger" onclick="cancel()">取消</button>
	        </div>
	    </div>
	</form>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/b2b/customer/add.js"></script>
</body>



</html>
