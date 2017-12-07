package com.kapphk.web.service.web.imethod.financial;

import javax.servlet.http.HttpServletResponse;

import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
/**
 * 财务管理业务层
 * @author dengwen
 * 2016-11-07 11:11:18
 */
public interface BeanFinancialService {

	/**
	 * 查询列表
	 * @param type 
	 */
	public Result searchList(String startTime,String endTime,String orderNo, String realName, String teacherName, 
			String itemName, String userName,String title, String type, Result rs,GridReq gridReq) throws Exception;

	/**
	 * 购买课程人数
	 * @author dengwen 
	 * 2016-11-7下午2:56:34
	 */
	public Result searchPurchaseList(Long courseId, Result rs, GridReq gridReq);

	/**
	 * 导出数据
	 * @author dengwen 
	 * 2016-11-7下午3:13:18
	 * @param type 
	 */
	public void exportExcel(HttpServletResponse response,String startTime,String endTime,String orderNo,String realName,
			String teacherName,String itemName,String userName,String title,String type,Long courseId);

	public void managerBonus();

	public void courseUse();

	public void courseCredits();

	public void teacherOrder();

	public void cancellationOfOrder();

	public void productOrder();

}
