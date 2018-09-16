package com.undecode.goduettocompanion.bakar.utils.date;

import android.text.TextUtils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Amr on 3/18/2017.
 */

public class DateFormatter {
    public static String getSlashedFormatDateTime(String dateTimeString) {
        DateTime dateTime = getLongDateTime(dateTimeString);
        return dateTime.dayOfMonth().getAsShortText() + " " +
                dateTime.monthOfYear().getAsShortText() + ", " +
                dateTime.year().getAsShortText() + " - " +
                dateTime.hourOfDay().getAsShortText() + ":" +
                dateTime.minuteOfHour().getAsShortText();
    }

    public static String getSlashedFormatDate(String dateTimeString) {
        DateTime dateTime = getLongDateTime(dateTimeString);
        return dateTime.dayOfMonth().getAsShortText() + " " +
                dateTime.monthOfYear().getAsShortText() + ", " +
                dateTime.year().getAsShortText();
    }

    public static String getSlashedFormatTime(String dateTimeString) {
        DateTime dateTime = getLongDateTime(dateTimeString);
        String time = dateTime.hourOfDay().getAsShortText() + ":" +
                dateTime.minuteOfHour().getAsShortText();
        return time;
    }

    public static String getDayOfWeek(String dateTimeSting) {
        DateTime dateTime = getLongDateTime(dateTimeSting);
        return dateTime.dayOfWeek().getAsShortText();
    }

    public static String getDayOfMonth(String dateTimeSting) {
        DateTime dateTime = getLongDateTime(dateTimeSting);
        return dateTime.dayOfMonth().getAsShortText();
    }

    public static DateTime getLongDateTime(String dateTimeString)
    {
        String Regexp = "\\/Date\\((\\-?\\d*?)([\\+\\-]\\d*)?\\)\\/";
        Pattern MyPattern = Pattern.compile(Regexp);
        Matcher MyMatcher = MyPattern.matcher(dateTimeString);
        MyMatcher.matches();
        Long time = Long.valueOf(MyMatcher.group(1));
        if (TextUtils.isEmpty(MyMatcher.group(2))) {
            return new DateTime(time, DateTimeZone.UTC);
        } else {
            Integer offsetHours = Integer.parseInt(MyMatcher.group(2).substring(0, 3));
            Integer offsetMinutes = Integer.parseInt(MyMatcher.group(2).substring(3, MyMatcher.group(2).length()));
            int offset = DateTimeZone.forOffsetHoursMinutes(offsetHours, offsetMinutes).getOffsetFromLocal(time);
            return new DateTime(time + offset).toDateTime(DateTimeZone.UTC);
        }
    }
}
