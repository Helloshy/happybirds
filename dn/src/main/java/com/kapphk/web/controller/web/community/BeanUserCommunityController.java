package com.kapphk.web.controller.web.community;

import com.kapphk.web.utils.GridReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kapphk.web.bean.community.BeanUserCommunity;
import com.kapphk.web.service.web.imethod.community.BeanUserCommunityService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.Result;
import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

/**
 * 社区成员控制层
 * @author dengwen
 * 2016-11-01 17:51:26
 */
@RestController
@RequestMapping("/web/community/usercommunity/")
public class BeanUserCommunityController {

	@Autowired
	private BeanUserCommunityService service;

	/**
	 * 查询列表
	 */
	@RequestMapping("searchList.htm")
	public Result getList(String itemName, String userName, String realName, GridReq gridReq){
		Result rs = new Result();
		try {
			return service.searchList(rs,itemName,userName,realName,gridReq);
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
	public Result saveData(BeanUserCommunity bean){
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
	public Result getData(BeanUserCommunity bean){
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
	 * 导出数据
	 * @author dengwen 
	 * 2016-10-8上午10:15:18
	 */
	@RequestMapping("exportExcel.htm")
	public void exportExcel(HttpServletResponse response){
		service.exportExcel(response);
	}

}
