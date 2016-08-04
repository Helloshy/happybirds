package org.happybirds.oa.admin.identity.dao;

import java.util.List;
import java.util.Map;

import org.happybirds.oa.admin.identity.entity.Module;
import org.happybirds.oa.core.common.web.PageModel;
import org.happybirds.oa.core.dao.HibernateDao;
/**
 * 模块数据访问接口
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2016-7-15 下午5:11:46
 * @version 1.0
 */
public interface ModuleDao extends HibernateDao {
	
	/**
	 * TODO   查询模块的code与name
	 * @return
	 * @Since 2016年7月28日
	 * @Version 1.0
	 */
	List<Map<String, Object>> getModuleCodeAndName();
	/**
	 * TODO   分页查询模块信息
	 * @param parentCode
	 * @param pageModel
	 * @param codeLen 
	 * @return
	 * @Since 2016年7月28日
	 * @Version 1.0
	 */
	List<Module> getModuleByPage(String parentCode, PageModel pageModel, int codeLen);
	/**
	 * TODO   根据父code查询子code
	 * @param parentCode 父code
	 * @return
	 * @Since 2016年7月29日
	 * @Version 1.0
	 */
	List<String> getModuleCodes(String parentCode,int codeLen);
	/**
	 * TODO   批量删除模块
	 * @param temp
	 * @Since 2016年7月29日
	 * @Version 1.0
	 */
	void deleteModule(String[] temp);
	/**
	 * TODO   根据codeLength查询模块
	 * @param codeLength 
	 * @return
	 * @Since 2016年7月30日
	 * @Version 1.0
	 */
	List<Map<String, Object>> getModuleCodeAndNameByCodeLength(int codeLength);
	/**
	 * TODO  查询12位code
	 * @param moduleCode
	 * @param codeLen 
	 * @return
	 * @Since 2016年7月30日
	 * @Version 1.0
	 */
	List<Module> getModulesBy12(String moduleCode, int codeLen);
	
}
