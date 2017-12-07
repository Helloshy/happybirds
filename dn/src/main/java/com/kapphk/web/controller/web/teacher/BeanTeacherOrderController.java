package com.kapphk.web.controller.web.teacher;

import com.kapphk.web.utils.GridReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kapphk.web.bean.teacher.BeanTeacherOrder;
import com.kapphk.web.service.web.imethod.teacher.BeanTeacherOrderService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.Result;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

/**
 * 预约讲学申请控制层
 * @author dengwen
 * 2016-10-09 10:15:25
 */
@RestController
@RequestMapping("/web/teacher/teacherorder/")
public class BeanTeacherOrderController {

	@SuppressWarnings("unused")
	@InitBinder
    private void dateBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, editor);
    }
	
	@Autowired
	private BeanTeacherOrderService service;

	/**
	 * 查询列表
	 */
	@RequestMapping("searchList.htm")
	public Result getList(BeanTeacherOrder bean, String itemName, Integer type, String realName, GridReq gridReq){
		Result rs = new Result();
		try {
			return service.searchList(rs,bean,itemName,type,realName,gridReq);
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
	public Result saveData(BeanTeacherOrder bean){
		Result rs = new Result();
		try {
			return service.saveData(rs,bean);
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
	public Result getData(BeanTeacherOrder bean){
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
	 * 更新状态
	 * @author dengwen 
	 * 2016-10-9上午11:53:54
	 */
	@RequestMapping("upstate.htm")
	public Result upstate(Long[] ids,BeanTeacherOrder bean){
		Result rs = new Result();
		try {
			return service.upstate(rs,Arrays.asList(ids),bean);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}
	
	/**
	 * 评价详情
	 * @author dengwen 
	 * 2016-10-9下午3:27:18
	 */
	@RequestMapping("commentDetail.htm")
	public Result upstate(Long id){
		Result rs = new Result();
		try {
			return service.commentDetail(rs,id);
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
	public void exportExcel(BeanTeacherOrder bean, String itemName, Integer type, String realName,HttpServletResponse response){
		service.exportExcel(bean,itemName,type,realName,response);
	}

}
