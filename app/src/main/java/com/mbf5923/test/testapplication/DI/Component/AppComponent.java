package com.mbf5923.test.testapplication.DI.Component;

import com.mbf5923.test.testapplication.DI.Module.ActivityBuilder;
import com.mbf5923.test.testapplication.DI.Module.ApiModule;
import com.mbf5923.test.testapplication.DI.Module.AppModule;
import com.mbf5923.test.testapplication.DI.Scope.ApplicationScope;
import com.mbf5923.test.testapplication.MyApp;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@ApplicationScope
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ApiModule.class,
        ActivityBuilder.class})
public interface AppComponent extends AndroidInjector<MyApp> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<MyApp> {
    }
}
