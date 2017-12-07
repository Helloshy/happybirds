package com.kapphk.web.controller.inter.community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kapphk.web.bean.community.BeanCommunity;
import com.kapphk.web.controller.BaseController;
import com.kapphk.web.service.inter.imethod.community.InterfaceBeanCommunityService;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;

/**
 * 社区动态
 * @author zoneyu 16-11-2
 */
@RestController
@RequestMapping("/community/")
public class InterfaceBeanCommunityController extends BaseController<BeanCommunity, Long> {
	
	@Autowired
	private InterfaceBeanCommunityService service ;
	
	/**
	 * 社区列表
	 * @author zoneyu 16-11-3
	 */
	@RequestMapping("searchCommunityList.do")
	public Result searchCommunityList(Long uid,String date){
		Result rs = new Result() ;
		try {
			rs = service.searchCommunityList(uid,date,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 校验是否能进入社区
	 * @author zoneyu 16-11-4
	 */
	@RequestMapping("checkUserInfo.do")
	public Result checkUserInfo(Long uid,Long communityId){
		Result rs = new Result() ;
		try {
			rs = service.checkUserInfo(uid,communityId,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 保存社区信息
	 * @author zoneyu 16-11-4
	 */
	@RequestMapping("saveCommunityInfo.do")
	public Result saveCommunityInfo(BeanCommunity bean){
		Result rs = new Result() ;
		try {
			rs = service.saveCommunityInfo(bean,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 社区成员
	 * @author zoneyu 16-11-4
	 */
	@RequestMapping("searchCommunityMembers.do")
	public Result searchCommunityMembers(BeanCommunity bean){
		Result rs = new Result() ;
		try {
			rs = service.searchCommunityMembers(bean,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 未加入的社区
	 * @author zoneyu 16-11-5
	 */
	@RequestMapping("searchCommunityListByProvince.do")
	public Result searchCommunityListByProvince(String province,Long uid,Integer page){
		Result rs = new Result() ;
		try {
			rs = service.searchCommunityListByProvince(province,uid,page,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 社区红点
	 * @author zoneyu 16-12-24
	 */
	@RequestMapping("getPoint.do")
	public Result getPoint(Long communityId,Long uid,String date,Integer position){
		Result rs = new Result() ;
		try {
			rs = service.getPoint(communityId,uid,date,position,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
}
