package com.mobileallin.mybakingapp.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobileallin.mybakingapp.R;
import com.mobileallin.mybakingapp.data.model.DetailAction;
import com.mobileallin.mybakingapp.data.model.Recipe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dawid on 2017-11-13.
 */

public class DetailActionsAdapter extends RecyclerView.Adapter<DetailActionsAdapter.ViewHolder> {

    private static final int INGREDIENT_VIEW_TYPE = 0;
    private static final int DETAIL_ACTION_VIEW_TYPE = 1;

    public DetailActionsAdapter () {
        this(new ArrayList<>());
    }

    public DetailActionsAdapter(List<DetailAction> detailActionsList) {
        this.detailActionsList = detailActionsList;
    }

    private List<DetailAction> detailActionsList;

    @Override
    public DetailActionsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_detail_item,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DetailActionsAdapter.ViewHolder holder, int position) {
        switch(getItemViewType(position)){
            case INGREDIENT_VIEW_TYPE:
                holder.bindIngredientsList();
                return;
            case DETAIL_ACTION_VIEW_TYPE:
                holder.bindDetailAction(detailActionsList.get(position - 1));
                return;
            default:
        }
    }

    public void setChosenRecipeData(Recipe recipe){
        detailActionsList = recipe.detailActions();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return detailActionsList == null? 0:detailActionsList.size() + 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.action_name)
        TextView actionNameView;

        public ViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        public void bindIngredientsList(){
            actionNameView.setText(R.string.recipe_ingredients);
        }

        public void bindDetailAction(DetailAction detailAction){
            actionNameView.setText(detailAction.shortDescription());
        }

        @Override
        public void onClick(View v) {
            Log.d(getClass().getSimpleName(), "item clicked");
        }
    }
}
