package com.kapphk.web.service.inter.impl.homepage;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.kapphk.web.bean.homepage.BeanAppNewsOrder;
import com.kapphk.web.bean.user.BeanUser;
import com.kapphk.web.dao.mapper.homepage.BeanAppNewsOrderMapper;
import com.kapphk.web.dao.mapper.user.BeanUserMapper;
import com.kapphk.web.service.BaseServiceImpl;
import com.kapphk.web.service.inter.imethod.homepage.InterfaceBeanAppNewsOrderService;
import com.kapphk.web.utils.DateUtils;
import com.kapphk.web.utils.HttpRequest;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;
import com.kapphk.web.utils.ValidateUtils;

/**
 * 资讯赞赏业务层(接口)
 * @author zoneyu 16-10-8
 */
@Service("interfaceBeanAppNewsOrderService")
public class InterfaceBeanAppNewsOrderServiceImpl extends BaseServiceImpl<BeanAppNewsOrder, Long> implements
		InterfaceBeanAppNewsOrderService {

	@Autowired
	private BeanAppNewsOrderMapper mapper ;
	
	//用户
	@Autowired
	private BeanUserMapper userMapper ;
	
	public void init() {
		super.setMapper(mapper) ;
	}

	/**
	 * 保存赞赏订单
	 * @author zoneyu 16-10-19
	 */
	public Result saveOrder(BeanAppNewsOrder bean, String root, Result rs) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>() ;
		bean.setOrderNo("ZS"+String.valueOf(System.nanoTime()));//订单号 
		String payMethod = bean.getPayMethod() ;
		if(!ValidateUtils.isBlank(payMethod)){
			if(payMethod.contains("币")){
				bean.setPayMethodType("欧币支付");
				bean.setPayTime(new Date());
				bean.setIsPay(1);
				map.put("payType", 0) ;//不需要跳支付
				
				//扣除用户的蓝币跟红币
				BeanUser user = userMapper.findById(bean.getUid()) ;
				BigDecimal discountBlue = ValidateUtils.isBlank(bean.getDiscountBlue()) ? BigDecimal.ZERO : bean.getDiscountBlue() ;
				BigDecimal discountRed = ValidateUtils.isBlank(bean.getDiscountRed()) ? BigDecimal.ZERO : bean.getDiscountRed() ;
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
				}
			}else{
				bean.setPayMethodType("支付方式");
				map.put("payType", 1) ;//需要跳支付
				
				Map<String,Object> dataMap = new HashMap<String, Object>() ;//返回值的map
				dataMap.put("orderNo", bean.getOrderNo()) ;
				dataMap.put("goodsName", "") ;
				dataMap.put("description", "资讯赞赏") ;
				dataMap.put("price", bean.getPayAmount()) ;
				
				String method = bean.getPayMethod() ;
				if("银联".equals(method)){
					if(ValidateUtils.isBlank(root)){
						rs.setStatus(MSG.ERROR.getStatus()) ;
						rs.setMsg("请传入root参数") ;
						return rs ;
					}
				}
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
					String txnAmt = String.format("%.0f", bean.getPayAmount().multiply(new BigDecimal("100"))) ;
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
					rs.setStatus(MSG.ERROR.getStatus());
					rs.setMsg("非法操作");
				}
			}
		}
		bean.setCreateTime(new Date());
		mapper.insert(bean) ;
		return rs ;
	}

}
