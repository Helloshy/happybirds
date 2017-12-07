package com.kapphk.web.controller.inter.product;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.kapphk.web.dao.mapper.product.BeanAppProductMapper;
import com.kapphk.web.service.inter.imethod.product.InterfaceBeanAppProductService;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.Result.MSG;

@RestController
@RequestMapping("/product/")
public class InterfaceBeanAppProductController {

	@Autowired
	private InterfaceBeanAppProductService service;
	
	@Autowired
	private BeanAppProductMapper productMapper;
	
	/**
	 * 商品列表
	 * @author dengwen 
	 * 2016-12-19下午4:32:39
	 */
	@RequestMapping("getProductList.do")
	public Result getProductList(Long categoryId,String itemName,Integer sales,Integer price,Integer page) {
		Result rs = new Result();
		try {
			rs = service.getProductList(categoryId, itemName, sales, price,page, rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("出现错误");
		}
		return rs;
	}
	
	/**
	 * 分类列表
	 * @author dengwen 
	 * 2016-12-19下午4:33:38
	 */
	@RequestMapping("getProductCategoryList.do")
	public Result getProductCategoryList() {
		Result rs = new Result();
		try {
			rs = service.getProductCategoryList(rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("出现错误");
		}
		return rs;
	}
	
	/**
	 * 商品详情
	 * @author dengwen 
	 * 2016-12-19下午4:32:39
	 */
	@RequestMapping("getProductInfo.do")
	public Result getProductInfo(Long id,Long uid) {
		Result rs = new Result();
		try {
			rs = service.getProductDetail(id,uid,rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("出现错误");
		}
		return rs;
	}
	
	/**
	 * 商品详情web
	 * @author dengwen
	 */
	@RequestMapping("getProductDetail.do")
	public ModelAndView getProductDetail(Long id,HttpServletRequest request){
		try {
			request.setAttribute("memo", productMapper.findById(id).getItemComment());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/jsp/detail.jsp") ;
	}
	
	/**
	 * 更新收藏
	 * @author dengwen 
	 */
	@RequestMapping("saveProductCollect.do")
	public Result saveProductCollect(Long uid,Long productId,Integer type){
		Result rs = new Result();
		try {
			rs = service.saveProductCollect(uid,productId,type,rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("出现错误");
		}
		return rs;
	}
	
	/**
	 * 获取商品收藏列表
	 * @author dengwen 
	 * 2016-12-21上午10:16:25
	 */
	@RequestMapping("getProductCollectList.do")
	public Result getProductCollectList(Long uid,Integer page){
		Result rs = new Result();
		try {
			rs = service.getProductCollectList(uid,page,rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("出现错误");
		}
		return rs;
	}
	
	/**
	 * 批量取消收藏
	 * @author dengwen 
	 * 2016-12-21上午9:42:43
	 */
	@RequestMapping("deleteProductCollect.do")
	public Result deleteProductCollect(Long[] ids) {
		Result rs = new Result();
		try {
			rs = service.deleteProductCollect(ids, rs);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(MSG.ERROR.getStatus());
			rs.setMsg("出现错误");
		}
		return rs;
	}
}
