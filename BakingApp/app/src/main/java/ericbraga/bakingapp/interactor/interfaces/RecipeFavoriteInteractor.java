package ericbraga.bakingapp.interactor.interfaces;

import ericbraga.bakingapp.model.Recipe;

public interface RecipeFavoriteInteractor {
    void execute(Recipe recipe, RecipeFavoriteInteractor.Callback callback);

    interface Callback {
        void onUpdateRecipe(Recipe recipe);
        void onError(String message);
    }
}
