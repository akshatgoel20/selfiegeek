package com.starksky.selfiegeek.network;

import android.util.Log;
import android.view.View;

import com.kinvey.java.core.KinveyClientCallback;
import com.starksky.selfiegeek.app.MyApplication;
import com.starksky.selfiegeek.model.Entity;

/**
 * Created by NTQK0716 on 2016-08-29.
 */
public class Save {

    private static final String TAG = Save.class.getSimpleName();

    public void onSaveClick(String str) {
        Entity entity = new Entity(str);
        entity.put("Description","image");
        MyApplication.getInstance().getClient().appData("entityCollectionone", Entity.class).save(entity, new KinveyClientCallback<Entity>() {
            @Override
            public void onSuccess(Entity result) {
                Log.i(TAG, "upload progress: " + result.getTitle());
            }

            @Override
            public void onFailure(Throwable error) {
                Log.e(TAG, "failed to save file.", error);
            }
        });
    }
}