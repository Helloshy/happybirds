package com.kapphk.system.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.base.persistence.BaseServiceImpl;
import com.kapphk.system.bean.SystemUser;
import com.kapphk.system.bean.SystemUserRole;
import com.kapphk.system.mapper.SystemUserMapper;
import com.kapphk.system.mapper.SystemUserRoleMapper;
import com.kapphk.system.service.SystemUserService;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.PasswordHelper;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;
import com.kapphk.web.utils.ValidateUtils;

/**
 * 后台系统用户业务层
 * @author zoneyu 16-5-30
 */
@Service
public class SystemUserServiceImpl extends BaseServiceImpl<SystemUser, Long> implements SystemUserService {

	@Autowired
	private SystemUserMapper mapper;
	@Autowired
	private SystemUserRoleMapper systemUserRoleMapper;
	
	@Override
	public void init() {
		super.setMapper(mapper);
	}
	
	
	@Override
	public SystemUser findByUsername(String username) {
		
		return mapper.findByUsername(username);
	}
	/**
	 * 查询
	 * @author zoneyu 196-5-30
	 */
	public Result searchList(SystemUser bean, int page, int rows,
			Result rs) throws Exception {
		int count = mapper.findByPageCount(bean) ;
		List<SystemUser> list = mapper.findByPage(bean, (page-1)*rows, rows) ;
		rs = DataUtils.mergeData(count, list, rs) ;
		return rs ;
	}

	/**
	 * 修改
	 * @author zoneyu 16-5-30
	 */
	public Result getData(Long id, Result rs) throws Exception {
		rs.put("info", mapper.findById(id)) ;
		return rs ;
	}

	/**
	 * 保存
	 * @author EXIA
	 */
	public Result saveData(SystemUser bean, Result rs,Long roleId) throws Exception {
		if(!StringUtils.isBlank(bean.getPwd()) && ValidateUtils.isBlank(bean.getId())){
			PasswordHelper passwordHelper = new PasswordHelper();
			passwordHelper.encryptPassword(bean);
		}else if(StringUtils.isBlank(bean.getPwd()) && ValidateUtils.isBlank(bean.getId())){
			rs.setError(MSG.CUS_ERROR);
			rs.setMsg("密码不能为空!");
			return rs;
		}

		SystemUserRole sur = new SystemUserRole();
		
		if(ValidateUtils.isBlank(bean.getId())){
			SystemUser user = mapper.findByUsername(bean.getUserName());
			if(user != null){
				rs.setError(MSG.CUS_ERROR);
				rs.setMsg("该账号已存在！");
				return rs;
			}
			bean.setCreateTime(new Date());
			mapper.insert(bean) ;
			sur.setRoleId(roleId);
			sur.setUserId(bean.getId());
			systemUserRoleMapper.insert(sur);
		}else{
			mapper.update(bean) ;
			sur.setRoleId(roleId);
			sur.setUserId(bean.getId());
			systemUserRoleMapper.update(sur);
		}
		return rs ;
	}

	/**
	 * 删除
	 * @author zoneyu 16-6-3
	 */
	public Result delete(String ids, Result rs) throws Exception {
		List<Long> idss = DataUtils.string2List(ids) ;
		mapper.deletes(idss) ;
		return rs ;
	}

	/**
	 * 后台页面登录
	 * @author zoneyu 16-6-3
	 */
	public Result sign(SystemUser bean, HttpServletRequest request,
			Result rs) throws Exception {
		SystemUser user = mapper.findEntityByCondition(bean) ;
		if(!ValidateUtils.isBlank(user)){
			HttpSession session = request.getSession() ;
			session.setAttribute("userId", user.getId());
			session.setAttribute("user", user);
		}else{
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("用户名或密码错误") ;
		}
		return rs ;
	}


	@Override
	public Map<String, Object> findUserAndUserRole(Long id) {
		Map<String ,Object> map = new HashMap<String, Object>();
		SystemUser sysUser = mapper.findById(id);
		SystemUserRole systemUserRole = systemUserRoleMapper.findById(id);
		map.put("sysUser", sysUser);
		map.put("systemUserRole", systemUserRole);
		return map;
	}


	@Override
	public Result editPwd(String pwd, String newPwd, String reNewPwd,Result result) {
		PasswordHelper passwordHelper = new PasswordHelper();
		SystemUser systemUser = (SystemUser) SecurityUtils.getSubject().getSession().getAttribute("systemUser");
		if(!systemUser.getPwd().equals(passwordHelper.encryptPassword(systemUser.getUserName(), pwd, systemUser.getSalt()))){
			result.setError(MSG.CUS_ERROR);
			result.setMsg("原密码不正确！");
			return result;
		}
		if(!newPwd.equals(reNewPwd)){
			result.setError(MSG.CUS_ERROR);
			result.setMsg("两次输入的密码不一致！");
			return result;
		}
		systemUser.setPwd(newPwd);
		passwordHelper.encryptPassword(systemUser);
		mapper.update(systemUser);
		return result;
	}


	public Result updateState(String ids, Result rs) throws Exception {
		List<Long> idss = DataUtils.string2List(ids) ;
		mapper.updateState(idss) ;
		return rs ;
	}



}
