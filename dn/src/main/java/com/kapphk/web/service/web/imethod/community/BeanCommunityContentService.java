package com.kapphk.web.service.web.imethod.community;

import com.kapphk.web.utils.GridReq;
import com.kapphk.web.bean.community.BeanCommunityContent;
import com.kapphk.web.utils.Result;
import java.util.List;
/**
 * 社区动态管理业务层
 * @author dengwen
 * 2016-11-02 11:51:09
 */
public interface BeanCommunityContentService {

	/**
	 * 查询列表
	 */
	public Result searchList(Result rs, BeanCommunityContent bean, String itemName, String nickName, GridReq gridReq) throws Exception;

	/**
	 * 保存
	 */
	public Result saveData(Result rs, BeanCommunityContent bean, String[] aid, String[] pid, Long[] cid,
			String aid_checkbox, String pid_checkbox, String cid_checkbox,String[] imgs) throws Exception;

	/**
	 * 详情
	 */
	public Result getData(Result rs, BeanCommunityContent bean) throws Exception;

	/**
	 * 删除
	 */
	public Result delete(Result rs, List<Long> ids) throws Exception;

	/**
	 * 删除评论
	 * @author dengwen 
	 * 2016-11-2下午5:16:02
	 */
	public Result deleteComment(Result rs, Long id) throws Exception;
}
