package com.kapphk.web.controller.web.system;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kapphk.web.bean.system.BeanLog;
import com.kapphk.web.service.web.imethod.system.BeanLogService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;

/**
 * 日志管理控制层
 * @author zoneyu 16-6-12
 */
@RestController
@RequestMapping("/web/system/log/")
public class BeanLogController {
	
	@Autowired
	private BeanLogService service;
	
	/**
	 * 查询
	 * @author zoneyu 16-6-12	
	 */
	@RequestMapping("searchList.htm")
	public Result getList(BeanLog bean,GridReq gridReq) {
		Result rs = new Result();
		try {
			return service.getList(bean,gridReq,rs);
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

}
