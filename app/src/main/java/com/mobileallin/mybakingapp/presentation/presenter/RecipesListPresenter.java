package com.mobileallin.mybakingapp.presentation.presenter;

import android.util.Log;

import com.arellomobile.mvp.MvpPresenter;
import com.mobileallin.mybakingapp.data.model.Recipe;
import com.mobileallin.mybakingapp.interactor.RecipesInteractor;
import com.mobileallin.mybakingapp.presentation.view.RecipesListView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Class responsible for showing the data in the main view
 */

public class RecipesListPresenter extends MvpPresenter<RecipesListView> {

    private Disposable disposable;
    private List<Recipe> recipesList;
    @Inject
    RecipesInteractor recipesInteractor;


    @Override
    public void attachView(RecipesListView view) {
        super.attachView(view);
        disposable = recipesInteractor.subscribeToRecipes()
                .subscribe(result -> {
                    this.recipesList = result;
                    getViewState().showRecipes(result);
                    Log.d("recipesList", recipesList.toString());
                });
    }
}
