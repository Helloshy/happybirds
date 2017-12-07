package com.kapphk.web.utils;

import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 数据转换工具类
 * @author zoneyu 14-5-30
 */
@Component
public class DataUtils {

	/**
	 * 获取邀请码
	 */
	public static String getInvitationCode(){
		char C = 'A' ;
		C=(char)(C+(int)(Math.random()*26));
		char c = 'a' ;
		c=(char)(c+(int)(Math.random()*26));
		String str = String.valueOf(C)+String.valueOf(c)+getCode()+getCode()+getCode()+getCode() ;
		return str ;
	}
	
	public static void main(String[] args) {
		System.out.println(getInvitationCode());
	}
	
	public static char getCode(){
		char d = '0' ;
		d=(char)(d+(int)(Math.random()*10));
		return d ;
	}
	
	/**
	 * 获取四位随机整数
	 */
	public String getRadom(){
		double d = Math.random()*10000 ;
		String str = String.valueOf(d).substring(0, 4) ;
		return str ;
	}
	
	/**
	 * 获取六位随机数
	 */
	public static String getSixRadom(){
		String code = "" ;
		for(int i = 0 ; i < 6 ; i++){
			code += getCode()+"" ;
		}
		return code ;
	}
	
	/**
	 * 获取任意位数的随机数
	 */
	public static String getRadom(int x){
		String code = "" ;
		for(int i = 0 ; i < x ; i++){
			code += getCode()+"" ;
		}
		return code ;
	}
	
	/**
	 * 将数据拼装成easyui分页格式
	 * @author zoneyu 14-6-2
	 * @param count
	 * @param list
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String,Object> mergeData(int count,List list){
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("total", count) ;
		map.put("rows", list) ;
		return map ;
	}
	
	
	@SuppressWarnings("rawtypes")
	public static Result mergeData(int count, List list , Result rs){
		rs.put("total", count) ;
		rs.put("rows", list) ;
		return rs;
	}
	
	/**
	 * 将list中的值转换成String，值以逗号隔开
	 */
	@SuppressWarnings("rawtypes")
	public static String list2String(Collection coll){
		String str = "" ;
		if(!ValidateUtils.isEmptyForCollection(coll)){
			Iterator iter = coll.iterator() ;
			while(iter.hasNext()){
				str += iter.next() +",";
			}
			str = str.substring(0, str.length() - 1) ;
		}
		return str;
	}
	
	/**
	 * 将list中的值转换成String，值以逗号隔开
	 */
	@SuppressWarnings("rawtypes")
	public static String list2String1(Collection coll){
		String str = "" ;
		if(!ValidateUtils.isEmptyForCollection(coll)){
			Iterator iter = coll.iterator() ;
			while(iter.hasNext()){
				str += "'"+iter.next() +"',";
			}
			str = str.substring(0, str.length() - 1) ;
		}
		return str;
	}

	/**
	 * 生成订单号
	 */
	public static String getOrderNo() {
		return DateUtils.getLocalDate("yyyyMMddHHmmss")+ DataUtils.getRadom(12);
	}
	
	/**
	 * 生成订单号
	 */
	public static String getOrderNo8() {
		return DateUtils.getLocalDate("yyyyMMddHHmmss")+ DataUtils.getRadom(6);
	}

	/**
	 * string2List
	 */
	public static List<Long> string2List(String str) {
		List<Long> list = new ArrayList<Long>() ;
		if(!ValidateUtils.isBlank(str)){
			String[] strs = str.split(",") ;
			for(String s : strs){
				if(!ValidateUtils.isBlank(s)) list.add(Long.valueOf(s)) ;
			}
		}
		return list;
	}
	
	/**
	 * string2ListString
	 */
	public static List<String> string2ListString(String str) {
		List<String> list = new ArrayList<String>() ;
		if(!ValidateUtils.isBlank(str)){
			String[] strs = str.split(",") ;
			for(String s : strs){
				list.add(s) ;
			}
		}
		return list;
	}
	
	/**
	 * 将null转成""
	 */
	public static Object nvl(Object obj){
		if(null == obj || "".equals(obj) || "null".equals(obj)){
			return "" ;
		}else{
			return obj ;
		}
	}
	
	/**
	 * 删除字符串中的某个元素，多个是以逗号隔开
	 * @param allStr      源字符串
	 * @param str         需要删除的元素
	 * @return
	 */
	public static String replaceStr(String allStr,String str){
		if(null != allStr && !"".equals(allStr)){
			allStr = allStr.replace(str, "") ;
			allStr = allStr.startsWith(",") ? allStr.substring(1, allStr.length()) : allStr.replace(",,", ",") ;
			allStr = allStr.endsWith(",") ? allStr.substring(0, allStr.length() - 1) : allStr.replace(",,", ",") ;
		}
		return allStr ;
	}
	
	/**
	 * 判断是否为空，为空返回""
	 * @param obj
	 * @return
	 */
	public static Object isNull(Object obj){
		if(null == obj || "".equals(obj) || "null".equals(obj)){
			return "" ;
		}else{
			return obj ;
		}
	}

	/**
	 * page是否为空，为空返回1
	 */
	public static Integer pageIsNull(Integer page) {
		if(null != page){
			return page ;
		}else{
			return 1;
		}
	}
	
	/**
	 * 根据List<Map>内的字段值返回一个升序排序的Comparator
	 * 只支持数字类型进行排序
	 */
	public static Comparator<Map<String, Object>> listMapA(final String sortName){
		return new Comparator<Map<String,Object>>() {
			public int compare(Map<String, Object> m1,Map<String, Object> m2){
				Double v1 = Double.valueOf(m1.get(sortName).toString());
				Double v2 = Double.valueOf(m2.get(sortName).toString());
				return v1.compareTo(v2);
			}
		};
	}
	
	/**
	 * 根据List<Map>内的字段值返回一个降序排序的Comparator
	 * 只支持数字类型进行排序
	 */
	public static Comparator<Map<String, Object>> listMapD(final String sortName){
		return new Comparator<Map<String,Object>>() {
			public int compare(Map<String, Object> m1,Map<String, Object> m2){
				Double v1 = Double.valueOf(m1.get(sortName).toString());
				Double v2 = Double.valueOf(m2.get(sortName).toString());
				return v2.compareTo(v1);
			}
		};
	}
	
}
