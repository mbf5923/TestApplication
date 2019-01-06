package com.mbf5923.test.testapplication.Data;

import android.content.Context;

import java.util.List;

import io.reactivex.Single;

public interface DataSource {
    Single<List<Contents>> getContents(Context ctx);

    Single<List<Contents>> getLoaclContents();

    Single<Detail> getDetail(String id);

    Single<Detail> getLoaclDetail(int id);
}
