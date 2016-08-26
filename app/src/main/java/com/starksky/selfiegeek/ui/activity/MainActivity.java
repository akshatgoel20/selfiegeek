package com.starksky.selfiegeek.ui.activity;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyPingCallback;
import com.starksky.selfiegeek.R;
import com.starksky.selfiegeek.ui.fragment.CameraFragment;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private GLSurfaceView vsv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new CameraFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, fragment).addToBackStack(TAG);
                fragmentTransaction.commit();
            }
        });


        final Client mKinveyClient = new Client.Builder("kid_HJGQ3Nhc", "fe7c9c680cb04963a91b574ad5e4e550"
                , this).build();
    mKinveyClient.ping(new KinveyPingCallback() {
        @Override
        public void onSuccess(Boolean aBoolean) {
            Log.d("status","success");

        }

        @Override
        public void onFailure(Throwable throwable) {
            Log.d("status","fail: "+throwable.toString());
        }
    });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
