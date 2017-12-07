package com.kapphk.web.controller.web.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kapphk.web.bean.system.BeanOpinion;
import com.kapphk.web.service.web.imethod.system.BeanOpinionService;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;

/**
 * 意见反馈控制层
 * @author cgj 16-4-6
 */
@RestController
@RequestMapping("/web/system/opinion/")
public class BeanOpinionController {
	
	@Autowired
	private BeanOpinionService service;

	/**
	 * 查询
	 * @author cgj 2016-4-6
	 */
	@RequestMapping("searchList.htm")
	public Result searchList(BeanOpinion Opinion,int page,int rows)throws Exception{
		Result rs = new Result();
		try {
			rs = service.searchList(Opinion,page,rows,rs);
		} catch (Exception e) { 
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("请求失败");
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * 保存
	 * @author cgj 16-4-6
	 */
	@RequestMapping("save.htm")
	public Result saveOpinion(BeanOpinion Opinion){          
		Result rs = new Result() ;
		try {
			rs = service.saveOpinion(Opinion,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs ;
	}
	
	/**
	 * 修改
	 * @author cgj r-4-6
	 */
	@RequestMapping("data.htm")
	public Result editGoodsType(Long id){
		Result rs = new Result() ;
		try {
			rs = service.editOpinion(rs,id) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs ;
	}
	
	/**
	 * 删除
	 * @author cgj 2016-4-6
	 */
	@RequestMapping("delete.htm")
	public Result delete(Long id) {
		Result rs = new Result();
		try {
			rs = service.delete(id,rs);
		} catch (Exception e) { 
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("请求失败");
			e.printStackTrace();
		}
		return rs;
	}
	
}
