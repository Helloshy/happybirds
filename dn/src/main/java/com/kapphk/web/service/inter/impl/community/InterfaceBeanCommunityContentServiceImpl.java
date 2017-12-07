package com.kapphk.web.service.inter.impl.community;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.web.bean.community.BeanCommunityCollection;
import com.kapphk.web.bean.community.BeanCommunityContent;
import com.kapphk.web.bean.community.BeanContentComment;
import com.kapphk.web.bean.system.BeanSetting;
import com.kapphk.web.bean.user.BeanUser;
import com.kapphk.web.bean.user.BeanUserCurrency;
import com.kapphk.web.dao.mapper.community.BeanCommunityCollectionMapper;
import com.kapphk.web.dao.mapper.community.BeanCommunityContentMapper;
import com.kapphk.web.dao.mapper.community.BeanContentCommentMapper;
import com.kapphk.web.dao.mapper.system.BeanSettingMapper;
import com.kapphk.web.dao.mapper.user.BeanUserCurrencyMapper;
import com.kapphk.web.dao.mapper.user.BeanUserMapper;
import com.kapphk.web.service.BaseServiceImpl;
import com.kapphk.web.service.inter.imethod.community.InterfaceBeanCommunityContentService;
import com.kapphk.web.utils.DateUtils;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;
import com.kapphk.web.utils.ValidateUtils;

/**
 * 社区动态业务层
 * @author zoneyu  16-11-2
 */
@Service("interfaceBeanCommunityContentService")
public class InterfaceBeanCommunityContentServiceImpl extends BaseServiceImpl<BeanCommunityContent, Long>
		implements InterfaceBeanCommunityContentService{

	@Autowired
	private BeanCommunityContentMapper mapper;
	
	//动态&公告评论
	@Autowired
	private BeanContentCommentMapper contentCommentMapper ;
	
	//动态&公告点赞
	@Autowired
	private BeanCommunityCollectionMapper communityCollectionMapper ;
	
	//设置
	@Autowired
	private BeanSettingMapper settingMapper ;
	
	//用户
	@Autowired
	private BeanUserMapper userMapper ;
	
	//用户流水
	@Autowired
	private BeanUserCurrencyMapper userCurrentcyMapper ;

	public void init() {
		super.setMapper(mapper);
	}

	/**
	 * 热门动态
	 * @author zoneyu 16-11-2
	 */
	public Result searchDynamicMax(Long uid,Long communityId, Integer page, Result rs) throws Exception {
		page = ValidateUtils.isBlank(page) ? PAGE : page ;
		if(!ValidateUtils.isBlank(communityId) && !ValidateUtils.isBlank(uid)){
			List<Map<String,Object>> list = mapper.searchDynamicMax(uid,communityId,(page-1)*ROWS,ROWS) ;
			if(!ValidateUtils.isEmptyForCollection(list)){
				for(Map<String,Object> map : list){
					String logoPath = (String) map.get("logoPath") ;
					List<String> photoList = new ArrayList<String>() ;
					if(!ValidateUtils.isBlank(logoPath)){
						String[] photos = logoPath.split(",") ;
						for(String s : photos){
							photoList.add(s) ;
						}
					}
					map.put("logoPath", photoList) ;
				}
			}
			rs.put("info", list) ;
		}else{
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("参数错误") ;
		}
		return rs ;
	}

	/**
	 * 公告&动态评论
	 * @author zoneyu 16-11-5
	 */
	public Result searchCommunityContentList(Long id, Integer page, Result rs) throws Exception {
		page = ValidateUtils.isBlank(page) ? PAGE : page ;
		if(!ValidateUtils.isBlank(id)){
			List<Map<String,Object>> list = mapper.searchCommunityContentList(id,(page-1)*ROWS,ROWS) ;
			rs.put("info", list) ;
		}else{
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("参数错误") ;
		}
		return rs ;
	}

	/**
	 * 保存公告&动态
	 * @author zoneyu 16-11-5
	 */
	public Result saveCommunityContent(BeanCommunityContent bean, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(bean.getUid()) && !ValidateUtils.isBlank(bean.getCommunityId())
				&& !ValidateUtils.isBlank(bean.getRecordType())){
			bean.setCreateTime(new Date());
			mapper.insert(bean) ;
			
			//获得蓝币
			if(bean.getRecordType() == 2){
				//查询当前用户动态发布数
				int count = mapper.searchCommunityCounts(bean.getUid(),DateUtils.getLocalDate("yyyy-MM-dd")) ;
				if(count < 3){
					Map<Integer, String> map = initSetting() ;
					String key = map.get((count+1)) ;
					BeanSetting set = new BeanSetting() ;
					set.setId(key);
					set.setItemType("blue_curreny_get");
					List<BeanSetting> list = settingMapper.findByPage(set, 0, 1) ;
					if(!ValidateUtils.isEmptyForCollection(list)){
						String value = list.get(0).getValue() ;
						BeanUser user = userMapper.findById(bean.getUid()) ;
						BigDecimal add = new BigDecimal(value) ;
						if(!ValidateUtils.isBlank(user)){
							BigDecimal blueCurrency = ValidateUtils.isBlank(user.getBlueCurrency()) ? BigDecimal.ZERO : user.getBlueCurrency() ;
							user.setBlueCurrency(blueCurrency.add(add));
							userMapper.update(user) ;
							
							//添加流水
							BeanUserCurrency currency = new BeanUserCurrency() ;
							currency.setContent("社区动态发布");
							currency.setCreateTime(new Date());
							currency.setCurrency(add);
							currency.setCurrencyType(1);
							currency.setRecordType(9);
							currency.setUid(bean.getUid());
							userCurrentcyMapper.insert(currency) ;
						}
						
					}
				}
			}
		}else{
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("参数错误") ;
		}
		return rs ;
	}
	
	private Map<Integer,String> initSetting(){
		Map<Integer,String> map = new HashMap<Integer, String>() ;
		map.put(1, "cur_firt_topic") ;
		map.put(2, "cur_second_topic") ;
		map.put(3, "cur_third_topic") ;
		return map ;
	}

	/**
	 * 帖子功能呢键
	 * @author zoneyu 16-11-10
	 */
	public Result operator(Long id, Long uid, Integer type, String content, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(id) && !ValidateUtils.isBlank(type)){
			if(type == 1){//举报
				BeanCommunityContent bean = new BeanCommunityContent() ;
				bean.setId(id);
				bean.setIsReport(1);
				mapper.update(bean) ;
			}else if(type == 2){//删除评论
				BeanContentComment bean = new BeanContentComment() ;
				bean.setId(id);
				bean.setUid(uid);
				contentCommentMapper.deleteByEntity(bean) ;
			}else if(type == 3){//评论
				if(!ValidateUtils.isBlank(content)){
					BeanContentComment bean = new BeanContentComment() ;
					bean.setContent(content);
					bean.setContentId(id);
					bean.setCreateTime(new Date());
					bean.setUid(uid);
					contentCommentMapper.insert(bean) ;
				}else{
					rs.setStatus(MSG.ERROR.getStatus()) ;
					rs.setMsg("评论内容不能为空") ;
				}
			}else if(type == 4){//删除帖子
				mapper.delete(id) ;
			}
		}else{
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("参数错误") ;
		}
		return rs ;
	}

	/**
	 * 帖子功能键
	 * @author zoneyu 16-11-10
	 */
	public Result savePoint(Long id, Long uid, Integer type, Result rs) throws Exception {
		if(!ValidateUtils.isBlank(id) && !ValidateUtils.isBlank(uid)){
			BeanCommunityCollection bean = new BeanCommunityCollection() ;
			bean.setContentId(id);
			bean.setUid(uid);
			int i = communityCollectionMapper.findByPageCount(bean) ;
			if(type == 1){//点赞
				if(i == 0){
					bean.setCreateTime(new Date());
					communityCollectionMapper.insert(bean) ;
					
					//点赞数加一
					BeanCommunityContent content = mapper.findById(id) ;
					content.setLikes(ValidateUtils.isBlank(content.getLikes()) ? 1 : (content.getLikes()+1));
					mapper.update(content) ;
				}else{
					rs.setStatus(MSG.ERROR.getStatus()) ;
					rs.setMsg("您已经点过赞了") ;
				}
			}else if(type == 0){//取消点赞
				if(i != 0){
					communityCollectionMapper.deleteByEntity(bean) ;
					
					//点赞数减一
					BeanCommunityContent content = mapper.findById(id) ;
					content.setLikes(ValidateUtils.isBlank(content.getLikes()) ? 0 : (content.getLikes()-1 < 0 ? 0 : content.getLikes()-1));
					mapper.update(content) ;
				}else{
					rs.setStatus(MSG.ERROR.getStatus()) ;
					rs.setMsg("您还没有点赞呢!") ;
				}
			}
		}else{
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("参数错误") ;
		}
		return rs ;
	}

}
