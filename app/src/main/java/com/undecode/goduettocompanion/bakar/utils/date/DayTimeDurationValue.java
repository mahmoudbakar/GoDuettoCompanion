package com.undecode.goduettocompanion.bakar.utils.date;

import android.util.Log;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayTimeDurationValue {
    public static final String format = "P0DT%dH0M0S";

    public static String getFormattedTime(String hour) {
        String x = String.format(Locale.ENGLISH, format, Integer.parseInt(hour.split(":")[0]));
        return x;
    }

    public static int getHourIndex(String time) {
        String regex = "T(.*\\d)H";
        int hour = 0;
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(time);

        while (matcher.find()) {
            Log.d("REGEX", "Full match: " + matcher.group(0));
            hour = Integer.parseInt(matcher.group(1));
            for (int i = 1; i <= matcher.groupCount(); i++) {
                Log.d("REGEX", "Group " + i + ": " + matcher.group(i));
            }
        }
        return hour;
    }
}