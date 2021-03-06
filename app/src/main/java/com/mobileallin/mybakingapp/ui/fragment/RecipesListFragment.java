package com.mobileallin.mybakingapp.ui.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.mobileallin.mybakingapp.MyBakingApp;
import com.mobileallin.mybakingapp.R;
import com.mobileallin.mybakingapp.dagger.component.MyBakingAppComponent;
import com.mobileallin.mybakingapp.data.model.Recipe;
import com.mobileallin.mybakingapp.presentation.presenter.RecipesListPresenter;
import com.mobileallin.mybakingapp.presentation.view.RecipesListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dawid on 2017-10-10.
 */

public class RecipesListFragment extends MvpAppCompatFragment implements RecipesListView {

    @InjectPresenter
    RecipesListPresenter recipesListPresenter;

    private RecipesAdapter recipeAdapter;

    @BindView(R.id.recipe_list)
    RecyclerView recipesRecyclerView;
    @BindView(R.id.empty_view)
    TextView emptyListView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.shield)
    FrameLayout shield;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshView;

    private Parcelable recipesListState;
    private static final String RECIPES_LIST_STATE = "recipes_list_state";

    @ProvidePresenter
    RecipesListPresenter providePresenter() {
        MyBakingAppComponent component = ((MyBakingApp) getActivity().getApplication()).getMyBakingAppComponent();
        return new RecipesListPresenter(component, this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null){
            recipesListState = savedInstanceState.getParcelable(RECIPES_LIST_STATE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipes_list, container, false);
        ButterKnife.bind(this, view);
        recipeAdapter = new RecipesAdapter(getContext(), emptyListView);
        recipeAdapter.setItemClickListener(position -> recipesListPresenter.enterDetailActivity(position));
        int columns = getResources().getInteger(R.integer.recipes_list_columns);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), columns);
        recipesRecyclerView.setLayoutManager(layoutManager);
        recipesRecyclerView.setAdapter(recipeAdapter);

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(RECIPES_LIST_STATE,
                recipesRecyclerView.getLayoutManager().onSaveInstanceState());
    }

    @Override
    public void showRecipes(List<Recipe> list) {
        recipeAdapter.setItems(list);
        if (recipesListState != null){
            recipesRecyclerView.getLayoutManager().onRestoreInstanceState(recipesListState);
        }
    }

    @Override
    public void enterDatailActivity(int itemPosition) {
      /*  Activity activity = getActivity();
        if (isAdded() && activity != null) {
        Toast.makeText(getActivity(), "Item nr" + itemPosition, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(activity, RecipeDetailActivity.class);
        startActivity(intent);
        }*/
    }

    @Override
    public void showLoading() {
        enableProgressBar(true);
    }

    @Override
    public void hideLoading() {
        enableProgressBar(false);
    }

    @Override
    public void hideSwipeRefresh() {

    }

    @Override
    public void showNetworkError() {
        Toast.makeText(getContext(), R.string.connection_error, Toast.LENGTH_SHORT).show();

    }

    private void enableProgressBar(boolean enable) {
        int visibility = enable ? View.VISIBLE : View.GONE;
        progressBar.setVisibility(visibility);
       /* shield.setVisibility(visibility);*/
        shield.setVisibility(View.INVISIBLE);
    }
}
