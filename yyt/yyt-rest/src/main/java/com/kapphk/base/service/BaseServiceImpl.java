package com.kapphk.base.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kapphk.base.mapper.BaseMapper;
import com.kapphk.web.utils.GetLatAndLngByBaidu;
import com.kapphk.web.utils.ValidateUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * service接口实现类 用于继承
 * @author Folo 2014年10月15日
 */
@SuppressWarnings("all")
public abstract class BaseServiceImpl<M, T>{
	
	static final int OK = 10000;
	static final int ERROR = 20001;
	static final int NOT_FOUND = 20003;
	static final int PARAMS_ERROR= 21001;
	static final int TOKEN_FAILURE = 10000;
	static final int VALUE_EMPTY = 20006;
	static final int EXIST = 20007;
	static final int NOT_EXIST = 20009;
	static final int STOP = 20008;
	static final int CUS_ERROR = 20005;
	
	//默认页跟行数
	protected static final Integer PAGE = 1 ;
	protected static final Integer ROWS = 10 ;
	
	/**
	 * 校验参数是为空
	 */
	public Map<String,Object> paramIsBlank(Object... args){
		Map<String,Object> map = new HashMap<String,Object>() ;
		if(args != null && args.length > 0){
			for(Object o : args){
				o.getClass().getName() ;
			}
		}else{
			map.put("rtCode", 10000) ;
			map.put("msg", "") ;
		}
		return map ;
	}
	
	/**
	 * 距离排序
	 */
	public void sort(List<Map<String,Object>> list){//小到大的排序
		for (int i = 0; i < list.size() - 1; i++) {  
            for (int j = 1; j < list.size() - i; j++) {  
            	Map<String,Object> map;  
            	Map<String,Object> map2 = list.get(j - 1) ;
            	Map<String,Object> map3 = list.get(j) ;
            	Double db2 = Double.valueOf(String.valueOf(map2.get("distance"))) ;
            	Double db3 = Double.valueOf(String.valueOf(map3.get("distance"))) ;
                if ((db2).compareTo(db3) > 0) {//比较两个整数的大小  
                    map = list.get(j - 1);
                    list.set((j - 1), list.get(j));
                    list.set(j, map);
                }  
            }  
        } 
	}
	
	/**
	 * 获得经纬度
	 */
	@SuppressWarnings("unchecked")
	public Map<Object,Double> getLatAndLng(String address) throws IOException{
		Map<Object,Double> dataMap = new HashMap<Object, Double>() ;
		if(!ValidateUtils.isBlank(address)){
			Map<String, Object> map = GetLatAndLngByBaidu.getCoordinate(address) ;
			if(!ValidateUtils.isEmptyForMap(map)){
				Map<Object, Object> map1 = (Map<Object, Object>)map.get("location") ;
				if(!ValidateUtils.isEmptyForMap(map1)){
					Object olat = map1.get("lat") ;
					Object olng = map1.get("lng") ;
					Double lat = Double.valueOf(String.valueOf(olat)) ;
					Double lng = Double.valueOf(String.valueOf(olng)) ;
					dataMap.put("lat", lat) ;
					dataMap.put("lng", lng) ;
				}
			}
		}else{
			dataMap.put("lat", 0d) ;
			dataMap.put("lng", 0d) ;
		}
		return dataMap ;
	}
	
	/**
	 * Mapper用于继承的类对象
	 */
	private BaseMapper<M, T> bm;
	
	public void setMapper(BaseMapper<M, T> mapper){
		this.bm = mapper;
	}
	
	public BaseMapper<M, T> getMapper(){
		if(null == bm) init();
		return bm;
	}
	
	/**
	 * 初始化
	 * @author Folo 2014年10月15日
	 */
	public abstract void init();
	
	/**
	 * 分页查找数据
	 * @author Folo 2014年10月15日
	 */
	public List<M> findByPage(M m, int offset, int pageSize){
		return getMapper().findByPage(m, offset, pageSize);
	}
	
	/**
	 * 分页查找数据
	 * @author EXIA
	 */
	public PageInfo<M> findByPage(M m, int offset, int pageSize,String sort){
		PageHelper.startPage(offset, pageSize);
		if(!StringUtils.isBlank(sort)){
			PageHelper.orderBy(sort);
		}
		List<M> list = getMapper().findList(m);
		PageInfo<M> pageInfo = new PageInfo<M>(list);
		return pageInfo;
	}
	
	/**
	 * 分页查找总条数
	 * @author Folo 2014年10月15日
	 */
	public int findByPageCount(M m){
		return getMapper().findByPageCount(m);
	}
	
	/**
	 * 根据id查询数据
	 * @author Folo 2014年10月15日
	 */
	public M findById(T id){
		return getMapper().findById(id);
	}
	
	/**
	 * 加载id为传入id的数据
	 * @author Folo 2014年10月15日
	 */
	public List<M> load(List<T> ids){
		return getMapper().load(ids);
	}
	
	/**
	 * 查询所有数据
	 * @author Folo 2014年10月15日
	 */
	public List<M> all(){
		return getMapper().all();
	}
	
	/**
	 * 查询总条数
	 * @author Folo 2014年10月15日
	 */
	public int count(){
		return getMapper().count();
	}
	
	/**
	 * 更新数据
	 * @author Folo 2014年10月15日
	 */
	public int update(M m){
		return getMapper().update(m);
	}
	
	/**
	 * 添加数据
	 * @author Folo 2014年10月15日
	 */
	public int insert(M m){
		return getMapper().insert(m);
	}
	
	/**
	 * 添加多条数据（直接添加到数据库 不做非空判断）
	 * @author Folo 2014年10月15日
	 */
	public int inserts(List<M> list){
		return getMapper().inserts(list);
	}
	
	/**
	 * 删除单条数据
	 * @author Folo 2014年10月15日
	 */
	public int delete(T id){
		return getMapper().delete(id);
	}
	
	/**
	 * 删除多条数据
	 * @author Folo 2014年10月15日
	 */
	public int deletes(List<T> ids){
		return getMapper().deletes(ids);
	}
	
	/**
	 * 校验参数是否为空，不为空为true,反之为false
	 */
	public boolean validateParam(Object... args){
		boolean b = true ;
		if(!ValidateUtils.isempty(args)){
			for(Object obj : args){
				if(ValidateUtils.isBlank(obj)){
					b = false ;
					break ;
				}
			}
		}
		return b ;
	}
	
}
