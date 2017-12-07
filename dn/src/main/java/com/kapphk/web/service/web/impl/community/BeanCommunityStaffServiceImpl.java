package com.kapphk.web.service.web.impl.community;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.web.bean.community.BeanCommunity;
import com.kapphk.web.bean.community.BeanCommunityStaff;
import com.kapphk.web.bean.community.BeanUserCommunity;
import com.kapphk.web.bean.user.BeanUser;
import com.kapphk.web.dao.mapper.community.BeanCommunityMapper;
import com.kapphk.web.dao.mapper.community.BeanCommunityStaffMapper;
import com.kapphk.web.dao.mapper.community.BeanUserCommunityMapper;
import com.kapphk.web.dao.mapper.user.BeanUserMapper;
import com.kapphk.web.service.web.imethod.community.BeanCommunityStaffService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.ExcelUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;
@Service
public class BeanCommunityStaffServiceImpl implements BeanCommunityStaffService {

	@Autowired
	private BeanCommunityStaffMapper mapper;
	
	@Autowired
	private BeanUserMapper userMapper;
	
	@Autowired
	private BeanCommunityMapper communityMapper;
	
	@Autowired
	private BeanUserCommunityMapper userCommunityMapper;
	/**
	 * 查询列表
	 */
	@Override
	public Result searchList(Result rs, String tagValue, String itemName, String managerPhone,
			String realName, GridReq gridReq) throws Exception {
		List<Map<String,Object>> list = mapper.findList(tagValue,itemName,managerPhone,realName,gridReq.getPage(),gridReq.getRows());
		int count = mapper.findCount(tagValue,itemName,managerPhone,realName);
		rs = DataUtils.mergeData(count, list, rs);
		return rs;
	}

	/**
	 * 保存
	 */
	@Override
	public Result saveData(Result rs, BeanCommunityStaff bean) throws Exception {
		
		BeanUser user = userMapper.findById(bean.getUid());
		BeanCommunity community = new BeanCommunity();
		community.setId(bean.getCommunityId());
		community.setUid(user.getId());
		if(!ValidateUtils.isEmptyForCollection(communityMapper.findAll(community))){
			rs.setStatus(Contents.ERROR);
			rs.setMsg("该用户已是本区长，请更改");
			return rs;
		}
		
		BeanCommunityStaff staff = new BeanCommunityStaff();
		staff.setCommunityId(bean.getCommunityId());
		staff.setUid(bean.getUid());
		List<BeanCommunityStaff> list = mapper.findAll(staff);
		if(!ValidateUtils.isEmptyForCollection(list)){
			if(ValidateUtils.isBlank(bean.getId()) || bean.getId() - list.get(0).getId() != 0){
				rs.setStatus(Contents.ERROR);
				rs.setMsg("该用户已是小组管理人员，请更改");
				return rs;
			}
		}
		
		if(ValidateUtils.isBlank(bean.getId())){
			bean.setCreateTime(new Date());
			bean.setTagType("社区身份");
			mapper.insert(bean);
		}else{
			mapper.update(bean);
		}
		BeanUserCommunity userCommunity = new BeanUserCommunity();
		userCommunity.setCommunityId(bean.getCommunityId());
		userCommunity.setUid(bean.getUid());
		userCommunity.setRecordType(1);
		userCommunityMapper.deleteByEntity(userCommunity);
		userCommunity.setCreateTime(new Date());
		userCommunityMapper.insert(userCommunity);
		return rs;
	}

	/**
	 * 详情
	 */
	@Override
	public Result getData(Result rs, BeanCommunityStaff bean) throws Exception {
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
	 * 获取全部用户
	 */
	@Override
	public List<Map<String, Object>> getUserList(String userName) {
		if(!ValidateUtils.isBlank(userName)){
			return mapper.findUserList(userName);
		}else{
			return new ArrayList<Map<String,Object>>();
		}
	}

	@Override
	public void exportExcel(HttpServletResponse response,String tagValue, String itemName, String managerPhone, String realName) {
		String[] particular = {"小组名称","区长手机号","区长姓名","社区身份","真实姓名","手机号","创建时间"};
		String[] property = {"itemName","managerPhone","managerName","tagValue","realName","userName","createTime"};
		ExcelUtils.downExcelList("社区管理人员.xlsx", particular, property, mapper.findList(tagValue,itemName,managerPhone,realName,0,9999999),response);
	}

}
