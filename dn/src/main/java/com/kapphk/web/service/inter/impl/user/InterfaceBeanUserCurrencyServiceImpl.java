package com.kapphk.web.service.inter.impl.user;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.web.bean.system.BeanSetting;
import com.kapphk.web.bean.user.BeanUser;
import com.kapphk.web.bean.user.BeanUserCurrency;
import com.kapphk.web.dao.mapper.system.BeanSettingMapper;
import com.kapphk.web.dao.mapper.user.BeanUserCurrencyMapper;
import com.kapphk.web.dao.mapper.user.BeanUserMapper;
import com.kapphk.web.service.inter.imethod.user.InterfaceBeanUserCurrencyService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.DateUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;

/**
 * 欧币明细
 * @author dengwen 
 * 2016-10-14下午4:41:11
 */
@Service
public class InterfaceBeanUserCurrencyServiceImpl implements
		InterfaceBeanUserCurrencyService {

	@Autowired
	private BeanUserCurrencyMapper mapper;
	
	@Autowired
	private BeanUserMapper userMapper;
	
	@Autowired
	private BeanSettingMapper settingMapper;

	/**
	 * 获取蓝币明细
	 */
	@Override
	public Result getUserCurrencyList(BeanUserCurrency bean, Integer page,
			Result rs) throws Exception {
		if(!ValidateUtils.isBlank(bean.getUid())){
			page = ValidateUtils.isBlank(page) ? 1 : page ;
			List<Map<String,Object>> list = mapper.getUserCurrencyList(bean,(page-1)*GridReq.ROWS,GridReq.ROWS);
			rs.put("info", list);
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("blueCurrency", userMapper.findById(bean.getUid()).getBlueCurrency());
			rs.put("user", map);
		}else{
			rs.setStatus(Contents.ERROR);
			rs.setMsg("参数有误");
		}
		return rs;
	}

	/**
	 * 签到
	 */
	@Override
	public Result saveCurrency(BeanUserCurrency bean, Result rs)
			throws Exception {
		if(!ValidateUtils.isBlank(bean.getUid())){
			bean.setCurrencyType(1);
			bean.setRecordType(5);
			int count = mapper.findSignIn(bean.getUid(),DateUtils.getLocalYmdDate());
			if(count == 0){
				BeanSetting setting = settingMapper.findById("cur_sign");
				BigDecimal bd = new BigDecimal(setting.getValue());
				bean.setCreateTime(new Date());
				bean.setContent("签到");
				bean.setCurrency(bd);
				mapper.insert(bean);
				BeanUser user = userMapper.findById(bean.getUid());
				user.setBlueCurrency(user.getBlueCurrency().add(bd));
				userMapper.update(user);
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("content", "+ "+setting.getValue()+" 动能蓝币");
				rs.put("info", map);
			}else{
				rs.setStatus(Contents.ERROR);
				rs.setMsg("您已签到，改天在来");
			}
		}else{
			rs.setStatus(Contents.ERROR);
			rs.setMsg("参数有误");
		}
		return rs;
	}
}
