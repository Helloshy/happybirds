package com.kapphk.yyt.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.kapphk.base.controller.BaseController;
import com.kapphk.system.bean.Guide;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.Result.MSG;
import com.kapphk.yyt.service.GuideService;

/**
 * 首页轮播图
 */
@RestController
@RequestMapping("/web/guide")
public class GuideController extends BaseController {

	@Autowired
	private GuideService guideService;
	
	/**
	 * 获取列表
	 * @param sort
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Map<String,Object> list(){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			Guide guide = new Guide();
			guide.setStatus(1);
			PageInfo<Guide> pageInfo = guideService.findByPage(guide, 1, 5, "create_time desc");
			map.put("data", pageInfo.getList());
			map.put("msg", "返回成功");
			map.put("status",Contents.OK);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg",MSG.ERROR.getMsg());
			map.put("status",Contents.ERROR);
		}
		return map;
	}
	
	
}
