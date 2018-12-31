package com.mbf5923.test.testapplication.Data;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerDataSource implements DataSource {
    private ApiService apiService;

    public ServerDataSource() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.connectTimeout(150, TimeUnit.SECONDS);
        client.readTimeout(150, TimeUnit.SECONDS);
        client.writeTimeout(150, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    @Override
    public Single<List<Contents>> getContents() {
        return apiService.getContents();
    }

    @Override
    public Single<List<Contents>> getLoaclContents() {
        return null;
    }

    @Override
    public Single<Detail> getDetail(String id) {
        String url = "/photos/" + id;
        return apiService.getDetail(url);
    }

    @Override
    public Single<Detail> getLoaclDetail(String id) {
        return null;
    }
}
