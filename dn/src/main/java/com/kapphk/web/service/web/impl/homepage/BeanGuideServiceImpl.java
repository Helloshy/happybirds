package com.kapphk.web.service.web.impl.homepage;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kapphk.redis.dao.JedisDao;
import com.kapphk.web.bean.homepage.BeanGuide;
import com.kapphk.web.dao.mapper.homepage.BeanGuideMapper;
import com.kapphk.web.service.web.imethod.homepage.BeanGuideService;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.FileUploadUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;
@Service
public class BeanGuideServiceImpl implements BeanGuideService {

	@Autowired
	private BeanGuideMapper mapper;
	
	@Resource(name="jedisClient")
	private JedisDao jedisDao ;
	
	/**
	 * 查询列表
	 */
	@Override
	public Result searchList(Result rs, BeanGuide bean, GridReq gridReq) throws Exception {
		DataUtils.trim(bean);
		List<Map<String,Object>> list = mapper.findList(bean, gridReq.getPage(), gridReq.getRows());
		int count = mapper.findCount(bean);
		rs = DataUtils.mergeData(count, list, rs);
		return rs;
	}

	/**
	 * 保存
	 */
	@Override
	public Result saveData(Result rs, BeanGuide bean, MultipartFile file,HttpServletRequest request) throws Exception {
		DataUtils.trim(bean);
		//上传图片
		if(!file.isEmpty()){
			try {
				byte[] bytes = file.getBytes();
				String logPath = FileUploadUtils.uploadFile(bytes,"upload/public", "2", request);
				bean.setLogoPath(logPath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(ValidateUtils.isBlank(bean.getId())){
			bean.setCreateTime(new Date());
			mapper.insert(bean);
		}else{
			mapper.update(bean);
		}
		//重新覆盖redis中的数据  zoneyu 16-12-23
		List<Map<String,Object>> list = mapper.getHomeGuideList() ;
		if(!ValidateUtils.isEmptyForCollection(list)){
			for(Map<String,Object> map : list){
				String itemLink = (String) map.get("itemLink") ;
				Long id = (Long) map.get("id") ;
				if(ValidateUtils.isBlank(itemLink)){
					String url = "/guide/getGuideDetail.do?id="+id ;
					map.put("url", url) ;
				}else{
					map.put("url", "") ;
				}
			}
			jedisDao.hashSaveEntity("banner", "index", list);
		}
		return rs;
	}

	/**
	 * 详情
	 */
	@Override
	public Result getData(Result rs, BeanGuide bean) throws Exception {
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
	public Result findCount(Result rs, BeanGuide bean) throws Exception {
		rs.put("count", mapper.findByPageCount(bean));
		return rs;
	}

}
