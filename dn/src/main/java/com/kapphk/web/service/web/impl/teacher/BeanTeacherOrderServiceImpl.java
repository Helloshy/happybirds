package com.kapphk.web.service.web.impl.teacher;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.web.bean.teacher.BeanTeacherComment;
import com.kapphk.web.bean.teacher.BeanTeacherOrder;
import com.kapphk.web.dao.mapper.teacher.BeanTeacherCommentMapper;
import com.kapphk.web.dao.mapper.teacher.BeanTeacherOrderMapper;
import com.kapphk.web.service.web.imethod.teacher.BeanTeacherOrderService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.ExcelUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;
@Service
public class BeanTeacherOrderServiceImpl implements BeanTeacherOrderService {

	@Autowired
	private BeanTeacherOrderMapper mapper;
	
	@Autowired
	private BeanTeacherCommentMapper commentMapper; 
	
	/**
	 * 查询列表
	 */
	@Override
	public Result searchList(Result rs, BeanTeacherOrder bean, String itemName, Integer type, String realName, GridReq gridReq) throws Exception {
		List<Map<String,Object>> list = mapper.findList(bean,itemName,type,realName,gridReq.getPage(),gridReq.getRows());
		int count = mapper.findCount(bean,itemName,type,realName);
		rs = DataUtils.mergeData(count, list, rs);
		return rs;
	}

	/**
	 * 保存
	 */
	@Override
	public Result saveData(Result rs, BeanTeacherOrder bean) throws Exception {
		DataUtils.trim(bean);
		if(ValidateUtils.isBlank(bean.getId())){
			bean.setCreateTime(new Date());
			mapper.insert(bean);
		}else{
			mapper.update(bean);
		}
		return rs;
	}

	/**
	 * 详情
	 */
	@Override
	public Result getData(Result rs, BeanTeacherOrder bean) throws Exception {
		rs.put("data", mapper.findDetailById(bean.getId()));
		return rs;
	}

	/**
	 * 删除
	 */
	@Override
	public Result delete(Result rs, List<Long> ids) throws Exception {
		rs.put("count", mapper.deletes(ids));
		return rs;
	}

	/**
	 * 更新状态
	 */
	@Override
	public Result upstate(Result rs, List<Long> ids, BeanTeacherOrder bean)
			throws Exception {
		if(!ValidateUtils.isBlank(bean.getState()) && !ValidateUtils.isEmptyForCollection(ids)){
			DataUtils.trim(bean);
			mapper.upstate(ids,bean);
		}else{
			rs.setStatus(Contents.ERROR);
			rs.setMsg("参数有误");
		}
		return rs;
	}

	/**
	 * 评价详情
	 */
	@Override
	public Result commentDetail(Result rs, Long id) throws Exception {
		if(!ValidateUtils.isBlank(id)){
			BeanTeacherComment comment = new BeanTeacherComment();
			comment.setOrderId(id);
			List<BeanTeacherComment> list = commentMapper.findAll(comment);
			rs.put("data", !ValidateUtils.isEmptyForCollection(list) ? list.get(0) : comment);
		}else{
			rs.setStatus(Contents.ERROR);
			rs.setMsg("参数有误");
		}
		return rs;
	}

	/**
	 * 导出
	 */
	@Override
	public void exportExcel(BeanTeacherOrder bean, String itemName,
			Integer type, String realName, HttpServletResponse response) {
		DataUtils.trim(bean);
		String[] particular = null;
		String[] property = null;
		if(bean.getRecordType() - 1 ==0){
			particular = new String[]{"预约讲师","用户真实姓名","申请人姓名","联系电话","讲学地点","讲学主题","开始时间-结束时间",
					"申请讲学场数","费用","状态","欧币抵扣","收款时间","实收金额","申请时间"};
			property = new String[]{"itemName","realName","nickName","phone","address","teachTheme","teachStart_teachEnd",
					"teachTimes","orderAmount","state1","discountRemark","pay_date1","actualAmount","createTime"};
		}else{
			particular = new String[]{"预约陪伴师","用户真实姓名","申请人姓名","联系电话","讲学地点","讲学主题","开始时间-结束时间",
					"费用","状态","欧币抵扣","收款时间","实收金额","申请时间"};
			property = new String[]{"itemName","realName","nickName","phone","address","teachTheme","teachStart_teachEnd",
					"orderAmount","state1","discountRemark","pay_date","actualAmount","createTime"};
		}
		ExcelUtils.downExcelList("1".equals(type) ? "预订成功表单.xlsx":"已完成表单.xlsx", particular, property, 
				mapper.findList(bean,itemName,type,realName,0,999999),response);
	}

}
