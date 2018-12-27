package com.mbf5923.test.testapplication.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mbf5923.test.testapplication.R;
import com.mbf5923.test.testapplication.models.contentsModel;
import com.mbf5923.test.testapplication.models.onecontentModel;
import com.mbf5923.test.testapplication.retrofit.GetDataService;
import com.mbf5923.test.testapplication.retrofit.RetrofitClientInstance;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    @BindView(R.id.tvtitle) TextView tvtitle;
    @BindView(R.id.imglogo)
    ImageView imglogo;
    @BindView(R.id.prgsreload)
    ProgressBar prgsreload;
    private int postid=0;

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
        postid=getArguments().getInt("id");

        // Inflate the layout for this fragment
        return root;
    }

    @OnClick(R.id.btnreload)
    public void reload(View view) {
        prgsreload.setVisibility(View.VISIBLE);
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        service.getcontent("/photos/"+postid)
                .subscribeOn(Schedulers.io())

                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<onecontentModel>() {
                    @Override
                    public void onSuccess(onecontentModel value) {
                        managereload(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("error",e.getMessage());
                    }
                });
    }

    private void managereload(onecontentModel value) {
        prgsreload.setVisibility(View.GONE);
        tvtitle.setText(value.getTitle());
        Glide.with(getContext()).load(value.getThumbnailUrl()).into(imglogo);
    }

}
