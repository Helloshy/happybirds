package com.kapphk.web.service.inter.impl.product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapphk.web.bean.product.BeanAppProductCollect;
import com.kapphk.web.bean.product.BeanAppProductInventory;
import com.kapphk.web.bean.product.BeanAppProductOrder;
import com.kapphk.web.dao.mapper.product.BeanAppProductCategoryMapper;
import com.kapphk.web.dao.mapper.product.BeanAppProductCollectMapper;
import com.kapphk.web.dao.mapper.product.BeanAppProductInventoryMapper;
import com.kapphk.web.dao.mapper.product.BeanAppProductMapper;
import com.kapphk.web.dao.mapper.product.BeanAppProductOrderMapper;
import com.kapphk.web.service.inter.imethod.product.InterfaceBeanAppProductService;
import com.kapphk.web.utils.Contents;
import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;
import com.kapphk.web.utils.ValidateUtils;

@Service
public class InterfaceBeanAppProductServiceImpl implements
		InterfaceBeanAppProductService {
	
	@Autowired
	private BeanAppProductMapper mapper;
	
	@Autowired
	private BeanAppProductCategoryMapper categoryMapper;
	
	@Autowired
	private BeanAppProductCollectMapper collectMapper;
	
	@Autowired
	private BeanAppProductInventoryMapper inventoryMapper;
	
	@Autowired
	private BeanAppProductOrderMapper productOrderMapper;
	
	/**
	 * 商品列表
	 */
	@Override
	public Result getProductList(Long categoryId, String itemName,
			Integer sales, Integer price, Integer page, Result rs)
			throws Exception {
		page = ValidateUtils.isBlank(page) ? 1 : page ;
		rs.put("info", mapper.getProductList(categoryId,itemName,sales,price,(page-1)*GridReq.ROWS,GridReq.ROWS));
		return rs;
	}

	/**
	 * 分类列表
	 */
	@Override
	public Result getProductCategoryList(Result rs) throws Exception {
		List<Map<String, Object>> list = categoryMapper.getList();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("itemName", "全部");
		map.put("categoryId", "");
		list.add(0, map);
		return rs.put("info", list);
	}

	/**
	 * 商品详情
	 */
	@Override
	public Result getProductDetail(Long id,Long uid,Result rs) throws Exception {
		if(!ValidateUtils.isBlank(id)){
			Map<String,Object> map = mapper.getProductDetail(id,uid);
			String str = (String) map.get("photos");
			map.put("photos", ValidateUtils.isBlank(str) ? new String[]{} : str.split(","));
			String color = (String) map.get("color");
			String size = (String) map.get("size"); 
			map.remove("size");
			map.remove("color");
			if(!ValidateUtils.isBlank(color) && !ValidateUtils.isBlank(size)){
				map.put("state", 3);
				map.put("sizeList", getCategory(size.split(","),color.split(","),id,"1","1"));
				map.put("colorList", getCategory(color.split(","),size.split(","),id,"2","1"));
			}else if(!ValidateUtils.isBlank(color)){
				map.put("state", 2);
				map.put("sizeList", new String[]{});
				map.put("colorList", new String[]{});
				map.put("colorList2", getCategory(color.split(","),null,id,"2",null));
			}else{
				map.put("state", 1);
				map.put("sizeList", new String[]{});
				map.put("colorList", new String[]{});
			}
			rs.put("info", map);
		}else{
			rs.setStatus(Contents.ERROR);
			rs.setMsg("参数有误");
		}
		return rs;
	}

	/**
	 * 更新商品收藏
	 */
	@Override
	public Result saveProductCollect(Long uid, Long productId, Integer type,
			Result rs) throws Exception {
		if(!ValidateUtils.isBlank(uid) && !ValidateUtils.isBlank(productId) && !ValidateUtils.isBlank(type)){
			BeanAppProductCollect collect = new BeanAppProductCollect();
			collect.setUid(uid);
			collect.setProductId(productId);
			if(type - 1 == 0){
				List<BeanAppProductCollect> list = collectMapper.findAll(collect);
				if(ValidateUtils.isEmptyForCollection(list)){
					collect.setCreateTime(new Date());
					collectMapper.insert(collect);
				}else{
					rs.setStatus(Contents.ERROR);
					rs.setMsg("您已收藏过");	
				}
			}else{
				collectMapper.deleteByEntity(collect);
			}
		}else{
			rs.setStatus(Contents.ERROR);
			rs.setMsg("参数有误");	
		}
		return rs;
	}
	
	private List<Map<String,Object>> getCategory(String[] str1, String[] str2,Long productId,String type,String state){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		BeanAppProductInventory inventory = new BeanAppProductInventory();
		BeanAppProductOrder productOrder = new BeanAppProductOrder();
		productOrder.setProductId(productId);
		if("1".equals(state)){
			for (String s1 : str1) {
				HashMap<String,Object> map = new HashMap<String,Object>();
				List<Map<String,Object>> list1 = new ArrayList<Map<String,Object>>();
				if(type.equals("1"))map.put("size", s1);
				else map.put("color", s1);
				for (String s2 : str2) {
					HashMap<String,Object> map2 = new HashMap<String,Object>();
					inventory = new BeanAppProductInventory();
					inventory.setProductId(productId);
					if(type.equals("1")){
						productOrder.setSize(s1);
						productOrder.setColor(s2);
						inventory.setSize(s1);
						inventory.setColor(s2);
						map2.put("color", s2);
					}else{
						productOrder.setSize(s2);
						productOrder.setColor(s1);
						inventory.setSize(s2);
						inventory.setColor(s1);
						map2.put("size", s2);
					}
					inventory = inventoryMapper.findEntityByCondition(inventory);
					map2.put("number", 0);
					map2.put("price", 0);
					if(!ValidateUtils.isBlank(inventory)){
						int count = inventory.getStockQty() - productOrderMapper.count();
						map2.put("number", count <= 0 ? 0 : count);
						map2.put("price", inventory.getPrice());
					}
					list1.add(map2);
				}
				map.put("value", list1);
				list.add(map);
			}
		}else{
			for (String s1 : str1) {
				HashMap<String,Object> map = new HashMap<String,Object>();
				inventory = new BeanAppProductInventory();
				inventory.setProductId(productId);
				inventory.setColor(s1);
				inventory = inventoryMapper.findEntityByCondition(inventory);
				productOrder.setColor(s1);
				map.put("number", 0);
				map.put("price", 0);
				map.put("color", s1);
				if(!ValidateUtils.isBlank(inventory)){
					int count = inventory.getStockQty() - productOrderMapper.count();
					map.put("number", count <= 0 ? 0 : count);
					map.put("price", inventory.getPrice());
				}
				list.add(map);
			}
		}
		return list;
	}

	/**
	 * 获取收藏列表
	 */
	@Override
	public Result getProductCollectList(Long uid, Integer page, Result rs)
			throws Exception {
		if(!ValidateUtils.isBlank(uid)){
			page = ValidateUtils.isBlank(page) ? 1 : page ;
			rs.put("info", collectMapper.getProductCollectList(uid,(page-1)*GridReq.ROWS,GridReq.ROWS));
		}else{
			rs.setStatus(Contents.ERROR);
			rs.setMsg("参数有误");	
		}
		return rs;
	}

	/**
	 * 批量取消收藏
	 */
	@Override
	public Result deleteProductCollect(Long[] ids, Result rs) throws Exception {
		if(!ValidateUtils.isempty(ids)){
			collectMapper.deletes(Arrays.asList(ids));
		}else{
			rs.setStatus(Contents.ERROR);
			rs.setMsg("参数有误");	
		}
		return rs;
	}

}
