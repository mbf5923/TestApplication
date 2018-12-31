package com.mbf5923.test.testapplication.Data;

import java.util.List;

import io.reactivex.Single;

public class LocalDataSource implements DataSource {
    @Override
    public Single<List<Contents>> getContents() {
        return null;
    }

    @Override
    public Single<List<Contents>> getLoaclContents() {
        return null;
    }

    @Override
    public Single<Detail> getDetail(String id) {
        return null;
    }

    @Override
    public Single<Detail> getLoaclDetail(String id) {
        return null;
    }


}
