package com.just.sapi.common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DatetimeUtil {
    public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM_00 = "yyyy-MM-dd HH:mm:00";

    public static String formattedDateTimeToString(LocalDateTime dateTime, String pattern) {
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDateTime formattedDateTime(LocalDateTime dateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(dateTime.format(formatter), formatter);
    }
}
