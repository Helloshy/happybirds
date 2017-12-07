package org.happybirds.oa.admin.identity.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.happybirds.oa.admin.identity.entity.Job;
import org.happybirds.oa.admin.identity.entity.Module;
import org.happybirds.oa.admin.identity.entity.Role;
import org.happybirds.oa.admin.identity.entity.User;
import org.happybirds.oa.core.common.web.PageModel;


/**
 * 权限模块业务处理接口
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2016-7-15 下午5:31:44
 * @version 1.0
 */
public interface IdentityService {
	int CODE_LEN = 4;
	/**
	 * 注意事务的传播特性，当进行一组操作事，操作单元中的一个操作具有事务性，则会传播到整个操作单元
	 * 
	 */
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
	/**
	 * TODO	     多条件分页查询用户	
	 * @param user 查询参数对象
	 * @param pageModel 分页实体对象
	 * @return List<User>
	 * @Since 2016年7月20日
	 * @Version 1.0
	 */
	List<User> getUserByPage(User user, PageModel pageModel);
	/**
	 * TODO 	查询所有的部门
	 * @return List<Dept>
	 * @Since 2016年7月20日
	 * @Version 1.0
	 */
	List<Map<String, Object>> loadDept();
	/**
	 * TODO 查询所有的部门和职位信息
	 * @return  String : 集合名，List对应的对象集合
	 * @Since 2016年7月21日
	 * @Version 1.0
	 */
	Map<String, List<Map<String, Object>>> loadDeptAndJobAjax();
	/**
	 *  TODO   查询userId是否已经存在
	 * @param userId 
	 * @return true : exist,false:not exist;
	 * @Since 2016年7月21日
	 * @Version 1.0
	 */
	boolean userIdIsExist(String userId);
	/**
	 * TODO   添加(用户id==null)或者更新用户(id!=null)
	 * @param user
	 * @param addUserOrUpdateFlag 
	 * @return true(执行操作成功)|false(执行操作失败)
	 * @Since 2016年7月23日
	 * @Version 1.0
	 */
	boolean addUserOrUpdate(User user, int addUserOrUpdateFlag);
	/**
	 * TODO  根据用户主键id 批量删除用户
	 * @param userIds
	 * @Since 2016年7月23日
	 * @Version 1.0
	 */
	void deleteUser(String userIds);
	/**
	 * TODO   批量审核用户状态
	 * @param userIds 
	 * @param status 
	 * @Since 2016年7月25日
	 * @Version 1.0
	 */
	void checkUserStatus(String userIds, Short status);
	/**
	 * TODO   分页查询角色对象
	 * @param clazz
	 * @param pageModel
	 * @return
	 * @Since 2016年7月25日
	 * @Version 1.0
	 */
	 List<Role> getRoleByPage(Class<Role> clazz, PageModel pageModel);
	/**
	 * TODO   添加角色
	 * @param role
	 * @Since 2016年7月25日
	 * @Version 1.0
	 */
	void addRole(Role role);
	/**
	 * TODO  更新角色
 	 * @param role
	 * @Since 2016年7月25日
	 * @Version 1.0
	 */
	void updateRole(Role role);
	/**
	 * TODO  根据角色id 查询对应角色
	 * @param id
	 * @return
	 * @Since 2016年7月25日
	 * @Version 1.0
	 */
	Role getRole(Long id);
	/**
	 * TODO   批量删除角色
	 * @param roleIds
	 * @Since 2016年7月25日
	 * @Version 1.0
	 */
	void deleteRole(String roleIds);
	
	/**
	 * TODO    异步加载操作树
	 * @return
	 * @Since 2016年7月27日
	 * @Version 1.0
	 */
	List<Map<String, Object>> loadModuleTree();
	/**
	 * TODO   分页查询模块信息
	 * @param parentCode
	 * @param pageModel
	 * @return
	 * @Since 2016年7月28日
	 * @Version 1.0
	 */
	List<Module> getModuleByPage(String parentCode, PageModel pageModel);
	/**
	 * TODO   添加模块
	 * @param module  封装模块参数
	 * @param parentCode  父模块的id
	 * @Since 2016年7月28日
	 * @Version 1.0
	 */
	void addModule(Module module, String parentCode);
	/**
	 * TODO   根据主键查询模块
	 * @param parentCode
	 * @return
	 * @Since 2016年7月29日
	 * @Version 1.0
	 */
	Module getModuleByCode(String parentCode);
	/**
	 * TODO   更新模块信息
	 * @param module
	 * @Since 2016年7月29日
	 * @Version 1.0
	 */
	void updateModule(Module module);
	/**
	 * TODO   批量删除模块
	 * @param codes
	 * @Since 2016年7月29日
	 * @Version 1.0
	 */
	void deleteModule(String codes);
	/**
	 * TODO   分页查询角色绑定的用户对象
	 * @param role
	 * @param pageModel 
	 * @return
	 * @Since 2016年7月29日
	 * @Version 1.0
	 */
	List<User> getBindUserByRoleId(Role role, PageModel pageModel);
	/**
	 * TODO  查询角色
	 * @param id
	 * @return
	 * @Since 2016年7月29日
	 * @Version 1.0
	 */
	Role getRoleById(Long id);
	/**
	 * TODO   分页查询角色未绑定的用户对象
	 * @param role
	 * @param pageModel
	 * @return
	 * @Since 2016年7月29日
	 * @Version 1.0
	 */
	List<User> getUnBindUserByRoleId(Role role, PageModel pageModel);
	/**
	 * TODO   角色绑定用户
	 * @param role
	 * @param userIds  用户id
	 * @Since 2016年7月29日
	 * @Version 1.0
	 */
	void bindUser(Role role, String userIds);
	/**
	 * TODO  解除角色绑定用户关系
	 * @return
	 * @Since 2016年7月29日
	 * @Version 1.0
	 */
	void unbindUser(Role role, String userIds);
	/**
	 * TODO   异步加载权限树
	 * @return
	 * @Since 2016年7月30日
	 * @Version 1.0
	 */
	List<Map<String, Object>> loadPopedomTree();
	/**
	 * TODO   查询12位的模块
	 * @param moduleCode 8位模块代号
	 * @return
	 * @Since 2016年7月30日
	 * @Version 1.0
	 */
	List<Module> getModules(String moduleCode);
	/**
	 * TODO   查询操作
	 * @param moduleCode
	 * @param id
	 * @return
	 * @Since 2016年7月30日
	 * @Version 1.0
	 */
	List<String> getOperasByModuleIdAndRoleId(String moduleCode, Long id);
	/**
	 * TODO   添加权限操作
	 * @param role
	 * @param moduleCode
	 * @param codes
	 * @Since 2016年7月30日
	 * @Version 1.0
	 */
	void addPopedom(Role role, String moduleCode, String codes);
	/**
	 * TODO    根据用户id查询所拥有的角色绑定的权限操作模块
	 * @return
	 * @Since 2016年7月31日
	 * @Version 1.0
	 */
	List<Map<String, Object>> loadMenuTree();
	/**
	 * TODO   根据用户id查询对应的操作权限
	 * @param userId 用户id
	 * @return [{popedomCode:"",popedomName:""},{popedomCode:"",popedomName:""}...]
	 * @Since 2016年8月1日
	 * @Version 1.0
	 */
	Set<String> getOperasCodeByUserId(String userId);
	/**
	 * 	TODO 根据用户id查询对应的操作权限
	 * @param userId
	 * @return {popedomCode,url}
	 * @Since 2016年8月1日
	 * @Version 1.0
	 */
	Map<String,String> getAllUserPopedoms(String userId);


}
