package com.starksky.selfiegeek.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.starksky.selfiegeek.R;
import com.starksky.selfiegeek.model.ImageList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by NTQK0716 on 2016-08-26.
 */
public class GridImagesAdapter extends RecyclerView.Adapter<GridImagesAdapter.ViewHolder> {
    ExifInterface xif;
    ArrayList<File> imageList = ImageList.getImageList();

    public void updateAd() {
        imageList = ImageList.getImageList();
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gridimage, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Bitmap b;
        if (isVideo(imageList.get(position).toString())) {
            b = ThumbnailUtils.createVideoThumbnail(imageList.get(position).getAbsolutePath(), MediaStore.Video.Thumbnails.MICRO_KIND);
            try {
                xif =new ExifInterface(imageList.get(position).getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
           int orientation =  xif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_UNDEFINED);
            b = rotateBitmap(b, orientation);
        } else {
            //   b = BitmapFactory.decodeFile(imageList[position].getAbsolutePath());
            final int THUMBSIZE = 128;
            b = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(imageList.get(position).getAbsolutePath()), THUMBSIZE, THUMBSIZE);

        }
        holder.image.setImageBitmap(b);
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int orientation) {

        Matrix matrix = new Matrix();
        switch (orientation) {
            case ExifInterface.ORIENTATION_NORMAL:
                return bitmap;
            case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                matrix.setScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                matrix.setRotate(180);
                break;
            case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                matrix.setRotate(180);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_TRANSPOSE:
                matrix.setRotate(90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.setRotate(90);
                break;
            case ExifInterface.ORIENTATION_TRANSVERSE:
                matrix.setRotate(-90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                matrix.setRotate(-90);
                break;
            default:
                return bitmap;
        }
        try {
            Bitmap bmRotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            bitmap.recycle();
            return bmRotated;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int getItemCount() {

        return imageList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        protected final ImageView image;

        public ViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.image_grid_view);
        }
    }

    boolean isVideo(String str) {
        if (str.contains(".mp4")) {
            return true;
        }
        return false;
    }
}
