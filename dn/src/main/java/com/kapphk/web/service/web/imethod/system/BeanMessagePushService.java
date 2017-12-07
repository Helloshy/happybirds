package com.kapphk.web.service.web.imethod.system;

import com.kapphk.web.bean.system.BeanMessagePush;
import com.kapphk.web.utils.Result;

public interface BeanMessagePushService {

	/**
	 * 查询
	 * @author zoneyu 2016-6-12
	 */
	public Result searchList(BeanMessagePush MessagePush, int page, int rows, Result rs) throws Exception ;

	/**
	 * 保存
	 * @author zoneyu 16-6-12
	 */
	public Result saveMessagePush(BeanMessagePush MessagePush, String[] type, Result rs) throws Exception ;

	/**
	 * 删除
	 * @author zoneyu 2016-6-12
	 */
	public Result delete(String ids, Result rs) throws Exception ;

	/**
	 * 详情
	 * @author dengwen 
	 * 2016-11-12下午4:59:03
	 */
	public Result getData(Long id, Result rs) throws Exception;

}
