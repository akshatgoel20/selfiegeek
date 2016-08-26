package com.starksky.selfiegeek.app;

import android.app.Application;
import android.graphics.Typeface;

import com.android.volley.RequestQueue;

import java.util.Locale;

/**
 * Created by akshat on 25/08/16.
 */
public class MyApplication extends Application {
    public static final String TAG = MyApplication.class.getSimpleName();

    private static MyApplication mInstance;

    private String sDefSystemLanguage;

    private Typeface fontStyle;

    private RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        sDefSystemLanguage = Locale.getDefault().getLanguage();
    }
}
