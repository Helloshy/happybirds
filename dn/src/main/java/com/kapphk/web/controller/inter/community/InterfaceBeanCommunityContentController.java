package com.kapphk.web.controller.inter.community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kapphk.web.bean.community.BeanCommunityContent;
import com.kapphk.web.controller.BaseController;
import com.kapphk.web.service.inter.imethod.community.InterfaceBeanCommunityContentService;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;

/**
 * 社区评论
 * @author zoneyu 16-11-3
 */
@RestController
@RequestMapping("/communityContent/")
public class InterfaceBeanCommunityContentController extends BaseController<BeanCommunityContent, Long> {
	
	@Autowired
	private InterfaceBeanCommunityContentService service ;
	
	/**
	 * 热门动态
	 * @author zoneyu 16-11-2
	 */
	@RequestMapping("searchDynamicMax.do")
	public Result searchDynamicMax(Long uid,Long communityId,Integer page){
		Result rs = new Result() ;
		try {
			rs = service.searchDynamicMax(uid,communityId,page,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 公告&动态评论
	 * @author zoneyu 16-11-5
	 */
	@RequestMapping("searchCommunityContentList.do")
	public Result searchCommunityContentList(Long id,Integer page){
		Result rs = new Result() ;
		try {
			rs = service.searchCommunityContentList(id,page,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 保存公告&动态
	 * @author zoneyu 16-11-5
	 */
	@RequestMapping("saveCommunityContent.do")
	public Result saveCommunityContent(BeanCommunityContent bean){
		Result rs = new Result() ;
		try {
			rs = service.saveCommunityContent(bean,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 帖子功能呢键
	 * @author zoneyu 16-11-10
	 */
	@RequestMapping("operator.do")
	public Result operator(Long id,Long uid,Integer type,String content){
		Result rs = new Result() ;
		try {
			rs = service.operator(id,uid,type,content,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 帖子功能键
	 * @author zoneyu 16-11-10
	 */
	@RequestMapping("savePoint.do")
	public Result savePoint(Long id,Long uid,Integer type){
		Result rs = new Result() ;
		try {
			rs = service.savePoint(id,uid,type,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
}
