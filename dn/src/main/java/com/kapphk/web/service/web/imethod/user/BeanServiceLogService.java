package com.kapphk.web.service.web.imethod.user;

import com.kapphk.web.utils.GridReq;
import com.kapphk.web.bean.user.BeanServiceLog;
import com.kapphk.web.utils.Result;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 回访记录管理业务层
 * @author dengwen
 * 2016-11-04 10:15:58
 */
public interface BeanServiceLogService {

	/**
	 * 查询列表
	 */
	public Result searchList(Result rs, BeanServiceLog bean, String userName, String realName, GridReq gridReq) throws Exception;

	/**
	 * 保存
	 */
	public Result saveData(Result rs, BeanServiceLog bean, HttpServletRequest request) throws Exception;

	/**
	 * 详情
	 */
	public Result getData(Result rs, BeanServiceLog bean) throws Exception;

	/**
	 * 删除
	 */
	public Result delete(Result rs, List<Long> ids) throws Exception;

	/**
	 * 导出数据
	 * @author dengwen 
	 * 2016-11-4上午10:21:55
	 */
	public void exportExcel(HttpServletResponse response, BeanServiceLog bean);

}
