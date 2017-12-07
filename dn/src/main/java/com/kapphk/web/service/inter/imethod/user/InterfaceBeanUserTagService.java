package com.kapphk.web.service.inter.imethod.user;

import com.kapphk.web.bean.tag.BeanUserTag;
import com.kapphk.web.service.BaseService;
import com.kapphk.web.utils.Result;

public interface InterfaceBeanUserTagService extends BaseService<BeanUserTag, String> {

	/**
	 * 标签集合
	 * @author zoneyu 16-9-30
	 */
	public Result getTagList(String tagType,Result rs) throws Exception ;

}
