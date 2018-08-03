package ericbraga.bakingapp.environment.interfaces;

import ericbraga.bakingapp.model.RecipeCollection;

public interface ExternalRepository {

    interface RepositoryCallback {
        void listRecipesContent(RecipeCollection recipes);
        void errorMessage(String message);
    }

    void listRecipes(RepositoryCallback callback);
}
