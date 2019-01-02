package com.mbf5923.test.testapplication.DI.Module;

import android.content.Context;

import com.mbf5923.test.testapplication.DI.Quantifier.ApplicationContext;
import com.mbf5923.test.testapplication.DI.Scope.ApplicationScope;

import com.mbf5923.test.testapplication.Data.Repository;
import com.mbf5923.test.testapplication.Data.Worker.DaggerWorkerFactory;
import com.mbf5923.test.testapplication.MyApp;

import androidx.work.WorkerFactory;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    Repository apiClientImp;


    @Provides
    @ApplicationScope
    @ApplicationContext
    Context provideContext(MyApp application) {
        return application.getApplicationContext();
    }

    @Provides
    @ApplicationScope
    WorkerFactory provideWorkerFactory(Repository apiClientImp)
    {
        return new DaggerWorkerFactory(apiClientImp);
    }
}
