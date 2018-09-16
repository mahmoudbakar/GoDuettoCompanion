package com.undecode.goduettocompanion.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.undecode.goduettocompanion.BookingDetailsActivity;
import com.undecode.goduettocompanion.R;
import com.undecode.goduettocompanion.bakar.network.API;
import com.undecode.goduettocompanion.bakar.network.OnDataReady;
import com.undecode.goduettocompanion.bakar.utils.date.DateFormatter;
import com.undecode.goduettocompanion.bakar.utils.date.MyDate;
import com.undecode.goduettocompanion.models.Bookings;
import com.undecode.goduettocompanion.models.ProfileModel;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScheduleFragment extends Fragment
{

    Map<Long, String> eventday;
    MyDate myDate;
    CalendarView calendarView;
    List<EventDay> events;

    public static ScheduleFragment newInstance()
    {
        ScheduleFragment fragment = new ScheduleFragment();
        return fragment;
    }

    public static ScheduleFragment newInstance(Bundle bundle)
    {
        ScheduleFragment fragment = new ScheduleFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
    View view;
    @Override
    public void onResume()
    {
        super.onResume();
        calendarView = view.findViewById(R.id.calendarView);
        try
        {
            calendarView.setDate(Calendar.getInstance());
        } catch (OutOfDateRangeException e)
        {
            e.printStackTrace();
        }
        calendarView.setOnDayClickListener(new OnDayClickListener()
        {
            @Override
            public void onDayClick(com.applandeo.materialcalendarview.EventDay eventDay)
            {
                Calendar clickedDayCalendar = eventDay.getCalendar();
                if (eventday.get(clickedDayCalendar.getTimeInMillis()) != null)
                {
                    Intent intent = new Intent(getContext(), BookingDetailsActivity.class);
                    intent.putExtra("id", eventday.get(clickedDayCalendar.getTimeInMillis()));
                    startActivity(intent);
                }else
                {
                    Toast.makeText(getContext(), "There is no bookings in this day.", Toast.LENGTH_SHORT).show();
                }
            }

        });
        events.removeAll(events);
        API.getInstance().getBookings(getContext(), ProfileModel.getInstance().getId(),
                new OnDataReady()
                {
                    @Override
                    public void onArrayReady(List list)
                    {
                        if (list.size() > 0)
                        {
                            try
                            {
                                Bookings bookings = (Bookings) list.get(0);
                                for (Bookings booking:((List<Bookings>)list))
                                {

                                    Calendar cal = Calendar.getInstance();
                                    DateTime dateTime = DateFormatter.getLongDateTime(booking.getStartTime());
                                    cal.set(Calendar.YEAR, dateTime.getYear());
                                    cal.set(Calendar.MONTH, dateTime.getMonthOfYear() - 1);
                                    cal.set(Calendar.DAY_OF_MONTH, dateTime.getDayOfMonth()+1);
                                    cal.set(Calendar.HOUR_OF_DAY, dateTime.getHourOfDay());
                                    cal.set(Calendar.MINUTE, dateTime.getMinuteOfHour());
                                    EventDay temp = new EventDay(cal, R.drawable.ic_reserved);
                                    eventday.put(temp.getCalendar().getTimeInMillis(), booking.getID());
                                    events.add(temp);
                                }
                                calendarView.setEvents(events);
                                calendarView.setEvents(events);
                            }catch (ClassCastException e){}
                        }
                    }

                    @Override
                    public void onObjectReady(Object object)
                    {

                    }
                }, "Getting Data..");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_schedule, container, false);
        events = new ArrayList<>();
        myDate = new MyDate();
        eventday  = new HashMap<>();
//        API.getInstance().getBookings(getContext(), ProfileModel.getInstance().getId(),
//                new OnDataReady()
//                {
//                    @Override
//                    public void onArrayReady(List list)
//                    {
//                        if (list.size() > 0)
//                        {
//                            try
//                            {
//                                Bookings bookings = (Bookings) list.get(0);
//                                for (Bookings booking:((List<Bookings>)list))
//                                {
//
//                                    Calendar cal = Calendar.getInstance();
//                                    DateTime dateTime = DateFormatter.getLongDateTime(booking.getStartTime());
//                                    cal.set(Calendar.YEAR, dateTime.getYear());
//                                    cal.set(Calendar.MONTH, dateTime.getMonthOfYear() - 1);
//                                    cal.set(Calendar.DAY_OF_MONTH, dateTime.getDayOfMonth()+1);
//                                    cal.set(Calendar.HOUR_OF_DAY, dateTime.getHourOfDay());
//                                    cal.set(Calendar.MINUTE, dateTime.getMinuteOfHour());
//                                    EventDay temp = new EventDay(cal, R.drawable.ic_reserved);
//                                    eventday.put(temp.getCalendar().getTimeInMillis(), booking.getID());
//                                    events.add(temp);
//                                }
//                                calendarView.setEvents(events);
//                            }catch (ClassCastException e){}
//                        }
//                    }
//
//                    @Override
//                    public void onObjectReady(Object object)
//                    {
//
//                    }
//                }, "Getting Data..");
        return view;
    }
}