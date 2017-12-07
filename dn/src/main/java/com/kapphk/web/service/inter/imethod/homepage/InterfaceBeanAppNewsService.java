package com.kapphk.web.service.inter.imethod.homepage;

import java.math.BigDecimal;
import java.util.List;

import com.kapphk.web.bean.course.BeanCourseCollection;
import com.kapphk.web.bean.homepage.BeanAppNews;
import com.kapphk.web.bean.user.BeanUserCollection;
import com.kapphk.web.service.BaseService;
import com.kapphk.web.utils.Result;

public interface InterfaceBeanAppNewsService extends BaseService<BeanAppNews, Long> {

	/**
	 * 资讯列表
	 * @author zoneyu 16-10-8
	 */
	public Result getNewsList(String newsTag, Integer page ,Long uid, Integer recordType, Result rs) throws Exception ;

	/**
	 * 资讯详情
	 * @author zoneyu 16-10-9
	 */
	public String upNewsDetail(Long id) throws Exception ;

	/**
	 * 资讯功能键
	 * @author zoneyu 16-10-9
	 */
	public Result saveOperater(BeanUserCollection bean,Integer type,Result rs) throws Exception ;

	/**
	 * 首页资讯、课程推荐、陪伴师推荐
	 * @author zoneyu 16-10-10
	 */
	public Result getHomeData(BigDecimal longitude, BigDecimal latitude, String city, Long uid, Result rs) throws Exception ;

	/**
	 * 欧巴资讯
	 * @author zoneyu 16-10-11
	 */
	public Result getList(String recordType, Integer page, Long uid, Result rs) throws Exception ;
	
	/**
	 * 资讯收藏列表
	 * @author dengwen 
	 * 2016-10-14下午2:10:30
	 */
	public Result getCollectionList(BeanCourseCollection bean, Integer page, Result rs)throws Exception;

	/**
	 * 取消资讯收藏
	 * @author dengwen 
	 * 2016-10-14下午3:56:42
	 */
	public Result deleteCollection(List<Long> ids, Result rs)throws Exception;

	/**
	 * 公司公告列表
	 * @author dengwen 
	 * 2016-10-14下午5:49:10
	 */
	public Result getNoticeList(BeanAppNews bean, Integer page, Result rs) throws Exception;

	/**
	 * 公司公告详情
	 * @author dengwen 
	 * 2016-11-12下午3:08:07
	 */
	public BeanAppNews getNoticeDetail(Long id);

	/**
	 * 资讯赞赏记录
	 * @author dengwen 16-11-15
	 */
	public Result getRecord(Long id, Long uid, Result rs) throws Exception;

	/**
	 * 动能集团所有的详情链接
	 * @author zoneyu 16-12-13
	 */
	public Result getDN(String title,Result rs) throws Exception;

	/**
	 * 动能集团所有的详情
	 * @author zoneyu 16-12-13
	 */
	public String getLinkInfo(Long id) throws Exception;

}
