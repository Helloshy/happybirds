package com.kapphk.web.service.inter.impl.user;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.web.bean.BeanPhoneCode;
import com.kapphk.web.bean.course.BeanCourseOrder;
import com.kapphk.web.bean.finance.BeanUserPayPassword;
import com.kapphk.web.bean.system.BeanSetting;
import com.kapphk.web.bean.system.BeanaAppVersions;
import com.kapphk.web.bean.tag.BeanUseIdentity;
import com.kapphk.web.bean.user.BeanUser;
import com.kapphk.web.bean.user.BeanUserCurrency;
import com.kapphk.web.dao.mapper.BeanPhoneCodeMapper;
import com.kapphk.web.dao.mapper.course.BeanCourseOfflineMapper;
import com.kapphk.web.dao.mapper.course.BeanCourseOrderMapper;
import com.kapphk.web.dao.mapper.finance.BeanUserPayPasswordMapper;
import com.kapphk.web.dao.mapper.system.BeanSettingMapper;
import com.kapphk.web.dao.mapper.system.BeanaAppVersionsMapper;
import com.kapphk.web.dao.mapper.tag.BeanUseIdentityMapper;
import com.kapphk.web.dao.mapper.user.BeanUserCurrencyMapper;
import com.kapphk.web.dao.mapper.user.BeanUserMapper;
import com.kapphk.web.service.BaseServiceImpl;
import com.kapphk.web.service.inter.imethod.user.InterfaceBeanUserService;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.MessageUtils;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;
import com.kapphk.web.utils.ValidateUtils;

/**
 * 用户业务层(接口)
 * @author zoneyu 16-9-30
 */
@Service("interfaceBeanUserService")
public class InterfaceBeanUserServiceImpl extends BaseServiceImpl<BeanUser, Long> implements
		InterfaceBeanUserService {

	@Autowired
	private BeanUserMapper mapper ;
	
	//短信验证码
	@Autowired
	private BeanPhoneCodeMapper phoneCodeMapper ;
	
	//身份
	@Autowired
	private BeanUseIdentityMapper userIdentityMapper ;
	
	@Autowired
	private BeanUserPayPasswordMapper payPasswordMapper;
	
	@Autowired
	private BeanUserCurrencyMapper currencyMapper;
	
	@Autowired
	private BeanSettingMapper settingMapper;
	
	@Autowired
	private BeanCourseOfflineMapper courseOfflineMapper ;
	
	@Autowired
	private BeanaAppVersionsMapper versionsMapper;
	
	@Autowired
	private BeanUseIdentityMapper identityMapper;
	
	@Autowired
	private BeanCourseOrderMapper orderMapper;
	
	public void init() {
		super.setMapper(mapper) ;
	}
	
	 /**
	  * 登录
	  * @author zoneyu 16-9-30
	  */
	public Result sign(BeanUser user , HttpServletRequest request, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(user.getUserName()) && !ValidateUtils.isBlank(user.getPwd())){
			BeanUser uu = new BeanUser() ;
			uu.setUserName(user.getUserName()) ;
			uu.setPwd(user.getPwd()) ;
			List<BeanUser> list = mapper.findByPage(uu, 0, 1) ;
			if(!ValidateUtils.isEmptyForCollection(list)){
				BeanUser u = list.get(0) ;
				Integer state = u.getState() ;
				if(state == 0){
					rs.setStatus(MSG.ERROR.getStatus()) ;
					rs.setMsg("账号无效") ;
					rs.put("JSESSIONID", "") ;
				}else{
					rs.put("info", u) ;
					rs.put("JSESSIONID", request.getSession().getId()) ;
					user.setId(u.getId()) ;
					mapper.update(user) ;
				}
			}else{
				rs.setStatus(MSG.ERROR.getStatus()) ;
				rs.setMsg("用户名或密码错误") ;
				rs.put("JSESSIONID", "") ;
			}
		}else{
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("参数错误") ;
		}
		return rs ;
	}

	 /**
	  * 忘记密码
	  * @author zoneyu 14-12-29
	  */
	public Result resetPassword(String userName, String pwd, String newPwd,
			String code, Integer type, Result rs) {
		if(!ValidateUtils.isBlank(userName) && !ValidateUtils.isBlank(newPwd)
				&& !ValidateUtils.isBlank(type)){
			if(type - 1 == 0){//
				BeanUser user = new BeanUser() ;
				user.setUserName(userName) ;
				user.setPwd(pwd) ;
				List<BeanUser> list = mapper.findByPage(user, 0, 1) ;
				if(!ValidateUtils.isEmptyForCollection(list)){
					BeanUser u = list.get(0) ;
					user.setPwd(newPwd) ;
					user.setId(u.getId()) ;
					mapper.update(user) ;
				}else{
					rs.setStatus(MSG.ERROR.getStatus()) ;
					rs.setMsg("原用户名或密码错误") ;
				}
			}else if(type - 2 == 0){//忘记密码
				if(!ValidateUtils.isBlank(code)){
					BeanPhoneCode c = new BeanPhoneCode() ;
					c.setPhone(userName) ;
					c.setCode(code) ;
					int count = phoneCodeMapper.findByPageCount(c) ;
					if(count > 0){
						mapper.updatePwd(userName,newPwd) ;
					}else{
						rs.setStatus(MSG.ERROR.getStatus()) ;
						rs.setMsg("验证码错误或已失效") ;
					}
				}else{
					rs.setStatus(MSG.ERROR.getStatus()) ;
					rs.setMsg("验证码为空") ;
				}
			}else{
				rs.setStatus(MSG.ERROR.getStatus()) ;
				rs.setMsg("错误") ;
			}
		}else{
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("参数错误") ;
		}
		return rs ;
	}

	 /**
	  * 获取短信验证码
	  * @author zoneyu 14-12-29
	  */
	public Result insertCodeAndGetCode(String phone, Integer type ,HttpServletRequest request, Result rs)
			throws Exception {
		if(!ValidateUtils.isBlank(phone) && !ValidateUtils.isBlank(type)){
			BeanUser u = new BeanUser() ;
			u.setUserName(phone) ;
			int count = mapper.findByPageCount(u) ;
			String radom = DataUtils.getRadom(4) ;
//			String radom = "1234";
			if(type - 1 == 0){//用户注册
				if(count > 0){
					rs.setStatus(MSG.ERROR.getStatus()) ;
					rs.setMsg("该手机号已经注册了，请直接登录") ;
				}else{
					//删除历史数据
					BeanPhoneCode c = new BeanPhoneCode() ;
					c.setPhone(phone) ;
					phoneCodeMapper.deleteByEntity(c) ;
					
					request.getSession().setAttribute("username", phone) ;
					//保存验证码 
					c.setCode(radom) ;
					c.setCreateTime(new Date()) ;
					phoneCodeMapper.insert(c) ;
					
					//发送验证码,测试不用发送短信
					MessageUtils.sendMessage(phone, radom) ;
					rs.put("info", radom) ;
				}
			}else if(type - 2 == 0){//忘记密码
				if(count > 0){
					//删除历史数据
					BeanPhoneCode c = new BeanPhoneCode() ;
					c.setPhone(phone) ;
					phoneCodeMapper.deleteByEntity(c) ;
					
					//保存验证码
					c.setCode(radom) ;
					c.setCreateTime(new Date()) ;
					phoneCodeMapper.insert(c) ;
					
					request.getSession().setAttribute("username", phone) ;
					
					//发送验证码,测试不用发送短信
					MessageUtils.sendMessage(phone, radom) ;
					rs.put("info", radom) ;
				}else{
					rs.setStatus(MSG.ERROR.getStatus()) ;
					rs.setMsg("该手机号未注册") ;
				}
			}
		}else{
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("参数错误") ;
		}
		return rs ;
	}

	/**
	  * 注册
	  * @author zoneyu 16-2-20
	  */
	public Result register(BeanUser user, String code, String dnPosition, String personalPosition, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(user.getUserName()) && !ValidateUtils.isBlank(user.getPwd()) 
				&& !ValidateUtils.isBlank(code) && !ValidateUtils.isBlank(user.getPosition())){
			BeanPhoneCode phoneCode = new BeanPhoneCode() ;
			phoneCode.setPhone(user.getUserName()) ;
			phoneCode.setCode(code) ;
			int count = phoneCodeMapper.findByPageCount(phoneCode) ;
			if(count == 0){
				rs.setStatus(MSG.ERROR.getStatus()) ;
				rs.setMsg("验证码失效或错误") ;
			}else{
				BeanUser u = new BeanUser() ;
				u.setUserName(user.getUserName()) ;
				count = mapper.findByPageCount(u) ;
				if(count > 0){
					rs.setStatus(MSG.ERROR.getStatus()) ;
					rs.setMsg("该手机号已存注册了，请直接登录") ;
				}else{
					//填写邀请人
					if(!ValidateUtils.isBlank(user.getInviteCode())){
						BeanUser ur = new BeanUser() ;
						ur.setInviteCode(user.getInviteCode());
						ur = mapper.findEntityByCondition(ur) ;
						if(!ValidateUtils.isBlank(ur)){
							user.setUidFrom(ur.getId());
						}
					}
					String position = user.getPosition() ;
					if("其他，动能集团学员，动能集团员工".contains(position)){
						user.setState(1);
					}else{
						user.setState(2);
					}
					
					//生成自己的邀请码
					String inviteCode = DataUtils.getRadom(9) ;
					boolean b = true ;
					while(b){
						BeanUser ur = new BeanUser() ;
						ur.setInviteCode(inviteCode);
						int counts = mapper.findByPageCount(ur);
						if(counts == 0){
							user.setInviteCode(inviteCode);
							b = false ;
						}else{
							inviteCode = DataUtils.getRadom(9) ;
						}
					}
					Date date = new Date() ;
					user.setCreateTime(date) ;
					
					//添加增量客服  zoneyu 16-12-8
					if(ValidateUtils.isBlank(user.getServiceBegin()) && !ValidateUtils.isBlank(user.getCity())){
						List<Long> userList = mapper.findStaffPositionByCity(user.getCity(),2) ;
						if(!ValidateUtils.isEmptyForCollection(userList)){
							int i = userList.size() > 1 ? new Random().nextInt(userList.size()-1) : 0;
							user.setServiceBegin(userList.get(i));
							mapper.update(user) ;
						}
					}
					DataUtils.trim(user);
					mapper.insert(user) ;
					rs.put("info", user.getId()) ;
					
					List<BeanUseIdentity> list = new ArrayList<BeanUseIdentity>() ;
					//添加身份
					if(!ValidateUtils.isBlank(personalPosition)){//个人身份
						String[] dns = personalPosition.split(",") ;
						for(String ds : dns){
							BeanUseIdentity bean = new BeanUseIdentity() ;
							bean.setCreateTime(date);
							bean.setTagIdentity(ds);
							bean.setTagType("个人身份");
							bean.setUid(user.getId());
							list.add(bean) ;
						}
					}
					//身份
					if(!ValidateUtils.isBlank(dnPosition)){//动能身份
						String[] dns = dnPosition.split(",") ;
						for(String ds : dns){
							BeanUseIdentity bean = new BeanUseIdentity() ;
							bean.setCreateTime(date);
							bean.setTagIdentity(ds);
							bean.setTagType("动能身份");
							bean.setUid(user.getId());
							list.add(bean) ;
						}
					}
					if(!ValidateUtils.isEmptyForCollection(list)) userIdentityMapper.inserts(list) ;
				}
			}
		}else{
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("参数错误") ;
		}
		return rs ;
	}

	/**
	  * 获取蓝币跟红币
	  * @author zoneyu 16-10-11
	  */
	public Result getCurency(Long uid, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(uid)){
			BeanUser bean = mapper.findById(uid) ;
			if(!ValidateUtils.isBlank(bean)){
				Map<String,Object> map = new HashMap<String,Object>() ;
				map.put("blueCurrency", bean.getBlueCurrency()) ;
				map.put("redCurrency", bean.getRedCurrency()) ;
				rs.put("info", map) ;
			}else{
				rs.setStatus(MSG.ERROR.getStatus()) ;
				rs.setMsg("用户不存在") ;
			}
		}else{
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("参数错误") ;
		}
		return rs ;
	}

	/**
	 * 获取个人信息
	 */
	@Override
	public Result getUserInfo(Long id ,String date, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(id)){
			Map<String,Object> map = mapper.findUserInfo(id);
			map.put("grPosition", ValidateUtils.isBlank(map.get("grPosition")) ? new String[]{} : ((String)map.get("grPosition")).split(","));
			map.put("dnPosition", ValidateUtils.isBlank(map.get("dnPosition")) ? new String[]{} : ((String)map.get("dnPosition")).split(","));
			map.put("directArea", ValidateUtils.isBlank(map.get("directArea")) ? new String[]{} : ((String)map.get("directArea")).split(","));
			map.put("indirectArea", ValidateUtils.isBlank(map.get("indirectArea")) ? new String[]{} : ((String)map.get("indirectArea")).split(","));
			rs.put("info", map);
		}else{
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("参数错误") ;
		}
		return rs;
	}

	/**
	 * 更新个人信息
	 */
	@Override
	public Result updateUser(BeanUser bean, String grPosition,
			String dnPosition, Result rs) throws Exception {
		DataUtils.trim(bean);
		if(!ValidateUtils.isBlank(bean.getId())){
			/*if(!ValidateUtils.isBlank(bean.getIdCard())){
				BeanUser user = new BeanUser();
				user.setIdCard(bean.getIdCard());
				List<BeanUser> list = mapper.findAll(user);
				if(!ValidateUtils.isEmptyForCollection(list) && list.get(0).getId() - bean.getId() != 0){
					rs.setStatus(MSG.ERROR.getStatus());
					rs.setMsg("该身份证号已存在，请核对在输入");
					return rs;
				}
			}*/
			if(bean.getState() - 3 == 0){
				bean.setState(2);
				bean.setCreateTime(new Date());
			}
			DataUtils.trim(bean) ;
			mapper.update(bean);
			
			//先删除之前身份
			BeanUseIdentity ui = new BeanUseIdentity();
			ui.setUid(bean.getId());
			userIdentityMapper.deleteByEntity(ui);
			
			//添加身份
			List<BeanUseIdentity> list = new ArrayList<BeanUseIdentity>();
			Date date = new Date();
			if(!ValidateUtils.isBlank(grPosition)){//个人身份
				String[] dns = grPosition.split(",") ;
				for(String s : dns){
					BeanUseIdentity i = new BeanUseIdentity();
					i.setCreateTime(date);
					i.setTagIdentity(s);
					i.setTagType("个人身份");
					i.setUid(bean.getId());
					list.add(i);
				}
			}
			if(!ValidateUtils.isBlank(dnPosition)){//动能身份
				String[] dns = dnPosition.split(",") ;
				for(String s : dns){
					BeanUseIdentity i = new BeanUseIdentity();
					i.setCreateTime(date);
					i.setTagIdentity(s);
					i.setTagType("动能身份");
					i.setUid(bean.getId());
					list.add(i);
				}
			}
			if(!ValidateUtils.isEmptyForCollection(list)) userIdentityMapper.inserts(list) ;
		}else{
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("参数错误");
		}
		return rs;
	}

	/**
	 * 获取动能家人
	 */
	@Override
	public Result getRelationList(Long id, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(id)){
			rs.put("info", mapper.getRelationList(id));
		}else{
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("参数错误");
		}
		return rs;
	}

	/**
	 * 更新动能贵人
	 */
	@Override
	public Result updateRelation(Long id, String inviteCode, Result rs)
			throws Exception {
		if(!ValidateUtils.isBlank(id) && !ValidateUtils.isBlank(inviteCode)){
			BeanUser user = new BeanUser();
			user.setInviteCode(inviteCode);
			List<BeanUser> list = mapper.findAll(user);
			if(!ValidateUtils.isEmptyForCollection(list)){
				user = mapper.findById(id);
				if(!inviteCode.equals(user.getInviteCode())){
					String uidFrom = list.get(0).getId()+"";
					Map<String, Object> map = mapper.getRelationList(id);
					String uid = (String) map.get("uid1") +","+ (String) map.get("uid2") +","+ (String) map.get("uid3");
					boolean bool = true;
					if(!ValidateUtils.isBlank(uid)){
						List<String> l = Arrays.asList(uid.split(","));
						if(l.contains(uidFrom)) bool = false;
					}
					if(bool){
						user.setUidFrom(Long.valueOf(uidFrom));
						mapper.update(user);
					}else{
						rs.setStatus(MSG.ERROR.getStatus());
						rs.setMsg("您的贵人不能是下级关系，请核对");
					}
				}else{
					rs.setStatus(MSG.ERROR.getStatus());
					rs.setMsg("您的贵人不能是自己");
				}
			}else{
				rs.setStatus(MSG.ERROR.getStatus());
				rs.setMsg("邀请码不存在，请核对邀请码");
			}
		}else{
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("参数错误");
		}
		return rs;
	}

	/**
	  * 省市区
	  * @author zoneyu 16-11-2
	  */
	public Result getAddress(Integer type, String pid, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(type)){
			List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>() ;
			List<Map<String,Object>> list = null ;
			if(type == 1){//省
				list = mapper.getProvince() ;
				if(!ValidateUtils.isBlank(list)){
					Map<String, Object> m = list.get(0) ;
					String code = (String)m.get("code") ;
					Map<String,Object> dataMap = new HashMap<String, Object>() ;
					List<String> childList = new ArrayList<String>() ;
					dataMap.put(code, childList) ;
					for(Map<String,Object> map : list){
						String code2 = (String)map.get("code") ;
						String id2 = (String) map.get("id") ;
						if(code.equals(code2)){
							childList.add(id2) ;
						}else{
							dataList.add(dataMap) ;
							childList = new ArrayList<String>() ;
							childList.add(code2) ;
							dataMap.put(code, childList) ;
							code = code2 ;
						}
					}
					dataList.add(dataMap) ;
					list = dataList ;
				}
			}else if(type == 2){//市
				list = mapper.getCity(pid) ;
				Map<String, Object> m = list.get(0) ;
				String code = (String)m.get("code") ;
				Map<String,Object> dataMap = new HashMap<String, Object>() ;
				List<String> childList = new ArrayList<String>() ;
				dataMap.put(code, childList) ;
				for(Map<String,Object> map : list){
					String code2 = (String)map.get("code") ;
					String id2 = (String) map.get("id") ;
					if(code.equals(code2)){
						childList.add(id2) ;
					}else{
						dataList.add(dataMap) ;
						dataMap = new HashMap<String, Object>() ;
						childList = new ArrayList<String>() ;
						childList.add(id2) ;
						dataMap.put(code2, childList) ;
						code = code2 ;
					}
				}
				dataList.add(dataMap) ;
				list = dataList ;
			}else if(type == 3){//区
				list = mapper.getDistrict(pid) ;
			}
			rs.put("info", list) ;
		}else{
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("参数错误");
		}
		return rs;
	}

	/**
	  * 省市区(全部数据)
	  * @author zoneyu 16-11-7
	  */
	public Result getAddressList(Result rs) throws Exception {
		List<Map<String, Object>> province = mapper.getProvince() ;
		List<Map<String, Object>> city = mapper.getCityAll() ;
		List<Map<String, Object>> district = mapper.getDistrictAll() ;
		if(!ValidateUtils.isEmptyForCollection(province) && !ValidateUtils.isEmptyForCollection(city)
				&& !ValidateUtils.isEmptyForCollection(district)){
			List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>() ;
			for(Map<String,Object> map : province){
				Map<String,Object> provinceMap = new HashMap<String, Object>() ;
				String pname = (String) map.get("id") ;
				provinceMap.put("name", pname) ;
				List<Map<String,Object>> cityList = new ArrayList<Map<String,Object>>() ;
				for(Map<String,Object> cmap : city){
					Map<String,Object> cityMap = new HashMap<String, Object>() ;
					String cname = (String) cmap.get("id") ;
					String cid = (String) cmap.get("pid") ;
					if(cid.equals(pname)){
						cityMap.put("name", cname) ;
						List<Map<String,Object>> districtList = new ArrayList<Map<String,Object>>() ;
						for(Map<String,Object> dmap : district){
							Map<String,Object> districtMap = new HashMap<String, Object>() ;
							String dname = (String) dmap.get("id") ;
							String did = (String) dmap.get("pid") ;
							if(did.equals(cname)){
								districtMap.put("name", dname) ;
								districtList.add(districtMap) ;
							}
						}
						cityMap.put("child", districtList) ;
						cityList.add(cityMap) ;
					}
				}
				provinceMap.put("child", cityList) ;
				dataList.add(provinceMap) ;
			}
			rs.put("info", dataList) ;
		}
		return rs ;
	}

	/**
	  * 注册--分享后的注册
	  * @author zoneyu 16-11-9
	  */
	public Result registerWithCode(BeanUser user, String code, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(user.getUserName()) && !ValidateUtils.isBlank(user.getPwd()) 
				&& !ValidateUtils.isBlank(code) && !ValidateUtils.isBlank(user.getInviteCode())){
			BeanPhoneCode phoneCode = new BeanPhoneCode() ;
			phoneCode.setPhone(user.getUserName()) ;
			phoneCode.setCode(code) ;
			int count = phoneCodeMapper.findByPageCount(phoneCode) ;
			if(count == 0){
				rs.setStatus(MSG.ERROR.getStatus()) ;
				rs.setMsg("验证码失效或错误") ;
			}else{
				BeanUser u = new BeanUser() ;
				u.setUserName(user.getUserName()) ;
				count = mapper.findByPageCount(u) ;
				if(count > 0){
					rs.setStatus(MSG.ERROR.getStatus()) ;
					rs.setMsg("该手机号已存注册了，请直接登录") ;
				}else{
					//填写邀请人
					if(!ValidateUtils.isBlank(user.getInviteCode())){
						BeanUser ur = new BeanUser() ;
						ur.setInviteCode(user.getInviteCode());
						ur = mapper.findEntityByCondition(ur) ;
						if(!ValidateUtils.isBlank(ur)){
							user.setUidFrom(ur.getId());
						}
					}
					user.setState(1);//有效
					//生成自己的邀请码
					String inviteCode = DataUtils.getRadom(9) ;
					boolean b = true ;
					while(b){
						BeanUser ur = new BeanUser() ;
						ur.setInviteCode(inviteCode);
						int counts = mapper.findByPageCount(ur);
						if(counts == 0){
							user.setInviteCode(inviteCode);
							b = false ;
						}else{
							inviteCode = DataUtils.getRadom(9) ;
						}
					}
					user.setCreateTime(new Date()) ;
					mapper.insert(user) ;
					rs.put("info", user.getId()) ;
				}
			}
		}else{
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("参数错误") ;
		}
		return rs ;
	}

	 /**
	  * 查询下级
	  * @author zoneyu 16-11-10
	  */
	public Result searchchildList(Long uid, Integer grade,Integer page, Result rs) throws Exception {
		page = ValidateUtils.isBlank(page) ? PAGE : page ;
		if(!ValidateUtils.isBlank(uid) && !ValidateUtils.isBlank(grade)){
			List<Map<String,Object>> list = mapper.searchchildList(uid,grade,(page-1)*ROWS,ROWS) ;
			rs.put("info", list) ;
		}else{
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("参数错误") ;
		}
		return rs ;
	}

	/**
	 * 我的分佣
	 */
	@Override
	public Result searchCentCommission(Long uid, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(uid)){
			Map<String, Object> map = mapper.findCentCommission(uid);
			Map<String, Object> map1 = mapper.getRelationList(uid);
			map.put("count1", map1.get("count1"));
			map.put("count2", map1.get("count2"));
			map.put("count3", map1.get("count3"));
			rs.put("info", map);
		}else{
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("参数错误");
		}
		return rs;
	}

	/**
	 * 分佣明细
	 */
	@Override
	public Result searchCentCommissionDetail(Long uid, String type,
			Integer page, Result rs) throws Exception {
		page = ValidateUtils.isBlank(page) ? PAGE : page ;
		if(!ValidateUtils.isBlank(uid)){
			List<Map<String,Object>> list = mapper.searchCentCommissionDetail(uid,type,(page-1)*ROWS,ROWS);
			rs.put("info", list);
		}else{
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("参数错误");
		}
		return rs;
	}

	/**
	 * 转账
	 */
	@Override
	public Result saveTransferAccounts(String userName, Long currency,
			Long uid, String pwd, Result rs) {
		if(!ValidateUtils.isBlank(userName) && !ValidateUtils.isBlank(currency) && 
				!ValidateUtils.isBlank(uid) && !ValidateUtils.isBlank(pwd)){
			if(currency <= 0){
				rs.setStatus(MSG.ERROR.getStatus());
				rs.setMsg("金额有误，必须大于0");
			}
			BeanUser user = new BeanUser();
			user.setUserName(userName);
			List<BeanUser> list = mapper.findAll(user);
			if(!ValidateUtils.isEmptyForCollection(list)){
				user = list.get(0);
				if(user.getId() - uid != 0){
					String position = user.getPosition() ;
					if("动能集团学员,其它".contains(position)){
						rs.setStatus(MSG.ERROR.getStatus());
						rs.setMsg("对方为学员/其他身份，不能转账红币");
						return rs ;
					}
					user.setRedCurrency(user.getRedCurrency().add(new BigDecimal(currency)));
				}else{
					rs.setStatus(MSG.ERROR.getStatus());
					rs.setMsg("不能给自己转账");
				}
			}else{
				rs.setStatus(MSG.ERROR.getStatus());
				rs.setMsg("该手机号不存在");
			}
			BeanUserPayPassword password = new BeanUserPayPassword();
			password.setUid(uid);
			password.setPwd(pwd);
			if(10001 == rs.getStatus() && ValidateUtils.isEmptyForCollection(payPasswordMapper.findAll(password))){
				rs.setStatus(MSG.ERROR.getStatus());
				rs.setMsg("支付密码不正确");
			}
			BeanUser bean = mapper.findById(uid);
			if(10001 == rs.getStatus()){
				if(bean.getRedCurrency().compareTo(new BigDecimal(currency)) >= 0){
					bean.setRedCurrency(bean.getRedCurrency().subtract(new BigDecimal(currency)));
				}else{
					rs.setStatus(MSG.ERROR.getStatus());
					rs.setMsg("红币不足");
				}
			}
			
			if(10001 == rs.getStatus()){
				//转出，减少
				BeanUserCurrency userCurrency = new BeanUserCurrency();
				userCurrency.setRecordType(8);
				userCurrency.setCreateTime(new Date());
				userCurrency.setCurrencyType(2);
				userCurrency.setUid(uid);
				userCurrency.setCurrency(new BigDecimal(currency).multiply(new BigDecimal(-1)));
				userCurrency.setContent("转入账户 "+userName);
				currencyMapper.insert(userCurrency);
				
				//转入，增加
				BeanUserCurrency userCurrency2 = new BeanUserCurrency();
				userCurrency2.setRecordType(8);
				userCurrency2.setCreateTime(new Date());
				userCurrency2.setCurrencyType(2);
				userCurrency2.setUid(user.getId());
				userCurrency2.setCurrency(new BigDecimal(currency));
				userCurrency2.setContent("转出账户 "+bean.getUserName());
				currencyMapper.insert(userCurrency2);
				
				mapper.update(user);//增加
				mapper.update(bean);//减少
			}
		}else{
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("参数错误");
		}
		return rs;
	}

	/**
	 * 分享注册
	 */
	@Override
	public String registerShare(Long id) {
		return mapper.findById(id).getInviteCode();
	}

	/**
	  * 获取设置内容
	  * @author zoenyu 16-12-2
	  */
	public Result getSetting(String itemName, Result rs) throws Exception {
		BeanSetting bean = new BeanSetting() ;
		bean.setItemName(itemName);
		bean.setItemType("service");
		List<BeanSetting> list = settingMapper.findByPage(bean, 0, 1);
		if(!ValidateUtils.isEmptyForCollection(list)){
			BeanSetting set = list.get(0) ;
			rs.put("info", set.getValue()) ;
		}else{
			rs.put("info", "") ;
		}
		return rs ;
	}

	/**
	  * 评测结果
	  * @author zoenyu 16-12-6
	  */
	public Result getResult(String answers, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(answers)){
			Map<String,Object> dataMap = new HashMap<String, Object>() ;
			List<Integer> list = new ArrayList<Integer>() ;
			String[] answerses = answers.split(",") ;
			if(answerses.length != 32){
				rs.setStatus(MSG.ERROR.getStatus());
				rs.setMsg("题目未答完");
			}else{
				int sum = 0 ;
				int total = 0 ;
				Map<String, Integer> map = getMap() ;
				for(int i = 1 ; i <= answerses.length ; i++){
					total += map.get(answerses[i-1]) ;
					if(i % 4 == 0){
						int SUM = sum + map.get(answerses[i-1]) ;
						list.add(SUM) ;
						sum = 0 ;
					}else{
						sum += map.get(answerses[i-1]) ;
					}
				}
				dataMap.put("result", DataUtils.list2String(list)) ;
				StringBuilder sb = new StringBuilder("经测评，您的家庭为") ;
				if(total >= 8 && total <= 40){
					sb.append("不合格家庭") ;
				}else if(total >= 41 && total <= 80){
					sb.append("合格家庭") ;
				}else if(total >= 81 && total <= 120){
					sb.append("优秀家庭") ;
				}else if(total >= 121 && total <= 160){
					sb.append("卓越家庭") ;
				}
				dataMap.put("msg", sb.toString()) ;
				rs.put("info", dataMap) ;
			}
		}else{
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("参数错误");
		}
		return rs;
	}
	
	/**
	 * 推荐课程初始化
	 */
	private Map<Integer,String> getCourse(){
		Map<Integer,String> map = new HashMap<Integer,String>() ;
		map.put(0, "少年演讲家学习动力营,合格父母动能特训班") ;
		map.put(1, "欧爸教育与演讲之道,动能系统特训班") ;
		map.put(2, "幸福家庭爱能赢,合格父母动能特训班") ;
		map.put(3, "动能陪伴师派遣,教育专员培训") ;
		map.put(4, "父母导师班,合格父母动能特训班") ;
		map.put(5, "动能系统特训班,我是欧爸·动能贵人之道") ;
		map.put(6, "欧爸父母大学(微课)家族群,合格父母动能特训班") ;
		map.put(7, "父母导师班,教育专员培训") ;
		return map ;
	}

	/**
	 * 初始化
	 */
	private Map<String,Integer> getMap(){
		Map<String,Integer> map = new HashMap<String, Integer>() ;
		map.put("A", 5) ;
		map.put("B", 3) ;
		map.put("C", 1) ;
		return map ;
	}

	/**
	  * 推荐课程
	  * @author zoenyu 16-12-6
	  */
	public Result getCourse(String result, Result rs) throws Exception {
		//查询推荐课程
		String[] results = result.split(",") ;
		StringBuilder sbr = new StringBuilder() ;
		for(int i = 0 ; i < results.length ; i++){
			String s = results[i] ;
			Integer in = Integer.valueOf(s) ;
			Map<Integer, String> courseMap = getCourse() ;
			if(in < 15){
				sbr.append(courseMap.get(i)).append(",") ;
			}
		}
		if(!ValidateUtils.isBlank(sbr.toString())){
			String name = sbr.toString() ;
			name = name.substring(0, name.length() - 1) ;
			List<String> nemes = DataUtils.string2ListString(name) ;
			rs.put("info",courseOfflineMapper.getCOurse(nemes));
		}
		return rs ;
	}

	/**
	  * 支付控制
	  * @author zoenyu 16-12-10
	  */
	public Result getPaySet(Result rs) throws Exception {
		rs.put("info", mapper.getPaySet()) ;
		return rs ;
	}

	/**
	 * 获取版本号
	 */
	@Override
	public Result getVersion(Result rs) throws Exception {
		List<BeanaAppVersions> list = versionsMapper.findAll(new BeanaAppVersions());
		rs.put("info", list.get(0));
		return rs;
	}

	@Override
	public Result usp(Result rs) throws Exception {
		BeanUseIdentity bui = new BeanUseIdentity();
		bui.setTagType("员工岗位");
		userIdentityMapper.deleteByEntity(bui);
		List<Map<String,Object>> list = mapper.getStaffPosition();
		if(!ValidateUtils.isEmptyForCollection(list)){
			List<BeanUseIdentity> uiList = new ArrayList<BeanUseIdentity>();
			Date date = new Date();
			for (Map<String,Object> map : list) {
				BeanUseIdentity ui = new BeanUseIdentity();
				ui.setCreateTime(date);
				ui.setTagType("员工岗位");
				ui.setUid((Long)map.get("id"));
				ui.setTagIdentity((String) map.get("staffPosition"));
				uiList.add(ui);
			}
			if(!ValidateUtils.isEmptyForCollection(uiList)) identityMapper.inserts(uiList);
		}
		return rs;
	}

}
