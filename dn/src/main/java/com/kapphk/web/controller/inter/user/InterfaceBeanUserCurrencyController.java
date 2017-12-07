package com.kapphk.web.controller.inter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kapphk.web.bean.user.BeanUserCurrency;
import com.kapphk.web.service.inter.imethod.user.InterfaceBeanUserCurrencyService;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;

/**
 * 欧币明细控制层
 * @author zoneyu 16-9-30
 */
@RestController
@RequestMapping("/currency/")
public class InterfaceBeanUserCurrencyController {
	
	@Autowired
	private InterfaceBeanUserCurrencyService service ;
	
	/**
	 * 获取蓝币明细
	 * @author denwgen
	 */
	@RequestMapping("getUserCurrencyList.do")
	public Result getUserCurrencyList(BeanUserCurrency bean,Integer page){
		Result rs = new Result();
		try {
			rs = service.getUserCurrencyList(bean,page,rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 签到
	 * @author denwgen
	 */
	@RequestMapping("saveCurrency.do")
	public Result saveCurrency(BeanUserCurrency bean){
		Result rs = new Result();
		try {
			rs = service.saveCurrency(bean,rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
}
