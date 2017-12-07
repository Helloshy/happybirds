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
    <title>抄表录入</title>
</head>

<body>
	<form id="dataFrom" action="${pageContext.request.contextPath}/meter/adjust/update.htm">
		<input  name="id" type="hidden" value="${resultMap.meterRecordId}"/>
	    <div class="dialog-1">
			<div class="layout-1">
				<span class="layout-left">燃气公司</span>
				<span>${resultMap.company_name}</span>
			</div>
			<div class="layout-1">
				<span class="layout-left">用户编号</span>
				<span>${resultMap.id}</span>
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
				<span>${resultMap.real_name}</span>
				<span style="margin-left: 75px;padding: 5px;">手机号码</span>
				<span>${resultMap.tel}</span>
			</div>
			<div class="layout-1">
				<span class="layout-left">用气地址</span>
				<span style="margin-left:15px;width: 300px">${resultMap.address}</span>
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
			</div>
			<div class="layout-1">
				<span class="layout-left">气表编号</span>
				<span>${resultMap.meter_no}</span>
			</div>
			<div class="layout-1">
				<span class="layout-left">上期表字</span>
				<span>${resultMap.last_count}</span>
				<span>当期表字</span>
				<span>${resultMap.current_count}</span>
				<span>用气量</span>
				<span id="gasConsum">${resultMap.gasConsum}</span>
			</div>
			<div class="layout-1">
				<span class="layout-left">费用</span>
				<span>${resultMap.amount}</span>
				<span >当前余额</span>
				<span>${resultMap.balance_amount}</span>
			</div>
			<div class="layout-1">
				<span class="layout-left">抄表员</span>
				<span>${resultMap.recordName}</span>
				<span >抄表时间</span>
				<span>${resultMap.record_time}</span>
			</div>
			<div class="layout-1">
				<span class="layout-left">调整当期表字</span>
				<i class="glyphicon glyphicon-plus"><input type="text" name="addMeter"/></i>
				<span >用气量</span>
				<span id="addGas"></span>
			</div>
			<div class="layout-1">
				<span class="layout-left">费用</span>
				<i class="glyphicon glyphicon-plus"><span id="addCharge"></span></i>
				<span >当前余额</span>
				<span id="amount"></span>
			</div>

	        <div class="btn-box">
	            <button type="submit" class="btn btn-primary save">保存</button>
	            <button type="button" class="btn btn-danger" onclick="cancel()">取消</button>
	        </div>
	    </div>
	</form>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/b2b/meter/import/edit.js"></script>
</body>



</html>
