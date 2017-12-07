package com.kapphk.web.service.inter.impl.course;

import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.kapphk.web.bean.course.BeanAppGoods;
import com.kapphk.web.bean.course.BeanCourse;
import com.kapphk.web.bean.course.BeanCourseOrder;
import com.kapphk.web.bean.course.BeanCourseStudent;
import com.kapphk.web.bean.course.BeanCourseSystem;
import com.kapphk.web.bean.course.BeanCourseType;
import com.kapphk.web.bean.course.BeanUserCoupon;
import com.kapphk.web.bean.system.BeanSetting;
import com.kapphk.web.bean.tag.BeanUseIdentity;
import com.kapphk.web.bean.user.BeanUser;
import com.kapphk.web.bean.user.BeanUserCurrency;
import com.kapphk.web.dao.mapper.course.BeanAppGoodsMapper;
import com.kapphk.web.dao.mapper.course.BeanCourseMapper;
import com.kapphk.web.dao.mapper.course.BeanCourseOrderMapper;
import com.kapphk.web.dao.mapper.course.BeanCourseStudentMapper;
import com.kapphk.web.dao.mapper.course.BeanCourseSystemMapper;
import com.kapphk.web.dao.mapper.course.BeanCourseTypeMapper;
import com.kapphk.web.dao.mapper.course.BeanUserCouponMapper;
import com.kapphk.web.dao.mapper.system.BeanSettingMapper;
import com.kapphk.web.dao.mapper.tag.BeanUseIdentityMapper;
import com.kapphk.web.dao.mapper.user.BeanUserCurrencyMapper;
import com.kapphk.web.dao.mapper.user.BeanUserMapper;
import com.kapphk.web.service.BaseServiceImpl;
import com.kapphk.web.service.inter.imethod.course.InterfaceBeanCourseOrderService;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.DateUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.HttpRequest;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;
import com.kapphk.web.utils.ValidateUtils;

/**
 * 课程订单业务层(接口)
 * @author zoneyu 16-10-14
 */
@Service("interfaceBeanCourseOrderService")
public class InterfaceBeanCourseOrderServiceImpl extends BaseServiceImpl<BeanCourseOrder, Long> implements
		InterfaceBeanCourseOrderService {

	@Autowired
	private BeanCourseOrderMapper mapper ;
	
	//抵扣券
	@Autowired
	private BeanUserCouponMapper userCouponMapper ;
	
	//用户
	@Autowired
	private BeanUserMapper userMapper ;
	
	//课程
	@Autowired
	private BeanCourseMapper courseMapper ;
	
	//课程系列
	@Autowired
	private BeanCourseTypeMapper courseTypeMapper ;
	
	//物品
	@Autowired
	private BeanAppGoodsMapper goodsMapper ;
	
	//孩子信息
	@Autowired
	private BeanCourseStudentMapper courseStudentMapper ;
	
	//系统课程
	@Autowired
	private BeanCourseSystemMapper courseSystemMapper ;
	
	//欧币流水
	@Autowired
	private BeanUserCurrencyMapper userCurrencyMapper ;
	
	@Autowired
	private BeanSettingMapper settingMapper;
	
	@Autowired
	private BeanUseIdentityMapper identityMapper;
	
	public void init() {
		super.setMapper(mapper) ;
	}

	/**
	 * 报名课程
	 * @author zoneyu 16-10-14
	 */
	public Result saveOrder(BeanCourseOrder bean, BeanCourseStudent student, String typeName, 
			Integer isCash, BigDecimal cash , String root, Integer isPublic, HttpServletRequest request,HttpServletResponse response, Result rs) throws Exception {
		Integer recordType = bean.getRecordType() ;
		if(!ValidateUtils.isBlank(bean.getCourseId()) && !ValidateUtils.isBlank(bean.getUid()) 
				&& !ValidateUtils.isBlank(recordType) ){
			bean.setOrderNo("BM"+String.valueOf(System.nanoTime()));//订单号 
			BigDecimal discountBlue = ValidateUtils.isBlank(bean.getDiscountBlue()) ? new BigDecimal("0") : bean.getDiscountBlue() ;
			BigDecimal discountRed = ValidateUtils.isBlank(bean.getDiscountRed()) ? new BigDecimal("0") : bean.getDiscountRed() ;
			Map<String,Object> dataMap = new HashMap<String, Object>() ;//返回值的map
			dataMap.put("payType", 1) ;//默认是需要跳支付的
			
			int port = request.getLocalPort() ;
			String msg = "您本次访问的端口是："+port ;
			PrintWriter out = response.getWriter() ;
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			out.write(msg);
			out.close();
			
			BeanCourse course = courseMapper.findById(bean.getCourseId()) ;
			if(course.getState() - 1 != 0){
				rs.setStatus(MSG.ERROR.getStatus()) ;
				rs.setMsg("该课程已报名结束") ;
				return rs ;
			}
			if(recordType == 1){//线下课程
				//是否是复训价
				Map<String,Object> map = mapper.checkInfo(bean) ;
				if(!ValidateUtils.isEmptyForMap(map)){
					Object isFirst = map.get("isFirst") ;
					Object amount = map.get("amount") ;
					bean.setOriginAmount(new BigDecimal(String.valueOf(amount)));//设置课程费用
					if("0".equals(String.valueOf(isFirst))){
						bean.setPayStatus(1);
					}else{
						bean.setPayStatus(2);
					}
				}
				//设置过期时间
				BeanCourseType courseType = courseTypeMapper.findById(bean.getCourseTypeId()) ;
				if(!ValidateUtils.isBlank(courseType)){
					String courseMonth = courseType.getCourseMonth() ;
					String date = "" ;
					if(!ValidateUtils.isBlank(courseMonth)){
						date = DateUtils.getRadomMonth(Integer.valueOf(courseMonth)) ;
					}else{
						date = DateUtils.getRadomMonth(1) ;
					}
					date = date + " 23:59:59" ;
					bean.setExpirationDate(DateUtils.parseDate("yyyy-MM-dd HH:mm:ss", date));
				}else{
					rs.setStatus(MSG.ERROR.getStatus()) ;
					rs.setMsg("课程系列信息有误") ;
					return rs ;
				}
			}else{
				//修改  zoneyu 16-11-25
				if(!ValidateUtils.isBlank(isCash) && isCash == 1){
					bean.setPayStatus(3);//现金赞赏
					bean.setOriginAmount(cash) ;
				}else{
					if(!ValidateUtils.isBlank(course)){
						bean.setOriginAmount(course.getAmount()) ;
					}else{
						rs.setStatus(MSG.ERROR.getStatus()) ;
						rs.setMsg("课程信息有误") ;
						return rs ;
					}
				}
			}
			
			if(!ValidateUtils.isBlank(bean.getCouponId())){//使用了抵扣券
				dataMap.put("payType", 0) ;//不需要跳支付
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
				//判断抵扣券是否使用了
				BeanUserCoupon buc = new BeanUserCoupon() ;
				buc.setUid(bean.getUid());
				buc.setId(bean.getCouponId());
				buc.setState(0);
				BeanUserCoupon coupon = userCouponMapper.findEntityByCondition(buc) ;
				if(!ValidateUtils.isBlank(coupon)){
					bean.setIsCoupon(1);
					bean.setPayMethod("课程抵扣券");
					bean.setIsPay(1);//已支付
					bean.setPayTime(new Date());
					bean.setPayAmount(new BigDecimal("0"));
					
					//更新抵扣券为已使用
					Date start = coupon.getDateFrom() ;//开始时间
					Date end = coupon.getDateTo() ;//结束时间
					long startTime = start.getTime() ;
					long endTime = end.getTime() ;
					long now = new Date().getTime() ;
					if(now - startTime < 0){
						rs.setStatus(MSG.ERROR.getStatus()) ;
						rs.setMsg("抵扣券使用时间未开始") ;
						return rs ;
					}else if(endTime - now < 0){
						rs.setStatus(MSG.ERROR.getStatus()) ;
						rs.setMsg("抵扣券已过期") ;
						return rs ;
					}else{
						coupon.setState(1);
						coupon.setCreateTime(new Date());
						userCouponMapper.update(coupon) ;
					}
				}else{
					rs.setStatus(MSG.ERROR.getStatus()) ;
					rs.setMsg("抵扣券已使用") ;
					return rs ;
				}
			}else if(!ValidateUtils.isBlank(isPublic) && isPublic == 1){//公益课
				dataMap.put("payType", 0) ;//不需要跳支付
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
				bean.setIsPay(1);
				bean.setPayMethodType("支付方式");
				bean.setPayTime(new Date());
			}else if(!ValidateUtils.isBlank(bean.getGoodsId())){//物品赞赏
				dataMap.put("payType", 0) ;
				BeanAppGoods goods = goodsMapper.findById(bean.getGoodsId()) ;
				Integer qty = bean.getQty() ;
				BigDecimal currency = goods.getCurrency() ;
				currency = currency.multiply(new BigDecimal(String.valueOf(qty))) ;
				//判断欧币数是否一致
				int to = discountBlue.add(discountRed).subtract(currency).compareTo(BigDecimal.ZERO) ;
				if(to == 0){
					//扣除用户的蓝币跟红币
					BeanUser user = userMapper.findById(bean.getUid()) ;
					BigDecimal blueCurrency = user.getBlueCurrency() ;
					BigDecimal redCurrency = user.getRedCurrency() ;
					int blue = blueCurrency.subtract(discountBlue).compareTo(BigDecimal.ZERO) ;
					int red = redCurrency.subtract(discountRed).compareTo(BigDecimal.ZERO) ;
					if(blue == -1){
						rs.setStatus(MSG.ERROR.getStatus()) ;
						rs.setMsg("蓝币不足") ;
						return rs ;
					}else if(red == -1){
						rs.setStatus(MSG.ERROR.getStatus()) ;
						rs.setMsg("红币不足") ;
						return rs ;
					}else{
						BeanUser buser = new BeanUser() ;
						buser.setId(bean.getUid());
						buser.setBlueCurrency(blueCurrency.subtract(discountBlue));
						buser.setRedCurrency(redCurrency.subtract(discountRed));
						userMapper.update(buser) ;
						//添加流水
						if(!ValidateUtils.isBlank(bean.getDiscountBlue()) && bean.getDiscountBlue().compareTo(BigDecimal.ZERO) == 1){
							BeanUserCurrency cy = new BeanUserCurrency() ;
							cy.setContent("赞赏");
							cy.setCreateTime(new Date());
							cy.setCurrency(bean.getDiscountBlue().multiply(new BigDecimal(-1)));
							cy.setCurrencyType(1);
							cy.setRecordType(3);
							cy.setUid(bean.getUid());
							userCurrencyMapper.insert(cy) ;
						}
						if(!ValidateUtils.isBlank(bean.getDiscountRed()) && bean.getDiscountRed().compareTo(BigDecimal.ZERO) == 1){
							BeanUserCurrency cy = new BeanUserCurrency() ;
							cy.setContent("赞赏");
							cy.setCreateTime(new Date());
							cy.setCurrency(bean.getDiscountBlue().multiply(new BigDecimal(-1)));
							cy.setCurrencyType(2);
							cy.setRecordType(3);
							cy.setUid(bean.getUid());
							userCurrencyMapper.insert(cy) ;
						}
					}
					bean.setOriginAmount(currency);//总费用
					bean.setPayTime(new Date());//支付时间
					bean.setIsPay(1);//已支付
					bean.setPayStatus(3);//赞赏
				}else{
					rs.setStatus(MSG.ERROR.getStatus()) ;
					rs.setMsg("欧币数不一致") ;
					return rs ;
				}
			}else{
				bean.setIsCoupon(0);
				//计算需要支付的金额，课程费用-蓝币-红币
				BigDecimal originAmount = bean.getOriginAmount() ;//课程总费用
				BigDecimal subtract = originAmount.subtract(discountBlue).subtract(discountRed) ;
				int i = subtract.compareTo(BigDecimal.ZERO);
				if(i == -1){
					rs.setStatus(MSG.ERROR.getStatus()) ;
					rs.setMsg("金额有误") ;
					return rs ;
				}else{
					if(i == 1){
						//判断是否是线下支付
						String payMethod = bean.getPayMethod() ;
						if(!"线下支付".equals(payMethod)){
							dataMap.put("payType", 1) ;
						}else{
							dataMap.put("payType", 0) ;
						}
					}else{
						dataMap.put("payType", 0) ;
						bean.setIsPay(1);//已支付
						bean.setPayTime(new Date());
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
								code = DataUtils.getRadom(16) ;
							}
						}
					}
					bean.setPayAmount(subtract);
				}
				//扣除用户的蓝币跟红币
				BeanUser user = userMapper.findById(bean.getUid()) ;
				BigDecimal blueCurrency = user.getBlueCurrency() ;
				BigDecimal redCurrency = user.getRedCurrency() ;
				int blue = blueCurrency.subtract(discountBlue).compareTo(BigDecimal.ZERO) ;
				int red = redCurrency.subtract(discountRed).compareTo(BigDecimal.ZERO) ;
				if(blue == -1){
					rs.setStatus(MSG.ERROR.getStatus()) ;
					rs.setMsg("蓝币不足") ;
					return rs ;
				}else if(red == -1){
					rs.setStatus(MSG.ERROR.getStatus()) ;
					rs.setMsg("红币不足") ;
					return rs ;
				}else{
					BeanUser buser = new BeanUser() ;
					buser.setId(bean.getUid());
					buser.setBlueCurrency(blueCurrency.subtract(discountBlue));
					buser.setRedCurrency(redCurrency.subtract(discountRed));
					userMapper.update(buser) ;
					String content = recordType - 1 == 0 ? "线虾课程抵扣"  : "网络课程抵扣";
					//添加流水
					if(!ValidateUtils.isBlank(bean.getDiscountBlue()) && bean.getDiscountBlue().compareTo(BigDecimal.ZERO) == 1){
						BeanUserCurrency cy = new BeanUserCurrency() ;
						cy.setContent(content);
						cy.setCreateTime(new Date());
						cy.setCurrency(bean.getDiscountBlue().multiply(new BigDecimal(-1)));
						cy.setCurrencyType(1);
						cy.setRecordType(4);
						cy.setUid(bean.getUid());
						userCurrencyMapper.insert(cy) ;
					}
					if(!ValidateUtils.isBlank(bean.getDiscountRed()) && bean.getDiscountRed().compareTo(BigDecimal.ZERO) == 1){
						BeanUserCurrency cy = new BeanUserCurrency() ;
						cy.setContent(content);
						cy.setCreateTime(new Date());
						cy.setCurrency(bean.getDiscountBlue().multiply(new BigDecimal(-1)));
						cy.setCurrencyType(2);
						cy.setRecordType(4);
						cy.setUid(bean.getUid());
						userCurrencyMapper.insert(cy) ;
					}
					
				}
			}
			String method = bean.getPayMethod() ;
			if("银联".equals(method)){
				if(ValidateUtils.isBlank(root)){
					rs.setStatus(MSG.ERROR.getStatus()) ;
					rs.setMsg("请传入root参数") ;
					return rs ;
				}
			}
			
			if("孩子课程".equals(typeName)){
				bean.setStudentPhone(student.getParentPhone());//设置订单表中的学员手机号
				student.setCreateTime(new Date());
				
				bean.setCreateTime(new Date());
				bean.setPayMethodType("支付方式");
				mapper.insert(bean) ;
				
				student.setId(bean.getId());
				courseStudentMapper.insert(student) ;
			}else{
				bean.setCreateTime(new Date());
				if(!ValidateUtils.isBlank(bean.getGoodsId())){
					bean.setPayMethodType("欧币支付");
				}else{
					bean.setPayMethodType("支付方式");
				}
				mapper.insert(bean) ;
			}
			dataMap.put("orderNo", bean.getOrderNo()) ;
			dataMap.put("goodsName", course.getItemName()) ;
			if(!ValidateUtils.isBlank(isCash) && isCash == 1){
				dataMap.put("description", "赞赏课程") ;
			}else{
				dataMap.put("description", "购买课程") ;
			}
			dataMap.put("price", bean.getPayAmount()) ;
			
			Properties prop = new Properties() ;
			InputStream in = this.getClass().getClassLoader().getResourceAsStream("info.properties") ;
			prop.load(in);
			
			if("支付宝".equals(method)){
				dataMap.put("notifyUrl", "/payBack/alipayBack.do") ;
				dataMap.put("partner", prop.get("alipay_partner")) ;
				dataMap.put("seller", prop.get("alipay_seller")) ;
				dataMap.put("key", prop.get("alipay_key")) ;
				rs.put("info", dataMap) ;
			}else if("微信".equals(method)){
				dataMap.put("notifyUrl", "/payBack/weiBack.do") ;
				dataMap.put("appId", prop.get("wx_app_id")) ;
				dataMap.put("appSecret", prop.get("wx_app_secret")) ;
				dataMap.put("partner", prop.get("wx_partner")) ;
				dataMap.put("partnerKey", prop.get("wx_partner_key")) ;
				rs.put("info", dataMap) ;
			}else if("银联".equals(method)){
				String merId = prop.getProperty("nion_merId") ;
				String txnAmt = String.format("%.0f", bean.getOriginAmount().multiply(new BigDecimal("100"))) ;
				String orderId = bean.getOrderNo() ;
				String txnTime = DateUtils.getLocalDate("yyyyMMddHHmmss") ;
				String url = root +"/ACPSample_AppServer/form05_6_2_AppConsume" ;
				String param = "merId="+merId+"&txnAmt="+txnAmt+ "&orderId="+orderId+"&txnTime="+txnTime ;
				System.out.println("请求链接："+url+param);
				String json = HttpRequest.sendGet(url, param) ;
				JSONObject parseObject = JSONObject.parseObject(json) ;
				if(!ValidateUtils.isBlank(parseObject)){
					boolean b = (Boolean)parseObject.get("status") ;
					if(b){
						dataMap.put("tn", parseObject.get("tn")) ;
						rs.put("info", dataMap) ;
					}else{
						rs.setStatus(MSG.ERROR.getStatus()) ;
						rs.setMsg(String.valueOf(parseObject.get("msg"))) ;
					}
				}
			}else{
				rs.put("info", dataMap) ;
			}
		}else{
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("参数错误") ;
		}
		return rs ;
	}
	
	/**
	 * 是否享受复训价
	 * @author zoneyu 16-10-14
	 */
	public Result checkInfo(BeanCourseOrder bean, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(bean.getCourseTypeId()) && !ValidateUtils.isBlank(bean.getStudentIdCard())){
			Map<String,Object> map = mapper.checkInfo(bean) ;
			rs.put("info", map) ;
		}else{
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("参数错误") ;
		}
		return rs ;
	}

	/**
	 * 支付成功获取订单信息
	 * @author zoneyu 16-10-17
	 */
	public Result getOrderInfo(BeanCourseOrder bean, Result rs) throws Exception {
		BeanCourseOrder order = mapper.findEntityByCondition(bean) ;
		if(!ValidateUtils.isBlank(order)){
			Map<String,Object> map = mapper.getOrderInfo(bean.getOrderNo()) ;
			if(!ValidateUtils.isEmptyForMap(map)){
				String courseCode = (String)map.get("courseCode") ;
				String showCode = courseCode.substring(0, 4)+" "+courseCode.substring(4, 8) ;
				map.put("showCode", showCode) ;
			}
			BigDecimal payAmount = ValidateUtils.isBlank(bean.getPayAmount()) ? BigDecimal.ZERO : bean.getPayAmount() ;
			BigDecimal discountRed = ValidateUtils.isBlank(bean.getDiscountRed()) ? BigDecimal.ZERO : bean.getDiscountRed() ;
			BigDecimal total = payAmount.add(discountRed) ;
			BigDecimal value = total.multiply(new BigDecimal(Double.valueOf("0.01"))) ;
			value = value.setScale(0, BigDecimal.ROUND_HALF_UP) ;
			map.put("currency", value) ;
			rs.put("info", map) ;
		}else{
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("订单有误") ;
		}
		return rs ;
	}

	/**
	 * 课程信息
	 * @author zoneyu 16-10-18
	 */
	public Result getCourseInfo(BeanCourseOrder bean, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(bean.getCourseCode())){
			Map<String,Object> map = mapper.getCourseInfo(bean) ;
			if(!ValidateUtils.isEmptyForMap(map)){
				Date expirationDate =  (Date) map.get("expirationDate") ;
				if(!ValidateUtils.isBlank(expirationDate)){
					long time = expirationDate.getTime() ;
					long now = new Date().getTime() ;
					if(now > time){
						map.put("isUse", 2) ;//已过期
					}
				}else{
					map.put("isUse", 2) ;//已过期
				}
			}
			rs.put("info", map) ;
		}else{
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("参数错误");
		}
		return rs;
	}

	/**
	 * 学员信息
	 * @author zoneyu 16-10-18
	 */
	public Result getStudentInfo(Long id, Integer type, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(id) && !ValidateUtils.isBlank(type)){
			if(type == 1){//成人课程&精英课程
				Map<String,Object> map = mapper.getStudentInfoByParent(id) ;
				rs.put("parentInfo", map) ;
			}else if(type == 2){//孩子课程
				Map<String,Object> map = mapper.getStudentInfoByChild(id) ;
				rs.put("childInfo", map) ;
			}
		}else{
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("参数错误");
		}
		return rs;
	}

	/**
	 * 验证课程信息
	 * @author zoneyu 16-10-18
	 */
	public Result passed(Long id, Long uid, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(id) && !ValidateUtils.isBlank(uid)){
			//判断当前人是否有验证权限
			BeanUser user = userMapper.findById(uid) ;
			Integer isPermissions = user.getIsPermissions() ;
			if(isPermissions == 1){
				BeanCourseOrder bean = mapper.findById(id) ;
				if(!ValidateUtils.isBlank(bean)){
					if(!ValidateUtils.isBlank(bean.getIsUse()) && bean.getIsUse() == 0){
						if(bean.getIsPay() == 1){
							//判断是否过期
							Date expirationDate = bean.getExpirationDate() ;
							if(!ValidateUtils.isBlank(expirationDate)){
								long time = expirationDate.getTime() ;
								long now = new Date().getTime() ;
								if(time - now > 0){
									BeanCourseOrder bean2 = new BeanCourseOrder() ;
									bean2.setId(id);
									bean2.setValidationTime(new Date());
									bean2.setValidationUid(uid);
									bean2.setIsUse(1);
									mapper.update(bean2) ;
									
									//16-11-23 添加分佣
									Integer isCoupon = bean.getIsCoupon() ;
									if(isCoupon == 0){//没有使用抵扣券，才会计算返佣
										//1、给自己返佣1%的蓝币
										BigDecimal discountRed = ValidateUtils.isBlank(bean.getDiscountRed()) ? BigDecimal.ZERO : bean.getDiscountRed() ;
										BigDecimal payAmount = ValidateUtils.isBlank(bean.getPayAmount()) ? BigDecimal.ZERO : bean.getPayAmount() ;
										BigDecimal total = discountRed.add(payAmount) ;//总返佣金额
										BigDecimal value = total.multiply(new BigDecimal(Double.valueOf("0.01"))) ;
										value = value.setScale(0, BigDecimal.ROUND_HALF_UP) ;
										if(value.compareTo(BigDecimal.ZERO) > 0){
											BeanUser buser = userMapper.findById(bean.getUid()) ;
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
										//2、给上三级返佣
										executeBusiness(uid,total);
									}
								}else{
									rs.setStatus(MSG.ERROR.getStatus());
									rs.setMsg("验证失败，订单已过期");
								}
							}else{
								rs.setStatus(MSG.ERROR.getStatus());
								rs.setMsg("验证失败，有效期未设置");
							}
						}else{
							rs.setStatus(MSG.ERROR.getStatus());
							rs.setMsg("订单未支付");
						}
					}else{
						rs.setStatus(MSG.ERROR.getStatus());
						rs.setMsg("该课程已经验证过了");
					}
				}else{
					rs.setStatus(MSG.ERROR.getStatus());
					rs.setMsg("数据有误");
				}
			}else{
				rs.setStatus(MSG.ERROR.getStatus());
				rs.setMsg("您没有验证课程的权限");
			}
		}else{
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("参数错误");
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
				BigDecimal rebate = (BigDecimal) map.get("rebate") ;
				BeanUser user = userMapper.findById(uid2) ;
				BigDecimal redCurrency = ValidateUtils.isBlank(user.getRedCurrency()) ? BigDecimal.ZERO : user.getRedCurrency() ; 
				BigDecimal value = total.multiply(rebate).multiply(new BigDecimal(rate)) ;
				value = value.setScale(0, BigDecimal.ROUND_HALF_UP) ;
				if(value.compareTo(BigDecimal.ZERO) > 0){
					user.setRedCurrency(redCurrency.add(value));
					userMapper.update(user) ;
				}
			}
		}
	}

	/**
	 * 赞赏记录
	 * @author zoneyu 16-10-20
	 */
	public Result getAdmirationList(Long courseId ,Integer page , Result rs) throws Exception {
		page = ValidateUtils.isBlank(page) ? 1 : page ;
		if(!ValidateUtils.isBlank(courseId)){
			List<Map<String,Object>> list = mapper.getAdmirationList(courseId,(page-1)*ROWS,ROWS) ;
			rs.put("info", list) ;
		}else{
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("参数错误");
		}
		return rs;
	}

	/**
	 * 课程订单--线下课程
	 * @author zoneyu 16-10-24
	 */
	public Result getOfflineOrderList(Long uid, Integer status, Integer page, Result rs) throws Exception {
		page = ValidateUtils.isBlank(page) ? 1 : page ;
		if(!ValidateUtils.isBlank(uid) && !ValidateUtils.isBlank(status)){
			List<Map<String,Object>> list = mapper.getOfflineOrderList(uid,status,(page-1)*ROWS,ROWS) ;
			if(!ValidateUtils.isEmptyForCollection(list)){
				for(Map<String,Object> map : list){
					Date expirationDate =  (Date) map.get("expirationDate") ;
					if(!ValidateUtils.isBlank(expirationDate)){
						long time = expirationDate.getTime() ;
						long now = new Date().getTime() ;
						if(now > time){
							map.put("isUse", 2) ;//已过期
						}
					}else{
						map.put("isUse", 2) ;//已过期
					}
				}
			}
			rs.put("info", list) ;
		}else{
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("参数错误");
		}
		return rs;
	}

	/**
	 * 课程订单--网络课程
	 * @author zoneyu 16-10-26
	 */
	public Result getOnlineOrderList(Long uid, Integer page, Result rs) throws Exception {
		page = ValidateUtils.isBlank(page) ? 1 : page ;
		if(!ValidateUtils.isBlank(uid)){
			List<Map<String,Object>> list = mapper.getOnlineOrderList(uid,(page-1)*ROWS,ROWS) ;
			rs.put("info", list) ;
		}else{
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("参数错误");
		}
		return rs ;
	}

	/**
	 * 获取个人管理的客户数据
	 */
	@Override
	public Result getClientList(Long uid, String param,Integer page, Result rs)
			throws Exception {
		if(!ValidateUtils.isBlank(uid)){
			String staffPosition = getStaffPosition(uid);
			if(ValidateUtils.isBlank(staffPosition)){
				rs.setStatus(MSG.ERROR.getStatus());
				rs.setMsg("员工岗位必须是存量客服或增量客服");
				return rs;
			}
			page = ValidateUtils.isBlank(page) ? 1 : page;
			rs.put("info", mapper.getClientList(uid,param,staffPosition,(page-1)*GridReq.ROWS,GridReq.ROWS));
		}else{
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("参数错误");
		}
		return rs;
	}

	/**
	 * 获取客户课程订单列表
	 */
	@Override
	public Result getCourseOrderList(Long uid,  Long id, Integer page, Result rs)
			throws Exception {
		if(!ValidateUtils.isBlank(uid) && !ValidateUtils.isBlank(id)){
			String staffPosition = getStaffPosition(id);
			if(ValidateUtils.isBlank(staffPosition)){
				rs.setStatus(MSG.ERROR.getStatus());
				rs.setMsg("员工岗位必须是存量客服或增量客服");
				return rs;
			}
			page = ValidateUtils.isBlank(page) ? 1 : page;
			rs.put("info", mapper.getCourseOrderList(uid,staffPosition,(page-1)*GridReq.ROWS,GridReq.ROWS));
		}else{
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("参数错误");
		}
		return rs;
	}

	/**
	 * 获取课程订单详情
	 */
	@Override
	public Result getCourseOrderDetail(Long id, Long uid, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(id) && !ValidateUtils.isBlank(uid)){
			String staffPosition = getStaffPosition(uid);
			if(ValidateUtils.isBlank(staffPosition)){
				rs.setStatus(MSG.ERROR.getStatus());
				rs.setMsg("员工岗位必须是存量客服或增量客服");
				return rs;
			}
			rs.put("info", mapper.getCourseOrderDetail(id,staffPosition));
		}else{
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("参数错误");
		}
		return rs;
	}

	/**
	  * 保存系统课程
	  * @author zoneyu 16-11-22
	  */
	public Result saveSystemOrder(BeanCourseOrder bean,String root,HttpServletRequest request,HttpServletResponse response, Result rs) throws Exception {
		Map<String,Object> dataMap = new HashMap<String, Object>() ;//返回值的map
		if(!ValidateUtils.isBlank(bean.getCourseId()) && !ValidateUtils.isBlank(bean.getUid())
				&& !ValidateUtils.isBlank(bean.getPayMethod())){
			dataMap.put("payType", 1) ;//默认是需要跳支付的
			bean.setOrderNo("BM"+String.valueOf(System.nanoTime()));//订单号 
			//查询课程总金额
			BeanCourse course = courseMapper.findById(bean.getCourseId()) ;
			if(!ValidateUtils.isBlank(course)){
				BigDecimal amount = course.getAmount() ;
				bean.setOriginAmount(amount);//课程总费用
				BigDecimal discountBlue = ValidateUtils.isBlank(bean.getDiscountBlue()) ? BigDecimal.ZERO : bean.getDiscountBlue() ;//抵扣的蓝币
				BigDecimal discountRed = ValidateUtils.isBlank(bean.getDiscountRed()) ? BigDecimal.ZERO : bean.getDiscountRed() ;//抵扣的红币
				discountBlue = discountBlue.add(discountRed) ;
				int i = discountBlue.subtract(amount).compareTo(BigDecimal.ZERO) ;
				if(i == 0){//蓝币+红币等于课程费用
					bean.setPayAmount(BigDecimal.ZERO);
					bean.setIsPay(1);
					bean.setPayTime(new Date());
					dataMap.put("payType", 0) ;
				}else{//蓝币+红币小于课程费用
					//计算需要支付的金额，课程费用-蓝币-红币
					if(i == 1){
						rs.setStatus(MSG.ERROR.getStatus()) ;
						rs.setMsg("金额有误") ;
						return rs ;
					}else{
						if(i == -1){
							//判断是否是线下支付
							String payMethod = bean.getPayMethod() ;
							if(!"线下支付".equals(payMethod)){
								dataMap.put("payType", 1) ;
							}else{
								dataMap.put("payType", 0) ;
							}
						}else{
							dataMap.put("payType", 0) ;
						}
						bean.setIsPay(0);
						bean.setPayAmount(amount.subtract(discountBlue));
					}
				}
				//扣除用户的蓝币跟红币
				BeanUser user = userMapper.findById(bean.getUid()) ;
				BigDecimal blueCurrency = user.getBlueCurrency() ;
				BigDecimal redCurrency = user.getRedCurrency() ;
				int blue = blueCurrency.subtract(discountBlue).compareTo(BigDecimal.ZERO) ;
				int red = redCurrency.subtract(discountRed).compareTo(BigDecimal.ZERO) ;
				if(blue == -1){
					rs.setStatus(MSG.ERROR.getStatus()) ;
					rs.setMsg("蓝币不足") ;
					return rs ;
				}else if(red == -1){
					rs.setStatus(MSG.ERROR.getStatus()) ;
					rs.setMsg("红币不足") ;
					return rs ;
				}else{
					BeanUser buser = new BeanUser() ;
					buser.setId(bean.getUid());
					buser.setBlueCurrency(blueCurrency.subtract(discountBlue));
					buser.setRedCurrency(redCurrency.subtract(discountRed));
					userMapper.update(buser) ;
					
					//添加流水
					if(!ValidateUtils.isBlank(bean.getDiscountBlue()) && bean.getDiscountBlue().compareTo(BigDecimal.ZERO) == 1){
						BeanUserCurrency cy = new BeanUserCurrency() ;
						cy.setContent("系统课程抵扣");
						cy.setCreateTime(new Date());
						cy.setCurrency(bean.getDiscountBlue().multiply(new BigDecimal(-1)));
						cy.setCurrencyType(1);
						cy.setRecordType(4);
						cy.setUid(bean.getUid());
						userCurrencyMapper.insert(cy) ;
					}
					if(!ValidateUtils.isBlank(bean.getDiscountRed()) && bean.getDiscountRed().compareTo(BigDecimal.ZERO) == 1){
						BeanUserCurrency cy = new BeanUserCurrency() ;
						cy.setContent("系统课程抵扣");
						cy.setCreateTime(new Date());
						cy.setCurrency(bean.getDiscountBlue().multiply(new BigDecimal(-1)));
						cy.setCurrencyType(2);
						cy.setRecordType(4);
						cy.setUid(bean.getUid());
						userCurrencyMapper.insert(cy) ;
					}
					
				}
				//生成课程内容的订单
				BeanCourseSystem system = new BeanCourseSystem() ;
				system.setCourseId(bean.getCourseId()) ;
				List<BeanCourseSystem> list = courseSystemMapper.findAll(system) ;
				List<BeanCourseOrder> courseList = new ArrayList<BeanCourseOrder>() ;
				if(!ValidateUtils.isEmptyForCollection(list)){
					for(BeanCourseSystem cs : list){
						Integer recordType = cs.getRecordType() ;
						if(recordType == 2){//网络课程
							BeanCourseOrder onlineOrder = new BeanCourseOrder() ;
							onlineOrder.setAddress(bean.getAddress());
							onlineOrder.setCourseId(cs.getCourseId());
							onlineOrder.setCreateTime(new Date());
							onlineOrder.setIsCoupon(0);
							onlineOrder.setIsPay(0);
							onlineOrder.setOrderNo(bean.getOrderNo());
							onlineOrder.setOriginAmount(bean.getOriginAmount()) ;
							onlineOrder.setPayAmount(bean.getOriginAmount());
							onlineOrder.setPayMethod(bean.getPayMethod());
							onlineOrder.setPayMethodType("支付方式");
							onlineOrder.setRecordType(2) ;
							onlineOrder.setPayStatus(1);
							onlineOrder.setStudentIdCard(bean.getStudentIdCard());
							onlineOrder.setStudentName(bean.getStudentName());
							onlineOrder.setStudentPhone(bean.getStudentPhone());
							onlineOrder.setSystemCourseName(course.getItemName());
							onlineOrder.setUid(bean.getUid());
							onlineOrder.setIsUse(0);
							courseList.add(onlineOrder) ;
						}else{
							rs.setStatus(MSG.ERROR.getStatus());
							rs.setMsg("课程信息有误");
							return rs ;
						}
					}
				}
				bean.setCreateTime(new Date());
				bean.setPayStatus(1);
				bean.setIsUse(0);
				bean.setRecordType(3) ;
				bean.setPayMethodType("支付方式");
				courseList.add(bean) ;
				if(!ValidateUtils.isEmptyForCollection(courseList)) mapper.inserts(courseList) ;
				//组装给前端的数据
				dataMap.put("orderNo", bean.getOrderNo()) ;
				dataMap.put("goodsName", course.getItemName()) ;
				dataMap.put("description", "购买课程") ;
				dataMap.put("price", bean.getPayAmount()) ;
				
				Properties prop = new Properties() ;
				InputStream in = this.getClass().getClassLoader().getResourceAsStream("info.properties") ;
				prop.load(in);
				
				String method = bean.getPayMethod() ;
				if("银联".equals(method)){
					if(ValidateUtils.isBlank(root)){
						rs.setStatus(MSG.ERROR.getStatus()) ;
						rs.setMsg("请传入root参数") ;
						return rs ;
					}
				}
				
				if("支付宝".equals(method)){
					dataMap.put("notifyUrl", "/payBack/alipayBack.do") ;
					dataMap.put("partner", prop.get("alipay_partner")) ;
					dataMap.put("seller", prop.get("alipay_seller")) ;
					dataMap.put("key", prop.get("alipay_key")) ;
					rs.put("info", dataMap) ;
				}else if("微信".equals(method)){
					dataMap.put("notifyUrl", "/payBack/weiBack.do") ;
					dataMap.put("appId", prop.get("wx_app_id")) ;
					dataMap.put("appSecret", prop.get("wx_app_secret")) ;
					dataMap.put("partner", prop.get("wx_partner")) ;
					dataMap.put("partnerKey", prop.get("wx_partner_key")) ;
					rs.put("info", dataMap) ;
				}else if("银联".equals(method)){
					String merId = prop.getProperty("nion_merId") ;
					String txnAmt = String.format("%.0f", bean.getOriginAmount().multiply(new BigDecimal("100"))) ;
					String orderId = bean.getOrderNo() ;
					String txnTime = DateUtils.getLocalDate("yyyyMMddHHmmss") ;
					String url = root +"/ACPSample_AppServer/form05_6_2_AppConsume" ;
					String param = "merId="+merId+"&txnAmt="+txnAmt+ "&orderId="+orderId+"&txnTime="+txnTime ;
					System.out.println("请求链接："+url+param);
					String json = HttpRequest.sendGet(url, param) ;
					JSONObject parseObject = JSONObject.parseObject(json) ;
					if(!ValidateUtils.isBlank(parseObject)){
						boolean b = (Boolean)parseObject.get("status") ;
						if(b){
							dataMap.put("tn", parseObject.get("tn")) ;
							rs.put("info", dataMap) ;
						}else{
							rs.setStatus(MSG.ERROR.getStatus()) ;
							rs.setMsg(String.valueOf(parseObject.get("msg"))) ;
						}
					}
				}else{
					rs.put("info", dataMap) ;
				}
			}else{
				rs.setStatus(MSG.ERROR.getStatus());
				rs.setMsg("课程信息有误");
			}
		}else{
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("参数错误");
		}
		return rs;
	}

	/**
	 * 取消订单返蓝币红币
	 */
	@Override
	public Result cancelOrder(String orderNo, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(orderNo)){
			BeanCourseOrder order = new BeanCourseOrder();
			order.setOrderNo(orderNo);
			order = mapper.findEntityByCondition(order);
			if(!ValidateUtils.isBlank(order) && order.getIsPay() - 0 == 0){
				BeanUser user = userMapper.findById(order.getUid());
				user.setBlueCurrency(user.getBlueCurrency().add(order.getDiscountBlue()));
				user.setRedCurrency(user.getRedCurrency().add(order.getDiscountRed()));
				order.setIsPay(2);
				mapper.update(order);
				userMapper.update(user);
			}
		}else{
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("参数错误");
		}
		return rs;
	}
	
	private String getStaffPosition(Long id){
		String staffPosition = "";
		BeanUseIdentity ui = new BeanUseIdentity();
		ui.setTagType("员工岗位");
		ui.setTagIdentity("存量客服");
		ui.setUid(id);
		List<BeanUseIdentity> list = identityMapper.findAll(ui);
		if(!ValidateUtils.isEmptyForCollection(list)){
			staffPosition = "存量客服";
		}else{
			ui.setTagIdentity("增量客服");
			list = identityMapper.findAll(ui);
			if(!ValidateUtils.isEmptyForCollection(list)) staffPosition = "增量客服";
		}
		
		return staffPosition;
		
	}
	
}
