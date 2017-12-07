/****************************datebox********************************/
/**
 * 日期选择扩展
 * 增加清除操作 用法data-options中添加 buttons:bclear
 */
var bclear = $.extend([], $.fn.datebox.defaults.buttons);
bclear.splice(1, 0, {
	text: '清除',
	handler: function(target){
		var value = '';
		var state = $.data(target, 'datebox');
		var opts = state.options;
		$(target).combo('setValue', value).combo('setText', value);
		state.calendar.calendar('moveTo', opts.parser(value));
		$(target).combo('hidePanel');
	}
});
/****************************validatebox********************************/
// 扩展easyui的validatebox验证配置
$.extend($.fn.validatebox.defaults.rules, {
	number : {
		validator : function(value) { return /^[0-9]*$/.test(value); },
		message : '请输入正确的数字.'
	},
	phone: {
		validator : function(value) { return /^(1[0-9])[0-9]{9}$/.test(value); },
		message : '请输入正确的手机号码.'
	},
	equals : {
		validator : function(value, param) { return value == $(param[0]).val(); },
		message : '输入{1}不匹配.'
	},
	minLength: {
		validator: function(value, param){ return value.length >= param[0]; },
		message: '至少输入 {0} 个字符.'
	},
	maxLength: {
		validator: function(value, param){ return value.length <= param[0]; },
		message: '最多输入 {0} 个字符.'
	},
	/**
	 * 时间验证 验证开始时间不能超过结束时间
	 * 开始时间输入框设置为data-options="validType:'time[\'#endTime\',\'填报\']'"
	 * #endTime = 结束时间输入框ID \'填报\' = 提示XX开始时间不能超过XX结束时间
	 */
	time: {
		validator: function(value, param){
			return value >  $(param[0]).timespinner('getValue');
		},
		message: '结束{1}不能小于开始{1}！'
	},
	/**
	 * 时间验证 验证开始时间不能超过结束时间
	 * 结束时间输入框设置为data-options="validType:'datebox[\'#startDay\',\'有效\']'"
	 * #endTime = 结束时间输入框ID \'填报\' = 提示XX开始时间不能超过XX结束时间
	 */
	date: {
		validator: function(value, param){
			return value >=  $(param[0]).datebox('getValue');
		},
		message: '结束{1}不能小于开始{1}！'
	},
	numbox: {
		validator: function(value, param){
			return value >=  $(param[0]).numberbox('getValue');
		},
		message: '结束{1}不能小于开始{1}！'
	},
	combobox: {
		validator: function(value, param){
			return value >=  $(param[0]).combobox('getValue');
		},
		message: '结束{1}不能小于开始{1}！'
	},
	empty: {
		validator: function(value){ console.log(value); return $.trim(value).length > 0 ? true : false; },
		message: '必须输入！' 
	} 
});