package com.mbf5923.test.testapplication.DI.Module;

import com.mbf5923.test.testapplication.Detail.DetailFragment;
import com.mbf5923.test.testapplication.Home.HomeFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class MainActivityBuilder {

    @ContributesAndroidInjector(modules = {HomeFragmentModule.class})
    abstract HomeFragment bindMainFragment();

    @ContributesAndroidInjector(modules = {DetailFragmentModule.class})
    abstract DetailFragment bindActionsFragment();
}
