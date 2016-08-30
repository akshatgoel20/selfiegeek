package com.starksky.selfiegeek.utils;

import android.os.Environment;
import android.util.Log;

import com.kinvey.android.callback.KinveyListCallback;
import com.kinvey.java.core.DownloaderProgressListener;
import com.kinvey.java.core.MediaHttpDownloader;
import com.starksky.selfiegeek.app.MyApplication;
import com.starksky.selfiegeek.framework.iface.ResponseListener;
import com.starksky.selfiegeek.model.Entity;
import com.starksky.selfiegeek.model.ImageList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by akshat on 26/08/16.
 */
public class FetchPhoto {
    private static final String TAG = FetchPhoto.class.getSimpleName();
    private ResponseListener mResponseListener;
   private  int count = 0 ;
ArrayList<File> imageList = new ArrayList<>();

    public FetchPhoto(ResponseListener responseListener) {
        mResponseListener = responseListener;
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

        MyApplication.getInstance().getClient().appData("entityCollectionone", Entity.class).get(new KinveyListCallback<Entity>() {
            @Override
            public void onSuccess(Entity[] result) {
                for(int i =0 ;i<result.length;i++) {
                    startDownload(result[i],result.length);
                }

                Log.d(TAG, "received " + result.length + " events");
            }

            @Override
            public void onFailure(Throwable error) {
                Log.d(TAG, "failed to fetch all", error);

            }
        });
    }

    void startDownload(final Entity result, final int size) {
        FileOutputStream fos = null;

        final String str ;
        try {
            try {
                File direct = new File(Environment.getExternalStorageDirectory() + "/selfiegeek/download/");
                if (!direct.exists()) {
                    File sgdir = new File("/sdcard/selfiegeek/download");
                    sgdir.mkdirs();
                }
                 str = String.format(Environment.getExternalStorageDirectory() + "/selfiegeek/download/"+"%d.jpg", System.currentTimeMillis());
                fos = new FileOutputStream(str);
                MyApplication.getInstance().getClient().file().downloadWithTTL(result.getTitle(), 1200000, fos, new DownloaderProgressListener() {
                    @Override
                    public void progressChanged(MediaHttpDownloader mediaHttpDownloader) throws IOException {

                    }

                    @Override
                    public void onSuccess(Void aVoid) {
                        count++ ;
                        imageList.add(new File(str));
                        if(count == size) {
                            ImageList.setImageList(imageList);
                            mResponseListener.updateAdapter();
                        }
                        Log.d(TAG, "One file downloaded");
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Log.d(TAG, "download failed"+throwable);
                    }
                });
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            }catch (Exception e){
            e.printStackTrace();
        }
    }
}

