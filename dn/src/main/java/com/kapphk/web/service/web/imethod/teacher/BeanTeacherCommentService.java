package com.kapphk.web.service.web.imethod.teacher;

import com.kapphk.web.utils.GridReq;
import com.kapphk.web.bean.teacher.BeanTeacherComment;
import com.kapphk.web.utils.Result;
import java.util.List;
/**
 * 名师评价业务层
 * @author dengwen
 * 2016-10-08 18:07:37
 */
public interface BeanTeacherCommentService {

	/**
	 * 查询列表
	 */
	public Result searchList(Result rs, BeanTeacherComment bean, GridReq gridReq) throws Exception;

	/**
	 * 保存
	 */
	public Result saveData(Result rs, BeanTeacherComment bean) throws Exception;

	/**
	 * 详情
	 */
	public Result getData(Result rs, BeanTeacherComment bean) throws Exception;

	/**
	 * 删除
	 */
	public Result delete(Result rs, List<Long> ids) throws Exception;

}
