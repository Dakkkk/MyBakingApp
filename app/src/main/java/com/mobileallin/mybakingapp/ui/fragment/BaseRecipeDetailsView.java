package com.mobileallin.mybakingapp.ui.fragment;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.mobileallin.mybakingapp.data.model.Recipe;

/**
 * Created by Dawid on 2017-11-13.
 */

interface BaseRecipeDetailsView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showChosenRecipeDetails(Recipe recipe);
}
