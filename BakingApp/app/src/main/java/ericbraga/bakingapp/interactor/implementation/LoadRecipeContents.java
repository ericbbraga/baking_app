package ericbraga.bakingapp.interactor.implementation;

import java.util.List;

import ericbraga.bakingapp.interactor.interfaces.ImageRepository;
import ericbraga.bakingapp.interactor.interfaces.RecipeDisplayInteractor;
import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.model.Step;

public class LoadRecipeContents<T> implements RecipeDisplayInteractor<T>,
        ImageRepository.ImageCallback<T> {

    private final ImageRepository<T> mRepository;
    private Callback<T> mOuterCallback;

    public LoadRecipeContents(ImageRepository<T> repository) {
        mRepository = repository;
    }

    @Override
    public void loadRecipeInformation(Recipe recipe, Callback<T> callback) {
        mOuterCallback = callback;

        String background = getBackground(recipe);
        if (background != null) {
            mRepository.loadImageFrom(background, this);
        } else {
            mOuterCallback.displayFallbackImage();
        }
    }

    private String getBackground(Recipe recipe) {
        List<Step> steps = recipe.getSteps();
        String background = null;

        int size = steps.size();
        if (size != 0) {
            Step step = steps.get(size - 1);
            String thumbnail = step.getThumbnailUrl();
            String videoUrl = step.getVideoUrl();

            if (thumbnail != null && !thumbnail.trim().isEmpty()) {
                background = thumbnail;
            } else if (videoUrl != null && !videoUrl.trim().isEmpty()) {
                background = videoUrl;
            }

        }

        return background;
    }

    @Override
    public void onSuccess(T image) {
        mOuterCallback.onSuccess(image);
    }

    @Override
    public void onError(String message) {
        mOuterCallback.onError(message);
    }
}
