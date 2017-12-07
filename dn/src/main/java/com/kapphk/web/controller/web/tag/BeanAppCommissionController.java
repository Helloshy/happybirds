package com.kapphk.web.controller.web.tag;

import com.kapphk.web.utils.GridReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kapphk.web.bean.tag.BeanAppCommission;
import com.kapphk.web.service.web.imethod.tag.BeanAppCommissionService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.Result;
import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

/**
 * 分佣比例管理控制层
 * @author dengwen
 * 2016-10-28 14:36:24
 */
@RestController
@RequestMapping("/web/tag/appcommission/")
public class BeanAppCommissionController {

	@Autowired
	private BeanAppCommissionService service;

	/**
	 * 查询列表
	 */
	@RequestMapping("searchList.htm")
	public Result getList(BeanAppCommission bean, GridReq gridReq){
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
	public Result saveData(BeanAppCommission bean){
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
	public Result getData(BeanAppCommission bean){
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
	 * 2016-10-28上午11:17:52
	 */
	@RequestMapping("exportExcel.htm")
	public void exportExcel(HttpServletResponse response){
		service.exportExcel(response);
	}

}
