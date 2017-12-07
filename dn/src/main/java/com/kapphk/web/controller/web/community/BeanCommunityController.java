package com.kapphk.web.controller.web.community;

import com.kapphk.web.utils.GridReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kapphk.web.bean.community.BeanCommunity;
import com.kapphk.web.bean.user.BeanUser;
import com.kapphk.web.service.web.imethod.community.BeanCommunityService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 动能社区管理控制层
 * @author dengwen
 * 2016-10-31 15:56:12
 */
@RestController
@RequestMapping("/web/community/community/")
public class BeanCommunityController {

	@Autowired
	private BeanCommunityService service;

	/**
	 * 查询列表
	 */
	@RequestMapping("searchList.htm")
	public Result getList(String province, String itemName, String userName,String realName, GridReq gridReq){
		Result rs = new Result();
		try {
			return service.searchList(rs,province,itemName,userName,realName,gridReq);
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
	public Result saveData(BeanCommunity bean,MultipartFile file,String[] province,String city,HttpServletRequest request,String[] imgs){
		Result rs = new Result();
		try {
			return service.saveData(rs,bean,file,province,city,request,imgs);
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
	public Result getData(BeanCommunity bean){
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
	 * 获取用户姓名
	 */
	@RequestMapping("getUserDetail.htm")
	public Result getUserDetail(BeanUser bean){
		Result rs = new Result();
		try {
			if(ValidateUtils.isBlank(bean.getUserName())) return rs;
			return service.getUserDetail(rs,bean);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}
	
	/**
	 * 获取全部小组
	 */
	@RequestMapping("getCommunityList.htm")
	public List<Map<String,Object>> getCommunityList(){
		return service.getCommunityList();
	}
	
	/**
	 * 导出数据
	 * @author dengwen 
	 * 2016-10-8上午10:15:18
	 */
	@RequestMapping("exportExcel.htm")
	public void exportExcel(HttpServletResponse response,String province, String itemName, String userName,String realName){
		service.exportExcel(response,province,itemName,userName,realName);
	}
}
