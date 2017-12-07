package com.kapphk.web.service.web.impl.tag;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.web.bean.tag.BeanAppArea;
import com.kapphk.web.bean.tag.BeanAppAreaDetail;
import com.kapphk.web.dao.mapper.tag.BeanAppAreaDetailMapper;
import com.kapphk.web.dao.mapper.tag.BeanAppAreaMapper;
import com.kapphk.web.service.web.imethod.tag.BeanAppAreaService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;
@Service
public class BeanAppAreaServiceImpl implements BeanAppAreaService {

	@Autowired
	private BeanAppAreaMapper mapper;
	
	@Autowired
	private BeanAppAreaDetailMapper detailMapper;
	/**
	 * 查询列表
	 */
	@Override
	public Result searchList(Result rs, BeanAppArea bean, GridReq gridReq) throws Exception {
		List<Map<String,Object>> list = mapper.findList(gridReq.getPage(),gridReq.getRows());
		int count = mapper.count();
		rs = DataUtils.mergeData(count, list, rs);
		return rs;
	}

	/**
	 * 保存
	 */
	@Override
	public Result saveData(Result rs, BeanAppArea bean, List<String> areaDetail) throws Exception {
		Date date = new Date();
		
		BeanAppArea area = mapper.findById(bean.getId());
		if(!ValidateUtils.isBlank(area)){
			if(ValidateUtils.isBlank(bean.getOldId()) || !area.getId().equals(bean.getOldId())){
				rs.setStatus(Contents.ERROR);
				rs.setMsg("该区域名称已存在，请更改");
				return rs;
			}
		}
		
		bean.setCreateTime(date);
		if(ValidateUtils.isBlank(bean.getOldId())){
			mapper.insert(bean);
		}else{
			mapper.update(bean);
		}
		
		BeanAppAreaDetail detail = new BeanAppAreaDetail();
		detail.setAreaId(bean.getId());
		detailMapper.deleteByEntity(detail);
		
		List<BeanAppAreaDetail> detailList = new ArrayList<BeanAppAreaDetail>();
		if(!ValidateUtils.isEmptyForCollection(areaDetail)){
			for (String s : areaDetail) {
				BeanAppAreaDetail d = new BeanAppAreaDetail();
				d.setAreaId(bean.getId());
				d.setCreateTime(date);
				d.setPid(s);
				detailList.add(d);
			}
		}
		
		if(!ValidateUtils.isEmptyForCollection(detailList))detailMapper.inserts(detailList);
		return rs;
	}

	/**
	 * 详情
	 */
	@Override
	public Result getData(Result rs, BeanAppArea bean) throws Exception {
		rs.put("data", mapper.findDetailById(bean.getId()));
		return rs;
	}

	/**
	 * 删除
	 */
	@Override
	public Result delete(Result rs, List<String> ids) throws Exception {
		rs.put("count", mapper.deletes(ids));
		return rs;
	}

	/**
	 * 获取全部区域
	 */
	@Override
	public List<Map<String, Object>> getAreaList() {
		return mapper.findAreaList();
	}

}
