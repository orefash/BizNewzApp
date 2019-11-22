package com.texgraphicscompany.biznewz.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class GeneralUtils {

    public static String convertToNewFormat(String dateStr) throws ParseException {
        TimeZone utc = TimeZone.getTimeZone("UTC");
        SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        SimpleDateFormat destFormat = new SimpleDateFormat("MMMM dd, yyyy");
        sourceFormat.setTimeZone(utc);
        Date convertedDate = sourceFormat.parse(dateStr);
        return destFormat.format(convertedDate);
    }
    public static String convertLToNewFormat(String dateStr) throws ParseException {
        TimeZone utc = TimeZone.getTimeZone("UTC");
        SimpleDateFormat sourceFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
        SimpleDateFormat destFormat = new SimpleDateFormat("MMMM dd, yyyy");
        sourceFormat.setTimeZone(utc);
        Date convertedDate = sourceFormat.parse(dateStr);
        return destFormat.format(convertedDate);
    }

}
