package com.kapphk.web.service.web.imethod.teacher;

import java.util.List;

import com.kapphk.web.bean.teacher.BeanAppAccompany;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
/**
 * 陪伴师经历业务层
 * @author dengwen
 * 2016-10-10 11:24:15
 */
public interface BeanAppAccompanyService {

	/**
	 * 查询列表
	 */
	public Result searchList(Result rs, Long teacherId, GridReq gridReq) throws Exception;

	/**
	 * 保存
	 */
	public Result saveData(Result rs, BeanAppAccompany bean,String[] imgs) throws Exception;

	/**
	 * 详情
	 */
	public Result getData(Result rs, BeanAppAccompany bean) throws Exception;

	/**
	 * 删除
	 */
	public Result delete(Result rs, List<Long> ids) throws Exception;

	public Result getUserName(Result rs, Long id) throws Exception;

}
