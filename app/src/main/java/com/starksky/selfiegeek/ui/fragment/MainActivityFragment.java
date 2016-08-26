package com.starksky.selfiegeek.ui.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.starksky.selfiegeek.R;
import com.starksky.selfiegeek.adapter.GridImagesAdapter;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    RecyclerView imageRecyclerView ;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_main, container, false);
        imageRecyclerView = (RecyclerView)rootView.findViewById(R.id.gridViewImages);
        loadContent();
        return rootView;
    }

    private void loadContent(){
    imageRecyclerView.setLayoutManager( new GridLayoutManager(imageRecyclerView.getContext(),3));
        imageRecyclerView.setAdapter(new GridImagesAdapter());
    }
}
