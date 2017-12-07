<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<%@include file="/common/include.jsp" %>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>资质认证审核详情</title>
    <style type="text/css">
    	.row b{
    		float:left
    	}
    	.label-title{
    		width:94px
    	}
    	.jbox-body{
    		z-index: 999999999!important
    	}
    	img{
    		width: 300px;
    		height: 200px;
    	}
    	.thumbnail > img, .thumbnail a > img {
		    margin-left: auto;
		    margin-right: auto;
		    width: 100%;
		}
		a.thumbnail {
		    width: 300px;
		    height: 200px;
		    overflow: hidden;
		}
		span.text {
		    position: absolute;
		    color: black;
		}
    </style>
</head>
<body>
<div class="col-sm-12">

		<button type="button" class="btn btn-sm btn-info" onclick="history.go(-1)">返回</button>
		<c:if test="${upgrade.state ==0 }">
			<button type="button" class="btn btn-sm btn-info" onclick="approve(1)">通过</button>
			<button type="button" class="btn btn-sm btn-info" onclick="approve(2)">不通过</button>
		</c:if>
		<div class="row col-sm-12">
			<div class="col-xs-5 label label-lg label-info arrowed-in arrowed-right">
				<b>账户信息</b>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-2">
				<label class="control-label col-sm-1 label-title">用户名：</label>
				<label class="control-label col-sm-2">${user.userName}</label>
	    	</div>
	    	<div class="col-sm-3">
				<label class="control-label col-sm-1 label-title" style="width:150px;">账号（手机号）：</label>
				<label class="control-label col-sm-2" >${user.registerPhone }</label>
			</div>
			<div class="col-sm-2">
				<label class="control-label col-sm-1 label-title">邮箱：</label>
				<label class="control-label col-sm-2">${user.email }</label>
			</div>
		</div>
		<div class="row col-sm-12">
			<div class="col-xs-5 label label-lg label-info arrowed-in arrowed-right">
				<b>企业信息</b>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-12">
				<label class="control-label col-sm-2 label-title">企业名称：</label>
				<label class="control-label col-sm-3">${user.companyName }</label>
	    	</div>
	    </div>
	    <div class="form-group">
	    	<div class="col-sm-3">
				<label class="control-label col-sm-2 label-title">联系人：</label>
				<label class="control-label col-sm-3">${user.contactPerson}</label>
			</div>
			<div class="col-sm-3">
				<label class="control-label col-sm-2 label-title">联系电话：</label>
				<label class="control-label col-sm-3" style="width: 200px">${user.phone}</label>
			</div>
			<div class="col-sm-3">
				<label class="control-label col-sm-2 label-title">QQ号：</label>
				<label class="control-label col-sm-3">${user.qq}</label>
			</div>
			<div class="col-sm-3">
				<label class="control-label col-sm-2 label-title">传真：</label>
				<label class="control-label col-sm-3" style="width: 200px">${user.fax}</label>
			</div>
		</div>
		<div class="form-group">
	    	<div class="col-sm-5">
				<label class="control-label col-sm-2 label-title">企业地址：</label>
				<label class="control-label col-sm-4">${user.address}</label>
			</div>
			<div class="col-sm-3">
				<label class="control-label col-sm-2 label-title">企业性质：</label>
				<label class="control-label col-sm-4" >
					<c:forEach items="${details}" var="detail">
						<c:if test="${detail.tagType =='账户类型' }">
							${detail.tagValue }
						</c:if>
					</c:forEach>
				</label>
			</div>
			<div class="col-sm-3">
				<label class="control-label col-sm-2 label-title">资质身份：</label>
				<label class="control-label col-sm-3">
				<c:if test="${user.isBuyer == 1}">买家</c:if>
				<c:if test="${user.isBuyer == 1 and user.isSeller == 1}">、</c:if>
				<c:if test="${user.isSeller == 1}">卖家</c:if>
				<c:if test="${user.isSeller != 1 && user.isBuyer != 1 }">----</c:if>
				</label>
			</div>
		</div>
		<div class="form-group">
	    	<div class="col-sm-12">
				<label class="control-label col-sm-2 label-title">企业介绍：</label>
				<label class="control-label col-sm-4">${user.remark}</label>
			</div>
		</div>
		<div class="row col-sm-12">
			<div class="col-xs-5 label label-lg label-info arrowed-in arrowed-right">
				<b>资质认证</b>
			</div>
		</div>
		<div class="form-group">
			<div class="row">
				<c:forEach items="${details}" var="detail">
				<c:if test="${detail.tagType =='资质' }">
				<%-- <c:if test="${not empty detail.logoPath && detail.logoPath != ''}"> --%>
			    <div class="col-sm-6 col-md-3"  style="padding-top:20px;">
			        <a href="#" class="thumbnail">
			        	<img width="300px;" src="${fileServer}${detail.logoPath }">
			        </a>
			        <span class="text" style="margin-left:100px;">${detail.tagValue }</span>
			    </div>
			     <%-- </c:if> --%>
			    </c:if>
			    </c:forEach>
			</div>
	    </div>
	    <input type="hidden" id="id" value="${upgrade.id }">
	    <input type="hidden" id="usid" value="${upgrade.uid }">
	    <script type="text/javascript" src="${ctx}/static/js/system/identity/details.js"></script>
	
</div>
</body>
</html>
