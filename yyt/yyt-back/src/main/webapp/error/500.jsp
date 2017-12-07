<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>500-系统错误</title>
		<meta name="description" content="500 Error Page" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<link rel="stylesheet" href="${ctx}/assets/css/bootstrap.css" />
		<link rel="stylesheet" href="${ctx}/assets/css/ace-fonts.css" />
		<link rel="stylesheet" href="${ctx}/assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
		<link rel="stylesheet" href="${ctx}/assets/css/ace-skins.css" />
		<link rel="stylesheet" href="${ctx}/assets/css/ace-rtl.css" />
		<!-- ace settings handler -->
		<script src="${ctx}/assets/js/ace-extra.js"></script>
	</head>

	<body class="no-skin">
		<div class="main-container ace-save-state" id="main-container">
			<script type="text/javascript">
				try{ace.settings.loadState('main-container')}catch(e){}
			</script>
			<div class="main-content">
				<div class="main-content-inner">
					<div class="page-content">
						<!-- /section:settings.box -->
						<div class="row">
							<div class="col-xs-12">
								<div class="error-container">
									<div class="well">
										<h1 class="grey lighter smaller">
											<span class="blue bigger-125">
												<i class="ace-icon fa fa-random"></i>
												500
											</span>
											系统错误
										</h1>

										<hr />
										<h3 class="lighter smaller">
											But we are working
											<i class="ace-icon fa fa-wrench icon-animated-wrench bigger-125"></i>
											on it!
										</h3>

										<div class="space"></div>

										<div>
											<h4 class="lighter smaller">Meanwhile, try one of the following:</h4>

											<ul class="list-unstyled spaced inline bigger-110 margin-15">
												<li>
													<i class="ace-icon fa fa-hand-o-right blue"></i>
													Read the faq
												</li>

												<li>
													<i class="ace-icon fa fa-hand-o-right blue"></i>
													Give us more info on how this specific error occurred!
												</li>
											</ul>
										</div>

										<hr />
										<div class="space"></div>

										<div class="center">
											<a href="javascript:history.back()" class="btn btn-grey">
												<i class="ace-icon fa fa-arrow-left"></i>
												返回
											</a>

											<a href="javascript:void(0)" class="btn btn-primary" id="showError">
												<i class="ace-icon fa fa-tachometer"></i>
												查看错误信息
											</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div><!-- /.main-container -->
	<script src="${ctx}/static/js/jquery-1.9.1.min.js"></script>
	<script src="${ctx}/static/plugins/layer/layer.js"></script>
	<script type="text/javascript">
	$("#showError").click(function(){
		layer.open({
			  type: 1,
			  skin: 'layui-layer-rim', //加上边框
			  area: ['70%', '70%'], //宽高
			  content: '${exception}'
		});
	});
	</script>
	</body>
</html>
