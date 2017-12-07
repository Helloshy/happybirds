package com.kapphk.yyt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.kapphk.base.controller.BaseController;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;
import com.kapphk.yyt.bean.UserNumber;
import com.kapphk.yyt.service.UserNumberService;

/**
 * 燃气控制器
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/web/userNumber")
public class UserNumberController extends BaseController {
	
	@Autowired
	private UserNumberService userNumberService;
	
	
	/**
	 * 绑定燃气帐号 
	 * @param uid 用户id
	 * @param unid 用户燃气编号
	 * @return
	 */
	@RequestMapping(value="/binding.htm",method=RequestMethod.POST)
	public Result binding(@RequestParam(required=true)Long uid,
			@RequestParam(required=true)Long unid){
		Result result = new Result("绑定成功");
		try {
			return userNumberService.bindingUser(uid, unid, result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("绑定失败");
			result.setStatus(Contents.ERROR);
		}
		return result;
	}

	/**
	 * 燃气帐号详情
	 * @param uid 用户id
	 * @param unid 用户燃气编号
	 * @return
	 */
	@RequestMapping(value="/ .htm",method=RequestMethod.GET)
	public Result details(@RequestParam(required=true)Long unid){
		Result result = new Result();
		try {
			UserNumber userNumnber = userNumberService.findById(unid);
			result.put(userNumnber);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("绑定失败");
			result.setStatus(Contents.ERROR);
		}
		return result;
	}
	
	/**
	 * 
	 * @param unid
	 * @return
	 */
	@RequestMapping(value="/lastDetails.htm",method=RequestMethod.POST)
	public Result lastDetails(@RequestParam(required=true)Long uid){
		Result result = new Result();
		try {
			List<UserNumber> numbers = userNumberService.findListByUid(null,uid);
			if(null != numbers && numbers.size()>0){
				result.put(numbers.get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("绑定失败");
			result.setStatus(Contents.ERROR);
		}
		return result;
	}
	
	/**
	 * 用户编号
	 * @param unid
	 * @param unid
	 * @return
	 */
	@RequestMapping(value="/list.htm",method=RequestMethod.GET)
	public Map<String,Object> list(@RequestParam(required=false)Long unid
			,@RequestParam(required=true)Long uid){
		Map<String ,Object> result = new HashMap<String ,Object>();
		try {
			List<UserNumber> numbers = userNumberService.findListByUid(unid,uid);
			result.put("msg", "返回成功");
			result.put("status",Contents.OK);
			if(numbers!= null && numbers.size() == 1){
				result.put("data", numbers.get(0));
			}else{
				result.put("data", numbers);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg",MSG.ERROR.getMsg());
			result.put("status",Contents.ERROR);
		}
		return result;
	}
	
	/**
	 * 查询单元列表
	 * @param unitName 单元名称
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping(value="/unitList.htm",method=RequestMethod.GET)
	public Map<String,Object> unitList(@RequestParam(required=false,name="q")String unitName
			,@RequestParam(defaultValue="1")Integer page
			,@RequestParam(defaultValue ="10")Integer limit){
		Map<String ,Object> result = new HashMap<String ,Object>();
		try {
			result.put("msg", "返回成功");
			result.put("status",Contents.OK);
			PageInfo<UserNumber> pageInfo = userNumberService.queryUnit(unitName,page,limit);
			result.put("data",pageInfo.getList());
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg",MSG.ERROR.getMsg());
			result.put("status",Contents.ERROR);
		}
		return result;
	}



}
