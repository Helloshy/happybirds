package com.kapphk.web.service.web.imethod.system;

import com.kapphk.web.utils.GridReq;
import com.kapphk.web.bean.system.BeanSetting;
import com.kapphk.web.utils.Result;
import java.util.List;
/**
 * 系统设置业务层
 * @author dengwen
 * 2016-10-11 16:53:42
 */
public interface BeanSettingService {

	/**
	 * 查询列表
	 */
	public Result searchList(Result rs, BeanSetting bean, GridReq gridReq) throws Exception;

	/**
	 * 保存
	 */
	public Result saveData(Result rs, BeanSetting bean) throws Exception;

	/**
	 * 详情
	 */
	public Result getData(Result rs, BeanSetting bean) throws Exception;

	/**
	 * 删除
	 */
	public Result delete(Result rs, List<Long> ids) throws Exception;

}
