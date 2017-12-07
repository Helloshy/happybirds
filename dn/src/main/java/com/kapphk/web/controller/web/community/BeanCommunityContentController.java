package com.kapphk.web.controller.web.community;

import com.kapphk.web.utils.GridReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kapphk.web.bean.community.BeanCommunityContent;
import com.kapphk.web.service.web.imethod.community.BeanCommunityContentService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.Result;
import java.util.Arrays;

/**
 * 社区动态管理控制层
 * @author dengwen
 * 2016-11-02 11:51:09
 */
@RestController
@RequestMapping("/web/community/communitycontent/")
public class BeanCommunityContentController {

	@Autowired
	private BeanCommunityContentService service;

	/**
	 * 查询列表
	 */
	@RequestMapping("searchList.htm")
	public Result getList(BeanCommunityContent bean, String itemName, String nickName, GridReq gridReq){
		Result rs = new Result();
		try {
			return service.searchList(rs,bean,itemName,nickName,gridReq);
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
	public Result saveData(BeanCommunityContent bean, String[] aid, String[] pid, Long[] cid,
			String aid_checkbox, String pid_checkbox, String cid_checkbox,String[] imgs){
		Result rs = new Result();
		try {
			return service.saveData(rs,bean,aid,pid,cid,aid_checkbox,pid_checkbox,cid_checkbox,imgs);
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
	public Result getData(BeanCommunityContent bean){
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
	 * 删除评论
	 */
	@RequestMapping("deleteComment.htm")
	public Result deleteComment(Long id){
		Result rs = new Result();
		try {
			return service.deleteComment(rs,id);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(Contents.ERROR);
			rs.setMsg("出现错误");
			return rs;
		}
	}

}
