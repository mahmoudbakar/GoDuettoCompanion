package com.undecode.goduettocompanion.bakar.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MyDate
{
    private SimpleDateFormat dt;
    private SimpleDateFormat sdt;

    public MyDate()
    {
        dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
        sdt = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    }

    public String getStringOfDateTime(Date date)
    {
        return dt.format(date);
    }

    public Date getDateTimeOfString(String date) throws ParseException
    {
        return dt.parse(date);
    }

    public Long getCurrentTimeMillis()
    {
        return Calendar.getInstance().getTimeInMillis();
    }

    public String getCurrentStringDateTime()
    {
        Date currentTime = Calendar.getInstance().getTime();
        return getStringOfDateTime(currentTime);
    }

    public String getStringOfDate(Date date)
    {
        return sdt.format(date);
    }

    public Date getDateOfString(String date) throws ParseException
    {
        return sdt.parse(date);
    }

    public String getCurrentStringDate()
    {
        Date currentTime = Calendar.getInstance().getTime();
        return getStringOfDate(currentTime);
    }
}
