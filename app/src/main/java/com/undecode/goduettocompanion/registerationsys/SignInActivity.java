package com.undecode.goduettocompanion.registerationsys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.undecode.goduettocompanion.MainActivity;
import com.undecode.goduettocompanion.R;
import com.undecode.goduettocompanion.interfaces.Constants;
import com.undecode.goduettocompanion.models.ProfileModel;
import com.undecode.goduettocompanion.bakar.utils.Animator;
import com.undecode.goduettocompanion.bakar.utils.Validation;
import com.undecode.goduettocompanion.bakar.utils.VolleySingleton;
import com.undecode.goduettocompanion.bakar.utils.date.DateFormatter;

import org.joda.time.DateTime;
import org.json.JSONException;
import org.json.JSONObject;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener
{
    private EditText edPhone, edPassword;
    private LinearLayout mainLay;
    private Animator animator;
    private VolleySingleton volleySingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        attachViews();
    }

    private void attachViews()
    {
        animator = new Animator();
        volleySingleton = VolleySingleton.getInstance(this);
        mainLay = findViewById(R.id.mainLay);
        edPhone = findViewById(R.id.edPhone);
        edPassword = findViewById(R.id.edPassword);
        TextView txtButtonSingIn = findViewById(R.id.txtButtonSignIn);
        TextView txtButtonSignUp = findViewById(R.id.txtButtonSignUp);
        TextView txtButtonForgetPassword = findViewById(R.id.txtButtonForgetPassword);
        txtButtonSingIn.setOnClickListener(this);
        txtButtonSignUp.setOnClickListener(this);
        txtButtonForgetPassword.setOnClickListener(this);
    }

    private void signIn()
    {
        Validation validation = new Validation(this);
        if (validation.isEmpty(edPhone, getString(R.string.phone)) || validation.isEmpty(edPassword, getString(R.string.password)))
        {
            animator.vibrateView(mainLay);
            return;
        }else
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Logging in..");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(false);
            progressDialog.setIndeterminate(true);
            progressDialog.show();
            try
            {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put(Constants.JsonKeys.PHONE, edPhone.getText().toString());
                //jsonObject.put(Constants.JsonKeys.PASSWORD, HashingHelper.getHashedValue(edPassword.getText().toString()));
                jsonObject.put(Constants.JsonKeys.PASSWORD, edPassword.getText().toString());
                Log.wtf("BakarTest", jsonObject.toString());
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Constants.API.LOGIN, jsonObject,
                        new Response.Listener<JSONObject>()
                        {
                            @Override
                            public void onResponse(JSONObject response)
                            {
                                Log.wtf("BakarTest", response.toString());
                                String loginResultId = response.optString("LoginResult");
                                if (loginResultId.equals("00000000-0000-0000-0000-000000000000"))
                                {
                                    Log.wtf("BakarTest", response.optString("LoginResult"));
                                    animator.vibrateView(mainLay);
                                    progressDialog.dismiss();
                                }else
                                {
                                    JSONObject getProfileJson = new JSONObject();
                                    try
                                    {
                                        getProfileJson.put("id", loginResultId);
                                        JsonObjectRequest getProfile = new JsonObjectRequest(Request.Method.POST, Constants.API.RETURN_BY_ID, getProfileJson,
                                                new Response.Listener<JSONObject>()
                                                {
                                                    @Override
                                                    public void onResponse(JSONObject response1)
                                                    {
                                                        JSONObject response = response1.optJSONObject("ReturnByIdResult");
                                                        Log.wtf("Bakar Profile = ", response.toString());
                                                        String activationCode = response.optString("ActivationCode");
                                                        int accountStatus = response.optInt("TAccountStatus");
                                                        int userStatus = response.optInt("UserStatus");
                                                        String id = response.optString("ID");
                                                        String name = response.optString("TName");
                                                        String userName = response.optString("TUserName");
                                                        String email = response.optString("TEmail");
                                                        String phone1 = response.optString("TMobileNumber");
                                                        String phone2 = response.optString("TAlternateMobileNumber");
                                                        String nationalityID = response.optString("TNationality");
                                                        String password = response.optString("TPassword");
//                                                        String tempBirthDate = response.optString("TDOB");
//                                                        tempBirthDate = tempBirthDate.substring(5);
//                                                        Log.wtf("Birth Date = ", tempBirthDate);
                                                        DateTime birthDate = DateFormatter.getLongDateTime(response.optString("TDOB"));
                                                        String pic = response.optString("TPicture");
                                                        ProfileModel profileModel = ProfileModel.getInstance();
                                                        profileModel.setProfileModel(id, name, userName, phone1, phone2, email, password, pic, nationalityID, activationCode, true, birthDate, userStatus, accountStatus);
                                                        progressDialog.dismiss();
                                                        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                                                        startActivity(intent);
                                                        finish();
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
                                        volleySingleton.addToRequestQueue(getProfile);
                                    } catch (JSONException e)
                                    {
                                        progressDialog.dismiss();
                                        e.printStackTrace();
                                    }
                                }
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            {
                                Log.wtf("BakarTest", error.getMessage());
                                progressDialog.dismiss();
                            }
                        });
                volleySingleton.addToRequestQueue(jsonObjectRequest);
            } catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.txtButtonSignIn:
                signIn();
                break;
            case R.id.txtButtonSignUp:
                Intent intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);
                break;
            case R.id.txtButtonForgetPassword:

                break;
        }
    }
}
