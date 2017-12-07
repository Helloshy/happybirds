package com.kapphk.web.controller.web.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kapphk.web.bean.system.BeanPublicHelp;
import com.kapphk.web.service.web.imethod.system.BeanPublicHelpService;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;

/**
 * 详情控制层
 * @author zoneyu 16-4-6
 */
@RestController
@RequestMapping("/web/system/public/")
public class BeanPublicHelpController {
	@Autowired
	private BeanPublicHelpService service;

	/**
	 * 查询
	 * @author zoneyu 2016-4-6
	 */
	@RequestMapping("searchList.htm")
	public Result searchList(BeanPublicHelp PublicHelp,int page,int rows)throws Exception{
		Result rs = new Result();
		try {
			rs = service.searchList(PublicHelp,page,rows,rs);
		} catch (Exception e) { 
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("请求失败");
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * 保存
	 * @author zoneyu 16-4-6
	 */
	@RequestMapping("save.htm")
	public Result savePublicHelp(BeanPublicHelp PublicHelp){          
		Result rs = new Result() ;
		try {
			rs = service.savePublicHelp(PublicHelp,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs ;
	}
	
	/**
	 * 修改
	 * @author zoneyu 16-4-6
	 */
	@RequestMapping("data.htm")
	public Result editGoodsType(Long id){
		Result rs = new Result();
		try {
			rs = service.editPublicHelp(rs,id) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs ;
	}
	
	/**
	 * 删除
	 * @author zoneyu 2016-4-6
	 */
	@RequestMapping("delete.htm")
	public Result delete(String ids) {
		Result rs = new Result();
		try {
			rs = service.delete(ids,rs);
		} catch (Exception e) { 
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("请求失败");
			e.printStackTrace();
		}
		return rs;
	}
	
}
