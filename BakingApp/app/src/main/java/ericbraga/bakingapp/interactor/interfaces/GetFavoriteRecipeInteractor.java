package ericbraga.bakingapp.interactor.interfaces;

import ericbraga.bakingapp.model.Recipe;

public interface GetFavoriteRecipeInteractor {
    void execute(Callback callback);

    interface Callback {
        void favoriteRecipe(Recipe recipe);
        void onError(String message);
    }
}
