package com.starksky.selfiegeek.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.starksky.selfiegeek.R;
import com.starksky.selfiegeek.adapter.GridImagesAdapter;
import com.starksky.selfiegeek.framework.iface.ResponseListener;
import com.starksky.selfiegeek.model.ImageList;
import com.starksky.selfiegeek.utils.FetchPhoto;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements ResponseListener {

    RecyclerView imageRecyclerView;
    GridImagesAdapter gridImagesAdapter;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        imageRecyclerView = (RecyclerView) rootView.findViewById(R.id.gridViewImages);
        gridImagesAdapter = new GridImagesAdapter();
        loadContent();
        return rootView;
    }

    private void loadContent() {
        new FetchPhoto(this);
        if (ImageList.getImageList() != null) {
            imageRecyclerView.setLayoutManager(new GridLayoutManager(imageRecyclerView.getContext(), 4));
            imageRecyclerView.setAdapter(gridImagesAdapter);
        } else {

        }
    }

    @Override
    public void updateAdapter() {
        gridImagesAdapter.updateAd();
    //    imageRecyclerView.setAdapter(gridImagesAdapter);
    }
}
