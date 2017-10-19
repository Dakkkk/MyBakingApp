package com.mobileallin.mybakingapp;

import android.app.Application;

import com.mobileallin.mybakingapp.dagger.component.MyBakingAppComponent;
import com.mobileallin.mybakingapp.dagger.module.AppModule;
import com.mobileallin.mybakingapp.dagger.component.DaggerMyBakingAppComponent;

/**
 * Created by Dawid on 2017-10-11.
 */

public class MyBakingApp extends Application {

    private MyBakingAppComponent myBakingAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        myBakingAppComponent = createAppComponent();
    }

    protected MyBakingAppComponent createAppComponent() {
        return DaggerMyBakingAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public MyBakingAppComponent getMyBakingAppComponent() {
        return myBakingAppComponent;
    }
}
