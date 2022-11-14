package com.voika.myundefined.infrastructure.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static final String NOMAL_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String YMDHMS_STR = "yyyyMMddHHmmss";

    public static final String YM = "yyyy-MM";

    public static final String YMD = "yyyy-MM-dd";

    /**
     * 当前Date向前推几分钟
     */
    public static Date dateMinusMinutes(long min) {
        Date now = new Date();
        LocalDateTime nowLocalDateTime = cn.hutool.core.date.DateUtil.toLocalDateTime(now);
        LocalDateTime localDateTime1 = nowLocalDateTime.minusMinutes(min);
        Date date = Date.from( localDateTime1.atZone( ZoneId.systemDefault()).toInstant());
        return date;
    }

    public static Date str2date(String datestr) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = format.parse(datestr);
        return parse;
    }
    public static String date2str(Date date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String var = format.format(date);
        return var;
    }

    /**
     * 是否过期
     */
    public static boolean isExpri(Date expir) {
        if (null == expir) {
            return false;
        }
        Date now = new Date();
        return now.after(expir);
    }

    /**
     * 当前时间向推几小时
     * @param ihour 小时
     * @return String
     */
    public static LocalDateTime dateRollHour(LocalDateTime time,int ihour) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        //（2）获取传入时间的前几小时时间
        LocalDateTime localDateTime = time.minusHours(ihour);
        LocalDateTime alertTime =  time.minusMinutes(ihour);
        System.out.println(dateTimeFormatter.format(time));
        return localDateTime;
    }

    /**
     * 当前时间向推几天
     * @param iday 天
     * @return String
     */
    public static LocalDateTime dateRollDay(LocalDateTime time,int iday) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateTimeFormatter.format(time));
        LocalDateTime alertTime = time.minusDays(iday);
        System.out.println(dateTimeFormatter.format(alertTime));
        return alertTime;
    }

    /**
     * 当前时间向推几分钟
     * @param iminute 分钟
     * @return String
     */
    public static LocalDateTime dateRollMinute(LocalDateTime time,int iminute) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        System.out.println(dateTimeFormatter.format(time));
        LocalDateTime alertTime = time.minusMinutes(iminute);
//        System.out.println(dateTimeFormatter.format(alertTime));
        return alertTime;
    }

    /**
     * 获取上个月的时间
     *
     * @param date
     * @return
     */
    public static final String getLastMonth(Date date){
        SimpleDateFormat format = new SimpleDateFormat(YM);
        Calendar calendar = Calendar.getInstance();
        // 设置为当前时间
        calendar.setTime(date);
        // 设置为上一个月
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
        date = calendar.getTime();
        return format.format(date);
    }

    /**
     * 把当前时间向前或者后推几天
     *
     * @param date
     * @return
     */
    public static final String getNeedDayDate(String date, int day){
        Date needDate = cn.hutool.core.date.DateUtil.parse(date,YMD);
        Calendar calendar = Calendar.getInstance();
        // 设置为当前时间
        calendar.setTime(needDate);
        // 设置为上一个月
        calendar.add(Calendar.DAY_OF_MONTH, day);
        SimpleDateFormat format = new SimpleDateFormat(YMD);
        return format.format(calendar.getTime());
    }
    public static final Date getNeedDayDate(Date date, int day){
        String format1 = cn.hutool.core.date.DateUtil.format(date, YMD);
        date = cn.hutool.core.date.DateUtil.parse(format1, YMD);
        Calendar calendar = Calendar.getInstance();
        // 设置为当前时间
        calendar.setTime(date);
        // 设置为上一个月
        calendar.add(Calendar.DAY_OF_MONTH, day);
        SimpleDateFormat format = new SimpleDateFormat(YMD);
        return calendar.getTime();
    }

}
