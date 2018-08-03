package ericbraga.bakingapp.interactor.interfaces;

import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.model.RecipeCollection;

public interface AsyncReadRepository {

    interface RepositoryCallback {
        void listRecipesContent(RecipeCollection recipes);
        void errorMessage(String message);
    }

    interface RepositoryCallbackStarredRecipe{
        void starredRecipe(Recipe recipe);
        void errorMessage(String message);
    }

    void listRecipes(RepositoryCallback callback);
    void getStarredRecipe(RepositoryCallbackStarredRecipe callback);
}
