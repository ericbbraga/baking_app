package ericbraga.bakingapp.interactor.interfaces;

import ericbraga.bakingapp.model.Recipe;

public interface RecipeDisplayInteractor<T> {
    void loadRecipeInformation(Recipe recipe, RecipeDisplayInteractor.Callback<T> callback);

    interface Callback<T> {
        void onSuccess(T image);
        void displayFallbackImage();
        void onError(String message);
    }
}
