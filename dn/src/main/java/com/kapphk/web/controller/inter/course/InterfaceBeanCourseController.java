package com.kapphk.web.controller.inter.course;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.kapphk.web.bean.course.BeanAppJoin;
import com.kapphk.web.bean.course.BeanCourse;
import com.kapphk.web.bean.course.BeanCourseCollection;
import com.kapphk.web.controller.BaseController;
import com.kapphk.web.service.inter.imethod.course.InterfaceBeanCourseService;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;

/**
 * 课程控制层
 * @author zoneyu 16-10-11
 */
@RestController
@RequestMapping("/course/")
public class InterfaceBeanCourseController extends BaseController<BeanCourse, Long> {
	
	@Autowired
	private InterfaceBeanCourseService service ;
	
	/**
	 * 课程列表
	 * @author zoneyu 16-10-11
	 */
	@RequestMapping("getCourseModuleList.do")
	public Result getCourseModuleList(Integer courseType, String courseGroup, Integer isPublic,Integer recordType,Integer page,String name,Long uid){
		Result rs = new Result() ;
		try {
			rs = service.getCourseModuleList(courseType,courseGroup,isPublic,recordType,name,uid,page,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 课程详情
	 * @author zoneyu 16-10-12
	 */
	@RequestMapping("getCourseDetail.do")
	public Result getCourseDetail(String typeName,Long uid,Long courseId){
		Result rs = new Result() ;
		try {
			rs = service.getCourseDetail(typeName,uid,courseId,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 课程收藏
	 * @author zoneyu 16-10-12
	 */
	@RequestMapping("saveCollection.do")
	public Result saveCollection(BeanCourseCollection bean,Integer type){
		Result rs = new Result() ;
		try {
			rs = service.saveCollection(bean,type,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 课程介绍&报名须知
	 * @author zoneyu 16-10-12
	 */
	@RequestMapping("getIntroduction.do")
	public ModelAndView getIntroduction(Long id,Integer typeName,HttpServletRequest request){
		try {
			String memo = service.getIntroduction(id,typeName) ;
			request.setAttribute("memo", memo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/jsp/detail.jsp") ;
	}
	
	/**
	 * 课程抵扣券
	 * @author zoneyu 16-10-13
	 */
	@RequestMapping("getUnUseCoupon.do")
	public Result getUnUseCoupon(Long courseTypeId,Long uid){
		Result rs = new Result() ;
		try {
			rs = service.getUnUseCoupon(courseTypeId,uid,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 课程收藏列表
	 * @author dengwen 
	 */
	@RequestMapping("getCollectionList.do")
	public Result getCollectionList(BeanCourseCollection bean, Integer page){
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
	 * 取消课程收藏
	 * @author dengwen 
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
	 * 线下课程详情
	 * @author zoneyu 16-10-20
	 */
	@RequestMapping("getOnlineCourseDetail.do")
	public Result getOnlineCourseDetail(Long courseId,Long uid){
		Result rs = new Result() ;
		try {
			rs = service.doGetOnlineCourseDetail(courseId,uid,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 线下课程简介
	 * @author zoneyu 16-10-20
	 */
	@RequestMapping("getOnlineCourseIntroduction.do")
	public ModelAndView getOnlineCourseIntroduction(Long courseId,HttpServletRequest request){
		try {
			String memo = service.getOnlineCourseIntroduction(courseId) ;
			request.setAttribute("memo", memo);
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return new ModelAndView("/jsp/detail.jsp") ;
	}
	
	/**
	 * 动能财商
	 * @author zoneyu 16-10-21
	 */
	@RequestMapping("getFinancialList.do")
	public Result getFinancialList(Integer page,Integer type){
		Result rs = new Result() ;
		try {
			rs = service.getFinancialList(type,page,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 系统课程详情
	 * @author zoneyu 16-10-21
	 */
	@RequestMapping("getSystemCourseDetail.do")
	public Result getSystemCourseDetail(Long courseId){
		Result rs = new Result() ;
		try {
			rs = service.getSystemCourseDetail(courseId,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 加盟申请
	 * @author zoneyu 16-10-22
	 */
	@RequestMapping("saveJoin.do")
	public Result saveJoin(BeanAppJoin bean){
		Result rs = new Result() ;
		try {
			rs = service.saveJoin(bean,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 更新播放次数
	 * @author zoneyu 16-10-22
	 */
	@RequestMapping("saveVedioPalys.do")
	public Result saveVedioPalys(Long id){
		Result rs = new Result() ;
		try {
			rs = service.saveVedioPalys(id,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	@RequestMapping("shareCourseDetail.do")
	public ModelAndView shareCourseDetail(Long courseId,HttpServletRequest request){
		try {
			Map<String, Object> map = service.shareCourseDetail(courseId);
			request.setAttribute("map", JSONObject.toJSON(map));
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return new ModelAndView("/jsp/shareCourseDetail.jsp") ;
	}
	
}
