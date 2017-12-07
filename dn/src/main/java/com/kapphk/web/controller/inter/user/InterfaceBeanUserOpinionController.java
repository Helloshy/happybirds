package com.kapphk.web.controller.inter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kapphk.web.bean.system.BeanOpinion;
import com.kapphk.web.service.inter.imethod.user.InterfaceBeanUserOpinionService;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;

/**
 * 意见反馈
 * @author dengwen 
 * 2016-10-17上午10:20:50
 */
@RequestMapping("/opinion/")
@RestController
public class InterfaceBeanUserOpinionController {

	@Autowired
	private InterfaceBeanUserOpinionService serivce;
	
	/**
	 * 保存意见
	 * @author dengwen 
	 * 2016-10-17上午10:22:33
	 */
	@RequestMapping("saveOpinion.do")
	public Result saveOpinion(BeanOpinion bean){
		Result rs = new Result();
		try {
			rs = serivce.saveOpinion(bean,rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 获取留言列表
	 * @author dengwen 
	 * 2016-10-17上午10:43:51
	 */
	@RequestMapping("getOpinionList.do")
	public Result getOpinionList(BeanOpinion bean){
		Result rs = new Result();
		try {
			rs = serivce.getOpinionList(bean,rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
}
