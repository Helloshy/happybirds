package com.kapphk.web.controller.web.financial;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kapphk.web.service.web.imethod.financial.BeanFinancialService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;

/**
 * 财务管理控制层
 * @author dengwen
 * 2016-11-07 11:11:18
 */
@RestController
@RequestMapping("/web/financial/financial/")
public class BeanFinancialController {
	@Autowired
	private BeanFinancialService service;

	/**
	 * 查询列表
	 */
	@RequestMapping("searchList.htm")
	public Result searchList(String startTime,String endTime,String orderNo,String realName,String teacherName,String itemName,
			String userName,String title,String type,GridReq gridReq){
		Result rs = new Result();
		try {
			String[] str = new String[]{startTime,endTime,orderNo,realName,teacherName,itemName,userName,title};
			DataUtils.trim(str);
			return service.searchList(str[0],str[1],str[2],str[3],str[4],str[5],str[6],str[7],type,rs,gridReq);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}
	
	/**
	 * 购买课程人数列表
	 * @author dengwen 
	 * 2016-11-7下午2:55:27
	 */
	@RequestMapping("searchPurchaseList.htm")
	public Result searchPurchaseList(Long courseId,GridReq gridReq){
		Result rs = new Result();
		try {
			return service.searchPurchaseList(courseId,rs,gridReq);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}
	
	/**
	 * 导出数据
	 * @author dengwen 
	 * 2016-10-8上午10:15:18
	 */
	@RequestMapping("exportExcel.htm")
	public void exportExcel(HttpServletResponse response,String type,Long courseId,String startTime,String endTime,String orderNo,String realName,
			String teacherName,String itemName,String userName,String title){
		String[] str = new String[]{startTime,endTime,orderNo,realName,teacherName,itemName,userName,title};
		DataUtils.trim(str);
		service.exportExcel(response,str[0],str[1],str[2],str[3],str[4],str[5],str[6],str[7],type,courseId);
	}
	
}
