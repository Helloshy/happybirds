package com.kapphk.yyt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kapphk.base.controller.BaseController;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;
import com.kapphk.yyt.bean.AppCompanyService;
import com.kapphk.yyt.service.CompanyService;

@RestController
@RequestMapping("/web/com")
public class CompanyController extends BaseController {
	
	@Autowired
	private CompanyService companyService;
	
	/**
	 * 1:报装 2:报修 3:安检
	 * @param recordType
	 * @return
	 */
	@RequestMapping("/details")
	public Result list(@RequestParam(required=true)Integer recordType,
			@RequestParam(required=true)Long companyid){
		Result result = new Result();
		try {
			return companyService.getCompanyService(companyid,recordType,result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg(MSG.ERROR.getMsg());
			result.setStatus(Contents.ERROR);
		}
		return result;
		
	}
	
	/**
	 * 帮助中心
	 * @return
	 */
	@RequestMapping("/helpList")
	public Map<String,Object> helpList(){
		
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			AppCompanyService appcompanyService = new AppCompanyService();
			appcompanyService.setRecordType(4);
			List<AppCompanyService> companyServices = companyService.getList(appcompanyService);
			result.put("data", companyServices);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", MSG.ERROR.getMsg());
			result.put("status", Contents.ERROR);
		}
		return result;
		
	}
	
	/**
	 * 帮助中心
	 * @return
	 */
	@RequestMapping("/helpDetails")
	public Result helpDetails(@RequestParam(required=true)Long id){
		
		Result result = new Result("返回成功");
		try {
			AppCompanyService appCompanyService =  companyService.findById(id);
			result.put(appCompanyService);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg(MSG.ERROR.getMsg());
			result.setStatus(Contents.ERROR);
		}
		return result;
	}
	
	
	
	
}
