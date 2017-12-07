package com.kapphk.shiro;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.kapphk.system.bean.Resource;
import com.kapphk.system.bean.Role;
import com.kapphk.system.bean.SystemUser;
import com.kapphk.system.service.ResourceService;
import com.kapphk.system.service.RoleService;
import com.kapphk.system.service.SystemUserService;

/**
 * 
 * @author EXIA
 *
 */
public class CustomRealm extends AuthorizingRealm {
	
	//注入service
	@Autowired
	private RoleService roleService;
	@Autowired
	private SystemUserService systemUserService;
	@Autowired
	private ResourceService resourceService;
	
	@Override
	public void setName(String name) {
		super.setName("customRealm");
	}
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		SystemUser systemUser = null;
		try {
			systemUser = systemUserService.findByUsername(username);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		if(systemUser==null){
			throw new UnknownAccountException();
		}
		
		if(systemUser.getState() == 0){
			throw new LockedAccountException();
		}

		String password = systemUser.getPwd();
		
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, // 用户名
				password, // 密码
				ByteSource.Util.bytes(username + "" + systemUser.getSalt()),// salt=username+salt
				getName() // realm name
		);

		Session session = SecurityUtils.getSubject().getSession();
		session.setAttribute("systemUser", systemUser);
		session.setAttribute("systemUserId", systemUser.getId());
		
		List<Role> roleList = roleService.findRoleByUserId(systemUser.getId());
		if(  null != roleList && roleList.size() > 0){
			if(roleList.get(0).getState() == 0){
				throw new AccountException();
			}
			session.setAttribute("role", roleList.get(0));
			session.setAttribute("roleId", roleList.get(0).getId());
		}else{
			throw new AccountException();
		}

		return authenticationInfo;
	}
	
	

	// 用于授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String loginName = SecurityUtils.getSubject().getPrincipal().toString();
		if (loginName != null) {
			String roleId = SecurityUtils.getSubject().getSession().getAttribute("roleId").toString();
			List<Resource> rs = resourceService.findResourceByRoleId(Long.parseLong(roleId));
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

			// 用户的角色对应的所有权限
			for (Resource resource : rs) {
				info.addStringPermission(resource.getPermission().toString());
			}
			return info;
		}
		return null;
	}
	
	//清除缓存
	public void clearCached() {
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		super.clearCache(principals);
	}


}
