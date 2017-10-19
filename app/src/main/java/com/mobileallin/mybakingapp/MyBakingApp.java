package com.mobileallin.mybakingapp;

import android.app.Application;

import com.mobileallin.mybakingapp.dagger.AppComponent;
import com.mobileallin.mybakingapp.dagger.AppModule;
import com.mobileallin.mybakingapp.dagger.DaggerAppComponent;

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
