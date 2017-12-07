package com.kapphk.web.controller.web.user;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kapphk.web.service.web.imethod.user.BeanReportTransService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;

@RestController
@RequestMapping("/web/user/reporttrans/")
public class BeanReportTransController {
	
	@Autowired
	private BeanReportTransService service;
	
	/**
	 * 查询列表
	 * @author dengwen 
	 * 2016-11-16上午11:04:43
	 */
	@RequestMapping("searchList.htm")
	public Result getList(String realName, String startTime,String endTime, GridReq gridReq){
		Result rs = new Result();
		String[] str = new String[]{realName,startTime,endTime};
		DataUtils.trim(str);
		try {
			return service.searchList(rs,str[0],str[1],str[2],gridReq);
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
	public void exportExcel(HttpServletResponse response){
		service.exportExcel(response);
	}
	
	/**
	 * 导入
	 * @author dengwen 
	 * 2016-11-16上午11:04:59
	 */
	@RequestMapping("importExcel.htm")
	public Result importExcel(MultipartFile file){
		Result rs = new Result();
		try {
			return service.importExcel(rs,file);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}
}
