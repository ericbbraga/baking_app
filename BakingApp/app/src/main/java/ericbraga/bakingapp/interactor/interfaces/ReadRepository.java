package ericbraga.bakingapp.interactor.interfaces;

import ericbraga.bakingapp.model.RecipeCollection;

public interface ReadRepository {

    interface RepositoryCallback {
        void listRecipesContent(RecipeCollection recipes);
        void errorMessage(String message);
    }

    void listRecipes(RepositoryCallback callback);
}