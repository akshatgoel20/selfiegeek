package com.starksky.selfiegeek.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.starksky.selfiegeek.R;
import com.starksky.selfiegeek.adapter.GridImagesAdapter;
import com.starksky.selfiegeek.framework.iface.ResponseListener;
import com.starksky.selfiegeek.model.ImageList;
import com.starksky.selfiegeek.utils.FetchPhoto;
import com.starksky.selfiegeek.utils.RecyclerItemClickListener;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements ResponseListener {
    private static final String TAG = MainActivityFragment.class.getSimpleName();
    RecyclerView imageRecyclerView;
    GridImagesAdapter gridImagesAdapter;
    TextView emptyText ;


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        imageRecyclerView = (RecyclerView) rootView.findViewById(R.id.gridViewImages);
        emptyText = (TextView)rootView.findViewById(R.id.textview_fragment);
        gridImagesAdapter = new GridImagesAdapter();
        imageRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("pos", position);
                Fragment fragment = new ImageViewFragment();
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, fragment).addToBackStack(TAG);
                fragmentTransaction.commit();
            }
        }));
        loadContent();
        return rootView;
    }

    private void loadContent() {
        new FetchPhoto(this);

            emptyText.setVisibility(View.GONE);
            imageRecyclerView.setLayoutManager(new GridLayoutManager(imageRecyclerView.getContext(), 4));
            imageRecyclerView.setAdapter(gridImagesAdapter);


    }

    @Override
    public void updateAdapter() {
        gridImagesAdapter.updateAd();
        //    imageRecyclerView.setAdapter(gridImagesAdapter);
    }
}
