package ericbraga.bakingapp.presenter.interfaces;

import ericbraga.bakingapp.environment.common.repositories.local.models.RecipeLocal;
import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.model.RecipeCollection;

public interface DisplayRecipesContract {

    interface Presenter {
        void attachView(DisplayRecipesContract.View view);
        void detachView();
        void onResume();
        void onPause();
        void recipeChosen(Recipe recipe);
    }

    interface View {
        void display(RecipeCollection recipes);
        void showError(String message);
        void displayNextScreen(Recipe recipe);
        void showEmptyList();
    }
}
