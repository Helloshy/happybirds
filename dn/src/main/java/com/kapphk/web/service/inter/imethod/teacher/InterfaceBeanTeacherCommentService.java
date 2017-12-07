package com.kapphk.web.service.inter.imethod.teacher;

import com.kapphk.web.bean.teacher.BeanTeacherComment;
import com.kapphk.web.utils.Result;

public interface InterfaceBeanTeacherCommentService {

	/**
	 * 保存评价
	 * @author dengwen 
	 * 2016-10-20下午4:13:33
	 */
	public Result saveComment(BeanTeacherComment bean, Result rs)throws Exception;

	/**
	 * 陪伴师评价列表
	 * @author dengwen 
	 * 2016-10-18下午5:26:06
	 */
	public Result getCommentList(Long tid, Long tid2, Integer page, Result rs)throws Exception;
}
