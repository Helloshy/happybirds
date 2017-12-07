package com.kapphk.web.service.web.impl.financial;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.web.bean.course.BeanCourseCredits;
import com.kapphk.web.bean.course.BeanCourseOrder;
import com.kapphk.web.bean.course.BeanCourseUse;
import com.kapphk.web.bean.system.BeanSetting;
import com.kapphk.web.bean.tag.BeanManagerBonus;
import com.kapphk.web.bean.user.BeanUser;
import com.kapphk.web.dao.mapper.BeanFinancialMapper;
import com.kapphk.web.dao.mapper.course.BeanCourseCreditsMapper;
import com.kapphk.web.dao.mapper.course.BeanCourseOrderMapper;
import com.kapphk.web.dao.mapper.course.BeanCourseUseMapper;
import com.kapphk.web.dao.mapper.product.BeanAppProductOrderMapper;
import com.kapphk.web.dao.mapper.system.BeanSettingMapper;
import com.kapphk.web.dao.mapper.tag.BeanManagerBonusMapper;
import com.kapphk.web.dao.mapper.teacher.BeanTeacherOrderMapper;
import com.kapphk.web.dao.mapper.user.BeanUserMapper;
import com.kapphk.web.service.web.imethod.financial.BeanFinancialService;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.DateUtils;
import com.kapphk.web.utils.ExcelUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;
@Service
public class BeanFinancialServiceImpl implements BeanFinancialService {

	@Autowired
	private BeanCourseOrderMapper orderMapper;
	
	@Autowired
	private BeanFinancialMapper mapper;
	
	@Autowired
	private BeanUserMapper userMapper;
	
	@Autowired
	private BeanSettingMapper settingMapper;
	
	@Autowired
	private BeanManagerBonusMapper managerBonusMapper;
	
	@Autowired
	private BeanCourseUseMapper useMapper;
	
	@Autowired
	private BeanCourseCreditsMapper creditsMapper;
	
	@Autowired
	private BeanTeacherOrderMapper teacherOrderMapper;
	
	@Autowired
	private BeanCourseOrderMapper courseOrderMapper;
	
	@Autowired
	private BeanAppProductOrderMapper productOrderMapper;
	
	/**
	 * 查询列表
	 */
	@Override
	public Result searchList(String startTime,String endTime,String orderNo, String realName, String teacherName, 
			String itemName, String userName,String title, String type, Result rs,GridReq gridReq) throws Exception {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		int count = 0;
		if("1".equals(type)){
			list = mapper.findList(startTime,endTime,orderNo,realName,type,gridReq.getPage(),gridReq.getRows());
			count = mapper.findCount(startTime,endTime,orderNo,realName,type);
		}else if("2".equals(type)){
			list = mapper.findList(startTime,endTime,itemName,teacherName,type,gridReq.getPage(),gridReq.getRows());
			count = mapper.findCount(startTime,endTime,itemName,teacherName,type);
		}else if("3".equals(type)){
			list = mapper.findList(startTime,endTime,itemName,teacherName,type,gridReq.getPage(),gridReq.getRows());
			count = mapper.findCount(startTime,endTime,itemName,teacherName,type);
		}else if("4".equals(type)){
			list = mapper.findList(null,null,itemName,teacherName,type,gridReq.getPage(),gridReq.getRows());
			count = mapper.findCount(null,null,itemName,teacherName,type);
		}else if("5".equals(type)){
			list = mapper.findList(startTime,endTime,itemName,realName,type,gridReq.getPage(),gridReq.getRows());
			count = mapper.findCount(startTime,endTime,itemName,realName,type);
		}else if("6".equals(type)){
			list = mapper.findList(startTime,endTime,itemName,realName,type,gridReq.getPage(),gridReq.getRows());
			count = mapper.findCount(startTime,endTime,itemName,realName,type);
		}else if("7".equals(type)){
			list = mapper.findList(startTime,endTime,teacherName,realName,type,gridReq.getPage(),gridReq.getRows());
			count = mapper.findCount(startTime,endTime,teacherName,realName,type);
		}else if("8".equals(type)){
			list = mapper.findList(null,null,userName,realName,type,gridReq.getPage(),gridReq.getRows());
			count = mapper.findCount(null,null,userName,realName,type);
		}else if("9".equals(type)){
			list = mapper.findList(null,null,itemName,title,type,gridReq.getPage(),gridReq.getRows());
			count = mapper.findCount(null,null,itemName,title,type);
		}else if("10".equals(type)){
			list = mapper.findList(null,itemName,userName,realName,type,gridReq.getPage(),gridReq.getRows());
			count = mapper.findCount(null,itemName,userName,realName,type);
		}else if("11".equals(type)){
			list = mapper.findList(startTime,endTime,userName,realName,type,gridReq.getPage(),gridReq.getRows());
			count = mapper.findCount(startTime,endTime,userName,realName,type);
		}else if("12".equals(type)){
			list = mapper.findList(startTime,endTime,itemName,realName,type,gridReq.getPage(),gridReq.getRows());
			count = mapper.findCount(startTime,endTime,itemName,realName,type);
		}else if("13".equals(type)){
			list = mapper.findList(startTime,endTime,itemName,realName,type,gridReq.getPage(),gridReq.getRows());
			count = mapper.findCount(startTime,endTime,itemName,realName,type);
		}else if("14".equals(type)){
			list = mapper.findList(startTime,endTime,itemName,realName,type,gridReq.getPage(),gridReq.getRows());
			count = mapper.findCount(startTime,endTime,itemName,realName,type);
		}else if("15".equals(type)){
			list = mapper.findList(startTime,endTime,userName,realName,type,gridReq.getPage(),gridReq.getRows());
			count = mapper.findCount(startTime,endTime,userName,realName,type);
		}else if("16".equals(type)){
			list = mapper.findList(startTime,endTime,userName,realName,type,gridReq.getPage(),gridReq.getRows());
			count = mapper.findCount(startTime,endTime,userName,realName,type);
		}else if("17".equals(type)){
			list = mapper.findList(startTime,endTime,userName,realName,type,gridReq.getPage(),gridReq.getRows());
			count = mapper.findCount(startTime,endTime,userName,realName,type);
		}
		rs = DataUtils.mergeData(count, list, rs);
		return rs;
	}

	/**
	 * 购买人数
	 */
	@Override
	public Result searchPurchaseList(Long courseId, Result rs, GridReq gridReq) {
		List<Map<String,Object>> list = orderMapper.findPurchaseList(courseId,gridReq.getPage(),gridReq.getRows());
		BeanCourseOrder order = new BeanCourseOrder();
		order.setCourseId(courseId);
		order.setIsPay(1);
		int count = orderMapper.findByPageCount(order);
		rs = DataUtils.mergeData(count, list, rs);
		return rs;
	}

	/**
	 * 导出数据
	 */
	@Override
	public void exportExcel(HttpServletResponse response,String startTime,String endTime,String orderNo,String realName,
			String teacherName,String itemName,String userName,String title,String type,Long courseId) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		String[] particular = null;
		String[] property = null;
		if("1".equals(type)){
			list = mapper.findList(startTime,endTime,orderNo,realName,type,0,9999999);
			particular = new String[]{"交易类型","交易订单号","交易对象姓名","手机号","实收交易金额","支付方式","抵扣蓝币","抵扣红币","交易时间"};
			property = new String[]{"itemName","orderNo","realName","userName","payAmount","payMethod","discountBlue","discountRed","createTime"};
			System.out.println(ExcelUtils.downExcelList("交易流水管理.xlsx", particular, property, list, response));
		}else if("2".equals(type)){
			list = mapper.findList(startTime,endTime,itemName,teacherName,type,0,9999999);
			particular = new String[]{"课程编号","课程名称","开课时间","讲师名称","每课时工资","课时数","课时总金额","现场成交金额","成交提成比例%","提成金额","总计","更新时间"};
			property = new String[]{"id","itemName","registerDate","userName","salsry","teachTimes","totalAmount","offlineAmount","rate","amount","total","createTime"};
			System.out.println(ExcelUtils.downExcelList("线下课程老师提成.xlsx", particular, property, list, response));
		}else if("3".equals(type)){
			list = mapper.findList(startTime,endTime,itemName,teacherName,type,0,9999999);
			particular = new String[]{"课程编号","课程名称","开课时间","教务名称","教务补贴费用","消耗人数","教务总人数","提成金额","更新时间"};
			property = new String[]{"id","itemName","registerDate","userName","subsidy","counts","contacts","amount","createTime"};
			System.out.println(ExcelUtils.downExcelList("线下课程教务提成.xlsx", particular, property, list, response));
		}else if("4".equals(type)){
			if(ValidateUtils.isBlank(courseId)){
				list = mapper.findList(null,null,itemName,teacherName,type,0,9999999);
				particular = new String[]{"课程编号","网络课程名称","讲师名称","课程单价","销售总额","提成比例","累计提成金额","更新时间"};
				property = new String[]{"orderNo","itemName","teacherName","amount","totalAmount","rate","amountSum","createTime"};
				System.out.println(ExcelUtils.downExcelList("网络课程讲师提成.xlsx", particular, property, list, response));
			}else{
				list = orderMapper.findPurchaseList(courseId,0,9999999);
				particular = new String[]{"用户手机号","真实姓名","用户上级","上级手机号","购买时间"};
				property = new String[]{"userName","realName","sjRealName","sjUserName","createTime"};
				System.out.println(ExcelUtils.downExcelList("课程购买人数.xlsx", particular, property, list, response));
			}
		}else if("5".equals(type)){
			list = mapper.findList(startTime,endTime,itemName,realName,type,0,9999999);
			particular = new String[]{"课程编号","课程名称","客服姓名","客服身份","提成比例%","课程实收费用","提成金额","购买用户账号","购买用户姓名","更新时间"};
			property = new String[]{"id","itemName","kfRealName","position","value","amount","totalAmount","userName","realName","createTime"};
			System.out.println(ExcelUtils.downExcelList("客服员工提成.xlsx", particular, property, list, response));
		}else if("6".equals(type)){
			list = mapper.findList(startTime,endTime,itemName,realName,type,0,9999999);
			particular = new String[]{"预约订单号","讲师名称","讲师级别","提成比例%","讲课时间","讲课地点","主办单位","主办单位邀约者","联系电话","讲课场次","实收讲课费","提成金额","更新时间"};
			property = new String[]{"id","itemName","level","rate","date","address","companyName","nickName","phone","teachTimes","actualAmount","amount","createTime"};
			System.out.println(ExcelUtils.downExcelList("预约讲学提成.xlsx", particular, property, list, response));
		}else if("7".equals(type)){
			list = mapper.findList(startTime,endTime,teacherName,realName,type,0,9999999);
			particular = new String[]{"订单号","客户姓名","客户账号","陪伴师名称","陪伴师星级","服务期限","订单实收费用","提成比例%","提成金额","更新时间"};
			property = new String[]{"id","realName","userName","itemName","level","teachTheme","actualAmount","rate","amount","createTime"};
			System.out.println(ExcelUtils.downExcelList("陪伴师学提成.xlsx", particular, property, list, response));
		}else if("8".equals(type)){
			list = mapper.findList(null,null,userName,realName,type,0,9999999);
			particular = new String[]{"月份","区域身份","注册账号","真实姓名","直接负责省份","直接提成比例","间接负责省份",
					"间接提成比例","当月直接总金额","直接提成金额","当月间接总金额","间接提成金额","总提成金额","更新时间"};
			property = new String[]{"month","recordType","userName","realName","directArea","directAreaRate","indirectArea",
					"indirectAreaRate","directAmount","directAreaAmount","indirectAmount","indirectAreaAmount","totalAmount","createTime"};
			System.out.println(ExcelUtils.downExcelList("区域经理提成.xlsx", particular, property, list, response));
		}else if("9".equals(type)){
			list = mapper.findList(null,null,itemName,title,type,0,9999999);
			particular = new String[]{"赞赏对象","资讯标题/课程名称","赞赏类型","赞赏费用","用户手机号","用户姓名","赞赏时间"};
			property = new String[]{"type","itemName","payMethod","payAmount","userName","realName","createTime"};
			System.out.println(ExcelUtils.downExcelList("赞赏明细.xlsx", particular, property, list, response));
		}else if("10".equals(type)){
			list = mapper.findList(null,itemName,userName,realName,type,0,9999999);
			particular = new String[]{"注册账号","真实姓名","消费事务","消费金额","一级代理账号","获得返佣","二级代理账号","获得返佣","三级代理账号","获得返佣","更新时间"};
			property = new String[]{"userName","realName","itemName","amount","userName1","amount1","userName2","amount2","userName3","amount3","createTime"};
			System.out.println(ExcelUtils.downExcelList("分佣明细.xlsx", particular, property, list, response));
		}else if("11".equals(type)){
			list = mapper.findList(startTime,endTime,userName,realName,type,0,9999999);
			particular = new String[]{"月份","注册账号","真实姓名","当月已消耗课程","当月已消耗数量","当月已消耗总金额","未消耗课程","未消耗数量","未消耗总金额","创建时间"};
			property = new String[]{"useMonth","userName","realName","useCourseName","useCounts","useAmount","unuseCourseName","unuseCounts","unuseAmount","createTime"};
			System.out.println(ExcelUtils.downExcelList("课程消耗情况表.xlsx", particular, property, list, response));
		}else if("12".equals(type)){
			list = mapper.findList(startTime,endTime,itemName,realName,type,0,9999999);
			particular = new String[]{"月份","客户姓名","课程系列名","课程抵扣卷数量","已购买课程名额","已上课消耗总名额","剩余名额","更新时间"};
			property = new String[]{"month","realName","courseTypeName","totalAmount","buyAmount","useAmount","lastAmount","createTime"};
			System.out.println(ExcelUtils.downExcelList("课程抵扣卷报表.xlsx", particular, property, list, response));
		}else if("13".equals(type)){
			list = mapper.findList(startTime,endTime,itemName,realName,type,0,9999999);
			particular = new String[]{"预约讲师","讲课日期","讲课地点","主办单位","主办单位邀约者","联系电话","讲课场次","赠送场次","讲课费","陪同人员","交通费","赠送原因","更新时间"};
			property = new String[]{"itemName","teachDate","address","companyName","nickName","phone","teachTimes","freeTimes","actualAmount","personnel","transportation","reason","createTime"};
			System.out.println(ExcelUtils.downExcelList("讲师派遣情况表.xlsx", particular, property, list, response));
		}else if("14".equals(type)){
			list = mapper.findList(startTime,endTime,itemName,realName,type,0,9999999);
			particular = new String[]{"陪伴师名称","陪伴师等级","陪伴天数","陪伴地点","陪伴时间","客户姓名","联系电话","收费金额","陪伴成交邀约者","交通费","更新时间"};
			property = new String[]{"itemName","teacherLevel","teachTheme","address","teachDate","nickName","phone","actualAmount","personnel","transportation","createTime"};
			System.out.println(ExcelUtils.downExcelList("陪伴派遣情况表.xlsx", particular, property, list, response));
		}else if("15".equals(type)){
			list = mapper.findList(startTime,endTime,userName,realName,type,0,9999999);
			particular = new String[]{"注册账号","真实姓名","动能身份","欧币金额变化","欧币类型","变化途径","剩余总蓝币数","剩余总红币数","更新时间"};
			property = new String[]{"userName","realName","tagId","currency","currencyType","content","blueCurrency","redCurrency","createTime"};
			System.out.println(ExcelUtils.downExcelList("欧币报表.xlsx", particular, property, list, response));
		}else if("16".equals(type)){
			list = mapper.findList(startTime,endTime,userName,realName,type,0,9999999);
			particular = new String[]{"注册账号","真实姓名","动能身份","总红币数","可使用红币数","提现中红币数","冻结中红币数","已使用红币数","更新时间"};
			property = new String[]{"userName","realName","tagId","total","redCurrency","txAmount","djAmount","syAmount","createTime"};
			System.out.println(ExcelUtils.downExcelList("分佣报表.xlsx", particular, property, list, response));
		}else if("17".equals(type)){
			list = mapper.findList(startTime,endTime,userName,realName,type,0,9999999);
			particular = new String[]{"合作伙伴账户","合作伙伴姓名","动能身份","级别","用户账号","用户姓名","课程名称","课程状态","收入额","代扣税率","代扣税金",
					"税后金额","分佣比例","理论分佣金额","个人税率","实际分佣金额","更新时间"};
			property = new String[]{"userName","realName","tagId","rank","userName1","realName1","itemName","isUse","amount","value","dkAmount","xhAmount",
					"rate","llAmount","value1","sjAmount","createTime"};
			System.out.println(ExcelUtils.downExcelList("会员结算月报表.xlsx", particular, property, list, response));
		}
		
	}
	
	/**
	 * 生成区域提成数据
	 * @author dengwen 
	 * 2016-11-21下午2:45:53
	 */
	public void managerBonus(){
		List<BeanManagerBonus> list = new ArrayList<BeanManagerBonus>();
		BeanSetting s1 = settingMapper.findById("area_manager");
		BeanSetting s2 = settingMapper.findById("area_general_manager");
		BeanSetting s3 = settingMapper.findById("area_general_manager_indirect");
		String startTime = getDate(-1)+" 00:00:00";
		String endTime = DateUtils.getLocalYmdDate()+" 23:59:59";
		String month = getDate(-1).substring(5, 7);
		Date date = new Date();
		for (Map<String, Object> map: userMapper.findByStaffPosition()) {
			BeanManagerBonus bean = new BeanManagerBonus();
			bean.setMonth(month);
			bean.setDirectArea((String)map.get("directArea"));
			bean.setIndirectArea((String)map.get("indirectArea"));
			String staffPosition = (String) map.get("staffPosition");
			bean.setUid((Long)map.get("id"));
			if(staffPosition.contains("区域总经理")){
				bean.setRecordType(2);
				bean.setDirectAreaRate(new BigDecimal(s2.getValue()));
				bean.setIndirectAreaRate(new BigDecimal(s3.getValue()));
				bean.setDirectAmoun(userMapper.findTotalAmount(startTime,endTime,Arrays.asList(((String)map.get("directArea")).split(","))));
				bean.setIndirectAmount(userMapper.findTotalAmount(startTime,endTime,Arrays.asList(((String)map.get("indirectArea")).split(","))));
			}else{
				bean.setRecordType(1);
				bean.setDirectAreaRate(new BigDecimal(s1.getValue()));
				bean.setDirectAmoun(userMapper.findTotalAmount(startTime,endTime,Arrays.asList(((String)map.get("directArea")).split(","))));
			}
			bean.setCreateTime(date);
			list.add(bean);
		}
		if(!ValidateUtils.isEmptyForCollection(list)) managerBonusMapper.inserts(list);
	}
	
	public static String getDate(int time){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, time);
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}

	/**
	 * 生成课程消耗数据
	 */
	@Override
	public void courseUse() {
		String startTime = getDate(-1)+" 00:00:00";
		String endTime = DateUtils.getLocalYmdDate()+" 23:59:59";
		String month = getDate(-1).substring(5, 7);
		Date date = new Date();
			List<BeanCourseUse> list = new ArrayList<BeanCourseUse>();	  
			for (Map<String, Object> map: useMapper.findCourseUseList(startTime,endTime)) {
				BeanCourseUse use = new BeanCourseUse();
				use.setUseMonth(month);
				use.setUid((Long)map.get("id"));
				use.setUseCourseName(String.valueOf(map.get("yItemName")));
				use.setUseCounts((Integer)map.get("ycounts"));
				use.setUseAmount(new BigDecimal((Integer)map.get("yAmount")));
				use.setUnuseCourseName(String.valueOf(map.get("wItemName")));
				use.setUnuseCounts((Integer)map.get("wcounts"));
				use.setUnuseAmount(new BigDecimal((Integer)map.get("wAmount")));
				use.setCreateTime(date);
				list.add(use);
		}
		if(!ValidateUtils.isEmptyForCollection(list)) useMapper.inserts(list);
	}

	/**
	 * 生成抵扣卷数据
	 */
	@Override
	public void courseCredits() {
		try {
			String startTime = getDate(-1)+" 00:00:00";
			String endTime = DateUtils.getLocalYmdDate()+" 23:59:59";
			String month = getDate(-1).substring(5, 7);
			Date date = new Date();
				List<BeanCourseCredits> list = new ArrayList<BeanCourseCredits>();	  
				for (Map<String, Object> map: creditsMapper.findCourseCreditsList(startTime,endTime)) {
					BeanCourseCredits credits = new BeanCourseCredits();
					credits.setMonth(month);
					credits.setUid((Long)map.get("id"));
					credits.setTotalAmount((Integer)map.get("totalAmount"));
					credits.setCouponId((String)map.get("couponId"));
					credits.setBuyAmount((Integer)map.get("buyAmount"));
					credits.setLastAmount((Integer)map.get("lastAmount"));
					credits.setUseAmount(ValidateUtils.isBlank(map.get("couponId")) ? 0 : 
						Integer.valueOf(creditsMapper.findUseCoupon(Arrays.asList(String.valueOf(map.get("couponId")).split(",")),1)));
					credits.setCourseTypeName(creditsMapper.findUseCoupon(Arrays.asList(String.valueOf(map.get("couponId")).split(",")),2));
					credits.setCreateTime(date);
					list.add(credits);
			}
			if(!ValidateUtils.isEmptyForCollection(list)) creditsMapper.inserts(list);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新讲学与陪伴订单状态
	 */
	@Override
	public void teacherOrder() {
		try {
			String date = DateUtils.getLocalYmdDate();
			int msg1 = teacherOrderMapper.upTeacherOrder(1,date);
			System.out.println("执行讲学与陪伴订单，更新开始数据："+msg1+" 条");
			int msg2 = teacherOrderMapper.upTeacherOrder(2,date);
			System.out.println("执行讲学与陪伴订单，更新结束数据："+msg2+" 条");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新课程订单或商品订单
	 */
	@Override
	public void cancellationOfOrder() {
		try {
			Calendar c = Calendar.getInstance() ;
			c.add(Calendar.MINUTE, -5) ;
			Date d = c.getTime() ;
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm") ;
			String date = sf.format(d);
			List<Long> ids = new ArrayList<Long>();
			BeanUser user = new BeanUser();
			
			//更新课程订单
			List<Map<String,Object>> list = userMapper.cancellationOfOrder(date,1);
			if(!ValidateUtils.isEmptyForCollection(list)){
				for (Map<String,Object> map : list) {
					user = userMapper.findById((Long) map.get("uid"));
					user.setBlueCurrency(user.getBlueCurrency().add(ValidateUtils.isBlank(map.get("db")) ? new BigDecimal(0) : new BigDecimal((String)map.get("db"))));
					user.setRedCurrency(user.getRedCurrency().add(ValidateUtils.isBlank(map.get("dr")) ? new BigDecimal(0) : new BigDecimal((String)map.get("dr"))));
					ids.add((Long)map.get("id"));
					userMapper.update(user);
				}
				courseOrderMapper.updateIsPayByIds(ids);
				System.out.println("执行了更新课程订单");
			}
			
			//更新商品订单
			list = userMapper.cancellationOfOrder(date,2);
			ids = new ArrayList<Long>();
			if(!ValidateUtils.isEmptyForCollection(list)){
				for (Map<String,Object> map : list) {
					user = userMapper.findById((Long) map.get("uid"));
					user.setBlueCurrency(user.getBlueCurrency().add(ValidateUtils.isBlank(map.get("db")) ? new BigDecimal(0) : new BigDecimal((String)map.get("db"))));
					user.setRedCurrency(user.getRedCurrency().add(ValidateUtils.isBlank(map.get("dr")) ? new BigDecimal(0) : new BigDecimal((String)map.get("dr"))));
					ids.add((Long)map.get("id"));
					userMapper.update(user);
				}
				productOrderMapper.updateStateByIds(ids);
				System.out.println("执行了更新商品订单");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新商品订单收货状态
	 */
	@Override
	public void productOrder() {
		try {
			String date = DateUtils.getRadomDate(-7);
			int count = productOrderMapper.updateStateByDate(date);
			System.out.println("执行了商品订单收货状态，更新了："+count+" 条");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
