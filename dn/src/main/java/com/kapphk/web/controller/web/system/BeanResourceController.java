package com.kapphk.web.controller.web.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kapphk.web.bean.system.BeanStandardResource;
import com.kapphk.web.service.web.imethod.system.BeanResourceService;
import com.kapphk.web.utils.Result;

/**
 * 菜单控制层
 * @author dengwen 
 * 2016-9-16下午1:37:40
 */
@RestController
@RequestMapping("/web/system/resource/")
public class BeanResourceController {
	
	@Autowired
	private BeanResourceService service;
	
	/**
	 * 查询菜单目录
	 * @author dengwen 
	 * 2016-9-16下午1:37:51
	 */
	@RequestMapping("findResourceTree.htm")
	public List<BeanStandardResource> findResourceTree(HttpServletRequest request,HttpServletResponse response,Result rs){
		List<BeanStandardResource> list = new ArrayList<BeanStandardResource>();
		Long userId = (Long)request.getSession().getAttribute("userId");
		list = service.findUserResourcesByUserId(userId,list);
		return list;
	}
	
	/**
	 * 初始化菜单下拉框
     * @author dengwen 
	 * 2016-9-16下午1:37:51
	 */
	@RequestMapping("searchResourceList.htm")
	public List<Map<String,Object>> searchResourceList(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>() ;
		try {
			list = service.searchResourceList(list) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list ;
	}
}
