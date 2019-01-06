package com.mbf5923.test.testapplication.Data;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.mbf5923.test.testapplication.Base.BaseView;
import com.mbf5923.test.testapplication.DI.Module.ApiModule;
import com.mbf5923.test.testapplication.DI.Module.AppModule;
import com.mbf5923.test.testapplication.DI.Scope.ApplicationScope;
import com.mbf5923.test.testapplication.Data.DB.AppDataBase;
import com.mbf5923.test.testapplication.Data.DB.ContentsDao;
import com.mbf5923.test.testapplication.Home.HomeContract;
import com.mbf5923.test.testapplication.Utils.Utils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class Repository implements DataSource {

    private AppDataBase localDataSource ;
    private ApiService networkApiService;


    @Inject
    public Repository(ApiService networkApiService,AppDataBase contentsDao) {
        this.networkApiService = networkApiService;
        this.localDataSource=contentsDao;
    }

    @Override
    public Single<List<Contents>> getContents(Context ctx) {


        if(Utils.isNetworkConnected(ctx)){
            Single<List<Contents>> content= networkApiService.getContents();
            return  content;
        }else {

            return localDataSource.contentsDao().getAllContents();
        }

    }

    @Override
    public Single<List<Contents>> getLoaclContents() {
        return localDataSource.contentsDao().getAllContents();
    }

    @Override
    public Single<Detail> getDetail(String id) {
        return networkApiService.getDetail("/photos/"+id);
    }

    @Override
    public Single<Detail> getLoaclDetail(int id) {
        return localDataSource.contentsDao().getOne(id);
    }
}
