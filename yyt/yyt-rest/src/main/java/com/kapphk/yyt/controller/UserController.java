package com.kapphk.yyt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kapphk.base.controller.BaseController;
import com.kapphk.system.bean.User;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.Result;
import com.kapphk.yyt.service.UserService;

/**
 * 用户控制器
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/web/user")
public class UserController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 微信登录
	 * @param headimgurl 微信用户头像
	 * @param openid 微信用户的openid
	 * @return
	 */
	@RequestMapping(value="/login.htm",method=RequestMethod.POST)
	public Result login(@RequestParam(required=false)String headimgurl
			,@RequestParam(required=true)String openid
			,@RequestParam(required=false)Integer sex){
		Result result = new Result("登录成功");
		try {
			User user = new User();
			user.setHeadimgurl(headimgurl);
			user.setOpenid(openid);
			user.setSex(sex);
			userService.login(user,result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("登录失败");
			result.setStatus(Contents.ERROR);
		}
		return result;
	}
	
	/**
	 * 员工登录
	 * @param username
	 * @param pwd
	 * @return
	 */
	@RequestMapping(value="/staffLogin.htm",method=RequestMethod.POST)
	public Result staffLogin(@RequestParam(required=true)String username,
			@RequestParam(required=true)String pwd){
		Result result = new Result("登录成功");
		try {
			
			userService.staffLogin(username,pwd,result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("登录失败");
			result.setStatus(Contents.ERROR);
		}
		return result;
	}
}
