package com.mbf5923.test.testapplication.retrofit;

import com.mbf5923.test.testapplication.models.contentsModel;
import com.mbf5923.test.testapplication.models.onecontentModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface GetDataService {
    @GET("/photos")
    Single<List<contentsModel>> getContents();

    @GET
    Single<onecontentModel> getcontent(@Url String url);
}
