package com.mbf5923.test.testapplication.Base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;

public abstract class BaseFragment extends DaggerFragment {
    public View rootview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootview == null) {
            rootview = inflater.inflate(getLayout(), container, false);
            ButterKnife.bind(this, rootview);
            setupViews();
        }
        return rootview;
    }

    public abstract void setupViews();

    public abstract int getLayout();
}
