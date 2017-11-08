package com.mobileallin.mybakingapp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.mobileallin.mybakingapp.R;

/**
 * Created by Dawid on 2017-10-10.
 */

public class RecipeDetailActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
    }

    @Override
    public void onBackPressed() {
        int fragments = getFragmentManager().getBackStackEntryCount();
        if (fragments == 1) {
            // make layout invisible since last fragment will be removed
        }
        super.onBackPressed();
    }
}
