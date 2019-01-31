package com.mbf5923.test.testapplication.Home;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mbf5923.test.testapplication.R;
import com.mbf5923.test.testapplication.Data.Contents;

import java.util.List;

import com.mbf5923.test.testapplication.Base.BaseFragment;
import com.mbf5923.test.testapplication.activities.MainActivity;
import com.mbf5923.test.testapplication.Detail.DetailFragment;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements HomeContract.View {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.reccontents)
    RecyclerView reccontents;
    @Inject
    HomePresenter presenter;




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {

        }
    }

    @Override
    public void setupViews() {
        reccontents.setLayoutManager(new LinearLayoutManager(getViewContext(), RecyclerView.VERTICAL, false));
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_content_list;
    }

    @Override
    public void ShowContentsList(List<Contents> Contents) {

        reccontents.setAdapter(new contentsAdapter(Contents) {
            @Override
            public void load() {

            }

            @Override
            public void onclicklistener(int position, View v) {
                DetailFragment detailFragment = new DetailFragment();
                Bundle bundle = new Bundle();
                bundle.putString("title", Contents.get(position).getTitle());
                bundle.putString("thumb", Contents.get(position).getThumbnailUrl());
                bundle.putInt("id", Contents.get(position).getId());
                detailFragment.setArguments(bundle);
                ((MainActivity) getActivity()).addExpedFragment(detailFragment);
            }
        });
    }

    @Override
    public void ShowError(String error) {
        Toast.makeText(getViewContext(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void ShowProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void Hiderogress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public Context getViewContext() {
        return getContext();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.attachView(this);

    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.dettachView();
    }
}
