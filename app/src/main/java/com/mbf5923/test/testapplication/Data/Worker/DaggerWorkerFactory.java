package com.mbf5923.test.testapplication.Data.Worker;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.mbf5923.test.testapplication.Data.Repository;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import androidx.work.ListenableWorker;
import androidx.work.WorkerFactory;
import androidx.work.WorkerParameters;

public class DaggerWorkerFactory extends WorkerFactory {

    Repository apiClientImp;


    public DaggerWorkerFactory(Repository apiClientImp) {
        this.apiClientImp = apiClientImp;

    }

    @Nullable
    @Override
    public ListenableWorker createWorker(@NonNull Context appContext, @NonNull String workerClassName, @NonNull WorkerParameters workerParameters) {
        ListenableWorker instance = null;
        try {
            Class workerClass= Class.forName(workerClassName).asSubclass(ListenableWorker.class);
            Constructor constructor = workerClass.getDeclaredConstructor(Context.class,WorkerParameters.class);
            instance = (ListenableWorker) constructor.newInstance(appContext, workerParameters);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return instance;
    }

}
