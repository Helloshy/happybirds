package com.kapphk.web.controller.inter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kapphk.web.bean.finance.BeanUserPayPassword;
import com.kapphk.web.bean.user.BeanUserBankInfo;
import com.kapphk.web.bean.user.BeanUserCashApply;
import com.kapphk.web.service.inter.imethod.user.InterfaceBeanUserCashApplyService;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;

/**
 * 提现申请控制层
 * @author zoneyu 16-11-9
 */
@RestController
@RequestMapping("/cashApply/")
public class InterfaceBeanUserCashApplyController {
	
	@Autowired
	private InterfaceBeanUserCashApplyService service;
	
	/**
	 * 银行卡信息
	 * @author zoneyu 16-11-9
	 */
	@RequestMapping("getBankInfo.do")
	public Result getBankInfo(BeanUserCashApply bean){
		Result rs = new Result();
		try {
			rs = service.getBankInfo(bean,rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 保存提现申请
	 * @author zoneyu 16-11-9
	 */
	@RequestMapping("saveApply.do")
	public Result saveApply(BeanUserCashApply bean,String pwd){
		Result rs = new Result();
		try {
			rs = service.saveApply(bean,pwd,rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 保存支付密码
	 * @author zoneyu 16-11-9
	 */
	@RequestMapping("savePayPwd.do")
	public Result saveApply(BeanUserPayPassword bean){
		Result rs = new Result();
		try {
			rs = service.saveApply(bean,rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 保存银行卡信息
	 * @author zoneyu 16-11-17
	 */
	@RequestMapping("saveBankInfo.do")
	public Result saveBankInfo(BeanUserBankInfo bean){
		Result rs = new Result();
		try {
			rs = service.saveBankInfo(bean,rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 更新支付密码
	 * @author zoneyu 16-12-3
	 */
	@RequestMapping("updatePayPwd.do")
	public Result updatePayPwd(BeanUserPayPassword bean){
		Result rs = new Result();
		try {
			rs = service.updatePayPwd(bean,rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
}
