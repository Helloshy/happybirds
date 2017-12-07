

var pathName=window.document.location.pathname;
var webRoot = pathName.substring(0,pathName.substr(1).indexOf('/')+1);
/**
 * <script type="text/javascript" src="<%=basePath%>/pictures/picture.js"></script> 引入js
 * PictureUtils.java 拷贝到后台java
 * dataPicture.init 添加时初始化
 * dataPicture.editPicture 修改时初始化 需要个数组图片
 * dataPicture.inmgs 图片值 xx.png,yy.png
 */
var style_image = '&nbsp;&nbsp;<div style="float: left;padding: 25px;width: 100px;height: 100px;" onmouseover="style_imageOver()" onmouseout="style_imageOut()" onclick="addPicture()">'
		+ '<img id="style_dd" src="'+webRoot+'/pictures/images/imageOut.png" style="width:100px;height:100px;"></img></div>';
var dataPicture = {
	imgs:"",
	init:function(){
		imgs = "";
		$("#imgs").html(style_image);
	},
	editPicture:function(array){
		var count=0;
		var html="";
		for ( var i = 0; i < array.length; i++) {
			if('' != array[i]){
				if(i%2 == 0)html += "<br/><br/>";
				html += '&nbsp;&nbsp;<div style="float: left;padding: 10px;width: 140px;height: 140px;" onmouseover="style_imageDelOver(this)" onmouseout="style_imageDelOut(this)">';
				html += '<img class="style_img" style="width:140px;height:140px;" src="'+array[i]+'"></img>';
				html += '<img class="style_delBtn" style="float: left;z-index: 99999;position: relative;margin-left: 110px;margin-top: -140px;width: 30px;display: none;"';
				html += 'onclick="removePicture(this)" src="'+webRoot+'/pictures/images/image_remove.png"></img></div>';
				count++;
			}
		}
		if(count%2 == 0)html += "<br/><br/>";
		html += style_image;
		$("#imgs").html(html);
		getImgs();
	}
}

//弹出添加窗口
function addPicture(){
	$("#multiple_picture").val("");// 清除form表单数据
	$( "#add-picture" ).dialog("open").dialog('setTitle','添加');
}

//删除图片
var removePicture = function(obj){
	$.messager.confirm('确认','确认要删除所选图片?',function(r){  
	       if(r){
	    	   $(obj).parent('div').remove();
		       var html = "";
		       var count;
		       $("#imgs").find(".style_img").each(function (i, domEle) { 
		    	   if(i%2 == 0)html += "<br/><br/>";
					html += '&nbsp;&nbsp;<div style="float: left;padding: 10px;width: 140px;height: 140px;" onmouseover="style_imageDelOver(this)" onmouseout="style_imageDelOut(this)">';
					html += '<img class="style_img" style="width:140px;height:140px;" src="'+$(domEle).attr('src')+'"></img>';
					html += '<img class="style_delBtn" style="float: left;z-index: 99999;position: relative;margin-left: 110px;margin-top: -140px;width: 30px;display: none;"';
					html += 'onclick="removePicture(this)" src="'+webRoot+'/pictures/images/image_remove.png"></img></div>';
					i++;
					count = i;
		       });
		       if(count%2 == 0)html += "<br/><br/>";
			   html += style_image;
		       $("#imgs").text('');
		       $("#imgs").html(html);
		       getImgs();
	       }
	});
}

//保存图片
var savePicture = function(){
	//保存图片路径
	var url = webRoot+"/web/pictureutils/savePictures.htm";
	var check = function(){};
	var success = function(data){
		if(data != null){
			var count = $("#imgs").find(".style_img").length;
			var html = "";
			$('#style_dd').parent('div').remove();
			for ( var i = 0; i < data.length; i++) {
				if(count%2 == 0)html += "<br/><br/>";
				html += '&nbsp;&nbsp;<div style="float: left;padding: 10px;width: 140px;height: 140px;" onmouseover="style_imageDelOver(this)" onmouseout="style_imageDelOut(this)">';
				html += '<img class="style_img" style="width:140px;height:140px;" src="'+data[i]+'"></img>';
				html += '<img class="style_delBtn" style="float: left;z-index: 99999;position: relative;margin-left: 110px;margin-top: -140px;width: 30px;display: none;"';
				html += 'onclick="removePicture(this)" src="'+webRoot+'/pictures/images/image_remove.png"></img></div>';
				count++;
			}
			if(count%2 == 0)html += "<br/><br/>";
			html += style_image;
			//追加img
			$("#imgs").append(html);
			getImgs();
			$('#add-picture').dialog('close');
		}
	}
	formSubmit($('#add-form-picture'),url,check,success);
}

//获取图片
var getImgs = function(){
	var img = [];
	 $("#imgs").find(".style_img").each(function (i, domEle) { 
		 img.push($(domEle).attr('src'));
	 });
	 dataPicture.imgs = img.join(",");
}

//显示图片
var style_imageDelOver = function(obj){
	$(obj).find('.style_delBtn').show();
}

//隐藏图片
var style_imageDelOut = function(obj){
	$(obj).find('.style_delBtn').hide();
}

var style_imageOver = function(){
	$('#style_dd').attr('src',webRoot+'/pictures/images/imageMove.png');
	//$('#style_dd').css('border','2px dashed #CCE6FF');
}
var style_imageOut = function(){
	$('#style_dd').attr('src',webRoot+'/pictures/images/imageOut.png');
	//$('#style_dd').css('border','2px dashed gray');
}