package com.kapphk.web.service;

import com.kapphk.web.bean.system.BeanPublicHelp;
import com.kapphk.web.utils.Result;

public interface PublicHelpService extends BaseService<BeanPublicHelp, Long> {

	/**
	 * 查询详情
	 * @author cgj 15-12-29
	 */
	public String findPublicDetail(BeanPublicHelp help) throws Exception ;

	/**
	 * 查询常见问题列表
	 * @author cgj 16-3-20
	 */
	public Result searchList(BeanPublicHelp help,Result rs) throws Exception ;

	/**
	 * 动能社区介绍
	 * @author exuan 16-10-31
	 */
	public Result searchDNList(BeanPublicHelp bean, Result rs) throws Exception ;

}
