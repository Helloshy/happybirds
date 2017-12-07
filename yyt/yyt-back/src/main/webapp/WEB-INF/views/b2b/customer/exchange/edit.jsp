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
    <title>修改</title>
</head>

<body>
	<form id="dataFrom" action="${pageContext.request.contextPath}/customer/list/update.htm">
		<input  name="id" type="hidden" value="${resultMap.id}"/>
	    <div class="dialog-1">
			<div class="layout-1">
				<span class="layout-left">燃气公司</span>
				<span><c:out value="${resultMap.company_name}"/></span>
			</div>
			<div class="layout-1">
				<span class="layout-left">用户编号</span>
				<span><c:out value="${resultMap.id}"/></span>
			</div>
			<div class="layout-1">
				<span class="layout-left">用户类型</span>
				<c:choose>
					<c:when test="${resultMap.record_tag eq '居民用户'}">
						<span>居民用户</span>
					</c:when>
					<c:when test="${resultMap.record_tag eq'公福用户'}">
						<span>公福用户</span>
					</c:when>
				</c:choose>
			</div>
			<div class="layout-1">
				<span class="layout-left">用户姓名</span>
				<input name="realName" value="${resultMap.real_name}" type="text"/>
				<span style="margin-left: 75px;padding: 5px;">手机号码</span>
				<input name="tel" value="${resultMap.tel}" type="text">
			</div>
			<div class="layout-1">
				<span class="layout-left">用气地址</span>
				<select name="itemName" id="itemName" style="width: 200px">
					<c:forEach items="${communities}" var="item">
						<option value="${item.itemName}">${item.itemName}</option>
					</c:forEach>
				</select>
				<input type="text" placeholder="详细地址" style="margin-left:15px;width: 300px"/>
			</div>
			<div class="layout-1">
				<span class="layout-left">用栓状态</span>
				<span>
					<c:choose>
						<c:when test="${resultMap.status == 0}">
							<span>闭栓</span>
						</c:when>
						<c:when test="${resultMap.status == 1 }">
							<span>开栓</span>
						</c:when>
					</c:choose>
				</span>
			</div>
			<div class="layout-1">
				<span class="layout-left">气表类型</span>
					<c:if test="${resultMap.status==1}">
						<c:choose>
							<c:when test="${resultMap.meter_type == 1}">
								<span>机械表</span>
							</c:when>
							<c:when test="${resultMap.meter_type == 2 }">
								<span>IC卡表</span>
							</c:when>
						</c:choose>
					</c:if>
			</div>
			<div class="layout-1">
				<span class="layout-left">气表编号</span>
				<span><c:out value="${resultMap.meter_no}"/></span>
			</div>
			<div class="layout-1">
				<span class="layout-left">当前余额</span>
				<span><c:out value="${resultMap.balance_amount}"/></span>
			</div>
	        <div class="btn-box">
	            <button type="submit" class="btn btn-primary save">保存</button>
	            <button type="button" class="btn btn-danger" onclick="cancel()">取消</button>
	        </div>
	    </div>
	</form>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/b2b/customer/edit.js"></script>
</body>



</html>
