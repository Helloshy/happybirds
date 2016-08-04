package org.happybirds.oa.admin.identity.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.happybirds.oa.admin.identity.dao.ModuleDao;
import org.happybirds.oa.admin.identity.entity.Module;
import org.happybirds.oa.core.common.web.PageModel;
import org.happybirds.oa.core.dao.impl.HibernateDaoImpl;
import org.happybirds.oa.core.exception.OAException;
import org.springframework.util.StringUtils;
/**
 * 模块数据访问接口实现类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2016-7-15 下午5:12:33
 * @version 1.0
 */
public class ModuleDaoImpl extends HibernateDaoImpl implements ModuleDao {
	
	/**
	 * TODO   查询模块的code与name
	 * @return
	 * @Since 2016年7月28日
	 * @Version 1.0
	 */
	public List<Map<String, Object>> getModuleCodeAndName(){
		
		try {
			String hql="select new map (code as id,name as name) from Module order by code asc ";
			return this.find(hql);
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("查询模块code与name出现异常");
		}
	}
	/**
	 * TODO   根据codeLength查询模块
	 * @return
	 * @Since 2016年7月30日
	 * @Version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getModuleCodeAndNameByCodeLength(int codeLength){
		try {
			Object [] params ={codeLength};
			String hql="select new map (code as id,name as name) from Module where length(code)<=? order by code asc ";
			return (List<Map<String, Object>>) this.find(hql, params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("查询模块code与name出现异常");
		}
	}
	/**
	 * TODO   分页查询模块信息
	 * @param parentCode
	 * @param pageModel
	 * @return
	 * @Since 2016年7月28日
	 * @Version 1.0
	 */
	public List<Module> getModuleByPage(String parentCode, PageModel pageModel,int codeLen){
		try {
			//定义集合来添加参数
			List<Object> params = new ArrayList<>();
			//判断code是否为空
			 parentCode = StringUtils.isEmpty(parentCode)?"":parentCode;
			String hql="select m from Module m where length(code)=?";
			params.add(codeLen+parentCode.length());
			//不为空时增加限定条件
			if(!StringUtils.isEmpty(parentCode)){
				hql+="and code like ?";
				params.add(parentCode+"%");
			}
			return this.findByPage(hql, pageModel, params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("分页查询模块出现异常");
		}
	}
	/**
	 * TODO   根据父code查询子code
	 * @param parentCode 父code
	 * @return
	 * @Since 2016年7月29日
	 * @Version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List<String> getModuleCodes(String parentCode,int codeLen){
		try {
			//定义集合来添加参数
			List<Object> params = new ArrayList<>();
			//判断code是否为空
			 parentCode = StringUtils.isEmpty(parentCode)?"":parentCode;
			String hql="select m.code from Module m where length(code)=?";
			params.add(codeLen+parentCode.length());
			//不为空时增加限定条件
			if(!StringUtils.isEmpty(parentCode)){
				hql+="and code like ?";
				params.add(parentCode+"%");
			}
			return (List<String>) this.find(hql, params.toArray());
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("分页查询模块出现异常");
		}
	}
	public void deleteModule(String[] temp){
		try {
			
			//delete from Module where code like "code%";
			if(temp!=null&&temp.length>0){
				//循环删除
				for (int i = 0; i < temp.length; i++) {
					//定义集合来添加参数
					List<Object> params = new ArrayList<>();
					String hql="delete from Module where code like ?";
					params.add(temp[i]+"%");
					this.bulkUpdate(hql, params.toArray());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("删除模块出现异常");
		}
	}
	/**
	 * TODO  查询12位code
	 * @param moduleCode
	 * @param codeLen 
	 * @return
	 * @Since 2016年7月30日
	 * @Version 1.0
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Module> getModulesBy12(String moduleCode, int codeLen){
		try {
			String hql="from Module where length(code)=? and code like?";
			List<Object> params = new ArrayList<>();
			params.add(moduleCode.length()+codeLen);
			params.add(moduleCode+"%");
			return (List<Module>) this.find(hql, params.toArray());
		} 
		catch (Exception e) {
				e.printStackTrace();
				throw new OAException("查询12位模块出现异常");
			}
		}
	
}
