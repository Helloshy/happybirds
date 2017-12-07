package com.kapphk.web.service.web.impl.course;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kapphk.web.bean.course.BeanCourseContact;
import com.kapphk.web.bean.user.BeanUser;
import com.kapphk.web.dao.mapper.course.BeanCourseContactMapper;
import com.kapphk.web.dao.mapper.user.BeanUserMapper;
import com.kapphk.web.service.web.imethod.course.BeanCourseContactService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.FileUploadUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;
@Service
public class BeanCourseContactServiceImpl implements BeanCourseContactService {

	@Autowired
	private BeanCourseContactMapper mapper;
	
	@Autowired
	private BeanUserMapper userMapper;
	/**
	 * 查询列表
	 */
	@Override
	public Result searchList(Result rs, BeanCourseContact bean, GridReq gridReq) throws Exception {
		DataUtils.trim(bean);
		List<Map<String,Object>> list = mapper.findList(bean,gridReq.getPage(),gridReq.getRows());
		int count = mapper.findCount(bean);
		rs = DataUtils.mergeData(count, list, rs);
		return rs;
	}

	/**
	 * 保存
	 */
	@Override
	public Result saveData(Result rs, BeanCourseContact bean,MultipartFile file,HttpServletRequest request) throws Exception {
		
		BeanCourseContact courseContact = new BeanCourseContact();
		courseContact.setCourseId(bean.getCourseId());
		courseContact.setUid(bean.getUid());
		List<BeanCourseContact> list = mapper.findAll(courseContact);
		if(!ValidateUtils.isEmptyForCollection(list) && (ValidateUtils.isBlank(bean.getId()) || list.get(0).getId() - bean.getId() != 0)){
			rs.setStatus(Contents.ERROR);
			rs.setMsg("该报名联系人已存在，请更改");
			return rs;
		}
		
		if(null != file && !file.isEmpty()){
			try {
				byte[] bytes = file.getBytes();
				String logoPath = FileUploadUtils.uploadFile(bytes,"upload/public", "2", request);
				bean.setLogoPath(logoPath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		BeanUser user = userMapper.findById(bean.getUid());
		if(ValidateUtils.isBlank(bean.getLogoPath()))bean.setLogoPath(user.getLogoPath());
		if(ValidateUtils.isBlank(bean.getUserName()))bean.setUserName(user.getNickName());
		if(ValidateUtils.isBlank(bean.getTel()))bean.setTel(user.getUserName());
		if(ValidateUtils.isBlank(bean.getId())){
			bean.setCreateTime(new Date());
			mapper.insert(bean);
		}else{
			mapper.update(bean);
		}
		return rs;
	}

	/**
	 * 详情
	 */
	@Override
	public Result getData(Result rs, BeanCourseContact bean) throws Exception {
		rs.put("data", mapper.findById(bean.getId()));
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
	 * 查询教务人员列表
	 */
	@Override
	public List<Map<String, Object>> searchContactList() {
		return mapper.findContactList();
	}

}
