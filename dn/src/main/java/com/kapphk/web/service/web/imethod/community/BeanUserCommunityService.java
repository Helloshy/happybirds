package com.kapphk.web.service.web.imethod.community;

import com.kapphk.web.utils.GridReq;
import com.kapphk.web.bean.community.BeanUserCommunity;
import com.kapphk.web.utils.Result;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
/**
 * 社区成员业务层
 * @author dengwen
 * 2016-11-01 17:51:26
 */
public interface BeanUserCommunityService {

	/**
	 * 查询列表
	 */
	public Result searchList(Result rs, String itemName, String userName, String realName, GridReq gridReq) throws Exception;

	/**
	 * 保存
	 */
	public Result saveData(Result rs, BeanUserCommunity bean) throws Exception;

	/**
	 * 详情
	 */
	public Result getData(Result rs, BeanUserCommunity bean) throws Exception;

	/**
	 * 删除
	 */
	public Result delete(Result rs, List<Long> ids) throws Exception;

	public void exportExcel(HttpServletResponse response);

}
