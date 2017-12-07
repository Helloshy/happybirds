package com.kapphk.web.service.web.imethod.community;

import com.kapphk.web.utils.GridReq;
import com.kapphk.web.bean.community.BeanCommunityStaff;
import com.kapphk.web.utils.Result;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
/**
 * 社区管理人员业务层
 * @author dengwen
 * 2016-11-01 13:39:54
 */
public interface BeanCommunityStaffService {

	/**
	 * 查询列表
	 */
	public Result searchList(Result rs, String tagValue, String itemName, String managerPhone,
			String realName, GridReq gridReq) throws Exception;

	/**
	 * 保存
	 */
	public Result saveData(Result rs, BeanCommunityStaff bean) throws Exception;

	/**
	 * 详情
	 */
	public Result getData(Result rs, BeanCommunityStaff bean) throws Exception;

	/**
	 * 删除
	 */
	public Result delete(Result rs, List<Long> ids) throws Exception;

	/**
	 * 获取全部用户
	 * @author dengwen 
	 * 2016-11-1下午2:14:40
	 */
	public List<Map<String, Object>> getUserList(String userName);

	/**
	 * 导出数据
	 * @author dengwen 
	 * 2016-11-2上午10:00:36
	 */
	public void exportExcel(HttpServletResponse response,String tagValue, String itemName, String managerPhone, String realName);

}
