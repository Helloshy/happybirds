package com.kapphk.web.service.web.impl.course;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kapphk.redis.dao.JedisDao;
import com.kapphk.web.bean.course.BeanCourse;
import com.kapphk.web.bean.course.BeanCourseOffline;
import com.kapphk.web.dao.mapper.course.BeanCourseMapper;
import com.kapphk.web.dao.mapper.course.BeanCourseOfflineMapper;
import com.kapphk.web.dao.mapper.course.BeanCourseOrderMapper;
import com.kapphk.web.service.web.imethod.course.BeanCourseOfflineService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.ExcelUtils;
import com.kapphk.web.utils.FileUploadUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;
@Service
public class BeanCourseOfflineServiceImpl implements BeanCourseOfflineService {

	@Autowired
	private BeanCourseOfflineMapper mapper;
	
	@Autowired
	private BeanCourseMapper courseMapper;
	
	@Autowired
	private BeanCourseOrderMapper orderMapper;
	
	@Resource(name="jedisClient")
	private JedisDao jedisDao ;
	
	/**
	 * 查询列表
	 */
	@Override
	public Result searchList(Result rs, String itemName, Long courseTypeId, Integer isRecommend,
			Integer state, BigDecimal amount, GridReq gridReq) throws Exception {
		List<Map<String, Object>> list = mapper.findList(itemName,courseTypeId,isRecommend,state,amount,gridReq.getPage(),gridReq.getRows());
		int count = mapper.findCount(itemName,courseTypeId,isRecommend,state,amount);
		rs = DataUtils.mergeData(count, list, rs);
		return rs;
	}

	@Override
	public Result saveData(Result rs,BeanCourse course,BeanCourseOffline offline,MultipartFile file,HttpServletRequest request) throws Exception {
		DataUtils.trim(course);
		DataUtils.trim(offline);
		if(null != file && !file.isEmpty()){
			try {
				byte[] bytes = file.getBytes();
				String logPath = FileUploadUtils.uploadFile(bytes,"upload/public", "2", request);
				course.setLogoPath(logPath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(ValidateUtils.isBlank(course.getId())){
			course.setRecordType(1);
			course.setCreateTime(new Date());
			courseMapper.insert(course);
			offline.setId(course.getId());
			mapper.insert(offline);
		}else{
			courseMapper.update(course);
			mapper.update(offline);
		}
		//如果是首页推荐，更新redis  zoneyu 16-12-23
		Integer isRecommend = offline.getIsRecommend() ;
		if(!ValidateUtils.isBlank(isRecommend) && isRecommend == 1){
			List<Map<String,Object>> courseList = courseMapper.findHomeData() ;
			jedisDao.hashSaveEntity("news", "index", courseList);
		}
		return rs;
	}

	/**
	 * 详情
	 */
	@Override
	public Result getData(Result rs, BeanCourse bean) throws Exception {
		return rs.put("data", mapper.findDetailById(bean.getId()));
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

	/**
	 * 省市区
	 */
	@Override
	public List<Map<String, Object>> searchPcd(String id, Integer type) {
		return mapper.findPcd(id,type);
	}

	/**
	 * 更新首页推荐
	 */
	@Override
	public Result saveIsRecommend(Result rs, BeanCourseOffline offline)
			throws Exception {
		mapper.update(offline);
		return rs;
	}

	/**
	 * 更新课程状态
	 */
	@Override
	public Result upState(Result rs, List<Long> ids, Integer state)
			throws Exception {
		mapper.upState(ids,state);
		return rs;
	}

	/**
	 * 导出
	 * @author zoneyu 16-12-23
	 */
	public void export(String itemName, Long courseTypeId, Integer isRecommend, Integer state, BigDecimal amount,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Map<String, Object>> list = mapper.findExportList(itemName,courseTypeId,isRecommend,state,amount);
		String excelName = "线下课程管理.xlsx" ;
		String[] particular = {"所属课程系列","课程标题","报到时间","报到地点","主讲老师","报名联系人","报名费用","现场成交费用","首页推荐","课程排序","更新时间"} ;
		String[] property = {"courseTypeId","itemName","registerDate","pcda","teacher","contact","amount","offlineAmount","isRecommend","sort","createTime"} ;
		ExcelUtils.downExcelList(excelName, particular, property, list, response) ;
	}

}
