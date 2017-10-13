package com.mobileallin.mybakingapp.dagger;

import com.mobileallin.mybakingapp.presentation.presenter.RecipesListPresenter;
import com.mobileallin.mybakingapp.ui.activity.RecipesListActivity;
import com.mobileallin.mybakingapp.ui.fragment.RecipesListFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Dawid on 2017-10-11.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(RecipesListActivity obj);

    void inject(RecipesListFragment obj);

    void inject(RecipesListPresenter obj);

/*
    RecipesService RecipesService();
*/
}


