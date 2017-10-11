package com.mobileallin.mybakingapp.network.service;

import com.mobileallin.mybakingapp.data.model.Recipe;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Dawid on 2017-10-11.
 */

public interface RecipesService {

    Observable<Long> putRecipes(List<Recipe> recipeList);

    Observable<List<Recipe>> getRecipeNames();

    Observable<Recipe> getRecipe(long id);

}
