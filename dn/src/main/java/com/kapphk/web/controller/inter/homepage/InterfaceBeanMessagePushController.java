package com.kapphk.web.controller.inter.homepage;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.kapphk.web.bean.system.BeanMessagePush;
import com.kapphk.web.service.inter.imethod.homepage.InterfaceBeanMessagePushService;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;

/**
 * 系统消息
 * @author dengwen 
 * 2016-11-12下午3:29:03
 */
@RestController
@RequestMapping("/message/")
public class InterfaceBeanMessagePushController {

	@Autowired
	private InterfaceBeanMessagePushService service;
	
	/**
	 * 系统通知列表
	 * @author dengwen 
	 * 2016-11-12下午3:31:20
	 */
	@RequestMapping("getMessagePushList.do")
	public Result getMessagePushList(Long uid,Integer page){
		Result rs = new Result() ;
		try {
			rs = service.getMessagePushList(uid,page,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 公司公告详情
	 * @author dengwen 16-10-8
	 */
	@RequestMapping("getMessagePushDetail.do")
	public ModelAndView getMessagePushDetail(Long id,HttpServletRequest request){
		try {
			BeanMessagePush bean = service.getMessagePushDetail(id);
			request.setAttribute("content", bean.getContent());
			request.setAttribute("title", bean.getTitle());
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			request.setAttribute("createTime", sf.format(bean.getCreateTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/jsp/notice.jsp");
	}
	
	/**
	 * 消息红点
	 * @author zoneyu 16-12-3
	 */
	@RequestMapping("getAmount.do")
	public Result getAmount(String time){
		Result rs = new Result() ;
		try {
			rs = service.getAmount(time,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
}
