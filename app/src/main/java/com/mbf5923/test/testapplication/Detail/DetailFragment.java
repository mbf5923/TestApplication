package com.mbf5923.test.testapplication.Detail;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mbf5923.test.testapplication.Base.BaseFragment;
import com.mbf5923.test.testapplication.Data.Repository;
import com.mbf5923.test.testapplication.R;
import com.mbf5923.test.testapplication.Data.Detail;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends BaseFragment implements DetailContract.View {

    @BindView(R.id.tvtitle)
    TextView tvtitle;
    @BindView(R.id.imglogo)
    ImageView imglogo;
    @BindView(R.id.prgsreload)
    ProgressBar prgsreload;
    private int postid = 0;
    private DetailContract.Presenter presenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new DetailPresenter(new Repository());

    }

    @Override
    public void setupViews() {
        tvtitle.setText(getArguments().getString("title"));
        Glide.with(getContext()).load(getArguments().getString("thumb")).into(imglogo);
        postid = getArguments().getInt("id");
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_detail;
    }

    @OnClick(R.id.btnreload)
    public void reload(View view) {
        presenter.GetDetail(String.valueOf(postid));
    }


    @Override
    public void ShowDetail(Detail details) {
        tvtitle.setText(details.getTitle());
        Glide.with(getContext()).load(details.getThumbnailUrl()).into(imglogo);
    }

    @Override
    public void ShowError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ShowProgress() {
        prgsreload.setVisibility(View.VISIBLE);
    }

    @Override
    public void Hiderogress() {
        prgsreload.setVisibility(View.GONE);
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
