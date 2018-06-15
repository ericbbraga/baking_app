package ericbraga.bakingapp.interactor;

import java.util.List;

import ericbraga.bakingapp.model.Recipe;

public interface RecipeInteractor {
    void load();

    interface Callback {
        void onResultReceive(List<Recipe> recipes);
        void onError(String message);
    }
}
