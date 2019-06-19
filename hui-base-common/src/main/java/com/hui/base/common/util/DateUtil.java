package com.hui.base.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <b><code>DateUtil</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2019/4/22 14:11.
 *
 * @author Hu-Weihui
 * @since hui-base-java ${PROJECT_VERSION}
 */
@Scope("singleton")
@Slf4j
public class DateUtil {
    private static final String DEFAULT_DATE_FORMAT = "yyyyMMddHHmmss";
    private static final String DATE_FORMAT_2 = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_FORMAT_3 = "yyyyMMdd";
    private static final String DATE_FORMAT_4 = "yyyyMMddHHmm";



    private static String yesterday;
    private static String tomorrow;
    private static String dealDate;
    private static int dealDateHour;
    private static String twoHourAgoDate;
    private static int twoHourAgoHour;
    private static String oneHourAgoDate;
    private static int oneHourAgoHour;
    private static String twoHourAfterDate;
    private static int twoHourAfterHour;
    private static String lastMonth;

    private static ThreadLocal<Map<String, SimpleDateFormat>> threadLocalMap = new ThreadLocal<Map<String, SimpleDateFormat>>() {
        @Override
        protected Map<String, SimpleDateFormat> initialValue() {
            Map<String, SimpleDateFormat> dateFormatMap = new HashMap<>();
            dateFormatMap.put(DEFAULT_DATE_FORMAT, new SimpleDateFormat(DEFAULT_DATE_FORMAT));
            dateFormatMap.put(DATE_FORMAT_2, new SimpleDateFormat(DATE_FORMAT_2));
            dateFormatMap.put(DATE_FORMAT_3, new SimpleDateFormat(DATE_FORMAT_3));
            dateFormatMap.put(DATE_FORMAT_4, new SimpleDateFormat(DATE_FORMAT_4));
            return dateFormatMap;
        }
    };

    /**
     * str转date（thread safe）
     *
     * @param dateStr
     * @return
     * @author Hu-Weihui
     */
    public static Date parse(String dateStr) {
        return parse(dateStr, DEFAULT_DATE_FORMAT);
    }

    /**
     * str转date 可指定dateformat（thread safe）
     *
     * @param dateStr
     * @return
     * @author Hu-Weihui
     */
    public static Date parse(String dateStr, String format) {
        try {
            return threadLocalMap.get().get(format).parse(dateStr);
        } catch (ParseException e) {
            log.error("parse date {} to str fail , {}", dateStr, e.getMessage());
            return null;
        }
    }

    /**
     * date转str（thread safe）
     *
     * @param date
     * @return
     * @author Hu-Weihui
     */
    public static String format(Date date) {
        return format(date,DEFAULT_DATE_FORMAT);
    }

    /**
     * date转str（thread safe）
     *
     * @param date
     * @return
     * @author Hu-Weihui
     */
    public static String format(Date date, String format) {
        try {
            return threadLocalMap.get().get(format).format(date);
        } catch (Exception e) {
            log.error("format date {} to str fail , {}", date, e.getMessage());
        }
        return null;
    }

    /**
     * 时间相减-差值（小时）
     *
     * @param star
     * @param end
     * @return
     * @author Hu-Weihui
     */
    public static BigDecimal subDate(Date star, Date end) {
        long hourTime = 60 * 60 * 1000L;
        long starTime = star.getTime();
        long endTime = end.getTime();
        BigDecimal starDecimal = new BigDecimal(starTime);
        BigDecimal endDecimal = new BigDecimal(endTime);
        BigDecimal subtract = endDecimal.subtract(starDecimal);
        BigDecimal divide = subtract.divide(new BigDecimal(hourTime), 2, BigDecimal.ROUND_HALF_UP);
        return divide;
    }

    /**
     * 获取时间的小时val（int）
     *
     * @param date
     * @return
     * @author Hu-Weihui
     */
    public static int getHourOfInt(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return hour;
    }

}
