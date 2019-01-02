package com.mbf5923.test.testapplication.DI.Module;

import com.mbf5923.test.testapplication.DI.Scope.ActivityScope;
import com.mbf5923.test.testapplication.activities.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules ={MainActivityBuilder.class})
    abstract MainActivity bindMainActivity();
}
