package com.kapphk.web.service.web.imethod.user;

import com.kapphk.web.utils.GridReq;
import com.kapphk.web.bean.user.BeanUser;
import com.kapphk.web.utils.Result;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
/**
 * 学员与其他用户业务层
 * @author dengwen
 * 2016-09-21 15:26:28
 */
public interface BeanUserService {

	/*************************** 列表  ***********************************/
	/**
	 * 查询列表
	 */
	public Result searchList(String staffPosition,String startTime,String endTime,Result rs, BeanUser bean, Integer type, String yqRealName, GridReq gridReq) throws Exception;

	/**
	 * 用户关系列表
	 * @author dengwen 
	 * 2016-9-23上午9:56:04
	 */
	public Result searchUserRelation(Result rs,String position,String userName,String realName,
			String yqRealName,String khRealName,Long id,GridReq gridReq)throws Exception;
	
	/**
	 * 获取客户数据列表
	 * @author dengwen 
	 * 2016-10-22下午2:20:45
	 */
	public Result searchServiceList(Result rs, String province, String city,
			String userName, String realName, HttpServletRequest request, GridReq gridReq)throws Exception;
	
	/*************************** 列表  ***********************************/
	
	/**
	 * 保存
	 */
	public Result saveData(String sjInviteCode,Result rs, BeanUser bean, MultipartFile file,
			HttpServletRequest request, List<String> grPosition, List<String> dnPosition) throws Exception;

	/**
	 * 详情
	 */
	public Result getData(Result rs, BeanUser bean) throws Exception;

	/**
	 * 删除
	 */
	public Result delete(Result rs, List<Long> ids) throws Exception;

	/**
	 * 省市区
	 * @author dengwen 
	 * 2016-9-21下午3:50:43
	 */
	public List<Map<String,Object>> getPcd(String id, Integer type, String status) throws Exception;

	/**
	 * 暂停或使用
	 * @author dengwen 
	 * 2016-9-21下午5:44:21
	 */
	public Result upstatus(Result rs, List<Long> asList, Integer state) throws Exception;

	/**
	 * 保存升级身份
	 * @author dengwen 
	 * 2016-9-22下午4:46:45
	 */
	public Result saveIdentity(Result rs, BeanUser user, String identity,
			BigDecimal amount,String dueDate1)throws Exception;

	/**
	 * 审核
	 * @author dengwen 
	 * 2016-9-22下午6:09:20
	 */
	public Result saveState(Result rs, List<Long> asList, Integer state,
			String rejectReason, String date)throws Exception;

	/**
	 * 获取用户
	 * @author dengwen 
	 * 2016-9-23上午11:14:18
	 */
	public List<Map<String, Object>> searchUserList(String staffPosition);

	/**
	 * 保存集团员工
	 * @author dengwen 
	 * 2016-9-23下午1:59:47
	 */
	public Result saveStaff(Result rs, BeanUser bean, MultipartFile file,
			List<String> directArea, List<String> indirectArea,List<String> staffPosition,HttpServletRequest request)throws Exception;
	
	/**
	 * 课程验证
	 * @author dengwen 
	 * 2016-10-22下午3:17:03
	 */
	public Result upIsPermissions(Result rs, BeanUser bean)throws Exception;
	
	/**
	 * 保存更改蓝币
	 * @author dengwen 
	 * 2016-11-3下午2:05:26
	 */
	public Result saveCurrency(Result rs, Long id, Integer amount,
			Integer type, String content)throws Exception;

	/************************** 导出   ******************************/
	
	public void exportStudent(HttpServletResponse response,String staffPosition,String startTime,
			String endTime,BeanUser bean, String type, String yqRealName);

	public void exportService(HttpServletResponse response,HttpServletRequest request,String province,
			String city,String realName,String userName);
	
	public void exportUserRelation(HttpServletResponse response,String position,String userName,String realName,
			String yqRealName,String khRealName,Long id);

	public void exportStaff(HttpServletResponse response,String staffPosition,String startTime,String endTime,
			BeanUser bean,String yqRealName);

	/************************** 导出   ******************************/
}
