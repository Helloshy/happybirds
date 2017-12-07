package com.kapphk.web.controller.web.system;

import com.kapphk.web.utils.GridReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kapphk.web.bean.system.BeanUserApply;
import com.kapphk.web.service.web.imethod.system.BeanUserApplyService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.Result;
import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

/**
 * 申请管理控制层
 * @author dengwen
 * 2016-09-21 13:53:41
 */
@RestController
@RequestMapping("/web/system/userapply/")
public class BeanUserApplyController {

	@Autowired
	private BeanUserApplyService service;

	/**
	 * 查询列表
	 */
	@RequestMapping("searchList.htm")
	public Result getList(BeanUserApply bean, GridReq gridReq){
		Result rs = new Result();
		try {
			return service.searchList(rs,bean,gridReq);
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
	public Result saveData(BeanUserApply bean){
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
	public Result getData(BeanUserApply bean){
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
	 * 导出
	 * @author dengwen 
	 * 2016-10-25下午5:30:14
	 */
	@RequestMapping("exportExcel.htm")
	public void exportExcel(BeanUserApply bean,HttpServletResponse response){
		service.exportExcel(bean,response);
	}

}
