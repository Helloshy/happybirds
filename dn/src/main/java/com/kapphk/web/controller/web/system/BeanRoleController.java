package com.kapphk.web.controller.web.system;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kapphk.web.bean.system.BeanRole;
import com.kapphk.web.service.web.imethod.system.BeanRoleService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;

/**
 * 角色管理控制层
 * @author dengwen 
 * 2016-9-14下午2:39:41
 */
@RestController
@RequestMapping("/web/system/role/")
public class BeanRoleController {
	
	@Autowired
	private BeanRoleService service;
	
	/**
	 * 查询
	 * @author dengwen 
	 * 2016-9-14下午2:40:17
	 */
	@RequestMapping("searchList.htm")
	public Result getList(BeanRole bean,GridReq grid) {
		Result rs = new Result();
		try {
			return service.getList(bean, grid, rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}

	/**
	 * 新增|修改
	 * @author dengwen 
	 * 2016-9-14下午2:40:34
	 */
	@RequestMapping("save.htm")
	public Result saveData(BeanRole bean,Long[] resourceId) {
		Result rs = new Result();
		try {
			rs = service.saveData(bean, Arrays.asList(resourceId), rs);
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
	 * 2016-9-14下午2:40:43
	 */
	@RequestMapping("data.htm")
	public Result data(Long id) {
		Result rs = new Result();
		try {
			return service.getData(id, rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}

	/**
	 * 删除
	 * @author dengwen 
	 * 2016-9-14下午2:40:58
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
	 * 初始化角色
	 * @author dengwen 
	 * 2016-9-14下午2:41:52
	 */
	@RequestMapping("initRole.htm")
	public List<Map<String,Object>> initRole(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>() ;
		try {
			list = service.searchGrant(list) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list ;
	}
}
