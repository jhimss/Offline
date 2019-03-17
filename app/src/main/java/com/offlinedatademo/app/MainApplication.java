package com.offlinedatademo.app;

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.offlinedatademo.volley.ApiCall;

public class MainApplication extends MultiDexApplication {

    @Override
    public void onCreate()
    {
        super.onCreate();
        MultiDex.install(this);
        ApiCall.getInstance(this);
    }
}
