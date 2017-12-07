package com.kapphk.web.service.web.impl.community;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.web.bean.community.BeanCommunityContent;
import com.kapphk.web.dao.mapper.community.BeanCommunityContentMapper;
import com.kapphk.web.dao.mapper.community.BeanCommunityDistrictMapper;
import com.kapphk.web.dao.mapper.community.BeanCommunityMapper;
import com.kapphk.web.dao.mapper.community.BeanContentCommentMapper;
import com.kapphk.web.dao.mapper.tag.BeanAppAreaDetailMapper;
import com.kapphk.web.service.web.imethod.community.BeanCommunityContentService;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;
@Service
public class BeanCommunityContentServiceImpl implements BeanCommunityContentService {

	@Autowired
	private BeanCommunityContentMapper mapper;
	
	@Autowired
	private BeanContentCommentMapper commentMapper;
	
	@Autowired
	private BeanCommunityMapper communityMapper;
	
	@Autowired
	private BeanAppAreaDetailMapper detailMapper;
	
	@Autowired
	private BeanCommunityDistrictMapper districtMapper;
	/**
	 * 查询列表
	 */
	@Override
	public Result searchList(Result rs, BeanCommunityContent bean, String itemName, 
			String nickName, GridReq gridReq) throws Exception {
		List<Map<String,Object>> list = mapper.findList(bean,itemName,nickName,gridReq.getPage(),gridReq.getRows());
		int count = mapper.findCount(bean,itemName,nickName);
		rs = DataUtils.mergeData(count, list, rs);
		return rs;
	}

	/**
	 * 保存
	 */
	@Override
	public Result saveData(Result rs, BeanCommunityContent bean, String[] aid, String[] pid, Long[] cid,
			String aid_checkbox, String pid_checkbox, String cid_checkbox,String[] imgs) throws Exception {
		if(!ValidateUtils.isempty(imgs)){
			if(imgs.length == 3){
				bean.setLogoPath3(imgs[2]);
				bean.setLogoPath2(imgs[1]);
				bean.setLogoPath(imgs[0]);
			}else if(imgs.length == 2){
				bean.setLogoPath2(imgs[1]);
				bean.setLogoPath(imgs[0]);
			}else{
				bean.setLogoPath(imgs[0]);
			}
		}
		Date date = new Date();
		List<BeanCommunityContent> list = new ArrayList<BeanCommunityContent>();
		if(!ValidateUtils.isBlank(pid_checkbox) || !ValidateUtils.isBlank(cid_checkbox)){
			for (Map<String, Object> map : communityMapper.findCommunityList()) {
				BeanCommunityContent content = new BeanCommunityContent();
				content.setCommunityId((Long)map.get("id"));
				content.setRecordType(bean.getRecordType());
				content.setIsReport(0);
				content.setCreateTime(date);
				content.setLikes(0);
				content.setUid(-1l);
				content.setLogoPath(bean.getLogoPath());
				content.setLogoPath2(bean.getLogoPath2());
				content.setLogoPath3(bean.getLogoPath3());
				content.setContent(bean.getContent());
				list.add(content);
			}
		}else{
			List<String> proList = null;
			if(!ValidateUtils.isBlank(aid_checkbox) || !ValidateUtils.isempty(aid)) 
				proList = detailMapper.findProvinceList(aid_checkbox,ValidateUtils.isempty(aid) ? null : Arrays.asList(aid));
			List<Long> communityId =  new ArrayList<Long>();
			if(!ValidateUtils.isEmptyForCollection(proList) || !ValidateUtils.isempty(pid)){
				communityId = districtMapper.findCommunityId(ValidateUtils.isEmptyForCollection(proList) ? null : proList,
						ValidateUtils.isempty(pid) ? null : Arrays.asList(pid));
			}
			if(!ValidateUtils.isempty(cid)){
				for (Long id : cid) {
					if(!communityId.contains(id))communityId.add(id);
				}
			}
			for (Long l : communityId) {
				BeanCommunityContent content = new BeanCommunityContent();
				content.setCommunityId(l);
				content.setRecordType(bean.getRecordType());
				content.setIsReport(0);
				content.setCreateTime(date);
				content.setLikes(0);
				content.setUid(-1l);
				content.setLogoPath(bean.getLogoPath());
				content.setLogoPath2(bean.getLogoPath2());
				content.setLogoPath3(bean.getLogoPath3());
				content.setContent(bean.getContent());
				list.add(content);
			}
		}
		if(!ValidateUtils.isEmptyForCollection(list)) mapper.inserts(list);
		return rs;
	}

	/**
	 * 详情
	 */
	@Override
	public Result getData(Result rs, BeanCommunityContent bean) throws Exception {
		Map<String, Object> map = mapper.findDetailById(bean.getId());
		map.put("commentList", commentMapper.fineCommentList((Long) map.get("id")));
		rs.put("data", map);
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
	 * 删除评论
	 */
	@Override
	public Result deleteComment(Result rs, Long id) throws Exception {
		commentMapper.delete(id);
		return rs;
	}

}
