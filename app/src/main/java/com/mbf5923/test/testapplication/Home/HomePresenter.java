package com.mbf5923.test.testapplication.Home;

import android.os.AsyncTask;
import androidx.annotation.NonNull;

import com.mbf5923.test.testapplication.Data.Contents;
import com.mbf5923.test.testapplication.Data.DB.AppDataBase;
import com.mbf5923.test.testapplication.Data.Repository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View view;
    private Repository dataSource;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public HomePresenter(Repository dataSource) {
        this.dataSource = dataSource;
    }

    @Inject
    AppDataBase localDataSource;

    @Override
    public void GetContentsList() {
        dataSource.getContents(view.getViewContext())
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
                        AsyncTask.execute(new Runnable() {
                            @Override
                            public void run() {
                                localDataSource.contentsDao().insertContents(contents);
                            }
                        });

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
    public void attachView(@NonNull HomeContract.View view) {
        this.view = view;
        GetContentsList();
    }

    @Override
    public void dettachView() {
        this.view = null;
        compositeDisposable.clear();
    }
}
