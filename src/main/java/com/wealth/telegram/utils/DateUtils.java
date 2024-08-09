package com.wealth.telegram.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

@Slf4j
public class DateUtils {

    public static Date dateAdd(Date date, String unit, Integer time) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.add(getUnit(unit), time);
        return now.getTime();
    }

    public static Date nowAdd(String unit, Integer time) {
        Calendar now = Calendar.getInstance();
        now.add(getUnit(unit), time);
        return now.getTime();
    }

    public static long getTimeStepSizeInSecond(String unit, Integer duration) {
        long result;
        switch (String.valueOf(unit)) {
            case "MINUTE":
                result = TimeUnit.MINUTES.toSeconds(duration);
                break;
            case "HOUR":
                result = TimeUnit.HOURS.toSeconds(duration);
                break;
            case "DAY":
                result = TimeUnit.DAYS.toSeconds(duration);
                break;
            default:
                result = TimeUnit.SECONDS.toSeconds(duration);
        }
        return result;
    }

    private static final TimeZone TIME_ZONE_LOCAL = TimeZone.getTimeZone("Asia/Phnom_Penh");
    private static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    public static String formatToLocal(String toDate) {
        try{
            return formatTo(parseDate(toDate, DATETIME_FORMAT), "dd MMM yyyy | hh:mm a", TIME_ZONE_LOCAL);
        }catch (Exception ex){
            return null;
        }
    }

    public static String formatToLocal(Date toDate){
        try{
            return formatTo(toDate, "dd MMM yyyy | hh:mm a", TIME_ZONE_LOCAL);
        }catch (Exception ex){
            return null;
        }
    }

    public static Date parseDate(String date, String dateFormats){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormats);
            sdf.setTimeZone(TIME_ZONE_LOCAL);
            return sdf.parse(date);
        }catch (Exception e){
            log.error("cannot parse date : {}, error: {}", date, e.getMessage());
        }
        return null;
    }

    private static String formatTo(Date toDate, String format, TimeZone toTimeZone) throws Exception {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            sdf.setTimeZone(toTimeZone);
            return sdf.format(toDate);
        } catch (Exception e) {
            throw new Exception("Date cannot be format.");
        }
    }

    public static Date getDateWithoutTime(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static String reportDateFormat(String strDate){
        try {
            return new SimpleDateFormat("dd-MMM-yyyy")
                    .format(new SimpleDateFormat("yyyy-MM-dd").parse(strDate));
        }catch (Exception ex){
            return "";
        }
    }

    public static String formatEpochToLocal(String value) {
        Date date = new Date();
        date.setTime(Long.parseLong(value));
        return formatToLocal(date);
    }

    private static int getUnit(String unit) {
        int result;
        switch (String.valueOf(unit)) {
            case "MINUTE":
                result = Calendar.MINUTE;
                break;
            case "HOUR":
                result = Calendar.HOUR;
                break;
            case "DAY":
                result = Calendar.DAY_OF_MONTH;
                break;
            default:
                result = Calendar.SECOND;
        }
        return result;
    }

}
