package ericbraga.bakingapp.environment.common.android.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ericbraga.bakingapp.R;
import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.model.RecipeCollection;

public class RecipesCardAdapter extends RecyclerView.Adapter<RecipesCardAdapter.RecipesHolder> {

    private RecipeCollection mCollection;

    private RecipesCardItemHandler mHandler;

    public interface RecipesCardItemHandler {
        void onItemClick(Recipe recipe);
    }

    public RecipesCardAdapter() {
        mCollection = new RecipeCollection();
        mHandler = null;
    }


    public void setRecipeCollection(RecipeCollection collection) {
        mCollection = collection;
        notifyDataSetChanged();
    }

    public void setRecipeHandler(RecipesCardItemHandler handler) {
        mHandler = handler;
    }

    @NonNull
    @Override
    public RecipesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.baking_element, parent, false);
        return new RecipesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipesHolder holder, int position) {
        Recipe recipe = mCollection.getElement(position);
        holder.mRecipesTitle.setText(recipe.getName());
    }

    @Override
    public int getItemCount() {
        return mCollection.size();
    }

    class RecipesHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        final ImageView mRecipesImageView;
        final TextView mRecipesTitle;

        RecipesHolder(View itemView) {
            super(itemView);
            mRecipesImageView = itemView.findViewById(R.id.baking_element_banner);
            mRecipesTitle = itemView.findViewById(R.id.baking_element_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mHandler != null) {
                int position = getAdapterPosition();
                Recipe recipe = mCollection.getElement(position);
                mHandler.onItemClick(recipe);
            }
        }
    }
}
