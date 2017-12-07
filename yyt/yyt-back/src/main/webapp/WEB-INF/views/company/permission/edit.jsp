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
     <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/plugins/bootstrap-select/bootstrap-select.min.css" />
    <style type="text/css">
    .ke-dialog-default.ke-dialog {
	  z-index: 99999999!important;
	}
	.clearfix:after{
            content: "";
            display: block;
            clear: both;
        }
     .clearfix{
         zoom: 1;
     }
     .float-left{
         float: left;
     }
     .float-right{
         float: right;
     }
    </style>
    
</head>

<body>
	<form id="dataFrom" action="${pageContext.request.contextPath}/permission/save.htm">
	    <input type="hidden" name="id" value="${role.id }">
	    <div class="dialog-1">
	        <div class="layout-1">
	            <span class="layout-left">权限组别：</span>
	            <input name="roleName" type="text"  style="width:50%" value="${role.roleName }"/>
	        </div>
	        <div class="layout-1">
	            <span class="layout-left">适用员工：</span>
	              <select style="width:50%" name="uid" id="user" multiple>
		             <c:forEach items="${users}" var="user">
		             	<option value="${user.id}" <c:if test="${user.status==1 }">selected="selected" </c:if>>${user.realName }</option>
		             </c:forEach>
	             </select>
	        </div>
	        <div class="layout-1">
	            	<span class="layout-left">权限页面：</span>
	        </div>
	        <c:forEach items="${resource }" var="res">
		        <div class="layout-1 clearfix" style="margin-left:50px;">
		        	<span class="layout-left float-left"><input type="checkbox" class="parent resource${res.id }" name="resourceId" value="${res.id }"> ${res.resourceName }：</span>
		        	 <div class="layout-right float-left">
		        	 <c:forEach items="${res.menus }" var="menu">
		        	 	<label>
		        			<input type="checkbox" name="resourceId" class="child resource${menu.id }" value="${menu.id }">${menu.resourceName }&nbsp;
		        		</label>
		        	</c:forEach>
		        	</div>
		        </div>
	         </c:forEach>
	        <div class="btn-box">
	            <button type="submit" class="btn btn-primary save">保存</button>
	            <button type="button" class="btn btn-danger" onclick="cancel()">取消</button>
	        </div>
	    </div>
	</form>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/plugins/bootstrap-select/bootstrap-select.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/plugins/bootstrap-select/defaults-zh_CN.min.js"></script>
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/company/permission/edit.js"></script>
</body>

</html>
