package com.kapphk.yyt.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.kapphk.base.controller.BaseController;
import com.kapphk.system.bean.Message;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;
import com.kapphk.yyt.service.MessageService;

/**
 * 消息控制器
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/web/message")
public class MessageController extends BaseController {
	
	@Autowired
	private MessageService messageService;
	
	/**
	 * 消息列表
	 * @param uid 用户id
	 * @param isRead 是否已读
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/list.htm",method = RequestMethod.GET)
	public Map<String ,Object> list(@RequestParam(required=true)Long uid
			,@RequestParam(required=false)Integer isRead
			,@RequestParam(defaultValue="1")Integer page
			,@RequestParam(defaultValue ="10")Integer limit){
		Map<String ,Object> result = new HashMap<String ,Object>();
		try {
			result.put("msg", "返回成功");
			result.put("status",Contents.OK);
			PageInfo<Message> pageInfo = messageService.findPageByUid(uid,isRead,page,limit);
			result.put("data",pageInfo.getList());
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg",MSG.ERROR.getMsg());
			result.put("status",Contents.ERROR);
		}
		return result;
	}
	
	/**
	 * 消息详情
	 * @param messageId
	 * @return
	 */
	@RequestMapping(value = "/details.htm",method = RequestMethod.GET)
	public Result details(@RequestParam(required=true)Long messageId){
		Result result = new Result("返回成功");
		try {
			Message message = messageService.findById(messageId);
			result.put(message);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg",MSG.ERROR.getMsg());
			result.setStatus(Contents.ERROR);
		}
		return result;
	}
	
	/**
	 * 设置为已读
	 * @param id 消息id
	 * @return
	 */
	@RequestMapping(value = "/read.htm",method = RequestMethod.POST)
	public Result read(@RequestParam(required=true)Long uid
			,@RequestParam(required=true)Long messageid
			,@RequestParam(required=false,defaultValue="1")Integer isread){
		Result result = new Result("设置成功");
		try {
			return messageService.updateState(uid,messageid,isread,result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg(MSG.ERROR.getMsg());
			result.setStatus(Contents.ERROR);
		}
		return result;
	}
}
