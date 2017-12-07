package com.kapphk.web.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * @author zoneyu tang 14-5-29
 */
public class DateUtils {

	/**
	 * 私有构造函数
	 */
	private DateUtils(){} ;
	
	/**
	 * 获取当前系统时间
	 * @author zoneyu tang 14-5-29
	 * @param pattern   日期格式
	 */
	public static String getLocalDate(String pattern){
		SimpleDateFormat sf = new SimpleDateFormat(pattern) ;
		return sf.format(new Date()) ;
	}
	
	/**
	 * 将日期格式转换为字符串
	 * @param pattern
	 * @param date
	 * @return
	 */
	public static String formartDate(String pattern,Date date){
		SimpleDateFormat sf = new SimpleDateFormat(pattern) ;
		String dateStr = sf.format(date) ;
		return dateStr ;
	}
	
	/**
	 * 将字符串转换为日期格式
	 * @param pattern
	 * @param date
	 * @return
	 * @throws Exception 
	 */
	public static Date parseDate(String pattern,String date) throws Exception{
		SimpleDateFormat sf = new SimpleDateFormat(pattern) ;
		Date dateTime = sf.parse(date) ;
		return dateTime ;
	}
	
	/**
	 * 获取7位随机数
	 * @return
	 */
	public static String getRadom(){
		long i = System.nanoTime() ;
		String str = String.valueOf(i) ;
		str = str.substring(7, 11) ;
		return str ;
	}
	
	/**
	 * 获取当前系统时间跟date之前的间隔时间（单位为秒）
	 * @param date   日期时间
	 * @throws Exception 
	 */
	public static long getTime(String date) throws Exception{
		Date nativeDate = parseDate("yyyy-MM-dd HH:mm:ss", date) ;
		//当前传入的时间
		long start = nativeDate.getTime() ;
		long local = new Date().getTime() ;
		long i = ((local - start)/1000);
		return i ;
	}
	
	/**
	 * 获取当前时间格式为(yyyy-MM-dd HH:mm:ss)
	 * @return
	 */
	public static String getLocalDate(){
		return formartDate("yyyy-MM-dd HH:mm:ss", new Date()) ;
	}
	
	/**
	 * 获取当前时间格式为(yyyy-MM-dd)
	 * @return
	 */
	public static String getLocalYmdDate(){
		return formartDate("yyyy-MM-dd", new Date()) ;
	}
	
	/**
	 * 计算从当前日期开始，time月的日期
	 * @param time   月
	 * @return  日期，格式为yyyy-MM-dd
	 */
	public static String getRadomMonth(int time){
		Calendar c = Calendar.getInstance() ;
		c.add(Calendar.MONTH, time) ;
		Date d = c.getTime() ;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd") ;
		String date = sf.format(d) ;
		return date ;
	}
	
	/**
	 * 计算从当前日期开始，time天的日期
	 * @param time  天
	 * @return
	 */
	public static String getRadomDate(int time){
		Calendar c = Calendar.getInstance() ;
		c.add(Calendar.DATE, time) ;
		Date d = c.getTime() ;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd") ;
		String date = sf.format(d) ;
		return date ;
	}
	
	/**
	 * 计算从当前日期开始，time天的日期（带时分秒）
	 * @param time   天
	 * @return
	 */
	public static String getRadomDateBySecond(int time){
		Calendar c = Calendar.getInstance() ;
		c.add(Calendar.DATE, time) ;
		Date d = c.getTime() ;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
		String date = sf.format(d) ;
		return date ;
	}
	
	/**
	 * 获取前一天
	 * @param time 
	 * @return
	 */
	public static String getYestoday(){
		Calendar c = Calendar.getInstance() ;
		c.add(Calendar.DATE, -1) ;
		Date d = c.getTime() ;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd") ;
		String date = sf.format(d) ;
		return date ;
	}
	
	public static String getDate(String start,String end) throws Exception{
		Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = df.parse(start);
        startCalendar.setTime(startDate);
        Date endDate = df.parse(end);
        endCalendar.setTime(endDate);
        String date = "" ;
        while(true){
            startCalendar.add(Calendar.DAY_OF_MONTH, 1);
            if(startCalendar.getTimeInMillis() < endCalendar.getTimeInMillis()){//TODO 转数组或是集合，楼主看着写吧
            	date += df.format(startCalendar.getTime()) + ",";
            }else{
                break;
            }
        }
        return date ;
	}
	
	/**
	 * 加减日期
	 * @param pattern 日期格式
	 * @param date 字符串日期
	 * @param type 操作类型（1、加；2、减）
	 * @return
	 * @throws Exception
	 */
	public static String addOrSubDate(String pattern,String date,int type) throws Exception{
		
		   Date datetime = parseDate(pattern,date);
		
		   Calendar calendar=Calendar.getInstance();  
		   calendar.setTime(datetime);
		   if(type == 1){
			   calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+1);//让日期加1  
		   }
		   
		   if(type == 2){
			   calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)-1);//让日期减1  
		   }
		   
		   
		   System.out.println(calendar.getTime());//加1之后的日期Top 
		   
		   String time =  formartDate(pattern,calendar.getTime());
		   
		   return time;
	}
	
	
	
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 6);
		System.out.println(c.getTime());
		
	}

	/**
	 * 获取今日，格式为日期格式
	 */
	public static Date getDay() throws Exception {
		Calendar c=Calendar.getInstance();
		int y = c.get(Calendar.YEAR);
		int m = c.get(Calendar.MONTH);
		int d = c.get(Calendar.DATE);
		String date = y + "-" + m + "-" + d ;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd") ;
		return sf.parse(date) ;
	}

	/**
	 * 获取今日，格式为日期格式
	 */
	public static Date getDay(String month, String regex) throws Exception {
		SimpleDateFormat sf = new SimpleDateFormat(regex) ;
		return sf.parse(month) ;
	}
	
	/**
	 * 设置访问时效
	 */
	public static boolean isOver(){
		boolean b = true ;
		String lastTime = "2017-01-01 00:00:00" ;
		long overTime = 0 ;
		try {
			overTime = DateUtils.parseDate("yyyy-MM-dd HH:mm:ss", lastTime).getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		long nativeTime = new Date().getTime() ;
		if(nativeTime > overTime){
			b = false ;
		}
		return b ;
	}
	
}
