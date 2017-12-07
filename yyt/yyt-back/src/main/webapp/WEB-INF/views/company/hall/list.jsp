<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<%@ include file="/common/include.jsp" %>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>营业网点</title>
		<meta name="description" content="Dynamic tables and grids using jqGrid plugin" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<style type="text/css">
		    .widget-box{
				padding-bottom: 25px;
			}
			.widget-header{
				background: #6FB3E0;
			}
			.widget-title{
				color:#fff;
				font-size: 15px;
			}
	    </style>
	</head>

	<body class="no-skin">
		<!-- /section:basics/navbar.layout -->
		<div class="main-container ace-save-state" id="main-container">
			<script type="text/javascript">
				try{ace.settings.loadState('main-container')}catch(e){}
			</script>

			<div class="main-content">
				<div class="main-content-inner">
					<div class="page-content">
						<!-- 搜索框开始 -->
						<div class="widget-box" >
							<div class="widget-header ">
								<span class="widget-title">查询</span>
							</div>
							<div class="widget-body" >
								<div class="widget-main">
									<div class="form-group">
										<div class="col-sm-10">
											 <input type="text" id="hallName" placeholder="输入关键字查询"/>
											 &nbsp;&nbsp;&nbsp;
								            <button type="button" id="queryFun" class="btn btn-sm btn-info">
								      			<span class="glyphicon glyphicon-search" aria-hidden="true">查询</span>      
					                        </button>
										</div>
									</div>
								</div>
							</div>
				  	</div>
					<!-- 搜索框结束 -->
						<div class="row">
						<div class="doc-buttons" style="padding: 10px 0;">
								<c:forEach items="${res}" var="key">
									 ${key.btn }
								</c:forEach>
							</div>
							<div class="col-xs-12">

								<table id="grid-table"></table>

								<div id="grid-pager"></div>

								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div>
			</div><!-- /.main-content -->

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div>
		<script type="text/javascript" src="${ctx}/static/js/company/hall/list.js"></script>
	</body>
</html>
