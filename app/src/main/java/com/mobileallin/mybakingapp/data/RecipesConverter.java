package com.mobileallin.mybakingapp.data;

import android.content.ContentValues;
import android.database.Cursor;

import com.mobileallin.mybakingapp.data.database.RecipesContract.RecipeEntry;
import com.mobileallin.mybakingapp.data.database.RecipesContract.StepEntry;
import com.mobileallin.mybakingapp.data.model.DetailAction;
import com.mobileallin.mybakingapp.data.model.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dawid on 2017-10-13.
 */

public class RecipesConverter {

    public ContentValues toContentValues(Recipe recipe) {
        ContentValues cv = new ContentValues();
        cv.put(RecipeEntry.COL_NAME, recipe.name());
        cv.put(RecipeEntry.COL_IMAGE_URL, recipe.imageUrl());
        cv.put(RecipeEntry.COL_SERVINGS, recipe.servings());
        return cv;
    }

    public List<Recipe> toRecipesNameList(Cursor cursor) {
        List<Recipe> list = new ArrayList<>();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                long id = getLong(cursor, RecipeEntry._ID);
                String name = getString(cursor, RecipeEntry.COL_NAME);
                String imageUrl = getString(cursor, RecipeEntry.COL_IMAGE_URL);
                int servings = getInt(cursor, RecipeEntry.COL_SERVINGS);

                Recipe recipe = Recipe.builder()
                        .setId(id)
                        .setName(name)
                        .setServings(servings)
                        .setDetailActions(new ArrayList<>())
                        .setImageUrl(imageUrl)
                        .build();
                list.add(recipe);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public Recipe toRecipe(Cursor recipeCursor, Cursor ingredientCursor, Cursor stepCursor) {
        List<DetailAction> stepList = new ArrayList<>();

        String recipeName = "";
        int recipeServings = 0;
        if (recipeCursor.getCount() > 0) {
            recipeCursor.moveToFirst();
            recipeName = getString(recipeCursor, RecipeEntry.COL_NAME);
            recipeServings = getInt(recipeCursor, RecipeEntry.COL_SERVINGS);
        }


        if (stepCursor.getCount() > 0) {
            stepCursor.moveToFirst();
            do {
                stepList.add(toDetailAction(stepCursor));
            } while (stepCursor.moveToNext());
        }

        return Recipe.builder()
                .setId(0)
                .setName(recipeName)
                .setDetailActions(stepList)
                .setServings(recipeServings)
                .setImageUrl("")
                .build();
    }

    public DetailAction toDetailAction(Cursor cursor) {
        int num = 0;
        String shortDescription = "";
        String description = "";
        String videoUrl = "";
        String thumbnailUrl = "";

        if (cursor.getCount() > 0) {
            num = getInt(cursor, StepEntry.COL_STEP_NUM);
            shortDescription = getString(cursor, StepEntry.COL_SHORT_DESCRIPTION);
            description = getString(cursor, StepEntry.COL_DESCRIPTION);
            videoUrl = getString(cursor, StepEntry.COL_VIDEO_URL);
            thumbnailUrl = getString(cursor, StepEntry.COL_THUMBNAIL_URL);
        }

        return DetailAction.builder()
                .setId(num)
                .setShortDescription(shortDescription)
                .setDescription(description)
                .setVideoURL(videoUrl)
                .setThumbnailURL(thumbnailUrl)
                .build();
    }

    /**
     * helper methods
     */

    private long getLong(Cursor cursor, String colName) {
        int colInd = cursor.getColumnIndex(colName);
        return cursor.getLong(colInd);
    }

    private int getInt(Cursor cursor, String colName) {
        int colInd = cursor.getColumnIndex(colName);
        return cursor.getInt(colInd);
    }

    private String getString(Cursor cursor, String colName) {
        int colInd = cursor.getColumnIndex(colName);
        return cursor.getString(colInd);
    }

    private float getFloat(Cursor cursor, String colName) {
        int colInd = cursor.getColumnIndex(colName);
        return cursor.getFloat(colInd);
    }


}
