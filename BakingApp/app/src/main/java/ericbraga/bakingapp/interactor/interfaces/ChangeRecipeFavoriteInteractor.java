package ericbraga.bakingapp.interactor.interfaces;

import ericbraga.bakingapp.model.Recipe;

public interface ChangeRecipeFavoriteInteractor {
    void execute(Recipe recipe, ChangeRecipeFavoriteInteractor.Callback callback);

    interface Callback {
        void onUpdateRecipe(Recipe recipe);
        void onError(String message);
    }
}
