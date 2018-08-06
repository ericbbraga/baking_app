package ericbraga.bakingapp.environment.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import ericbraga.bakingapp.R;
import ericbraga.bakingapp.environment.factories.RecipePresenterFactory;
import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.model.RecipeCollection;
import ericbraga.bakingapp.presenter.interfaces.RecipeContract;

public class RecipesCardAdapter extends RecyclerView.Adapter<RecipesCardAdapter.RecipesHolder> {
    private final RecipePresenterFactory<Drawable> mPresenterFactory;

    private RecipeCollection mCollection;

    private RecipesCardItemCallback mCallback;

    public interface RecipesCardItemCallback {
        void onItemClick(Recipe recipe);
        void favoriteItem(Recipe recipe, boolean starred);
    }

    public RecipesCardAdapter(RecipePresenterFactory<Drawable> factory) {
        mPresenterFactory = factory;
        mCollection = null;
        mCallback = null;
    }

    public void setRecipeCollection(RecipeCollection collection) {
        mCollection = collection;
        notifyDataSetChanged();
    }

    public void setRecipeCallback(RecipesCardItemCallback handler) {
        mCallback = handler;
    }

    public void updateRecipeOnCollection(Recipe recipe) {
        if (mCollection.hasRecipe(recipe)) {
            mCollection.updateRecipe(recipe);

            int position = mCollection.getPosition(recipe.getId());
            if (position >= 0) {
                notifyItemChanged(position);
            }
        }
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
        mPresenterFactory.createPresenter(recipe).attachView(holder);
    }

    @Override
    public int getItemCount() {
        return mCollection == null ? 0 : mCollection.size();
    }

    class RecipesHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, RecipeContract.View<Drawable> {

        final View mContent;
        final ImageView mRecipesImageView;
        final ToggleButton mStarImageView;
        final TextView mRecipesTitle;
        final ProgressBar mProgressBar;

        RecipesHolder(View itemView) {
            super(itemView);
            mContent = itemView.findViewById(R.id.baking_content);
            mRecipesImageView = itemView.findViewById(R.id.baking_element_banner);
            mStarImageView = itemView.findViewById(R.id.baking_star);
            mRecipesTitle = itemView.findViewById(R.id.baking_element_title);
            mProgressBar = itemView.findViewById(R.id.baking_progress);

            mContent.setOnClickListener(this);
            mStarImageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            if (mCallback == null) {
                return;
            }

            switch (view.getId()) {
                case R.id.baking_content:
                    contentViewClick();
                    break;

                case R.id.baking_star:
                    starViewClick();
                    break;

                default:
                    break;
            }
        }

        private void contentViewClick() {
            int position = getAdapterPosition();
            Recipe recipe = mCollection.getElement(position);
            mCallback.onItemClick(recipe);
        }

        private void starViewClick() {
            boolean starred = mStarImageView.isChecked();

            int position = getAdapterPosition();
            Recipe recipe = mCollection.getElement(position);
            mCallback.favoriteItem(recipe, starred);
        }

        @Override
        public void showProgress() {
            mContent.setVisibility(View.INVISIBLE);
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void setTitleRecipe(String title) {
            mRecipesTitle.setText(title);
        }

        @Override
        public void display(Drawable image) {
            mRecipesImageView.setImageDrawable(image);
        }

        @Override
        public void showError(String error) {
            // TODO: Show error
        }

        @Override
        public void hideProgress() {
            mContent.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.INVISIBLE);
        }

        @Override
        public void configureStarred(boolean starred) {
            mStarImageView.setChecked(starred);
        }
    }
}
