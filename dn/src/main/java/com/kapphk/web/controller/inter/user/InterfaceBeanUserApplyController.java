package com.kapphk.web.controller.inter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kapphk.web.bean.system.BeanUserApply;
import com.kapphk.web.service.inter.imethod.user.InterfaceBeanUserApplyService;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;

/**
 * 申请控制层
 * @author zoneyu 16-9-30
 */
@RestController
@RequestMapping("/apply/")
public class InterfaceBeanUserApplyController {
	
	@Autowired
	private InterfaceBeanUserApplyService service;
	
	/**
	 * 获取动能留学课程
	 * @author dengwen 
	 * 2016-10-17下午5:58:37
	 */
	@RequestMapping("getCourseList.do")
	public Result getCourseList(Integer page){
		Result rs = new Result();
		try {
			rs = service.getCourseList(page,rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 保存动能留学申请
	 * @author dengwen 
	 * 2016-10-17下午6:34:38
	 */
	@RequestMapping("saveApply.do")
	public Result saveApply(BeanUserApply bean){
		Result rs = new Result();
		try {
			rs = service.saveApply(bean,rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
}
