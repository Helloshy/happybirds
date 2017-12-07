package com.kapphk.web.controller.inter.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kapphk.web.bean.course.BeanAppGoods;
import com.kapphk.web.controller.BaseController;
import com.kapphk.web.service.inter.imethod.course.InterfaceBeanGoodsService;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;

/**
 * 物品控制层
 * @author zoneyu 16-10-20
 */
@RestController
@RequestMapping("/goods/")
public class InterfaceBeanAppGoodsController extends BaseController<BeanAppGoods, Long> {
	
	@Autowired
	private InterfaceBeanGoodsService service ;
	
	/**
	 * 物品列表
	 * @author zoneyu 16-10-20
	 */
	@RequestMapping("getGoodsList.do")
	public Result getGoodsList(){
		Result rs = new Result() ;
		try {
			rs = service.getGoodsList(rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
	
}
