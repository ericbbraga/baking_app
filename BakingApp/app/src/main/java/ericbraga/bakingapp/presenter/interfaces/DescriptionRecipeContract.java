package ericbraga.bakingapp.presenter.interfaces;

import java.util.List;

import ericbraga.bakingapp.model.Ingredient;
import ericbraga.bakingapp.model.Step;

public interface DescriptionRecipeContract {
    interface Presenter {
        void attachView(DescriptionRecipeContract.View view);
        void detachView();
        void onResume();
        void onPause();
        void showMoreSteps(int position);
    }

    interface View {
        void showDescriptionRecipe(String recipeName);
        void showIngredients(List<Ingredient> ingredients);
        void showSteps(List<Step> steps);
    }

    interface Router {
        void showMoreStepInfo(List<Step> steps, int position);
    }

}
