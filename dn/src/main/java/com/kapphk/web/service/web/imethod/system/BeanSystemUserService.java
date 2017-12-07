package com.kapphk.web.service.web.imethod.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.kapphk.web.bean.system.BeanSystemUser;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;

public interface BeanSystemUserService {

	/**
	 * 保存
	 * @author dengwen 
	 * 2016-9-14下午1:58:03
	 */
	public Result saveData(BeanSystemUser bean, Long roleId, Result rs)throws Exception;
	
	/**
	 * 查询
	 * @author dengwen 
	 * 2016-9-14下午1:56:36
	 */
	public Result searchList(BeanSystemUser bean, GridReq grid, Result rs)throws Exception;

	/**
	 * 删除
	 * @author dengwen 
	 * 2016-9-14下午2:18:34
	 */
	public Result delete(List<Long> ids, Result rs)throws Exception;

	/**
	 * 详情
	 * @author dengwen 
	 * 2016-9-14下午2:23:11
	 */
	public Result getData(Long id, Result rs)throws Exception;

	/**
	 * 登录
	 * @author dengwen 
	 * 2016-9-14下午2:56:18
	 */
	public Result sign(BeanSystemUser bean, HttpServletRequest request,
			Result rs)throws Exception;

	/**
	 * 修改密码
	 * @author dengwen 
	 * 2016-9-14下午3:04:17
	 */
	public Result changePwd(HttpServletRequest request, String opwd,
			String npwd, Result rs)throws Exception;
	
}


  