package com.mbf5923.test.testapplication.Data;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiService {
    @GET("/photos")
    Single<List<Contents>> getContents();

    @GET
    Single<Detail> getDetail(@Url String url);
}
