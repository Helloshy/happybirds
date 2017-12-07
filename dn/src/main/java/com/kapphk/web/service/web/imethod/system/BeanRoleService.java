package com.kapphk.web.service.web.imethod.system;

import java.util.List;
import java.util.Map;

import com.kapphk.web.bean.system.BeanRole;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;

public interface BeanRoleService {

	/**
	 * 查询
	 * 
	 * @author dengwen 2016-9-14下午2:42:21
	 */
	public Result getList(BeanRole bean, GridReq grid, Result rs)
			throws Exception;

	/**
	 * 新增|修改
	 * 
	 * @author dengwen 2016-9-14下午2:42:21
	 */
	public Result saveData(BeanRole bean, List<Long> resourceId, Result rs)
			throws Exception;

	/**
	 * 详情
	 * 
	 * @author dengwen 2016-9-14下午2:42:21
	 */
	public Result getData(Long id, Result rs) throws Exception;

	/**
	 * 删除
	 * 
	 * @author dengwen 2016-9-14下午2:42:21
	 */
	public Result delete(List<Long> ids, Result rs) throws Exception;

	/**
	 * 初始化角色
	 * 
	 * @author dengwen 2016-9-14下午2:42:21
	 */
	public List<Map<String, Object>> searchGrant(List<Map<String, Object>> list)
			throws Exception;

}
