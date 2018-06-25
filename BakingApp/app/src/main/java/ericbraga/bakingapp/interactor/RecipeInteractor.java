package ericbraga.bakingapp.interactor;

import ericbraga.bakingapp.model.RecipeCollection;

public interface RecipeInteractor {
    void load();

    interface Callback {
        void onResultReceive(RecipeCollection collection);
        void onError(String message);
    }
}
