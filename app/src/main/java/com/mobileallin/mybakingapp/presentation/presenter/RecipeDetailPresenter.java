package com.mobileallin.mybakingapp.presentation.presenter;

import android.os.Bundle;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mobileallin.mybakingapp.dagger.component.MyBakingAppComponent;
import com.mobileallin.mybakingapp.interactor.RecipeDetailInteractor;
import com.mobileallin.mybakingapp.navigation.Router;
import com.mobileallin.mybakingapp.presentation.view.BaseRecipeDetailsView;
import com.mobileallin.mybakingapp.utils.Keys;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by Dawid on 2017-11-07.
 */
@InjectViewState
public class RecipeDetailPresenter extends MvpPresenter<BaseRecipeDetailsView>{

    @Inject
    Router router;

    @Inject
    RecipeDetailInteractor recipeDetailInteractor;

    private Disposable disposable;
    private long id;

    public RecipeDetailPresenter(MyBakingAppComponent component){
        Log.d(getClass().getSimpleName(), "RecipeDetailPresenter called!");

        component.inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        Bundle args = router.getArguments(getClass().getName());
        id = args.getLong(Keys.ID);
    }

    @Override
    public void attachView(BaseRecipeDetailsView view) {
        super.attachView(view);
        Log.d(getClass().getSimpleName(), "attachView called!");

        disposable = recipeDetailInteractor.getRecipe(id)
                .subscribe(recipe -> getViewState().showChosenRecipeDetails(recipe));

        Log.d(getClass().getSimpleName(), disposable.toString());

    }

    @Override
    public void detachView(BaseRecipeDetailsView view) {
        super.detachView(view);
        disposable.dispose();
    }
}
