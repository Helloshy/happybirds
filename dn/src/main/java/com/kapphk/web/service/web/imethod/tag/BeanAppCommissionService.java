package com.kapphk.web.service.web.imethod.tag;

import com.kapphk.web.utils.GridReq;
import com.kapphk.web.bean.tag.BeanAppCommission;
import com.kapphk.web.utils.Result;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
/**
 * 分佣比例管理业务层
 * @author dengwen
 * 2016-10-28 14:36:24
 */
public interface BeanAppCommissionService {

	/**
	 * 查询列表
	 */
	public Result searchList(Result rs, BeanAppCommission bean, GridReq gridReq) throws Exception;

	/**
	 * 保存
	 */
	public Result saveData(Result rs, BeanAppCommission bean) throws Exception;

	/**
	 * 详情
	 */
	public Result getData(Result rs, BeanAppCommission bean) throws Exception;

	/**
	 * 删除
	 */
	public Result delete(Result rs, List<Long> ids) throws Exception;

	/**
	 * 导出
	 * @author dengwen 
	 * 2016-10-28下午3:18:15
	 */
	public void exportExcel(HttpServletResponse response);

}
