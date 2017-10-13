package com.mobileallin.mybakingapp.interactor;

import com.mobileallin.mybakingapp.data.model.Recipe;
import com.mobileallin.mybakingapp.helper.time.TimeController;
import com.mobileallin.mybakingapp.network.HttpClient;
import com.mobileallin.mybakingapp.network.service.RecipesService;

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
    private RecipesService recipesService;
    private TimeController timeController;

    public RecipesInteractor(RecipesService recipesService, HttpClient client, TimeController timeController, Scheduler ioScheduler, Scheduler uiScheduler) {
        this.recipesService = recipesService;
        this.client = client;
        this.timeController = timeController;
        this.ioScheduler = ioScheduler;
        this.uiScheduler = uiScheduler;
    }

    public Observable<Long> updateRecipes(){
        return timeController.isItTimeToUpdate()
                .filter(result -> result == true)
                .concatMap(result -> client.getRecipes())
                .concatMap(recipes -> recipesService.putRecipes(recipes))
                .doOnNext(aLong -> timeController.saveTimeOfLastUpdate())
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler);
    }

    public Observable<List<Recipe>> subscribeToRecipes() {
        return recipesService.getRecipeNames()
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler);
    }


}
