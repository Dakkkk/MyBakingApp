package com.mobileallin.mybakingapp.data.database;

import com.mobileallin.mybakingapp.data.model.Recipe;
import com.mobileallin.mybakingapp.network.service.RecipesService;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Dawid on 2017-10-13.
 */

public class RecipesDb implements RecipesService {

    RecipesDb(){

    }

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
