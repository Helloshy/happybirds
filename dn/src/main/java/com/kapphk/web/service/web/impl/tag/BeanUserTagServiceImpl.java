package com.kapphk.web.service.web.impl.tag;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kapphk.web.bean.tag.BeanUserTag;
import com.kapphk.web.dao.mapper.tag.BeanUserTagMapper;
import com.kapphk.web.service.web.imethod.tag.BeanUserTagService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.FileUploadUtils;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;

@Service
public class BeanUserTagServiceImpl implements BeanUserTagService {

	@Autowired
	private BeanUserTagMapper mapper;

	/**
	 * 查询列表
	 */
	@Override
	public Result searchList(Result rs, BeanUserTag bean, GridReq gridReq) throws Exception {
		
		 List<BeanUserTag> list = mapper.findByPage(bean, gridReq.getPage(), gridReq.getRows());
		 int count = mapper.findByPageCount(bean); 
		 rs = DataUtils.mergeData(count, list, rs);
		 return rs;
	}

	/**
	 * 保存
	 */
	@Override
	public Result saveData(Result rs, BeanUserTag bean, MultipartFile file,
			HttpServletRequest request) throws Exception {

		// 上传图片
		if (null != file && !file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				String logPath = FileUploadUtils.uploadFile(bytes,"upload/public", "2", request);
				bean.setLogoPath(logPath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if("区域管理".equals(bean.getTagType()) || "资讯分类".equals(bean.getTagType()) 
				|| "学科特长".equals(bean.getTagType()) || "个人身份".equals(bean.getTagType())){
			BeanUserTag userTag = new BeanUserTag();
			userTag.setId(bean.getId());
			userTag.setTagType(bean.getTagType());
			List<BeanUserTag> list = mapper.findAll(userTag);
			String msg = "";
			if("区域管理".equals(bean.getTagType())){
				msg = "该区域讲师团名已存在，请更改";
			}else if("资讯分类".equals(bean.getTagType())){
				msg = "分类名称已存在，请更改";
			}else if("学科特长".equals(bean.getTagType())){
				msg = "学科特长已存在，请更改";
			}else if("个人身份".equals(bean.getTagType())){
				msg = "个人身份已存在，请更改";
			}
			if (ValidateUtils.isBlank(bean.getOldId())) {
				bean.setCreateTime(new Date());
				if(ValidateUtils.isEmptyForCollection(list)){
					mapper.insert(bean);
				}else{
					rs.setStatus(Contents.ERROR);
					rs.setMsg(msg);
				}
			} else {
				if(!ValidateUtils.isEmptyForCollection(list) && !list.get(0).getId().equals(bean.getOldId())){
					rs.setStatus(Contents.ERROR);
					rs.setMsg(msg);
				}else{
					mapper.update(bean);
				}
			}
		}else{
			mapper.update(bean);
		}
		return rs;
	}

	/**
	 * 详情
	 */
	@Override
	public Result getData(Result rs, BeanUserTag bean) throws Exception {
		rs.put("data", mapper.findAll(bean).get(0));
		return rs;
	}

	/**
	 * 删除
	 */
	@Override
	public Result delete(Result rs, List<String> ids,BeanUserTag bean) throws Exception {
		if("区域管理".equals(bean.getTagType())){
			try {
				rs.put("count", mapper.dele(ids,bean));
			} catch (Exception e) {
				e.printStackTrace();
				rs.setStatus(Contents.ERROR);
				rs.setMsg("该讲师团已存在讲师，必须先删除讲师");
			}
		}else{
			rs.put("count", mapper.dele(ids,bean));
		}
		return rs;
	}

	/**
	 * 获取标签列表
	 */
	@Override
	public List<Map<String, Object>> searchTagTypeList(BeanUserTag bean,String type) {
		List<Map<String, Object>> list = mapper.findTagTypeList(bean);
		if(!ValidateUtils.isBlank(type)){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id", "");
			map.put("text", "全部");
			list.add(0, map);
		}
		return list;
	}

}
