package com.undecode.goduettocompanion;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.undecode.goduettocompanion.data.Countries;
import com.undecode.goduettocompanion.interfaces.Constants;
import com.undecode.goduettocompanion.models.CountriesModel;
import com.undecode.goduettocompanion.registerationsys.SignInActivity;
import com.undecode.goduettocompanion.registerationsys.SignUpActivity;
import com.undecode.goduettocompanion.bakar.utils.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        attachViews();
    }

    private void attachViews()
    {
        TextView txtButtonSingIn = findViewById(R.id.txtButtonSignIn);
        TextView txtButtonSignUp = findViewById(R.id.txtButtonSignUp);
        txtButtonSingIn.setOnClickListener(this);
        txtButtonSignUp.setOnClickListener(this);

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading..");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Constants.API.LIST_COUNTRIES, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        Countries countries = Countries.getInstance();
                        List<CountriesModel> countriesModels = new ArrayList<>();
                        JSONArray jsonArray = response.optJSONArray("ListCountriesResult");
                        for (int i = 0; i < jsonArray.length(); i++)
                        {
                            JSONObject jsonObject = jsonArray.optJSONObject(i);
                            countriesModels.add(
                                    new CountriesModel(
                                            jsonObject.optString("ID"),
                                            jsonObject.optString("CountryName"),
                                            jsonObject.optString("PhonePrefix"),
                                            jsonObject.optString("flag")));
                        }
                        countries.setCountriesModels(countriesModels);
                        progressDialog.dismiss();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        progressDialog.dismiss();
                    }
                });
        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.txtButtonSignIn:
                Intent intentSignIn = new Intent(this, SignInActivity.class);
                startActivity(intentSignIn);
                finish();
                break;
            case R.id.txtButtonSignUp:
                Intent intentSignUp = new Intent(this, SignUpActivity.class);
                startActivity(intentSignUp);
                break;
        }
    }
}
