package com.kapphk.web.service.web.impl.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jpush.api.utils.Jgpush;

import com.kapphk.web.bean.system.BeanMessageDetail;
import com.kapphk.web.bean.system.BeanMessagePush;
import com.kapphk.web.dao.mapper.system.BeanMessageDetailMapper;
import com.kapphk.web.dao.mapper.system.BeanMessagePushMapper;
import com.kapphk.web.dao.mapper.user.BeanUserMapper;
import com.kapphk.web.service.web.imethod.system.BeanMessagePushService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.DataUtils;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;

/**
 * 推送消息业务层
 * @author zoneyu 16-3-21
 */
@Service
public class BeanMessagePushServiceImpl implements BeanMessagePushService {

	@Autowired
	private BeanMessagePushMapper mapper ;
	
	@Autowired
	private BeanUserMapper userMapper;
	
	@Autowired
	private BeanMessageDetailMapper detailMapper;
	
	/**
	 * 查询
	 * @author zoneyu 2016-4-4
	 */
	public Result searchList(BeanMessagePush MessagePush, int page, int rows, Result rs)
			throws Exception {
		int count = mapper.findByPageCount(MessagePush) ;
		List<BeanMessagePush> list = mapper.findByPage(MessagePush, (page-1)*rows, rows) ;
		return DataUtils.mergeData(count, list, rs) ;
	}

	/**
	 * 保存
	 * @author zoneyu 2016-4-4
	 */
	public Result saveMessagePush(BeanMessagePush bean, String[] type, Result rs) throws Exception {
		if(ValidateUtils.isBlank(bean.getId())){
			Date date = new Date();
			if(ValidateUtils.isempty(type)){
				rs.setStatus(Contents.ERROR);
				rs.setMsg("浏览身份不能为空");
				return rs;
			}
			List<String> list = new ArrayList<String>();
			if("全部".equals(type[0])){
				bean.setRemark("全部");
				list = userMapper.findByPositionList(null);
			}else{
				List<String> positionList = new ArrayList<String>();
				String remark = "";
				for (String str : type) {
					remark += str.trim()+"、";
					positionList.add(str.trim());
				}
				bean.setRemark(remark.substring(0, remark.length()-1));
				list = userMapper.findByPositionList(positionList);
			}
			bean.setCreateTime(date);
			mapper.insert(bean);
			List<BeanMessageDetail> detailList = new ArrayList<BeanMessageDetail>();
			for (String id : list) {
				BeanMessageDetail detail = new BeanMessageDetail();
				detail.setUid(Long.valueOf(id));
				detail.setMessageId(bean.getId());
				detail.setCreateTime(date);
				detail.setIsRead(0);
				detailList.add(detail);
			}
			if(!ValidateUtils.isEmptyForCollection(detailList)) detailMapper.inserts(detailList);
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("id", bean.getId().toString());
			map.put("type", "1");
			try {
				String[] alias = DataUtils.list2String(list).split(",") ;
				Jgpush.sendMessage_simple(bean.getTitle(), alias, map);
				if("全部".equals(type[0])){
					Jgpush.sendMessage_all(bean.getTitle(), map);
				}else{
					String[] ids = new String[800];
					int index = 0;
					for (int i = 0; i < list.size(); i++) {
						ids[index] = "alias"+String.valueOf(list.get(i));
						index++;
						if(index == 800){
							Jgpush.sendMessage_simple(bean.getTitle(), ids, map);
							ids = new String[800];
							index = 0;
						}
					}
					if(index != 0 ){
						String[] ids1 = new String[index];
						for (int i = 0; i < ids.length; i++) {
							if(index == i) break;
							ids1[i] = ids[i];
						}
						Jgpush.sendMessage_simple(bean.getTitle(), ids1, map);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			mapper.update(bean);
		}
		return rs ;
	}

	/**
	 * 详情
	 */
	@Override
	public Result getData(Long id, Result rs) throws Exception {
		return rs.put("data", mapper.findById(id));
	}

	/**
	 * 删除
	 * @author zoneyu 2016-4-4
	 */
	public Result delete(String ids, Result rs) throws Exception {
		mapper.deletes(DataUtils.string2List(ids)) ;
		return rs;
	}

}
