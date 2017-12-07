package com.kapphk.web.service.web.imethod.user;

import com.kapphk.web.utils.GridReq;
import com.kapphk.web.bean.user.BeanUserCashApply;
import com.kapphk.web.utils.Result;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
/**
 * 提现申请管理业务层
 * @author dengwen
 * 2016-10-28 11:05:33
 */
public interface BeanUserCashApplyService {

	/**
	 * 查询列表
	 */
	public Result searchList(Result rs, BeanUserCashApply bean, String userName, String realName, String startTime, String endTime,
			GridReq gridReq) throws Exception;

	/**
	 * 保存
	 */
	public Result saveData(Result rs, BeanUserCashApply bean) throws Exception;

	/**
	 * 详情
	 */
	public Result getData(Result rs, BeanUserCashApply bean) throws Exception;

	/**
	 * 删除
	 */
	public Result delete(Result rs, List<Long> ids) throws Exception;

	/**
	 * 更新处理与到账
	 * @author dengwen 
	 * 2016-10-28上午11:18:06
	 */
	public Result upstatus(Result rs, List<Long> ids, Integer state) throws Exception;

	/**
	 * 导出
	 * @author dengwen 
	 * 2016-10-28上午11:18:27
	 */
	public void exportExcel(HttpServletResponse response,BeanUserCashApply bean, String userName, String realName, String startTime, String endTime);

}
