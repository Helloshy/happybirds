package com.airparking.apms.comm;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * 时间格式转换器
 * 
 * @author luojihao
 * 
 */
public class DateFormats {

  private static final ThreadLocal<DateFormat> datetimeFormat = new ThreadLocal<DateFormat>() {
    @Override
    protected DateFormat initialValue() {
      return new SimpleDateFormat("yyyyMMddHHmmss");
    }
  };

  private static final ThreadLocal<DateFormat> dateFormat = new ThreadLocal<DateFormat>() {
    @Override
    protected DateFormat initialValue() {
      return new SimpleDateFormat("yyyyMMdd");
    }
  };

  private static final ThreadLocal<DateFormat> dateFormat1 = new ThreadLocal<DateFormat>() {
    @Override
    protected DateFormat initialValue() {
      return new SimpleDateFormat("yyyy-MM-dd");
    }
  };

  private static final ThreadLocal<DateFormat> timeFormat = new ThreadLocal<DateFormat>() {
    @Override
    protected DateFormat initialValue() {
      return new SimpleDateFormat("HHmmss");
    }
  };

  private static final ThreadLocal<DateFormat> displayFormat = new ThreadLocal<DateFormat>() {
    @Override
    protected DateFormat initialValue() {
      return new SimpleDateFormat("yyyy-MM-dd HH:mm");
    }
  };

  private static final ThreadLocal<DateFormat> dateTimeDispalyFormat = new ThreadLocal<DateFormat>() {
    @Override
    protected DateFormat initialValue() {
      return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
  };

  public static String formatDateTimeDispaly(Date date) {
    return dateTimeDispalyFormat.get().format(date);
  }

  public static String formatDisplay(Date date) {
    return displayFormat.get().format(date);
  }

  public static String formatDatetime(Date date) {
    return datetimeFormat.get().format(date);
  }

  public static Date parseDatetime(String dateString) {
    try {
      return datetimeFormat.get().parse(dateString);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }

  public static Date parseDateTimeDispalyFormat(String dateString) {
    try {
      return dateTimeDispalyFormat.get().parse(dateString);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }

  public static Date parseDisplayDatetime(String dateString) {
    try {
      return displayFormat.get().parse(dateString);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }

  public static Date parseDate1(String dateString) {
    try {
      return dateFormat1.get().parse(dateString);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }

  public static String formatDate(Date date) {
    return dateFormat.get().format(date);
  }

  public static String formatDate1(Date date) {
    return dateFormat1.get().format(date);
  }

  public static String formatTime(Date date) {
    return timeFormat.get().format(date);
  }

  public static Date parseDate(String dateString) {
    try {
      return dateFormat.get().parse(dateString);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }

  public static Date getSysMaxDatetime() {
    return parseDatetime("21990101000000");
  }

  public static final int SECOND = 0;
  public static final int MINUTES = 1;
  public static final int HOUR = 2;
  public static final int DAY = 3;

  public static long diffDate(Date date1, Date date2, int field) {
    long diff = date1.getTime() - date2.getTime();
    switch (field) {
    case SECOND:
      return diff / 1000;
    case MINUTES:
      return diff / (1000 * 60);
    case HOUR:
      return diff / (1000 * 60 * 60);
    case DAY:
      return diff / (1000 * 60 * 60 * 24);
    default:
      return diff;
    }
  }
  
 
  public static String firstMonthDay(Date d){
	 int day = d.getDate();			
	 d = DateUtils.setDays(d, 1);
	return dateFormat1.get().format(d);
  }
  
  public static String firstYearDay(Date d){
	 int day = d.getDate();			
	 d = DateUtils.setDays(d, 1);
	 Date d1 = DateUtils.setMonths(d, 0);
	return dateFormat1.get().format(d1);
  }
  
  public static void main(String[] args) throws ParseException {
	  //String date = "2016-12-08";
	  Date parse = dateFormat1.get().parse("2017-11-22");
	  String firstYearDay = firstMonthDay(parse);
	  System.out.println(firstYearDay);
  }
}
