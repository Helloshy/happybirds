package com.kapphk.web.service.inter.impl.user;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.web.bean.finance.BeanUserPayPassword;
import com.kapphk.web.bean.user.BeanUser;
import com.kapphk.web.bean.user.BeanUserBankInfo;
import com.kapphk.web.bean.user.BeanUserCashApply;
import com.kapphk.web.dao.mapper.finance.BeanUserPayPasswordMapper;
import com.kapphk.web.dao.mapper.user.BeanUserBankInfoMapper;
import com.kapphk.web.dao.mapper.user.BeanUserCashApplyMapper;
import com.kapphk.web.dao.mapper.user.BeanUserMapper;
import com.kapphk.web.service.inter.imethod.user.InterfaceBeanUserCashApplyService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;

/**
 * 提现申请业务层(接口)
 * @author zoneyu 16-11-9
 */
@Service
public class InterfaceBeanUserCashApplyServiceImpl implements
		InterfaceBeanUserCashApplyService {
	
	@Autowired
	private BeanUserCashApplyMapper mapper;
	
	//支付密码
	@Autowired
	private BeanUserPayPasswordMapper userPayPasswordMapper;
	
	//用户
	@Autowired
	private BeanUserMapper userMapper;
	
	//银行卡
	@Autowired
	private BeanUserBankInfoMapper userBankInfoMapper;

	/**
	 * 保存提现申请
	 * @author zoneyu 16-11-9
	 */
	public Result saveApply(BeanUserCashApply bean ,String pwd , Result rs) throws Exception {
		if(!ValidateUtils.isBlank(bean.getUid()) && !ValidateUtils.isBlank(bean.getBankName()) && !ValidateUtils.isBlank(pwd)
				&& !ValidateUtils.isBlank(bean.getBankInfo()) && !ValidateUtils.isBlank(bean.getCardId())
				&& !ValidateUtils.isBlank(bean.getCardSignature()) && !ValidateUtils.isBlank(bean.getAmount())){
			
			BeanUser user = userMapper.findById(bean.getUid()) ;
			if(!ValidateUtils.isBlank(user)){
				BeanUserPayPassword b = new BeanUserPayPassword() ;
				b.setUid(bean.getUid());
				b.setPwd(pwd);
				//判断支付密码是否正确
				if(userPayPasswordMapper.findByPageCount(b) > 0){
					//判断金额是否足够
					if(user.getRedCurrency().compareTo(bean.getAmount()) >= 0){
						bean.setCreateTime(new Date());
						mapper.insert(bean) ;
						//保存银行卡信息
						BeanUserBankInfo bank = new BeanUserBankInfo() ;
						bank.setUid(bean.getUid());
						BeanUserBankInfo bank2 = userBankInfoMapper.findEntityByCondition(bank) ;
						if(ValidateUtils.isBlank(bank2)){
							bank.setAcountName(bean.getCardSignature());
							bank.setBankAddress(bean.getBankInfo());
							bank.setBankName(bean.getBankName());
							bank.setCardNo(bean.getCardId());
							bank.setCreateTime(new Date());
							userBankInfoMapper.insert(bank) ;
						}else{
							bank2.setAcountName(bean.getCardSignature());
							bank2.setBankAddress(bean.getBankInfo());
							bank2.setBankName(bean.getBankName());
							bank2.setCardNo(bean.getCardId());
							userBankInfoMapper.update(bank2) ;
						}
						//扣除用户红币数
						user.setRedCurrency(user.getRedCurrency().subtract(bean.getAmount()));
						userMapper.update(user) ;
					}else{
						rs.setStatus(Contents.ERROR);
						rs.setMsg("用户余额不足");
					}
				}else{
					rs.setStatus(Contents.ERROR);
					rs.setMsg("支付密码错误");
				}
			}else{
				rs.setStatus(Contents.ERROR);
				rs.setMsg("用户不存在");
			}
		}else{
			rs.setStatus(Contents.ERROR);
			rs.setMsg("参数有误");
		}
		return rs ;
	}

	/**
	 * 银行卡信息
	 * @author zoneyu 16-11-9
	 */
	public Result getBankInfo(BeanUserCashApply bean, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(bean.getUid())){
			BeanUserBankInfo info = new BeanUserBankInfo() ;
			info.setUid(bean.getUid());
			info = userBankInfoMapper.findEntityByCondition(info) ;
			rs.put("info", info) ;
			
			BeanUserPayPassword password = new BeanUserPayPassword() ;
			password.setUid(bean.getUid());
			int count = userPayPasswordMapper.findByPageCount(password) ;
			rs.put("setPwd", count) ;
		}else{
			rs.setStatus(Contents.ERROR);
			rs.setMsg("参数有误");
		}
		return rs ;
	}

	/**
	 * 保存支付密码
	 * @author zoneyu 16-11-9
	 */
	public Result saveApply(BeanUserPayPassword bean, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(bean.getUid()) && !ValidateUtils.isBlank(bean.getAnswer1()) 
				&& !ValidateUtils.isBlank(bean.getAnswer2()) && !ValidateUtils.isBlank(bean.getPwd())){
			BeanUserPayPassword b = new BeanUserPayPassword() ;
			b.setUid(bean.getUid());
			int count = userPayPasswordMapper.findByPageCount(b) ;
			if(count == 0){
				bean.setCreateTime(new Date());
				userPayPasswordMapper.insert(bean) ;
			}else{
				rs.setStatus(Contents.ERROR);
				rs.setMsg("您已经设置过支付密码了");
			}
		}else{
			rs.setStatus(Contents.ERROR);
			rs.setMsg("参数有误");
		}
		return rs ;
	}

	/**
	 * 保存银行卡信息
	 * @author zoneyu 16-11-17
	 */
	public Result saveBankInfo(BeanUserBankInfo bean, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(bean.getUid()) && !ValidateUtils.isBlank(bean.getAcountName()) 
				&& !ValidateUtils.isBlank(bean.getBankAddress()) && !ValidateUtils.isBlank(bean.getBankName())
				&& !ValidateUtils.isBlank(bean.getCardNo())){
			BeanUserBankInfo bank = new BeanUserBankInfo() ;
			bank.setUid(bean.getUid());
			userBankInfoMapper.deleteByEntity(bank) ;
			
			bean.setCreateTime(new Date()) ;
			userBankInfoMapper.insert(bean) ;
		}else{
			rs.setStatus(Contents.ERROR);
			rs.setMsg("参数有误");
		}
		return rs ;
	}

	/**
	 * 更新支付密码
	 * @author zoneyu 16-12-3
	 */
	public Result updatePayPwd(BeanUserPayPassword bean, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(bean.getUid()) && !ValidateUtils.isBlank(bean.getAnswer1()) 
				&& !ValidateUtils.isBlank(bean.getAnswer2()) && !ValidateUtils.isBlank(bean.getPwd())){
			BeanUserPayPassword pass = new BeanUserPayPassword() ;
			pass.setUid(bean.getUid());
			pass.setAnswer1(bean.getAnswer1());
			pass.setAnswer2(bean.getAnswer2());
			BeanUserPayPassword password = userPayPasswordMapper.findEntityByCondition(pass) ;
			if(!ValidateUtils.isBlank(password)){
				password.setPwd(bean.getPwd());
				userPayPasswordMapper.update(password) ;
			}else{
				rs.setStatus(Contents.ERROR);
				rs.setMsg("问题答案有误");
			}
		}else{
			rs.setStatus(Contents.ERROR);
			rs.setMsg("参数有误");
		}
		return rs ;
	}

}
