package com.mobileallin.mybakingapp.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.mobileallin.mybakingapp.data.model.Recipe;

import java.util.List;

/**
 * Created by Dawid on 2017-10-11.
 */

@StateStrategyType(OneExecutionStateStrategy.class)
public interface RecipesListView extends MvpView {

    void showRecipes(List<Recipe> list);

    void showLoading();

    void hideLoading();

    void hideSwipeRefresh();

    void showNetworkError();

}
