package com.koreatech.naeilro.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
    public static String getTimestampToDate(String timestampStr) {
        long timestamp = Long.parseLong(timestampStr);
        Date date = new java.util.Date(timestamp * 1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("MM/dd");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+9"));
        String formattedDate = sdf.format(date);
        return formattedDate;
    }

    public static String getTodayDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar time = Calendar.getInstance();
        return format.format(time.getTime());
    }

    public static String getDateFormatChange(String changeText, String beforeFormat, String changeFormat) {
        try {
            SimpleDateFormat dt = new SimpleDateFormat(beforeFormat);
            Date date = dt.parse(changeText);
            SimpleDateFormat dt1 = new SimpleDateFormat(changeFormat);
            return dt1.format(date);
        } catch (ParseException e) {
            return "";
        }
    }
}
