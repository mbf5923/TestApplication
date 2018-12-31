package com.mbf5923.test.testapplication.Detail;

import com.mbf5923.test.testapplication.Base.BasePresenter;
import com.mbf5923.test.testapplication.Base.BaseView;
import com.mbf5923.test.testapplication.Data.Detail;

import java.util.List;

public interface DetailContract {
    interface View extends BaseView {
        void ShowDetail(Detail details);

        void ShowError(String error);

        void ShowProgress();

        void Hiderogress();

    }

    interface Presenter extends BasePresenter<View> {
        void GetDetail(String id);
    }
}
