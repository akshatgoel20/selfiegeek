package com.starksky.selfiegeek.utils;

import android.os.Environment;

import com.starksky.selfiegeek.model.ImageList;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by akshat on 26/08/16.
 */
public class FetchPhoto {




    public FetchPhoto() {
        File file = new File(Environment.getExternalStorageDirectory()+"/selfiegeek/");
        File imageList[] = file.listFiles();
        ImageList.setImageList(imageList);
    }
}
