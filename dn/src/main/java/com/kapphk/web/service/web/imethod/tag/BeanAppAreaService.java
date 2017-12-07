package com.kapphk.web.service.web.imethod.tag;

import com.kapphk.web.utils.GridReq;
import com.kapphk.web.bean.tag.BeanAppArea;
import com.kapphk.web.utils.Result;
import java.util.List;
import java.util.Map;
/**
 * 省份区域管理业务层
 * @author dengwen
 * 2016-10-28 14:37:41
 */
public interface BeanAppAreaService {

	/**
	 * 查询列表
	 */
	public Result searchList(Result rs, BeanAppArea bean, GridReq gridReq) throws Exception;

	/**
	 * 保存
	 */
	public Result saveData(Result rs, BeanAppArea bean, List<String> areaDetail) throws Exception;

	/**
	 * 详情
	 */
	public Result getData(Result rs, BeanAppArea bean) throws Exception;

	/**
	 * 删除
	 */
	public Result delete(Result rs, List<String> ids) throws Exception;

	/**
	 * 获取全部区域
	 * @author dengwen 
	 * 2016-11-2上午11:39:54
	 */
	public List<Map<String, Object>> getAreaList();

}
