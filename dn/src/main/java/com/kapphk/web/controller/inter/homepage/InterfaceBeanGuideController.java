package com.kapphk.web.controller.inter.homepage;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.kapphk.web.bean.homepage.BeanGuide;
import com.kapphk.web.controller.BaseController;
import com.kapphk.web.service.inter.imethod.homepage.InterfaceBeanGuideService;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;

/**
 * 轮播图控制层
 * @author zoneyu 16-10-10
 */
@RestController
@RequestMapping("/guide/")
public class InterfaceBeanGuideController extends BaseController<BeanGuide, Long> {
	
	@Autowired
	private InterfaceBeanGuideService service ;
	
	/**
	 * 首页轮播图数据
	 * @author zoneyu 16-10-10
	 */
	@RequestMapping("getHomeGuideList.do")
	public Result getHomeGuideList(){
		Result rs = new Result() ;
		try {
			rs = service.getHomeGuideList(rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 轮播图详情
	 * @author zoneyu 16-10-10
	 */
	@RequestMapping("getGuideDetail.do")
	public ModelAndView getGuideDetail(Long id,HttpServletRequest request){
		try {
			String memo = service.getGuideDetail(id);
			request.setAttribute("memo", memo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/jsp/detail.jsp") ;
	}
	
	/**
	 * 动能名师轮播图数据
	 * @author denwgen16-10-10
	 */
	@RequestMapping("getTeacherGuideList.do")
	public Result getTeacherGuideList(BeanGuide bean){
		Result rs = new Result();
		try {
			rs = service.getTeacherGuideList(bean,rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 动能名师区域与级别
	 * @author denwgen16-10-10
	 */
	@RequestMapping("getDistrictList.do")
	public Result getDistrictList(){
		Result rs = new Result();
		try {
			rs = service.getDistrictList(rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	
}
