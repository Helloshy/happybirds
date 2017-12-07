package com.kapphk.web.controller.web.teacher;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kapphk.web.bean.teacher.BeanAppAccompany;
import com.kapphk.web.service.web.imethod.teacher.BeanAppAccompanyService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;

/**
 * 陪伴师经历控制层
 * @author dengwen
 * 2016-10-10 11:24:15
 */
@RestController
@RequestMapping("/web/teacher/appaccompany/")
public class BeanAppAccompanyController {

	@Autowired
	private BeanAppAccompanyService service;

	/**
	 * 查询列表
	 */
	@RequestMapping("searchList.htm")
	public Result getList(Long teacherId, GridReq gridReq){
		Result rs = new Result();
		try {
			return service.searchList(rs,teacherId,gridReq);
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
	public Result saveData(BeanAppAccompany bean,String[] imgs){
		Result rs = new Result();
		try {
			return service.saveData(rs,bean,imgs);
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
	public Result getData(BeanAppAccompany bean){
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
	
	
	@RequestMapping("getUserName.htm")
	public Result getUserName(Long id){
		Result rs = new Result();
		try {
			return service.getUserName(rs,id);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}

}
