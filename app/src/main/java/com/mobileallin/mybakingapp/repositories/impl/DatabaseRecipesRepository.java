package com.mobileallin.mybakingapp.repositories.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.mobileallin.mybakingapp.data.RecipesConverter;
import com.mobileallin.mybakingapp.data.RecipesEvent;
import com.mobileallin.mybakingapp.data.database.RecipesContract;
import com.mobileallin.mybakingapp.data.database.RecipesDbHelper;
import com.mobileallin.mybakingapp.data.model.DetailAction;
import com.mobileallin.mybakingapp.data.model.Recipe;
import com.mobileallin.mybakingapp.repositories.RecipesRepository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Dawid on 2017-10-19.
 */

public class DatabaseRecipesRepository implements RecipesRepository {

    private final String TAG = getClass().getName();
    private final RecipesDbHelper recipesDbHelper;
    private final RecipesConverter converter;
    private final PublishSubject<RecipesEvent> publishSubject;


    public DatabaseRecipesRepository(RecipesDbHelper recipesDbHelper, RecipesConverter converter) {
        this.recipesDbHelper = recipesDbHelper;
        this.converter = converter;
        this.publishSubject = PublishSubject.create();

    }

    @Override
    public Observable<Long> putRecipes(List<Recipe> recipeList) {
        return Observable.fromCallable(() -> {
            long result = 0L;
            SQLiteDatabase db = recipesDbHelper.getWritableDatabase();
            db.delete(RecipesContract.RecipeEntry.TABLE_NAME, null, null);
            db.delete(RecipesContract.StepEntry.TABLE_NAME, null, null);
            db.delete(RecipesContract.IngredientEntry.TABLE_NAME, null, null);
            try {
                db.beginTransaction();
                for (Recipe recipe : recipeList) {
                    ContentValues cv = converter.toContentValues(recipe);
                    long recipeId = db.insert(RecipesContract.RecipeEntry.TABLE_NAME, null, cv);
                }
                db.setTransactionSuccessful();
            } catch (Exception e) {
                result = -1L;
            } finally {
                db.endTransaction();
            }
            return result;
        });
    }

    @Override
    public Observable<List<Recipe>> getRecipeNames() {
        return Observable.concat(Observable.just(RecipesEvent.SUBSCRIBE), publishSubject)
                .doOnNext(repoEvent -> Log.d(TAG, "event: " + repoEvent.toString()))
                .concatMap(repoEvent -> getAllRecipeNames());
    }

    @Override
    public Observable<Recipe> getRecipe(long id) {
        return Observable.fromCallable(() -> {
            SQLiteDatabase db = recipesDbHelper.getReadableDatabase();

            String selection = RecipesContract.RecipeEntry._ID + " =? ";
            String[] selArgs = {Long.toString(id)};
            Cursor recipeCursor = db.query(RecipesContract.RecipeEntry.TABLE_NAME, null, selection, selArgs,
                    null, null, null);

            selection = RecipesContract.StepEntry.COL_RECIPE_ID + " =? ";
            Cursor ingredientsCursor = db.query(RecipesContract.IngredientEntry.TABLE_NAME, null, selection, selArgs,
                    null, null, null);


            selection = RecipesContract.StepEntry.COL_RECIPE_ID + " =? ";
            Cursor stepCursor = db.query(RecipesContract.StepEntry.TABLE_NAME, null, selection, selArgs,
                    null, null, null);

            Recipe recipe = converter.toRecipe(recipeCursor, ingredientsCursor, stepCursor);
            recipeCursor.close();
            ingredientsCursor.close();
            stepCursor.close();
            return recipe;
        });
    }

    @Override
    public Observable<DetailAction> getDetailAction(long id, int step) {
        return null;
    }


    /**
     * helper methods
     */

    private Observable<List<Recipe>> getAllRecipeNames() {
        return Observable.fromCallable(() -> {
            SQLiteDatabase db = recipesDbHelper.getReadableDatabase();
            Cursor cursor = db.query(RecipesContract.RecipeEntry.TABLE_NAME, null, null, null,
                    null, null, null);
            List<Recipe> list = converter.toRecipesNameList(cursor);
            cursor.close();
            return list;
        });
    }
}
