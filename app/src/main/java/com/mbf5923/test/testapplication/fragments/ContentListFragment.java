package com.mbf5923.test.testapplication.fragments;


import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.mbf5923.test.testapplication.R;
import com.mbf5923.test.testapplication.activities.MainActivity;
import com.mbf5923.test.testapplication.adapters.contentsAdapter;
import com.mbf5923.test.testapplication.models.contentsModel;
import com.mbf5923.test.testapplication.presenter.ContentListFragmentPresenter;
import com.mbf5923.test.testapplication.retrofit.GetDataService;
import com.mbf5923.test.testapplication.retrofit.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContentListFragment extends Fragment implements ContentListFragmentPresenter.View {

    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.reccontents)
    RecyclerView reccontents;
    private View root;
    private contentsAdapter contentsAdapter;
    private List<contentsModel> contentsModels = new ArrayList<>();

    public ContentListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root=inflater.inflate(R.layout.fragment_content_list, container, false);
        ButterKnife.bind(this, root);
        initialadapter();
        requestdata();

        // Inflate the layout for this fragment
        return root;
    }

    private void initialadapter() {
        contentsAdapter = new contentsAdapter(contentsModels) {
            @Override
            public void load() {
                //implement load more
                if (contentsModels.size() > 11) {

                }


            }
        };
        contentsAdapter.setOnItemClickListener(new contentsAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                DetailFragment detailFragment=new DetailFragment();
                Bundle bundle = new Bundle();
                bundle.putString("title", contentsModels.get(position).getTitle());
                bundle.putString("thumb", contentsModels.get(position).getThumbnailUrl());
                bundle.putInt("id", contentsModels.get(position).getId());
                detailFragment.setArguments(bundle);
                ((MainActivity)getActivity()).addExpedFragment(detailFragment);


            }

            @Override
            public void onItemLongClick(int position, View v) {
                Log.e("e", contentsModels.get(position).getTitle());
            }
        });

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        layoutManager.setStackFromEnd(false);
        reccontents.setLayoutManager(layoutManager);
        reccontents.setAdapter(contentsAdapter);
    }
    
    private void requestdata(){
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        service.getContents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<contentsModel>>() {
                    @Override
                    public void onSuccess(List<contentsModel> value) {
                        generatecontentList(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("error",e.getMessage());
                    }
                });





    }

    private void generatecontentList(List<contentsModel> body) {
        progressBar.setVisibility(View.GONE);

        for(int i=0;i<body.size();i++){
            contentsModels.add(body.get(i));
        }
        contentsAdapter.notifyDataSetChanged();
    }

    @Override
    public void changefragment(String info) {

    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

}
