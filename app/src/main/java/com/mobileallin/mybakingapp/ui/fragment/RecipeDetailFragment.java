package com.mobileallin.mybakingapp.ui.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.mobileallin.mybakingapp.R;
import com.mobileallin.mybakingapp.data.model.Recipe;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dawid on 2017-11-07.
 */

public class RecipeDetailFragment extends MvpAppCompatFragment implements BaseRecipeDetailsView {


    @BindView(R.id.actions_list)
    RecyclerView detailActionsRecyclerView;

    private DetailActionsAdapter detailActionsAdapter;

    private Parcelable listState;
    private static final String LIST_STATE = "list_state";

    public RecipeDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null){
            listState = savedInstanceState.getParcelable(LIST_STATE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        ButterKnife.bind(this, view);

        detailActionsAdapter = new DetailActionsAdapter();
        detailActionsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        detailActionsRecyclerView.setAdapter(detailActionsAdapter);
        return view;
    }

    @Override
    public void showChosenRecipeDetails(Recipe recipe) {
        getActivity().setTitle(recipe.name());
        detailActionsAdapter.setChosenRecipeData(recipe);
        if (listState != null){
            detailActionsRecyclerView.getLayoutManager().onRestoreInstanceState(listState);
        }
    }
}
