package com.kapphk.web.controller.web.system;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kapphk.web.bean.system.BeanLog;
import com.kapphk.web.bean.system.BeanSystemUser;
import com.kapphk.web.dao.mapper.system.BeanLogMapper;
import com.kapphk.web.service.web.imethod.system.BeanSystemUserService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;
import com.kapphk.web.utils.getIP;

/**
 * 后台系统用户控制层
 * @author dengwen 
 * 2016-9-14下午1:55:10
 */
@RestController
@RequestMapping("/web/system/systemuser/")
public class BeanSystemUserController{
	
	@Autowired
	private BeanSystemUserService service;
	
	@Autowired
	private BeanLogMapper logMapper;

	/**
	 * 查询
	 * @author dengwen 
	 * 2016-9-14下午1:55:17
	 */
	@RequestMapping("searchList.htm")
	public Result searchList(BeanSystemUser bean, GridReq grid) {
		Result rs = new Result();
		try {
			rs = service.searchList(bean, grid, rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
		}
		return rs;
	}

	/**
	 * 新增|修改
	 * @author dengwen 
	 * 2016-9-14下午1:55:31
	 */
	@RequestMapping("save.htm")
	public Result saveData(BeanSystemUser bean, Long roleId) {
		Result rs = new Result();
		try {
			rs = service.saveData(bean, roleId, rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
		}
		return rs;
	}

	/**
	 * 详情
	 * @author dengwen 
	 * 2016-9-14下午2:22:14
	 */
	@RequestMapping("data.htm")
	public Result getData(Long id) {
		Result rs = new Result();
		try {
			rs = service.getData(id, rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
		}
		return rs;
	}
	
	/**
	 * 删除
	 * @author dengwen 
	 * 2016-9-14下午2:22:32
	 */
	@RequestMapping("delete.htm")
	public Result delete(Long[] ids) {
		Result rs = new Result();
		try {
			rs = service.delete(Arrays.asList(ids), rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
		}
		return rs;
	}
	
	/**
	 * 登录
	 * @author dengwen 
	 * 2016-9-14下午2:55:51
	 */
	@RequestMapping("login.htm")
	public Result sign(BeanSystemUser bean,HttpServletRequest request){
		Result rs = new Result();
		try {
			rs = service.sign(bean, request,rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
		}
		return rs;
	}
	
	/**
	 * 退出
	 * @author zoneyu 16-6-3
	 */
	@RequestMapping("loginout.htm")
	public Result loginout(HttpServletRequest request){
		Result rs = new Result();
		BeanSystemUser user = (BeanSystemUser) request.getSession().getAttribute("user");
		if(!ValidateUtils.isBlank(user)){
			BeanLog log = new BeanLog();
			log.setIpAddress(getIP.getIpAddr(request));
			log.setCreateTime(new Timestamp(new Date().getTime()));
			log.setSid(user.getId());
			log.setOperationType("退出");
			log.setEventName("退出系统");
			log.setUserName(user.getUserName());
			logMapper.insert(log);
		}
		request.getSession().removeAttribute("userId");
		return rs;
	}
	
	/**
	 * 修改密码
	 * @author dengwen 
	 * 2016-9-14下午3:03:25
	 */
	@RequestMapping("changePwd.htm")
	public Result changePwd(HttpServletRequest request,String opwd,String npwd) {
		Result rs = new Result();
		try {
			rs = service.changePwd(request, opwd, npwd, rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
		}
		return rs;
	}

}
