package com.kapphk.web.service.inter.impl.product;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.kapphk.web.bean.product.BeanAppProduct;
import com.kapphk.web.bean.product.BeanAppProductInventory;
import com.kapphk.web.bean.product.BeanAppProductOrder;
import com.kapphk.web.bean.system.BeanSetting;
import com.kapphk.web.bean.user.BeanUser;
import com.kapphk.web.bean.user.BeanUserCurrency;
import com.kapphk.web.dao.mapper.product.BeanAppProductInventoryMapper;
import com.kapphk.web.dao.mapper.product.BeanAppProductMapper;
import com.kapphk.web.dao.mapper.product.BeanAppProductOrderMapper;
import com.kapphk.web.dao.mapper.system.BeanSettingMapper;
import com.kapphk.web.dao.mapper.user.BeanUserCurrencyMapper;
import com.kapphk.web.dao.mapper.user.BeanUserMapper;
import com.kapphk.web.service.inter.imethod.product.InterfaceBeanAppProductOrderService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.DateUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.HttpRequest;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;

@Service
public class InterfaceBeanAppProductOrderServiceImpl implements
		InterfaceBeanAppProductOrderService {
	
	@Autowired
	private BeanAppProductOrderMapper mapper;
	
	@Autowired
	private BeanUserMapper userMapper;
	
	@Autowired
	private BeanUserCurrencyMapper userCurrencyMapper ;
	
	@Autowired
	private BeanAppProductInventoryMapper inventoryMapper;
	
	@Autowired
	private BeanAppProductMapper productMapper;
	
	@Autowired
	private BeanSettingMapper settingMapper;

	/**
	 * 商品评价列表
	 */
	@Override
	public Result getProductCommtentList(Long productId, Integer page, Result rs)
			throws Exception {
		page = ValidateUtils.isBlank(page) ? 1 : page ;
		if(!ValidateUtils.isBlank(productId)){
			rs.put("info", mapper.getProductCommtentList(productId,(page-1)*GridReq.ROWS,GridReq.ROWS));
		}else{
			rs.setStatus(Contents.ERROR);
			rs.setMsg("参数有误");
		}
		return rs;
	}

	/**
	 * 确认订单
	 */
	@Override
	public Result saveProductOrder(BeanAppProductOrder bean, String root, Result rs)
			throws Exception {
		DataUtils.trim(bean);
		if(ValidateUtils.isBlank(bean.getDiscountBlue())) bean.setDiscountBlue(new BigDecimal(0));
		if(ValidateUtils.isBlank(bean.getDiscountRed())) bean.setDiscountRed(new BigDecimal(0));
		if(!ValidateUtils.isBlank(bean.getUid()) && !ValidateUtils.isBlank(bean.getProductId()) && 
		   !ValidateUtils.isBlank(bean.getPrice()) && !ValidateUtils.isBlank(bean.getQty()) &&
		   !ValidateUtils.isBlank(bean.getPayAmount()) && !ValidateUtils.isBlank(bean.getPayMethod()) &&
		   !ValidateUtils.isBlank(bean.getDeliveryAddress()) && !ValidateUtils.isBlank(bean.getDeliveryAmount()) &&
		   !ValidateUtils.isBlank(bean.getDeliveryName()) && !ValidateUtils.isBlank(bean.getDeliveryPhone())){
		    if(ValidateUtils.isBlank(bean.getColor())) bean.setColor("");
		    if(ValidateUtils.isBlank(bean.getColorName())) bean.setColorName("");
		    if(ValidateUtils.isBlank(bean.getSize())) bean.setSize("");
		    if(ValidateUtils.isBlank(bean.getSizeName())) bean.setSizeName("");
			Map<String,Object> dataMap = new HashMap<String, Object>() ;//返回值的map
			//扣除用户的蓝币跟红币
			BeanUser user = userMapper.findById(bean.getUid()) ;
			if(user.getBlueCurrency().compareTo(bean.getDiscountBlue()) < 0){
				rs.setStatus(Contents.ERROR) ;
				rs.setMsg("蓝币不足") ;
				return rs ;
			}else if(user.getRedCurrency().compareTo(bean.getDiscountRed()) < 0){
				rs.setStatus(Contents.ERROR) ;
				rs.setMsg("红币不足") ;
				return rs ;
			}
			
			//判断数量是否足够
			BeanAppProductInventory inventory = new BeanAppProductInventory();
			inventory.setProductId(bean.getProductId());
			if(!ValidateUtils.isBlank(bean.getSize())) inventory.setSize(bean.getSize());
			if(!ValidateUtils.isBlank(bean.getColor())) inventory.setColor(bean.getColor());
			
			BeanAppProductOrder productOrder = new BeanAppProductOrder();
			if(!ValidateUtils.isBlank(bean.getColor())) productOrder.setColor(bean.getColor());
			if(!ValidateUtils.isBlank(bean.getSize())) productOrder.setSize(bean.getSize());
			int count = mapper.count()+bean.getQty();
			List<BeanAppProductInventory> list = inventoryMapper.findAll(inventory);
			if(!ValidateUtils.isEmptyForCollection(list)){
				inventory = list.get(0);
				if(inventory.getStockQty() - count >= 0){
					//添加流水
					if(bean.getDiscountBlue().compareTo(new BigDecimal(0)) > 0){
						BeanUserCurrency cy = new BeanUserCurrency() ;
						cy.setContent("商品抵扣");
						cy.setCreateTime(new Date());
						cy.setCurrency(bean.getDiscountBlue().multiply(new BigDecimal(-1)));
						cy.setCurrencyType(1);
						cy.setRecordType(4);
						cy.setUid(bean.getUid());
						userCurrencyMapper.insert(cy) ;
					}
					if(bean.getDiscountRed().compareTo(new BigDecimal(0)) > 0){
						BeanUserCurrency cy = new BeanUserCurrency() ;
						cy.setContent("商品抵扣");
						cy.setCreateTime(new Date());
						cy.setCurrency(bean.getDiscountBlue().multiply(new BigDecimal(-1)));
						cy.setCurrencyType(2);
						cy.setRecordType(4);
						cy.setUid(bean.getUid());
						userCurrencyMapper.insert(cy) ;
					}
				}else{
					rs.setStatus(Contents.ERROR) ;
					rs.setMsg("商品数量不足") ;
					return rs ;
				}
			}else{
				rs.setStatus(Contents.ERROR) ;
				rs.setMsg("商品不存在") ;
				return rs ;
			}
			
			user.setBlueCurrency(user.getBlueCurrency().subtract(bean.getDiscountBlue()));
			user.setRedCurrency(user.getRedCurrency().subtract(bean.getDiscountRed()));
			userMapper.update(user);
			bean.setOrderNo("BC"+String.valueOf(System.nanoTime()));//订单号 
			bean.setPayMethodType("支付方式");
			bean.setState(0);
			bean.setCreateTime(new Date());
			mapper.insert(bean);
			BeanAppProduct product = productMapper.findById(bean.getProductId());
			
			Properties prop = new Properties() ;
			InputStream in = this.getClass().getClassLoader().getResourceAsStream("info.properties") ;
			prop.load(in);
			dataMap.put("payType", 1) ;//默认是需要跳支付的
			dataMap.put("orderNo", bean.getOrderNo());
			dataMap.put("price", bean.getPayAmount());
			dataMap.put("goodsName", product.getItemName());
			dataMap.put("description", product.getItemRemark());
			dataMap.put("id", bean.getId());
			String method = bean.getPayMethod() ;
			if(bean.getPayAmount().compareTo(new BigDecimal(0)) == 0){
				dataMap.put("payType", 0) ;
				bean.setState(1);
				bean.setPayTime(new Date());
				mapper.update(bean);
				BeanSetting setting = settingMapper.findById("cur_percent");
				BigDecimal bigDecimal = bean.getPayAmount().add(bean.getDiscountBlue()).add(bean.getDiscountRed());
				int amount = (int) (bigDecimal.intValue()*Integer.valueOf(setting.getValue())*0.01);
				user.setBlueCurrency(user.getBlueCurrency().add(new BigDecimal(amount)));
				userMapper.update(user);
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
				String merId = prop.getProperty("nion_merId");
				String txnAmt = String.format("%.0f", bean.getPrice().multiply(new BigDecimal("100"))) ;
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
						rs.setStatus(Contents.ERROR) ;
						rs.setMsg(String.valueOf(parseObject.get("msg"))) ;
					}
				}
		}else{
			rs.setStatus(Contents.ERROR);
			rs.setMsg("参数有误");
		}
		}
		return rs;
	}

	/**
	 * 订单详情
	 */
	@Override
	public Result getProductOrderDetail(Long id, Result rs) throws Exception {
		return rs.put("info", mapper.findDetailById(id));
	}

	/**
	 * 获取订单列表
	 */
	@Override
	public Result getProductOrderList(BeanAppProductOrder bean, Integer page, Result rs)
			throws Exception {
		if(!ValidateUtils.isBlank(bean.getUid()) && !ValidateUtils.isBlank(bean.getState())){
			page = ValidateUtils.isBlank(page) ? 1 : page ;
			rs.put("info", mapper.getProductOrderList(bean,(page-1)*GridReq.ROWS,GridReq.ROWS));
		}else{
			rs.setStatus(Contents.ERROR);
			rs.setMsg("参数有误");
		}
		return rs;
	}

	/**
	 * 保存评价
	 */
	@Override
	public Result saveProductOrderComment(BeanAppProductOrder bean, Result rs)
			throws Exception {
		if(!ValidateUtils.isBlank(bean.getId()) && !ValidateUtils.isBlank(bean.getCommentStar())){
			if(ValidateUtils.isBlank(mapper.findById(bean.getId()).getCommentTime())){
				BeanAppProductOrder order = new BeanAppProductOrder();
				order.setState(3);
				order.setCommentTime(new Date());
				order.setId(bean.getId());
				order.setCommentStar(bean.getCommentStar());
				order.setCommentRemark(bean.getCommentRemark());
				mapper.update(order);
			}else{
				rs.setStatus(Contents.ERROR);
				rs.setMsg("该订单已评价过");
			}
		}else{
			rs.setStatus(Contents.ERROR);
			rs.setMsg("参数有误");
		}
		return rs;
	}
	
}
