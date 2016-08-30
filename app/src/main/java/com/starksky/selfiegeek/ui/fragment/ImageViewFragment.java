package com.starksky.selfiegeek.ui.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.starksky.selfiegeek.R;
import com.starksky.selfiegeek.model.ImageList;

/**
 * Created by NTQK0716 on 2016-08-30.
 */
public class ImageViewFragment extends Fragment {
    ImageView imageView;
    int position ;
    String type ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(getArguments()!= null){
            position = getArguments().getInt("pos");
        }
        String absPath = ImageList.getImageList().get(position).getAbsolutePath();
        if(absPath.contains(".jpg")) {
            View rootView = inflater.inflate(R.layout.fragment_view_image, container, false);
            imageView = (ImageView) rootView.findViewById(R.id.view_image);
            Bitmap bitmap1 = BitmapFactory.decodeFile(absPath);
            imageView.setImageBitmap(bitmap1);
            return rootView;
        }else{
            View rootView = inflater.inflate(R.layout.fragment_view_image, container, false);
            return rootView ;
        }
    }
}
