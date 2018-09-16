package com.undecode.goduettocompanion.registerationsys;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.tsongkha.spinnerdatepicker.DatePicker;
import com.tsongkha.spinnerdatepicker.DatePickerDialog;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;
import com.undecode.goduettocompanion.R;
import com.undecode.goduettocompanion.data.Countries;
import com.undecode.goduettocompanion.interfaces.Constants;
import com.undecode.goduettocompanion.models.CountriesModel;
import com.undecode.goduettocompanion.bakar.utils.Animator;
import com.undecode.goduettocompanion.bakar.utils.FileUpload;
import com.undecode.goduettocompanion.bakar.utils.FillSpinner;
import com.undecode.goduettocompanion.bakar.utils.ImageDownloader;
import com.undecode.goduettocompanion.bakar.utils.Validation;
import com.undecode.goduettocompanion.bakar.utils.VolleySingleton;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener, IPickResult, AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener, RadioGroup.OnCheckedChangeListener
{
    private CircleImageView imageView;
    private Bitmap bitmap;
    private AppCompatSpinner spCountries;
    private List<CountriesModel> countriesModels;
    private FillSpinner fillSpinner;
    private EditText edPhoneCode1, edPhoneCode2, edName, edUserName, edEmail, edPhone1, edPhone2, edPass1, edPass2;
    private TextView txtDate, txtButtonCreateAccount;
    private String countryId, imageLocation;
    private RadioGroup radioGroup;
    private boolean gender;
    private Date dateRepresentation;
    private Animator animator;
    private Validation validation;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        attachViews();
    }

    private void attachViews()
    {
        progressDialog = new ProgressDialog(this);
        validation = new Validation(this);
        animator = new Animator();
        fillSpinner = new FillSpinner();
        countriesModels = Countries.getInstance().getCountriesModels();
        edPhoneCode1 = findViewById(R.id.edPhoneCode1);
        edPhoneCode1.setEnabled(false);
        edPhoneCode2 = findViewById(R.id.edPhoneCode2);
        edPhoneCode1.setEnabled(false);
        imageView = findViewById(R.id.imgProfile);
        imageView.setOnClickListener(this);
        spCountries = findViewById(R.id.spCountries);
        ArrayAdapter countriesModelArrayAdapter = new ArrayAdapter<>(SignUpActivity.this,
                R.layout.item_spinner,
                countriesModels);
        fillSpinner.fillSpinner(countriesModelArrayAdapter, spCountries);
        spCountries.setOnItemSelectedListener(this);
        txtDate = findViewById(R.id.txtDate);
        txtDate.setOnClickListener(this);
        edName = findViewById(R.id.edName);
        edUserName = findViewById(R.id.edUserName);
        edEmail = findViewById(R.id.edEmail);
        edPhone1 = findViewById(R.id.edPhone1);
        edPhone2 = findViewById(R.id.edPhone2);
        edPass1 = findViewById(R.id.edPassword);
        edPass2 = findViewById(R.id.edPassword2);
        radioGroup = findViewById(R.id.rdGroup);
        radioGroup.setOnCheckedChangeListener(this);
        RadioButton rdMale = findViewById(R.id.rdMale);
        rdMale.setChecked(true);
        txtButtonCreateAccount = findViewById(R.id.txtButtonCreateUser);
        txtButtonCreateAccount.setOnClickListener(this);
    }

    @SuppressLint("StaticFieldLeak")
    private void uploadImage()
    {
        if (imageLocation != null && imageLocation.length() > 1)
        {
            new AsyncTask<Void, Integer, String>()
            {
                @Override
                protected void onPreExecute()
                {
                    super.onPreExecute();
                    progressDialog.setMessage("Uploading image..");
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.setCancelable(false);
                    progressDialog.setIndeterminate(true);
                    progressDialog.show();
                }

                @Override
                protected String doInBackground(Void... voids)
                {
                    return ImageDownloader.uploadImage(bitmap);
                }

                @Override
                protected void onPostExecute(String s)
                {
                    super.onPostExecute(s);
                    try
                    {
                        progressDialog.dismiss();
                        JSONObject jsonObject = new JSONObject(s);
                        String imageUrl = jsonObject.optString("PostFileResult");
                        createAccount(imageUrl);
                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
            }.execute();
        }else
        {
            createAccount("null");
        }
    }

    private void createAccount(String imageUrl)
    {
        String pass1 = edPass1.getText().toString();
        String pass2 = edPass2.getText().toString();
        boolean isEmailValid = validation.isEmailValid(edEmail.getText().toString());
        if (dateRepresentation != null
                && pass1.equals(pass2)
                && isEmailValid
                && !validation.isEmpty(edName, "Name", animator)
                && !validation.isEmpty(edUserName, "UserName", animator)
                && !validation.isEmpty(edPhone1, "Phone", animator)
                && !validation.isEmpty(edPass1, "Password", animator))
        {
            try
            {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", edName.getText().toString());
                jsonObject.put("userName", edUserName.getText().toString());
                jsonObject.put("mobile", edPhoneCode1.getText().toString() + edPhone1.getText().toString());
                jsonObject.put("email", edEmail.getText().toString());
                jsonObject.put("password", edPass1.getText().toString());
                jsonObject.put("profilePic", imageUrl);
                jsonObject.put("nationality", countryId);
                String birth = getString(R.string.datePrefix) + String.valueOf(dateRepresentation.getTime()) + getString(R.string.datePostfix);
                jsonObject.put("birthdate", birth);
                jsonObject.put("altMobile", edPhoneCode1.getText().toString() + edPhone2.getText().toString());
                jsonObject.put("gender", String.valueOf(gender));
                Log.wtf("test Bakar", jsonObject.toString());
                progressDialog.setMessage("Creating your account please wait..");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setCancelable(false);
                progressDialog.setIndeterminate(true);
                progressDialog.show();
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Constants.API.REGISTER, jsonObject,
                        new Response.Listener<JSONObject>()
                        {
                            @Override
                            public void onResponse(JSONObject response)
                            {
                                progressDialog.dismiss();
                                if (!response.optString("RegisterResult").equals("00000000-0000-0000-0000-000000000000"))
                                {
                                    Toast.makeText(SignUpActivity.this, "Account created successfully.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else
                                {
                                    Toast.makeText(SignUpActivity.this, "Error while creating your account.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            {
                                progressDialog.dismiss();
                                Toast.makeText(SignUpActivity.this, "Error while creating your account.", Toast.LENGTH_SHORT).show();
                            }
                        });
                VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
            } catch (JSONException e)
            {
                e.printStackTrace();
            }
        }else if (dateRepresentation == null)
        {
            Toast.makeText(this, "please pick your birth date", Toast.LENGTH_SHORT).show();
            new SpinnerDatePickerDialogBuilder()
                    .context(this)
                    .callback(SignUpActivity.this)
                    .spinnerTheme(R.style.NumberPickerStyle)
                    .showTitle(true)
                    .showDaySpinner(true)
                    .defaultDate(1990, 0, 1)
                    .maxDate(2008, 0, 1)
                    .minDate(1920, 0, 1)
                    .build()
                    .show();
        }else if (!pass1.equals(pass2))
        {
            Toast.makeText(this, "password does not match.", Toast.LENGTH_SHORT).show();
            animator.vibrateView(edPass1);
            animator.vibrateView(edPass2);
        }else if (!isEmailValid)
        {
            Toast.makeText(this, "wrong email format", Toast.LENGTH_SHORT).show();
            animator.vibrateView(edEmail);
        }
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.imgProfile:
                PickImageDialog.build(new PickSetup()
                        .setIconGravity(Gravity.CENTER)
                        .setButtonOrientation(LinearLayoutCompat.HORIZONTAL))
                        .show(this).setOnPickResult(this);
                break;
            case R.id.txtDate:
                new SpinnerDatePickerDialogBuilder()
                        .context(this)
                        .callback(SignUpActivity.this)
                        .spinnerTheme(R.style.NumberPickerStyle)
                        .showTitle(true)
                        .showDaySpinner(true)
                        .defaultDate(1990, 0, 1)
                        .maxDate(2008, 0, 1)
                        .minDate(1920, 0, 1)
                        .build()
                        .show();
                break;
            case R.id.txtButtonCreateUser:
                uploadImage();
                break;
        }
    }

    @Override
    public void onPickResult(PickResult pickResult)
    {
        if (pickResult.getError() == null)
        {
            //If you want the Uri.
            //Mandatory to refresh image from Uri.
            //getImageView().setImageURI(null);

            //Setting the real returned image.
            //getImageView().setImageURI(r.getUri());

            //If you want the Bitmap.
            bitmap = pickResult.getBitmap();
            imageView.setImageBitmap(bitmap);

            //Image path
            imageLocation = pickResult.getPath();
            //new FileUpload(pickResult.getPath()).execute();
        } else {
            //Handle possible errors
            //TODO: do what you have to do with r.getError();
            Toast.makeText(this, pickResult.getError().getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        switch (parent.getId())
        {
            case R.id.spCountries:
                countryId = countriesModels.get(position).getId();
                edPhoneCode1.setText(countriesModels.get(position).getPhonePrefix());
                edPhoneCode2.setText(countriesModels.get(position).getPhonePrefix());
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
    {
        txtDate.setText(String.valueOf(year)+"-"+String.valueOf((monthOfYear+1))+"-"+String.valueOf(dayOfMonth));
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, monthOfYear);
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        dateRepresentation = cal.getTime();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId)
    {
        switch (checkedId)
        {
            case R.id.rdMale:
                gender = true;
                Toast.makeText(this, "Male Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rdFemale:
                gender = false;
                Toast.makeText(this, "Female Clicked", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
