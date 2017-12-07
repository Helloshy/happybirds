package com.kapphk.web.controller.web.system;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kapphk.web.bean.system.BeanaAppVersions;
import com.kapphk.web.service.web.imethod.system.BeanAppVersionsService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;

@RestController
@RequestMapping("/web/system/version/")
public class BeanAppVersionsController {

	@Autowired
	private BeanAppVersionsService service;

	/**
	 * 查询
	 */
	@RequestMapping("searchList.htm")
	public Result searchList(int page,int rows)throws Exception{
		Result rs = new Result();
		try {
			rs = service.searchList(page,rows,rs);
		} catch (Exception e) { 
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("请求失败");
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("save.htm")
	public Result saveData(BeanaAppVersions bean){          
		Result rs = new Result() ;
		try {
			rs = service.saveData(bean,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs ;
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
	
}
