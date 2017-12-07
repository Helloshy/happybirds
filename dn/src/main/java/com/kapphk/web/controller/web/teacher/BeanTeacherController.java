package com.kapphk.web.controller.web.teacher;

import com.kapphk.web.utils.GridReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kapphk.web.bean.teacher.BeanTeacher;
import com.kapphk.web.bean.teacher.BeanTeacherArrange;
import com.kapphk.web.service.web.imethod.teacher.BeanTeacherService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 动能讲师控制层
 * @author dengwen
 * 2016-10-08 10:38:46
 */
@RestController
@RequestMapping("/web/teacher/teacher/")
public class BeanTeacherController {

	@Autowired
	private BeanTeacherService service;

	@SuppressWarnings("unused")
	@InitBinder
    private void dateBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, editor);
    }
	
	/**
	 * 查询列表
	 */
	@RequestMapping("searchList.htm")
	public Result getList(BeanTeacher bean, String district, GridReq gridReq){
		Result rs = new Result();
		try {
			return service.searchList(rs,bean,district,gridReq);
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
	public Result saveData(BeanTeacher bean,String[] district,MultipartFile file,HttpServletRequest request){
		Result rs = new Result();
		try {
			return service.saveData(rs,bean,ValidateUtils.isempty(district) ? new ArrayList<String>() : Arrays.asList(district) ,file,request);
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
	public Result getData(BeanTeacher bean){
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
	 * 获取全部老师
	 * @author dengwen 
	 * 2016-9-27上午9:01:19
	 */
	@RequestMapping("searchTeacherList.htm")
	public List<Map<String,Object>> searchTeacherList(Integer recordType,String type){
		return service.searchTeacherList(recordType,type);
	}
	
	/**
	 * 导出数据
	 * @author dengwen 
	 * 2016-10-8上午10:15:18
	 */
	@RequestMapping("exportExcel.htm")
	public void exportExcel(BeanTeacher bean, String district,HttpServletResponse response){
		service.exportExcel(bean,district,response);
	}
	
	/**
	 * 保存预约时间
	 * @author dengwen 
	 * 2016-10-11上午11:39:05
	 */
	@RequestMapping("saveTa.htm")
	public Result saveTa(BeanTeacherArrange bean){
		Result rs = new Result();
		try {
			return service.saveTa(rs,bean);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}
	
	/**
	 * 获取预约时间详情
	 * @author dengwen 
	 * 2016-10-11下午2:02:46
	 */
	@RequestMapping("arrangeDate.htm")
	public Result arrangeDate(BeanTeacherArrange bean){
		Result rs = new Result();
		try {
			return service.arrangeDate(rs,bean);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}
	
	/**
	 * 保存首页推荐
	 */
	@RequestMapping("saveIsHome.htm")
	public Result saveIsHome(BeanTeacher bean,HttpServletRequest request){
		Result rs = new Result();
		try {
			return service.saveData(rs,bean,new ArrayList<String>(),null,request);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}

}
