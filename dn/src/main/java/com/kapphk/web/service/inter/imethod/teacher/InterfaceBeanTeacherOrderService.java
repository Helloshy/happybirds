package com.kapphk.web.service.inter.imethod.teacher;

import com.kapphk.web.bean.system.BeanSetting;
import com.kapphk.web.bean.teacher.BeanTeacherOrder;
import com.kapphk.web.utils.Result;

public interface InterfaceBeanTeacherOrderService {

	/**
	 * 保存订单
	 * @author dengwen 
	 * 2016-10-17下午5:25:30
	 */
	public Result saveTeacherOrder(BeanTeacherOrder bean, Result rs)throws Exception;

	/**
	 * 获取订单列表
	 * @author dengwen 
	 * 2016-10-20下午2:31:02
	 */
	public Result getTeacherOrderList(BeanTeacherOrder bean, Integer page, Result rs) throws Exception;

	/**
	 * 支付订单
	 * @author dengwen 
	 * 2016-10-20下午3:48:41
	 */
	public Result savePayOrder(BeanTeacherOrder bean, Result rs) throws Exception;

	/**
	 * 订单详情
	 * @author dengwen 
	 * 2016-10-20下午4:51:37
	 */
	public Result getOrderDetail(BeanTeacherOrder bean, Result rs) throws Exception;

	/**
	 * 取消订单
	 * @author dengwen 
	 * 2016-10-21上午11:59:48
	 */
	public Result cancelOrder(BeanTeacherOrder bean, Result rs) throws Exception;

	/**
	 * 获取列表最大抵扣比例
	 * @author dengwen 
	 * 2016-11-12上午11:14:52
	 */
	public Result getSettingDetail(BeanSetting bean, Result rs) throws Exception;

	/**
	 * 预约详情
	 * @author zoneyu 16-11-22
	 */
	public Result searchTeacherOrderDetail(Long id, Result rs) throws Exception;

}
