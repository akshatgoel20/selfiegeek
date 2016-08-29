package com.starksky.selfiegeek.app;

import android.app.Application;
import android.graphics.Typeface;

import com.android.volley.RequestQueue;
import com.kinvey.android.Client;

import java.util.Locale;

/**
 * Created by akshat on 25/08/16.
 */
public class MyApplication extends Application {
    public static final String TAG = MyApplication.class.getSimpleName();

    private static MyApplication mInstance;

    private String sDefSystemLanguage;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        sDefSystemLanguage = Locale.getDefault().getLanguage();
    }

    public Client getClient(){
        final Client mKinveyClient = new Client.Builder("kid_HJGQ3Nhc", "fe7c9c680cb04963a91b574ad5e4e550"
                , this).build();
        return mKinveyClient;
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }
}
