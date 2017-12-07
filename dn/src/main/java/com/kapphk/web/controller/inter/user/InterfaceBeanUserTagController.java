package com.kapphk.web.controller.inter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kapphk.web.bean.tag.BeanUserTag;
import com.kapphk.web.controller.BaseController;
import com.kapphk.web.service.inter.imethod.user.InterfaceBeanUserTagService;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;

/**
 * 标签控制层
 * @author zoneyu 16-9-30
 */
@RestController
@RequestMapping("/tag/")
public class InterfaceBeanUserTagController extends BaseController<BeanUserTag, Long> {
	
	@Autowired
	private InterfaceBeanUserTagService service ;
	
	/**
	 * 标签集合
	 * @author zoneyu 16-9-30
	 */
	@RequestMapping("getTagList.do")
	public Result getTagList(String tagType){
		Result rs = new Result() ;
		try {
			rs = service.getTagList(tagType,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
}
