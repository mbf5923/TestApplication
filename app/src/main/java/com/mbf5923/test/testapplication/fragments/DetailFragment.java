package com.mbf5923.test.testapplication.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mbf5923.test.testapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    @BindView(R.id.tvtitle) TextView tvtitle;
    @BindView(R.id.imglogo)
    ImageView imglogo;
    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root=inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, root);
        tvtitle.setText(getArguments().getString("title"));
        Glide.with(getContext()).load(getArguments().getString("thumb")).into(imglogo);
        // Inflate the layout for this fragment
        return root;
    }



}
