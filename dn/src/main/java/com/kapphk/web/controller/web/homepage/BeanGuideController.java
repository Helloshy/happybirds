package com.kapphk.web.controller.web.homepage;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kapphk.web.bean.homepage.BeanGuide;
import com.kapphk.web.service.web.imethod.homepage.BeanGuideService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;

/**
 * 轮播图控制层
 * @author dengwen
 * 2016-09-21 10:34:01
 */
@RestController
@RequestMapping("/web/homepage/guide/")
public class BeanGuideController {

	@Autowired
	private BeanGuideService service;

	/**
	 * 查询列表
	 */
	@RequestMapping("searchList.htm")
	public Result getList(BeanGuide bean, GridReq gridReq){
		Result rs = new Result();
		try {
			return service.searchList(rs,bean,gridReq);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}

	/**
	 * 保存
	 */
	@RequestMapping("save.htm")
	public Result saveData(BeanGuide bean,MultipartFile file,HttpServletRequest request){
		Result rs = new Result();
		try {
			return service.saveData(rs,bean,file, request);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}

	/**
	 * 详情
	 */
	@RequestMapping("data.htm")
	public Result getData(BeanGuide bean){
		Result rs = new Result();
		try {
			return service.getData(rs,bean);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}

	/**
	 * 删除
	 */
	@RequestMapping("delete.htm")
	public Result delete(Long[] ids){
		Result rs = new Result();
		try {
			return service.delete(rs,Arrays.asList(ids));
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}
	
	/**
	 * 查询轮播图总数
	 */
	@RequestMapping("findCount.htm")
	public Result findCount(BeanGuide bean){
		Result rs = new Result();
		try {
			return service.findCount(rs,bean);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}
	

}
