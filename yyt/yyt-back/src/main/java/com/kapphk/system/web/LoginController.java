package com.kapphk.system.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kapphk.system.bean.Resource;
import com.kapphk.system.service.ResourceService;
import com.kapphk.web.utils.TreeUtil;

@Controller
@RequestMapping("/system")
public class LoginController {
	@Autowired
	private ResourceService resourceService;
	
	/**
	 * 跳转到登录页
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(HttpServletRequest request){
		request.removeAttribute("error");
		return "/login";
	}
	
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
	public String login(String username, String password, HttpServletRequest request) {
		try {
			if (!request.getMethod().equals("POST")) {
				request.setAttribute("error", "支持POST方法提交！");
			}
			if (StringUtils.isBlank(username)||StringUtils.isBlank(password)){
				request.setAttribute("error", "用户名或密码不能为空！");
				return "/login";
			}
			Subject user = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			try {
				user.login(token);
			} catch (LockedAccountException lae) {
				token.clear();
				request.setAttribute("error", "用户已经被禁用不能登录，请与管理员联系！");
				return "/login";
			} catch (ExcessiveAttemptsException e) {
				token.clear();
				request.setAttribute("error", "账号：" + username + " 登录失败次数过多,锁定10分钟!");
				return "/login";
			} catch(AccountException e) {
				token.clear();
				request.setAttribute("error", "账号：" + username + " 没有登录权限！");
				return "/login";
			} catch (AuthenticationException e) {
				token.clear();
				request.setAttribute("error", "用户或密码不正确！");
				return "/login";
			}
			request.removeAttribute("error");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "登录异常，请联系管理员！");
			return "/login";
		}
		return "redirect:index.htm";
	}
	
	/**
	 * 后台首页
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/index.htm",method=RequestMethod.GET)
	public String index(Model model,Long parentId){
		Object obj = SecurityUtils.getSubject().getSession().getAttribute("roleId");
		List<Resource> list = new ArrayList<Resource>(); 
		if(obj != null){
			
			list = resourceService.findResourceByRoleId(Long.parseLong(obj.toString()));
		}
		
		TreeUtil treeUtil = new TreeUtil();
		List<Resource> ns = treeUtil.getChildResources(list, 0L);
		model.addAttribute("list", ns);
		parentId = 2L;
		model.addAttribute("parentId", parentId);
		return "index";
	}
	
	/**
	 * 菜单列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/menu",method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getMenu() throws Exception {
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		Object obj = SecurityUtils.getSubject().getSession().getAttribute("roleId");
		List<Resource> list = new ArrayList<Resource>(); 
		if(obj != null){
			list = resourceService.findResourceByRoleId(Long.parseLong(obj.toString()));
		}
		TreeUtil treeUtil = new TreeUtil();
		List<Resource> ns = treeUtil.getChildResources(list, 0L);
		jsonMap.put("list", ns);
		return jsonMap;
	}
	
	@RequestMapping(value="logout",method=RequestMethod.GET)
	public String logout(){
		SecurityUtils.getSubject().logout();
		return "redirect:login.htm";
	}
	
}
