package com.mobileallin.mybakingapp.network.service;

import com.mobileallin.mybakingapp.data.model.Recipe;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Dawid on 2017-10-12.
 */

public class Recipes implements RecipesService {
    @Override
    public Observable<Long> putRecipes(List<Recipe> recipeList) {
        return null;
    }

    @Override
    public Observable<List<Recipe>> getRecipeNames() {
        return null;
    }

    @Override
    public Observable<Recipe> getRecipe(long id) {
        return null;
    }
}
