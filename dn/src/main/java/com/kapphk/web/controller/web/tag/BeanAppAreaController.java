package com.kapphk.web.controller.web.tag;

import com.kapphk.web.utils.GridReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kapphk.web.bean.tag.BeanAppArea;
import com.kapphk.web.service.web.imethod.tag.BeanAppAreaService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 省份区域管理控制层
 * @author dengwen
 * 2016-10-28 14:37:41
 */
@RestController
@RequestMapping("/web/tag/apparea/")
public class BeanAppAreaController {

	@Autowired
	private BeanAppAreaService service;

	/**
	 * 查询列表
	 */
	@RequestMapping("searchList.htm")
	public Result getList(BeanAppArea bean, GridReq gridReq){
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
	public Result saveData(BeanAppArea bean,String[] areaDetail){
		Result rs = new Result();
		try {
			return service.saveData(rs,bean,ValidateUtils.isempty(areaDetail) ? new ArrayList<String>() : Arrays.asList(areaDetail));
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
	public Result getData(BeanAppArea bean){
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
	public Result delete(String[] ids){
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
	 * 获取全部区域
	 * @author dengwen 
	 * 2016-11-2上午11:38:22
	 */
	@RequestMapping("getAreaList.htm")
	public List<Map<String,Object>> getAreaList(){
		return service.getAreaList();
	}

}
