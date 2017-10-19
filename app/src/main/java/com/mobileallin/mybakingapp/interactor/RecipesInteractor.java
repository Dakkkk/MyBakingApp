package com.mobileallin.mybakingapp.interactor;

import com.mobileallin.mybakingapp.dagger.IoScheduler;
import com.mobileallin.mybakingapp.dagger.UiScheduler;
import com.mobileallin.mybakingapp.data.model.Recipe;
import com.mobileallin.mybakingapp.helper.time.TimeController;
import com.mobileallin.mybakingapp.network.HttpClient;
import com.mobileallin.mybakingapp.repositories.RecipesRepository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * "Model/Controller". RecipesInteractor will fetch data from web service.
 * After getting the data, the interactor will send the data to the presenter.
 * Thus, making changes in your UI.
 */

public class RecipesInteractor {

    private HttpClient client;
    private Scheduler ioScheduler;
    private Scheduler uiScheduler;
    private RecipesRepository recipesRepository;
    private TimeController timeController;

    public RecipesInteractor(RecipesRepository recipesRepository, HttpClient client,
                             TimeController timeController,
                             @IoScheduler Scheduler ioScheduler, @UiScheduler Scheduler uiScheduler) {
        this.recipesRepository = recipesRepository;
        this.client = client;
        this.timeController = timeController;
        this.ioScheduler = ioScheduler;
        this.uiScheduler = uiScheduler;
    }

    public Observable<Long> updateRecipes(){
        return timeController.isItTimeToUpdate()
                .filter(result -> result == true)
                .concatMap(result -> client.getRecipes())
                .concatMap(recipes -> recipesRepository.putRecipes(recipes))
                .doOnNext(aLong -> timeController.saveTimeOfLastUpdate())
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler);
    }

    public Observable<List<Recipe>> subscribeToRecipes() {
        return recipesRepository.getRecipeNames()
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler);
    }


}
