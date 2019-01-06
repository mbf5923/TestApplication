package com.mbf5923.test.testapplication;

import android.app.Activity;
import android.support.multidex.MultiDexApplication;


import com.mbf5923.test.testapplication.DI.Component.DaggerAppComponent;

import javax.inject.Inject;

import androidx.work.Configuration;
import androidx.work.WorkManager;
import androidx.work.WorkerFactory;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class MyApp extends MultiDexApplication implements HasActivityInjector {
    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Inject
    WorkerFactory workerFactory;



    @Override
    public void onCreate() {
        super.onCreate();
//        Fabric.with(this, new Crashlytics());
//
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
        DaggerAppComponent
                .builder()
                .create(this)
                .inject(this);

        Configuration config = (new Configuration.Builder())
                .setWorkerFactory(workerFactory)
                .build();

        WorkManager.initialize(this, config);



    }



    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }
}
