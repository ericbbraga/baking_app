package ericbraga.bakingapp.presenter.interfaces;

import android.graphics.Bitmap;

import java.util.List;

import ericbraga.bakingapp.model.Ingredient;
import ericbraga.bakingapp.model.Step;

public interface DescriptionRecipeContract {
    interface Presenter<T> {
        void attachView(DescriptionRecipeContract.View<T> view);
        void detachView();
        void onResume();
        void onPause();
        void showMoreSteps();
    }

    interface View<T> {
        void showImageRecipe(T image);
        void showDescriptionRecipe(String recipeName);
        void showIngredients(List<Ingredient> ingredients);
        void showStepPreview(T image);
        void showStepName(String title);
        void showMoreStepInfo(Step step);
    }

}
