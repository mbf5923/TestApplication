package com.mbf5923.test.testapplication.DI.Annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.work.ListenableWorker;
import dagger.MapKey;

@Retention(RetentionPolicy.RUNTIME)
@MapKey
public @interface WorkerKey {
    Class<? extends ListenableWorker> value();
}
