package org.happybirds.oa.admin.identity.service;

import java.util.List;
import java.util.Map;

import org.happybirds.oa.admin.identity.entity.Job;
import org.happybirds.oa.admin.identity.entity.User;


/**
 * 权限模块业务处理接口
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2016-7-15 下午5:31:44
 * @version 1.0
 */
public interface IdentityService {

	List<Job> getJobs();
	
	/**
	 * 登录方法
	 * @param userId 用户名
	 * @param password 密码
	 * @param vcode 验证码
	 * @param key 是否记住用户 0: 不记住  1 : 记住
	 * @return Map集合
	 */
	Map<String, Object> login(String userId, String password, String vcode,
			int key);
	/**
	 * 根据主键userId查询用户
	 * @param userId 用户Id
	 * @return 用户
	 */
	User getUser(String userId, boolean isMD5);
}
