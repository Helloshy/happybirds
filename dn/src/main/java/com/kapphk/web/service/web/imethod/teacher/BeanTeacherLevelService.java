package com.kapphk.web.service.web.imethod.teacher;

import com.kapphk.web.utils.GridReq;
import com.kapphk.web.bean.teacher.BeanTeacherLevel;
import com.kapphk.web.utils.Result;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
/**
 * 讲学提成管理业务层
 * @author dengwen
 * 2016-10-08 09:54:23
 */
public interface BeanTeacherLevelService {

	/**
	 * 查询列表
	 */
	public Result searchList(Result rs, BeanTeacherLevel bean, GridReq gridReq) throws Exception;

	/**
	 * 保存
	 */
	public Result saveData(Result rs, BeanTeacherLevel bean) throws Exception;

	/**
	 * 详情
	 */
	public Result getData(Result rs, BeanTeacherLevel bean) throws Exception;

	/**
	 * 删除
	 */
	public Result delete(Result rs, List<Long> ids) throws Exception;

	/**
	 * 导出数据
	 * @author dengwen 
	 * 2016-10-8上午10:15:40
	 */
	public void exportExcel(HttpServletResponse response);

	/**
	 * 获取级别列表
	 * @author dengwen 
	 * 2016-10-8上午10:56:05
	 */
	public List<Map<String, Object>> searchLevelList(Integer recordType,
			String type);

}
