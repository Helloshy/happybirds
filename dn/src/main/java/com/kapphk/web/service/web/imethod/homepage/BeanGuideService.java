package com.kapphk.web.service.web.imethod.homepage;

import com.kapphk.web.utils.GridReq;
import com.kapphk.web.bean.homepage.BeanGuide;
import com.kapphk.web.utils.Result;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
/**
 * 轮播图业务层
 * @author dengwen
 * 2016-09-21 10:36:39
 */
public interface BeanGuideService {

	/**
	 * 查询列表
	 */
	public Result searchList(Result rs, BeanGuide bean, GridReq gridReq) throws Exception;

	/**
	 * 保存
	 */
	public Result saveData(Result rs, BeanGuide bean, MultipartFile file, HttpServletRequest request) throws Exception;

	/**
	 * 详情
	 */
	public Result getData(Result rs, BeanGuide bean) throws Exception;

	/**
	 * 删除
	 */
	public Result delete(Result rs, List<Long> ids) throws Exception;

	public Result findCount(Result rs, BeanGuide bean) throws Exception;

}
