package com.starksky.selfiegeek.model;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by akshat on 28/08/16.
 */
public class ImageList {

    public static File[] getImageList() {
        return imageList;
    }

    public static void setImageList(File[] imageList) {
        ImageList.imageList = imageList;
    }

    static File imageList[] ;
}
