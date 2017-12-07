package com.kapphk.web.service.web.impl.community;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.web.bean.community.BeanUserCommunity;
import com.kapphk.web.dao.mapper.community.BeanUserCommunityMapper;
import com.kapphk.web.service.web.imethod.community.BeanUserCommunityService;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.ExcelUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;
@Service
public class BeanUserCommunityServiceImpl implements BeanUserCommunityService {

	@Autowired
	private BeanUserCommunityMapper mapper;
	/**
	 * 查询列表
	 */
	@Override
	public Result searchList(Result rs, String itemName, String userName, String realName, GridReq gridReq) throws Exception {
		List<Map<String,Object>> list = mapper.findList(itemName,userName,realName,gridReq.getPage(),gridReq.getRows());
		int count = mapper.findCount(itemName,userName,realName);
		rs = DataUtils.mergeData(count, list, rs);
		return rs;
	}

	/**
	 * 保存
	 */
	@Override
	public Result saveData(Result rs, BeanUserCommunity bean) throws Exception {
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
	public Result getData(Result rs, BeanUserCommunity bean) throws Exception {
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

	/**
	 * 导出数据
	 */
	@Override
	public void exportExcel(HttpServletResponse response) {
		String[] particular = {"小组名称","区长手机号","区长姓名","社区身份","真实姓名","手机号","上级姓名","上级手机号","创建时间"};
		String[] property = {"itemName","managerPhone","managerName","tagValue","realName","userName","sjRealName","sjUserName","createTime"};
		ExcelUtils.downExcelList("社区成员管理.xlsx", particular, property, mapper.findList(null,null,null,0,9999999),response);
	}

}
