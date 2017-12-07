<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>详细信息</title>
    <style type="text/css">
    .ke-dialog-default.ke-dialog {
	  z-index: 99999999!important;
	}
    </style>
    
</head>
<body>
    <div class="dialog-1">
         <div class="layout-1">
            <span class="layout-left">昵称：</span>
            <span class="layout-right">${userApply.nickName }</span>
        </div>
         <div class="layout-1">
            <span class="layout-left">注册账号：</span>
            <span class="layout-right">${userApply.userName }</span>
        </div>
         <div class="layout-1">
            <span class="layout-left">联系方式：</span>
            <span class="layout-right">${userApply.phone }</span>
        </div>
         <div class="layout-1">
            <span class="layout-left">申请留言：</span>
            <span class="layout-right">${userApply.remark }</span>
        </div>
         <div class="layout-1">
            <span class="layout-left">更新时间：</span>
            <span class="layout-right">
            <fmt:formatDate value="${userApply.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
            </span>
        </div>
        <div class="btn-box">
            <button type="button" class="btn btn-danger" onclick="cancel()">关闭</button>
        </div>
    </div>
 </body>
</html>
