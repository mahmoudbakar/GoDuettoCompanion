package com.undecode.goduettocompanion;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.gson.Gson;
import com.undecode.goduettocompanion.bakar.network.OnDataReady;
import com.undecode.goduettocompanion.models.ProfileModel;
import com.undecode.goduettocompanion.models.dataholder.BookingResponse;
import com.undecode.goduettocompanion.models.dataholder.CreateBookingHolder;
import com.undecode.goduettocompanion.bakar.network.API;
import com.undecode.goduettocompanion.models.searchcompanion.CompanionSearch;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class CompanionBookingActivity extends AppCompatActivity implements OnDataReady
{
    @BindView(R.id.image)
    CircleImageView image;
    @BindView(R.id.txtName)
    TextView txtName;
    @BindView(R.id.txtPrice)
    TextView txtPrice;
    @BindView(R.id.txtDate)
    TextView txtDate;
    @BindView(R.id.txtStartTime)
    TextView txtStartTime;
    @BindView(R.id.txtEndTime)
    TextView txtEndTime;
    @BindView(R.id.txtAddress)
    TextView txtAddress;
    @BindView(R.id.textView5)
    TextView txtBookOld;
    @BindView(R.id.txtBtnBook)
    TextView txtBtnBook;
    @BindView(R.id.imagePickDate)
    ImageView imagePickDate;
    @BindView(R.id.imagePickStartTime)
    ImageView imagePickStartTime;
    @BindView(R.id.imageEndTime)
    ImageView imageEndTime;
    @BindView(R.id.imagePickLocation)
    ImageView imagePickLocation;
    @BindView(R.id.imagePickDrop)
    ImageView imagePickDrop;
    @BindView(R.id.txtDropAddress)
    TextView txtDropAddress;
    @BindView(R.id.pickStart)
    ConstraintLayout pickStart;
    @BindView(R.id.pickEnd)
    ConstraintLayout pickEnd;

    String date_time = "";
    int mYear;
    int mMonth;
    int mDay;

    int sHour;
    int sMinute;

    int eHour;
    int eMinute;
    boolean st, ed, pl, dl, dt;
    CreateBookingHolder bookingHolder;
    CompanionSearch companionSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companion_booking);
        Gson gson = new Gson();
        companionSearch = gson.fromJson(getIntent().getStringExtra("companion"), CompanionSearch.class);
        ButterKnife.bind(this);
        Glide.with(image).load(companionSearch.getCPicture()).into(image);
        txtName.setText(companionSearch.getCEmail());
        try
        {
            txtPrice.setText(companionSearch.getWorkingHours().get(0).getHourlyWages()+" $");
        }catch (IndexOutOfBoundsException e)
        {
            txtPrice.setText("N/A");
        }
        pickStart.setVisibility(View.GONE);
        pickEnd.setVisibility(View.GONE);
        txtBookOld.setVisibility(View.INVISIBLE);
        bookingHolder = new CreateBookingHolder();
        bookingHolder.setCompanionId(companionSearch.getID());
        bookingHolder.setTravellerId(ProfileModel.getInstance().getId());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("New Booking");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    Calendar cal = Calendar.getInstance();

    @OnClick(R.id.imagePickDate)
    public void onImagePickDateClicked()
    {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener()
                {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
                    {
                        String month = String.valueOf(monthOfYear + 1), day = String.valueOf(dayOfMonth);
                        if (String.valueOf(monthOfYear).length() < 2) {
                            month = "0" + String.valueOf(monthOfYear + 1);
                        }
                        if (String.valueOf(dayOfMonth).length() < 2) {
                            day = "0" + String.valueOf(dayOfMonth);
                        }

                        cal.set(Calendar.YEAR, year);
                        cal.set(Calendar.MONTH, monthOfYear);
                        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        pickStart.setVisibility(View.VISIBLE);
                        pickEnd.setVisibility(View.VISIBLE);
                        dt = true;
                        date_time = day + "-" + month + "-" + year;
                        txtDate.setText(date_time);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    @OnClick(R.id.imagePickStartTime)
    public void onImagePickStartTimeClicked() {
        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String hh = String.valueOf(hourOfDay), mm = String.valueOf(minute);
                        if (String.valueOf(hourOfDay).length() < 2) {
                            hh = "0" + String.valueOf(hourOfDay);
                        }
                        if (String.valueOf(minute).length() < 2) {
                            mm = "0" + String.valueOf(minute);
                        }
                        sHour = hourOfDay;
                        sMinute = minute;

                        txtStartTime.setText(hh + ":" + mm);
                        cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        cal.set(Calendar.MINUTE, minute);
                        Date startDate = cal.getTime();
                        bookingHolder.setStartTime(getString(R.string.datePrefix) + String.valueOf(startDate.getTime()) + getString(R.string.datePostfix));
                        st = true;
                    }
                }, sHour, sMinute, true);
        timePickerDialog.show();
    }

    @OnClick(R.id.imageEndTime)
    public void onImageEndTimeClicked() {
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        eHour = c.get(Calendar.HOUR_OF_DAY);
        eMinute = c.get(Calendar.MINUTE);
        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String hh = String.valueOf(hourOfDay), mm = String.valueOf(minute);
                        if (String.valueOf(hourOfDay).length() < 2) {
                            hh = "0" + String.valueOf(hourOfDay);
                        }
                        if (String.valueOf(minute).length() < 2) {
                            mm = "0" + String.valueOf(minute);
                        }

                        eHour = hourOfDay;
                        eMinute = minute;

                        txtEndTime.setText(hh + ":" + mm);
                        cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        cal.set(Calendar.MINUTE, minute);
                        Date endDate = cal.getTime();
                        bookingHolder.setEndTime(getString(R.string.datePrefix) + String.valueOf(endDate.getTime()) + getString(R.string.datePostfix));
                        ed = true;
                    }
                }, eHour, eMinute, true);
        timePickerDialog.show();
    }


    int PICK_UP = 1;
    int DROP_OFF = 2;

    @OnClick(R.id.imagePickLocation)
    public void onImagePickLocationClicked() {
        try {
            PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
            startActivityForResult(builder.build(this), PICK_UP);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.imagePickDrop)
    public void onImageDropLocationClicked()
    {
        try
        {
            PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
            startActivityForResult(builder.build(this), DROP_OFF);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == PICK_UP)
        {
            if (resultCode == RESULT_OK)
            {
                Place place = PlacePicker.getPlace(data, this);
                bookingHolder.setPickUpLat(place.getLatLng().latitude);
                bookingHolder.setPickupLong(place.getLatLng().longitude);
                bookingHolder.setPickupAddress(place.getName().toString());
                txtAddress.setText(place.getName());
                pl = true;
            }
        } else if (requestCode == DROP_OFF)
        {
            if (resultCode == RESULT_OK)
            {
                Place place = PlacePicker.getPlace(data, this);
                bookingHolder.setDropOfLat(place.getLatLng().latitude);
                bookingHolder.setDropOfLong(place.getLatLng().longitude);
                bookingHolder.setDropOfAdd(place.getName().toString());
                txtDropAddress.setText(place.getName());
                dl = true;
            }
        }
    }

    @OnClick(R.id.txtBtnBook)
    public void onViewClicked()
    {
        if (dl && pl && st && ed && dt)
        {
            API.getInstance().createBooking(this, bookingHolder, this, "Booking in progress");
        }else
        {
            Toast.makeText(this, "Some Data is Missed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onArrayReady(List list)
    {

    }

    @Override
    public void onObjectReady(Object object)
    {
        try
        {
            BookingResponse response = (BookingResponse) object;
            if (response.getCreateBookingResult().length() > 1)
            {
                Toast.makeText(this, "Booked Successfully.", Toast.LENGTH_SHORT).show();
                finish();
            }
        }catch (ClassCastException e){}
    }
}
