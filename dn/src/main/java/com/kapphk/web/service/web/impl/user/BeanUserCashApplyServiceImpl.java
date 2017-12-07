package com.kapphk.web.service.web.impl.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.web.bean.user.BeanUserCashApply;
import com.kapphk.web.dao.mapper.user.BeanUserCashApplyMapper;
import com.kapphk.web.service.web.imethod.user.BeanUserCashApplyService;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.ExcelUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;
@Service
public class BeanUserCashApplyServiceImpl implements BeanUserCashApplyService {

	@Autowired
	private BeanUserCashApplyMapper mapper;
	/**
	 * 查询列表
	 */
	@Override
	public Result searchList(Result rs, BeanUserCashApply bean, String userName, String realName, 
			String startTime, String endTime, GridReq gridReq) throws Exception {
		String[] str = {startTime,endTime}; 
		DataUtils.trim(str);
		List<Map<String,Object>> list = mapper.findList(bean,userName,realName,str[0],str[1],gridReq.getPage(),gridReq.getRows());
		int count = mapper.findCount(bean,userName,realName,str[0],str[1]);
		rs = DataUtils.mergeData(count, list, rs);
		return rs;
	}

	/**
	 * 保存
	 */
	@Override
	public Result saveData(Result rs, BeanUserCashApply bean) throws Exception {
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
	public Result getData(Result rs, BeanUserCashApply bean) throws Exception {
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
	 * 更新状态
	 */
	@Override
	public Result upstatus(Result rs, List<Long> ids, Integer state) throws Exception {
		rs.put("count", mapper.upstatus(ids,state));
		return rs;
	}

	/**
	 * 导出
	 */
	@Override
	public void exportExcel(HttpServletResponse response,BeanUserCashApply bean, String userName, 
			String realName, String startTime, String endTime) {
		String[] str = {startTime,endTime}; 
		DataUtils.trim(str);
		List<Map<String,Object>> list = mapper.findList(bean,userName,realName,str[0],str[1],0,9999999);
		String[] particular = new String[]{"手机号","真实姓名","开户银行","支行信息","银行卡账号","开户人姓名","提现金额","处理状态","申请时间"};
		String[] property = new String[]{"userName","realName","bankName","bankInfo","cardId","cardSignature","amount","state1","createTime"};
		System.out.println(ExcelUtils.downExcelList("提现申请.xlsx", particular, property, list, response));
	}

}
