	CommonUtil = {
		/**
		 * ajax同步请求 返回一个html内容 dataType=html. 默认为html格式 如果想返回json.
		 * CommonUtil.ajax(url, data,"json")
		 * 
		 */
		ajax : function(url, type, data, dataType) {
			if (dataType == null || dataType == "" || dataType == undefined) {
				dataType = "html";
			}
			if (type == null || type == "" || type == undefined) {
				dataType = "post";
			}
			var html = '没有结果!';
			if (url.indexOf("?") > -1) {
				url = url + "&_t=" + new Date();
			} else {
				url = url + "?_t=" + new Date();
			}
			$.ajax({
				type : type,
				data : data,
				url : url,
				dataType : dataType,// 这里的dataType就是返回回来的数据格式了html,xml,json
				async : false,
				cache : false,// 设置是否缓存，默认设置成为true，当需要每次刷新都需要执行数据库操作的话，需要设置成为false
				success : function(data) {
					html = data;
				}
			});
			return html;
		}
	}
	
	
	 function htmlspecialchars(str) {
		 if(str == null || str == "" && str == undefined){
			 return "";
		 }
		var s = "";
		if (str.length == 0)
			return "";
		for (var i = 0; i < str.length; i++) {
			switch (str.substr(i, 1)) {
			case "<":
				s += "&lt;";
				break;
			case ">":
				s += "&gt;";
				break;
			case "&":
				s += "&amp;";
				break;
			case " ":
				if (str.substr(i + 1, 1) == " ") {
					s += " &nbsp;";
					i++;
				} else
					s += " ";
				break;
			case "\"":
				s += "&quot;";
				break;
			case "\n":
				s += "";
				break;
			default:
				s += str.substr(i, 1);
				break;
			}
		}
		return s;
	}