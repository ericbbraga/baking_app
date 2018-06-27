package ericbraga.bakingapp.mvpcontract;

import android.view.View;

import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.model.RecipeCollection;

public interface DisplayRecipesContract {

    interface Presenter {
        void attachView(DisplayRecipesContract.View view);
        void detachView(DisplayRecipesContract.View view);
        void onResume();
        void onPause();
        void recipeChosen(Recipe recipe);
    }

    interface View {
        void display(RecipeCollection recipes);
        void showError(String message);
        void displayNextScreen(Recipe recipe);
    }
}
