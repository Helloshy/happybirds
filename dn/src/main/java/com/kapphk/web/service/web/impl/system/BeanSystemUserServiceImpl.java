package com.kapphk.web.service.web.impl.system;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.web.bean.system.BeanLog;
import com.kapphk.web.bean.system.BeanRole;
import com.kapphk.web.bean.system.BeanSystemUser;
import com.kapphk.web.bean.system.BeanSystemUserRole;
import com.kapphk.web.dao.mapper.system.BeanLogMapper;
import com.kapphk.web.dao.mapper.system.BeanRoleMapper;
import com.kapphk.web.dao.mapper.system.BeanSystemUserMapper;
import com.kapphk.web.dao.mapper.system.BeanSystemUserRoleMapper;
import com.kapphk.web.service.web.imethod.system.BeanSystemUserService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.MD5;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;
import com.kapphk.web.utils.getIP;

/**
 * 后台系统用户业务层
 * @author dengwen 
 * 2016-9-14下午1:57:15
 */
@Service
public class BeanSystemUserServiceImpl implements BeanSystemUserService {

	@Autowired
	private BeanSystemUserMapper mapper;

	@Autowired
	private BeanSystemUserRoleMapper userRoleMapper;

	@Autowired
	private BeanLogMapper logMapper;
	
	@Autowired
	private BeanRoleMapper roleMapper;
	
	/**
	 * 查询
	 */
	public Result searchList(BeanSystemUser bean, GridReq grid, Result rs) throws Exception {
		int count = mapper.count();
		List<BeanSystemUser> list = mapper.findByPage(bean, grid.getPage(), grid.getRows());
		rs = DataUtils.mergeData(count, list, rs);
		return rs;
	}

	/**
	 * 保存
	 */
	@Override
	public Result saveData(BeanSystemUser bean, Long roleId, Result rs) throws Exception {
		
		BeanSystemUser user = new BeanSystemUser();
		user.setUserName(bean.getUserName());
		BeanSystemUser systemUser = mapper.findEntityByCondition(user);
		BeanRole role = roleMapper.findById(roleId);
		bean.setName(role.getRoleName());
		//新增
		if(ValidateUtils.isBlank(bean.getId())){
			if(ValidateUtils.isBlank(systemUser)){
				bean.setPassword(bean.getPwd());
				bean.setPwd(MD5.getMD5(bean.getPwd()));
				bean.setCreateTime(new Date());
				mapper.insert(bean);
			}else{
				rs.setStatus(Contents.ERROR);
				rs.setMsg("该账号已存在，请更改");
			}
		}else{//修改
			if(!ValidateUtils.isBlank(systemUser) && systemUser.getId() - bean.getId() != 0){
				rs.setStatus(Contents.ERROR);
				rs.setMsg("该账号已存在，请更改");
			}else{
				if(!ValidateUtils.isBlank(bean.getPassword()) && !ValidateUtils.isBlank(bean.getPwd()) && !systemUser.getPwd().equals(MD5.getMD5(bean.getPassword()))){
					rs.setStatus(Contents.ERROR);
					rs.setMsg("原密码有误");
				}else{
					bean.setPwd(ValidateUtils.isBlank(bean.getPwd()) ? systemUser.getPwd() : MD5.getMD5(bean.getPwd()));
					mapper.update(bean);
				}
			}
		}
		
		if(rs.getStatus() == 10001){
			//先删除在保存角色
			BeanSystemUserRole userRole = new BeanSystemUserRole();
			userRole.setUserId(bean.getId());
			userRoleMapper.deleteByEntity(userRole);
			userRole.setRoleId(roleId);
			userRoleMapper.insert(userRole);
		}
		return rs;
	}

	/**
	 * 删除
	 */
	@Override
	public Result delete(List<Long> ids, Result rs) throws Exception {
		if(!ValidateUtils.isEmptyForCollection(ids)){
			rs.put("count", mapper.deletes(ids));
		}
		return rs;
	}

	/**
	 * 详情
	 */
	@Override
	public Result getData(Long id, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(id)){
			rs.put("data", mapper.findDetail(id));
		}else{
			rs.setStatus(Contents.ERROR);
			rs.setMsg("参数有误");
		}
		return rs;
	}

	/**
	 * 登录
	 */
	@Override
	public Result sign(BeanSystemUser bean, HttpServletRequest request,
			Result rs) throws Exception {
		if(!ValidateUtils.isBlank(bean.getUserName()) && !ValidateUtils.isBlank(bean.getPwd())){
			BeanSystemUser user = mapper.findEntityByCondition(bean);
			if(!ValidateUtils.isBlank(user)){
				if(user.getState() - 1 == 0){
					HttpSession session = request.getSession() ;
					session.setAttribute("userId", user.getId());
					session.setAttribute("user", user);
					BeanSystemUserRole userRole = new BeanSystemUserRole();
					userRole.setUserId(user.getId());
					List<BeanSystemUserRole> list = userRoleMapper.findAll(userRole);
					if(!ValidateUtils.isEmptyForCollection(list)){
						session.setAttribute("role_value", roleMapper.findById(list.get(0).getRoleId()).getRoleValue());
					}
					BeanLog log = new BeanLog();
					log.setIpAddress(getIP.getIpAddr(request));
					log.setCreateTime(new Date());
					log.setSid(user.getId());
					log.setOperationType("登录");
					log.setEventName("登录系统");
					log.setUserName(user.getUserName());
					logMapper.insert(log);
				}else{
					rs.setStatus(Contents.ERROR);
					rs.setMsg("该账号已无效");
				}
			}else{
				rs.setStatus(Contents.ERROR);
				rs.setMsg("用户名或密码有误");
			}
		}else{
			rs.setStatus(Contents.ERROR);
			rs.setMsg("用户名或密码不能为空");
		}
		return rs;
	}

	/**
	 * 修改密码
	 */
	@Override
	public Result changePwd(HttpServletRequest request, String opwd,
			String npwd, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(opwd) && !ValidateUtils.isBlank(npwd)){
			Long id = (Long) request.getSession().getAttribute("userId");
			BeanSystemUser user = mapper.findById(id);
			if(user.getPwd().equals(MD5.getMD5(opwd))){
				user.setPwd(MD5.getMD5(npwd));
				mapper.update(user);
				request.getSession().setAttribute("user", user);
			}else{
				rs.setStatus(Contents.ERROR);
				rs.setMsg("原密码有误");
			}
		}else{
			rs.setStatus(Contents.ERROR);
			rs.setMsg("参数有误");
		}
		return rs;
	}

}
