package ericbraga.bakingapp.presenter.interfaces;

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
    }

    interface View {
        void display(RecipeCollection recipes);
        void showError(String message);
        void clearFavoriteItem();
        void showEmptyList();
        void hideEmptyList();
    }

    interface Router {
        void displayNextScreen(Recipe recipe);
    }
}
