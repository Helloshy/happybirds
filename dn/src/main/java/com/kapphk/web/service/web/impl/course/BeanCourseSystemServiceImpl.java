package com.kapphk.web.service.web.impl.course;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kapphk.web.bean.course.BeanCourse;
import com.kapphk.web.bean.course.BeanCourseSystem;
import com.kapphk.web.dao.mapper.course.BeanCourseMapper;
import com.kapphk.web.dao.mapper.course.BeanCourseOrderMapper;
import com.kapphk.web.dao.mapper.course.BeanCourseSystemMapper;
import com.kapphk.web.service.web.imethod.course.BeanCourseSystemService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.ExcelUtils;
import com.kapphk.web.utils.FileUploadUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;
@Service
public class BeanCourseSystemServiceImpl implements BeanCourseSystemService {

	@Autowired
	private BeanCourseSystemMapper mapper;
	
	@Autowired
	private BeanCourseMapper courseMapper;
	
	@Autowired
	private BeanCourseOrderMapper orderMapper;
	/**
	 * 查询列表
	 */
	@Override
	public Result searchList(Result rs, BeanCourse bean, GridReq gridReq) throws Exception {
		List<Map<String,Object>> list = mapper.findList(bean,gridReq.getPage(),gridReq.getRows());
		int count = mapper.findCount(bean);
		rs = DataUtils.mergeData(count, list, rs);
		return rs;
	}

	/**
	 * 保存
	 */
	@Override
	public Result saveData(Result rs, BeanCourse bean, List<Long> courseOffline, List<Long> courseOnline,
			HttpServletRequest request, MultipartFile file,String itemRemark) throws Exception {
		bean.setRecordType(3);
		if(null != file && !file.isEmpty()){
			try {
				byte[] bytes = file.getBytes();
				String logoPath = FileUploadUtils.uploadFile(bytes,"upload/public", "2", request);
				bean.setLogoPath(logoPath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Date date = new Date();
		if(ValidateUtils.isBlank(bean.getId())){
			bean.setCreateTime(date);
			courseMapper.insert(bean);
		}else{
			courseMapper.update(bean);
		}
		
		//先删除之前套餐
		BeanCourseSystem system = new BeanCourseSystem();
		system.setSystemId(bean.getId());
		mapper.deleteByEntity(system);
		List<BeanCourseSystem> list = new ArrayList<BeanCourseSystem>();
		if(!ValidateUtils.isEmptyForCollection(courseOffline)){
			for (Long co : courseOffline) {
				BeanCourseSystem s = new BeanCourseSystem();
				s.setSystemId(bean.getId());
				s.setRecordType(1);
				s.setCourseId(co);
				s.setItemRemark(itemRemark);
				s.setCreateTime(date);
				list.add(s);
			}
		}
		if(!ValidateUtils.isEmptyForCollection(courseOnline)){
			for (Long on : courseOnline) {
				BeanCourseSystem s = new BeanCourseSystem();
				s.setSystemId(bean.getId());
				s.setRecordType(2);
				s.setCourseId(on);
				s.setItemRemark(itemRemark);
				s.setCreateTime(date);
				list.add(s);
			}
		}
		if(!ValidateUtils.isEmptyForCollection(list)){
			mapper.inserts(list);
		}
		
		return rs;
	}

	/**
	 * 详情
	 */
	@Override
	public Result getData(Result rs, BeanCourseSystem bean) throws Exception {
		rs.put("data", mapper.findDetailById(bean.getId()));
		return rs;
	}

	/**
	 * 删除
	 */
	@Override
	public Result delete(Result rs, List<Long> ids) throws Exception {
		//验证该课程是否生成订单
		if(orderMapper.verifyByCourseIds(ids) > 0){
			rs.setStatus(Contents.ERROR);
			rs.setMsg("该课程已生成订单，不可删除");
		}else{
			rs.put("count", courseMapper.upstatusByCourseIds(ids));
		}
		return rs;
	}

	@Override
	public void exportExcel(BeanCourse bean, HttpServletResponse response) {
		List<Map<String,Object>> list = mapper.findList(bean,0,9999999);
		String[] particular = new String[]{"系统课程名称","系统课程简介","费用","课程套餐内容","内容数量","更新时间"};
		String[] property = new String[]{"itemName","itemRemark","amount","courses","counts","createTime"};
		System.out.println(ExcelUtils.downExcelList("系统课程表单.xlsx", particular, property, list, response));
	}

}
