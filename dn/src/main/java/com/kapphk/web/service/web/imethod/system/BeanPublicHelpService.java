package com.kapphk.web.service.web.imethod.system;

import com.kapphk.web.bean.system.BeanPublicHelp;
import com.kapphk.web.utils.Result;

public interface BeanPublicHelpService {

	/**
	 * 查询
	 * @author zoneyu 2016-4-6
	 */
	public Result searchList(BeanPublicHelp PublicHelp,int page, int rows, Result rs) throws Exception ;

	/**
	 * 保存
	 * @author zoneyu 16-4-6
	 */
	public Result savePublicHelp(BeanPublicHelp PublicHelp, Result rs) throws Exception ;

	/**
	 * 修改
	 * @author zoneyu 16-4-6
	 */
	public Result editPublicHelp(Result rs,Long id) throws Exception ;

	/**
	 * 删除
	 * @author zoneyu 2016-4-6
	 */
	public Result delete(String ids, Result rs) throws Exception ;

}
