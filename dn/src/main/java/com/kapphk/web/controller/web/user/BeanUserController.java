package com.kapphk.web.controller.web.user;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kapphk.web.bean.user.BeanUser;
import com.kapphk.web.service.web.imethod.user.BeanUserService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;

/**
 * 学员与其他用户控制层
 * @author dengwen
 * 2016-09-21 15:26:28
 */
@RestController
@RequestMapping("/web/user/user/")
public class BeanUserController {

	@Autowired
	private BeanUserService service;

	@SuppressWarnings("unused")
	@InitBinder
    private void dateBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, editor);
    }
	
	/*************************** 列表  ***********************************/
	
	/**
	 * 查询列表
	 */
	@RequestMapping("searchList.htm")
	public Result getList(String staffPosition,String startTime,String endTime,BeanUser bean, Integer type, String yqRealName, GridReq gridReq){
		Result rs = new Result();
		try {
			String[] str = new String[]{staffPosition,startTime,endTime,yqRealName};
			DataUtils.trim(str);
			return service.searchList(str[0],str[1],str[2],rs,bean,type,str[3],gridReq);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}
	
	/**
	 * 用户关系列表
	 * @author dengwen 
	 * 2016-9-23上午9:55:53
	 */
	@RequestMapping("searchUserRelation.htm")
	public Result searchUserRelation(String position,String userName,String realName,
			String yqRealName,String khRealName,Long id,GridReq gridReq){
		Result rs = new Result();
		try {
			return service.searchUserRelation(rs,position,userName,realName,yqRealName,khRealName,id,gridReq);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}
	
	/**
	 * 客服管理客户列表
	 * @author dengwen 
	 * 2016-10-22下午2:19:16
	 */
	@RequestMapping("searchServiceList.htm")
	public Result searchServiceList(String province,String city,String realName,
			String userName,GridReq gridReq,HttpServletRequest request){
		Result rs = new Result();
		try {
			return service.searchServiceList(rs,province,city,userName,realName,request,gridReq);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}
	
	
	/*************************** 列表  ***********************************/
	

	/**
	 * 保存
	 */
	@RequestMapping("save.htm")
	public Result saveData(String sjInviteCode,BeanUser bean,MultipartFile file,HttpServletRequest request,String[] grPosition,String[] dnPosition){
		Result rs = new Result();
		try {
			return service.saveData(sjInviteCode,rs,bean,file,request,
					ValidateUtils.isempty(grPosition) ? null : Arrays.asList(grPosition),
					ValidateUtils.isempty(dnPosition) ? null : Arrays.asList(dnPosition));
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
	public Result getData(BeanUser bean){
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
	 * 省市区
	 */
	@RequestMapping("pcd.htm")
	public List<Map<String,Object>> getPcd(String id,Integer type,String status){
		try {
			return service.getPcd(id,type,status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 暂停或使用
	 */
	@RequestMapping("upstatus.htm")
	public Result upstatus(Long[] ids,Integer state){
		Result rs = new Result();
		try {
			return service.upstatus(rs,Arrays.asList(ids),state);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}
	
	
	/**
	 * 保存升级身份
	 * @author dengwen 
	 * 2016-9-22下午4:44:13
	 */
	@RequestMapping("saveIdentity.htm")
	public Result saveIdentity(BeanUser user,String identity,BigDecimal amount,String dueDate1){
		Result rs = new Result();
		try {
			return service.saveIdentity(rs,user,identity,amount,dueDate1);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}
	
	/**
	 * 审核
	 * @author dengwen 
	 * 2016-9-22下午6:07:28
	 */
	@RequestMapping("saveState.htm")
	public Result saveState(Long[] ids,Integer state,String rejectReason,String date){
		Result rs = new Result();
		try {
			return service.saveState(rs,Arrays.asList(ids),state,rejectReason,date);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}
	
	/**
	 * 获取用户
	 * @author dengwen 
	 * 2016-9-23上午11:14:01
	 */
	@RequestMapping("searchUserList.htm")
	public List<Map<String,Object>> searchUserList(String staffPosition){
		return service.searchUserList(staffPosition);
	}
	
	/**
	 * 保存集团员工
	 * @author dengwen 
	 * 2016-9-23下午1:58:01
	 */
	@RequestMapping("saveStaff.htm")
	public Result saveStaff(BeanUser bean, MultipartFile file,String[] directArea,String[] indirectArea,String[] staffPosition,HttpServletRequest request){
		Result rs = new Result();
		try {
			return service.saveStaff(rs,bean,file,ValidateUtils.isempty(directArea) ? new ArrayList<String>() : Arrays.asList(directArea),
					ValidateUtils.isempty(indirectArea) ? new ArrayList<String>() : Arrays.asList(indirectArea),
					ValidateUtils.isempty(staffPosition) ? new ArrayList<String>() : Arrays.asList(staffPosition),request);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}
	
	/**
	 * 课程验证
	 * @author dengwen 
	 * 2016-10-22下午3:16:06
	 */
	@RequestMapping("upIsPermissions.htm")
	public Result upIsPermissions(BeanUser bean){
		Result rs = new Result();
		try {
			return service.upIsPermissions(rs,bean);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}
	
	/**
	 * 保存更改蓝币
	 * @author dengwen 
	 * 2016-11-3下午2:02:54
	 */
	@RequestMapping("saveCurrency.htm")
	public Result saveCurrency(Long id, Integer amount, Integer type, String content){
		Result rs = new Result();
		try {
			return service.saveCurrency(rs,id,amount,type,content);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}
	

	/*************************** 导出  ***********************************/

	/**
	 * 导出学员
	 * @author dengwen 
	 * 2016-10-22下午2:04:58
	 */
	@RequestMapping("exportStudent.htm")
	public void exportStudent(HttpServletResponse response,String staffPosition,String startTime,
			String endTime,BeanUser bean, String type, String yqRealName){
		String[] str = new String[]{staffPosition,startTime,endTime,yqRealName};
		DataUtils.trim(str);
		DataUtils.trim(bean);
		service.exportStudent(response,str[0],str[1],str[2],bean,type,str[3]);
	}
	
	/**
	 * 导出集团员工
	 * @author dengwen 
	 * 2016-10-22下午3:36:34
	 */
	@RequestMapping("exportStaff.htm")
	public void exportStaff(HttpServletResponse response,String staffPosition,String startTime,String endTime,
			BeanUser bean,String yqRealName){
		String[] str = new String[]{staffPosition,startTime,endTime,yqRealName};
		DataUtils.trim(str);
		DataUtils.trim(bean);
		service.exportStaff(response,str[0],str[1],str[2],bean,str[3]);
	}
	
	/**
	 * 导出客户管理数据
	 * @author dengwen 
	 * 2016-10-22下午2:04:58
	 */
	@RequestMapping("exportService.htm")
	public void exportService(HttpServletResponse response,HttpServletRequest request,String province,
			String city,String realName,String userName){
		service.exportService(response,request,province,city,realName,userName);
	}
	
	/**
	 * 导出用户关系Excel
	 * @author dengwen 
	 * 2016-9-23上午10:11:17
	 */
	@RequestMapping("exportUserRelation.htm")
	public void exportUserRelation(HttpServletResponse response,String position,String userName,String realName,
			String yqRealName,String khRealName,Long id){
		service.exportUserRelation(response,position,userName,realName,yqRealName,khRealName,id);
	}
	
	/*************************** 导出  ***********************************/	
}
