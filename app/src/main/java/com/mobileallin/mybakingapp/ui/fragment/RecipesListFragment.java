package com.mobileallin.mybakingapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.mobileallin.mybakingapp.MyBakingApp;
import com.mobileallin.mybakingapp.R;
import com.mobileallin.mybakingapp.dagger.AppComponent;
import com.mobileallin.mybakingapp.data.model.Recipe;
import com.mobileallin.mybakingapp.presentation.presenter.RecipesListPresenter;
import com.mobileallin.mybakingapp.presentation.view.RecipesListView;

import java.util.List;

/**
 * Created by Dawid on 2017-10-10.
 */

public class RecipesListFragment extends MvpAppCompatFragment implements RecipesListView{

    @InjectPresenter
    RecipesListPresenter recipesListPresenter;

    @ProvidePresenter
    RecipesListPresenter providePresenter(){
        AppComponent component = ((MyBakingApp)getActivity().getApplication()).getAppComponent();
        return new RecipesListPresenter(component);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipes_list, container, false);

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void showRecipes(List<Recipe> list) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void hideSwipeRefresh() {

    }

    @Override
    public void showNetworkError() {

    }
}
