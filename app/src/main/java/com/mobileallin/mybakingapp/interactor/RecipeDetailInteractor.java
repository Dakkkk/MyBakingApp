package com.mobileallin.mybakingapp.interactor;

import com.mobileallin.mybakingapp.dagger.IoScheduler;
import com.mobileallin.mybakingapp.dagger.UiScheduler;
import com.mobileallin.mybakingapp.data.model.DetailAction;
import com.mobileallin.mybakingapp.data.model.Recipe;
import com.mobileallin.mybakingapp.repositories.RecipesRepository;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Created by Dawid on 2017-11-13.
 */

public class RecipeDetailInteractor {
    private RecipesRepository repo;
    private Scheduler ioScheduler;
    private Scheduler uiScheduler;

    public RecipeDetailInteractor(RecipesRepository repo, @IoScheduler Scheduler ioScheduler,
                                   @UiScheduler Scheduler uiScheduler){
        this.repo = repo;
        this.ioScheduler = ioScheduler;
        this.uiScheduler = uiScheduler;
    }

    public Observable<Recipe> getRecipe(long id){
        return repo.getRecipe(id)
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler);
    }

    public Observable<DetailAction> getStep(long recipeId, int stepNum){
        return repo.getDetailAction(recipeId, stepNum)
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler);
    }
}
