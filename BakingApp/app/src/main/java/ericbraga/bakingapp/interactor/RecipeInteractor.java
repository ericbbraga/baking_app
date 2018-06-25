package ericbraga.bakingapp.interactor;

import ericbraga.bakingapp.boundary.RecipeCollection;
import ericbraga.bakingapp.environment.common.WebRecipeCollection;

public interface RecipeInteractor {
    void load();

    interface Callback {
        void onResultReceive(RecipeCollection collection);
        void onError(String message);
    }
}
