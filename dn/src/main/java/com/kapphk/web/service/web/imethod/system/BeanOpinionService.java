package com.kapphk.web.service.web.imethod.system;

import com.kapphk.web.bean.system.BeanOpinion;
import com.kapphk.web.utils.Result;

public interface BeanOpinionService {

	/**
	 * 查询
	 * @author cgj 2016-4-6
	 */
	public Result searchList(BeanOpinion Opinion, int page, int rows, Result rs) throws Exception ;

	/**
	 * 保存
	 * @author cgj 16-4-6
	 */
	public Result saveOpinion(BeanOpinion Opinion, Result rs) throws Exception ;

	/**
	 * 修改
	 * @author cgj 16-4-6
	 */
	public Result editOpinion(Result rs,Long id) throws Exception ;

	/**
	 * 删除
	 * @author cgj 2016-4-6
	 */
	public Result delete(Long id, Result rs) throws Exception ;

}
