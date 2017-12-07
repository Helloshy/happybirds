package com.kapphk.web.controller.web.community;

import com.kapphk.web.utils.GridReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kapphk.web.bean.community.BeanCommunityStaff;
import com.kapphk.web.service.web.imethod.community.BeanCommunityStaffService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.Result;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

/**
 * 社区管理人员控制层
 * @author dengwen
 * 2016-11-01 13:39:54
 */
@RestController
@RequestMapping("/web/community/communitystaff/")
public class BeanCommunityStaffController {

	@Autowired
	private BeanCommunityStaffService service;

	/**
	 * 查询列表
	 */
	@RequestMapping("searchList.htm")
	public Result getList(String tagValue, String itemName, String managerPhone, String realName, GridReq gridReq){
		Result rs = new Result();
		try {
			return service.searchList(rs,tagValue,itemName,managerPhone,realName,gridReq);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}

	/**
	 * 保存
	 */
	@RequestMapping("save.htm")
	public Result saveData(BeanCommunityStaff bean){
		Result rs = new Result();
		try {
			return service.saveData(rs,bean);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}

	/**
	 * 详情
	 */
	@RequestMapping("data.htm")
	public Result getData(BeanCommunityStaff bean){
		Result rs = new Result();
		try {
			return service.getData(rs,bean);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}

	/**
	 * 删除
	 */
	@RequestMapping("delete.htm")
	public Result delete(Long[] ids){
		Result rs = new Result();
		try {
			return service.delete(rs,Arrays.asList(ids));
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}
	
	/**
	 * 获取全部用户
	 * @author dengwen 
	 * 2016-11-1下午2:14:14
	 */
	@RequestMapping("getUserList.htm")
	public List<Map<String,Object>> getUserList(String userName){
		return service.getUserList(userName);
	}
	
	/**
	 * 导出数据
	 * @author dengwen 
	 * 2016-10-8上午10:15:18
	 */
	@RequestMapping("exportExcel.htm")
	public void exportExcel(HttpServletResponse response,String tagValue, String itemName, String managerPhone, String realName){
		service.exportExcel(response,tagValue,itemName,managerPhone,realName);
	}

}
