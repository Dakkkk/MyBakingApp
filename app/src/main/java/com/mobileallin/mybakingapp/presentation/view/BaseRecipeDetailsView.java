package com.mobileallin.mybakingapp.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.mobileallin.mybakingapp.data.model.Recipe;

/**
 * Created by Dawid on 2017-11-13.
 */

public interface BaseRecipeDetailsView extends MvpView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showChosenRecipeDetails(Recipe recipe);
}
