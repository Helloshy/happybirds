package com.kapphk.web.service.web.impl.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.web.bean.user.BeanServiceLog;
import com.kapphk.web.dao.mapper.user.BeanServiceLogMapper;
import com.kapphk.web.service.web.imethod.user.BeanServiceLogService;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.ExcelUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;
@Service
public class BeanServiceLogServiceImpl implements BeanServiceLogService {

	@Autowired
	private BeanServiceLogMapper mapper;
	/**
	 * 查询列表
	 */
	@Override
	public Result searchList(Result rs, BeanServiceLog bean, String userName, String realName, GridReq gridReq) throws Exception {
		List<Map<String,Object>> list = mapper.findList(bean,userName,realName,gridReq.getPage(),gridReq.getRows());
		int count = mapper.findCount(bean,userName,realName);
		rs = DataUtils.mergeData(count, list, rs);
		return rs;
	}

	/**
	 * 保存
	 */
	@Override
	public Result saveData(Result rs, BeanServiceLog bean, HttpServletRequest request) throws Exception {
		if(ValidateUtils.isBlank(bean.getId())){
			bean.setServiceId((Long)request.getSession().getAttribute("userId"));
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
	public Result getData(Result rs, BeanServiceLog bean) throws Exception {
		rs.put("data", mapper.findById(bean.getId()));
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

	@Override
	public void exportExcel(HttpServletResponse response, BeanServiceLog bean) {
		List<Map<String,Object>> list = mapper.findList(bean,null,null,0,9999999);
		String[] particular = new String[]{"客户账号","客户姓名","回访内容","回访时间","回访员工账号","回访员工姓名"};
		String[] property = new String[]{"kuserName","krealName","logContent","logDate","userName","realName"};
		System.out.println(ExcelUtils.downExcelList("回访记录.xlsx", particular, property, list, response));
	}

}
