package com.kapphk.web.service.inter.impl.teacher;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.web.bean.system.BeanSetting;
import com.kapphk.web.bean.teacher.BeanTeacherOrder;
import com.kapphk.web.bean.user.BeanUser;
import com.kapphk.web.bean.user.BeanUserCurrency;
import com.kapphk.web.dao.mapper.system.BeanSettingMapper;
import com.kapphk.web.dao.mapper.teacher.BeanTeacherOrderMapper;
import com.kapphk.web.dao.mapper.user.BeanUserCurrencyMapper;
import com.kapphk.web.dao.mapper.user.BeanUserMapper;
import com.kapphk.web.service.inter.imethod.teacher.InterfaceBeanTeacherOrderService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;
/**
 * 预约讲学订单
 * @author dengwen 
 * 2016-10-17下午5:24:56
 */
@Service
public class InterfaceBeanTeacherOrderServiceImpl implements
		InterfaceBeanTeacherOrderService {
	
	@Autowired
	private BeanTeacherOrderMapper mapper;
	
	@Autowired
	private BeanSettingMapper settingMapper; 
	
	@Autowired
	private BeanUserMapper userMapper;
	
	@Autowired
	private BeanUserCurrencyMapper userCurrencyMapper;

	/**
	 * 保存订单
	 */
	@Override
	public Result saveTeacherOrder(BeanTeacherOrder bean, Result rs)
			throws Exception {
		if(!ValidateUtils.isBlank(bean.getRecordType()) && bean.getRecordType() - 1 ==0){
			if(!ValidateUtils.isBlank(bean.getTeacherId()) && !ValidateUtils.isBlank(bean.getUid()) && !ValidateUtils.isBlank(bean.getNickName())
					  && !ValidateUtils.isBlank(bean.getPhone()) && !ValidateUtils.isBlank(bean.getAddress()) && !ValidateUtils.isBlank(bean.getTeachTheme())
					  && !ValidateUtils.isBlank(bean.getTeachStart()) && !ValidateUtils.isBlank(bean.getTeachEnd()) && !ValidateUtils.isBlank(bean.getTeachTimes())){
				bean.setCreateTime(new Date());
				bean.setState(0);
				if(ValidateUtils.isBlank(bean.getId())){
					mapper.insert(bean);
				}else{
					mapper.update(bean);
				}
			}else{
				rs.setStatus(Contents.ERROR);
				rs.setMsg("参数有误");
			}
		}else if(!ValidateUtils.isBlank(bean.getRecordType()) && bean.getRecordType() - 2 ==0){
			if(!ValidateUtils.isBlank(bean.getTeacherId()) && !ValidateUtils.isBlank(bean.getUid()) && !ValidateUtils.isBlank(bean.getNickName())
					  && !ValidateUtils.isBlank(bean.getPhone()) && !ValidateUtils.isBlank(bean.getAddress()) && !ValidateUtils.isBlank(bean.getTeachTheme())
					  && !ValidateUtils.isBlank(bean.getTeachStart())){
				Calendar c = Calendar.getInstance();
				c.setTime(bean.getTeachStart());
				c.add(Calendar.DATE, Integer.parseInt(bean.getTeachTheme().substring(0,bean.getTeachTheme().indexOf("天"))));
				bean.setTeachEnd(c.getTime());
				bean.setCreateTime(new Date());
				bean.setState(0);
				if(ValidateUtils.isBlank(bean.getId())){
					mapper.insert(bean);
				}else{
					mapper.update(bean);
				}
			}else{
				rs.setStatus(Contents.ERROR);
				rs.setMsg("参数有误");
			}
		}else{
			rs.setStatus(Contents.ERROR);
			rs.setMsg("参数有误");
		}
		return rs;
	}

	/**
	 * 获取订单列表
	 */
	@Override
	public Result getTeacherOrderList(BeanTeacherOrder bean, Integer page, Result rs)
			throws Exception {
		if(!ValidateUtils.isBlank(bean.getUid()) && !ValidateUtils.isBlank(bean.getRecordType()) && !ValidateUtils.isBlank(bean.getState())){
			page = ValidateUtils.isBlank(page) ? 1 : page ;
			rs.put("info", mapper.findTeacherOrderList(bean,(page-1)*5,5));
		}else{
			rs.setStatus(Contents.ERROR);
			rs.setMsg("参数有误");
		}
		return rs;
	}

	/**
	 * 支付订单
	 */
	@Override
	public Result savePayOrder(BeanTeacherOrder bean,Result rs) throws Exception {
		if(!ValidateUtils.isBlank(bean.getId()) && !ValidateUtils.isBlank(bean.getState())){
			if(bean.getState() - 1 == 0){
				BeanTeacherOrder order = mapper.findById(bean.getId());
				String content = order.getRecordType() - 1 == 0 ? "讲学订单" : "陪伴订单";
				if(ValidateUtils.isBlank(bean.getDiscountBlue())) bean.setDiscountBlue(new BigDecimal(0));
				if(ValidateUtils.isBlank(bean.getDiscountRed())) bean.setDiscountRed(new BigDecimal(0));
				if(bean.getDiscountBlue().compareTo(new BigDecimal(0)) > 0 || bean.getDiscountRed().compareTo(new BigDecimal(0)) > 0){
					BeanUser user = userMapper.findById(order.getUid()) ;
					if(user.getBlueCurrency().compareTo(bean.getDiscountBlue()) < 0){
						rs.setStatus(Contents.ERROR) ;
						rs.setMsg("蓝币不足") ;
						return rs ;
					}else if(user.getRedCurrency().compareTo(bean.getDiscountRed()) < 0){
						rs.setStatus(Contents.ERROR) ;
						rs.setMsg("红币不足") ;
						return rs ;
					}
					user.setBlueCurrency(user.getBlueCurrency().subtract(bean.getDiscountBlue()));
					user.setRedCurrency(user.getRedCurrency().subtract(bean.getDiscountRed()));
					userMapper.update(user);
					String str = "使用 ";
					if(bean.getDiscountBlue().compareTo(new BigDecimal(0)) > 0){
						BeanUserCurrency cy = new BeanUserCurrency() ;
						cy.setContent(content+"抵扣");
						cy.setCreateTime(new Date());
						cy.setCurrency(bean.getDiscountBlue().multiply(new BigDecimal(-1)));
						cy.setCurrencyType(1);
						cy.setRecordType(4);
						cy.setUid(order.getUid());
						userCurrencyMapper.insert(cy) ;
						str+= bean.getDiscountBlue()+" 蓝币, ";
					}
					if(bean.getDiscountRed().compareTo(new BigDecimal(0)) > 0){
						BeanUserCurrency cy = new BeanUserCurrency() ;
						cy.setContent(content+"抵扣");
						cy.setCreateTime(new Date());
						cy.setCurrency(bean.getDiscountBlue().multiply(new BigDecimal(-1)));
						cy.setCurrencyType(2);
						cy.setRecordType(4);
						cy.setUid(order.getUid());
						userCurrencyMapper.insert(cy) ;
						str+= bean.getDiscountRed()+" 红币, ";
					}
					str += "抵扣 "+(bean.getDiscountRed().intValue()+bean.getDiscountBlue().intValue())+" 元";
					bean.setDiscountRemark(str);
				}
				mapper.update(bean);
			}else{
				rs.setStatus(Contents.ERROR);
				rs.setMsg("订单状态已改变，必须为待付费状态");
			}
		}else{
			rs.setStatus(Contents.ERROR);
			rs.setMsg("参数有误");
		}
		return rs;
	}

	/**
	 * 订单详情
	 */
	@Override
	public Result getOrderDetail(BeanTeacherOrder bean, Result rs)
			throws Exception {
		if(!ValidateUtils.isBlank(bean.getId())){
			rs.put("info", mapper.findOrderDetail(bean.getId()));
		}else{
			rs.setStatus(Contents.ERROR);
			rs.setMsg("参数有误");
		}
		return rs;
	}

	/**
	 * 取消订单
	 */
	@Override
	public Result cancelOrder(BeanTeacherOrder bean, Result rs)
			throws Exception {
		if(!ValidateUtils.isBlank(bean.getId())){
			bean.setState(3);
			mapper.update(bean);
		}else{
			rs.setStatus(Contents.ERROR);
			rs.setMsg("参数有误");
		}
		return rs;
	}

	/**
	 * 获取列表抵扣比例
	 */
	@Override
	public Result getSettingDetail(BeanSetting bean, Result rs)
			throws Exception {
		if(!ValidateUtils.isBlank(bean.getId())){
			rs.put("info", settingMapper.findById(bean.getId()));
		}else{
			rs.setStatus(Contents.ERROR);
			rs.setMsg("参数有误");
		}
		return rs;
	}

	/**
	 * 预约详情
	 * @author zoneyu 16-11-22
	 */
	public Result searchTeacherOrderDetail(Long id, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(id)){
			rs.put("info", mapper.searchTeacherOrderDetail(id));
		}else{
			rs.setStatus(Contents.ERROR);
			rs.setMsg("参数有误");
		}
		return rs;
	}

}
