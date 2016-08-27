package com.starksky.selfiegeek.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.starksky.selfiegeek.R;

import java.io.File;

/**
 * Created by NTQK0716 on 2016-08-26.
 */
public class GridImagesAdapter extends RecyclerView.Adapter<GridImagesAdapter.ViewHolder> {

    File file = new File(Environment.getExternalStorageDirectory()+"/selfiegeek/");
    File imageList[] = file.listFiles();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gridimage, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Bitmap b = BitmapFactory.decodeFile(imageList[position].getAbsolutePath());
        holder.image.setImageBitmap(b);
    }

    @Override
    public int getItemCount() {

        return imageList.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        protected final ImageView image;

        public ViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.image_grid_view);
        }
    }
}
