package com.undecode.goduettocompanion.bakar.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class BakarSingleton
{
    private static BakarSingleton instance;
    private RequestQueue requestQueue;
    private static Context context;

    private BakarSingleton(Context mContext)
    {
        context = mContext;
        requestQueue = getRequestQueue();
    }

    public static synchronized BakarSingleton getInstance(Context mContext)
    {
        if (instance == null)
        {
            instance = new BakarSingleton(mContext);
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
