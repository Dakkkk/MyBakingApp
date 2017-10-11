package com.mobileallin.mybakingapp.interactor;

import com.mobileallin.mybakingapp.network.HttpClient;

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

    public RecipesInteractor(HttpClient client, Scheduler ioScheduler) {
        this.client = client;
        this.ioScheduler = ioScheduler;
    }



}
