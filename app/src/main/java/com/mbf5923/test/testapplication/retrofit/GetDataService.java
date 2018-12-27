package com.mbf5923.test.testapplication.retrofit;

import com.mbf5923.test.testapplication.models.contentsModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {
    @GET("/photos")
    Single<List<contentsModel>> getContents();
}
