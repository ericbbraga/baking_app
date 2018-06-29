package ericbraga.bakingapp.presenter;

import java.util.List;

import ericbraga.bakingapp.model.Ingredient;
import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.model.Step;
import ericbraga.bakingapp.mvpcontract.DescriptionRecipeContract;

public class DescriptionPresenter implements DescriptionRecipeContract.Presenter {
    private final Recipe mRecipe;
    private DescriptionRecipeContract.View mView;

    public DescriptionPresenter(Recipe recipe) {
        mRecipe = recipe;
    }

    @Override
    public void attachView(DescriptionRecipeContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void onResume() {
        if (mView != null) {
            setRecipeName(mRecipe.getName());
            setIngredients();
            setSteps();
        }
    }

    private void setRecipeName(String recipe) {
        if (mView != null) {
            mView.showDescriptionRecipe(recipe);
        }
    }

    private void setIngredients() {
        if (mView != null) {
            List<Ingredient> ingredients = mRecipe.getIngredients();
            mView.showIngredients(ingredients);
        }
    }

    private void setSteps() {
        if (mView != null) {
            mView.showSteps(mRecipe.getSteps());
        }
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onClickedItem(Step step) {
        mView.showMoreStepInfo(step);
    }
}
