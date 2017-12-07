package com.kapphk.web.controller.inter.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kapphk.web.bean.product.BeanAppProductOrder;
import com.kapphk.web.service.inter.imethod.product.InterfaceBeanAppProductOrderService;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;

@RestController
@RequestMapping("/productOrder/")
public class InterfaceBeanAppProductOrderController {

	@Autowired
	private InterfaceBeanAppProductOrderService service;
	
	/**
	 * 商品评价列表
	 * @author dengwen 
	 * 2016-12-19下午4:32:39
	 */
	@RequestMapping("getProductCommtentList.do")
	public Result getProductCommtentList(Long productId,Integer page) {
		Result rs = new Result();
		try {
			rs = service.getProductCommtentList(productId,page, rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("出现错误");
		}
		return rs;
	}
	
	/**
	 * 保存订单
	 * @author dengwen 
	 * 2016-12-20下午3:34:46
	 */
	@RequestMapping("saveProductOrder.do")
	public Result saveProductOrder(BeanAppProductOrder bean,String root) {
		Result rs = new Result();
		try {
			rs = service.saveProductOrder(bean,root, rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("出现错误");
		}
		return rs;
	}
	
	/**
	 * 订单详情
	 * @author dengwen 
	 * 2016-12-20下午5:11:29
	 */
	@RequestMapping("getProductOrderDetail.do")
	public Result getProductOrderDetail(Long id) {
		Result rs = new Result();
		try {
			rs = service.getProductOrderDetail(id, rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("出现错误");
		}
		return rs;
	}
	
	/**
	 * 商品订单列表
	 * @author dengwen 
	 * 2016-12-21上午11:38:29
	 */
	@RequestMapping("getProductOrderList.do")
	public Result getProductOrderList(BeanAppProductOrder bean,Integer page) {
		Result rs = new Result();
		try {
			rs = service.getProductOrderList(bean,page, rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("出现错误");
		}
		return rs;
	}
	
	/**
	 * 保存评价
	 * @author dengwen 
	 * 2016-12-21上午11:38:29
	 */
	@RequestMapping("saveProductOrderComment.do")
	public Result saveProductOrderComment(BeanAppProductOrder bean) {
		Result rs = new Result();
		try {
			rs = service.saveProductOrderComment(bean , rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("出现错误");
		}
		return rs;
	}
	
	
}
