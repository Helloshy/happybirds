package com.kapphk.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.kapphk.web.bean.system.BeanPublicHelp;
import com.kapphk.web.service.PublicHelpService;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;

/**
 * 详情控制层
 * @author exuan 16-8-25
 */
@RestController
@RequestMapping("/public/")
public class PublicHelpController {

	@Autowired
	private PublicHelpService service ;
	
	/**
	 * 获取详情list
	 * @author exuan 16-8-25
	 */
	@RequestMapping("searchList.do")
	public Result searchList(BeanPublicHelp bean){
		Result rs = new Result() ;
		try {
			rs = service.searchList(bean,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 跳转详情页面
	 * @author exuan 16-8-25
	 */
	@RequestMapping("findPublicDetail.do")
	public ModelAndView findPublicDetail(BeanPublicHelp bean,HttpServletRequest request){
		String memo = "" ;
		try {
			memo = service.findPublicDetail(bean) ;
			request.setAttribute("memo", memo) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/jsp/detail.jsp") ;
	}
	
	/**
	 * 动能社区介绍
	 * @author exuan 16-10-31
	 */
	@RequestMapping("searchDNList.do")
	public Result searchDNList(BeanPublicHelp bean){
		Result rs = new Result() ;
		try {
			rs = service.searchDNList(bean,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
}
