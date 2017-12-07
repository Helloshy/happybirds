package com.kapphk.yyt.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.base.service.BaseServiceImpl;
import com.kapphk.system.bean.User;
import com.kapphk.system.mapper.UserMapper;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.MD5;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;
import com.kapphk.yyt.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService{

	@Autowired
	private UserMapper mapper;
	
	@Override
	public void init() {
		super.setMapper(mapper);
	}

	@Override
	public Result login(User user,Result result) {
		
		User u  = new User();
		u.setOpenid(user.getOpenid());
		List<User> users = mapper.findList(u);
		if(users != null && users.size()==1){
			u = users.get(0);
			if(ValidateUtils.isBlank(user.getHeadimgurl())){
				u.setHeadimgurl(user.getHeadimgurl());
			}
			if(ValidateUtils.isBlank(user.getSex())){
				u.setSex(user.getSex());
			}
			mapper.update(u);
			u.setPwd(null);
			result.put(u);
		}else{
			user.setCreateTime(new Date());
			user.setState(1);
			user.setUsername(user.getOpenid());
			user.setPwd(MD5.getMD5("123456"));
			mapper.insert(user);
			user.setPwd(null);
			result.put(user);
		}
		return result;
	}

	
	@Override
	public void staffLogin(String username, String pwd, Result result) {
		User user = new User();
		user.setUsername(username);
		user = mapper.findEntityByCondition(user);
		if(null == user || ! MD5.getMD5(pwd).equals(user.getPwd())){
				result.setMsg("账号或密码不正确，请重新输入");
				result.setStatus(Contents.ERROR);
		}
	}

	
}
