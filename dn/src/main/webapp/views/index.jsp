<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/";%>
<%String contextPath = request.getContextPath();%>
<jsp:include page="include.jsp"></jsp:include>
<!DOCTYPE HTML">
<html>
  <head>
    <title>动能后台管理系统</title>
    <script type="text/javascript">
		$.extend($.fn.validatebox.defaults.rules, {    
		    opwd: {    
		        validator: function(value){ 
		        	var bool = false;
		        	if(value.indexOf(" ") != -1){
		        	}else if(value.length < 6 || value.length > 12){
		        	}else{
		        		bool = true;
		        	}
		            return bool;    
		        },  
		        message: '密码长度为6-12位且非空格字符',   
		    },
		    npwd: {    
		        validator: function(value){ 
		        	var data = $('#pdata-form').form('getData');
		        	return value == data.opwd ? false : true;   
		        },  
		        message: '新密码不能不旧密码相同',   
		    }, 
		    npwdc: {    
		        validator: function(value){ 
		        	var data = $('#pdata-form').form('getData');
		        	return data.npwd != value ? false : true;   
		        },  
		        message: '确认密码与新密码不一致',   
		    }   
		}); 		
    	window.onerror=function(sMessage, sUrl, sLine){
    		//$.messager.progress('close');
    		//$.messager.alert('错误!',sMessage +"\n"+sUrl+"\n请刷新再试!",'error');
    		return true;
    	}
    	var showEditPwd = function(){
    		var doms = $('#pdata-form .easyui-textbox');
			$.each(doms, function(i, domEle) {
				$(domEle).textbox('setValue', '');
			});
    		$('#pupdate-dialog').dialog('open');
    	}
    	var editPwd = function(){
    		if(!$("#pdata-form").form('validate'))return;
    		var data = $('#pdata-form').form('getData');
    		asynPAjaxMin('<%=basePath%>web/system/systemuser/changePwd.htm',data,function(data){
    				console.log(data);
    				if(data && data.status == '10001'){
    					$.messager.alert('提示', '修改密码成功，请重新登录！','info',function(){
    						parent.window.location.href = '<%=basePath%>/login/login.jsp';
    					});
    				
    				}else{
    					$.messager.alert('错误', data.msg,'error');
    				}
    		},null);	
    	};
 
    </script>
  </head>
  <body>
  <div id="bodyLayout" class="easyui-layout" style="width:100%;height:100%;" data-options="fit:true">
  	 <div data-options="region:'north',border:false" style="height:81px;">
  	 	<iframe name="_topFrame" src="<%=basePath %>views/north.jsp" allowTransparency="true" style="border:0;width:100%;height:100%;" frameBorder="0"></iframe>
  	 </div>
     <div  id="myMain" data-options="region:'center',border:false,fit:true,href:'<%=basePath %>/views/main.jsp',onResize:myResize" style="padding:0px;width:100%;height:100%;">
     </div>
  </div>
  
  <!-- 添加/修改弹出dialog buttons配置按钮默认是添加与取消按钮 -->
	<div id="pupdate-dialog" class="easyui-dialog" style="width: 450px;height: 250px;" 
		data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,title:'修改密码',cache: false,
		buttons:[{text:'修改',iconCls:'icon-ok',handler:editPwd},{text:'关闭',iconCls:'icon-cancel',handler:function(){$('#pupdate-dialog').dialog('close');}}]">
		<form id="pdata-form" method="post" style="margin: 0px;padding: 0px;height: 96%" >
		<input type="hidden" name="id" />
		<table class="mf-table">
			<tr class="mf-line hasRole">
				<td class="mf-left">原密码：</td>
				<td class="mf-right"> <input type="password" name="opwd" class="easyui-textbox" style="width: 250px" data-options="required:true,validType:'opwd'"> </td>
			</tr>
			<tr class="mf-line hasRole">
				<td class="mf-left">新密码：</td>
				<td class="mf-right"> <input type="password" name="npwd" class="easyui-textbox" style="width: 250px" data-options="required:true,validType:['opwd','npwd']"> </td>
			</tr>
			<tr class="mf-line hasRole">
				<td class="mf-left">确认密码：</td>
				<td class="mf-right"> <input type="password" name="npwd1" class="easyui-textbox" style="width: 250px" data-options="required:true,validType:'npwdc'"> </td>
			</tr>
		</table>
		</form>
	</div>
  </body>
</html>
