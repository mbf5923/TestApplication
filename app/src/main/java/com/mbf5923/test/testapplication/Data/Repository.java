package com.mbf5923.test.testapplication.Data;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class Repository implements DataSource {
    private ServerDataSource serverDataSource = new ServerDataSource();
    private LocalDataSource localDataSource = new LocalDataSource();
    private final ApiService networkApiService;

    @Inject
    public Repository(ApiService networkApiService) {
        this.networkApiService = networkApiService;
    }

    @Override
    public Single<List<Contents>> getContents() {
        return serverDataSource.getContents();
    }

    @Override
    public Single<List<Contents>> getLoaclContents() {
        return localDataSource.getContents();
    }

    @Override
    public Single<Detail> getDetail(String id) {
        return serverDataSource.getDetail(id);
    }

    @Override
    public Single<Detail> getLoaclDetail(String id) {
        return serverDataSource.getLoaclDetail(id);
    }
}
