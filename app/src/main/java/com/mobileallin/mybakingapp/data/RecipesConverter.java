package com.mobileallin.mybakingapp.data;

import android.content.ContentValues;
import android.database.Cursor;

import com.mobileallin.mybakingapp.data.model.Recipe;
import com.mobileallin.mybakingapp.data.database.RecipesContract.RecipeEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dawid on 2017-10-13.
 */

public class RecipesConverter {

    public ContentValues toContentValues(Recipe recipe){
        ContentValues cv = new ContentValues();
        cv.put(RecipeEntry.COL_NAME, recipe.name());
        cv.put(RecipeEntry.COL_IMAGE_URL, recipe.imageUrl());
        cv.put(RecipeEntry.COL_SERVINGS, recipe.servings());
        return cv;
    }

    public List<Recipe> toRecipesNameList(Cursor cursor) {
        List<Recipe> list = new ArrayList<>();
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                long id = getLong(cursor, RecipeEntry._ID);
                String name = getString(cursor, RecipeEntry.COL_NAME);
                String imageUrl = getString(cursor, RecipeEntry.COL_IMAGE_URL);
                int servings = getInt(cursor, RecipeEntry.COL_SERVINGS);
                Recipe recipe = Recipe.builder()
                        .setId(id)
                        .setName(name)
                        .setServings(servings)
                        .setImageUrl(imageUrl)
                        .build();
                list.add(recipe);
            }while(cursor.moveToNext());
        }
        return list;
    }

    /** helper methods */

    private long getLong(Cursor cursor, String colName){
        int colInd = cursor.getColumnIndex(colName);
        return cursor.getLong(colInd);
    }

    private int getInt(Cursor cursor, String colName){
        int colInd = cursor.getColumnIndex(colName);
        return cursor.getInt(colInd);
    }

    private String getString(Cursor cursor, String colName){
        int colInd = cursor.getColumnIndex(colName);
        return cursor.getString(colInd);
    }

    private float getFloat(Cursor cursor, String colName){
        int colInd = cursor.getColumnIndex(colName);
        return cursor.getFloat(colInd);
    }
}
