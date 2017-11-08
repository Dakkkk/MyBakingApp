package com.mobileallin.mybakingapp.presentation.presenter;

import android.os.Bundle;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mobileallin.mybakingapp.dagger.component.MyBakingAppComponent;
import com.mobileallin.mybakingapp.data.model.Recipe;
import com.mobileallin.mybakingapp.interactor.RecipesInteractor;
import com.mobileallin.mybakingapp.navigation.Command;
import com.mobileallin.mybakingapp.navigation.Router;
import com.mobileallin.mybakingapp.presentation.view.RecipesListView;
import com.mobileallin.mybakingapp.utils.Keys;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Class responsible for showing the data in the main view
 */
@InjectViewState
public class RecipesListPresenter extends MvpPresenter<RecipesListView> {

    private static final String TAG = "RecipesListPresenter";
    private List<Recipe> recipesList;
    private RecipesListView view;

    @Inject
    RecipesInteractor recipesInteractor;
    @Inject
    Router router;



    public RecipesListPresenter(MyBakingAppComponent component, RecipesListView view) {
        component.inject(this);
        this.view = view;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        recipesInteractor.updateRecipes()
                .doOnSubscribe(result -> getViewState().showLoading())
                .doAfterTerminate(() -> getViewState().hideLoading())
                .subscribe(aLong -> {
                }, throwable -> Log.d(TAG, throwable.toString()), () -> {
                });

    }


    @Override
    public void attachView(RecipesListView view) {
        super.attachView(view);

        //Dispose the observer to avoid memory leaks
        Disposable disposable = recipesInteractor.subscribeToRecipes()
                .subscribe(result -> {
                    this.recipesList = result;
                    getViewState().showRecipes(result);
                    Log.d("recipesList", recipesList.toString());
                });
    }

    public void enterDetailActivity(int position) {
/*
       view.enterDatailActivity(position);
*/
        Log.d(RecipesListPresenter.class.getSimpleName(), "enterDetailActivity called");
        Bundle args = new Bundle();
        args.putLong(Keys.ID, recipesList.get(position).id());
        router.putCommand(Command.SHOW_RECIPE_DETAILS, RecipeDetailPresenter.class.getName(), args);
    }
}
