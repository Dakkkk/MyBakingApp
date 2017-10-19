package com.mobileallin.mybakingapp;

import android.app.Application;

import com.mobileallin.mybakingapp.dagger.component.AppComponent;
import com.mobileallin.mybakingapp.dagger.module.AppModule;
import com.mobileallin.mybakingapp.dagger.component.DaggerAppComponent;

/**
 * Created by Dawid on 2017-10-11.
 */

public class MyBakingApp extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = createAppComponent();
    }

    protected AppComponent createAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
