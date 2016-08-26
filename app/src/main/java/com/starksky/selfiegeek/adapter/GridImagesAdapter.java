package com.starksky.selfiegeek.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.starksky.selfiegeek.R;

/**
 * Created by NTQK0716 on 2016-08-26.
 */
public class GridImagesAdapter  extends RecyclerView.Adapter<GridImagesAdapter.ViewHolder>{

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gridimage, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.image.setImageResource(R.mipmap.ic_launcher);
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        protected final ImageView image;

        public ViewHolder(View view) {
            super(view);
            image = (ImageView)view.findViewById(R.id.image_grid_view);
        }
    }
}
