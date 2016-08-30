package com.starksky.selfiegeek.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.starksky.selfiegeek.app.MyApplication;

/**
 * Created by NTQK0716 on 2016-08-30.
 */
public class PerformChecks {


    /**
     * Method used to check whether Network is available or not
     *
     * @return boolean
     */
    public static boolean isNetworkAvailable() {
        ConnectivityManager manager =
                (ConnectivityManager) MyApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }

    /**
     * Method to show the toast message on screen.
     *
     * @param message
     * @param duration
     */
    public static void showToast(String message, int duration) {
        Toast.makeText(MyApplication.getInstance(), message, duration).show();
    }
}
