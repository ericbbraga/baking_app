package ericbraga.bakingapp.environment.common.interfaces;

import ericbraga.bakingapp.boundary.RecipeCollection;

public interface Repository {

    interface RepositoryCallback {
        void listRecipesContent(RecipeCollection recipes);
        void errorMessage(String message);
    }

    void listRecipes(RepositoryCallback callback);
}