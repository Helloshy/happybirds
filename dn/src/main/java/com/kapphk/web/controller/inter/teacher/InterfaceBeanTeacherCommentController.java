package com.kapphk.web.controller.inter.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kapphk.web.bean.teacher.BeanTeacherComment;
import com.kapphk.web.service.inter.imethod.teacher.InterfaceBeanTeacherCommentService;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;

/**
 * 订单评价控制层
 * @author dengwen 
 * 2016-10-20下午4:09:29
 */
@RequestMapping("/comment/")
@RestController
public class InterfaceBeanTeacherCommentController {

	@Autowired
	private InterfaceBeanTeacherCommentService service;
	
	/**
	 * 保存评价
	 * @author dengwen 
	 * 2016-10-20下午4:12:44
	 */
	@RequestMapping("saveComment.do")
	public Result saveComment(BeanTeacherComment bean){
		Result rs = new Result() ;
		try {
			rs = service.saveComment(bean,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
	/**
	 * 陪伴师评价列表
	 * 
	 * @author dengwen 2016-10-18下午3:26:57
	 */
	@RequestMapping("getCommentList.do")
	public Result getCommentList(Long tid, Integer page) {
		Result rs = new Result();
		try {
			rs = service.getCommentList(tid, tid, page, rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("出现错误");
		}
		return rs;
	}
}
