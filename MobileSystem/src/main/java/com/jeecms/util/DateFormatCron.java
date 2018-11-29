package com.jeecms.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 将Date 转成  cron
 * @author jinlei
 *
 */
public class DateFormatCron {


    private static final String CRON_DATE_FORMAT = "0 mm HH dd MM ? yyyy";
 
    /***
     *
     * @param date 时间
     * @return cron类型的日期
     */
    public static String getCron(final Date  date){
        SimpleDateFormat sdf = new SimpleDateFormat(CRON_DATE_FORMAT);
        String formatTimeStr = "";
        if (date != null) {
            formatTimeStr = sdf.format(date);
        }
        return formatTimeStr;
    }
 
    /***
     *
     * @param cron Quartz cron的类型的日期
     * @return Date日期
     */
 
    public static Date getDate(final String cron) {
 	
        if(cron == null) {
            return null;
        }
 
        SimpleDateFormat sdf = new SimpleDateFormat(CRON_DATE_FORMAT);
 
		try {
			Date date = sdf.parse(cron);
			
		    return date;
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		return null;
    }
    
//    public static void main(String[] args) {
//    	System.out.println(getCron(new Date()));
//		System.out.println(getDate("0 34 14 25 07 ? 2018"));
//	}
}
