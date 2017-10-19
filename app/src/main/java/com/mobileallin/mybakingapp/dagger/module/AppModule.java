package com.mobileallin.mybakingapp.dagger.module;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.GsonBuilder;
import com.mobileallin.mybakingapp.dagger.ApplicationContext;
import com.mobileallin.mybakingapp.dagger.IoScheduler;
import com.mobileallin.mybakingapp.dagger.UiScheduler;
import com.mobileallin.mybakingapp.data.RecipesConverter;
import com.mobileallin.mybakingapp.data.database.RecipesDbHelper;
import com.mobileallin.mybakingapp.helper.time.TimeController;
import com.mobileallin.mybakingapp.interactor.RecipesInteractor;
import com.mobileallin.mybakingapp.network.AutoValueGsonFactory;
import com.mobileallin.mybakingapp.network.HttpClient;
import com.mobileallin.mybakingapp.repositories.RecipesRepository;
import com.mobileallin.mybakingapp.repositories.impl.DatabaseRecipesRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dawid on 2017-10-11.
 */

@Module
public class AppModule {


    private final Context context;
    private static final String SHARED_PREFS_NAME = "app_preferences";

    public AppModule(Context context) {
        this.context = context;
    }

    @ApplicationContext
    @Singleton
    @Provides
    public Context provideApplicationContext() {
        return context;
    }

    @IoScheduler
    @Singleton
    @Provides
    public Scheduler provideIoScheduler() {
        return Schedulers.io();
    }

    @UiScheduler
    @Singleton
    @Provides
    public Scheduler provideUiScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Singleton
    @Provides
    public RecipesInteractor provideRecipesInteractor(RecipesRepository recipesRepository, HttpClient client, TimeController timeController,
                                                      @IoScheduler Scheduler ioScheduler, @UiScheduler Scheduler uiScheduler) {
        return new RecipesInteractor(recipesRepository, client, timeController, ioScheduler, uiScheduler);
    }

    @Singleton
    @Provides
    public RecipesRepository provideRecipesRepository(RecipesDbHelper recipesDbHelper) {
        RecipesConverter converter = new RecipesConverter();
        return new DatabaseRecipesRepository(recipesDbHelper, converter);
    }

    @Singleton
    @Provides
    public HttpClient provideClient() {
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(
                new GsonBuilder().registerTypeAdapterFactory(AutoValueGsonFactory.create())
                        .create());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HttpClient.ENDPOINT)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .build();

        return retrofit.create(HttpClient.class);
    }

    @Singleton
    @Provides
    public TimeController provideTimeController(SharedPreferences pref) {
        return new TimeController(pref);
    }

    @Singleton
    @Provides
    public SharedPreferences providePreferences(@ApplicationContext Context context) {
        return context.getSharedPreferences(SHARED_PREFS_NAME, Activity.MODE_PRIVATE);
    }


}
