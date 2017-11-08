package com.mobileallin.mybakingapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mobileallin.mybakingapp.MyBakingApp;
import com.mobileallin.mybakingapp.R;
import com.mobileallin.mybakingapp.navigation.Command;
import com.mobileallin.mybakingapp.navigation.INavigator;
import com.mobileallin.mybakingapp.navigation.Router;

import javax.inject.Inject;

public class RecipesListActivity extends AppCompatActivity implements INavigator {

    @Inject
    Router router;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_list);
        ((MyBakingApp) getApplication()).getMyBakingAppComponent().inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        router.attachToNavigator(this);
    }

    @Override
    public void handleCommand(Command command) {
        Log.d(RecipesListActivity.class.getSimpleName(), "handleCommand called");

        switch (command) {
            case SHOW_RECIPE_DETAILS: {
                showRecipeDetails();
                break;
            }

            default:
        }
    }

    private void showRecipeDetails() {
        Log.d(RecipesListActivity.class.getSimpleName(), "showRecipeDetails called");
        Intent intent = new Intent(this, RecipeDetailActivity.class);
        startActivity(intent);
    }
}
