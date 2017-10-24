package com.mobileallin.mybakingapp.ui.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobileallin.mybakingapp.R;
import com.mobileallin.mybakingapp.dagger.module.GlideApp;
import com.mobileallin.mybakingapp.data.model.Recipe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dawid on 2017-10-19.
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {
    private Context context;
    private View emptyView;
    private List<Recipe> items;
    private IOnItemClickListener itemClickListener;

    public interface IOnItemClickListener{
        void onItemClick(int position);
    }

    public void setItemClickListener(IOnItemClickListener listener){
        itemClickListener = listener;
    }

    public RecipesAdapter(Context context, View emptyView){
        this(context, new ArrayList<>(), emptyView);
    }

    public RecipesAdapter(Context context, List<Recipe> items, View emptyView){
        this.context = context;
        this.items = items;
        this.emptyView = emptyView;
        showEmptyView(items);
    }

    public void setItems(List<Recipe> items){
        this.items = items;
        showEmptyView(items);
        notifyDataSetChanged();
    }

    @Override
    public RecipesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipes_list_item,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(items.get(position));

    }

    @Override
    public int getItemCount() {
        return items == null? 0:items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.recipe_image)
        ImageView recipeImageView;
        @BindView(R.id.recipe_name)
        TextView recipeNameView;
        @BindView(R.id.recipe_servings)
        TextView servingsView;

        public ViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        public void bind(Recipe recipe){
            GlideApp.with(context)
                    .load(recipe.imageUrl())
                    .placeholder(R.drawable.ic_cake_black_32dp)
                    .error(R.drawable.ic_cake_black_32dp)
                    .into(recipeImageView);

            recipeNameView.setText(recipe.name());

            int servings = recipe.servings();
            String servingsStr = servings <= 0? "-":String.valueOf(servings);
            servingsView.setText(servingsStr);
        }


        @Override
        public void onClick(View v) {
            if (itemClickListener != null){
                itemClickListener.onItemClick(getAdapterPosition());
            }
        }
    }

    /** util methods */

    private void showEmptyView(List<Recipe> list){
        if (emptyView != null) {
            int visibility = (list == null || list.isEmpty()) ? View.VISIBLE : View.INVISIBLE;
            this.emptyView.setVisibility(visibility);
        }
    }
}
