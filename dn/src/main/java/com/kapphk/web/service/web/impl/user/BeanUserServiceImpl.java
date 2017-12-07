package com.kapphk.web.service.web.impl.user;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kapphk.web.bean.tag.BeanUseIdentity;
import com.kapphk.web.bean.user.BeanUser;
import com.kapphk.web.bean.user.BeanUserArea;
import com.kapphk.web.bean.user.BeanUserCurrency;
import com.kapphk.web.bean.user.BeanUserFee;
import com.kapphk.web.dao.mapper.tag.BeanUseIdentityMapper;
import com.kapphk.web.dao.mapper.user.BeanUserAreaMapper;
import com.kapphk.web.dao.mapper.user.BeanUserCurrencyMapper;
import com.kapphk.web.dao.mapper.user.BeanUserFeeMapper;
import com.kapphk.web.dao.mapper.user.BeanUserMapper;
import com.kapphk.web.service.web.imethod.user.BeanUserService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.DateUtils;
import com.kapphk.web.utils.ExcelUtils;
import com.kapphk.web.utils.FileUploadUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.MD5;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;
import com.kapphk.web.utils.Result.MSG;
@Service
public class BeanUserServiceImpl implements BeanUserService {

	@Autowired
	private BeanUserMapper mapper;
	
	@Autowired
	private BeanUseIdentityMapper identityMapper;
	
	@Autowired
	private BeanUserFeeMapper feeMapper;
	
	@Autowired
	private BeanUserAreaMapper areaMapper;
	
	@Autowired
	private BeanUserCurrencyMapper currencyMapper;
	
	/*************************** 列表  ***********************************/
	
	/**
	 * 查询列表
	 */
	@Override
	public Result searchList(String staffPosition,String startTime,String endTime,Result rs, BeanUser bean, Integer type, String yqRealName, GridReq gridReq) throws Exception {
		DataUtils.trim(bean);
		List<Map<String,Object>> list = mapper.findList(staffPosition,startTime,endTime,bean,type,yqRealName,gridReq.getPage(),gridReq.getRows());
		int count = mapper.findCount(staffPosition,startTime,endTime,bean,type,yqRealName);
		rs = DataUtils.mergeData(count, list, rs);
		return rs;
	}
	
	/**
	 * 用户关系列表
	 */
	@Override
	public Result searchUserRelation(Result rs, String position,String userName,String realName,
			String yqRealName,String khRealName,Long id,GridReq gridReq)throws Exception {
		int count = mapper.findUserRelationCount(position,userName,realName,yqRealName,khRealName,id);
		List<Map<String,Object>> list = mapper.findUserRelationList(position,userName,realName,yqRealName,khRealName,id,gridReq.getPage(),gridReq.getRows());
		rs = DataUtils.mergeData(count, list, rs);
		return rs;
	}
	
	/**
	 * 客服管理数据
	 */
	@Override
	public Result searchServiceList(Result rs, String province, String city,
			String userName, String realName, HttpServletRequest request, GridReq gridReq) throws Exception {
		String roleValue = (String) request.getSession().getAttribute("role_value");
		Long userId = "ROLE_SUPER_ADMIN".equals(roleValue) ? null : (Long) request.getSession().getAttribute("userId");
		int count = mapper.findServiceCount(province,city,userName,realName,userId);
		List<Map<String,Object>> list = mapper.findServiceList(province,city,userName,realName,userId,gridReq.getPage(),gridReq.getRows());
		rs = DataUtils.mergeData(count, list, rs);
		return rs;
	}
	
	
	/*************************** 列表  ***********************************/

	
	
	
	
	/**
	 * 保存
	 */
	@Override
	public Result saveData(String sjInviteCode,Result rs, BeanUser bean, MultipartFile file,
			HttpServletRequest request, List<String> grPosition, List<String> dnPosition) throws Exception {
		DataUtils.trim(bean);
		if(file != null && !file.isEmpty()){
			byte[] bytes = file.getBytes();
			String logPath = FileUploadUtils.uploadFile(bytes,"upload/public", "1", request);
			bean.setLogoPath(logPath);
		}
		if(!ValidateUtils.isBlank(sjInviteCode)){
			rs = updateRelation(bean.getId(), sjInviteCode, rs);
			if(rs.getStatus() - 20001 == 0) return rs;
		}
		
		if(ValidateUtils.isBlank(bean.getId())){
			bean.setCreateTime(new Date());
			mapper.insert(bean);
		}else{
			mapper.update(bean);
		}
		
		//先删除个人身份与动能身份
		BeanUseIdentity ui = new BeanUseIdentity();
		ui.setUid(bean.getId());
		identityMapper.deleteByEntity(ui);
		
		Date date = new Date();
		List<BeanUseIdentity> list = new ArrayList<BeanUseIdentity>();
		if(!ValidateUtils.isEmptyForCollection(grPosition)){
			for (String s : grPosition) {
				BeanUseIdentity i = new BeanUseIdentity();
				i.setUid(bean.getId());
				i.setTagType("个人身份");
				i.setTagIdentity(s);
				i.setCreateTime(date);
				list.add(i);
			}
		}
		if(!ValidateUtils.isEmptyForCollection(dnPosition)){
			for (String s : dnPosition) {
				BeanUseIdentity i = new BeanUseIdentity();
				i.setUid(bean.getId());
				i.setTagType("动能身份");
				i.setTagIdentity(s);
				i.setCreateTime(date);
				list.add(i);
			}
		}
		
		if(!ValidateUtils.isEmptyForCollection(list))identityMapper.inserts(list);
		
		return rs;
	}

	/**
	 * 详情
	 */
	@Override
	public Result getData(Result rs, BeanUser bean) throws Exception {
		rs.put("data", mapper.findDetailById(bean.getId()));
		return rs;
	}

	/**
	 * 删除
	 */
	@Override
	public Result delete(Result rs, List<Long> ids) throws Exception {
		rs.put("count", mapper.deletes(ids));
		return rs;
	}

	/**
	 * 省市区
	 */
	@Override
	public List<Map<String,Object>> getPcd(String id, Integer type, String status) throws Exception {
		List<Map<String, Object>> list = mapper.findPcd(id,type);
		if(!ValidateUtils.isBlank(status)){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("id", "");
			map.put("text", "全部");
			list.add(0, map);
		}
		return list;
	}

	/**
	 * 暂停或使用
	 */
	@Override
	public Result upstatus(Result rs, List<Long> ids, Integer state)
			throws Exception {
		if(!ValidateUtils.isEmptyForCollection(ids) && !ValidateUtils.isBlank(state)){
			mapper.upstatus(ids,state);
		}
		return rs;
	}

	/**
	 * 保存升级身份
	 */
	@Override
	public Result saveIdentity(Result rs, BeanUser user, String identity,
			BigDecimal amount,String dueDate1) throws Exception {
		if(!ValidateUtils.isBlank(user.getId()) && !ValidateUtils.isBlank(identity)
				&& !ValidateUtils.isBlank(amount)){
			//删除之前缴纳费用
			BeanUserFee fee = new BeanUserFee();
			fee.setUid(user.getId());
			//feeMapper.deleteByEntity(fee);
			//先删除之前的动能身份 
			BeanUseIdentity useIdentity = new BeanUseIdentity();
			useIdentity.setTagType("动能身份");
			useIdentity.setUid(user.getId());
			identityMapper.deleteByEntity(useIdentity);
			//更新用户信息
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			user.setDueDate(format.parse(dueDate1));
			mapper.update(user);
			user = mapper.findById(user.getId());
			//保存缴纳费用
			fee.setRemark(identity);
			fee.setAmount(amount);
			fee.setCreateTime(new Date());
			feeMapper.insert(fee);
			Date date = new Date();
			List<BeanUseIdentity> list = new ArrayList<BeanUseIdentity>();
			//保存身份
			for (String str : identity.split(",")) {
				BeanUseIdentity ui = new BeanUseIdentity();
				ui.setUid(user.getId());
				ui.setTagIdentity(str);
				ui.setTagType("动能身份");
				ui.setCreateTime(date);
				list.add(ui);
			}
			
			//升级身份获得分佣
			if(!ValidateUtils.isBlank(user.getUidFrom())){
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				String endDate = DateUtils.getLocalYmdDate();
				BeanUser beanUser = mapper.findById(user.getUidFrom());
				if(beanUser.getPosition().equals("动能集团员工") || (beanUser.getPosition().equals("动能集团合作伙伴")
						&& sf.format(beanUser.getDueDate()).compareTo(endDate) >= 0)){
					int totalAmount = (int) (amount.intValue()*mapper.findCommissionByuid(beanUser.getId()));
					beanUser.setRedCurrency(beanUser.getRedCurrency().add(new BigDecimal(totalAmount)));
					mapper.update(beanUser);
				}
			}
			
			if(!ValidateUtils.isEmptyForCollection(list))identityMapper.inserts(list);
		}else{
			rs.setStatus(Contents.ERROR);
			rs.setMsg("参数有误");
		}
		return rs;
	}

	/**
	 * 审核
	 */
	@Override
	public Result saveState(Result rs, List<Long> asList, Integer state,
			String rejectReason, String date) throws Exception {
		if(!ValidateUtils.isEmptyForCollection(asList) && !ValidateUtils.isBlank(state)){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			mapper.saveState(asList,state,rejectReason,ValidateUtils.isBlank(date) ? null : format.parse(date));
		}else{
			rs.setStatus(Contents.ERROR);
			rs.setMsg("参数有误");
		}
		return rs;
	}

	/**
	 * 获取用户
	 */
	@Override
	public List<Map<String, Object>> searchUserList(String staffPosition) {
		return mapper.searchUserList(staffPosition);
	}

	/**
	 * 保存员工
	 */
	@Override
	public Result saveStaff(Result rs, BeanUser bean, MultipartFile file,List<String> directArea,
			 List<String> indirectArea,List<String> staffPosition,HttpServletRequest request)
			throws Exception {
		DataUtils.trim(bean);
		BeanUser user = new BeanUser();
		if(file != null && !file.isEmpty()){
			byte[] bytes = file.getBytes();
			String logPath = FileUploadUtils.uploadFile(bytes,"upload/public", "1", request);
			bean.setLogoPath(logPath);
		}
		
		bean.setPosition("动能集团员工");
		if(ValidateUtils.isBlank(bean.getId())){
			user = new BeanUser();
			user.setUserName(bean.getUserName());
			int count = mapper.findByPageCount(user);
			if(count == 0){
				//生成自己的邀请码
				user = new BeanUser();
				while(true){
					String inviteCode = DataUtils.getRadom(9);
					user.setInviteCode(inviteCode);
					int counts = mapper.findByPageCount(user);
					if(counts == 0){
						bean.setInviteCode(inviteCode);
						break;
					}
				}
				bean.setCreateTime(new Date());
				bean.setPwd(MD5.getMD5("123456"));
				mapper.insert(bean);
			}else{
				rs.setStatus(Contents.ERROR);
				rs.setMsg("注册账号已存在、请更改");
				return rs;
			}
		}else{
			mapper.update(bean);
		}
		
		//删除之前的管辖数据
		BeanUserArea userArea = new BeanUserArea();
		userArea.setUid(bean.getId());
		areaMapper.deleteByEntity(userArea);
		Date date = new Date();
		List<BeanUserArea> list = new ArrayList<BeanUserArea>();
		List<BeanUseIdentity> spList = new ArrayList<BeanUseIdentity>();
		//直接管辖
		if(!ValidateUtils.isEmptyForCollection(directArea)){
			for (String ia : directArea) {
				BeanUserArea ua = new BeanUserArea();
				ua.setProvince(ia);
				ua.setUid(bean.getId());
				ua.setRecordType(1);
				ua.setCreateTime(date);
				list.add(ua);
			}
		}
		//间接管辖
		if(!ValidateUtils.isEmptyForCollection(indirectArea)){
			for (String ia : indirectArea) {
				BeanUserArea ua = new BeanUserArea();
				ua.setProvince(ia);
				ua.setUid(bean.getId());
				ua.setRecordType(2);
				ua.setCreateTime(date);
				list.add(ua);
			}
		}
		//先删除之前的员工身份 
		BeanUseIdentity useIdentity = new BeanUseIdentity();
		useIdentity.setTagType("员工岗位");
		useIdentity.setUid(bean.getId());
		identityMapper.deleteByEntity(useIdentity);
		if(!ValidateUtils.isEmptyForCollection(staffPosition)){
			//保存身份
			for (String str : staffPosition) {
				BeanUseIdentity ui = new BeanUseIdentity();
				ui.setUid(bean.getId());
				ui.setTagIdentity(str);
				ui.setTagType("员工岗位");
				ui.setCreateTime(date);
				spList.add(ui);
			}
		}
		if(!ValidateUtils.isEmptyForCollection(spList))identityMapper.inserts(spList);
		if(!ValidateUtils.isEmptyForCollection(list))areaMapper.inserts(list);
		return rs;
	}
	
	/**
	 * 课程验证
	 */
	@Override
	public Result upIsPermissions(Result rs, BeanUser bean) throws Exception {
		mapper.update(bean);
		return rs;
	}
	
	/**
	 * 保存更改蓝币
	 */
	@Override
	public Result saveCurrency(Result rs, Long id, Integer amount,
			Integer type, String content) throws Exception {
		BeanUser user = mapper.findById(id);
		BigDecimal bd = new BigDecimal(Double.toString(amount));
		if(type - 2 == 0){
			if(user.getBlueCurrency().compareTo(bd) < 0){
				rs.setStatus(Contents.ERROR);
				rs.setMsg("蓝币不足，更改失败");
				return rs;
			}else{
				user.setBlueCurrency(user.getBlueCurrency().subtract(bd));
			}
		}else{
			user.setBlueCurrency(user.getBlueCurrency().add(bd));
		}
		mapper.update(user);
		BeanUserCurrency currency = new BeanUserCurrency();
		currency.setContent(content);
		currency.setRecordType(6);
		currency.setCreateTime(new Date());
		currency.setUid(id);
		currency.setCurrencyType(1);
		currency.setCurrency(type - 2 == 0 ? bd = new BigDecimal(Double.toString(amount*-1)) : bd);
		currencyMapper.insert(currency);
		return rs;
	}
	
	/*************************** 导出  ***********************************/
	
	/**
	 * 导出用户关系
	 */
	@Override
	public void exportUserRelation(HttpServletResponse response,String position,String userName,String realName,
			String yqRealName,String khRealName,Long id) {
		List<Map<String,Object>> list = mapper.findUserRelationList(position,userName,realName,yqRealName,khRealName,id,0,9999999);
		String[] particular = new String[]{"注册账号","真实姓名","动能身份","邀请码","邀请人姓名","邀请人邀请码","分佣层级","客户姓名","客户邀请码","更新时间"};
		String[] property = new String[]{"userName","realName","position","inviteCode","yqRealName","yqInviteCode","hierarchy","khRealName","khInviteCode","createTime"};
		System.out.println(ExcelUtils.downExcelList("用户关系.xlsx", particular, property, list, response));
	}
	
	/**
	 * 导出学员
	 */
	@Override
	public void exportStudent(HttpServletResponse response,String staffPosition,String startTime,
			String endTime,BeanUser bean, String type, String yqRealName) {
		List<Map<String,Object>> list = mapper.findList(staffPosition,startTime,endTime,bean,Integer.valueOf(type),yqRealName,0,9999999);
		String[] particular = new String[]{"用户账号","昵称","真实姓名","个人身份","动能身份","省市区","性别","身份证号","邀请码","邀请人姓名","邀请人邀请码","蓝币","红币","注册时间"};
		String[] property = new String[]{"userName","nickName","realName","grPosition","dnPosition","pcd","sex","idCard","inviteCode","yqRealName","yqInviteCode","blueCurrency","redCurrency","createTime"};
		System.out.println(ExcelUtils.downExcelList("1".equals(type) ? "动能学员.xlsx" : "动能合作伙伴.xlsx", particular, property, list, response));
	}
	
	/**
	 * 导出客服管理
	 */
	@Override
	public void exportService(HttpServletResponse response,HttpServletRequest request,String province,
			String city,String realName,String userName) {
		String roleValue = (String) request.getSession().getAttribute("role_value");
		Long userId = "ROLE_SUPER_ADMIN".equals(roleValue) ? null : (Long) request.getSession().getAttribute("userId");
		List<Map<String,Object>> list = mapper.findServiceList(province,city,userName,realName,userId,0,9999999);
		String[] particular = new String[]{"客服账号","客服姓名","客服身份","客户账号","客户姓名","省市区","最新购买课程","课程状态","报到时间","更新时间"};
		String[] property = new String[]{"userName","realName","position","kuserName","krealName","pcd","itemName","isUse","registerDate","createTime"};
		System.out.println(ExcelUtils.downExcelList("客服管理.xlsx", particular, property, list, response));
	}

	/**
	 * 导出集团员工
	 */
	@Override
	public void exportStaff(HttpServletResponse response,String staffPosition,String startTime,String endTime,
			BeanUser bean,String yqRealName) {
		List<Map<String,Object>> list = mapper.findList(staffPosition,startTime,endTime,bean,3,yqRealName,0,9999999);
		String[] particular = new String[]{"注册账号","昵称","真实姓名","管辖省份","员工岗位","省市区","性别","身份证号","邀请码","蓝币","红币","状态","验证课程","更新时间"};
		String[] property = new String[]{"userName","nickName","realName","directArea","staffPosition","pcd","sex","idCard","inviteCode","blueCurrency","redCurrency","state1","isPermissions1","createTime"};
		System.out.println(ExcelUtils.downExcelList("集团员工.xlsx", particular, property, list, response));
	}

	/*************************** 导出  ***********************************/

	/**
	 * 更新动能贵人
	 */
	public Result updateRelation(Long id, String inviteCode, Result rs)
			throws Exception {
			BeanUser user = new BeanUser();
			user.setInviteCode(inviteCode);
			List<BeanUser> list = mapper.findAll(user);
			if(!ValidateUtils.isEmptyForCollection(list)){
				user = mapper.findById(id);
				if(!inviteCode.equals(user.getInviteCode())){
					if(user.getUidFrom() - list.get(0).getId() == 0) return rs;
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
						rs.setMsg("您的上级不能是下级关系，请核对");
					}
				}else{
					rs.setStatus(MSG.ERROR.getStatus());
					rs.setMsg("您的上级不能是自己");
				}
			}else{
				rs.setStatus(MSG.ERROR.getStatus());
				rs.setMsg("邀请码不存在，请核对邀请码");
			}
		return rs;
	}
}
