package com.kapphk.shiro;


import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.config.Ini;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.kapphk.system.bean.Resource;
import com.kapphk.system.mapper.ResourceMapper;
import com.kapphk.system.service.ResourceService;


/**
 * 
 * @author EXIA
 * 
 */
public class CustomChainDefinition implements FactoryBean<Ini.Section> {

	@Autowired
	private ResourceMapper resourceService;

	// 静态资源访问权限
	private String filterChainDefinitions = null;

	public Ini.Section getObject() throws Exception {
		Ini ini = new Ini();
		ini.load(filterChainDefinitions);
		Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
		List<Resource> lists = resourceService.all();
		for (Resource resource : lists) {
			if (!StringUtils.isBlank(resource.getUrl() + "") && !StringUtils.isBlank(resource.getPermission() + "")) {
				String permission = "perms[" + resource.getPermission() + "]";
				section.put(resource.getUrl() + "", permission);
			}

		}
		section.put("/**", "authc");
		return section;
	}

	public void setFilterChainDefinitions(String filterChainDefinitions) {
		this.filterChainDefinitions = filterChainDefinitions;
	}

	public Class<?> getObjectType() {
		return this.getClass();
	}

	public boolean isSingleton() {
		return false;
	}
}
