package com.kapphk.web.service.web.imethod.teacher;

import com.kapphk.web.utils.GridReq;
import com.kapphk.web.bean.teacher.BeanTeacherOrder;
import com.kapphk.web.utils.Result;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
/**
 * 预约讲学申请业务层
 * @author dengwen
 * 2016-10-09 10:15:25
 */
public interface BeanTeacherOrderService {

	/**
	 * 查询列表
	 */
	public Result searchList(Result rs, BeanTeacherOrder bean, String itemName, Integer type, String realName, GridReq gridReq) throws Exception;

	/**
	 * 保存
	 */
	public Result saveData(Result rs, BeanTeacherOrder bean) throws Exception;

	/**
	 * 详情
	 */
	public Result getData(Result rs, BeanTeacherOrder bean) throws Exception;

	/**
	 * 删除
	 */
	public Result delete(Result rs, List<Long> ids) throws Exception;

	/**
	 * 更新状态
	 * @author dengwen 
	 * 2016-10-9上午11:54:09
	 */
	public Result upstate(Result rs, List<Long> asList, BeanTeacherOrder bean) throws Exception;

	/**
	 * 评价详情
	 * @author dengwen 
	 * 2016-10-9下午3:27:50
	 */
	public Result commentDetail(Result rs, Long id) throws Exception;

	/**
	 * 导出
	 * @author dengwen 
	 * 2016-10-9下午4:32:58
	 */
	public void exportExcel(BeanTeacherOrder bean, String itemName,
			Integer type, String realName, HttpServletResponse response);

}
