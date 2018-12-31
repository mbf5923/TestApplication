package com.mbf5923.test.testapplication.Home;

import com.mbf5923.test.testapplication.Base.BasePresenter;
import com.mbf5923.test.testapplication.Base.BaseView;
import com.mbf5923.test.testapplication.Data.Contents;

import java.util.List;

public interface HomeContract {
    interface View extends BaseView {
        void ShowContentsList(List<Contents> Contents);

        void ShowError(String error);

        void ShowProgress();

        void Hiderogress();

    }

    interface Presenter extends BasePresenter<View> {
        void GetContentsList();
    }
}
