package com.mobileallin.mybakingapp.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mobileallin.mybakingapp.MyBakingApp;
import com.mobileallin.mybakingapp.R;

public class RecipesListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_list);
        ((MyBakingApp) getApplication()).getMyBakingAppComponent().inject(this);
    }
}
