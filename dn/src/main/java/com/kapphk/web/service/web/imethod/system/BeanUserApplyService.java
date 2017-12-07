package com.kapphk.web.service.web.imethod.system;

import com.kapphk.web.utils.GridReq;
import com.kapphk.web.bean.system.BeanUserApply;
import com.kapphk.web.utils.Result;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
/**
 * 申请管理业务层
 * @author dengwen
 * 2016-09-21 13:53:41
 */
public interface BeanUserApplyService {

	/**
	 * 查询列表
	 */
	public Result searchList(Result rs, BeanUserApply bean, GridReq gridReq) throws Exception;

	/**
	 * 保存
	 */
	public Result saveData(Result rs, BeanUserApply bean) throws Exception;

	/**
	 * 详情
	 */
	public Result getData(Result rs, BeanUserApply bean) throws Exception;

	/**
	 * 删除
	 */
	public Result delete(Result rs, List<Long> ids) throws Exception;

	public void exportExcel(BeanUserApply bean, HttpServletResponse response);

}
