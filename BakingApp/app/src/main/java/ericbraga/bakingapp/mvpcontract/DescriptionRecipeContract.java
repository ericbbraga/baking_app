package ericbraga.bakingapp.mvpcontract;

import android.graphics.Bitmap;

import java.util.Iterator;
import java.util.List;

import ericbraga.bakingapp.model.Ingredient;
import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.model.RecipeCollection;
import ericbraga.bakingapp.model.Step;

public interface DescriptionRecipeContract {
    interface Presenter {
        void attachView(DescriptionRecipeContract.View view);
        void detachView();
        void onResume();
        void onPause();
        void onClickedItem(Step step);
    }

    interface View {
        void showImageRecipe(Bitmap recipeBitmap);
        void showDescriptionRecipe(String recipeName);
        void showIngredients(List<Ingredient> ingredients);
        void showSteps(List<Step>steps);
        void showMoreStepInfo(Step step);
    }

}
