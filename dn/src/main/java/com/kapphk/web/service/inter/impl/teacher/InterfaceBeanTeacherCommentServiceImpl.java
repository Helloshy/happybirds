package com.kapphk.web.service.inter.impl.teacher;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.web.bean.teacher.BeanTeacherComment;
import com.kapphk.web.dao.mapper.teacher.BeanTeacherCommentMapper;
import com.kapphk.web.service.inter.imethod.teacher.InterfaceBeanTeacherCommentService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;
import com.kapphk.web.utils.Result.MSG;
/**
 * 订单评价业务层
 * @author dengwen 
 * 2016-10-20下午4:11:40
 */
@Service
public class InterfaceBeanTeacherCommentServiceImpl implements InterfaceBeanTeacherCommentService {
	
	@Autowired
	private BeanTeacherCommentMapper mapper;

	/**
	 * 保存评价
	 */
	@Override
	public Result saveComment(BeanTeacherComment bean, Result rs)
			throws Exception {
		if(!ValidateUtils.isBlank(bean.getUid()) && !ValidateUtils.isBlank(bean.getTeacherId()) && !ValidateUtils.isBlank(bean.getOrderId())
				&& !ValidateUtils.isBlank(bean.getStar()) && !ValidateUtils.isBlank(bean.getContent())){
			BeanTeacherComment comment = new BeanTeacherComment();
			comment.setUid(bean.getUid());
			comment.setOrderId(bean.getOrderId());
			comment.setTeacherId(bean.getTeacherId());
			if(!ValidateUtils.isBlank(mapper.findAll(comment))){
				comment.setCreateTime(new Date());
				comment.setContent(bean.getContent());
				mapper.insert(comment);
			}else{
				rs.setStatus(Contents.ERROR);
				rs.setMsg("改订单已评价过，不可重复评价");
			}
		}else{
			rs.setStatus(Contents.ERROR);
			rs.setMsg("参数有误");
		}
		return rs;
	}
	
	/**
	 * 获取陪伴师评价列表
	 */
	@Override
	public Result getCommentList(Long tid, Long tid2, Integer page, Result rs)
			throws Exception {
		if(!ValidateUtils.isBlank(tid)){
			page = ValidateUtils.isBlank(page) ? 1 : page;
			rs.put("info", mapper.getCommentList(tid,(page-1)*7,7));
		}else{
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("参数错误");
		}
		return rs;
	}

}
