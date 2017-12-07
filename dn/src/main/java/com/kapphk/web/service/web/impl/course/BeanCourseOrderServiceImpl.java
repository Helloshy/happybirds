package com.kapphk.web.service.web.impl.course;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.web.bean.course.BeanCourse;
import com.kapphk.web.bean.course.BeanCourseOrder;
import com.kapphk.web.bean.course.BeanCourseStudent;
import com.kapphk.web.bean.course.BeanCourseSystem;
import com.kapphk.web.bean.course.BeanCourseType;
import com.kapphk.web.bean.course.BeanUserCoupon;
import com.kapphk.web.bean.system.BeanSetting;
import com.kapphk.web.bean.user.BeanUser;
import com.kapphk.web.bean.user.BeanUserCurrency;
import com.kapphk.web.dao.mapper.course.BeanCourseMapper;
import com.kapphk.web.dao.mapper.course.BeanCourseOfflineMapper;
import com.kapphk.web.dao.mapper.course.BeanCourseOrderMapper;
import com.kapphk.web.dao.mapper.course.BeanCourseStudentMapper;
import com.kapphk.web.dao.mapper.course.BeanCourseSystemMapper;
import com.kapphk.web.dao.mapper.course.BeanCourseTypeMapper;
import com.kapphk.web.dao.mapper.course.BeanUserCouponMapper;
import com.kapphk.web.dao.mapper.system.BeanSettingMapper;
import com.kapphk.web.dao.mapper.user.BeanUserCurrencyMapper;
import com.kapphk.web.dao.mapper.user.BeanUserMapper;
import com.kapphk.web.service.web.imethod.course.BeanCourseOrderService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.DateUtils;
import com.kapphk.web.utils.ExcelUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;

import cn.jpush.api.utils.Jgpush;

/**
 * 课程订单业务层
 * 
 * @author dengwen 2016-10-24下午4:06:38
 */
@Service
public class BeanCourseOrderServiceImpl implements BeanCourseOrderService {

	@Autowired
	private BeanCourseOrderMapper mapper;

	@Autowired
	private BeanUserMapper userMapper;

	@Autowired
	private BeanUserCouponMapper couponMapper;
	
	@Autowired
	private BeanCourseSystemMapper systemMapper;
	
	@Autowired
	private BeanCourseMapper courseMapper;
	
	@Autowired
	private BeanCourseOfflineMapper offlineMapper;
	
	@Autowired
	private BeanCourseTypeMapper typeMapper;
	
	@Autowired
	private BeanUserCurrencyMapper currencyMapper;
	
	@Autowired
	private BeanSettingMapper settingMapper;
	
	@Autowired
	private BeanCourseStudentMapper studentMapper;

	/**
	 * 订单列表
	 */
	@Override
	public Result searchList(Result rs, Long uid,Long[] couponId, Integer payStatus, Integer isUse,
			String orderNo, String itemName, String realName, String startTime,
			String endTime, String type, GridReq gridReq, Integer isPay) throws Exception {
		String[] str = new String[] {startTime, endTime};
		DataUtils.trim(str);
		List<Map<String, Object>> list = mapper.findList(uid,ValidateUtils.isempty(couponId) ? null : Arrays.asList(couponId),payStatus, isUse,
				orderNo, itemName, realName, str[0], str[1], type,gridReq.getPage(), gridReq.getRows(),isPay);
		int count = mapper.findCount(uid,ValidateUtils.isempty(couponId) ? null : Arrays.asList(couponId),payStatus, isUse, orderNo, 
				itemName,realName, str[0], str[1], type,isPay);
		rs = DataUtils.mergeData(count, list, rs);
		return rs;
	}

	@Override
	public Result saveData(Result rs, BeanCourseOrder bean, String userName)
			throws Exception {
		
		if(!ValidateUtils.isBlank(bean.getRecordType()) && bean.getRecordType() - 1 ==0){
			//报课码
			String code = DataUtils.getRadom(8) ;
			boolean b = true ;
			while(b){
				BeanCourseOrder order = new BeanCourseOrder() ;
				order.setCourseCode(code);
				int count = mapper.findByPageCount(order) ;
				if(count == 0){
					bean.setCourseCode(code);
					b = false ;
				}else{
					code = DataUtils.getRadom(8) ;
				}
			}
			bean.setCourseCode(code);
		}
		
		if (ValidateUtils.isBlank(bean.getId())) {
			Date date = new Date();
			BeanUser user = new BeanUser();
			user.setUserName(userName);
			List<BeanUser> list = userMapper.findAll(user);
			if (!ValidateUtils.isEmptyForCollection(list)) {
				List<BeanUserCurrency> currencyList = new ArrayList<BeanUserCurrency>();
				BeanUserCurrency currency = null;
				user = list.get(0);
				bean.setPayMethodType("支付方式");
				bean.setIsPay(1);
				bean.setValidationTime(date);
				bean.setCreateTime(date);
				bean.setOrderNo("BM" + String.valueOf(System.nanoTime()));// 订单号
				bean.setUid(user.getId());
				if(ValidateUtils.isBlank(bean.getIsUse()))bean.setIsUse(1);
				String content = "线下课程抵扣";
				if(bean.getRecordType() - 2 == 0) content = "网络课程抵扣";
				else if (bean.getRecordType() - 3 == 0) content = "系统课程抵扣";
				// 运算蓝币红币
				if (!"课程抵扣券".equals(bean.getPayMethod()) && !ValidateUtils.isBlank(bean.getDiscountBlue())) {
					if (user.getBlueCurrency()
							.compareTo(bean.getDiscountBlue()) < 0) {
						rs.setStatus(Contents.ERROR);
						rs.setMsg("蓝币不足！");
						return rs;
					} else {
						user.setBlueCurrency(user.getBlueCurrency().subtract(
								bean.getDiscountBlue()));
						currency = new BeanUserCurrency();
						currency.setCreateTime(date);
						currency.setUid(user.getId());
						currency.setCurrencyType(1);
						currency.setRecordType(4);
						currency.setCurrency(bean.getDiscountBlue().multiply(new BigDecimal(-1)));
						currency.setContent(content);
						currencyList.add(currency);
					}
				}else{
					bean.setDiscountBlue(new BigDecimal(0));
				}
				if (!"课程抵扣券".equals(bean.getPayMethod()) && !ValidateUtils.isBlank(bean.getDiscountRed())) {
					if (user.getRedCurrency().compareTo(bean.getDiscountRed()) < 0) {
						rs.setStatus(Contents.ERROR);
						rs.setMsg("红币不足！");
						return rs;
					} else {
						user.setRedCurrency(user.getRedCurrency().subtract(
								bean.getDiscountRed()));
						currency = new BeanUserCurrency();
						currency.setCreateTime(date);
						currency.setUid(user.getId());
						currency.setCurrencyType(2);
						currency.setRecordType(4);
						currency.setCurrency(bean.getDiscountRed().multiply(new BigDecimal(-1)));
						currency.setContent(content);
						currencyList.add(currency);
					}
				}else{
					bean.setDiscountRed(new BigDecimal(0));
				}
				userMapper.update(user);
				if(!ValidateUtils.isEmptyForCollection(currencyList)) currencyMapper.inserts(currencyList);
				
				// 验证是否还有抵扣卷
				if (bean.getRecordType() - 1 == 0 && "课程抵扣券".equals(bean.getPayMethod())) {
					List<BeanUserCoupon> couponList = couponMapper.findByCourseId(user.getId(),bean.getCourseId(),DateUtils.getLocalYmdDate());
					if (!ValidateUtils.isEmptyForCollection(couponList)) {
						BeanUserCoupon coupon = couponList.get(0);
						coupon.setState(1);
						couponMapper.update(coupon);
						bean.setCouponId(coupon.getId());
						bean.setCourseTypeId(coupon.getCourseId());
					} else {
						rs.setStatus(Contents.ERROR);
						rs.setMsg("抵扣券不足！");
						return rs;
					}
				}
				
				if(!"课程抵扣券".equals(bean.getPayMethod()) && bean.getRecordType() != 1) executeBusiness(bean.getUid(),
						new BigDecimal((bean.getPayAmount().intValue()+bean.getDiscountRed().intValue())));
				mapper.insert(bean);
				if(bean.getRecordType() - 3 == 0){
					BeanCourse course = courseMapper.findById(bean.getCourseId());
					BeanCourseSystem system = new BeanCourseSystem();
					system.setSystemId(bean.getCourseId());
					List<BeanCourseOrder> orderList = new ArrayList<BeanCourseOrder>();
					List<BeanUserCoupon> couponList = new ArrayList<BeanUserCoupon>();
					BeanCourseType type = new BeanCourseType();
					Calendar c = Calendar.getInstance();
					for (BeanCourseSystem s : systemMapper.findAll(system)) {
						if(s.getRecordType() - 1 == 0){
							BeanUserCoupon coupon = new BeanUserCoupon();
							type = typeMapper.findById(offlineMapper.findById(s.getCourseId()).getCourseTypeId());
							coupon.setCourseId(type.getId());
							coupon.setState(0);
							coupon.setUid(user.getId());
							coupon.setCouponName(type.getItemName()+": 抵扣卷");
							coupon.setCouponContent("可以使用此抵扣券全额购买: "+type.getItemName());
							coupon.setCreateTime(date);
							coupon.setDateFrom(date);
							c.add(Calendar.MONTH, Integer.valueOf(type.getCourseMonth()));
							coupon.setDateTo(c.getTime());
							couponList.add(coupon);
						}else{
							BeanCourseOrder order = new BeanCourseOrder();
							order.setPayMethodType("支付方式");
							order.setIsPay(1);
							order.setIsUse(1);
							order.setCreateTime(date);
							order.setOrderNo("BM" + String.valueOf(System.nanoTime()));// 订单号
							order.setUid(user.getId());
							order.setDiscountBlue(bean.getDiscountBlue());
							order.setDiscountRed(bean.getDiscountRed());
							order.setSystemCourseName(course.getItemName());
							order.setCourseId(s.getCourseId());
							order.setPayAmount(bean.getPayAmount());
							order.setOriginAmount(bean.getOriginAmount());
							order.setPayMethod(bean.getPayMethod());
							order.setPayTime(bean.getPayTime());
							order.setPayStatus(1);
							order.setRecordType(2);
							orderList.add(order);
						}
					}
					if(!ValidateUtils.isEmptyForCollection(orderList))mapper.inserts(orderList);
					if(!ValidateUtils.isEmptyForCollection(couponList)) couponMapper.inserts(couponList);
				}
			} else {
				rs.setStatus(Contents.ERROR);
				rs.setMsg("该购买账号不存在，请核对在保存");
			}
		} else {
			bean.setIsPay(1);
			mapper.update(bean);
			//添加推送信息  zoneyu 16-12-24
			BeanCourseOrder order = mapper.findById(bean.getId()) ;
			String alia = "alias"+String.valueOf(order.getUid()) ;
			String[] alias = new String[]{alia} ;
			String msg = "您有新的线下课程订单已生成" ;
			try {
				Jgpush.sendMessage_simple(msg, alias, null) ;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rs;
	}
	
	/**
	 * 上三级返佣
	 */
	private void executeBusiness(Long uid,BigDecimal total){
		BeanSetting s1 = settingMapper.findById("withhold_tax");
		BeanSetting s2 = settingMapper.findById("withhold_individual_tax");
		Float rate = (float) ((100-Float.valueOf(s1.getValue()))*0.01*((100-Float.valueOf(s2.getValue()))*0.01));
		List<Map<String,Object>> list = userMapper.getThreeUid(uid) ;
		if(!ValidateUtils.isEmptyForCollection(list)){
			for(Map<String,Object> map : list){
				Long uid2 = (Long) map.get("uid") ;
				BigDecimal rebale = (BigDecimal) map.get("rebale") ;
				BeanUser user = userMapper.findById(uid2) ;
				BigDecimal redCurrency = ValidateUtils.isBlank(user.getRedCurrency()) ? BigDecimal.ZERO : user.getRedCurrency() ; 
				BigDecimal value = total.multiply(rebale).multiply(new BigDecimal(rate)) ;
				value = value.setScale(0, BigDecimal.ROUND_HALF_UP) ;
				user.setRedCurrency(redCurrency.add(value));
				userMapper.update(user) ;
			}
		}
	}

	/**
	 * 取消订单
	 */
	@Override
	public Result upstatus(Result rs, BeanCourseOrder bean) throws Exception {
		mapper.update(bean);
		return rs;
	}

	/**
	 * 获取课程列表
	 */
	@Override
	public List<Map<String, Object>> getCourseList(String type) {
		return mapper.findCourseList(type);
	}

	/**
	 * 课程详情
	 */
	@Override
	public Result getCourseDetail(Result rs, Long id) throws Exception {
		return rs.put("data", mapper.findCourseDetail(id));
	}

	/**
	 * 用户详情
	 */
	@Override
	public Result getUserDetail(Result rs, BeanUser bean) throws Exception {
		if (!ValidateUtils.isBlank(bean.getUserName())) {
			Map<String, Object> map = mapper.findUserDetail(bean.getUserName());
			if (!ValidateUtils.isBlank(map)
					&& !ValidateUtils.isBlank(map.get("realName"))) {
				rs.put("data", map);
			} else {
				rs.setStatus(Contents.ERROR);
				rs.setMsg("该购买账号不存在，请更改");
			}
		}
		return rs;
	}

	/**
	 * 导出
	 */
	@Override
	public void exportExcel(Long uid, Long[] couponId, Integer payStatus,
			Integer isUse, String orderNo, String itemName, String realName,
			String startTime, String endTime, String type,HttpServletResponse response,Integer isPay) {
		String[] str = new String[] {startTime, endTime};
		DataUtils.trim(str);
		List<Map<String, Object>> list = mapper.findList(uid,ValidateUtils.isempty(couponId) ? null : Arrays.asList(couponId),payStatus, isUse,
				orderNo, itemName, realName, str[0], str[1], type, 0, 9999999,isPay);
		String[] particular = {};
		String[] property = {};
		String title = "";
		if (type.equals("1")) {
			title = "线下课程订单.xlsx";
			particular = new String[] { "课程名称", "报到时间", "报到地点", "购买账号", "真实姓名",
					"所在社区", "上级账号", "上级姓名", "状态", "课程费用", "实收费用", "支付方式","抵扣蓝币", "抵扣红币", "支付时间" };
			property = new String[] { "itemName", "registerDate", "pcda","userName", "realName", "community", "sjUserName",
					"sjRealName", "payStatus", "originAmount", "payAmount","payMethod", "discountBlue", "discountRed", "payTime" };
		} else if (type.equals("2")) {
			title = "网络课程订单.xlsx";
			particular = new String[] { "课程名称", "购买账号", "真实姓名", "所在社区", "上级账号",
					"上级姓名", "课程费用", "实收费用", "支付方式", "抵扣蓝币", "抵扣红币", "支付时间" };
			property = new String[] { "itemName", "userName", "realName","community", "sjUserName", "sjRealName",
					"originAmount","payAmount", "payMethod", "discountBlue", "discountRed","payTime" };
		} else {
			title = "系统课程订单.xlsx";
			particular = new String[] { "课程名称", "套餐内容", "内容数量", "购买账号", "真实姓名",
					"所在社区", "上级账号", "上级姓名", "课程费用", "实收费用", "支付方式", "抵扣蓝币","抵扣红币", "支付时间" };
			property = new String[] { "itemName", "meal", "counts", "userName","realName", "community", "sjUserName", "sjRealName",
					"originAmount", "payAmount", "payMethod", "discountBlue","discountRed", "payTime" };
		}
		System.out.println(ExcelUtils.downExcelList(title, particular,property, list, response));
	}

	/**
	 * 保存孩子信息
	 */
	@Override
	public Result saveCourseStudent(Result rs, BeanCourseStudent bean)
			throws Exception {
		if(ValidateUtils.isBlank(studentMapper.findById(bean.getId()))){
			bean.setCreateTime(new Date());
			studentMapper.insert(bean);
		}else{
			studentMapper.update(bean);
		}
		return rs;
	}

	/**
	 * 订单详情
	 */
	@Override
	public Result courseOrderDetail(Result rs, Long id) throws Exception {
		return rs.put("data", mapper.findCourseOrderDetail(id));
	}
}
