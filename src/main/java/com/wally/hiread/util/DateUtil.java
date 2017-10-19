package com.wally.hiread.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by eric on 26/05/2017.
 */
public class DateUtil {
    public static final String DATE_FORMAT="yyyy-MM-dd";
    public static final String DATETIME_FORMAT="yyyy-MM-dd HH:mm:ss";
    public static final SimpleDateFormat df=new SimpleDateFormat(DATE_FORMAT);
    public static final SimpleDateFormat dtf=new SimpleDateFormat(DATETIME_FORMAT);


    public static String dateToString(Date date, String pattern) {
        SimpleDateFormat sf = new SimpleDateFormat(pattern);

        sf.format(date);

        return sf.format(date);
    }

    public static Date currentDate(){
        return new Date();
    }


    /**
     * 计算天使差
     * @param start
     * @param end
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static long calculateIntervalDay(String start, String end, String pattern) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat(pattern);

        Date startDate = sf.parse(start);
        Date endDate = sf.parse(end);

        long diff = endDate.getTime() - startDate.getTime();

        return TimeUnit.MILLISECONDS.toDays(diff);
    }

    public static boolean isNDaysBefore(Date d,int n) throws ParseException{
        Date now=new Date();
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(now);
        calendar.set(Calendar.DATE,calendar.get(Calendar.DATE)-n);
        Date date=calendar.getTime();

        String startTimeStr=df.format(date)+" 00:00:00";
        String endTimeStr=df.format(now)+" 23:59:59";
        Date startTime=dtf.parse(startTimeStr);
        Date endTime=dtf.parse(endTimeStr);
        if((d.after(startTime)||d.equals(startTime))&&
                (d.before(endTime)||d.equals(endTime))
                ){
            return true;
        }
        return false;
    }
}
