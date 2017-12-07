package com.kapphk.web.service.web.impl.system;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.web.bean.system.BeanUserApply;
import com.kapphk.web.dao.mapper.system.BeanUserApplyMapper;
import com.kapphk.web.service.web.imethod.system.BeanUserApplyService;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.ExcelUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;
@Service
public class BeanUserApplyServiceImpl implements BeanUserApplyService {

	@Autowired
	private BeanUserApplyMapper mapper;
	/**
	 * 查询列表
	 */
	@Override
	public Result searchList(Result rs, BeanUserApply bean, GridReq gridReq) throws Exception {
		DataUtils.trim(bean);
		List<Map<String,Object>> list = mapper.findList(bean,gridReq.getPage(),gridReq.getRows());
		int count = mapper.findCount(bean);
		rs = DataUtils.mergeData(count, list, rs);
		return rs;
	}

	/**
	 * 保存
	 */
	@Override
	public Result saveData(Result rs, BeanUserApply bean) throws Exception {
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
	public Result getData(Result rs, BeanUserApply bean) throws Exception {
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

	@Override
	public void exportExcel(BeanUserApply bean, HttpServletResponse response) {
		DataUtils.trim(bean);
		List<Map<String,Object>> list = mapper.findList(bean,0,9999999);
		String[] particular = {"申请来源","姓名","联系电话","省市区","申请内容","申请时间"};
		String[] property = {"recordType","userName","userTel","pcd","content","createTime"};
		ExcelUtils.downExcelList("申请管理表单.xlsx", particular, property, list,response);
	}

}
