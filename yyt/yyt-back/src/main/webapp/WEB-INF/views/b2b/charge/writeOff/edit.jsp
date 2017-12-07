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
    <title>缴费冲销</title>
</head>

<body>
	<form id="dataFrom" action="${pageContext.request.contextPath}/charge/writeOff/update.htm">
		<input  name="id" type="hidden" value="${resultMap.id}"/>
	    <div class="dialog-1">
			<div class="layout-1">
				<span class="layout-left">所属燃气公司</span>
				<span>${resultMap.company_name}</span>
			</div>
			<div class="layout-1">
				<span class="layout-left">用户编号</span>
				<span>${resultMap.id}</span>
			</div>
			<div class="layout-1">
				<span class="layout-left">用户姓名</span>
				<span>${resultMap.real_name}</span>
				<span style="margin-left: 75px;padding: 5px;">手机号码</span>
				<span>${resultMap.tel}</span>
			</div>
			<div class="layout-1">
				<span class="layout-left">用气地址</span>
				<span style="width: 300px">${resultMap.address}</span>
			</div>
			<div class="layout-1">
				<span class="layout-left">气表类型</span>
						<c:choose>
							<c:when test="${resultMap.meter_type == 1}">
								<span>机械表</span>
							</c:when>
							<c:when test="${resultMap.meter_type == 2 }">
								<span>IC卡表</span>
							</c:when>
						</c:choose>
				<span style="margin-left: 30px">气表编号</span>
				<span>${resultMap.meter_no}</span>
			</div>
			<div class="layout-1">
				<span class="layout-left">当前余额</span>
				<span>${resultMap.balance_amount}</span>
				<span style="margin-left: 30px">冲销金额</span>
				<span class="glyphicon glyphicon-minus" style="font-size: 10px"></span><input type="text" name="amount" />
			</div>
			<div class="layout-1">
				<span class="layout-left">冲销时间</span>
				<input type="text" class="date-picker" name="updateTime"/>
			</div>

	        <div class="btn-box">
	            <button type="submit" class="btn btn-primary save">确定</button>
	            <button type="button" class="btn btn-danger" onclick="cancel()">取消</button>
	        </div>
	    </div>
	</form>

	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/b2b/charge/writeOff/edit.js"></script>
</body>



</html>
