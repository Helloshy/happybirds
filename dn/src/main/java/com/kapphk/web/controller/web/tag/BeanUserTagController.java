package com.kapphk.web.controller.web.tag;

import com.kapphk.web.utils.GridReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kapphk.web.bean.tag.BeanUserTag;
import com.kapphk.web.service.web.imethod.tag.BeanUserTagService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.Result;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 标签控制层
 * @author dengwen
 * 2016-09-29 09:10:52
 */
@RestController
@RequestMapping("/web/tag/usertag/")
public class BeanUserTagController {

	@Autowired
	private BeanUserTagService service;

	/**
	 * 查询列表
	 */
	@RequestMapping("searchList.htm")
	public Result getList(BeanUserTag bean, GridReq gridReq){
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
	public Result saveData(BeanUserTag bean,MultipartFile file,HttpServletRequest request){
		Result rs = new Result();
		try {
			return service.saveData(rs,bean,file,request);
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
	public Result getData(BeanUserTag bean){
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
	public Result delete(String[] ids,BeanUserTag bean){
		Result rs = new Result();
		try {
			return service.delete(rs,Arrays.asList(ids),bean);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}

	/**
	 * 获取标签列表
	 * @author dengwen 
	 * 2016-9-30上午11:44:12
	 */
	@RequestMapping("searchTagTypeList.htm")
	public List<Map<String,Object>> searchTagTypeList(BeanUserTag bean,
													  @RequestParam(required = false) String type){
		return service.searchTagTypeList(bean,type);
	}
	
}
