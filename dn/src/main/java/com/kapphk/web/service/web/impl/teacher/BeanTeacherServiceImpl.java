package com.kapphk.web.service.web.impl.teacher;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kapphk.web.bean.teacher.BeanTeacher;
import com.kapphk.web.bean.teacher.BeanTeacherArrange;
import com.kapphk.web.bean.teacher.BeanTeacherDistrict;
import com.kapphk.web.bean.user.BeanUser;
import com.kapphk.web.dao.mapper.teacher.BeanTeacherArrangeMapper;
import com.kapphk.web.dao.mapper.teacher.BeanTeacherDistrictMapper;
import com.kapphk.web.dao.mapper.teacher.BeanTeacherMapper;
import com.kapphk.web.dao.mapper.user.BeanUserMapper;
import com.kapphk.web.service.web.imethod.teacher.BeanTeacherService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.ExcelUtils;
import com.kapphk.web.utils.FileUploadUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;
@Service
public class BeanTeacherServiceImpl implements BeanTeacherService {

	@Autowired
	private BeanTeacherMapper mapper;
	
	@Autowired
	private BeanTeacherDistrictMapper districtMapper;
	
	@Autowired
	private BeanTeacherArrangeMapper arrangeMapper;
	
	@Autowired
	private BeanUserMapper userMapper;
	
	/**
	 * 查询列表
	 */
	@Override
	public Result searchList(Result rs, BeanTeacher bean, String district, GridReq gridReq) throws Exception {
		List<Map<String,Object>> list = mapper.findList(bean,district,gridReq.getPage(),gridReq.getRows());
		int count = mapper.findCount(bean,district);
		rs = DataUtils.mergeData(count, list, rs);
		return rs;
	}

	/**
	 * 保存
	 */
	@Override
	public Result saveData(Result rs, BeanTeacher bean,List<String> district,MultipartFile file,HttpServletRequest request) throws Exception {
		
		if(file != null && !file.isEmpty()){
			byte[] bytes = file.getBytes();
			String logoPath = FileUploadUtils.uploadFile(bytes, "upload/public", "1", request);
			bean.setLogoPath(logoPath);
		}
		
		if(!ValidateUtils.isBlank(bean.getRecordType()) && bean.getRecordType() - 2 == 0){
			BeanTeacher teacher = new BeanTeacher();
			teacher.setUid(bean.getUid());
			List<BeanTeacher> list = mapper.findAll(teacher);
			if(!ValidateUtils.isEmptyForCollection(list)){
				if(!ValidateUtils.isBlank(bean.getId()) && list.get(0).getId() - bean.getId() == 0){}
				else {
					rs.setStatus(Contents.ERROR);
					rs.setMsg("该陪伴师已存在，请更改");
					return rs;
				}
			}
			BeanUser user = userMapper.findById(bean.getUid());
			bean.setItemName(user.getRealName());
			bean.setLogoPath(user.getLogoPath());
		}
		
		if(ValidateUtils.isBlank(bean.getId())){
			bean.setCreateTime(new Date());
			mapper.insert(bean);
		}else{
			mapper.update(bean);
		}
		
		//删除之前数据
		BeanTeacherDistrict teacherDistrict = new BeanTeacherDistrict();
		teacherDistrict.setTeacherId(bean.getId());
		districtMapper.deleteByEntity(teacherDistrict);
		Date date = new Date();
		if(!ValidateUtils.isEmptyForCollection(district)){
			List<BeanTeacherDistrict> list = new ArrayList<BeanTeacherDistrict>();
			for (String s : district) {
				BeanTeacherDistrict td = new BeanTeacherDistrict();
				td.setTagValue(s);
				td.setTagType("区域管理");
				td.setTeacherId(bean.getId());
				td.setCreateTime(date);
				list.add(td);
			}
			if(!ValidateUtils.isEmptyForCollection(list))districtMapper.inserts(list);
		}
		return rs;
	}

	/**
	 * 详情
	 */
	@Override
	public Result getData(Result rs, BeanTeacher bean) throws Exception {
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
	 * 获取全部老师
	 */
	@Override
	public List<Map<String, Object>> searchTeacherList(Integer recordType,String type) {
		List<Map<String,Object>> list = mapper.findTeacherList(recordType);
		if(!ValidateUtils.isBlank(type)){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("id", "");
			map.put("text", "全部");
		}
		return list;
	}

	/**
	 * 导出数据
	 */
	@Override
	public void exportExcel(BeanTeacher bean, String district,HttpServletResponse response) {
		DataUtils.trim(bean);
		bean.setRecordType(1);
		String[] particular = {"区域","级别","讲师名称","讲师简介","擅长主题","课程介绍","网络课程","更新时间"};
		String[] property = {"district","teacherLevel","itemName","itemRemark","itemTheme","itemContent","onlines","createTime"};
		ExcelUtils.downExcelList("动能名师表单.xlsx", particular, property, mapper.findList(bean,district,0,99999999), response);
	}

	/**
	 * 保存预约时间
	 */
	@Override
	public Result saveTa(Result rs, BeanTeacherArrange bean) throws Exception {
		if(ValidateUtils.isBlank(bean.getId())){
			bean.setCreateTime(new Date());
			arrangeMapper.insert(bean);
		}else{
			arrangeMapper.update(bean);
		}
		return rs;
	}

	/**
	 * 获取预约时间详情
	 */
	@Override
	public Result arrangeDate(Result rs, BeanTeacherArrange bean)
			throws Exception {
		List<BeanTeacherArrange> list = arrangeMapper.findAll(bean);
		if(!ValidateUtils.isEmptyForCollection(list)){
			rs.put("data", list.get(0));
		}
		return rs;
	}

}
