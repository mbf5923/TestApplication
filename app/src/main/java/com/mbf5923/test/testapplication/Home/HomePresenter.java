package com.mbf5923.test.testapplication.Home;

import com.mbf5923.test.testapplication.Data.Contents;
import com.mbf5923.test.testapplication.Data.DataSource;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View view;
    private DataSource dataSource;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public HomePresenter(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void GetContentsList() {
        dataSource.getContents()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Contents>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                        view.ShowProgress();
                    }

                    @Override
                    public void onSuccess(List<Contents> contents) {
                        view.ShowContentsList(contents);
                        view.Hiderogress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.ShowError(e.toString());
                        view.Hiderogress();
                    }
                });
    }

    @Override
    public void attachView(HomeContract.View view) {
        this.view = view;
        GetContentsList();
    }

    @Override
    public void dettachView() {
        this.view = null;
        compositeDisposable.clear();
    }
}
