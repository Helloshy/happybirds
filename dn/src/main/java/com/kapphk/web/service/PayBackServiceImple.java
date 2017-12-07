package com.kapphk.web.service;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.util.AlipayNotify;
import com.kapphk.web.bean.community.BeanCommunity;
import com.kapphk.web.bean.community.BeanUserCommunity;
import com.kapphk.web.bean.course.BeanCourse;
import com.kapphk.web.bean.course.BeanCourseOrder;
import com.kapphk.web.bean.course.BeanCourseSystem;
import com.kapphk.web.bean.course.BeanCourseType;
import com.kapphk.web.bean.course.BeanUserCoupon;
import com.kapphk.web.bean.homepage.BeanAppNewsOrder;
import com.kapphk.web.bean.product.BeanAppProductOrder;
import com.kapphk.web.bean.system.BeanSetting;
import com.kapphk.web.bean.user.BeanUser;
import com.kapphk.web.bean.user.BeanUserCurrency;
import com.kapphk.web.dao.mapper.community.BeanCommunityMapper;
import com.kapphk.web.dao.mapper.community.BeanUserCommunityMapper;
import com.kapphk.web.dao.mapper.course.BeanCourseMapper;
import com.kapphk.web.dao.mapper.course.BeanCourseOfflineMapper;
import com.kapphk.web.dao.mapper.course.BeanCourseOrderMapper;
import com.kapphk.web.dao.mapper.course.BeanCourseSystemMapper;
import com.kapphk.web.dao.mapper.course.BeanCourseTypeMapper;
import com.kapphk.web.dao.mapper.course.BeanUserCouponMapper;
import com.kapphk.web.dao.mapper.homepage.BeanAppNewsOrderMapper;
import com.kapphk.web.dao.mapper.product.BeanAppProductOrderMapper;
import com.kapphk.web.dao.mapper.system.BeanSettingMapper;
import com.kapphk.web.dao.mapper.user.BeanUserCurrencyMapper;
import com.kapphk.web.dao.mapper.user.BeanUserMapper;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.ValidateUtils;
import com.tenpay.util.XMLUtil;

/**
 * 支付回调业务层
 * @author zoneyu 16-3-15
 */
@Service("payBackService")
public class PayBackServiceImple implements PayBackService {

	//订单明细
	@Autowired
	private BeanCourseOrderMapper courseOrderMapper ;
	
	//设置
	@Autowired
	private BeanSettingMapper settingMapper;
	
	//用户
	@Autowired
	private BeanUserMapper userMapper ;
	
	//资讯赞赏
	@Autowired
	private BeanAppNewsOrderMapper newsOrderMapper ;
	
	//课程抵扣券
	@Autowired
	private BeanUserCouponMapper userCouponMapper ;
	
	@Autowired
	private BeanCommunityMapper communityMapper;
	
	@Autowired
	private BeanUserCommunityMapper userCommunityMapper;
	
	@Autowired
	private BeanCourseSystemMapper systemMapper;
	
	@Autowired
	private BeanCourseTypeMapper typeMapper;
	
	@Autowired
	private BeanCourseOfflineMapper offlineMapper;
	
	@Autowired
	private BeanCourseMapper courseMapper;
	
	//欧币流水
	@Autowired
	private BeanUserCurrencyMapper userCurrencyMapper ;
	
	@Autowired
	private BeanAppProductOrderMapper productOrderMapper;
	
	/**
	 * 支付宝回调接口
	 * @author zoneyu 16-3-15
	 */
	@SuppressWarnings("rawtypes")
	public String alipayBack(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("支付宝调用了....");
		String status = "fail" ;
		try {
			//获取支付宝POST过来反馈信息
			Map<String,String> params = new HashMap<String,String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
				params.put(name, valueStr);
			}
			
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			// 商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
			System.out.println("订单号：" + out_trade_no);
			// 买家支付宝账号
			String buyer_email = new String(request.getParameter("buyer_email").getBytes("ISO-8859-1"), "UTF-8");
			System.out.println("买家支付宝账号：" + buyer_email);
			// 交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
			System.out.println("交易状态：" + trade_status);
			// 交易总金额
			String total_fee = new String(request.getParameter("total_fee").getBytes("ISO-8859-1"), "UTF-8");
			System.out.println("总金额：" + total_fee);

			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			if(AlipayNotify.verify(params)){//验证成功
				System.out.println("支付宝验证成功");
				if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
					executeBusiness(out_trade_no) ;
				}
				status = "success" ;//请不要修改或删除
			}else{//验证失败
				System.out.println("验证失败");
				status = "fail" ;
			}
		} catch (Exception e) {
			System.out.println("验证失败");
			e.printStackTrace() ;
		}
		return status ;
	}

	public void executeBusiness(String out_trade_no) throws Exception {
		System.out.println("支付成功......");
		if(out_trade_no.indexOf("BM") >= 0){
			System.out.println("购买课程....");
			Date date = new Date();
			BeanCourseOrder order = new BeanCourseOrder() ;
			order.setOrderNo(out_trade_no);
			BeanCourseOrder oldOrder = courseOrderMapper.findEntityByCondition(order) ;
			BigDecimal payAmount = ValidateUtils.isBlank(oldOrder.getPayAmount()) ? BigDecimal.ZERO : oldOrder.getPayAmount() ;
			BigDecimal discountRed = ValidateUtils.isBlank(oldOrder.getDiscountRed()) ? BigDecimal.ZERO : oldOrder.getDiscountRed() ;
			payAmount = payAmount.add(discountRed) ;//总分佣金额
			order.setId(oldOrder.getId());
			order.setIsPay(1);
			order.setPayTime(date);
			Integer recordType = oldOrder.getRecordType() ;
			
			//存量客服  zoneyu 16-12-8
			BeanUser user = userMapper.findById(oldOrder.getUid()) ;
			if(!ValidateUtils.isBlank(user) && ValidateUtils.isBlank(user.getServiceStock()) && !ValidateUtils.isBlank(user.getCity())){
				List<Long> userList = userMapper.findStaffPositionByCity(user.getCity(),1) ;
				if(!ValidateUtils.isEmptyForCollection(userList)){
					int i = userList.size() > 1 ? new Random().nextInt(userList.size()-1) : 0;
					user.setServiceStock(userList.get(i));
					userMapper.update(user) ;
				}
			}
			
			if(recordType == 1){//线下课程
				//报课码
				String code = DataUtils.getRadom(8) ;
				boolean b = true ;
				while(b){
					BeanCourseOrder order2 = new BeanCourseOrder() ;
					order2.setCourseCode(code);
					int count = courseOrderMapper.findByPageCount(order2) ;
					if(count == 0){
						order.setCourseCode(code);
						b = false ;
					}else{
						code = DataUtils.getRadom(8) ;
					}
				}
			}else if(recordType == 2){//网络课程
				//上三级返佣
				executeBusiness2(oldOrder.getUid(), payAmount);
			}else if(recordType == 3){
				//上三级返佣
				executeBusiness2(oldOrder.getUid(), payAmount);
				
				BeanCourse course = courseMapper.findById(oldOrder.getCourseId());
				BeanCourseSystem system = new BeanCourseSystem();
				system.setSystemId(oldOrder.getCourseId());
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
						coupon.setCouponContent("可以使用此抵扣卷全额购买: "+type.getItemName());
						coupon.setCreateTime(date);
						coupon.setDateFrom(date);
						c.add(Calendar.MONTH, Integer.valueOf(type.getCourseMonth()));
						coupon.setDateTo(c.getTime());
						couponList.add(coupon);
					}else{
						BeanCourseOrder co = new BeanCourseOrder();
						co.setPayMethodType("支付方式");
						co.setIsPay(1);
						co.setIsUse(0);
						co.setCreateTime(date);
						co.setOrderNo("BM" + String.valueOf(System.nanoTime()));// 订单号
						co.setUid(user.getId());
						co.setDiscountBlue(oldOrder.getDiscountBlue());
						co.setDiscountRed(oldOrder.getDiscountRed());
						co.setSystemCourseName(course.getItemName());
						co.setCourseId(s.getCourseId());
						co.setPayAmount(oldOrder.getPayAmount());
						co.setOriginAmount(oldOrder.getOriginAmount());
						co.setPayMethod(oldOrder.getPayMethod());
						co.setPayTime(oldOrder.getPayTime());
						co.setPayStatus(1);
						co.setRecordType(2);
						orderList.add(co);
					}
				}	
				if(!ValidateUtils.isEmptyForCollection(orderList))courseOrderMapper.inserts(orderList);
				if(!ValidateUtils.isEmptyForCollection(couponList)) userCouponMapper.inserts(couponList);
				//判断该用户是不是区长或者管理人员
				BeanCommunity community = new BeanCommunity();
				community.setUid(oldOrder.getUid());
				if(ValidateUtils.isEmptyForCollection(communityMapper.findAll(community))
					&&	(communityMapper.findCountByUid(oldOrder.getUid()) == 0)) saveCommunity(oldOrder);
				
			}
			courseOrderMapper.update(order) ;
		}else if(out_trade_no.indexOf("ZS") >= 0){
			System.out.println("赞赏....");
			BeanAppNewsOrder order = new BeanAppNewsOrder() ;
			order.setOrderNo(out_trade_no);
			BeanAppNewsOrder oldOrder = newsOrderMapper.findEntityByCondition(order) ;
			order.setId(oldOrder.getId());
			order.setIsPay(1) ;
			order.setPayTime(new Date());
			newsOrderMapper.update(order) ;
		}else if(out_trade_no.indexOf("BC") >= 0){
			BeanAppProductOrder productOrder = new BeanAppProductOrder();
			productOrder.setOrderNo(out_trade_no);
			productOrder = productOrderMapper.findAll(productOrder).get(0);
			productOrder.setState(1);
			productOrder.setPayTime(new Date());
			productOrderMapper.update(productOrder);
			BeanSetting setting = settingMapper.findById("cur_percent");
			BigDecimal bigDecimal = productOrder.getPayAmount().add(productOrder.getDiscountBlue()).add(productOrder.getDiscountRed());
			int amount = (int) (bigDecimal.intValue()*Integer.valueOf(setting.getValue())*0.01);
			BeanUser user = userMapper.findById(productOrder.getUid());
			user.setBlueCurrency(user.getBlueCurrency().add(new BigDecimal(amount)));
			userMapper.update(user);
		}else{
			System.out.println("神马，都不是????");
		}
	}
	
	/**
	 * 上三级返佣
	 */
	public void executeBusiness2(Long uid,BigDecimal total){
		BigDecimal value = total.multiply(new BigDecimal(Double.valueOf("0.01"))) ;
		value = value.setScale(0, BigDecimal.ROUND_HALF_UP) ;
		if(value.compareTo(BigDecimal.ZERO) > 0){
			BeanUser buser = userMapper.findById(uid) ;
			BigDecimal blueCurrency = ValidateUtils.isBlank(buser.getBlueCurrency()) ? BigDecimal.ZERO : buser.getBlueCurrency();
			buser.setBlueCurrency(blueCurrency.add(value)) ;
			userMapper.update(buser) ;
			//添加流水
			BeanUserCurrency currency = new BeanUserCurrency() ;
			currency.setContent("购买课程返佣");
			currency.setCreateTime(new Date());
			currency.setCurrency(value);
			currency.setCurrencyType(1);
			currency.setRecordType(7);
			currency.setUid(uid);
			userCurrencyMapper.insert(currency) ;
		}
		
		BeanSetting s1 = settingMapper.findById("withhold_tax");
		BeanSetting s2 = settingMapper.findById("withhold_individual_tax");
		Float rate = (float) ((100-Float.valueOf(s1.getValue()))*0.01*((100-Float.valueOf(s2.getValue()))*0.01));
		List<Map<String,Object>> list = userMapper.getThreeUid(uid) ;
		if(!ValidateUtils.isEmptyForCollection(list)){
			for(Map<String,Object> map : list){
				Long uid2 = (Long) map.get("uid") ;
				BigDecimal rebate = (BigDecimal) map.get("rebate") ;
				BeanUser user = userMapper.findById(uid2) ;
				BigDecimal redCurrency = ValidateUtils.isBlank(user.getRedCurrency()) ? BigDecimal.ZERO : user.getRedCurrency() ; 
				BigDecimal value2 = total.multiply(rebate).multiply(new BigDecimal(rate)) ;
				value2 = value.setScale(0, BigDecimal.ROUND_HALF_UP) ;
				user.setRedCurrency(redCurrency.add(value2));
				userMapper.update(user) ;
			}
		}
	}
	
	/**
	 * 微信回调接口
	 * @author zoneyu 16-3-15
	 */
	@SuppressWarnings("unchecked")
	public String weiBack(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			System.out.println("微信支付调用了---------");
		    String resXml="";
			PrintWriter out = response.getWriter() ;
			InputStream inStream = null ;
			ByteArrayOutputStream outSteam = null ;
			try {
				inStream = request.getInputStream();
				outSteam = new ByteArrayOutputStream();
			    byte[] buffer = new byte[1024];
			    int len = 0;
			    while ((len = inStream.read(buffer)) != -1) {
			      outSteam.write(buffer, 0, len);
			    }
			    String result = new String(outSteam.toByteArray(), "utf-8");
			    Map<String,Object> map = XMLUtil.doXMLParse(result);
	            StringBuffer bufferResult = new StringBuffer();
	            Float total_fee = Float.parseFloat(map.get("total_fee").toString())/100;
	            String result_code = map.get("result_code").toString();
	            String return_code = map.get("return_code").toString();
	            String out_trade_no = map.get("out_trade_no").toString();
	            bufferResult.append("微信返回结果集：" + result+"\n");
	            bufferResult.append("通信状态result_code："+result_code+"\n");
	            bufferResult.append("交易状态：return_code：" +return_code+"\n");
	            bufferResult.append("签名sign：" + map.get("sign").toString()+"\n");
	            bufferResult.append("商品订单号：" +out_trade_no+"\n");
	            bufferResult.append("交易号："+map.get("transaction_id").toString()+"\n");
	            bufferResult.append("总金额：" + total_fee+"\n");
	            System.out.println(bufferResult.toString());
				//业务结果
				if(!result_code.equals("SUCCESS")){
		            System.out.println("交易失败："+result_code);
					resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
					+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
					out.write(resXml);
					out.flush();
					out.close();
					return "FAIL";//交易失败
				}else{
					System.out.println("验证成功.....");
					executeBusiness(out_trade_no) ;
				}
				resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
							+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
			} catch (Exception e) {
				e.printStackTrace();
				resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
						+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
			}finally{
				out.write(resXml);
				outSteam.close();
			    inStream.close();
				out.close();
			}
			return "SUCCESS";
		}

	/**
	 * 微信回调接口
	 * @author exuan 16-11-29
	 */
	public String union(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String msg = "ok" ;
		String out_trade_no = req.getParameter("out_trade_no") ;
		executeBusiness(out_trade_no) ;
		return msg ;
	}
	
	public void saveCommunity(BeanCourseOrder oldOrder){
		BeanSetting setting = settingMapper.findById("community_condition");
		if(setting.getValue().equals(oldOrder.getCourseId()+"")){
			BeanUser user = userMapper.findById(oldOrder.getUid());
			if(!ValidateUtils.isBlank(user.getUidFrom())){
				BeanUser user2 = userMapper.findById(user.getUidFrom());
				BeanCommunity community = new BeanCommunity();
				community.setUid(user2.getId());
				List<BeanCommunity> list = communityMapper.findAll(community);
				if(!ValidateUtils.isEmptyForCollection(list)){
					BeanUserCommunity userCommunity = new BeanUserCommunity();
					userCommunity.setUid(user.getId());
					userCommunityMapper.deleteByEntity(userCommunity);
					userCommunity.setCommunityId(list.get(0).getId());
					userCommunity.setCreateTime(new Date());
					userCommunityMapper.insert(userCommunity);
				}else{
					saveCommunity(user);
				}
			}else{
				saveCommunity(user);
			}
		}
	}
	
	public void saveCommunity(BeanUser user){
		if(!ValidateUtils.isBlank(user.getCity())){
			Long communityId = communityMapper.findCommunityByUid(user.getCity());
			if(!ValidateUtils.isBlank(communityId)){
				BeanUserCommunity userCommunity = new BeanUserCommunity();
				userCommunity.setUid(user.getId());
				userCommunityMapper.deleteByEntity(userCommunity);
				userCommunity.setCommunityId(communityId);
				userCommunity.setCreateTime(new Date());
				userCommunityMapper.insert(userCommunity);
			}
		}
	}
	
}

