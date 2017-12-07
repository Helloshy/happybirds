package com.kapphk.web.controller.web.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kapphk.web.bean.system.BeanMessagePush;
import com.kapphk.web.service.web.imethod.system.BeanMessagePushService;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;

/**
 * 消息推送控制层
 * @author zoneyu 16-6-12
 */
@RestController
@RequestMapping("/web/system/messagepush/")
public class BeanMessagePushController {
	
	@Autowired
	private BeanMessagePushService service;

	/**
	 * 查询
	 * @author zoneyu 2016-6-12
	 */
	@RequestMapping("searchList.htm")
	public Result searchList(BeanMessagePush MessagePush, int page, int rows)throws Exception{
		Result rs = new Result();
		try {
			rs = service.searchList(MessagePush,page,rows,rs);
		} catch (Exception e) { 
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("请求失败");
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * 保存
	 * @author zoneyu 16-6-12
	 */
	@RequestMapping("save.htm")
	public Result saveMessagePush(BeanMessagePush bean, String[] type){          
		Result rs = new Result() ;
		try {
			rs = service.saveMessagePush(bean,type,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs ;
	}
	
	/**
	 * 修改
	 * @author zoneyu 16-6-12
	 */
	@RequestMapping("data.htm")
	public Result getData(Long id){
		Result rs = new Result();
		try {
			return service.getData(id,rs) ;
		} catch (Exception e) {
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("请求失败");
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * 删除
	 * @author zoneyu 2016-6-12
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
