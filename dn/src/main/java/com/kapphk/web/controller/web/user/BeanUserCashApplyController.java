package com.kapphk.web.controller.web.user;

import com.kapphk.web.utils.GridReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kapphk.web.bean.user.BeanUserCashApply;
import com.kapphk.web.service.web.imethod.user.BeanUserCashApplyService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.Result;
import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

/**
 * 提现申请管理控制层
 * @author dengwen
 * 2016-10-28 11:05:33
 */
@RestController
@RequestMapping("/web/user/usercashapply/")
public class BeanUserCashApplyController {

	@Autowired
	private BeanUserCashApplyService service;

	/**
	 * 查询列表
	 */
	@RequestMapping("searchList.htm")
	public Result getList(BeanUserCashApply bean, String userName, String realName, String startTime, String endTime, GridReq gridReq){
		Result rs = new Result();
		try {
			return service.searchList(rs,bean,userName,realName,startTime,endTime,gridReq);
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
	public Result saveData(BeanUserCashApply bean){
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
	public Result getData(BeanUserCashApply bean){
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
	 * 更新处理与到账
	 * @author dengwen 
	 * 2016-10-28上午11:16:33
	 */
	@RequestMapping("upstatus.htm")
	public Result upstatus(Long[] ids,Integer state){
		Result rs = new Result();
		try {
			return service.upstatus(rs,Arrays.asList(ids),state);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}
	
	/**
	 * 导出
	 * @author dengwen 
	 * 2016-10-28上午11:17:52
	 */
	@RequestMapping("exportExcel.htm")
	public void exportExcel(HttpServletResponse response,BeanUserCashApply bean, String userName, String realName, String startTime, String endTime){
		service.exportExcel(response,bean,userName,realName,startTime,endTime);
	}
	

}
