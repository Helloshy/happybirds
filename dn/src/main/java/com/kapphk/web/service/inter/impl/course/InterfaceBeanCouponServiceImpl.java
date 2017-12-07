package com.kapphk.web.service.inter.impl.course;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.web.bean.course.BeanUserCoupon;
import com.kapphk.web.dao.mapper.course.BeanUserCouponMapper;
import com.kapphk.web.service.inter.imethod.course.InterfaceBeanCouponService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;

/**
 * 课程抵扣卷业务层
 * @author dengwen 
 * 2016-10-14上午10:29:57
 */
@Service
public class InterfaceBeanCouponServiceImpl implements
		InterfaceBeanCouponService {

	@Autowired
	private BeanUserCouponMapper mapper;
	
	/**
	 * 获取抵扣卷列表
	 */
	@Override
	public Result getCouponList(BeanUserCoupon bean, Integer page, Result rs)
			throws Exception {
		if(!ValidateUtils.isBlank(bean.getUid()) && !ValidateUtils.isBlank(bean.getState())){
			page = ValidateUtils.isBlank(page) ? 1 : page ;
			List<Map<String,Object>> list = mapper.findCouponInfo(bean, (page-1)*GridReq.ROWS, GridReq.ROWS);
			rs.put("info", list);
		}else{
			rs.setStatus(Contents.ERROR);
			rs.setMsg("参数有误");
		}
		return rs;
	}

}
