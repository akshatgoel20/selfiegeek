package com.starksky.selfiegeek.network;

import android.util.Log;

import com.kinvey.java.core.MediaHttpUploader;
import com.kinvey.java.core.UploaderProgressListener;
import com.kinvey.java.model.FileMetaData;
import com.starksky.selfiegeek.app.MyApplication;

import java.io.File;
import java.io.IOException;

/**
 * Created by akshat on 28/08/16.
 */
public class Upload {
    private static final String TAG = Upload.class.getSimpleName();

    public void uploadcontent(final File image) {
        //   File file[] = ImageList.getImageList();
        //   File image = file[ImageList.getImageList().length-1];

        MyApplication.getInstance().getClient().file().upload(image, new UploaderProgressListener() {
            @Override
            public void progressChanged(MediaHttpUploader uploader) throws IOException {
                Log.i(TAG, "upload progress: " + uploader.getUploadState());

            }

            @Override
            public void onSuccess(FileMetaData fileMetaData) {
                Log.i(TAG, "successfully upload file");
                if(image.getName().contains(".jpg")) {
                    new Save().onSaveClick(image.getName(), "image");
                }else if(image.getName().contains(".mp4")){
                    new Save().onSaveClick(image.getName(), "video");
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.e(TAG, "failed to upload file.", throwable);
            }
        });

    }
}
