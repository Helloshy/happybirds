package com.kapphk.web.service.web.imethod.homepage;

import com.kapphk.web.utils.GridReq;
import com.kapphk.web.bean.homepage.BeanAppNews;
import com.kapphk.web.utils.Result;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
/**
 * 欧巴今日说业务层
 * @author dengwen
 * 2016-09-23 17:39:09
 */
public interface BeanAppNewsService {

	/**
	 * 查询列表
	 */
	public Result searchList(Result rs, BeanAppNews bean, GridReq gridReq) throws Exception;

	/**
	 * 保存
	 */
	public Result saveData(Result rs, BeanAppNews bean,MultipartFile file,HttpServletRequest request) throws Exception;

	/**
	 * 详情
	 */
	public Result getData(Result rs, BeanAppNews bean) throws Exception;

	/**
	 * 删除
	 */
	public Result delete(Result rs, List<Long> ids) throws Exception;

	/**
	 * 保存置顶
	 * @author dengwen 
	 * 2016-9-26上午9:11:24
	 */
	public Result saveTop(Result rs, BeanAppNews bean) throws Exception;

	public Result saveQuality(Result rs, BeanAppNews bean) throws Exception;

}
