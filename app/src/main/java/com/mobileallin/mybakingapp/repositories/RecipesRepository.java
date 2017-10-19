package com.mobileallin.mybakingapp.repositories;

import com.mobileallin.mybakingapp.data.model.Recipe;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Dawid on 2017-10-19.
 */

public interface RecipesRepository {

    Observable<Long> putRecipes(List<Recipe> recipeList);

    Observable<List<Recipe>> getRecipeNames();

    Observable<Recipe> getRecipe(long id);
}
