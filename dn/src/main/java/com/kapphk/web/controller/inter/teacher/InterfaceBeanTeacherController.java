package com.kapphk.web.controller.inter.teacher;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.kapphk.web.bean.course.BeanCourseCollection;
import com.kapphk.web.bean.teacher.BeanAccompanyLike;
import com.kapphk.web.bean.teacher.BeanTeacherCollection;
import com.kapphk.web.service.inter.imethod.teacher.InterfaceBeanTeacherService;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;

/**
 * 老师控制层
 * 
 * @author dengwen 2016-10-14下午2:10:01
 */
@RestController
@RequestMapping("/teacher/")
public class InterfaceBeanTeacherController {

	@Autowired
	private InterfaceBeanTeacherService service;

	@InitBinder
	private void dateBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, editor);
	}

	/**
	 * 陪伴师收藏列表
	 * @author dengwen 2016-10-17下午3:48:07
	 */
	@RequestMapping("getCollectionList.do")
	public Result getCollectionList(BeanCourseCollection bean, Integer page) {
		Result rs = new Result();
		try {
			rs = service.getCollectionList(bean, page, rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("出现错误");
		}
		return rs;
	}

	/**
	 * 取消陪伴师收藏
	 * @author dengwen 2016-10-17下午3:48:07
	 */
	@RequestMapping("deleteCollection.do")
	public Result deleteCollection(Long[] ids) {
		Result rs = new Result();
		try {
			rs = service.deleteCollection(Arrays.asList(ids), rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("出现错误");
		}
		return rs;
	}

	/**
	 * 保存收藏老师
	 * @author dengwen 2016-10-17下午3:48:07
	 */
	@RequestMapping("saveCollection.do")
	public Result saveCollection(BeanTeacherCollection bean, String type) {
		Result rs = new Result();
		try {
			rs = service.saveCollection(bean, type, rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("出现错误");
		}
		return rs;
	}

	/**
	 * 获取动能讲师
	 * @author dengwen 2016-10-17下午3:48:07
	 */
	@RequestMapping("getTeacherList.do")
	public Result getTeacherList(String district, String level,
			String itemName, Integer page) {
		Result rs = new Result();
		try {
			rs = service.getTeacherList(district, level, itemName, page, rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("出现错误");
		}
		return rs;
	}

	/**
	 * 获取动能讲师详情
	 * @author dengwen 2016-10-17下午3:48:07
	 */
	@RequestMapping("getTeacherDetail.do")
	public Result getTeacherDetail(Long id) {
		Result rs = new Result();
		try {
			rs = service.getTeacherDetail(id, rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("出现错误");
		}
		return rs;
	}

	/**
	 * 获取动能讲师课程列表
	 * @author dengwen 2016-10-17下午3:48:07
	 */
	@RequestMapping("getCourseList.do")
	public Result getCourseList(Long id, Integer page) {
		Result rs = new Result();
		try {
			rs = service.getCourseList(id, page, rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("出现错误");
		}
		return rs;
	}

	/**
	 * 获取陪伴师列表
	 * @author dengwen 2016-10-17下午3:48:07
	 */
	@RequestMapping("getAccompanyList.do")
	public Result getAccompanyList(Long uid, BigDecimal longitude, BigDecimal latitude, Integer sex, String level,
			String itemName, String tagValue, String city, Integer page) {
		Result rs = new Result();
		try {
			rs = service.getAccompanyList(uid, longitude, latitude, sex, level, itemName, tagValue,
					city,page, rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("出现错误");
		}
		return rs;
	}

	/**
	 * 获取性别，星级，学科
	 * @author dengwen 2016-10-18下午2:31:27
	 */
	@RequestMapping("getConditionList.do")
	public Result getConditionList() {
		Result rs = new Result();
		try {
			rs = service.getConditionList(rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("出现错误");
		}
		return rs;
	}

	/**
	 * 陪伴师经历列表
	 * @author dengwen 2016-10-18下午3:26:57
	 */
	@RequestMapping("getUndergoList.do")
	public Result getUndergoList(Long uid, Long tid, Integer page) {
		Result rs = new Result();
		try {
			rs = service.getUndergoList(uid, tid, page, rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("出现错误");
		}
		return rs;
	}

	/**
	 * 保存经历点赞
	 * @author dengwen 2016-10-18下午5:38:42
	 */
	@RequestMapping("saveAccompanyLike.do")
	public Result saveAccompanyLike(BeanAccompanyLike bean) {
		Result rs = new Result();
		try {
			rs = service.saveAccompanyLike(bean, rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("出现错误");
		}
		return rs;
	}

	/**
	 * 可预约时间
	 * @author dengwen 2016-10-18下午5:54:15
	 */
	@RequestMapping("getTeacherArrangeDetail.do")
	public Result getTeacherArrangeDetail(Long tid) {
		Result rs = new Result();
		try {
			rs = service.getTeacherArrangeDetail(tid, rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("出现错误");
		}
		return rs;
	}

	/**
	 * 陪伴师详情
	 * @author dengwen 2016-10-18下午3:26:57
	 */
	@RequestMapping("getAccompanyDetail.do")
	public ModelAndView getAccompanyDetail(Long id,HttpServletRequest request) {
		String memo;
		try {
			memo = service.getAccompanyDetail(id);
			request.setAttribute("memo", memo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/jsp/detail.jsp");
	}
	
	/**
	 * 经历详情
	 * @author dengwen 2016-10-18下午3:26:57
	 */
	@RequestMapping("getUndergoDetail.do")
	public ModelAndView getUndergoDetail(Long id,HttpServletRequest request) {
		String memo;
		try {
			memo = service.getUndergoDetail(id);
			request.setAttribute("memo", memo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/jsp/detail.jsp");
	}

}
