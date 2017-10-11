package com.mobileallin.mybakingapp.interactor;

import com.mobileallin.mybakingapp.data.model.Recipe;
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

    public RecipesInteractor(HttpClient client, Scheduler ioScheduler, RecipesService recipesService) {
        this.client = client;
        this.recipesService = recipesService;
        this.ioScheduler = ioScheduler;
    }


    public Observable<List<Recipe>> subscribeToRecipes() {
        return recipesService.getRecipeNames()
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler);
    }
}
