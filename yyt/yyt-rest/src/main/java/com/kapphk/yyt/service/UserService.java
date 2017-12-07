package com.kapphk.yyt.service;

import com.kapphk.base.service.BaseService;
import com.kapphk.system.bean.User;
import com.kapphk.web.utils.Result;

public interface UserService extends BaseService<User, Long>{
	
	
	/**
	 * 微信登录
	 * @param user
	 * @return
	 */
	public Result login(User user,Result result);

	/**
	 * 员工登录
	 * @param username 账号
	 * @param pwd 密码
	 * @param result
	 */
	public void staffLogin(String username, String pwd, Result result);
	
	
}
