<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/common/include.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
</head>
<body>
    <h1>Welcome!!!</h1>
    <h2><a href="producer.htm">发送消息</a></h2>
    <h2><a href="receive.htm">从队列中取一个消息</a></h2>
      <div class="layout-1">
        <textarea id="editor_id" name="editor_id" cols="100" rows="8"></textarea>
        </div>
 	<script type="text/javascript">
    	KindEditor.ready(function(K){
    		window.editor = K.create("#editor_id");
    	});
    </script>
</body>
</html>