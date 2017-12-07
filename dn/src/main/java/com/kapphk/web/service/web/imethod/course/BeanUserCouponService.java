package com.kapphk.web.service.web.imethod.course;

import com.kapphk.web.utils.GridReq;
import com.kapphk.web.bean.course.BeanUserCoupon;
import com.kapphk.web.utils.Result;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
/**
 * 抵扣卷业务层
 * @author dengwen
 * 2016-09-22 10:45:31
 */
public interface BeanUserCouponService {

	/**
	 * 查询列表
	 */
	public Result searchList(Result rs, BeanUserCoupon bean, GridReq gridReq) throws Exception;

	/**
	 * 保存
	 */
	public Result saveData(Result rs, BeanUserCoupon bean) throws Exception;

	/**
	 * 详情
	 */
	public Result getData(Result rs, BeanUserCoupon bean) throws Exception;

	/**
	 * 删除
	 */
	public Result delete(Result rs, List<Long> ids) throws Exception;

	/**
	 * 导出
	 * @author dengwen 
	 * 2016-9-28上午9:44:26
	 */
	public void exportExcel(BeanUserCoupon bean,HttpServletResponse response);

}
