package com.undecode.goduettocompanion;

import android.app.Application;

import com.undecode.goduettocompanion.bakar.network.BakarRequests;


public class MyApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        BakarRequests.getInstance(this);
    }
}
