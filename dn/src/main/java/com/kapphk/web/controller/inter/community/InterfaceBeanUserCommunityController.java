package com.kapphk.web.controller.inter.community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kapphk.web.bean.community.BeanUserCommunity;
import com.kapphk.web.controller.BaseController;
import com.kapphk.web.service.inter.imethod.community.InterfaceBeanUserCommunityService;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;

/**
 * 我的社区控制层
 * @author zoneyu 16-11-2
 */
@RestController
@RequestMapping("/userCommunity/")
public class InterfaceBeanUserCommunityController extends BaseController<BeanUserCommunity, Long> {
	
	@Autowired
	private InterfaceBeanUserCommunityService service ;
	
	/**
	 * 社区信息
	 * @author zoneyu 16-11-2
	 */
	@RequestMapping("searchCommunityInfo.do")
	public Result searchCommunityInfo(Long id){
		Result rs = new Result() ;
		try {
			rs = service.searchCommunityInfo(id,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 社区公告、动态
	 * @author zoneyu 16-11-2
	 */
	@RequestMapping("searchNoticeOrDynamic.do")
	public Result searchNoticeOrDynamic(Long uid,Long id,Integer type,Integer page){
		Result rs = new Result() ;
		try {
			rs = service.searchNoticeOrDynamic(uid,id,type,page,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 社区个数
	 * @author zoneyu 16-11-2
	 */
	@RequestMapping("getCommunityCounts.do")
	public Result getCommunityCounts(Long uid){
		Result rs = new Result() ;
		try {
			rs = service.getCommunityCounts(uid,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 加入社区
	 * @author zoneyu 16-11-5
	 */
	@RequestMapping("saveUserCommunity.do")
	public Result saveUserCommunity(BeanUserCommunity bean){
		Result rs = new Result() ;
		try {
			rs = service.saveUserCommunity(bean,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
}
