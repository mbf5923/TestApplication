package com.mbf5923.test.testapplication.Detail;

import com.mbf5923.test.testapplication.Data.Contents;
import com.mbf5923.test.testapplication.Data.DataSource;
import com.mbf5923.test.testapplication.Data.Detail;
import com.mbf5923.test.testapplication.Home.HomeContract;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DetailPresenter implements DetailContract.Presenter {
    private DetailContract.View view;
    private DataSource dataSource;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public DetailPresenter(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void attachView(DetailContract.View view) {
        this.view = view;
    }

    @Override
    public void dettachView() {
        this.view = null;
    }

    @Override
    public void GetDetail(String id) {
        dataSource.getDetail(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Detail>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                        view.ShowProgress();
                    }

                    @Override
                    public void onSuccess(Detail detail) {
                        view.ShowDetail(detail);
                        view.Hiderogress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.ShowError(e.toString());
                        view.Hiderogress();
                    }
                });

    }
}
