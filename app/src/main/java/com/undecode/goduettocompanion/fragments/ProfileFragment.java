package com.undecode.goduettocompanion.fragments;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.tsongkha.spinnerdatepicker.DatePicker;
import com.tsongkha.spinnerdatepicker.DatePickerDialog;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;
import com.undecode.goduettocompanion.R;
import com.undecode.goduettocompanion.data.Countries;
import com.undecode.goduettocompanion.interfaces.Constants;
import com.undecode.goduettocompanion.models.CountriesModel;
import com.undecode.goduettocompanion.models.ProfileModel;
import com.undecode.goduettocompanion.registerationsys.SignInActivity;
import com.undecode.goduettocompanion.registerationsys.SignUpActivity;
import com.undecode.goduettocompanion.bakar.utils.Animator;
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

public class ProfileFragment extends Fragment implements View.OnClickListener, IPickResult, AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener, RadioGroup.OnCheckedChangeListener
{
    private ImageView imageView;
    private Bitmap bitmap;
    private AppCompatSpinner spCountries;
    private List<CountriesModel> countriesModels;
    private FillSpinner fillSpinner;
    private EditText edPhoneCode1, edPhoneCode2, edName, edUserName, edEmail, edPhone1, edPhone2, edPass1, edPass2;
    private TextView txtDate, txtButtonCreateAccount;
    private String countryId, imageLocation, imageUrl;
    private RadioGroup radioGroup;
    private boolean gender;
    private Date dateRepresentation;
    private Animator animator;
    private Validation validation;
    private static ProfileFragment profileFragment;
    private RadioButton rdMale, rdFemale;
    private Context context;
    ProgressDialog progressDialog;

    public static ProfileFragment newInstance()
    {
        if (profileFragment == null)
        {
            profileFragment = new ProfileFragment();
        }
        return profileFragment;
    }

    public static ProfileFragment newInstance(Bundle bundle)
    {
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        attachViews(view);
        return view;
    }

    private void attachViews(View v)
    {
        context = getContext();
        progressDialog = new ProgressDialog(context);
        validation = new Validation(getContext());
        animator = new Animator();
        fillSpinner = new FillSpinner();
        countriesModels = Countries.getInstance().getCountriesModels();
        edPhoneCode1 = v.findViewById(R.id.edPhoneCode1);
        edPhoneCode1.setEnabled(false);
        edPhoneCode2 = v.findViewById(R.id.edPhoneCode2);
        edPhoneCode1.setEnabled(false);
        imageView = v.findViewById(R.id.imgProfile);
        imageView.setOnClickListener(this);
        spCountries = v.findViewById(R.id.spCountries);
        ArrayAdapter countriesModelArrayAdapter = new ArrayAdapter<>(getContext(),R.layout.item_spinner,countriesModels);
        fillSpinner.fillSpinner(countriesModelArrayAdapter, spCountries);
        spCountries.setOnItemSelectedListener(this);
        txtDate = v.findViewById(R.id.txtDate);
        txtDate.setOnClickListener(this);
        edName = v.findViewById(R.id.edName);
        edUserName = v.findViewById(R.id.edUserName);
        edEmail = v.findViewById(R.id.edEmail);
        edPhone1 = v.findViewById(R.id.edPhone1);
        edPhone2 = v.findViewById(R.id.edPhone2);
        edPass1 = v.findViewById(R.id.edPassword);
        edPass2 = v.findViewById(R.id.edPassword2);
        radioGroup = v.findViewById(R.id.rdGroup);
        radioGroup.setOnCheckedChangeListener(this);
        rdMale = v.findViewById(R.id.rdMale);
        rdFemale = v.findViewById(R.id.rdFemale);
        txtButtonCreateAccount = v.findViewById(R.id.txtButtonCreateUser);
        txtButtonCreateAccount.setOnClickListener(this);
        dataBinding();
    }

    private void dataBinding()
    {
        ProfileModel profileModel = ProfileModel.getInstance();
        imageUrl = profileModel.getProfilePic();
        try {
            Glide.with(context).load(imageUrl).into(imageView);
        }catch (Exception e){}
        edName.setText(profileModel.getName());
        edUserName.setText(profileModel.getUserName());
        edEmail.setText(profileModel.getEmail());
        edPass1.setText(profileModel.getPassword());
        edPass2.setText(profileModel.getPassword());
        txtDate.setText(profileModel.getBirthDate().getYear()+"-"+profileModel.getBirthDate().getMonthOfYear()+"-"+profileModel.getBirthDate().getDayOfMonth());
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, profileModel.getBirthDate().getYear());
        cal.set(Calendar.MONTH, profileModel.getBirthDate().getMonthOfYear()-1);
        cal.set(Calendar.DAY_OF_MONTH, profileModel.getBirthDate().getDayOfMonth());
        dateRepresentation = cal.getTime();
        if (profileModel.isGender())
        {
            rdMale.setChecked(true);
        }else
        {
            rdFemale.setChecked(true);
        }
        int i = 0;
        while (!profileModel.getNationalityID().equals(countriesModels.get(i).getId()))
        {
            i++;
        }
        spCountries.setSelection(i);
        edPhone1.setText(profileModel.getMobile().substring(countriesModels.get(i).getPhonePrefix().length()));
        edPhone2.setText(profileModel.getMobile2().substring(countriesModels.get(i).getPhonePrefix().length()));
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
                        imageUrl = jsonObject.optString("PostFileResult");
                        createAccount();
                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
            }.execute();
        }else
        {
            createAccount();
        }
    }

    private void createAccount()
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
                ProfileModel profileModel = ProfileModel.getInstance();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", edName.getText().toString());
                jsonObject.put("Id", profileModel.getId());
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
                final ProgressDialog progressDialog = new ProgressDialog(context);
                progressDialog.setMessage("Updating your profile please wait..");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setCancelable(false);
                progressDialog.setIndeterminate(true);
                progressDialog.show();
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Constants.API.EDIT_PROFILE_DATA, jsonObject,
                        new Response.Listener<JSONObject>()
                        {
                            @Override
                            public void onResponse(JSONObject response)
                            {
                                Log.wtf("Edit Response Bakar: ", response.toString());
                                progressDialog.dismiss();
                                Toast.makeText(context, "Profile updated successfully.", Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            {
                                progressDialog.dismiss();
                                Toast.makeText(context, "Error while updating your profile.", Toast.LENGTH_SHORT).show();
                            }
                        });
                VolleySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
            } catch (JSONException e)
            {
                e.printStackTrace();
            }
        }else if (dateRepresentation == null)
        {
            Toast.makeText(context, "please pick your birth date", Toast.LENGTH_SHORT).show();
            new SpinnerDatePickerDialogBuilder()
                    .context(context)
                    .callback(ProfileFragment.this)
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
            Toast.makeText(context, "password does not match.", Toast.LENGTH_SHORT).show();
            animator.vibrateView(edPass1);
            animator.vibrateView(edPass2);
        }else if (!isEmailValid)
        {
            Toast.makeText(context, "wrong email format", Toast.LENGTH_SHORT).show();
            animator.vibrateView(edEmail);
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
            Toast.makeText(getContext(), "Selected", Toast.LENGTH_SHORT).show();

            //Image path
            imageLocation = pickResult.getPath();
        } else {
            //Handle possible errors
            //TODO: do what you have to do with r.getError();
            Toast.makeText(getContext(), pickResult.getError().getMessage(), Toast.LENGTH_LONG).show();
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
                Toast.makeText(getContext(), "Male Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rdFemale:
                gender = false;
                Toast.makeText(getContext(), "Female Clicked", Toast.LENGTH_SHORT).show();
                break;
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
                        .show(getActivity()).setOnPickResult(this);
                break;
            case R.id.txtDate:
                new SpinnerDatePickerDialogBuilder()
                        .context(getContext())
                        .callback(this)
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
}
