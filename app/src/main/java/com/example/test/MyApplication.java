package com.example.test;

import android.app.Application;
import android.content.Context;

/**
 * Created by cyc20 on 2017/12/4.
 */

public class MyApplication extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
