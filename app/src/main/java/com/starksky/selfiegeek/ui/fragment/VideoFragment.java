package com.starksky.selfiegeek.ui.fragment;


import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.starksky.selfiegeek.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by akshat on 27/08/16.
 */
public class VideoFragment extends Fragment implements SurfaceHolder.Callback, View.OnClickListener {
    private static final String TAG = CameraFragment.class.getSimpleName();
    android.hardware.Camera camera;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    MediaRecorder recorder;
    boolean recording = false;
    Button captureButton;
    Button modeSelect;


    public VideoFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_video, container, false);
        surfaceView = (SurfaceView) rootView.findViewById(R.id.surfaceview_video);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);


        recorder = new MediaRecorder();

        initRecorder();

        modeSelect = (Button) rootView.findViewById(R.id.modeselect_video);
        modeSelect.setOnClickListener(this);
        captureButton = (Button) rootView.findViewById(R.id.capture_button_video);
        captureButton.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        prepareRecorder();
        camera = android.hardware.Camera.open();
        Camera.Parameters param;
        camera.setDisplayOrientation(90);
        param = camera.getParameters();
        param.setPreviewSize(352, 288);
        camera.setParameters(param);

        try {
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (recording) {
            recorder.stop();
            recording = false;
        }
        recorder.release();

    }

    private void initRecorder() {
        recorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
        recorder.setVideoSource(MediaRecorder.VideoSource.DEFAULT);

        CamcorderProfile cpHigh = CamcorderProfile
                .get(CamcorderProfile.QUALITY_HIGH);
        recorder.setProfile(cpHigh);
        File direct = new File(Environment.getExternalStorageDirectory() + "/selfiegeek");
        if (!direct.exists()) {
            File sgdir = new File("/sdcard/selfiegeek/");
            sgdir.mkdirs();
        }
        recorder.setOrientationHint(90);
        try {
            String fileName = String.format("/sdcard/selfiegeek/%d.mp4", System.currentTimeMillis());
            recorder.setOutputFile(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        recorder.setMaxDuration(50000); // 50 seconds
        recorder.setMaxFileSize(5000000); // Approximately 5 megabytes
        recorder.setPreviewDisplay(surfaceHolder.getSurface());

    }

    private void prepareRecorder() {


        try {
            recorder.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.capture_button_video:
                if (recording) {
                    recorder.stop();
                    recording = false;
                    captureButton.setText("Record");

                    // Let's initRecorder so we can record again
                    initRecorder();
                    prepareRecorder();
                } else {
                    camera.release();
                    captureButton.setText("Recording");
                    recording = true;
                    recorder.start();

                }
                break;
            case R.id.modeselect_video:
                Fragment fragment = new CameraFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, fragment).addToBackStack(TAG);
                fragmentTransaction.commit();
                break;

        }

    }
}
