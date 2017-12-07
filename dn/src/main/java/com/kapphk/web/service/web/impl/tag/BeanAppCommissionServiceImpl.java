package com.kapphk.web.service.web.impl.tag;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.web.bean.tag.BeanAppCommission;
import com.kapphk.web.bean.tag.BeanUserTag;
import com.kapphk.web.dao.mapper.tag.BeanAppCommissionMapper;
import com.kapphk.web.dao.mapper.tag.BeanUserTagMapper;
import com.kapphk.web.service.web.imethod.tag.BeanAppCommissionService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.ExcelUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;
@Service
public class BeanAppCommissionServiceImpl implements BeanAppCommissionService {

	@Autowired
	private BeanAppCommissionMapper mapper;
	
	@Autowired
	private BeanUserTagMapper tagMapper;
	/**
	 * 查询列表
	 */
	@Override
	public Result searchList(Result rs, BeanAppCommission bean, GridReq gridReq) throws Exception {
		List<BeanAppCommission> list = mapper.findByPage(bean, gridReq.getPage(), gridReq.getRows());
		int count = mapper.findByPageCount(bean);
		rs = DataUtils.mergeData(count, list, rs);
		return rs;
	}

	/**
	 * 保存
	 */
	@Override
	public Result saveData(Result rs, BeanAppCommission bean) throws Exception {
		
		BeanAppCommission commission = new BeanAppCommission();
		commission.setTagId(bean.getTagId());
		List<BeanAppCommission> list = mapper.findAll(commission);
		if(!ValidateUtils.isEmptyForCollection(list)){
			if(ValidateUtils.isBlank(bean.getId()) || bean.getId() - list.get(0).getId() != 0){
				rs.setStatus(Contents.ERROR);
				rs.setMsg("该身份已存在，请更改");
				return rs;
			}
		}
		bean.setModifyTime(new Date());
		BeanUserTag tag = new BeanUserTag();
		tag.setCreateTime(new Date());
		tag.setTagType("动能身份");
		tag.setId(bean.getTagId());
		if(ValidateUtils.isBlank(bean.getId())){
			bean.setCreateTime(new Date());
			bean.setTagType("动能身份");
			mapper.insert(bean);
			tagMapper.insert(tag);
		}else{
			tag.setOldId(mapper.findById(bean.getId()).getTagId());
			mapper.update(bean);
			tagMapper.update(tag);
		}
		return rs;
	}

	/**
	 * 详情
	 */
	@Override
	public Result getData(Result rs, BeanAppCommission bean) throws Exception {
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
	public void exportExcel(HttpServletResponse response) {
		List<Map<String,Object>> list = mapper.exportExcel();
		String[] particular = new String[]{"合作伙伴身份","一级分佣比例","二级分佣比例","三级分佣比例","一级客服身份升级返佣比例","修改时间"};
		String[] property = new String[]{"tagId","levelOne","levelTwo","levelThree","levelOneUpgrade","modifyTime"};
		System.out.println(ExcelUtils.downExcelList("提现申请.xlsx", particular, property, list, response));
	}

}
