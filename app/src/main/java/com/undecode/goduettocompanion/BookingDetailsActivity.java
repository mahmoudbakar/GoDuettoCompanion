package com.undecode.goduettocompanion;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;
import com.undecode.goduettocompanion.bakar.network.API;
import com.undecode.goduettocompanion.bakar.network.OnDataReady;
import com.undecode.goduettocompanion.bakar.utils.date.DateFormatter;
import com.undecode.goduettocompanion.bakar.utils.date.MyDate;
import com.undecode.goduettocompanion.models.Bookings;
import com.undecode.goduettocompanion.models.CompanionProfile;
import com.willy.ratingbar.BaseRatingBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class BookingDetailsActivity extends AppCompatActivity implements OnMapReadyCallback, SpeedDialView.OnActionSelectedListener
{
    @BindView(R.id.image)
    CircleImageView image;
    @BindView(R.id.rate)
    BaseRatingBar rate;
    @BindView(R.id.txtName)
    TextView txtName;
    @BindView(R.id.txtCountry)
    TextView txtCountry;
    @BindView(R.id.txtCity)
    TextView txtCity;
    @BindView(R.id.txtAddress)
    TextView txtAddress;
    @BindView(R.id.txtDate)
    TextView txtDate;
    @BindView(R.id.txtFrom)
    TextView txtFrom;
    @BindView(R.id.txtTo)
    TextView txtTo;
    private GoogleMap mMap;
    private Bookings book;
    private CompanionProfile companionProfile;
    String tripID = "";
    SpeedDialActionItem terminateButton, endButton, startButton, cancelButton;
    MyDate myDate;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);
        myDate = new MyDate();
        tripID = getIntent().getStringExtra("id");
        terminateButton = new SpeedDialActionItem.Builder(R.id.fab_terminate_trip, R.drawable.ic_close)
                .setFabBackgroundColor(getResources().getColor(R.color.red))
                .setLabel("Terminate Trip")
                .create();
        endButton = new SpeedDialActionItem.Builder(R.id.fab_end_trip, R.drawable.ic_exit)
                .setFabBackgroundColor(getResources().getColor(R.color.gray))
                .setLabel("End Trip")
                .create();
        startButton = new SpeedDialActionItem.Builder(R.id.fab_start_trip, R.drawable.ic_check)
                .setFabBackgroundColor(getResources().getColor(R.color.green))
                .setLabel("Start Trip")
                .create();
        cancelButton = new SpeedDialActionItem.Builder(R.id.fab_cancel_trip, R.drawable.ic_remove)
                .setFabBackgroundColor(getResources().getColor(R.color.orange))
                .setLabel("Cancel Booking")
                .create();
        SpeedDialView speedDialView = findViewById(R.id.speedDial);
        speedDialView.setOnActionSelectedListener(this);



        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        API.getInstance().getBookDetails(this, tripID,
                new OnDataReady()
                {
                    @Override
                    public void onArrayReady(List list)
                    {

                    }

                    @Override
                    public void onObjectReady(Object object)
                    {
                        try {
                            book = (Bookings) object;
                            txtAddress.setText(book.getPickupAddress());
                            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
                            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                            mapFragment.getMapAsync(BookingDetailsActivity.this);
                            switch (book.getStatus())
                            {
                                case 0:
                                    speedDialView.addActionItem(cancelButton);
                                    break;
                                case 1:
                                    switch (book.getTripStatus())
                                    {
                                        case 0:
                                            speedDialView.addActionItem(startButton);
                                            speedDialView.addActionItem(cancelButton);
                                            break;
                                        case 1:
                                            speedDialView.addActionItem(terminateButton);
                                            if (myDate.getCurrentTimeMillis() >= DateFormatter.getLongDateTime(book.getEndTime()).getMillis())
                                            {
                                                Toast.makeText(BookingDetailsActivity.this, "Trip time ended", Toast.LENGTH_SHORT).show();
                                                speedDialView.addActionItem(endButton);
                                            }
                                            break;
                                    }
                                    break;
                            }
                            API.getInstance().getCompanionProfile(BookingDetailsActivity.this, book.getCMID(),
                                    new OnDataReady()
                                    {
                                        @Override
                                        public void onArrayReady(List list)
                                        {

                                        }

                                        @Override
                                        public void onObjectReady(Object object)
                                        {
                                            companionProfile = (CompanionProfile) object;
                                            txtName.setText(companionProfile.getCUsername());
                                        }
                                    }, "Loading");
                        } catch (ClassCastException e)
                        {

                        }
                    }
                }, "Getting Trip Details..");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(book.getPickupLat(), book.getPickupLong());
        mMap.addMarker(new MarkerOptions().position(sydney).title("Pickup Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 14f));
    }

    @Override
    public boolean onActionSelected(SpeedDialActionItem actionItem)
    {
        switch (actionItem.getId())
        {
            case R.id.fab_start_trip:
                new AlertDialog.Builder(this)
                        .setTitle("Start Trip")
                        .setMessage("Do you really want to Start this trip?")
                        .setIcon(R.drawable.ic_plane)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                API.getInstance().startCompanion(BookingDetailsActivity.this, tripID, "Starting Trip..");
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();
                break;
            case R.id.fab_end_trip:
                new AlertDialog.Builder(this)
                        .setTitle("End Trip")
                        .setMessage("Do you really want to end this trip?")
                        .setIcon(R.drawable.ic_warning)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                API.getInstance().endCompanion(BookingDetailsActivity.this, tripID, "Ending your trip..");
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();
                break;
            case R.id.fab_terminate_trip:
                //TextView tx;
                String[] s = { "India ", "Arica", "India ", "Arica", "India ", "Arica",
                        "India ", "Arica", "India ", "Arica" };
                final ArrayAdapter<String> adp = new ArrayAdapter<String>(BookingDetailsActivity.this,
                        android.R.layout.simple_spinner_item, s);

                //tx = (TextView)findViewById(R.id.txt1);
                //edComplain.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 100));

                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.terminate_dialog, null);
                final Spinner sp = view.findViewById(R.id.spTerminate);
                sp.setAdapter(adp);
                final EditText edComplain = view.findViewById(R.id.edTerminate);

                new AlertDialog.Builder(this)
                        .setTitle("Terminate Trip")
                        .setMessage("Do you really want to Terminate this trip?")
                        .setIcon(R.drawable.ic_warning)
                        .setView(view)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                API.getInstance().terminateCompanion(BookingDetailsActivity.this, tripID, 1, edComplain.getText().toString());
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();
                break;
            case R.id.fab_cancel_trip:
                new AlertDialog.Builder(this)
                        .setTitle("Cancel Confirm")
                        .setMessage("Do you really want to cancel booking?")
                        .setIcon(R.drawable.ic_warning)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                API.getInstance().cancelBooking(BookingDetailsActivity.this, tripID, "Terminating this trip..");
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();
                break;
//            case R.id.fab_delete_trip:
//                API.getInstance().deleteBooking(this, tripID, "Terminating this trip..");
//                finish();
//                break;
        }
        return false;
    }
}
