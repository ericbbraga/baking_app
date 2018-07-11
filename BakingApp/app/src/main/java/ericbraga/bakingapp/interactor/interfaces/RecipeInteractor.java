package ericbraga.bakingapp.interactor.interfaces;

import ericbraga.bakingapp.model.RecipeCollection;

public interface RecipeInteractor {
    void load(RecipeInteractor.Callback callback);

    interface Callback {
        void onResultReceive(RecipeCollection collection);
        void onError(String message);
    }
}