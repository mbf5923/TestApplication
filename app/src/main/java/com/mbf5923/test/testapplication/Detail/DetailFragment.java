package com.mbf5923.test.testapplication.Detail;


import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.mbf5923.test.testapplication.Base.BaseFragment;
import com.mbf5923.test.testapplication.R;
import com.mbf5923.test.testapplication.Data.Detail;
import com.squareup.picasso.Picasso;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

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
    @Inject
     DetailPresenter presenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void setupViews() {
        tvtitle.setText(getArguments().getString("title"));
        Picasso.get().load(getArguments().getString("thumb")).into(imglogo);

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
        Picasso.get().load(details.getThumbnailUrl()).into(imglogo);

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
