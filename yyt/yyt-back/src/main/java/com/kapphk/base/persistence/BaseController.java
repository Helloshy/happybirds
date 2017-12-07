package com.kapphk.base.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.kapphk.system.bean.Resource;
import com.kapphk.system.service.ResourceService;

/** 
*
* @author EXIA
* @version 1.0
* @createDate 2016年9月23日 上午9:38:15 
*
*/
public class BaseController {
	
	@Autowired
	private ResourceService resourceService;
	
	public List<Resource> findRes(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		String id = request.getParameter("id");
		List<Resource> list = new ArrayList<Resource>();
		if(!StringUtils.isBlank(id)){
			try {
				list = resourceService.findByParentId(Long.parseLong(id));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
