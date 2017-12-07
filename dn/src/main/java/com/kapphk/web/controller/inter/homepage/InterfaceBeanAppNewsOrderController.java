package com.kapphk.web.controller.inter.homepage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kapphk.web.bean.homepage.BeanAppNewsOrder;
import com.kapphk.web.controller.BaseController;
import com.kapphk.web.service.inter.imethod.homepage.InterfaceBeanAppNewsOrderService;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;

/**
 * 资讯赞赏控制层
 * @author zoneyu 16-10-19
 */
@RestController
@RequestMapping("/newsOrder/")
public class InterfaceBeanAppNewsOrderController extends BaseController<BeanAppNewsOrder, Long> {
	
	@Autowired
	private InterfaceBeanAppNewsOrderService service ;
	
	/**
	 * 保存赞赏订单
	 * @author zoneyu 16-10-19
	 */
	@RequestMapping("saveOrder.do")
	public Result saveOrder(BeanAppNewsOrder bean,String root){
		Result rs = new Result() ;
		try {
			rs = service.saveOrder(bean,root,rs) ;
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus()) ;
			rs.setMsg("出现错误") ;
		}
		return rs ;
	}
}
