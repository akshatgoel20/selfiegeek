package com.starksky.selfiegeek.utils;

import android.os.Environment;
import android.util.Log;

import com.kinvey.android.AsyncAppData;
import com.kinvey.android.callback.KinveyListCallback;
import com.starksky.selfiegeek.app.MyApplication;
import com.starksky.selfiegeek.model.EventEntity;
import com.starksky.selfiegeek.model.ImageList;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by akshat on 26/08/16.
 */
public class FetchPhoto {
    private static final String TAG = FetchPhoto.class.getSimpleName();


    public FetchPhoto() {
      /*  File file = new File(Environment.getExternalStorageDirectory()+"/selfiegeek/");
        File imageList[] = file.listFiles();*/
        // ImageList.setImageList(imageList);
        AsyncAppData<EventEntity> myevents = MyApplication.getInstance().getClient().appData("events", EventEntity.class);
            myevents.get(new KinveyListCallback<EventEntity>() {
            @Override
            public void onSuccess(EventEntity[] result) {
                Log.d(TAG, "received " + result.length + " events");
            }

            @Override
            public void onFailure(Throwable error) {
                Log.d(TAG, "failed to fetch all", error);
            }
        });
    }
}
