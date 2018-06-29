package ericbraga.bakingapp.environment.android.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestFutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.HashMap;
import java.util.List;

import ericbraga.bakingapp.R;
import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.model.RecipeCollection;
import ericbraga.bakingapp.model.Step;

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
        List<Step> steps = recipe.getSteps();
        if (steps.size() != 0) {
            Step step = recipe.getSteps().get(steps.size() - 1);
            String thumbnail = step.getThumbnailUrl();
            String videoUrl = step.getVideoUrl();

            String backgroudHolder = getBackgroudHolder(thumbnail, videoUrl);

            if (backgroudHolder != null) {
                Glide.with(holder.itemView)
                        .load(backgroudHolder)
                        .listener(new RecipesCardRequestListener(holder, recipe))
                        .submit();
            }
        }
    }

    private String getBackgroudHolder(String thumbnail, String videoUrl) {
        if (thumbnail != null && !thumbnail.trim().isEmpty()) {
            return thumbnail;
        } else if (videoUrl != null && !videoUrl.trim().isEmpty()) {
            return videoUrl;
        }

        return null;
    }

    private class RecipesCardRequestListener implements RequestListener<Drawable> {

        private final RecipesHolder mHolder;
        private final Recipe mRecipe;

        public RecipesCardRequestListener(RecipesHolder holder, Recipe recipe) {
            mHolder = holder;
            mRecipe = recipe;
        }

        @Override
        public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                    Target<Drawable> target, boolean isFirstResource) {
            return false;
        }

        @Override
        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target,
                                       DataSource dataSource, boolean isFirstResource) {

            mHolder.mRecipesTitle.setText(mRecipe.getName());
            mHolder.mRecipesImageView.setImageDrawable(resource);
            return true;
        }
    }

    @Override
    public int getItemCount() {
        return mCollection.size();
    }

    class RecipesHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

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
