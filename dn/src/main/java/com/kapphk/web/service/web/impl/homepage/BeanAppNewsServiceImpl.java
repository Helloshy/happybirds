package com.kapphk.web.service.web.impl.homepage;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kapphk.web.bean.homepage.BeanAppNews;
import com.kapphk.web.dao.mapper.homepage.BeanAppNewsMapper;
import com.kapphk.web.service.web.imethod.homepage.BeanAppNewsService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.FileUploadUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;
@Service
public class BeanAppNewsServiceImpl implements BeanAppNewsService {

	@Autowired
	private BeanAppNewsMapper mapper;
	/**
	 * 查询列表
	 */
	@Override
	public Result searchList(Result rs, BeanAppNews bean, GridReq gridReq) throws Exception {
		DataUtils.trim(bean);
		List<BeanAppNews> list = mapper.findList(bean, gridReq.getPage(), gridReq.getRows());
		int count = mapper.findCount(bean);
		rs = DataUtils.mergeData(count, list, rs);
		return rs;
	}

	/**
	 * 保存
	 */
	@Override
	public Result saveData(Result rs, BeanAppNews bean,MultipartFile file,HttpServletRequest request) throws Exception {
		if(null != file && !file.isEmpty()){
			try {
				byte[] bytes = file.getBytes();
				String logPath = FileUploadUtils.uploadFile(bytes,"upload/public", "2", request);
				bean.setLogoPath(logPath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if( !ValidateUtils.isBlank(bean.getIsTop()) && bean.getIsTop() - 1 == 0){
			//更新之前置顶数据
			mapper.upIsTop(bean);
		}
		if(ValidateUtils.isBlank(bean.getId())){
			bean.setCreateTime(new Date());
			mapper.insert(bean);
		}else{
			mapper.update(bean);
		}
		return rs;
	}

	/**
	 * 详情
	 */
	@Override
	public Result getData(Result rs, BeanAppNews bean) throws Exception {
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

	/**
	 * 保存置顶
	 */
	@Override
	public Result saveTop(Result rs, BeanAppNews bean) throws Exception {
		if(!ValidateUtils.isBlank(bean.getId()) && !ValidateUtils.isBlank(bean.getIsTop())){
			if(bean.getIsTop() - 1 == 0){
				//更新之前置顶数据
				mapper.upIsTop(bean);
			}
			mapper.update(bean);
		}else{
			rs.setStatus(Contents.ERROR);
			rs.setMsg("参数有误");
		}
		return rs;
	}

	@Override
	public Result saveQuality(Result rs, BeanAppNews bean) throws Exception {
		if(!ValidateUtils.isBlank(bean.getId())){
			if(!ValidateUtils.isBlank(bean.getIsHome()) && bean.getIsHome() - 1 == 0){
				BeanAppNews news = new BeanAppNews();
				news.setRecordType(3);
				news.setIsHome(1);
				if(mapper.findByPageCount(news) - 4 ==0){
					rs.setStatus(Contents.ERROR);
					rs.setMsg("首页推荐最多4条，请先取消其中一条");
					return rs;
				}
			}
			mapper.update(bean);
		}else{
			rs.setStatus(Contents.ERROR);
			rs.setMsg("参数有误");
		}
		return rs;
	}

}
