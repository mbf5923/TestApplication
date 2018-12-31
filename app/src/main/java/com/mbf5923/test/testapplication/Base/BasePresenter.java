package com.mbf5923.test.testapplication.Base;

public interface BasePresenter<T extends BaseView> {
    void attachView(T view);

    void dettachView();
}
