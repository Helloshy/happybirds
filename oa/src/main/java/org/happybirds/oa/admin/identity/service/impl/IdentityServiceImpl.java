package org.happybirds.oa.admin.identity.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.happybirds.oa.admin.AdminConstant;
import org.happybirds.oa.admin.identity.dao.DeptDao;
import org.happybirds.oa.admin.identity.dao.JobDao;
import org.happybirds.oa.admin.identity.dao.ModuleDao;
import org.happybirds.oa.admin.identity.dao.PopedomDao;
import org.happybirds.oa.admin.identity.dao.RoleDao;
import org.happybirds.oa.admin.identity.dao.UserDao;
import org.happybirds.oa.admin.identity.entity.Job;
import org.happybirds.oa.admin.identity.entity.User;
import org.happybirds.oa.admin.identity.service.IdentityService;
import org.happybirds.oa.core.action.VerifyAction;
import org.happybirds.oa.core.common.security.MD5;
import org.happybirds.oa.core.common.web.CookieTools;
import org.happybirds.oa.core.exception.OAException;

import com.opensymphony.xwork2.ActionContext;

/**
 * 权限模块业务处理接口实现类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2016-7-15 下午5:32:15
 * @version 1.0
 */
public class IdentityServiceImpl implements IdentityService {
	/**
	 * 注入dao接口实体
	 */
	@Resource
	private JobDao jobDao;
	@Resource
	private  DeptDao deptDao;
	@Resource
	private  ModuleDao moduleDao;
	@Resource
	private  PopedomDao popedomDao;
	@Resource
	private  RoleDao roleDao;
	@Resource
	private  UserDao userDao;

	@Override
	public List<Job> getJobs() {
		return jobDao.find("from Job");
	}
	/**
	 * 登录方法
	 * @param userId 用户名
	 * @param password 密码
	 * @param vcode 验证码
	 * @param key 是否记住用户 0: 不记住  1 : 记住
	 * @return Map集合
	 */
	public Map<String, Object> login(String userId, String password, String vcode,
			int key){
		try{
			/** 定义提示信息 */
			Map<String, Object> map = new HashMap<>();
			map.put("tip", "验证码不正确！");
			map.put("status", 1);
			/** 从session中获取验证码 */
			String code = (String)ServletActionContext.getRequest().getSession()
						.getAttribute(VerifyAction.VERIFY_CODE);
			/** 判断验证码是否正确 */
			if (code != null && code.equalsIgnoreCase(vcode)){
				/** 根据用户名与密码查询用户 */
				User user = getUser(userId, false);
				/** 判断用户是否存在，如果存在就存入Session */
				if (user != null && user.getPassWord().equals(MD5.getMD5(password))){
					ActionContext.getContext().getSession().put(AdminConstant.SESSION_USER, user);
					/** 判断是否要记住用户 */
					if (key == 1){
						/** 操作cookie */
						CookieTools.addCookie(AdminConstant.COOKIE_NAME, user.getUserId(), AdminConstant.MAX_AGE);
					}
					map.put("tip", "登录成功！");
					map.put("status", 0);
				}else{
					map.put("tip", "用户名或者密码不正确！");
					map.put("status", 2);
				}
			}
			/** 返回提示信息 */
			return map;
		}catch(Exception ex){
			throw new OAException("登录方法时出现了异常！", ex);
		}
	}
	/**
	 * 根据主键userId查询用户
	 * @param userId 用户Id
	 * @return 用户
	 */
	public User getUser(String userId, boolean isMD5){
		try{
			User user = null;
			if (isMD5){
				user = userDao.getUser(userId);
			}else{
				user = userDao.get(User.class, userId);
			}
			return user;
		}catch(Exception ex){
			throw new OAException("根据主键userId查询用户方法时出现了异常！", ex);
		}
	}
}
