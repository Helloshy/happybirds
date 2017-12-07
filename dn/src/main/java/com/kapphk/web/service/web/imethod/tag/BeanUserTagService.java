package com.kapphk.web.service.web.imethod.tag;

import com.kapphk.web.utils.GridReq;
import com.kapphk.web.bean.tag.BeanUserTag;
import com.kapphk.web.utils.Result;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
/**
 * 标签业务层
 * @author dengwen
 * 2016-09-29 09:10:52
 */
public interface BeanUserTagService {

	/**
	 * 查询列表
	 */
	public Result searchList(Result rs, BeanUserTag bean, GridReq gridReq) throws Exception;

	/**
	 * 保存
	 */
	public Result saveData(Result rs, BeanUserTag bean, MultipartFile file, HttpServletRequest request) throws Exception;

	/**
	 * 详情
	 */
	public Result getData(Result rs, BeanUserTag bean) throws Exception;

	/**
	 * 删除
	 */
	public Result delete(Result rs, List<String> ids,BeanUserTag bean) throws Exception;

	/**
	 * 获取标签列表
	 * @author dengwen 
	 * 2016-9-30上午11:45:36
	 */
	public List<Map<String, Object>> searchTagTypeList(BeanUserTag bean,String type);

}
