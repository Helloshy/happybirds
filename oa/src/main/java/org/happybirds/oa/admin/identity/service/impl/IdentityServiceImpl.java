package org.happybirds.oa.admin.identity.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
import org.happybirds.oa.admin.identity.entity.Module;
import org.happybirds.oa.admin.identity.entity.Popedom;
import org.happybirds.oa.admin.identity.entity.Role;
import org.happybirds.oa.admin.identity.entity.User;
import org.happybirds.oa.admin.identity.service.IdentityService;
import org.happybirds.oa.core.action.VerifyAction;
import org.happybirds.oa.core.common.security.MD5;
import org.happybirds.oa.core.common.web.CookieTools;
import org.happybirds.oa.core.common.web.PageModel;
import org.happybirds.oa.core.dao.GeneratorDao;
import org.happybirds.oa.core.exception.OAException;
import org.springframework.util.StringUtils;

import com.opensymphony.xwork2.ActionContext;

/**
 * 权限模块业务处理接口实现类
 * 
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
	private DeptDao deptDao;
	@Resource
	private ModuleDao moduleDao;
	@Resource
	private PopedomDao popedomDao;
	@Resource
	private RoleDao roleDao;
	@Resource
	private UserDao userDao;
	@Resource
	private GeneratorDao generatorDao;

	@Override
	public List<Job> getJobs() {
		return jobDao.find("from Job");
	}

	/**
	 * 登录方法
	 * 
	 * @param userId
	 *            用户名
	 * @param password
	 *            密码
	 * @param vcode
	 *            验证码
	 * @param key
	 *            是否记住用户 0: 不记住 1 : 记住
	 * @return Map集合
	 */
	public Map<String, Object> login(String userId, String password,
			String vcode, int key) {
		try {
			/** 定义提示信息 */
			Map<String, Object> map = new HashMap<>();
			map.put("tip", "验证码不正确！");
			map.put("status", 1);
			/** 从session中获取验证码 */
			String code = (String) ServletActionContext.getRequest()
					.getSession().getAttribute(VerifyAction.VERIFY_CODE);
			/** 判断验证码是否正确 */
			if (code != null && code.equalsIgnoreCase(vcode)) {
				/** 根据用户名与密码查询用户 */
				User user = getUser(userId, false);
				/** 判断用户是否存在，如果存在就存入Session */
				if (user != null
						&& user.getPassWord().equals(MD5.getMD5(password))) {
					/** 判断用户status属性是否为1 */
					if (user.getStatus() == 1) {
						ActionContext.getContext().getSession()
								.put(AdminConstant.SESSION_USER, user);
						/**查询用户权限集合并放入session中**/
						Map<String,String> popedoms = this.getAllUserPopedoms(user.getUserId());
						ActionContext.getContext().getSession().put(AdminConstant.USER_ALL_POPEDOMS, popedoms);
						/** 判断是否要记住用户 */
						if (key == 1) {
							/** 操作cookie */
							CookieTools.addCookie(AdminConstant.COOKIE_NAME,
									user.getUserId(), AdminConstant.MAX_AGE);
						}
						map.put("tip", "登录成功！");
						map.put("status", 0);
					} else {
						/** 状态 0新建,1审核,2不通过,3冻结 */
						String[] tipArrs = { "新建", "审核", "不通过", "冻结" };
						map.put("tip", "您的账号处于【" + tipArrs[user.getStatus()]
								+ "】状态，请联系系统管理员！");
						map.put("status", 3);
					}
				} else {
					map.put("tip", "用户名或者密码不正确！");
					map.put("status", 2);
				}
			}
			/** 返回提示信息 */
			return map;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new OAException("登录方法时出现了异常！", ex);
		}
	}

	/**
	 * 根据主键userId查询用户
	 * 
	 * @param userId
	 *            用户Id
	 * @return 用户
	 */
	public User getUser(String userId, boolean isMD5) {
		try {
			User user = null;
			if (isMD5) {
				user = userDao.getUser(userId);
			} else {
				user = userDao.get(User.class, userId);

			}
			/** 查询部门与职位 */
			if (user != null) {
				if (user.getDept() != null)
					user.getDept().getId();
				if (user.getJob() != null)
					user.getJob().getCode();
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("根据主键userId查询用户方法时出现了异常！", e);
		}
	}

	/**
	 * 
	 * TODO 多条件分页查询用户
	 * 
	 * @param user
	 *            查询条件bean对象
	 * @param pageModel
	 *            分页实体对象
	 * @return List<User>
	 */
	@Override
	public List<User> getUserByPage(User user, PageModel pageModel) {
		/** 调用userDao查询 */
		try {
			List<User> users = userDao.getUserByPage(user, pageModel);
			if (users != null) {
				for (User u : users) {
					if (u.getDept() != null)
						u.getDept().getName();
					if (u.getJob() != null)
						u.getJob().getName();
					if (u.getCreater() != null)
						u.getCreater().getName();
					if (u.getChecker() != null)
						u.getChecker().getName();
				}
			}
			return users;
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("多条件分页查询用户出现异常");
		}
	}

	/**
	 * 
	 * TODO 查询所有的部门
	 * 
	 * @return
	 * @Since 2016年7月20日
	 * @Version 1.0
	 */
	@Override
	public List<Map<String, Object>> loadDept() {
		try {
			return deptDao.getDeptByIdAndName();
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("load dept failed");
		}

	}

	/**
	 * TODO 查询所有的部门和职位信息
	 * 
	 * @return String : 集合名，List对应的对象集合
	 * @Since 2016年7月21日
	 * @Version 1.0
	 */
	@Override
	public Map<String, List<Map<String, Object>>> loadDeptAndJobAjax() {
		try {
			List<Map<String, Object>> depts = deptDao.getDeptByIdAndName();
			List<Map<String, Object>> jobs = jobDao.getJobByCodeAndName();
			Map<String, List<Map<String, Object>>> responseMaps = new HashMap<>();
			responseMaps.put("depts", depts);
			responseMaps.put("jobs", jobs);
			return responseMaps;

		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("load deptAndjob falied!");
		}
	}

	/**
	 * 
	 * TODO 查询用户userId是否存在
	 * 
	 * @param userId
	 * @return true: not exist|false:exist
	 * @Since 2016年7月21日
	 * @Version 1.0
	 */
	@Override
	public boolean userIdIsExist(String userId) {
		return getUser(userId, false) == null;

	}

	/**
	 * TODO 添加(用户id==null)或者更新用户(id!=null)
	 * 
	 * @param user
	 * @return true(执行操作成功)|false(执行操作失败)
	 * @Since 2016年7月23日
	 * @Version 1.0
	 */
	@Override
	public boolean addUserOrUpdate(User user, int addUserOrUpdateFlag) {
		boolean flag = false;
		try {
			/** 执行添加操作 */
			if (addUserOrUpdateFlag == AdminConstant.addUserFlag) {
				/** 设置属性 */
				user.setPassWord(MD5.getMD5(user.getPassWord()));// 密码加密
				user.setCreateDate(new Date());// 添加时间
				user.setCreater(AdminConstant.getUserInSession());// 设置执行添加操作的用户
				userDao.saveOrUpdate(user);
				flag = true;
			} else {
				/** 执行更新操作 */
				/** 从一级持久态中拿出对象并设置属性 */
				User u = getUser(user.getUserId(), false);
				u.setAnswer(user.getAnswer());
				u.setDept(user.getDept());
				u.setEmail(user.getEmail());
				u.setJob(user.getJob());
				u.setModifier(AdminConstant.getUserInSession());
				u.setModifyDate(new Date());
				u.setName(user.getName());
				u.setPassWord(MD5.getMD5(user.getPassWord()));
				u.setPhone(user.getPhone());
				u.setQqNum(user.getQqNum());
				u.setQuestion(user.getQuestion());
				u.setTel(user.getTel());
				flag = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (user.getUserId() == null)
				throw new OAException("添加用户操作失败");
			else {
				throw new OAException("更新用户操作失败");
			}
		}
		return flag;
	}

	/**
	 * 
	 * TODO 批量删除用户
	 * 
	 * @param userIds
	 * @Since 2016年7月25日
	 * @Version 1.0
	 */
	@Override
	public void deleteUser(String userIds) {
		try {
			String[] ids = userIds.split(",");
			userDao.deleteUser(ids);
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("删除用户发生异常");
		}

	}

	/**
	 * TODO 批量审核用户状态
	 * 
	 * @param userIds
	 * @param status
	 * @Since 2016年7月25日
	 * @Version 1.0
	 */
	public void checkUserStatus(String userIds, Short status) {
		try {
			String[] ids = userIds.split(",");
			// 遍历设置用户属性
			for (int i = 0; i < ids.length; i++) {
				User user = this.getUser(ids[i], false);
				if (user != null) {
					user.setStatus(status);
					user.setCheckDate(new Date());
					user.setChecker(AdminConstant.getUserInSession());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("审核用户发生异常");
		}
	}

	/**
	 * TODO 分页查询对象
	 * 
	 * @param clazz
	 * @param pageModel
	 * @return
	 * @Since 2016年7月25日
	 * @Version 1.0
	 */
	@Override
	public List<Role> getRoleByPage(Class<Role> clazz, PageModel pageModel) {
		List<Role> roles = null;
		try {
			roles = roleDao.getRoleByPage(pageModel);
			if (roles != null) {
				for (Role role : roles) {
					if (role != null && role.getCreater() != null)
						role.getCreater().getName();
					if (role != null && role.getModifier() != null)
						role.getModifier().getName();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("分页查询对象出现异常");
		}
		return roles;
	}

	/**
	 * TODO 添加角色
	 * 
	 * @param role
	 * @Since 2016年7月25日
	 * @Version 1.0
	 */
	@Override
	public void addRole(Role role) {
		try {
			role.setCreateDate(new Date());
			role.setCreater(AdminConstant.getUserInSession());
			roleDao.save(role);
		} catch (Exception e) {
			throw new OAException("添加角色对象出现异常");
		}

	}

	/**
	 * TODO 更新角色
	 * 
	 * @param role
	 * @Since 2016年7月25日
	 * @Version 1.0
	 */

	@Override
	public void updateRole(Role role) {
		try {
			Role r = this.getRole(role.getId());
			r.setName(role.getName());
			r.setRemark(role.getRemark());
			r.setModifier(AdminConstant.getUserInSession());
			r.setModifyDate(new Date());
		} catch (Exception e) {
			throw new OAException("更新角色对象出现异常");
		}
	}

	/**
	 * TODO 根据角色id 查询对应角色
	 * 
	 * @param id
	 * @return
	 * @Since 2016年7月25日
	 * @Version 1.0
	 */

	@Override
	public Role getRole(Long id) {
		try {
			Role role = roleDao.getRoleById(id);
			if (role != null)
				role.getUsers();
			return role;
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("get role falied");
		}

	}

	/**
	 * TODO 批量删除角色
	 * 
	 * @param roleIds
	 * @Since 2016年7月25日
	 * @Version 1.0
	 */
	@Override
	public void deleteRole(String roleIds) {
		try {
			String[] ids = roleIds.split(",");
			Long[] tempIds = new Long[ids.length];
			for (int i = 0; i < ids.length; i++) {
				tempIds[i] = Long.valueOf(ids[i]);
			}
			roleDao.delete(tempIds);
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("删除角色发生异常");
		}
	}

	/**
	 * TODO 异步加载操作树
	 * 
	 * @return
	 * @Since 2016年7月27日
	 * @Version 1.0
	 */
	@Override
	public List<Map<String, Object>> loadModuleTree() {
		try {
			// 通过当前用户的id查到相应的角色，在查询对应的角色中具有哪些权限，在从对应的权限中查询对应的资源（可以操作的模块或者能够进行模块的哪些操作）
			/*
			 * String userId = AdminConstant.getUserInSession().getUserId();
			 * String
			 * hql="select m from Module m from Popedom p where role.id in";
			 * hql+="(select id from Role )";
			 */
			// data:[{id:'',pid:'',name:''},{id:'',pid:'',name:''}...]
			// 查询到所有的id 根据id 生成pid
			List<Map<String, Object>> lists = moduleDao.getModuleCodeAndName();
			for (Map<String, Object> map : lists) {
				String id = map.get("id").toString();
				String pid = id.length() == CODE_LEN ? "0" : id.substring(0,
						id.length() - CODE_LEN);
				String name = map.get("name").toString();
				map.put("id", id);
				map.put("pid", pid);
				map.put("name", name);
			}
			return lists;
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("加载操作树发生异常");
		}
	}

	/**
	 * TODO 分页查询模块信息
	 * 
	 * @param parentCode
	 * @param pageModel
	 * @return
	 * @Since 2016年7月28日
	 * @Version 1.0
	 */
	public List<Module> getModuleByPage(String parentCode, PageModel pageModel) {
		try {
			List<Module> modules = moduleDao.getModuleByPage(parentCode,
					pageModel, CODE_LEN);
			// 加载关联表信息
			if (modules != null) {
				for (Module module : modules) {
					if (module != null && module.getCreater() != null)
						module.getCreater().getName();
					if (module != null && module.getModifier() != null)
						module.getModifier().getName();
				}
			}
			return modules;
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("查询模块信息发生异常");
		}
	}

	/**
	 * TODO 添加模块
	 * 
	 * @param module
	 *            封装模块参数
	 * @param parentCode
	 *            父模块的id
	 * @Since 2016年7月28日
	 * @Version 1.0
	 */
	public void addModule(Module module, String parentCode) {
		try {
			// 设置页面没有封装的属性
			// //查询到所有的子code
			// List<String> codes =
			// moduleDao.getModuleCodes(parentCode,CODE_LEN);
			// int [] temp =new int [codes.size()];
			// if(codes!=null){
			// //定义一个数组
			// for (int i = 0; i < codes.size(); i++) {
			// //parseInt()方法会将前面的0全部去掉 ，而我们需要原样输出,怎么样做到让"0001">>0001呢？
			// temp[i]=Integer.parseInt(codes.get(i));
			// }
			// }
			// Arrays.sort(temp);
			// String code = Integer.toString((temp[temp.length-1]+1));
			String code = generatorDao.generatorField(Module.class, "code",
					parentCode, CODE_LEN);
			module.setCode(code);
			module.setCreateDate(new Date());
			module.setCreater(AdminConstant.getUserInSession());
			moduleDao.save(module);
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("添加模块发生异常");
		}
	}

	public Module getModuleByCode(String parentCode) {
		try {
			return moduleDao.get(Module.class, parentCode);
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("查询模块信息发生异常");
		}
	}

	public void updateModule(Module module) {
		try {
			if (module != null) {
				Module m = this.getModuleByCode(module.getCode());
				m.setName(module.getName());
				m.setModifyDate(new Date());
				m.setModifier(AdminConstant.getUserInSession());
				m.setRemark(module.getRemark());
				m.setUrl(module.getUrl());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("更新模块信息发生异常");
		}
	}

	public void deleteModule(String codes) {
		try {
			String[] temp = codes.split(",");
			moduleDao.deleteModule(temp);
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("删除模块发生异常");
		}
	}

	public List<User> getBindUserByRoleId(Role role, PageModel pageModel) {
		try {
			if (role != null && role.getId() != null) {
				List<User> users = roleDao.getBindUserByRoleId(role.getId(),
						pageModel);
				// 加载延迟信息
				if (users != null) {
					for (User u : users) {
						if (u.getDept() != null)
							u.getDept().getName();
						if (u.getJob() != null)
							u.getJob().getName();
						if (u.getCreater() != null)
							u.getCreater().getName();
						if (u.getChecker() != null)
							u.getChecker().getName();
					}
				}
				return users;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("分页查询角色绑定用户出现异常");
		}
		return null;
	}

	public Role getRoleById(Long id) {
		try {
			return roleDao.getRoleById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("查询角色出现异常");
		}
	}

	/**
	 * TODO 分页查询角色未绑定的用户对象
	 * 
	 * @param role
	 * @param pageModel
	 * @return
	 * @Since 2016年7月29日
	 * @Version 1.0
	 */
	public List<User> getUnBindUserByRoleId(Role role, PageModel pageModel) {
		try {
			if (role != null && role.getId() != null) {
				List<User> users = roleDao.getUnBindUserByRoleId(role.getId(),
						pageModel);
				// 加载延迟信息
				if (users != null) {
					for (User u : users) {
						if (u.getDept() != null)
							u.getDept().getName();
						if (u.getJob() != null)
							u.getJob().getName();
						if (u.getCreater() != null)
							u.getCreater().getName();
						if (u.getChecker() != null)
							u.getChecker().getName();
					}
				}
				return users;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("分页查询角色未绑定用户出现异常");
		}
		return null;

	}

	/**
	 * TODO 角色绑定用户
	 * 
	 * @param role
	 * @param userIds
	 *            用户id
	 * @Since 2016年7月29日
	 * @Version 1.0
	 */
	public void bindUser(Role role, String userIds) {
		try {
			// 查询角色
			Role r = roleDao.getRoleById(role.getId());
			// 查询角色对应的user集合 向集合中添加元素
			Set<User> users = r.getUsers();
			String[] ids = userIds.split(",");
			for (int i = 0; i < ids.length; i++) {
				User user = this.getUser(ids[i], false);// 这里要使用未加密的方法，小问题找了半小时！！！
				users.add(user);
			}
			r.setUsers(users);
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("角色绑定用户出现异常");
		}
	}

	/**
	 * TODO 解除角色绑定用户关系
	 * 
	 * @return
	 * @Since 2016年7月29日
	 * @Version 1.0
	 */
	public void unbindUser(Role role, String userIds) {
		try {
			// 查询角色
			Role r = roleDao.getRoleById(role.getId());
			// 查询角色对应的user集合 向集合中添加元素
			Set<User> users = r.getUsers();
			String[] ids = userIds.split(",");
			for (int i = 0; i < ids.length; i++) {
				User user = this.getUser(ids[i], false);// 这里要使用未加密的方法，小问题找了半小时！！！
				users.remove(user);
			}
			r.setUsers(users);
			// 第二种方式
			/*
			 * Set<User> users = r.getUsers(); String[] ids =
			 * userIds.split(","); Iterator<User> iterator = users.iterator();
			 * for (int j = 0; j < ids.length; j++) { while(iterator.hasNext()){
			 * User u = iterator.next(); if(u.getUserId().equals(ids[j])){
			 * iterator.remove(); break; } } }
			 */
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("解除角色绑定用户出现异常");
		}
	}

	/**
	 * TODO 异步加载权限树
	 * 
	 * @return
	 * @Since 2016年7月30日
	 * @Version 1.0
	 */
	public List<Map<String, Object>> loadPopedomTree(){
		try {
			// data:[{id:'',pid:'',name:''},{id:'',pid:'',name:''}...]
			// 查询到所有的id  根据id 生成pid
			List<Map<String, Object>>   lists  = moduleDao.getModuleCodeAndNameByCodeLength(CODE_LEN*2);
			for (Map<String, Object> map : lists) {
				String id = map.get("id").toString();
				String pid = id.length()==CODE_LEN?"0":id.substring(0, id.length()-CODE_LEN);
				String name = map.get("name").toString();
				map.put("id", id);
				map.put("pid", pid);
				map.put("name", name.replaceAll("-", ""));
			}
			return lists;
		}
		 catch (Exception e) {
			e.printStackTrace();
			throw new OAException("加载权限树发生异常");
		}
	}	
	
		/**
		 * TODO   查询12位的模块
		 * @param moduleCode 8位模块代号
		 * @return
		 * @Since 2016年7月30日
		 * @Version 1.0
		 */
	@Override
	public List<Module> getModules(String moduleCode) {
		try {
			return moduleDao.getModulesBy12(moduleCode,CODE_LEN);
		}
		 catch (Exception e) {
			e.printStackTrace();
			throw new OAException("查询模块信息发生异常");
		}
	}
	/**
	 * TODO   查询操作
	 * @param moduleCode
	 * @param id
	 * @return
	 * @Since 2016年7月30日
	 * @Version 1.0
	 */
	public List<String> getOperasByModuleIdAndRoleId(String moduleCode, Long id){
		try {
			return popedomDao.getOperasByModuleIdAndRoleId(moduleCode,id);
		}
		 catch (Exception e) {
			e.printStackTrace();
			throw new OAException("查询操作发生异常");
		}
	}
	/**
	 * TODO   添加权限操作
	 * @param role
	 * @param moduleCode
	 * @param codes
	 * @Since 2016年7月30日
	 * @Version 1.0
	 */
	public void addPopedom(Role role, String moduleCode, String codes){
		try {
			//首先删除所有权限
			popedomDao.deletePopedomByRoleIdAndModuleCode(role.getId(),moduleCode);
			System.out.println("moduleCode: "+ moduleCode);
			System.out.println("codes: "+ codes);
			if(!StringUtils.isEmpty(codes)&&!StringUtils.isEmpty(moduleCode)){
				Module module = new Module();
				module.setCode(moduleCode);
				String [] ids = codes.split(",");
				for (String code : ids) {
					Module m = new Module();
					m.setCode(code);
					Popedom popedom = new Popedom();
					popedom.setCreateDate(new Date());
					popedom.setCreater(AdminConstant.getUserInSession());
					popedom.setModule(module);
					popedom.setOpera(m);
					popedom.setRole(role);
					popedomDao.saveOrUpdate(popedom);
				}
			}
		}
		 catch (Exception e) {
			e.printStackTrace();
			throw new OAException("添加权限发生异常");
		}
	}
	/**
	 * TODO    根据用户id查询所拥有的角色绑定的权限操作模块
	 * @return
	 * @Since 2016年7月31日
	 * @Version 1.0
	 */
	public List<Map<String, Object>> loadMenuTree(){
		try {
			//查询到能够操作的8位code
			 List<String> lists = popedomDao.getModuleAndOperaByUserId();
			 //生成4位code
			 List<Map<String, Object>> result = new ArrayList<>();
			 String parentCode=null;
			 for(String moduleCode:lists){
				 Map<String, Object> map = new HashMap<>();
				 //从目的出发  id 4位 pid 0 id 8 位 pid 4位
				 //module 00010001>>id:0001 ||
				 Module module = moduleDao.get(Module.class, moduleCode);
				 //00010002 00010003>>0001
				 map.put("id", moduleCode);
				 String id=moduleCode;
				 String pid=id.substring(0,id.length()-CODE_LEN);
				 map.put("pid", pid);
				 map.put("name", module.getName().replaceAll("-", ""));
				 map.put("url", module.getUrl());
				 result.add(map);
				 //当且仅当parentCode为空，pid 不相同时 才会不跳出循环
				 if(parentCode!=null&&parentCode.startsWith(pid)){
					 continue;
				 }
				 map = new HashMap<>();
				 parentCode = pid;
				 module =  moduleDao.get(Module.class, parentCode);
				 map.put("id", parentCode);
				 map.put("pid", "0");
				 map.put("name", module.getName().replaceAll("-", ""));
				 map.put("url", module.getUrl());
				 result.add(map);
			 }
			return result;
		}
		 catch (Exception e) {
			e.printStackTrace();
			throw new OAException("查询用户操作菜单树发生异常");
		}
	}
	/**
	 * TODO   根据用户id查询对应的操作权限
	 * @param userId 用户id
	 * @return 操作模块主键集合
	 * @Since 2016年8月1日
	 * @Version 1.0
	 */
	public Set<String> getOperasCodeByUserId(String userId){
		try {
			List<String> temp =  popedomDao.getOperasCodeByUserId(userId);
			Set<String> operas = new TreeSet<>();
			operas.addAll(temp);
			//增加8位code
			for (String opera : temp) {
				String parentCode =opera.substring(0,opera.length()-CODE_LEN);
				operas.add(parentCode);//相同的code不会添加
			}
			return operas;
		}
		 catch (Exception e) {
			e.printStackTrace();
			throw new OAException("查询用户权限资源出现异常");
		}
	}
	public Map<String,String> getAllUserPopedoms(String userId){
		try {
			Set<String> operas = this.getOperasCodeByUserId(userId);
			Map<String,String> popedoms = new HashMap<>();
			for (String opera : operas) {
				Module module = moduleDao.get(Module.class, opera);
				String url = module.getUrl();
				popedoms.put(opera, url);
			}
			return popedoms;
		}
		 catch (Exception e) {
			e.printStackTrace();
			throw new OAException("查询用户权限资源出现异常");
		}
	}
	
}
