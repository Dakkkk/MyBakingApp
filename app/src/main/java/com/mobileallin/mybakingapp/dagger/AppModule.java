package com.mobileallin.mybakingapp.dagger;

import android.content.Context;

import com.mobileallin.mybakingapp.data.database.RecipesDbHelper;
import com.mobileallin.mybakingapp.helper.time.TimeController;
import com.mobileallin.mybakingapp.interactor.RecipesInteractor;
import com.mobileallin.mybakingapp.network.HttpClient;
import com.mobileallin.mybakingapp.network.service.RecipesService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

/**
 * Created by Dawid on 2017-10-11.
 */

@Module
public class AppModule {

    private final Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @ApplicationContext
    @Singleton
    @Provides
    public Context provideApplicationContext(){
        return context;
    }

    @Singleton
    @Provides
    public RecipesInteractor provideRecipesInteractor(RecipesService recipesService, HttpClient client, TimeController timeController,
                                                      @IoScheduler Scheduler ioScheduler, @UiScheduler Scheduler uiScheduler){
        return new RecipesInteractor(recipesService, client, timeController, ioScheduler, uiScheduler);
    }

    @Singleton
    @Provides
    public RecipesService provideRecipesService(RecipesDbHelper recipesDbHelper){
/*
        return new RecipesDb(recipesDbHelper);
*/
        return null;
    }
}
