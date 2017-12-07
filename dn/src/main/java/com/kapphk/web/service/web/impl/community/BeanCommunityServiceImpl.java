package com.kapphk.web.service.web.impl.community;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kapphk.web.bean.community.BeanCommunity;
import com.kapphk.web.bean.community.BeanCommunityDistrict;
import com.kapphk.web.bean.community.BeanUserCommunity;
import com.kapphk.web.bean.user.BeanUser;
import com.kapphk.web.dao.mapper.community.BeanCommunityDistrictMapper;
import com.kapphk.web.dao.mapper.community.BeanCommunityMapper;
import com.kapphk.web.dao.mapper.community.BeanUserCommunityMapper;
import com.kapphk.web.dao.mapper.user.BeanUserMapper;
import com.kapphk.web.service.web.imethod.community.BeanCommunityService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.ExcelUtils;
import com.kapphk.web.utils.FileUploadUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;
@Service
public class BeanCommunityServiceImpl implements BeanCommunityService {

	@Autowired
	private BeanCommunityMapper mapper;
	
	@Autowired
	private BeanUserMapper userMapper;
	
	@Autowired
	private BeanCommunityDistrictMapper districtMapper;
	
	@Autowired
	private BeanUserCommunityMapper userCommunityMapper;
	/**
	 * 查询列表
	 */
	@Override
	public Result searchList(Result rs, String province, String itemName, String userName,String realName, GridReq gridReq) throws Exception {
		List<Map<String,Object>> list = mapper.findList(province,itemName,userName,realName,gridReq.getPage(),gridReq.getRows());
		Integer count = mapper.findCount(province,itemName,userName,realName);
		rs = DataUtils.mergeData(ValidateUtils.isBlank(count) ? 0 : count, list, rs);
		return rs;
	}

	/**
	 * 保存
	 */
	@Override
	public Result saveData(Result rs, BeanCommunity bean,MultipartFile file,String[] province,
			String city,HttpServletRequest request,String[] imgs) throws Exception {
		Date date = new Date();
		BeanCommunity community = new BeanCommunity();
		community.setRecordType(bean.getRecordType());
		community.setItemName(bean.getItemName());
		List<BeanCommunity> communityList = mapper.findAll(community);
		if(!ValidateUtils.isEmptyForCollection(communityList) && (ValidateUtils.isBlank(bean.getId()) ||
				communityList.get(0).getId() - bean.getId() != 0)){
			rs.setStatus(Contents.ERROR);
			rs.setMsg("该小组名称已存在， 请更改");
			return rs;
		}
		
		//上传图片
		if(!file.isEmpty()){
			try {
				byte[] bytes = file.getBytes();
				String logPath = FileUploadUtils.uploadFile(bytes,"upload/public", "2", request);
				bean.setLogoPath(logPath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(!ValidateUtils.isempty(imgs)){
			if(imgs.length == 3){
				bean.setPhotoPath3(imgs[2]);
				bean.setPhotoPath2(imgs[1]);
				bean.setPhotoPath1(imgs[0]);
			}else if(imgs.length == 2){
				bean.setPhotoPath2(imgs[1]);
				bean.setPhotoPath1(imgs[0]);
			}else{
				bean.setPhotoPath1(imgs[0]);
			}
		}
		//如果是个人社区验证用户
		boolean b = false;
		if(ValidateUtils.isBlank(bean.getId())){
			if(bean.getRecordType() - 1 == 0){
				BeanUser user = new BeanUser();
				user.setUserName(bean.getUserName());
				user.setPosition("动能集团合作伙伴");
				List<BeanUser> list = userMapper.findAll(user);
				if(ValidateUtils.isEmptyForCollection(list)){
					rs.setStatus(Contents.ERROR);
					rs.setMsg("该用户不存在， 只能是合作伙伴账号");
					return rs;
				}
				bean.setUid(list.get(0).getId());
				//判断是否存在社区
				community.setRecordType(bean.getRecordType());
				community.setUid(bean.getUid());
				community.setItemName(null);
				if(ValidateUtils.isEmptyForCollection(mapper.findAll(community))) b = true;
			}else{
				bean.setUid(-1l);
			}
			bean.setCreateTime(date);
			mapper.insert(bean);
		}
		
		//删除服务地区
		BeanCommunityDistrict district = new BeanCommunityDistrict();
		district.setCommunityId(bean.getId());
		districtMapper.deleteByEntity(district);
		List<BeanCommunityDistrict> districtList = new ArrayList<BeanCommunityDistrict>();
		if(bean.getRecordType() - 1 ==  0){
			district = new BeanCommunityDistrict();
			district.setCommunityId(bean.getId());
			district.setProvince(province[0]);
			district.setCity(city);
			district.setCreateTime(date);
			districtList.add(district);
		}else{
			if(!ValidateUtils.isempty(province)){
				for (String s : province) {
					BeanCommunityDistrict d = new BeanCommunityDistrict();
					d.setProvince(s);
					d.setCreateTime(date);
					d.setCommunityId(bean.getId());
					districtList.add(d);
				}
			}
		}
		if(!ValidateUtils.isEmptyForCollection(districtList)) districtMapper.inserts(districtList);
		
		if(ValidateUtils.isBlank(bean.getId())){
			//不存在社区时
			if(b){
				//获取一级客户数
				List<Long> userList = mapper.findUsersById(bean.getUid());
				//删除一级客户跟从的社区
				userCommunityMapper.deleteByuids(ValidateUtils.isEmptyForCollection(userList) ? null : userList,bean.getUid());
				List<BeanUserCommunity> ucl = new ArrayList<BeanUserCommunity>();
				if(!ValidateUtils.isEmptyForCollection(userList)){
					for (Long id : userList) {
						BeanUserCommunity userCommunity = new BeanUserCommunity();
						userCommunity.setCommunityId(bean.getId());
						userCommunity.setRecordType(2);
						userCommunity.setUid(id);
						userCommunity.setCreateTime(date);
						ucl.add(userCommunity);
					}
				}
				//添加社区成员
				if(!ValidateUtils.isEmptyForCollection(ucl)) userCommunityMapper.inserts(ucl);
			}
		}else{
			mapper.update(bean);
		}
		return rs;
	}

	/**
	 * 详情
	 */
	@Override
	public Result getData(Result rs, BeanCommunity bean) throws Exception {
		Map<String, Object> map = mapper.findDetailById(bean.getId());
		String logoPaths = "";
		if(!ValidateUtils.isBlank(map.get("photoPath1")))logoPaths += "," + map.get("photoPath1");
		if(!ValidateUtils.isBlank(map.get("photoPath2")))logoPaths += "," + map.get("photoPath2");
		if(!ValidateUtils.isBlank(map.get("photoPath3")))logoPaths += "," + map.get("photoPath3");
		map.put("logoPaths", logoPaths.length() >0 ? logoPaths.substring(1) : "");
		rs.put("data", map);
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
	 * 获取员工姓名
	 */
	@Override
	public Result getUserDetail(Result rs, BeanUser bean) throws Exception {
		List<BeanUser> list = userMapper.findAll(bean);
		if(!ValidateUtils.isEmptyForCollection(list) && "动能集团合作伙伴".equals(list.get(0).getPosition())){
			rs.put("data", list.get(0));
		}else{
			rs.setStatus(Contents.ERROR);
			rs.setMsg("该用户不存在，请更改");
		}
		return rs;
	}

	/**
	 * 获取全部小组
	 */
	@Override
	public List<Map<String, Object>> getCommunityList() {
		return mapper.findCommunityList();
	}

	@Override
	public void exportExcel(HttpServletResponse response,String province, String itemName, String userName,String realName) {
		String[] particular = {"小组类别","服务地区","小组名称","区长手机号","区长姓名","帖子数","成员数","创建时间"};
		String[] property = {"recordType","district","itemName","userName","realName","contents","members","createTime"};
		ExcelUtils.downExcelList("社区小组.xlsx", particular, property, mapper.findList(province,itemName,userName,realName,0,9999999),response);
	}

}
