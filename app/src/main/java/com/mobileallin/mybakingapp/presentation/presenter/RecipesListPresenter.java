package com.mobileallin.mybakingapp.presentation.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mobileallin.mybakingapp.dagger.component.MyBakingAppComponent;
import com.mobileallin.mybakingapp.data.model.Recipe;
import com.mobileallin.mybakingapp.interactor.RecipesInteractor;
import com.mobileallin.mybakingapp.presentation.view.RecipesListView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Class responsible for showing the data in the main view
 */
@InjectViewState
public class RecipesListPresenter extends MvpPresenter<RecipesListView> {

    private static final String TAG = "RecipesListPresenter";
    private Disposable disposable;
    private List<Recipe> recipesList;

    @Inject
    RecipesInteractor recipesInteractor;

    public RecipesListPresenter(MyBakingAppComponent component) {
        component.inject(this);

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
        disposable = recipesInteractor.subscribeToRecipes()
                .subscribe(result -> {
                    this.recipesList = result;
                    getViewState().showRecipes(result);
                    Log.d("recipesList", recipesList.toString());
                });
    }
}
