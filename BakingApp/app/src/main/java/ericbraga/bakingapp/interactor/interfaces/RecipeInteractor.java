package ericbraga.bakingapp.interactor.interfaces;

import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.model.RecipeCollection;

public interface RecipeInteractor {
    void load(RecipeInteractor.Callback callback);
    void changeRecipeStarred(Recipe recipe);

    interface Callback {
        void onResultReceive(RecipeCollection collection);
        void onUpdateRecipe();
        void onError(String message);
    }
}
