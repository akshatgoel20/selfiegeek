package com.starksky.selfiegeek.network;

import android.util.Log;
import android.view.View;

import com.kinvey.java.core.KinveyClientCallback;
import com.starksky.selfiegeek.app.MyApplication;
import com.starksky.selfiegeek.framework.iface.ResponseListener;
import com.starksky.selfiegeek.model.Entity;

/**
 * Created by NTQK0716 on 2016-08-29.
 */
public class Save {

ResponseListener responseListener ;

    private static final String TAG = Save.class.getSimpleName();

    public void onSaveClick(String str, String type) {
        Entity entity = new Entity(str);
        entity.put("Description",type);
        MyApplication.getInstance().getClient().appData(MyApplication.getInstance().getClient().user().getId(),
                Entity.class).save(entity, new KinveyClientCallback<Entity>() {
            @Override
            public void onSuccess(Entity result) {
               // responseListener.updateAdapter();
                Log.i(TAG, "upload progress: " + result.getTitle());
            }

            @Override
            public void onFailure(Throwable error) {
                Log.e(TAG, "failed to save file.", error);
            }
        });
    }
}
