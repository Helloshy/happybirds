<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<!DOCTYPE html>
<html>
	<head>
	<%@include file="/common/include.jsp" %>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="renderer" content="webkit">
		<title>系统管理后台-登录</title>

		<meta name="description" content="User login page" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<!-- text fonts -->
		<link rel="stylesheet" href="${ctx}/assets/css/ace-fonts.css" />
		<!-- ace styles -->
		<link rel="stylesheet" href="${ctx}/assets/css/ace.css" />

		<!--[if lte IE 9]>
			<link rel="stylesheet" href="${ctx}/assets/css/ace-part2.css" />
		<![endif]-->
		<link rel="stylesheet" href="${ctx}/assets/css/ace-rtl.css" />

		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="${ctx}/assets/css/ace-ie.css" />
		<![endif]-->
		<script type="text/javascript">
			if (window.top!=null && window.top.document.URL!=document.URL){   
		        window.top.location= document.URL;
		    }   
		</script>
	</head>

	<body class="login-layout  light-login">
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container">
							<div class="center">
								<h1>
									<i class="ace-icon fa fa-leaf green" style="display: none;"></i>
									<span class="grey" id="id-text2">燃e通</span>
								</h1>
								<h4 class="blue" id="id-company-text">&copy; 启汇</h4>
							</div>
							<div class="space-6"></div>
							<div class="position-relative">
								<div id="login-box" class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header blue lighter bigger">
												<i class="ace-icon fa fa-coffee green"></i>
												请输入您的信息
											</h4>
											<div class="space-6"></div>
											<form action="${ctx}/system/login.htm" method="post" class="data-form">
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" name="username" class="form-control" placeholder="用户名" />
															<i class="ace-icon fa fa-user"></i>
														</span>
													</label>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" name="password" class="form-control" placeholder="密码" />
															<i class="ace-icon fa fa-lock"></i>
														</span>
													</label>
													<div class="space"></div>
													<div class="clearfix">
														<label class="inline">
															<input type="checkbox" name="rememberMe" class="ace" />
															<span class="lbl"> 记住我</span>
														</label>
														<button type="button" class="width-35 pull-right btn btn-sm btn-primary">
															<i class="ace-icon fa fa-key"></i>
															<span class="bigger-110">登录</span>
														</button>
													</div>
													<div class="space-4"></div>
												</fieldset>
											</form>
										</div>
										<div class="toolbar clearfix">
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='${ctx}/static/js/jquery.mobile.custom.js'>"+"<"+"/script>");
		</script>
		<script type="text/javascript">
			$(".btn-primary").click(function(){
				$(".data-form").submit();
			});
			document.onkeydown = function(e) {
		        e = e || window.event;
		        if(e.keyCode == 13) {
		            $(".data-form").submit();
		        }
		    }
			if("${error}" != ""){
				layer.alert("${error}");
			}
		</script>
	</body>
</html>
