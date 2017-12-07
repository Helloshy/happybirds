package com.kapphk.web.service.web.imethod.community;

import com.kapphk.web.utils.GridReq;
import com.kapphk.web.bean.community.BeanCommunity;
import com.kapphk.web.bean.user.BeanUser;
import com.kapphk.web.utils.Result;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
/**
 * 动能社区管理业务层
 * @author dengwen
 * 2016-10-31 15:56:12
 */
public interface BeanCommunityService {

	/**
	 * 查询列表
	 */
	public Result searchList(Result rs, String province, String itemName, String userName,String realName, GridReq gridReq) throws Exception;

	/**
	 * 保存
	 */
	public Result saveData(Result rs, BeanCommunity bean,MultipartFile file,String[] province,String city,HttpServletRequest request,String[] imgs) throws Exception;

	/**
	 * 详情
	 */
	public Result getData(Result rs, BeanCommunity bean) throws Exception;

	/**
	 * 删除
	 */
	public Result delete(Result rs, List<Long> ids) throws Exception;

	/**
	 * 获取用户姓名
	 * @author dengwen 
	 * 2016-10-31下午3:59:03
	 */
	public Result getUserDetail(Result rs, BeanUser bean) throws Exception;

	/**
	 * 获取全部小组
	 * @author dengwen 
	 * 2016-11-1下午2:00:07
	 */
	public List<Map<String, Object>> getCommunityList();

	/**
	 * 导出数据
	 * @author dengwen 
	 * 2016-11-2上午9:55:06
	 */
	public void exportExcel(HttpServletResponse response,String province, String itemName, String userName,String realName);

}
