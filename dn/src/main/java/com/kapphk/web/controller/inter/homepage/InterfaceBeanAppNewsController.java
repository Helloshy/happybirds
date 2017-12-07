package com.kapphk.web.controller.inter.homepage;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.kapphk.web.bean.course.BeanCourseCollection;
import com.kapphk.web.bean.homepage.BeanAppNews;
import com.kapphk.web.bean.user.BeanUserCollection;
import com.kapphk.web.controller.BaseController;
import com.kapphk.web.service.inter.imethod.homepage.InterfaceBeanAppNewsService;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;

/**
 * 资讯控制层
 * @author zoneyu 16-10-8
 */
@RestController
@RequestMapping("/news/")
public class InterfaceBeanAppNewsController extends BaseController<BeanAppNews, Long> {
	
	@Autowired
	private InterfaceBeanAppNewsService service ;
	
	/**
	 * 资讯列表
	 * @author zoneyu 16-10-8
	 */
	@RequestMapping("getNewsList.do")
	public Result getNewsList(String newsTag,Integer page,Long uid,Integer recordType){
		Result rs = new Result() ;
		try {
			rs = service.getNewsList(newsTag,page,uid,recordType,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 欧巴资讯
	 * @author zoneyu 16-10-11
	 */
	@RequestMapping("getList.do")
	public Result getList(String recordType,Integer page,Long uid){
		Result rs = new Result() ;
		try {
			rs = service.getList(recordType,page,uid,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 资讯详情
	 * @author zoneyu 16-10-9
	 */
	@RequestMapping("getNewsDetail.do")
	public ModelAndView upNewsDetail(Long id,HttpServletRequest request){
		String memo;
		try {
			memo = service.upNewsDetail(id);
			request.setAttribute("memo", memo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/jsp/detail.jsp") ;
	}
	
	/**
	 * 资讯功能键
	 * @author zoneyu 16-10-9
	 */
	@RequestMapping("saveOperater.do")
	public Result saveOperater(BeanUserCollection bean,Integer type){
		Result rs = new Result() ;
		try {
			rs = service.saveOperater(bean,type,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 首页资讯、课程推荐、陪伴师推荐
	 * @author zoneyu 16-10-10
	 */
	@RequestMapping("getHomeData.do")
	public Result getHomeData(BigDecimal longitude,BigDecimal latitude,String city,Long uid){
		Result rs = new Result() ;
		try {
			rs = service.getHomeData(longitude,latitude,city,uid,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 资讯收藏列表
	 * @author dengwen 
	 */
	@RequestMapping("getCollectionList.do")
	public Result getCollectionList(BeanCourseCollection bean,Integer page){
		Result rs = new Result() ;
		try {
			rs = service.getCollectionList(bean,page,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	 /**
	 * 取消资讯收藏
	 *author dengwen 
	 */
	@RequestMapping("deleteCollection.do")
	public Result deleteCollection(Long[] ids){
		Result rs = new Result() ;
		try {
			rs = service.deleteCollection(Arrays.asList(ids),rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 公司公告列表
	 * @author dengwen 16-10-8
	 */
	@RequestMapping("getNoticeList.do")
	public Result getNoticeList(BeanAppNews bean,Integer page){
		Result rs = new Result() ;
		try {
			rs = service.getNoticeList(bean,page,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 公司公告详情
	 * @author dengwen 16-10-8
	 */
	@RequestMapping("getNoticeDetail.do")
	public ModelAndView getNoticeDetail(Long id,HttpServletRequest request){
		try {
			BeanAppNews bean = service.getNoticeDetail(id);
			request.setAttribute("content", bean.getContent());
			request.setAttribute("title", bean.getTitle());
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			request.setAttribute("createTime", sf.format(bean.getCreateTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/jsp/notice.jsp");
	}
	
	
	/**
	 * 资讯赞赏记录
	 * @author dengwen 16-11-15
	 */
	@RequestMapping("getRecord.do")
	public Result getRecord(Long id,Long uid){
		Result rs = new Result() ;
		try {
			rs = service.getRecord(id,uid,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs ;
	}
	
	/**
	 * 动能集团所有的详情链接
	 * @author zoneyu 16-12-13
	 */
	@RequestMapping("getDN.do")
	public Result getDN(String title){
		Result rs = new Result() ;
		try {
			rs = service.getDN(title,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs ;
	}
	
	/**
	 * 动能集团所有的详情
	 * @author zoneyu 16-12-13
	 */
	@RequestMapping("getLinkInfo.do")
	public ModelAndView getLinkInfo(Long id,HttpServletRequest request){
		try {
			String memo = service.getLinkInfo(id) ;
			request.setAttribute("memo", memo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/jsp/detail.jsp");
	}
	
}
