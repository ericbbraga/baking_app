package ericbraga.bakingapp.presenter.interfaces;

import ericbraga.bakingapp.environment.activities.MainActivity;
import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.model.RecipeCollection;

public interface DisplayRecipesContract {

    interface Presenter {
        void attachView(DisplayRecipesContract.View view);
        void detachView();
        void onResume();
        void onPause();
        void recipeChosen(Recipe recipe);
        void favoriteItem(Recipe recipe, boolean starred);
        void setRouter(Router mainActivity);
    }

    interface View {
        void display(RecipeCollection recipes);
        void showError(String message);
        void showEmptyList();
        void hideEmptyList();
        void updateRecipeStatus(Recipe recipe);
    }

    interface Router {
        void displayNextScreen(Recipe recipe);
    }
}
