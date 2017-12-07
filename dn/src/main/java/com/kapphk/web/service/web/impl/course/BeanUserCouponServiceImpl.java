package com.kapphk.web.service.web.impl.course;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.web.bean.course.BeanCourseType;
import com.kapphk.web.bean.course.BeanUserCoupon;
import com.kapphk.web.dao.mapper.course.BeanCourseTypeMapper;
import com.kapphk.web.dao.mapper.course.BeanUserCouponMapper;
import com.kapphk.web.service.web.imethod.course.BeanUserCouponService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.ExcelUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;
@Service
public class BeanUserCouponServiceImpl implements BeanUserCouponService {

	@Autowired
	private BeanUserCouponMapper mapper;
	
	@Autowired
	private BeanCourseTypeMapper courseTypeMapper;
	/**
	 * 查询列表
	 */
	@Override
	public Result searchList(Result rs, BeanUserCoupon bean, GridReq gridReq) throws Exception {
		if(!ValidateUtils.isBlank(bean.getUid())){
			DataUtils.trim(bean);
			List<Map<String,Object>> list = mapper.findList(bean,gridReq.getPage(),gridReq.getRows());
			int count = mapper.findCount(bean);
			rs = DataUtils.mergeData(count, list, rs);
		}else{
			rs.setStatus(Contents.ERROR);
			rs.setMsg("参数有误");
		}
		return rs;
	}

	/**
	 * 保存
	 */
	@Override
	public Result saveData(Result rs, BeanUserCoupon bean) throws Exception {
		BeanCourseType courseType = courseTypeMapper.findById(bean.getCourseId());
		bean.setCouponName(courseType.getItemName()+"抵扣卷");
		bean.setCouponContent("可以使用此抵扣卷全额购买"+courseType.getItemName());
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
	public Result getData(Result rs, BeanUserCoupon bean) throws Exception {
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
	 * 导出
	 */
	@Override
	public void exportExcel(BeanUserCoupon bean,HttpServletResponse response) {
		String[] particular =  {"用户账号","昵称","真实姓名","课程系列名称","抵扣卷名称","抵扣卷说明","有效时间"};
		String[] property = {"userName","nickName","realName","courseId","couponName","couponContent","dateFromdateTo"};
		ExcelUtils.downExcelList("抵扣卷表单.xlsx", particular, property, mapper.findList(bean,0,99999999), response);
	}

}
