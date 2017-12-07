package com.kapphk.web.service.inter.imethod.user;

import com.kapphk.web.bean.finance.BeanUserPayPassword;
import com.kapphk.web.bean.user.BeanUserBankInfo;
import com.kapphk.web.bean.user.BeanUserCashApply;
import com.kapphk.web.utils.Result;

public interface InterfaceBeanUserCashApplyService {

	/**
	 * 保存提现申请
	 * @author zoneyu 16-11-9
	 */
	public Result saveApply(BeanUserCashApply bean ,String pwd , Result rs) throws Exception;

	/**
	 * 银行卡信息
	 * @author zoneyu 16-11-9
	 */
	public Result getBankInfo(BeanUserCashApply bean, Result rs) throws Exception;

	/**
	 * 保存支付密码
	 * @author zoneyu 16-11-9
	 */
	public Result saveApply(BeanUserPayPassword bean, Result rs) throws Exception;

	/**
	 * 保存银行卡信息
	 * @author zoneyu 16-11-17
	 */
	public Result saveBankInfo(BeanUserBankInfo bean, Result rs) throws Exception;

	/**
	 * 更新支付密码
	 * @author zoneyu 16-12-3
	 */
	public Result updatePayPwd(BeanUserPayPassword bean, Result rs) throws Exception;

}
