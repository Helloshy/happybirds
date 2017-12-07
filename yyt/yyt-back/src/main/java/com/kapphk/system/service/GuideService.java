package com.kapphk.system.service;

import java.util.List;

import com.kapphk.base.persistence.BaseService;
import com.kapphk.system.bean.Guide;
import com.kapphk.web.utils.Result;

public interface GuideService extends BaseService<Guide, Long> {

	/**
	 * 查询
	 */
	public Result searchList(Guide guide, int page, int rows, Result rs) throws Exception ;

	/**
	 * 保存
	 */
	public Result saveGuide(Guide guide, Result rs) throws Exception ;

	/**
	 * 修改
	 */
	public Guide editGuide(Long id) throws Exception ;

	/**
	 * 删除
	 */
	public Result delete(String ids, Result rs) throws Exception ;
	
	public List<Guide> findList(Guide guide);

}
