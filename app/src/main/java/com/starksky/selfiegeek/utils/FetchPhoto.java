package com.starksky.selfiegeek.utils;

import android.util.Log;

import com.kinvey.android.callback.KinveyListCallback;
import com.kinvey.java.core.DownloaderProgressListener;
import com.kinvey.java.core.MediaHttpDownloader;
import com.starksky.selfiegeek.app.MyApplication;
import com.starksky.selfiegeek.model.Entity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by akshat on 26/08/16.
 */
public class FetchPhoto {
    private static final String TAG = FetchPhoto.class.getSimpleName();


    public FetchPhoto() {
      /*  File file = new File(Environment.getExternalStorageDirectory()+"/selfiegeek/");
        File imageList[] = file.listFiles();*/
        // ImageList.setImageList(imageList);
        /*AsyncAppData<EventEntity> myevents = MyApplication.getInstance().getClient().appData("myFileID", EventEntity.class);
            myevents.get(new KinveyListCallback<EventEntity>() {
            @Override
            public void onSuccess(EventEntity[] result) {
                Log.d(TAG, "received " + result.length + " events");
            }

            @Override
            public void onFailure(Throwable error) {
                Log.d(TAG, "failed to fetch all", error);
            }
        });*/

        MyApplication.getInstance().getClient().appData("entityCollection", Entity.class).get(new KinveyListCallback<Entity>() {
            @Override
            public void onSuccess(Entity[] result) {
                for(int i=0;i<result.length;i++) {
                    startDownload(result[i]);
                }
                Log.d(TAG, "received " + result.length + " events");
            }

            @Override
            public void onFailure(Throwable error) {
                Log.d(TAG, "failed to fetch all", error);

            }
        });
    }

    void startDownload(Entity result) {
        FileOutputStream fos = null;
        try {
            String str = String.format("/sdcard/selfiegeek/akshat.jpg", System.currentTimeMillis());
            fos = new FileOutputStream(str);
            MyApplication.getInstance().getClient().file().downloadWithTTL(result.getTitle(), 1200000, fos, new DownloaderProgressListener() {
                @Override
                public void progressChanged(MediaHttpDownloader mediaHttpDownloader) throws IOException {

                }

                @Override
                public void onSuccess(Void aVoid) {
                    Log.d(TAG,"One file downloaded");
                }

                @Override
                public void onFailure(Throwable throwable) {

                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

