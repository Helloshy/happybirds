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
    <title>确认缴费</title>
</head>

<body>
	<form id="dataFrom2" action="${pageContext.request.contextPath}/charge/manage/confirmPayment.htm">
		<input  name="id" type="hidden" value="${resultMap.id}"/>
		<input type="hidden" name="createTime" value="${resultMap.createTime}">
	    <div class="dialog-1">
			<div class="layout-1">
				<span class="layout-left">用户姓名</span>
				<span>${resultMap.real_name}</span>
			</div>
			<div class="layout-1">
				<span class="layout-left">用气地址</span>
				<span style="margin-left:15px;width: 300px">${resultMap.address}</span>
			</div>
			<div class="layout-1">
				<span class="layout-left">缴费金额</span>
				<input type="text" name="amount" value="${resultMap.amount}"/>
				<input type="radio" name="payMethod" value="1" checked="checked"/><span>现金支付</span>
			</div>
	        <div class="btn-box">
	            <button type="submit" class="btn btn-primary save">确认并打印票据</button>
	            <button type="button" class="btn btn-danger" onclick="cancel()">取消</button>
	        </div>
	    </div>
	</form>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/b2b/charge/manage/payment.js"></script>
</body>



</html>
