package com.undecode.goduettocompanion.bakar.utils;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton
{
    private static VolleySingleton instance;
    private RequestQueue requestQueue;
    private static Context context;

    private VolleySingleton(Context mContext)
    {
        context = mContext;
        requestQueue = getRequestQueue();
    }

    public static synchronized VolleySingleton getInstance(Context mContext)
    {
        if (instance == null)
        {
            instance = new VolleySingleton(mContext);
        }
        return instance;
    }

    public RequestQueue getRequestQueue()
    {
        if (requestQueue == null)
        {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request)
    {
        getRequestQueue().add(request);
    }
}
