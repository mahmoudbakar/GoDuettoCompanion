package com.undecode.goduettocompanion.bakar.network;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class BakarRequests
{
    private static final String TAG = "BakarRequests";
    private static BakarRequests instance = null;

    public BakarSingleton bakarSingleton;
    //lets test

    private BakarRequests(Context context)
    {
        bakarSingleton = BakarSingleton.getInstance(context);
    }

    public static synchronized BakarRequests getInstance(Context context)
    {
        if (instance == null)
        {
            instance = new BakarRequests(context);
        }
        return instance;
    }

    public static synchronized BakarRequests getInstance()
    {
        if (instance == null)
        {
            throw new IllegalStateException(BakarRequests.class.getSimpleName() + " is not initialized, Call getInstance(sendContextHere) first");
        }
        return instance;
    }

    public void jsonObjectPostRequest(Context context, String url, JSONObject jsonObject, final OnBakarReady listener, final boolean progress, String message)
    {
        try
        {
            Log.wtf("Bakar Request", jsonObject.toString());
        }catch (NullPointerException e){}
        final ProgressDialog progressDialog = new ProgressDialog(context);
        if (progress)
        {

            progressDialog.setMessage(message);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(false);
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        Log.wtf("Bakar Response ", response.toString());
                        listener.onSuccess(response);
                        if (progress) progressDialog.dismiss();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        listener.onError(error);
                        if (progress) progressDialog.dismiss();
                    }
                })
        {
            @Override
            public String getBodyContentType()
            {
                return "application/json";
            }
        };
        bakarSingleton.addToRequestQueue(request);
    }

    public void jsonObjectGetRequest(Context context, String url, final OnBakarReady listener, final boolean progress, String message)
    {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        if (progress)
        {

            progressDialog.setMessage(message);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(false);
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        listener.onSuccess(response);
                        if (progress) progressDialog.dismiss();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        listener.onError(error);
                        if (progress) progressDialog.dismiss();
                    }
                });
        bakarSingleton.addToRequestQueue(request);
    }
}
